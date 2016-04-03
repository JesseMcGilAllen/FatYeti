package edu.matc.entjava.fatyeti.entity;

/**
 * Created by cdperry on 4/3/16.
 */
import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
