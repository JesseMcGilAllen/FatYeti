package edu.matc.entjava.fatyeti.model;

/**
 * Created by cdperry on 4/3/16.
 */
import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("org.jsonschema2pojo")
public class Location {

    public Double lat;
    public Double lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Location() {
        this.lat = 0.0;
        this.lng = 0.0;
    }

    public Location(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
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

}
