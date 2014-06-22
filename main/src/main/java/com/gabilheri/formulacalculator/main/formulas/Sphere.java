package com.gabilheri.formulacalculator.main.formulas;

import android.content.Context;

import com.gabilheri.formulacalculator.main.R;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class Sphere implements Formula {

    public static final int SPHERE_AREA = 0;
    public static final int SPHERE_SURFACE = 1;
    public static final int SPHERE_RADIUS_VOLUME = 2;
    public static final int SPHERE_RADIUS_AREA = 3;
    private long mRadius;
    private double mVolumeOrArea;
    private int mType;
    private String mTitle;

    /**
     *
     * @param mContext
     */
    public Sphere(Context mContext) {
        this.mTitle = mContext.getResources().getStringArray(R.array.sphere)[0];
    }

    /**
     *
     * @param mContext
     * @param mRadius
     * @param mType
     */
    public Sphere(Context mContext, long mRadius, int mType) {
        this(mContext);
        this.mRadius = mRadius;
        this.mType = mType;
    }

    public Sphere(Context mContext, double mVolumeOrArea, int mType) {
        this(mContext);
        this.mVolumeOrArea = mVolumeOrArea;
        this.mType = mType;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public double evaluate() {
        if(mType == SPHERE_AREA) {
            return 0.75 * Math.PI * Math.pow(mRadius, 3);
        } else if(mType == SPHERE_SURFACE) {
            return 4 * Math.PI * Math.pow(mRadius, 2);
        } else if(mType == SPHERE_RADIUS_VOLUME) {
            return Math.pow(3 * (mVolumeOrArea / (Math.PI * 4)), (1/3));
        } else if(mType == SPHERE_RADIUS_AREA) {
            return (1/2) * Math.sqrt(mVolumeOrArea / Math.PI);
        } else {
            return 0;
        }
    }

    @Override
    public int getDrawableId() {
        return 0;
    }
}
