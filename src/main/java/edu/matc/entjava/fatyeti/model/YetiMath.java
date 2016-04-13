package edu.matc.entjava.fatyeti.model;

import java.util.List;

/**
 * This class provides methods to determine the distance between two
 * latitude/longitude coordinate pairs, as well as to determine which Station
 * is closest to a given location.
 *
 * @author Matthew R. Trower
 * @version 1.0 04/12/16
 */
public class YetiMath {

    /**
     * no-arg constructor
     */
    public YetiMath() {

    }

    /**
     * This method determines the distance between two latitude/longitude
     * coordinate pairs using Pythagoras'
     * theorm.
     * @param loc1 a Location representing a latitude/longitude pair
     * @param loc2 another Location representing a latitude/longitude pair
     * @return the distance between loc1 and loc2
     */
    public static double SimpleDistance(Location loc1, Location loc2) {

        int R = Integer.parseInt(YetiConfig.getProperty("radius.earth"));
        double φm = (loc1.getLat() + loc2.getLat()) / 2;
        double Δφ = loc2.getLat() - loc1.getLat();
        double Δλ = loc2.getLng() - loc1.getLng();

        return ptheorem_equilateral(φm, Δφ, Δλ, R);

    }

    /**
     * Given a list of Station objects and a Location object this method finds
     * the Station that is closest to the given Location by comparing the
     * distance between the Station and the Location using their
     * latitude/longitude coordinate pairs.
     *
     * @param stations a list of Station objects
     * @param loc a Location to which the distance to each Station should be
     * measured
     * @return the station that is closest to the given Location object
     */
    public static Station findNearestStation(List<Station> stations,
                                             Location loc) {

        Station nearestStation = null;

        for (Station station: stations) {
            if (    nearestStation == null ||
                    SimpleDistance(       station.getLocation(), loc) <
                    SimpleDistance(nearestStation.getLocation(), loc) ) {

                nearestStation = station;
            }
        }

        return nearestStation;

    }

    /**
     * This method implements Pythagoras' theorem on an equirectangular
     * projection
     * @param φm the mean latitude
     * @param Δφ the difference between the latitude components of the
     * coordinate pair
     * @param Δλ the difference between the longitude components of the
     * coordinate pair
     * @param R the radius of the sphere (Earth) on which the coordinates lie
     * @return the distance between the latitude/longitude coordinate pairs
     */
    private static double ptheorem_equilateral(double φm, double Δφ,
                                                double Δλ, double R) {
        double x = Δλ * Math.cos(φm);
        double y = Δφ;

        return Math.sqrt(x * x + y * y) * R;
    }
