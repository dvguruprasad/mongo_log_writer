import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class StringUtil {
    public static List<String> quoteSensitiveSplit(String str) {
        if (Strings.isNullOrEmpty(str))
            return newArrayList();
        ArrayList<String> result = newArrayList();
        StringBuilder builder = new StringBuilder();
        boolean doubleQuoteOpen = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"') {
                doubleQuoteOpen = !doubleQuoteOpen;
            }
            if (!doubleQuoteOpen && str.charAt(i) == ' ' && builder.toString().trim().length() > 0) {
                result.add(builder.toString().trim());
                builder = new StringBuilder();
            } else {
                builder.append(str.charAt(i));
            }
        }
        if (builder.length() > 0)
            result.add(builder.toString().trim());
        return result;
    }
}
