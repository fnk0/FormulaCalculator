package com.gabilheri.formulacalculator.main.formulas;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/5/14
 */
public abstract class Formula {

    public abstract String getTitle();

    public abstract double evaluate();

    public abstract int getDrawableId();

}
