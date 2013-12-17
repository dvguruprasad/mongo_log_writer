import org.junit.Test;

import java.io.IOException;

import static com.google.common.collect.ImmutableMap.of;

public class PopulateLogsTest {
    @Test
    public void shouldPopulateLogs() throws IOException {
        AddToLogs addToLogs = new AddToLogs(MongoDB.create(of("host", "127.0.0.1", "database", "cinnamon")).collection("rp_logs"));
        ToLogEntry toLogEntry = new ToLogEntry();
        new PopulateLogs(addToLogs, toLogEntry).apply(of("logFileDirectory", (Object) "/Users/Thoughtworker/code/kroger/rp_logs/w3-banner_timing_log.11222013"));
    }
}
