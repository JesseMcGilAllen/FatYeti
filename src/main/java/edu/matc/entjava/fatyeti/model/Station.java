package edu.matc.entjava.fatyeti.model;

import edu.matc.entjava.fatyeti.entity.Location;

/**
 * Created by jessemcgilallen on 3/22/16.
 */
public class Station {

    private Location location;
    private String description;
    private Double snowfallInches;
    private Double durationHours;
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

    public Double getSnowfallInches() {
        return snowfallInches;
    }

    /**
     * @param snowfallInches
     */
    public void setSnowfallInches(Double snowfallInches) {
        this.snowfallInches = snowfallInches;
    }

    public Double getDurationHours() {
        return durationHours;
    }

    /**
     * @param durationHours
     */
    public void setDurationHours(Double durationHours) {
        this.durationHours = durationHours;
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
                "location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", snowfallInches=" + snowfallInches +
                ", durationHours=" + durationHours +
                ", elevationFeet=" + elevationFeet +
                '}';
    }
}
