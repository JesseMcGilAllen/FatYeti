package edu.matc.entjava.fatyeti.controller;

import java.io.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import edu.matc.entjava.fatyeti.model.Location;
import edu.matc.entjava.fatyeti.model.Station;
import edu.matc.entjava.fatyeti.model.StationScraper;
import edu.matc.entjava.fatyeti.model.YetiMath;

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
        JsonGenerator generator = Json.createGenerator(out);

        Location location = new Location(request.getParameter("zipCode"));

        if (location.isLatLongDefined()) {
            StationScraper scraper = new StationScraper();
            Station station =
                YetiMath.findNearestStation(scraper.noaaStations(), location);

            writeStation(generator, station);
        } else {
            writeError(generator, "No results returned from Google.");
        }

        out.close();
    }

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

    private void writeError(JsonGenerator generator, String message) {
        generator
            .writeStartObject()
                .write("success", false)
                .write("errorMessage", message)
            .writeEnd();
        generator.close();
    }

}
