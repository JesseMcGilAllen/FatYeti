package edu.matc.entjava.fatyeti.model;

import junit.framework.TestCase;
import java.util.Properties;

/**
 * Created by cdperry on 4/13/16.
 */
public class LocationTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = YetiConfig.class.getClassLoader();
        properties.load(classLoader.getResourceAsStream("fatyeti.properties"));

        YetiConfig.setProperties(properties);
    }

    public void testIsLatLongDefined() throws Exception {
        Location location = new Location("53527");
        boolean isLatLongDefined = location.isLatLongDefined();
        assertTrue("Stations not available", isLatLongDefined);
    }

}
