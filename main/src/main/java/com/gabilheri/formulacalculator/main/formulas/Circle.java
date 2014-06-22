package com.gabilheri.formulacalculator.main.formulas;

import android.content.Context;

import com.gabilheri.formulacalculator.main.R;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/5/14
 */
public class Circle implements Formula {

    public static final int CIRCLE_AREA = 0;
    public static final int CIRCLE_PERIMETER = 1;
    private long mRadius;
    private int mType;
    private String mTitle;


    public Circle() {}

    public Circle(Context mContext, long mRadius, int mType) {
        this.mTitle = mContext.getResources().getStringArray(R.array.circle)[0];
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
