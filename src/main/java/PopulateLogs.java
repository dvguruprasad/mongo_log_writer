import com.google.common.base.Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static com.google.common.base.Functions.compose;
import static com.google.common.collect.ImmutableMap.of;

public class PopulateLogs implements Function<Map<String, Object>, Map<String, Object>> {
    private AddToLogs addToLogs;
    private ToLogEntry toLogEntry;

    public PopulateLogs(AddToLogs addToLogs, ToLogEntry toLogEntry) {
        this.addToLogs = addToLogs;
        this.toLogEntry = toLogEntry;
    }

    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        try {
            File logDirectory = new File((String) input.get("logFileDirectory"));
            File[] logFiles = logDirectory.listFiles();
            for (File logFile : logFiles) {
                BufferedReader br = new BufferedReader(new FileReader(logFile));
                String line;
                while ((line = br.readLine()) != null) {
                    compose(addToLogs, toLogEntry).apply(line);
                }
                br.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return of();
    }
}
