package com.gabilheri.formulacalculator.main.formulas;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/5/14
 */
public interface  Formula {

    public String getTitle();

    public double evaluate();

    public int getDrawableId();

}
