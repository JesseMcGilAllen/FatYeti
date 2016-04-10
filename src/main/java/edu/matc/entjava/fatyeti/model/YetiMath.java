package edu.matc.entjava.fatyeti.model;

import java.util.List;

/**
 * @author Matthew R. Trower
 * class YetiMath
 * TODO: comment
 */
public class YetiMath {
    /*
     *  Constructor for YetiMath
     */
    public YetiMath() {

    }

    // Fast and simple.  Imprecise, but more than good enough for this.
    public static double SimpleDistance(Location loc1, Location loc2) {
        int R = 6371000; // Earth's radius (M)
        double φm = (loc1.getLat() + loc2.getLat()) / 2;
        double Δφ = loc2.getLat() - loc1.getLat();
        double Δλ = loc2.getLng() - loc1.getLng();

        return ptheorem_equilateral(φm, Δφ, Δλ, R);
    }

    public static Station findNearestStation(List<Station> stations, Location loc) {
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

    // Pythagoras' theorem on an equirectangular projection
    private static double ptheorem_equilateral(double φm, double Δφ,
                                                double Δλ, double R) {
        double x = Δλ * Math.cos(φm);
        double y = Δφ;

        return Math.sqrt(x * x + y * y) * R;
    }
}
