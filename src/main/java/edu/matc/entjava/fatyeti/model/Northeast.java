package edu.matc.entjava.fatyeti.model;

/**
 * Created by cdperry on 4/3/16.
 */
import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("org.jsonschema2pojo")
public class Northeast {

    public Double lat;
    public Double lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
