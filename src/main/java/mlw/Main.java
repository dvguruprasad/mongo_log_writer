package mlw;

import java.net.UnknownHostException;
import java.util.Map;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.ImmutableMap.of;

public class Main {
    public static void main(String args[]) throws UnknownHostException {
        MongoDB mongoDB = MongoDB.create(mongoProperties());
        AddToLogs addToLogs = new AddToLogs(mongoDB.collection("rp_logs"));
        ToLogEntry toLogEntry = new ToLogEntry();
        String logDirectoryPath = fromNullable(System.getProperty("log.dir")).or("/Users/Thoughtworker/logs");
        new PopulateLogs(addToLogs, toLogEntry).apply(logDirectoryPath);
    }

    private static Map<String, String> mongoProperties() {
        return of("host", fromNullable(System.getProperty("host")).or("127.0.0.1"),
                "database", fromNullable(System.getProperty("database")).or("foo"),
                "port", fromNullable(System.getProperty("port")).or(""),
                "username", fromNullable(System.getProperty("username")).or(""),
                "password", fromNullable(System.getProperty("password")).or(""));
    }
}
