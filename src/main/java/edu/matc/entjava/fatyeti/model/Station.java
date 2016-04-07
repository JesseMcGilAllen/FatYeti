package edu.matc.entjava.fatyeti.model;

import java.time.LocalDate;

/**
 * Created by jessemcgilallen on 3/22/16.
 */
public class Station {

    private Location location;
    private String description;
    private LocalDate lastUpdatedDate;
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

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
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
