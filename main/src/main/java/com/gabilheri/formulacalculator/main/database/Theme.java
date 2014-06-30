package com.gabilheri.formulacalculator.main.database;

import java.util.HashMap;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class Theme {

    public static final int THEME_FULL = 0x0000;
    public static final int THEME_BASIC = 0x0001;
    public static final String LOG_TAG = "Theme";

    private String username;
    private String themeName;
    private String themeType;
    private String displayColor;
    private HashMap<Integer, Integer> buttonBackground;
    private HashMap<Integer, Integer> buttonHightlight;
    private HashMap<Integer, Integer> buttonTextColor;

    public Theme() {


    }
}
