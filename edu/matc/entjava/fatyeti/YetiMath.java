package edu.matc.java113.fatyeti;

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
    public static double SimpleDistance(YetiLoc loc1, YetiLoc loc2) {
        int R = 6371000; // Earth's radius (M)
        double φm = (loc1.latR() + loc2.latR()) / 2;
        double Δφ = loc2.latR() - loc1.latR();
        double Δλ = loc2.lonR() - loc1.lonR();

        return ptheorem_equilateral(φm, Δφ, Δλ, R);
    }

    // Pythagoras' theorem on an equirectangular projection
    private static double ptheorem_equilateral(double φm, double Δφ,
                                                double Δλ, double R) {
        double x = Δλ * Math.cos(φm);
        double y = Δφ;

        return Math.sqrt(x * x + y * y) * R;
    }
}
