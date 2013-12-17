package mlw;

import com.google.common.base.Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.google.common.base.Functions.compose;

public class PopulateLogs implements Function<String, Void> {
    private AddToLogs addToLogs;
    private ToLogEntry toLogEntry;

    public PopulateLogs(AddToLogs addToLogs, ToLogEntry toLogEntry) {
        this.addToLogs = addToLogs;
        this.toLogEntry = toLogEntry;
    }

    @Override
    public Void apply(String logDirectoryPath) {
        try {
            File logDirectory = new File(logDirectoryPath);
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
        return null;
    }
}
