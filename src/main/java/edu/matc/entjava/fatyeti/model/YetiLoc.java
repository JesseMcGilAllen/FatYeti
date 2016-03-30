package edu.matc.entjava.model;

/**
 * @author Matthew R. Trower
 * class YetiLoc
 * TODO: comment
 */
public class YetiLoc {
    // Stored in signed decimal degrees
    private final double longitude;
    private final double latitude;

    /*
     *  Constructor for YetiLoc
     */
    public YetiLoc() {
        longitude = 0;
        latitude = 0;
    }

    public YetiLoc(double lon, double lat) {
        longitude = lon;
        latitude = lat;
    }

    public double lon() {
        return longitude;
    }

    public double lonR() {
        return Math.toRadians(longitude);
    }

    public double lat() {
        return latitude;
    }

    public double latR() {
        return Math.toRadians(latitude);
    }
}
