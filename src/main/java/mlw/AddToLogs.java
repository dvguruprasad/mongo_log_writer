package mlw;

import com.google.common.base.Function;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class AddToLogs implements Function<DBObject, Void> {
    private final DBCollection rpLogs;

    public AddToLogs(DBCollection rpLogs) {
        this.rpLogs = rpLogs;
        this.rpLogs.drop();
    }

    @Override
    public Void apply(DBObject dbObject) {
        rpLogs.insert(dbObject);
        return null;
    }
}
