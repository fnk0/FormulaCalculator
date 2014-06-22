package com.gabilheri.formulacalculator.main.formulas;

import android.content.Context;
import static com.gabilheri.formulacalculator.main.utils.Utils.*;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.utils.Utils;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/5/14
 */
public class Triangle implements Formula {

    public static final int TRIANGLE_PERIMETER = 0;
    public static final int TRIANGLE_AREA = 1;
    public static final int TRIANGLE_PYTHAGOREAN = 2;
    public static final int TRIANGLE_SSA = 3;
    public static final int TRIANGLE_ASA = 4;
    public static final int TRIANGLE_SAS = 5;
    public static final int TRIANGLE_SSS = 6;
    public static final int TRIANGLE_AAS = 7;
    private String mTitle;
    private int mType = -1;
    private double sideA, sideB, sideC, angleA, angleB, angleC, mBase, mHeight;

    /**
     * Default constructor
     * @param mContext
     *              the context in which this Triangle is being instantiated
     */
    public Triangle(Context mContext) {
        this.mTitle = mContext.getResources().getStringArray(R.array.triangle)[0];
    }

    /**
     * Constructor used to calculate the Area of a Triangle
     *
     * @param mContext
     *              the context in which this Triangle is being instantiated
     * @param mBase
     *              the base of this triangle
     * @param mHeight
     *              the Height of this Triangle
     */
    public Triangle(Context mContext, double mBase, double mHeight) {
        this(mContext);
        this.mHeight = mHeight;
        this.mBase = mBase;
    }

    /**
     *
     * Constructor that should be used to calculate the Angles of a Triangle SSS OR
     * To calculate the missing side using the pythagorean formula
     * If calculating Pythagorean the missing side should be passed as -1
     *
     * @param mContext
     *              the context in which this Triangle is being instantiated
     * @param sideA
     *              the opposite side of the 90 degree angle
     * @param sideB
     *              the adjacent side of the 90 degree angle
     * @param sideC
     *              the Hypotenuse of this triangle
     * @param mType
     *              The type of calculation that should be done. For this constructor it should be
     *              PYTHAGOREAN or SSS
     */
    public Triangle(Context mContext, double sideA, double sideB, double sideC, int mType) {
        this(mContext);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.mType = mType;
    }

    /**
     *
     * Most general constructor For all the Triangles involving Angles and Sides
     *
     * @param mContext
     *               the context in which this Triangle is being instantiated
     * @param sideA
     * @param sideB
     * @param sideC
     * @param angleA
     * @param angleB
     * @param angleC
     * @param mType
     *             the type of triangle that should be evaluated.
     */
    public Triangle(Context mContext, double sideA, double sideB, double sideC, double angleA, double angleB, double angleC, int mType) {
        this(mContext);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.angleA = angleA;
        this.angleB = angleB;
        this.angleC = angleC;
        this.mType = mType;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public double evaluate() {

        if(mType != -1) {
            switch (mType) {
                case TRIANGLE_AREA:
                    return (mBase * mHeight) / 2;
                case TRIANGLE_PERIMETER:
                    return sideA + sideB + sideC;
                case TRIANGLE_PYTHAGOREAN:
                    return evaluatePythagorean();
            }
        }

        return 0;
    }

    @Override
    public int getDrawableId() {
        return 0;
    }

    /**
     *
     * @return
     */
    private double evaluatePythagorean() {
        if(sideC == -1) {
            return Math.sqrt(squareNUmber(sideA) + squareNUmber(sideB));
        } else if(sideB == -1) {
            return Math.sqrt(squareNUmber(sideC) - squareNUmber(sideB));
        } else if(sideA == -1) {
            return Math.sqrt(squareNUmber(sideC) - squareNUmber(sideA));
        }
        return 0;
    }
}
