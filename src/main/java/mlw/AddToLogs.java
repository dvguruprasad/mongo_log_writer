package mlw;

import com.google.common.base.Function;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.net.UnknownHostException;
import java.util.Map;

public class AddToLogs implements Function<DBObject, Void> {
    public AddToLogs(DBCollection rpLogs) {
        this.rpLogs = rpLogs;
        this.rpLogs.drop();
    }

    private final DBCollection rpLogs;

    public AddToLogs(Map<String, String> mongoProperties) throws UnknownHostException {
        rpLogs = MongoDB.create(mongoProperties).collection("rp_logs");
    }

    @Override
    public Void apply(DBObject dbObject) {
        rpLogs.insert(dbObject);
        return null;
    }
}
