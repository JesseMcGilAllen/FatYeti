package edu.matc.entjava.fatyeti.model;

import junit.framework.TestCase;

import java.util.List;
import java.io.File;
import java.util.Properties;


/**
 * @author jessemcgilallen
 * @version 1.0 4/13/16.
 */
public class StationScraperTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = YetiConfig.class.getClassLoader();
        properties.load(classLoader.getResourceAsStream("fatyeti.properties"));

        YetiConfig.setProperties(properties);
    }

    public void testNoaaStations() throws Exception {
        StationScraper scraper = new StationScraper();
        List<Station> stations = scraper.noaaStations();

        boolean stationsAvailable = stations.size() > 0;

        assertTrue("Stations not available", stationsAvailable);
    }


}