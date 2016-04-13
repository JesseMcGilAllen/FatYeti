package edu.matc.entjava.fatyeti.model;

/**
 * Created by cdperry on 4/3/16.
 */
import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class models the 'geometry' component of the Google Maps API response.  It is used to help translate
 * the Google Maps API JSON response into a Java object for further processing.
 *
 * @author cdperry
 * @version 1.0 04/12/16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("org.jsonschema2pojo")
public class Geometry {

    public Bounds bounds;
    public Location location;
    public String locationType;
    public Viewport viewport;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Location getLocation() {
        return this.location;
    }

}
