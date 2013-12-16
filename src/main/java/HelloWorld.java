import com.mongodb.Mongo;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Thoughtworker on 12/15/13.
 */
public class HelloWorld {
    public static void Main(String args[]) throws UnknownHostException {
        Mongo mongo = new Mongo("127.0.0.1");
        List<String> databaseNames = mongo.getDatabaseNames();
        System.out.println(databaseNames);
    }
}
