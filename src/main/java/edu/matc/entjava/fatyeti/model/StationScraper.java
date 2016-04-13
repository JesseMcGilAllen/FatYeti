package edu.matc.entjava.fatyeti.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to scrape the HTML response from NOAA's snow depth web page.
 *
 * @author jessemcgilallen
 * @version 1.0 04/12/16
 */
public class StationScraper {

    private final Logger logger = Logger.getLogger(this.getClass());

    public StationScraper() {

    }

    /**
     * This method acts as the driver for the class.
     * It connects to the noaaUrl provided by that method.
     * Then walks through grabbing the proper table.
     * Followed by getting a collection of Station objects by parsing
     * each table row representing a Station.
     * @return a list of Station objects
     */
    public List<Station> noaaStations() {

        List<Station> stations = new ArrayList<Station>();
        String url = noaaUrl();

        try {
            Document document = htmlDocumentFromUrl(url);
            Elements rows = relevantRowsForDocument(document);
            stations = stationsFromRows(rows);

        } catch (IOException ioException) {

            logger.error(ioException);
        }


        return stations;
    }

    /**
     * @return the url for the noaa webpage containing snow depth data
     */
    private String noaaUrl() {
        return YetiConfig.getProperty("url.noaa.snowdepth");
        //return "http://www.nohrsc.noaa.gov/nsa/reports.html?region=National&var=snowdepth";
    }

    /**
     * gets the html document at the url argument
     * @param url
     * @return Jsoup Document object that represents the webpage at the url parameter
     * @throws IOException
     */
    private Document htmlDocumentFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    /**
     * This method gets the stations table.
     * Then removes the header row
     * and returns an Elements object containing the remaining rows.
     * @param document
     * @return an Elements object representing the table rows containing station data
     */
    private Elements relevantRowsForDocument(Document document) {
        Element table = htmlTableForStations(document);
        Elements rows = rowsFromTable(table);
        rows = removeHeaderRowFromRows(rows);

        return rows;
    }

    /**
     * This method takes a document object and returns a table element
     * containing the desired noaa data
     * @param document
     * @return Jsoup element that represents the table that contains station data
     */
    private Element htmlTableForStations(Document document) {
        return document.select("table.gray_data_table").first();
    }

    /**
     * @param table
     * @return an Elements object representing table rows
     */
    private Elements rowsFromTable(Element table) {
         return table.select("tr");
    }

    /**
     * This method removes the first row representing the header row from the
     * incoming rows parameter.
     * @param rows
     * @return an Elements object representing table rows
     */
    private Elements removeHeaderRowFromRows(Elements rows) {
        rows.remove(rows.first());

        return rows;
    }

    /**
     * This method creates a list of stations from incoming rows argument.
     * @param rows
     * @return a List of Station objects
     */
    private List<Station> stationsFromRows(Elements rows) {

        List<Station> stations = new ArrayList<Station>();

        for (Element row: rows) {
            Station station = stationForRow(row);

            // Only adds stations that contain all information
            if (station != null) {
                stations.add(station);
            }
        }

        return stations;
    }

    /**
     * This method takes a Jsoup element representing a table row
     * and creates a Station object from the individual table cells in the row.
     * @param row
     * @return Station object created from row values
     */
    private Station stationForRow(Element row) {
        Station station = new Station();

        Elements cells = rowCells(row);
        Integer rowSize = 5;

        /**
         *  checks that row contains a full set of information
         *  5 tds per tr
         */
        if (cells.size() != rowSize) {
            return null;
        }

        Element coordinatesCell = cells.first();
        Element dateCell = cells.get(1);
        Element snowfallCell = cells.get(2);
        Element elevationCell = cells.get(3);
        Element descriptionCell = cells.last();

        station.setLocation(coordinatesFromCell(coordinatesCell));
        station.setDescription(stringForCell(descriptionCell));
        station.setLastUpdatedDate(stringForCell(dateCell));
        station.setSnowfallDepth(doubleFromString(stringForCell(snowfallCell)));
        station.setElevationFeet(integerFromString(stringForCell(elevationCell)));

        return station;
    }

    /**
     * This method takes a coordinates String argument representing
     * latitude and longitude coordinates.
     * It strips the argument of unnecessary symbols and
     * creates a Location object with the cleaned up argument.
     * @param coordinates
     * @return A Location created by the substrings of the coordinates argument
     */
    private Location locationUsing(String coordinates) {
        Location location;

        String cleanedCoordinates = coordinates.replaceAll("([()]|N|W|E|S)", "");

        String[] individualCoordinates = cleanedCoordinates.split(",");

        Double lat = Double.parseDouble(individualCoordinates[0]);
        Double lng = Double.parseDouble(individualCoordinates[1]);

        location = new Location(lat, lng);

        return location;
    }

    /**
     * Returns a collection containing table cell data for row argument.
     * @param row
     * @return an Elements collection containing Element object representing
     * the table cells for the row argument.
     */
    private Elements rowCells(Element row) {
        return row.select("td");
    }

    /**
     * Returns a string of the title attribute value of the cell argument.
     * The title attribute represents the latitude and longitude of the
     * station.
     * @param cell
     * @return String of the title attribute of the cell param
     */
    private Location coordinatesFromCell(Element cell) {
        String coordinates = cell.attr("title");

        return locationUsing(coordinates);
    }

    /**
     * @param cell
     * @return String of the text for the cell parameter
     */
    private String stringForCell(Element cell) {
        return cell.text();
    }

    /**
     * @param string
     * @return Double representation of the string Parameter
     */
    private Double doubleFromString(String string) {
        return Double.parseDouble(string);
    }

    /**
     * @param string
     * @return Integer representation of the string Parameter
     */
    private Integer integerFromString(String string) {
        return Integer.parseInt(string);
    }



}
