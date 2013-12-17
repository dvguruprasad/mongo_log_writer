import com.google.common.base.Strings;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import java.net.UnknownHostException;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class MongoDB {
    private DB database;

    private MongoDB(DB database) {
        this.database = database;
    }

    public static MongoDB create(Map<String, String> mongoProperties) throws UnknownHostException {
        Mongo mongo;
        if (!Strings.isNullOrEmpty(mongoProperties.get("port")))
            mongo = new Mongo(mongoProperties.get("host"), parseInt(mongoProperties.get("port")));
        else
            mongo = new Mongo(mongoProperties.get("host"));
        DB database = mongo.getDB(mongoProperties.get("database"));

        if (!Strings.isNullOrEmpty(mongoProperties.get("username")) && !Strings.isNullOrEmpty(mongoProperties.get("password")))
            database.authenticate(mongoProperties.get("username"), mongoProperties.get("password").toCharArray());
        return new MongoDB(database);
    }

    public DBCollection collection(String collectionName) {
        return database.getCollection(collectionName);
    }
}
