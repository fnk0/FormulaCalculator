package com.gabilheri.formulacalculator.main.logic.unit;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/23/14.
 */
public class UnitSpinnerItem  {

    private String title;
    private int icon, unitType;

    public UnitSpinnerItem() {}

    public UnitSpinnerItem(String title, int icon, int unitType) {
        this.title = title;
        this.icon = icon;
        this.unitType = unitType;
    }

    public String getTitle() {
        return title;
    }

    public UnitSpinnerItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getIcon() {
        return icon;
    }

    public UnitSpinnerItem setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public int getUnitType() {
        return unitType;
    }

    public UnitSpinnerItem setUnitType(int unitType) {
        this.unitType = unitType;
        return this;
    }
}
