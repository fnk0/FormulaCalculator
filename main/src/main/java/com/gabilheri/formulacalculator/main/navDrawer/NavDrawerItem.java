package com.gabilheri.formulacalculator.main.navDrawer;

/**
 * Created by marcus on 5/6/14.
 * @author Marcus Gabilheri
 * @since May 2014
 * @version 1.0
 */
public class NavDrawerItem {

    private String title;
    private int icon;

    public NavDrawerItem() {}

    public NavDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
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
}
