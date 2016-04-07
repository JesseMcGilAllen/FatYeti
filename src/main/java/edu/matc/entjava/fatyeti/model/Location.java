package edu.matc.entjava.fatyeti.model;

/**
 * Created by cdperry on 4/3/16.
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
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
    private boolean latLongDefined;
    private Properties properties;

    public Location() {
        this.lat = 0.0;
        this.lng = 0.0;
        latLongDefined = false;
    }

    public Location(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
        latLongDefined = true;
    }

    /**
     * This constructor takes a ZIP code and will attempt to convert the ZIP code to a (latitude, longitude) pair
     * @param zipCode a String representation of a postal code.
     */
    public Location(String zipCode) {
        loadPropertiesFile("/fatyeti.properties");
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
     * Returns an indicator as to whether or not latitude and longitude coordinates were set in the
     * object.
     * @return a boolean stating whether or not a latitude and longitude were set in the object.
     */
    public boolean isLatLongDefined() {
        return this.latLongDefined;
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

        API = properties.getProperty("url.google.maps.api") + "?address="
                + zipCode
                + "&components=postal_code:"
                + zipCode;

        try {

            googleMapsResponse = mapper.readValue(new URL(API), GoogleGeoCodeResponse.class);

            results = googleMapsResponse.getResults();

            if (results.size() > 0) {
                this.latLongDefined = true;
                Result result = results.get(0);
                this.lat = result.getGeometry().getLocation().getLat();
                this.lng = result.getGeometry().getLocation().getLng();
            } else {
                this.latLongDefined = false;
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void loadPropertiesFile(String propertiesFilePath) {

        properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream(
                    propertiesFilePath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

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
