package edu.matc.entjava.fatyeti.model;

import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is used to translate the Google Maps API JSON response into a Java object.  It is utilized by the
 * Jackson library.
 *
 * @author cdperry
 * @version 1.0 04/12/16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("org.jsonschema2pojo")
public class GoogleGeoCodeResponse {

    public List<Result> results = new ArrayList<Result>();
    public String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<Result> getResults() {
        return this.results;
    }

    public String getStatus() {
        return this.status;
    }

}
