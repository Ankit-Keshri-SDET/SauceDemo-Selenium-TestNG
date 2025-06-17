package utils;

import enums.EnvType;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String get(String key) {
        String value = prop.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in config.properties: " + key);
        }
        return value;
    }

    public static EnvType getEnvironment() {
        String env = System.getProperty("env");
        if (env != null) {
            return EnvType.valueOf(env.toUpperCase());
        } else {
            return EnvType.valueOf(get("environment").toUpperCase());
        }
    }
}
