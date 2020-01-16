package framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static Properties prop = new Properties();
    private static String propFileName = "config.properties";

    public static String getConfig(String configName){
        InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(propFileName);
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(configName);
    }
}
