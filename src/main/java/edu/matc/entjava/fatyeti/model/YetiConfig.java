package edu.matc.entjava.fatyeti.model;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Matthew R. Trower
 * class YetiConfig
 * TODO: comment
 */
public class YetiConfig {
    private static Properties properties = new Properties();

    static {
        try {
            ClassLoader classLoader = YetiConfig.class.getClassLoader();
            properties.load(classLoader.getResourceAsStream("/fatyeti.properties"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
