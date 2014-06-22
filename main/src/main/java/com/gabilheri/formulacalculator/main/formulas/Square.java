package com.gabilheri.formulacalculator.main.formulas;

import android.content.Context;

import com.gabilheri.formulacalculator.main.R;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/5/14
 */
public class Square implements Formula {

    public static final int SQUARE_AREA = 0;
    public static final int SQUARE_PERIMETER = 1;
    public static final int SQUARE_VOLUME = 2;
    private String mTitle;
    private int mSide;
    private int mType;

    public Square(Context mContext) {
        mTitle = mContext.getResources().getStringArray(R.array.square)[0];
    }

    public Square(int mSide, int mType) {
        this.mSide = mSide;
        this.mType = mType;
    }


    public Square setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public int getSide() {
        return mSide;
    }

    public Square setSide(int mSide) {
        this.mSide = mSide;
        return this;
    }

    public int getType() {
        return mType;
    }

    public Square setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public int getDrawableId() {
        return R.drawable.ic_square;
    }

    @Override
    public double evaluate() {

        if(mType == SQUARE_AREA) {
            return Math.pow(mSide, 2);
        } else if (mType == SQUARE_VOLUME) {
            return Math.pow(mSide, 3);
        } else if(mType == SQUARE_PERIMETER) {
            return mSide * 4;
        } else {
            return 0;
        }
    }
}
