package com.gabilheri.formulacalculator.main.database;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class Theme {

    public static final String LOG_TAG = "Theme";
    /**
     *  Constant to set this Theme Public
     */
    public static final int THEME_PUBLIC = 0;
    /**
     *  Constant to set this Theme Private
     */
    public static final int THEME_PRIVATE = 1;

    /**
     * Constant to set this Theme to be a System Theme
     */
    public static final int THEME_SYSTEM = 2;

    /**
     * Variables for this Theme
     */
    private String username;
    private String themeName;
    private int themeType;
    private int primaryColor;
    private int secondaryColor;
    private int displayColor;
    private int displayTextColor;
    private int primaryButtonTextColor;
    private int secondaryButtonTextColor;
    private long id;

    /**
     * Default Empty Constructor
     */
    public Theme() {}

    /**
     * Constructor for the Theme Object that will be saved in the database
     *
     * @param username
     *          The username to which this theme belongs to
     * @param themeName
     *          The name for this theme.
     * @param themeType
     *          The type of this theme. It can be public or private.
     * @param primaryColor
     *          The primary color of this theme
     * @param secondaryColor
     *          The secondary color of this theme
     * @param displayColor
     *          The display color of this theme
     * @param primaryButtonTextColor
     *          The TextColor for the primary buttons for this theme
     * @param secondaryButtonTextColor
     *          The TextColor for the secondary buttons for this theme
     * @param displayTextColor
     *          THe TextColor for the Display of this Theme
     *
     */
    public Theme(String username, String themeName, int themeType, int primaryColor, int secondaryColor, long id,
                 int displayColor, int primaryButtonTextColor, int secondaryButtonTextColor, int displayTextColor) {
        this.username = username;
        this.themeName = themeName;
        this.themeType = themeType;
        this.id = id;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.displayColor = displayColor;
        this.primaryButtonTextColor = primaryButtonTextColor;
        this.secondaryButtonTextColor = secondaryButtonTextColor;
        this.displayTextColor = displayTextColor;
    }

    /**
     *
     * @return
     *      The username of this Theme
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     *          The userneme for this Theme
     * @return
     *          This object for a easy chain build
     */
    public Theme setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     *
     * @return
     *       The theme name of this Theme
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     *
     * @param themeName
     *      The theme name for this theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setThemeName(String themeName) {
        this.themeName = themeName;
        return this;
    }

    /**
     *
     * @return
     *      The type of this Theme
     */
    public int getThemeType() {
        return themeType;
    }

    /**
     *
     * @param themeType
     *      The type for this Theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setThemeType(int themeType) {
        this.themeType = themeType;
        return this;
    }

    /**
     *
     * @return
     *      The primary color for this Theme
     */
    public int getPrimaryColor() {
        return primaryColor;
    }

    /**
     *
     * @param primaryColor
     *      The primary color for this Theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    /**
     *
     * @return
     *      The secondary color of this Theme
     */
    public int getSecondaryColor() {
        return secondaryColor;
    }

    /**
     *
     * @param secondaryColor
     *      The secondary color for this theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    /**
     *
     * @return
     *      The display color of this Theme
     */
    public int getDisplayColor() {
        return displayColor;
    }

    /**
     *
     * @param displayColor
     *      The display color for this Theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setDisplayColor(int displayColor) {
        this.displayColor = displayColor;
        return this;
    }

    /**
     *
     * @return
     *      The primary buttons text color of this Theme
     */
    public int getPrimaryButtonTextColor() {
        return primaryButtonTextColor;
    }

    /**
     *
     * @param primaryButtonTextColor
     *      The primary buttons text color for this Theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setPrimaryButtonTextColor(int primaryButtonTextColor) {
        this.primaryButtonTextColor = primaryButtonTextColor;
        return this;
    }

    /**
     *
     * @return
     *      The secondary buttons text color of this Theme
     */
    public int getSecondaryButtonTextColor() {
        return secondaryButtonTextColor;
    }

    /**
     *
     * @param secondaryButtonTextColor
     *      The TextColor for the secondary buttons for this theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setSecondaryButtonTextColor(int secondaryButtonTextColor) {
        this.secondaryButtonTextColor = secondaryButtonTextColor;
        return this;
    }

    /**
     *
     * @return
     *       The display TextColor for this Theme
     */
    public int getDisplayTextColor() {
        return displayTextColor;
    }

    /**
     *
     * @param displayTextColor
     *      THe TextColor for the Display of this Theme
     * @return
     *      This object for a easy chain build
     */
    public Theme setDisplayTextColor(int displayTextColor) {
        this.displayTextColor = displayTextColor;
        return this;
    }

    /**
     *
     * @return
     *          The ID for this Theme
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     *      The ID for this  Theme
     * @return
     *      This object for a easy chain
     */
    public Theme setId(long id) {
        this.id = id;
        return this;
    }
}
