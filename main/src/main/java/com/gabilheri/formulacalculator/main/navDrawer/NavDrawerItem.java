package com.gabilheri.formulacalculator.main.navDrawer;

import android.graphics.drawable.Drawable;

/**
 * Created by marcus on 5/6/14.
 * @author Marcus Gabilheri
 * @since May 2014
 * @version 1.0
 */
public class NavDrawerItem {

    private String title;
    private int icon;
    private Drawable drawable;

    public NavDrawerItem() {}

    public NavDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public NavDrawerItem(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
