package edu.matc.entjava.fatyeti.model;

/**
 * This class models a response which will be converted to JSON and returned to the user. It contains information
 * about whether or not a snow depth retrieval was successful and, if so, provides information about the nearest
 * station reporting the snow depth.
 *
 * @author cdperry
 * @version 1.0 04/12/16
 */
public class Snowfall {

    private boolean isSuccess;
    private String errorMessage;
    private Station station;

    /**
     * no-arg constructor
     */
    public Snowfall() {

    }

    /**
     * This constructor is used to create Snowfall objects
     *
     * @param isSuccess a boolean indicating whether or not the ZIP code was translated to a lat/long pair
     * @param errorMessage if an error occurred during lat/long translation (or elsewhere) this will describe the error
     * @param station the closest weather station to the ZIP code of interest
     */
    public Snowfall(boolean isSuccess, String errorMessage, Station station) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.station = station;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {

        return "{isSuccess=" + this.isSuccess + "}," +
            "{errorMessage=" + this.errorMessage + "}," +
            station.toString();

    }

}
