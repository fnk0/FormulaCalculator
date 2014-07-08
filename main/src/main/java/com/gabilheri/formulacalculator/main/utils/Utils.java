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

    /**
     * Simple method with a regular expression to strip the HTML of a String
     * This method is specially used to remove the HTML needed to format all the
     * colored parenthesys
     *
     * @param toStrip
     *          The String that needs html to be stripped
     * @return
     *          A string html free!
     */
    public static String stripHTML(String toStrip) {
        return toStrip.replaceAll("\\<.*?>", "");
    }

    /**
     *
     * Helper method to easy squaring a number.
     * Very handful for all the trig functions that involves a lot
     * of squaring numbers such as pytaghorean, etc..
     *
     * @param toSquare
     *          The value to be squared
     * @return
     *          the squared number
     */
    public static double squareNumber(double toSquare) {
        return Math.pow(toSquare, 2);
    }

    /**
     * Helper function to convert a rgba String to a hexString
     * The hex String can them be displayed or converted to a int represented by the hex value
     * @param rgbaString
     *          The String with a color in RGBA format
     * @return
     *          a String with a color formated in Hex Value
     */
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

    /**
     * Checks if the button selected is an Operator or not
     * @param buttonId
     *          The ID of the pressed button
     * @return
     *      true if is an operator
     */
    public static boolean isButtonOperator(int buttonId) {
        switch (buttonId) {
            case R.id.plus:
            case R.id.minus:
            case R.id.keypadSqrt:
            case R.id.multiply:
            case R.id.divide:
            case R.id.keypadPwr:
            case R.id.btnPercent:
                return true;
            default:
                return false;
        }
    }
}
