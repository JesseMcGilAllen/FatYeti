package edu.matc.entjava.fatyeti.model;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to read the properties file for the program and to make it available to other
 * objects that may need to utilize the properties.
 *
 * @author Matthew R. Trower
 * @version 1.0 04/12/16
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

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        YetiConfig.properties = properties;
    }

    /**
     * This method returns the property of interest as specified by the 'key' parameter
     * @param key the name of the property whose value should be returned
     * @return the value of the property specified by the 'key' parameter
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
