package mlw;

import org.junit.Test;

import java.io.IOException;

import static com.google.common.collect.ImmutableMap.of;

public class PopulateLogsTest {
    @Test
    public void shouldPopulateLogs() throws IOException {
        AddToLogs addToLogs = new AddToLogs(MongoDB.create(of("host", "127.0.0.1", "database", "foo")).collection("rp_logs"));
        ToLogEntry toLogEntry = new ToLogEntry();
        new PopulateLogs(addToLogs, toLogEntry).apply("/Users/Thoughtworker/logs");
    }
}
