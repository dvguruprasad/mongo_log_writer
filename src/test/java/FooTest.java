import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.junit.Test;
import sun.misc.Regexp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * Created by Thoughtworker on 12/15/13.
 */
public class FooTest {
    @Test
    public void foo() throws IOException {
        Mongo mongo = new Mongo("127.0.0.1");
        DB database = mongo.getDB("cinnamon");
        DBCollection rpLogs = database.getCollection("rp_logs");
        rpLogs.drop();

        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/Thoughtworker/code/kroger/rp_logs/w3-banner_timing_log.11222013/w3-banner-secure_timing_log.11222013.txt")));
        String line = br.readLine();
        List<String> fields = StringUtil.quoteSensitiveSplit(line);
        rpLogs.insert(logEntry(fields));
//        while ((line = br.readLine()) != null) {
            // process the line.
//        }
        br.close();
    }

    private BasicDBObject logEntry(List<String> fields) {
        return new BasicDBObject("domain", fields.get(0))
                .append("ip", fields.get(1))
                .append("timestamp", new Date());
    }

}
