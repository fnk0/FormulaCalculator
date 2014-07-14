package com.gabilheri.formulacalculator.main.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class Theme implements Parcelable{

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
     * Name of the Default Theme for this Application
     */
    public static final String DEFAULT_THEME = "Default Theme";

    /**
     * Name of the Secondary Theme for this Application
     */
    public static final String SECONDARY_THEME = "Secondary Theme";

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
    private int primaryHighlightColor;
    private int secondaryHighlightColor;
    private int selectedColor;
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
     * @param selectedColor
     *          The Selected Color for this Theme
     *
     */
    public Theme(String username, String themeName, int themeType, int primaryColor, int secondaryColor, long id, int selectedColor,
                 int displayColor, int primaryButtonTextColor, int secondaryButtonTextColor, int displayTextColor) {
        this.username = username;
        this.themeName = themeName;
        this.themeType = themeType;
        this.id = id;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.displayColor = displayColor;
        this.selectedColor = selectedColor;
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

    /**
     *
     * @return
     *        The color for the Buttons when they are in Selected state
     */
    public int getSelectedColor() {
        return selectedColor;
    }

    /**
     *
     * @param selectedColor
     *      The Selected Color for this Theme
     * @return
     *      This object for a easy chain
     */
    public Theme setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        return this;
    }

    /**
     *
     * @return
     *      The Highlight color of the Primary Button of this Theme
     */
    public int getPrimaryHighlightColor() {
        return primaryHighlightColor;
    }

    /**
     *
     * @param primaryHighlightColor
     *          The Highlight color for this Theme
     * @return
     *          This object for a easy chain
     */
    public Theme setPrimaryHighlightColor(int primaryHighlightColor) {
        this.primaryHighlightColor = primaryHighlightColor;
        return this;
    }

    /**
     *
     * @return
     *      The Highlight color of the Secondary Button of this Theme
     */
    public int getSecondaryHighlightColor() {
        return secondaryHighlightColor;
    }

    /**
     *
     * @param secondaryHighlightColor
     *      The Highlight color for the Secondary Button of this Theme
     * @return
     *       This object for a easy chain
     */
    public Theme setSecondaryHighlightColor(int secondaryHighlightColor) {
        this.secondaryHighlightColor = secondaryHighlightColor;
        return this;
    }

    protected Theme(Parcel in) {
        username = in.readString();
        themeName = in.readString();
        themeType = in.readInt();
        primaryColor = in.readInt();
        secondaryColor = in.readInt();
        displayColor = in.readInt();
        displayTextColor = in.readInt();
        primaryButtonTextColor = in.readInt();
        secondaryButtonTextColor = in.readInt();
        primaryHighlightColor = in.readInt();
        secondaryHighlightColor = in.readInt();
        selectedColor = in.readInt();
        id = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(themeName);
        dest.writeInt(themeType);
        dest.writeInt(primaryColor);
        dest.writeInt(secondaryColor);
        dest.writeInt(displayColor);
        dest.writeInt(displayTextColor);
        dest.writeInt(primaryButtonTextColor);
        dest.writeInt(secondaryButtonTextColor);
        dest.writeInt(primaryHighlightColor);
        dest.writeInt(secondaryHighlightColor);
        dest.writeInt(selectedColor);
        dest.writeLong(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Theme> CREATOR = new Parcelable.Creator<Theme>() {
        @Override
        public Theme createFromParcel(Parcel in) {
            return new Theme(in);
        }

        @Override
        public Theme[] newArray(int size) {
            return new Theme[size];
        }
    };

}
