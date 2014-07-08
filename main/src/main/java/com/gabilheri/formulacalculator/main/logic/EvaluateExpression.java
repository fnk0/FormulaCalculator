package com.gabilheri.formulacalculator.main.logic;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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

    private String expression;
    private int angleType;
    public static int DEGREE = 0;
    public static int RADIANS = 1;
    private Fragment fragment;
    private DecimalFormat df;
    public static String LOG_TAG = "EVALUATE EXPRESSION";

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
        // TODO implement this precisiona s a setting feature.
        df = new DecimalFormat("#0.#########"); // Current precision = 9
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

        CustomFunction cosdFunc = null;
        CustomFunction tandFunc = null;
        CustomFunction sindFunc = null;
        CustomFunction funX = null;
        CustomFunction log10 = null;
        CustomFunction ln = null;
        CustomFunction sqrt = null;
        CustomFunction fact = null;
        CustomFunction release = null;
        CustomOperator percent = null;
        double varPi = Math.PI;
        double varE = Math.E;

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

            release = new CustomFunction("var") {
                @Override
                public double applyFunction(double... doubles) {
                    SharedPreferences storedVar = fragment.getActivity().getSharedPreferences("storeVar", Context.MODE_PRIVATE);
                    return Double.parseDouble(storedVar.getString("var" + (int) doubles[0], "0"));
                }
            };

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
        try {
            Calculable calc = new ExpressionBuilder(expression)
                    .withOperation(percent)
                    .withCustomFunction(cosdFunc)
                    .withCustomFunction(sindFunc)
                    .withCustomFunction(tandFunc)
                    .withCustomFunction(log10)
                    .withCustomFunction(ln)
                    .withCustomFunction(sqrt)
                    .withCustomFunction(fact)
                    .withCustomFunction(release)
                    .withVariable(fragment.getString(R.string.pi), varPi)
                    .withVariable(fragment.getString(R.string.var_e), varE)
                    .build();
            double result = calc.calculate();
            result = Double.parseDouble(df.format(result));
            
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
     * @return
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
     * @return
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
}
