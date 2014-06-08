package com.gabilheri.formulacalculator.main.formulas;

import android.content.Context;

import com.gabilheri.formulacalculator.main.R;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/5/14
 */
public class Circle extends Formula {

    private static final int CIRCLE_AREA = 0;
    private static final int CIRCLE_PERIMETER = 1;
    private int mRadius;
    private int mType;
    private String mTitle;


    public Circle(Context mContext) {
        this.mTitle = mContext.getResources().getStringArray(R.array.circle)[0];
    }

    public Circle(int mRadius, int mType) {
        this.mRadius = mRadius;
        this.mType = mType;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public double evaluate() {

        if(mType == CIRCLE_AREA) {
            return Math.PI * Math.pow(mRadius, 2);
        } else if(mType == CIRCLE_PERIMETER) {
            return 2 * Math.PI * mRadius;
        } else {
            return 0;
        }
    }

    @Override
    public int getDrawableId() {
        return R.drawable.ic_circle;
    }
}
