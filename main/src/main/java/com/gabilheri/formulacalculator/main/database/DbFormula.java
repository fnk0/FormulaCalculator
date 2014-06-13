package com.gabilheri.formulacalculator.main.database;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class DbFormula {

    private int id;
    private String formulaName;
    private int drawableId;
    private int favorite;

    public DbFormula() {}

    public DbFormula(String formulaName, int drawableId) {
        this.formulaName = formulaName;
        this.drawableId = drawableId;
    }

    public int getId() {
        return id;
    }

    public DbFormula setId(int id) {
        this.id = id;
        return this;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public DbFormula setFormulaName(String formulaName) {
        this.formulaName = formulaName;
        return this;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public DbFormula setDrawableId(int drawableId) {
        this.drawableId = drawableId;
        return this;
    }

    public int getFavorite() {
        return favorite;
    }

    public DbFormula setFavorite(int favorite) {
        this.favorite = favorite;
        return this;
    }
}
