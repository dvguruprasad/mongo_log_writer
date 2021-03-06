package mlw;

import com.google.common.base.Function;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToLogEntry implements Function<String, DBObject> {
    private static Pattern pattern;

    static {
        pattern = Pattern.compile("([\\w\\d.-]+) ([\\d.]+) \\[([\\w\\d:.-]+)\\] [-\\w]+ [-\\w]+ \\[(\\d{2}/\\w+/\\d{4}:\\d{2}:\\d{2}:\\d{2}\\s[+-]\\d{4})\\] \"(.+?)\"" +
                "  (\\d{3}):(\\d{3}) ([\\d-]+) \"(.+?)\" .*$");
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss zzz");

    @Override
    public DBObject apply(String logLine) {
        Matcher matcher = pattern.matcher(logLine);
        if (!matcher.matches())
            throw new RuntimeException("Could not parse " + logLine);
        Date timestamp = null;
        try {
            timestamp = dateFormat.parse(matcher.group(4));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new BasicDBObject("domain", matcher.group(1))
                .append("ip", matcher.group(2))
                .append("timestamp", timestamp)
                .append("request_uri", matcher.group(5))
                .append("response_status", matcher.group(6))
                .append("response status_1", matcher.group(7))
                .append("response_time", matcher.group(8))
                .append("user_agent", matcher.group(9));
    }
}
