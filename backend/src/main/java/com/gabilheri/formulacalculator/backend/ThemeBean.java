package com.gabilheri.formulacalculator.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/10/14.
 */
@Entity
public class ThemeBean {

    /**
     * Variables for this Theme
     */
    @Id
    private long id;
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

    public ThemeBean() {
    }

    public ThemeBean(long id, String username, String themeName, int themeType, int primaryColor, int secondaryColor, int displayColor, int displayTextColor, int primaryButtonTextColor, int secondaryButtonTextColor, int primaryHighlightColor, int secondaryHighlightColor, int selectedColor) {
        this.id = id;
        this.username = username;
        this.themeName = themeName;
        this.themeType = themeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.displayColor = displayColor;
        this.displayTextColor = displayTextColor;
        this.primaryButtonTextColor = primaryButtonTextColor;
        this.secondaryButtonTextColor = secondaryButtonTextColor;
        this.primaryHighlightColor = primaryHighlightColor;
        this.secondaryHighlightColor = secondaryHighlightColor;
        this.selectedColor = selectedColor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getThemeType() {
        return themeType;
    }

    public void setThemeType(int themeType) {
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

    public int getDisplayTextColor() {
        return displayTextColor;
    }

    public void setDisplayTextColor(int displayTextColor) {
        this.displayTextColor = displayTextColor;
    }

    public int getPrimaryButtonTextColor() {
        return primaryButtonTextColor;
    }

    public void setPrimaryButtonTextColor(int primaryButtonTextColor) {
        this.primaryButtonTextColor = primaryButtonTextColor;
    }

    public int getSecondaryButtonTextColor() {
        return secondaryButtonTextColor;
    }

    public void setSecondaryButtonTextColor(int secondaryButtonTextColor) {
        this.secondaryButtonTextColor = secondaryButtonTextColor;
    }

    public int getPrimaryHighlightColor() {
        return primaryHighlightColor;
    }

    public void setPrimaryHighlightColor(int primaryHighlightColor) {
        this.primaryHighlightColor = primaryHighlightColor;
    }

    public int getSecondaryHighlightColor() {
        return secondaryHighlightColor;
    }

    public void setSecondaryHighlightColor(int secondaryHighlightColor) {
        this.secondaryHighlightColor = secondaryHighlightColor;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }
}
