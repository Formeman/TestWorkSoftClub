package framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {
    public static String getTextByPattern(String text, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return text;
    }
}
