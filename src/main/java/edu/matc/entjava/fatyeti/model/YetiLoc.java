package edu.matc.entjava.fatyeti.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entjava.fatyeti.entity.GoogleGeoCodeResponse;
import edu.matc.entjava.fatyeti.entity.Location;
import edu.matc.entjava.fatyeti.entity.Result;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author Matthew R. Trower
 * class YetiLoc
 * TODO: comment, put the Google Maps API URL string into a configuration file?
 */
public class YetiLoc {

    // Stored in signed decimal degrees
    private final double longitude;
    private final double latitude;
    private boolean resultsFound;

    /*
     *  Constructor for YetiLoc
     */
    public YetiLoc() {
        longitude = 0;
        latitude = 0;
    }

    public YetiLoc(double lon, double lat) {
        longitude = lon;
        latitude = lat;
    }

    /**
     * This constructor takes a ZIP code and will attempt to convert the ZIP code to a (latitude, longitude) pair
     * @param zipCode a String representation of a postal code.
     */
    public YetiLoc(String zipCode) {

        Location location;

        location = this.translateZIPtoLatLong(zipCode);

        this.latitude = location.getLat();
        this.longitude = location.getLng();

    }

    /**
     * Given a ZIP code this method attempts to translate that ZIP code into a (latitude, longitude) coordinate
     * pair.
     * @param zipCode a text representation of a postal code to be sent to the Google Maps API
     * @return a Location object contains the (latitude, longitude) pair.
     */
    private Location translateZIPtoLatLong(String zipCode) {

        ObjectMapper mapper = new ObjectMapper();
        String API;
        GoogleGeoCodeResponse googleMapsResponse;
        List<Result> results;
        Location location = new Location();

        API = "http://maps.googleapis.com/maps/api/geocode/json?address="
                + zipCode
                + "&components=postal_code:"
                + zipCode;

        try {

            googleMapsResponse = mapper.readValue(new URL(API), GoogleGeoCodeResponse.class);

            results = googleMapsResponse.getResults();

            if (results.size() > 0) {
                this.resultsFound = true;
                Result result = results.get(0);
                location = result.getGeometry().getLocation();
            } else {
                this.resultsFound = false;
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return location;

    }

    public double lon() {
        return longitude;
    }

    public double lonR() {
        return Math.toRadians(longitude);
    }

    public double lat() {
        return latitude;
    }

    public double latR() {
        return Math.toRadians(latitude);
    }

    /**
     * Returns the status of the Google Maps API search for the passed ZIP code.
     * @return a boolean stating whether or not a result was returned from the Google Maps API
     */
    public boolean getResultsFound() {
        return this.resultsFound;
    }

    /**
     * This method creates a String showing the contents of the YetiLoc object.
     * @return a String describing the YetiLoc object.
     */
    public String toString() {
        return "The coordinates are: "
                + this.latitude + " latitude, "
                + this.longitude + " longitude.";
    }
}
