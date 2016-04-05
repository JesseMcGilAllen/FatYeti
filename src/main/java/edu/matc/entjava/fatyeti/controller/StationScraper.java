package edu.matc.entjava.fatyeti.controller;

import edu.matc.entjava.fatyeti.model.Station;
import edu.matc.entjava.fatyeti.entity.Location;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jessemcgilallen on 3/22/16.
 */
public class StationScraper {

    private final Logger logger = Logger.getLogger(this.getClass());

    public StationScraper() {

    }

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

    private String noaaUrl() {
        return "http://www.nohrsc.noaa.gov/nsa/reports.html?region=National&var=snowfall&sort=value&units=e";
    }

    private Document htmlDocumentFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private Elements relevantRowsForDocument(Document document) {
        Element table = htmlTableForStations(document);
        Elements rows = rowsFromTable(table);
        rows = removeHeaderRowFromRows(rows);

        return rows;
    }

    /**
     *
     * @param document
     * @return table that contains station data
     */
    private Element htmlTableForStations(Document document) {
        return document.select("table.gray_data_table").first();
    }

    private Elements rowsFromTable(Element table) {
         return table.select("tr");
    }

    private Elements removeHeaderRowFromRows(Elements rows) {
        rows.remove(rows.first());

        return rows;
    }

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

    private Station stationForRow(Element row) {
        Station station = new Station();

        Elements cells = rowCells(row);

        /**
         *  checks that row contains a full set of information
         *  6 tds per tr
         */
        if (cells.size() != 6) {
            return null;
        }

        Element coordinatesCell = cells.first();
        Element snowfallCell = cells.get(2);
        Element durationCell = cells.get(3);
        Element elevationCell = cells.get(4);
        Element descriptionCell = cells.last();

        station.setLocation(coordinatesFromCell(coordinatesCell));
        station.setDescription(stringForCell(descriptionCell));
        station.setSnowfallInches(doubleFromString(stringForCell(snowfallCell)));
        station.setDurationHours(doubleFromString(stringForCell(durationCell)));
        station.setElevationFeet(integerFromString(stringForCell(elevationCell)));

        return station;
    }

    private Location locationUsing(String coordinates) {
        Location location;

        String cleanedCoordinates = coordinates.replaceAll("([()]|N|W|E|S)", "");

        String[] individualCoordinates = cleanedCoordinates.split(",");

        Double lat = Double.parseDouble(individualCoordinates[0]);
        Double lng = Double.parseDouble(individualCoordinates[1]);

        location = new Location(lat, lng);

        return location;
    }

    private Elements rowCells(Element row) {
        return row.select("td");
    }

    private Location coordinatesFromCell(Element cell) {
        String coordinates = cell.attr("title");

        return locationUsing(coordinates);
    }

    private String stringForCell(Element cell) {
        return cell.text();
    }

    private Double doubleFromString(String string) {
        return Double.parseDouble(string);
    }

    private Integer integerFromString(String string) {
        return Integer.parseInt(string);
    }



}
