package com.gabilheri.formulacalculator.main.database;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class Theme {

    public static final String LOG_TAG = "Theme";
    public static final int THEME_PUBLIC = 0;
    public static final int THEME_PRIVATE = 1;

    private String username;
    private String themeName;
    private String themeType;
    private int primaryColor;
    private int secondaryColor;
    private int displayColor;
    private int textColor;
    private int buttonTextColor;
    private int type;

    /**
     * Default Empty Constructor
     */
    public Theme() {}

    /**
     * Constructor for the Theme Object
     *
     * @param username
     * @param themeName
     * @param themeType
     * @param primaryColor
     * @param secondaryColor
     * @param displayColor
     * @param textColor
     * @param buttonTextColor
     * @param type
     */
    public Theme(String username, String themeName, String themeType, int primaryColor, int secondaryColor, int displayColor, int textColor, int buttonTextColor, int type) {
        this.username = username;
        this.themeName = themeName;
        this.themeType = themeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.displayColor = displayColor;
        this.textColor = textColor;
        this.buttonTextColor = buttonTextColor;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeType() {
        return themeType;
    }

    public void setThemeType(String themeType) {
        this.themeType = themeType;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public int getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(int displayColor) {
        this.displayColor = displayColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getButtonTextColor() {
        return buttonTextColor;
    }

    public void setButtonTextColor(int buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
