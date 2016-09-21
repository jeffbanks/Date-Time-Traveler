package com.jjbanks.learning.util;

/**
 * 
 * @author jjbanks.com
 *
 */
public final class SpaceUtil {

    private static final int MIN_LAT = -90;
    private static final int MAX_LAT = 90;
    private static final int MIN_LONG = -180;
    private static final int MAX_LONG = 180;

    /**
     * Default constructor.
     */
    private SpaceUtil() {
    }

    /**
     * 
     * @param latitude
     *            to be verified
     * @param longitude
     *            to be verified
     * @return status of validity
     */
    public static boolean isLatLongValid(final double latitude,
            final double longitude) {

        if (latitude < MIN_LAT || latitude > MAX_LAT) {
            return false;
        }

        if (longitude < MIN_LONG || longitude > MAX_LONG) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return random latitude within min and max constraints
     */
    public static double generateRandomLatitude() {
        return MIN_LAT + (double) (Math.random() * ((MAX_LAT - MIN_LAT) + 1));
    }

    /**
     * 
     * @return random longitude within min and max constraints
     */
    public static double generateRandomLongitude() {
        return MIN_LONG
                + (double) (Math.random() * ((MAX_LONG - MIN_LONG) + 1));

    }
}
