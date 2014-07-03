package com.gabilheri.formulacalculator.main.utils;

import android.content.Context;
import android.util.Log;

import com.gabilheri.formulacalculator.main.R;

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

    /**
     * Checks if the number before or after a parenthesis is an operator or not.
     * If the number is not an operator a multiplication symbol will be automatically added :D
     * @param c
     * @return
     */
    public static boolean isOperator(char c, Context mContext) {
        if (c == '+' || c == '-' || c == '/' || c == '*' || c == mContext.getString(R.string.sqrt).charAt(0)) {
            return true;
        }
        if (c != 'e' && Character.isLetter(c)) {
            return true;
        }
        return false;
    }
}
