package com.gabilheri.formulacalculator.main.utils;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class Utils {

    public static String stripHTML(String toStrip) {
        return toStrip.replaceAll("\\<.*?>", "");
    }
}
