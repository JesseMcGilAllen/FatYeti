package edu.matc.entjava.fatyeti.model;

/**
 * The Station class models information about a weather station as reported by NOAA. It contains information
 * such as the weather station's coordinates (latitude/longitude), a description of the station, the snow depth
 * reported by that station, the station's elevation and the last time this station's data was updated.
 *
 * @author jessemcgilallen
 * @version 1.0 04/12/16
 */
public class Station {

    private Location location;
    private String description;
    private String lastUpdatedDate;
    private Double snowfallDepth;
    private Integer elevationFeet;

    public Station() {

    }

    public Location getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSnowfallDepth() {
        return snowfallDepth;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * @param snowfallDepth
     */
    public void setSnowfallDepth(Double snowfallDepth) {
        this.snowfallDepth = snowfallDepth;
    }

    public Integer getElevationFeet() {
        return elevationFeet;
    }

    /**
     * @param elevationFeet
     */
    public void setElevationFeet(Integer elevationFeet) {
        this.elevationFeet = elevationFeet;
    }

    @Override
    public String toString() {
        return "Station{" +
                "location= Lat: " + location.lat + " Long: " + location.lng +
                ", description='" + description + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", snowfallDepth=" + snowfallDepth +
                ", elevationFeet=" + elevationFeet +
                '}';
    }

}
