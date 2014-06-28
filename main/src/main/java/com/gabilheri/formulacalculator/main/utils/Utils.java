package com.gabilheri.formulacalculator.main.utils;

import android.util.Log;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class Utils {

    public static final String LOG_TAG = "UtilsLog";

    public static String stripHTML(String toStrip) {
        return toStrip.replaceAll("\\<.*?>", "");
    }

    public static double squareNUmber(double toSquare) {
        return Math.pow(toSquare, 2);
    }

    public static String rgbaToHex(String rgbaString) {

        String[] rgbColors = rgbaString.split(",");
        String red = pad(Integer.toHexString(Integer.parseInt(rgbColors[0])));
        String green = pad(Integer.toHexString(Integer.parseInt(rgbColors[1])));
        String blue =  pad(Integer.toHexString(Integer.parseInt(rgbColors[2])));
        String alpha = pad(Integer.toHexString((int) (Float.parseFloat(rgbColors[3]) * 255)));

        Log.i(LOG_TAG, "Hex String: #" + alpha + red + green + blue);

        return "#" + alpha + red + green + blue;
    }

    private static String pad(String s) {
        return (s.length() == 1) ? "0" + s : s;
    }
}
