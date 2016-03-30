package edu.matc.entjava.fatyeti.controller;

/**
 * Created by jessemcgilallen on 3/22/16.
 */
public class Station {

    private String coordinates;
    private String description;
    private Double snowfallInches;
    private Double durationHours;
    private Integer elevationFeet;

    public Station() {

    }

    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
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
                "coordinates='" + coordinates + '\'' +
                ", description='" + description + '\'' +
                ", snowfallInches=" + snowfallInches +
                ", durationHours=" + durationHours +
                ", elevationFeet=" + elevationFeet +
                '}';
    }
}
