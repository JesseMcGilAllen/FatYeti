package edu.matc.java113.FatYeti;

/**
 * @author Matthew R. Trower
 * class YetiLoc
 * TODO: comment
 */
public class YetiLoc {
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

    public double lat() {
        return latitude;
    }
}
