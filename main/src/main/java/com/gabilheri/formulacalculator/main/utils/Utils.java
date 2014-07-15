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
     * @param toStrip The String that needs html to be stripped
     * @return A string html free!
     */
    public static String stripHTML(String toStrip) {
        return toStrip.replaceAll("\\<.*?>", "");
    }

    /**
     * Helper method to easy squaring a number.
     * Very handful for all the trig functions that involves a lot
     * of squaring numbers such as pytaghorean, etc..
     *
     * @param toSquare The value to be squared
     * @return the squared number
     */
    public static double squareNumber(double toSquare) {
        return Math.pow(toSquare, 2);
    }

    /**
     * Helper function to convert a rgba String to a hexString
     * The hex String can them be displayed or converted to a int represented by the hex value
     *
     * @param rgbaString The String with a color in RGBA format
     * @return a String with a color formated in Hex Value
     */
    public static String rgbaToHex(String rgbaString) {
        String[] rgbColors = rgbaString.split(",");
        String red = pad(Integer.toHexString(Integer.parseInt(rgbColors[0])));
        String green = pad(Integer.toHexString(Integer.parseInt(rgbColors[1])));
        String blue = pad(Integer.toHexString(Integer.parseInt(rgbColors[2])));
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
     *
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
     *
     * @param buttonId The ID of the pressed button
     * @return true if is an operator
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

    /**
     * Method to find if a double can be represented as a integer
     * @param number
     *      The double number
     * @return
     *      True if can be represented as a integer
     */
    public static boolean isInt(double number) {
        return number % 2 == 0 || (number + 1) % 2 == 0;
    }

    /**
     * Method to convert a double number to a Fraction representantion
     *
     * @param doub The double number to be converted
     * @return A String with a Fraction Representantion
     */
    public static String doubleToFraction(double doub) {
        //we get the whole part
        int whole = (int) doub;
        //we get the rest
        double rest = doub - (double) whole;
        int numerator = 1, denominator = 1;
        //if the whole part of the number is greater than 0
        //we'll try to transform the rest of the number to an Integer
        //by multiplying the number until it become an integer
        if (whole >= 1) {
            for (int i = 2; ; i++) {
            /*when we find the "Integer" number(it'll be the numerator)
             * we also found the denominator(i,which is the number that transforms the number to integer)
             * For example if we have the number = 2.5 when it is multiplied by 2
             * now it's 5 and it's integer, now we have the numerator(the number (2.5)*i(2) = 5)
             * and the denominator i = 2
             */
                if (isInt(rest * (double) i)) {
                    numerator = (int) (rest * (double) i);
                    denominator = i;
                    break;
                }
                if (i > 10000) {
                    //if i is greater than 10000 it's posible that the number is irrational
                    //and it can't be represented as a fractional number
                    return doub + "";
                }
            }
            //if we have the number 3.5 the whole part is 3 then we have the rest represented in fraction 0.5 = 1/2
            //so we have a mixed fraction 3+1/2 = 7/2
            numerator = (whole * denominator) + numerator;
        } else {
            //If not we'll try to transform the original number to an integer
            //with the same process
            for (int i = 2; ; i++) {
                if (isInt(doub * (double) i)) {
                    numerator = (int) (doub * (double) i);
                    denominator = i;
                    break;
                }
                if (i > 10000) {
                    return doub + "";
                }
            }
        }

        return numerator + "/" + denominator;
    }
}
