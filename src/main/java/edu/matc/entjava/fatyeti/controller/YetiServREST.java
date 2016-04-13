package edu.matc.entjava.fatyeti.controller;

import javax.ws.rs.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entjava.fatyeti.model.Location;
import edu.matc.entjava.fatyeti.model.Station;
import edu.matc.entjava.fatyeti.model.StationScraper;
import edu.matc.entjava.fatyeti.model.YetiMath;
import edu.matc.entjava.fatyeti.model.Snowfall;

/**
 * The YetiServREST class generates a RESTful web service that, when passed an API version and a ZIP code,
 * returns a JSON response that contains information about current snow depth at the center of that location.
 *
 * @author cdperry
 * @version 1.0 04/12/16
 */
@Path("/getSnowfall")
public class YetiServREST {

    /**
     * This method takes an API version and a ZIP code parameter.  The ZIP code is converted to latitude and
     * longitude coordinates and, if successful, snow depth is retrieved from nearby weather stations via
     * NOAA.  The results are then returned as a JSON response.
     *
     * @param API A version number of the API to be utilized
     * @param zipCode A U.S. Postal Code representing the location whose snow depth is of interest
     * @return a JSON response containing information about snow depth as reported by the nearest weather station
     */
    @GET
    @Path("{API}/{zipCode}")
    @Produces("application/JSON")
    public String getSnowFall(@PathParam("API") String API, @PathParam("zipCode") String zipCode) {

        Location location = new Location(zipCode);
        String jsonResponse = "{}";
        Snowfall results;

        if (location.isLatLongDefined()) {
            StationScraper scraper = new StationScraper();
            Station station =
                    YetiMath.findNearestStation(scraper.noaaStations(), location);
            results = new Snowfall(true, "", station);
        } else {
            results = new Snowfall();
            results.setSuccess(false);
            results.setErrorMessage("No results returned from Google Maps API");
        }

        try {
            jsonResponse = createJSON(results);
        } catch (JsonProcessingException ex) {
            jsonResponse = "{\"errorMessage\":\"Error encountered generating JSON\""
                + ",\"station\":null,\"success\":false}";
        } catch (Exception ex) {
            jsonResponse = "{\"errorMessage\":\"Unexpected exception encountered\""
                + ",\"station\":null,\"success\":false}";
        }

        return jsonResponse;

    }

    /**
     * This is a helper method which converts a Snowfall object into a JSON response
     *
     * @param snowfall contains information about snow depth from the nearest weather station
     * @return a JSON response string
     * @throws JsonProcessingException
     */
    private String createJSON(Snowfall snowfall) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(snowfall);

    }

}
