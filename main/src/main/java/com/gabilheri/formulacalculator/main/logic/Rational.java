package com.gabilheri.formulacalculator.main.logic;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/16/14.
 */
public class Rational {

    private int num, denom;

    public Rational(double d) {
        String s = String.valueOf(d);
        int digitsDec = s.length() - 1 - s.indexOf('.');

        int denom = 1;
        for(int i = 0; i < digitsDec; i++){
            d *= 10;
            denom *= 10;
        }
        int num = (int) Math.round(d);

        this.num = num;
        this.denom = denom;
    }

    public Rational(int num, int denom) {
        this.num = num; this.denom = denom;
    }

    public String toString() {
        return String.valueOf(num) + "/" + String.valueOf(denom);
    }
}
