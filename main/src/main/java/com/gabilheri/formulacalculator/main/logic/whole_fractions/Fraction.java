package com.gabilheri.formulacalculator.main.logic.whole_fractions;

/**
 * Created by dawkins on 7/16/14.
 */
public class Fraction {

    private int denominator;
    private int numerator;

    public Fraction() {
        this(1,1);
    }

    public Fraction(int numerator, int denominator) {
        this.setNumerator(numerator);
        this.setDenominator(denominator);
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {

        if(denominator==0) {
            throw new InvalidNumeratorException("Cannot have 0 as a denominator");
        }

        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * Returns a fraction string
     *
     * @return
     */
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

}