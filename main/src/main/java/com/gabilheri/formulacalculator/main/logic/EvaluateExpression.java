package com.gabilheri.formulacalculator.main.logic;


import android.app.Fragment;
import android.util.Log;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.text.DecimalFormat;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.CustomFunction;
import de.congrace.exp4j.CustomOperator;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.InvalidCustomFunctionException;

/**
 * Created by marcus on 5/15/14.
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May 2014
 */
public class EvaluateExpression {

    private String expression, previousResult;
    private int angleType;
    public static int DEGREE = 0;
    public static int RADIANS = 1;
    private Fragment fragment;
    private DecimalFormat df;
    public static String LOG_TAG = "EVALUATE EXPRESSION";
    private int precision;

    /**
     * Default constructor.
     */
    public EvaluateExpression() {
    }

    /**
     * Constructor for the Evaluate Expression method.
     * @param expression
     *          The expression to be evaluated
     * @param fragment
     *          The fragment in which this expression is being callen
     */
    public EvaluateExpression(String expression, Fragment fragment, int angleType) {
        this.expression = expression;
        this.fragment = fragment;
        this.angleType = angleType;

        precision = Utils.getPrecisionValue(fragment.getActivity());
        df = Utils.getDecimalFormatForPrecision(precision);
    }

    /**
     * Returns the current expression of this method.
     * @return
     */
    public String getExpression() {
        return expression;
    }

    /**
     *
     * @param expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Main evaluate method. Evaluates the expression and returns the result string.
     * @return
     */
    public String evaluate() {
        String toReturn;
        Log.i("FULL EXPRESSION", expression);

        if(expression.equals("")) {
            return null;
        }

        CustomFunction arcSinFunc = null;
        CustomFunction arcCosFunc = null;
        CustomFunction arcTanFunc = null;
        CustomFunction cosdFunc = null;
        CustomFunction tandFunc = null;
        CustomFunction sindFunc = null;
        CustomFunction funX = null;
        CustomFunction taxFunc = null;
        CustomFunction log10 = null;
        CustomFunction ln = null;
        CustomFunction sqrt = null;
        CustomFunction cbrt = null;
        CustomFunction fact = null;
        CustomOperator percent = null;
        double varPi = Math.PI;
        double varE = Math.E;

        /**
         * The library has built in functions to handle all the trig functions
         * Overrading is necessary to handle degrees and radians
         */
        try {
            cosdFunc = new CustomFunction("cos") {
                public double applyFunction(double[] values) {
                    if(angleType == DEGREE) {
                        return Double.parseDouble(df.format(MathUtils.cosDegrees(values[0])));
                    } else if(angleType == RADIANS) {
                        return Double.parseDouble(df.format(Math.cos(values[0])));
                    } else {
                        return 0;
                    }
                }
            };
            tandFunc = new CustomFunction("tan") {
                public double applyFunction(double[] values) {
                    if(angleType == DEGREE) {
                        return Double.parseDouble(df.format(MathUtils.tanDegrees(values[0])));
                    } else {
                        return Double.parseDouble(df.format(Math.tan(values[0])));
                    }
                }
            };
            sindFunc = new CustomFunction("sin") {
                public double applyFunction(double[] values) {
                    if(angleType == DEGREE) {
                        return Double.parseDouble(df.format(MathUtils.sinDegrees(values[0])));
                    } else {
                        return Double.parseDouble(df.format(Math.sin(values[0])));
                    }
                }
            };
            log10 = new CustomFunction("log") {
                public double applyFunction(double[] values) {
                    return Math.log10(values[0]);
                }
            };
            ln = new CustomFunction("ln") {
                public double applyFunction(double[] values) {
                    return Math.log(values[0]);
                }
            };
            sqrt = new CustomFunction("sqrt") {
                @Override
                public double applyFunction(double... doubles) {
                    return Math.sqrt(doubles[0]);
                }
            };
            fact = new CustomFunction("fact") {
                @Override
                public double applyFunction(double... doubles) {
                    return factorial(doubles[0]);
                }
            };

            cbrt = new CustomFunction("cbrt") {
                @Override
                public double applyFunction(double... doubles) {
                    return Math.cbrt(doubles[0]);
                }
            };

            arcSinFunc = new CustomFunction("arcSin") {
                @Override
                public double applyFunction(double... doubles) {
                    if(angleType == DEGREE) {
                        return Double.parseDouble(df.format(MathUtils.arcSinDegrees(doubles[0])));
                    } else {
                        return Double.parseDouble(df.format(Math.asin(doubles[0])));
                    }
                }
            };

            arcCosFunc = new CustomFunction("arcCos") {
                @Override
                public double applyFunction(double... doubles) {
                    if(angleType == DEGREE) {
                        return Double.parseDouble(df.format(MathUtils.arcCosDegrees(doubles[0])));
                    } else {
                        return Double.parseDouble(df.format(Math.acos(doubles[0])));
                    }
                }
            };

            arcTanFunc = new CustomFunction("arcTan") {
                @Override
                public double applyFunction(double... doubles) {
                    if(angleType == DEGREE) {
                        return Double.parseDouble(df.format(MathUtils.arcTanDegrees(doubles[0])));
                    } else {
                        return Double.parseDouble(df.format(Math.atan(doubles[0])));
                    }
                }
            };

            taxFunc = new CustomFunction("tax") {
                @Override
                public double applyFunction(double... doubles) {
                    return doubles[0] * Utils.getTaxVaue(fragment.getActivity());
                }
            };

            /**
             * Override the normal modulus operand to handle the percentage.
             *
             */
            // TODO implement settings to let the user choose between percent vs modulus
            percent = new CustomOperator("%", true, 4 ) {
                @Override
                protected double applyOperation(double[] doubles) {
                    if (doubles[1] == 0d){
                        throw new ArithmeticException("Division by zero!");
                    }
                    return ((doubles[0] / 100) * doubles[1]);
                }
            };
        } catch (InvalidCustomFunctionException e1) {
            e1.printStackTrace();
        }

        expression = Utils.stripHTML(expression);
        expression = expression.replaceAll(fragment.getActivity().getString(R.string.divide), "/");
        expression = expression.replaceAll(fragment.getActivity().getString(R.string.multiply), "*");
        expression = expression.replaceAll(fragment.getActivity().getString(R.string.cube_root), "cbrt");
        expression = expression.replaceAll(fragment.getActivity().getString(R.string.sqrt), "sqrt");
        if(!checkFactorial(expression)) {
            return "Factorial error! Should be whole number!";
        }
        expression = expression.replaceAll(fragment.getString(R.string.factorial), "fact");

        try {
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '(' && i > 0 && !Utils.isOperator(expression.charAt(i - 1), fragment.getActivity())) {

                    if(expression.charAt(i) == '(' && expression.charAt(i -1) == '(') {
                        continue;
                    }
                    expression = expression.substring(0, i) + "*" + expression.substring(i, expression.length());
                }
                if (expression.charAt(i) == ')' && !Utils.isOperator(expression.charAt(i + 1), fragment.getActivity())) {
                    if(expression.charAt(i) == ')' && expression.charAt(i + 1) == ')') {
                        continue;
                    }
                    expression = expression.substring(0, i + 1) + "*" + expression.substring(i + 1, expression.length());
                }

                if(expression.charAt(i) == 'e' || expression.charAt(i) == fragment.getActivity().getString(R.string.pi).charAt(0)) {
                    if(Character.isDigit(expression.charAt(i - 1))) {
                        expression = expression.substring(0, i) + "*" + expression.substring(i, expression.length());
                    }
                    if(Character.isDigit(expression.charAt(i + 1))) {
                        expression = expression.substring(0, i+1) + "*" + expression.substring(i+1, expression.length());
                    }
                }

                try {
                    if (expression.charAt(i) == '.' && isNumber(expression.charAt(i - 1))) {
                        Log.i("EVALUATE EXPRESSION!", "FOUND DOT");
                        expression = expression.substring(0, i) + "0" + expression.substring(i, expression.length());
                        i++;
                    }
                } catch (Exception e) {
                    expression = expression.substring(0, i) + "0" + expression.substring(i, expression.length());
                    i++;
                }
            }
        } catch (Exception ex) {

        }
        Log.i("EXPRESSION: ", expression);

        if(previousResult == null || previousResult.equals(fragment.getString(R.string.input_error))) {
            previousResult = "0.0";
        }

        try {
            Calculable calc = new ExpressionBuilder(expression)
                    .withOperation(percent)
                    .withCustomFunction(cosdFunc)
                    .withCustomFunction(sindFunc)
                    .withCustomFunction(tandFunc)
                    .withCustomFunction(log10)
                    .withCustomFunction(ln)
                    .withCustomFunction(sqrt)
                    .withCustomFunction(cbrt)
                    .withCustomFunction(arcSinFunc)
                    .withCustomFunction(arcCosFunc)
                    .withCustomFunction(arcTanFunc)
                    .withCustomFunction(fact)
                    .withCustomFunction(taxFunc)
                    .withVariable(fragment.getString(R.string.pi), varPi)
                    .withVariable(fragment.getString(R.string.var_e), varE)
                    .withVariable(fragment.getString(R.string.ans), Double.parseDouble(previousResult))
                    .build();
            double result = calc.calculate();

            if(precision != 0) {
                result = Double.parseDouble(df.format(result));
            }

            if(result % 1 == 0) {
                Log.i("RESULT: ", "IS INTEGER!");
                long mLong = (long) result;
                toReturn = "" + mLong;
            } else {
                toReturn = "" + result;
            }

        } catch (Exception ex) {
            toReturn = fragment.getResources().getString(R.string.input_error);
            ex.printStackTrace();
        }
        return toReturn;
    }



    /**
     * Small factorial function.
     * Needs to be improved for a more sofiscated version :D
     * @param num
     *          The number to get the factorial for
     * @return
     *          num!
     */
    public double factorial(double num) {
        if(num == 0) {
            return 1;
        }
        return  num * factorial(num -1);
    }

    public boolean isNumber(char  c) {

        if (!Character.isDigit(c)) {
            return true;
        }
        return false;
    }

    /**
     * Helper function to check if all the factorial functions have valid input.
     * @param exp
     *          the expression for this evaluate
     * @return
     *          true if this expression can be evaluated.
     */
    public boolean checkFactorial(String exp) {
        double toCheck;
        int start = 0;
        int end = 0;
        boolean check = false;

        for(int i = 0; i < exp.length(); i++) {
            if(exp.charAt(i) == '!') {
                Log.i("INSIDE CHECK FACTORIAL!", "FOUND FACTORIAL!");
                check = true;
                start = i + 2;
                for(int j = i; j < exp.length(); j++) {
                    if(exp.charAt(j) == ')') {
                        end =  j;
                        break;
                    }
                }
            }
            if(check) {
                //Log.i("INSIDE CHECK FACTORIAL", "Start: " + start + " End: " + end);
                toCheck = Double.parseDouble(exp.substring(start, end));
                if(toCheck % 1 != 0) {
                    return false;
                }
            }
        }
        //Log.i("INSIDE CHECK FACTORIAL!", "NO FACTORIAL!");
        return true;
    }

    /**
     *
     * @return
     *      The current Previous Answer that is being used by this expression
     */
    public String getPreviousResult() {
        return previousResult;
    }

    /**
     * Setter to the Previous Answer
     *
     * @param previousResult
     *          The previous Answer to be used by this Expression
     * @return
     *          This Object for a Easy Chain build
     */
    public EvaluateExpression setPreviousResult(String previousResult) {
        this.previousResult = previousResult;
        return this;
    }
}
