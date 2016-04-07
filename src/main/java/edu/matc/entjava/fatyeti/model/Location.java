package edu.matc.entjava.fatyeti.model;

/**
 * Created by cdperry on 4/3/16.
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("org.jsonschema2pojo")
public class Location {

    public Double lat;
    public Double lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private boolean resultsFound;

    public Location() {
        this.lat = 0.0;
        this.lng = 0.0;
    }

    public Location(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * This constructor takes a ZIP code and will attempt to convert the ZIP code to a (latitude, longitude) pair
     * @param zipCode a String representation of a postal code.
     */
    public Location(String zipCode) {

        this.translateZIPtoLatLong(zipCode);

    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Double getLat() {
        return this.lat;
    }

    public Double getLng() {
        return this.lng;
    }

    /**
     * Given a ZIP code this method attempts to translate that ZIP code into a (latitude, longitude) coordinate
     * pair.
     * @param zipCode a text representation of a postal code to be sent to the Google Maps API
     * @return a Location object contains the (latitude, longitude) pair.
     */
    private void translateZIPtoLatLong(String zipCode) {

        ObjectMapper mapper = new ObjectMapper();
        String API;
        GoogleGeoCodeResponse googleMapsResponse;
        List<Result> results;


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
                this.lat = result.getGeometry().getLocation().getLat();
                this.lng = result.getGeometry().getLocation().getLng();
            } else {
                this.resultsFound = false;
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
                + this.lat + " latitude, "
                + this.lng + " longitude.";
    }

}
