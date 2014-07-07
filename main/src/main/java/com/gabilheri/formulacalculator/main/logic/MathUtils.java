package com.gabilheri.formulacalculator.main.logic;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/6/14.
 */
public class MathUtils  {

    /**
     * Method to evaluate the cosine function using an angle specified in degrees
     * @param v
     *          The angle to be evaluated
     * @return
     *          The cos of the specified angle in degrees
     */
    public static double cosDegrees(double v) {
        return Math.cos(Math.toRadians(v));
    }

    public static double sinDegrees(double v) {
        return Math.sin(Math.toRadians(v));
    }

    public static double tanDegrees(double v) {
        return Math.tan(Math.toRadians(v));
    }
}
