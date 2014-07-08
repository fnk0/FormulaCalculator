package com.gabilheri.formulacalculator.main.formulas;

import android.content.Context;
import android.util.Log;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.logic.MathUtils;

import java.text.DecimalFormat;

import static com.gabilheri.formulacalculator.main.logic.MathUtils.sinDegrees;
import static com.gabilheri.formulacalculator.main.utils.Utils.squareNumber;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/5/14
 */
public class Triangle implements Formula {

    private static final String LOG_TAG = "TRIANGLE";
    public static final int TRIANGLE_PERIMETER = 0;
    public static final int TRIANGLE_AREA = 1;
    public static final int TRIANGLE_PYTHAGOREAN = 2;
    public static final int TRIANGLE_SSA = 3;
    public static final int TRIANGLE_ASA = 4;
    public static final int TRIANGLE_SAS = 5;
    public static final int TRIANGLE_SSS = 6;
    public static final int TRIANGLE_AAS = 7;
    public static final int DEGREES = 99;
    public static final int RADIANS = 100;
    private int angleType;
    private String mTitle;
    private int mType = -1;
    private double sideA, sideB, sideC, angleA, angleB, angleC, mBase, mHeight;
    private DecimalFormat df;

    /**
     * Default constructor
     * @param mContext
     *              the context in which this Triangle is being instantiated
     */
    public Triangle(Context mContext) {
        this.mTitle = mContext.getResources().getStringArray(R.array.triangle)[0];
    }

    /**
     * Constructor for this object
     *
     * @param mContext
     *          the context in which this Triangle is being instantiated
     * @param type
     *          the type of this triangle
     */
    public Triangle(Context mContext, int type) {
        this(mContext);
        this.mType = type;
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
     * Returns the current angle type set for this triangle
     *
     * @return
     *          degrees or radians
     */
    public int getAngleType() {
        return angleType;
    }

    /**
     * Setter for the angle type.
     * @param angleType
     *          the angle type of this triangle
     * @return
     *          This object for easy chain when building a object
     */
    public Triangle setAngleType(int angleType) {
        this.angleType = angleType;
        return this;
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
     * Method to solve for the missing side in a right triangle.
     *
     * @return
     *      A double with the value of the unknown side
     */
    private double evaluatePythagorean() {
        if(sideC == -1) {
            return Math.sqrt(squareNumber(sideA) + squareNumber(sideB));
        } else if(sideB == -1) {
            return Math.sqrt(squareNumber(sideC) - squareNumber(sideB));
        } else if(sideA == -1) {
            return Math.sqrt(squareNumber(sideC) - squareNumber(sideA));
        }
        return 0;
    }

    /**
     * Angle Angle Side
     * AAS Triangle - 2 Angles and a side which is NOT between the angles;
     *
     * Necessary Input: Angle A, Angle C, Side C
     * Returns: Angle C, Side A, Side B
     *
     * @param sideC
     *          The known side of this triangle
     * @param angleA
     *          The angle adjacent to the known side
     * @param angleC
     *          The opposite angle to the known side
     *
     * @return
     *      an array with the results of this triangle.
     */
    public double[] angleAngleSide(double sideC, double angleA, double angleC) {

        double[] result = new double[3];

        angleB = 180 - angleA - angleC;
        sideA = (sideC * sinDegrees(angleA)) / sinDegrees(angleC);
        sideB = (sideC * sinDegrees(angleB)) / sinDegrees(angleC);

        result[0] = angleB;
        result[1] = sideA;
        result[2] = sideB;

        if(MainActivity.DEBUG) {
            Log.i(LOG_TAG, "Angle B: " + angleB);
            Log.i(LOG_TAG, "Side A: " + sideA);
            Log.i(LOG_TAG, "Side B: " + sideB);
        }

        return result;
    }

    /**
     * Angle Side Angle
     * ASA Triangle - 2 Angles and a side in BETWEEN the angles
     *
     * Necessary input: angleA, angleB, sideC
     * Returns: angleC, sideA, sideB
     *
     * @param angleA
     *          The angle on the LEFT of the known side
     * @param angleB
     *          The angle on the RIGHT of the known side
     * @param sideC
     *          The side in between the Angles
     * @return
     *          an array with the results for this triangle.
     */
    public double[] angleSideAngle(double angleA, double angleB, double sideC) {

        double[] result = new double[3];

        angleC = 180 - angleA - angleB;
        sideA = (sideC * sinDegrees(angleA)) / sinDegrees(angleC);
        sideB = (sideC * sinDegrees(angleB)) / sinDegrees(angleC);

        result[0] = sideA;
        result[1] = sideB;
        result[2] = angleC;


        if(MainActivity.DEBUG) {
            Log.i(LOG_TAG, "Angle C: " + angleC);
            Log.i(LOG_TAG, "Side A: " + sideA);
            Log.i(LOG_TAG, "Side B: " + sideB);
        }

        return result;
    }

    /**
     * Side Angle Side
     * SAS Triangle - 2 Sides and a Angle in BETWEEN the sides.
     *
     * Necessary Input: angleA, sideB, sideC
     * Returns: sideA, angleB, angleC
     *
     * @param angleA
     *          The angle in between the sides
     * @param sideB
     *          The side to the LEFT of the Angle
     * @param sideC
     *          The side to the RIGHT of the Angle
     * @return
     *          An array with the results of this triangle.
     */
    public double[] sideAngleSide(double angleA, double sideB, double sideC) {

        double[] result = new double[3];

        sideA = Math.sqrt(squareNumber(sideB) + squareNumber(sideC) - (2 * sideB * sideC * MathUtils.cosDegrees(angleA)));
        angleB = Math.toDegrees(Math.asin((sideB * sinDegrees(angleA)) / sideA));
        angleC = 180 - angleA - angleB;

        result[0] = sideA;
        result[1] = angleB;
        result[2] = angleC;

        if(MainActivity.DEBUG) {
            Log.i(LOG_TAG, "Side A: " + sideA);
            Log.i(LOG_TAG, "Angle B: " + angleB);
            Log.i(LOG_TAG, "Angle C: " + angleC);
        }

        return result;
    }

    /**
     *
     * Side Side Angle
     * SSA Triangle - 2 Sides and a angle that is NOT in between the sides.
     *
     * Necessary Input: sideB, sideC, angleB
     * Returns: sideA, angleA, angleC
     *
     * @param sideB
     *          The side opposite to the known Angle
     * @param sideC
     *          The side adjacent to the known Angle
     * @param angleB
     *          The known Angle
     * @return
     *          An array with the results of this triangle
     */
    public double[] sideSideAngle(double sideB, double sideC, double angleB) {

        double[] result = new double[7];
        double numSolutions = 1;
        double angleA2;
        double angleC2;
        double sideA2;

        angleC = Math.toDegrees(Math.asin((sideC * sinDegrees(angleB)) / sideB));
        angleA = 180 - angleC - angleB;
        sideA = (sideB * sinDegrees(angleA)) / sinDegrees(angleB);

        result[1] = sideA;
        result[2] = angleA;
        result[3] = angleC;

        if(MainActivity.DEBUG) {
            Log.i(LOG_TAG, "Side A: " + sideA);
            Log.i(LOG_TAG, "Angle A: " + angleA);
            Log.i(LOG_TAG, "Angle B: " + angleB);
            Log.i(LOG_TAG, "Angle C: " + angleC);
            if(angleA + angleB + angleC == 180) {
                Log.i(LOG_TAG, "TRUE!");
            } else {
                Log.i(LOG_TAG, "FALSE!");
            }
        }

        angleC2 = 180 - angleC;
        angleA2 = 180 - angleC2 - angleB;

        if(angleA2 > 0) {
            numSolutions = 2;
            sideA2 = (sideB * sinDegrees(angleA2) / sinDegrees(angleB));
            result[4] = sideA2;
            result[5] = angleC2;
            result[6] = angleA2;

            if(MainActivity.DEBUG) {
                Log.i(LOG_TAG, "Side A - 2: " + sideA2);
                Log.i(LOG_TAG, "Angle A - 2: " + angleA2);
                Log.i(LOG_TAG, "Angle B - 2: " + angleB);
                Log.i(LOG_TAG, "Angle C - 2: " + angleC2);
                if(angleA2 + angleB + angleC2 == 180) {
                    Log.i(LOG_TAG, "TRUE!");
                } else {
                    Log.i(LOG_TAG, "FALSE!");
                }
            }
        }

        result[0] = numSolutions;

        return result;
    }

    /**
     * Side Side Side
     * SSS Triangle - 3 Sides are known
     *
     * Input Required: sideA, sideB, sideC
     * Returns: angleA, angleB, angleC
     * @param sideA
     *          an side of the triangle
     * @param sideB
     *          an side of the triangle
     * @param sideC
     *          an side of the triangle
     * @return
     *          The unknown angles of this triangle
     */
    public double[] sideSideSide(double sideA, double sideB, double sideC) {

        double[] result = new double[3];

        angleA = Math.toDegrees(Math.acos((squareNumber(sideB) + squareNumber(sideC) - squareNumber(sideA)) / (2 * sideB * sideC)));
        angleB = Math.toDegrees(Math.acos((squareNumber(sideA) + squareNumber(sideC) - squareNumber(sideB)) / (2 * sideA * sideC)));
        angleC = 180 - angleA - angleB;

        result[0] = angleA;
        result[1] = angleB;
        result[2] = angleC;

        if(MainActivity.DEBUG) {
            Log.i(LOG_TAG, "Angle A: " + angleA);
            Log.i(LOG_TAG, "Angle B: " + angleB);
            Log.i(LOG_TAG, "Angle C: " + angleC);
        }

        return result;
    }
}
