package edu.matc.entjava.fatyeti.controller;

import java.io.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entjava.fatyeti.model.*;

/**
 * @author Matthew R. Trower
 * class YetiServ
 * TODO: comment
 */
@WebServlet(
    loadOnStartup = 1,
    name = "YetiServ",
    urlPatterns = { "/YetiServ", "/snowfall" }
)
public class YetiServ extends HttpServlet {

    public void init() throws ServletException {

    }

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request                   the HttpServletRequest object
     *@param  response                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        //JsonGenerator generator = Json.createGenerator(out);
        Location location = new Location(request.getParameter("zipCode"));
        String jsonResponse = "{}";
        Snowfall results;
        double distanceToStation;

        if (location.isLatLongDefined()) {
            StationScraper scraper = new StationScraper();
            Station station =
                YetiMath.findNearestStation(scraper.noaaStations(), location);
            distanceToStation = YetiMath.SimpleDistance(station.getLocation(), location);
            //writeStation(generator, station);
            results = new Snowfall(true, "", station, distanceToStation);
        } else {
            //writeError(generator, "No results returned from Google.");
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
        out.println(jsonResponse);
        out.close();
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

    /**
     * This is a helper method which translates Station information into a JSON response
     *
     * @param generator a JsonGenerator object
     * @param station a Station object which contains information about snowfall
     */
    private void writeStation(JsonGenerator generator, Station station) {
        generator
            .writeStartObject()
                .write("success", true)
                .write("errorMessage", "")
                .writeStartObject("station")
                    .write("description", station.getDescription())
                    .write("latitude", station.getLocation().getLat())
                    .write("longitude", station.getLocation().getLng())
                .writeEnd()
                .write("lastUpdated", station.getLastUpdatedDate())
                .write("snowfall", station.getSnowfallDepth())
            .writeEnd();
        generator.close();
    }

    /**
     * This is a helper method that writes a dummy JSON object in case the Google
     * geocoding call failed.
     *
     * @param generator a JsonGenerator object
     * @param message an error message to be returned in the JSON response
     */
    private void writeError(JsonGenerator generator, String message) {
        generator
            .writeStartObject()
                .write("success", false)
                .write("errorMessage", message)
            .writeEnd();
        generator.close();
    }

}
