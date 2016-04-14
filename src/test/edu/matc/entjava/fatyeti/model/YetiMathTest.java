package edu.matc.entjava.fatyeti.model;

import junit.framework.TestCase;

import java.util.Properties;

/**
 * Created by cdperry on 4/13/16.
 */
public class YetiMathTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = YetiConfig.class.getClassLoader();
        properties.load(classLoader.getResourceAsStream("fatyeti.properties"));
        YetiConfig.setProperties(properties);
    }

    public void testSimpleDistance() throws Exception {
        Location location1 = new Location(10.0, 20.0);
        Location location2 = new Location(40.0, 100.0);

        Double distance = YetiMath.SimpleDistance(location1, location2);

        assertTrue(distance == 8725026.212140283);
    }

}