package edu.matc.entjava.fatyeti.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import edu.matc.entjava.fatyeti.model.Location;
import edu.matc.entjava.fatyeti.model.YetiLoc;

/**
 *  <p>
 *  This servlet is used to test the YetiLoc class.  It allows the user to enter a ZIP code and will display the
 *  latitude and longitude of the center of that ZIP code, provided the ZIP code is valid.
 *  </p>
 *  @author Chris Perry
 */
@WebServlet(
        name = "convertZIPCode",
        urlPatterns = { "/convertZIPCode" }
)
public class ZIP extends HttpServlet {

    //ObjectMapper mapper = new ObjectMapper();
    //Logger logger = Logger.getLogger(this.getClass());

    /**
     *  This method handles HTTP POST requests.
     *
     *  @param  request                   the HttpServletRequest object
     *  @param  response                   the HttpServletResponse object
     *  @exception  ServletException  if there is a Servlet failure
     *  @exception  IOException       if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = null;
        //YetiLoc yeti;
        Location location;
        String zipCode;
        String url;

        url = "index.jsp";
        zipCode = request.getParameter("zipCode");
        session = request.getSession();
        //yeti = new YetiLoc(zipCode);
        location = new Location(zipCode);

        //if (yeti.getResultsFound()) {
        if (location.isLatLongDefined()) {
            //session.setAttribute("latitude", yeti.lat());
            //session.setAttribute("longitude", yeti.lon());
            session.setAttribute("latitude", location.getLat());
            session.setAttribute("longitude", location.getLng());
            session.setAttribute("errorMessage", "");
        } else {
            session.setAttribute("errorMessage", "No results returned from Google.");
        }

        response.sendRedirect(url);

    }

}
