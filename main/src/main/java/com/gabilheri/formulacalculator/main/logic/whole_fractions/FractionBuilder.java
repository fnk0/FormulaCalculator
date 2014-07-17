package com.gabilheri.formulacalculator.main.logic.whole_fractions;



/**
 * Created by dawkins on 7/16/14.
 */
public class FractionBuilder extends Fraction {

    public static final double PRECISON = 0.1;

    public FractionBuilder() {

    }

    public Fraction createFraction(int numerator, int denominator) {
        return new Fraction(numerator, denominator);
    }

    public Fraction createFraction(double number) throws IrrationalNumberException {

        int numerator = 1, denominator = 0;

        for(int x = 1; x < Integer.MAX_VALUE; x++) {
            double test = number * x;
            if(test == ((int)test)) {
                numerator = (int)test;
                denominator = x;
                break;
            }
        }

        if(denominator == 0) {
            throw new IrrationalNumberException("Fraction is irrational or too large to be computed");
        }

        return new Fraction(numerator, denominator);
    }

}
