package com.gabilheri.formulacalculator.backend;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/22/14.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ThemeBean {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    private long id;

    @Persistent
    private String username;

    @Persistent
    private String themeName;

    @Persistent
    private int themeType;

    @Persistent
    private int primaryColor;

    @Persistent
    private int secondaryColor;

    @Persistent
    private int displayColor;

    @Persistent
    private int displayTextColor;

    @Persistent
    private int primaryButtonTextColor;

    @Persistent
    private int secondaryButtonTextColor;

    @Persistent
    private int primaryHighlightColor;

    @Persistent
    private int secondaryHighlightColor;

    @Persistent
    private int selectedColor;

    /**
     * Default Empty Constructor
     */
    public ThemeBean() {}

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
    public ThemeBean(String username, String themeName, int themeType, int primaryColor, int secondaryColor, long id, int selectedColor,
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
    public ThemeBean setUsername(String username) {
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
    public ThemeBean setThemeName(String themeName) {
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
    public ThemeBean setThemeType(int themeType) {
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
    public ThemeBean setPrimaryColor(int primaryColor) {
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
    public ThemeBean setSecondaryColor(int secondaryColor) {
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
    public ThemeBean setDisplayColor(int displayColor) {
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
    public ThemeBean setPrimaryButtonTextColor(int primaryButtonTextColor) {
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
    public ThemeBean setSecondaryButtonTextColor(int secondaryButtonTextColor) {
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
    public ThemeBean setDisplayTextColor(int displayTextColor) {
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
    public ThemeBean setId(long id) {
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
    public ThemeBean setSelectedColor(int selectedColor) {
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
    public ThemeBean setPrimaryHighlightColor(int primaryHighlightColor) {
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
    public ThemeBean setSecondaryHighlightColor(int secondaryHighlightColor) {
        this.secondaryHighlightColor = secondaryHighlightColor;
        return this;
    }

}
