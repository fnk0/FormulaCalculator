package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.adapters.FormulasListAdapter;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.ResultLog;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.dialogs.VariablesDialog;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.logic.EvaluateExpression;
import com.gabilheri.formulacalculator.main.logic.Expression;
import com.gabilheri.formulacalculator.main.logic.ExpressionElement;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.HashMap;
import java.util.List;


public class CalculatorFragment extends Fragment implements FragmentWithKeypad, View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private static final String LOG_TAG = "MainFragment";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    protected LinearLayout resultLayoutKey;
    private static FormulasListAdapter listAdapter;
    private static ExpandableListView listView;
    private static List<String> formulaTitles;
    private static HashMap<String, List<String>> formulas;
    private static TextView inputBoxKey, resultBoxKey, blinkingText;
    private String textInputBox1, previousResult;
    private int angleType;
    private int parCounter = 0;
    private String[] colors;
    private VariablesDialog varDialog;
    private boolean clearResult = false;
    private DatabaseHelper dbHelper;
    private KeypadFragment mKeypadFragment;
    private KeypadFunctionsFragment mKeypadFunctionsFragment;
    private View rootView;
    private Theme currentTheme;
    private int resizeType = Utils.NOT_RESIZE;
    private Expression stackExpression;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public CalculatorFragment() {
        mKeypadFragment = new KeypadFragment();
        mKeypadFragment.setListener(this);
        mKeypadFunctionsFragment = new KeypadFunctionsFragment();
        mKeypadFunctionsFragment.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        stackExpression = new Expression();
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SharedPreferences mPreferences = getActivity().getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        currentTheme = dbHelper.getThemeByName(mPreferences.getString(MainActivity.CURRENT_THEME, MainActivity.CURRENT_THEME));

        LinearLayout mLayout = (LinearLayout) view.findViewById(R.id.calculatorFrag);
        //Utils.setInsets(getActivity(), mLayout);

        blinkingText = (TextView) view.findViewById(R.id.blinkingText);
        blinkingText.setBackgroundColor(currentTheme.getDisplayTextColor());
        Utils.blink(blinkingText);

        angleType = EvaluateExpression.DEGREE;
        rootView = view;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        resultLayoutKey = (LinearLayout) view.findViewById(R.id.resultLayoutKey);
        resultLayoutKey.setBackgroundColor(currentTheme.getDisplayColor());
        inputBoxKey = (TextView) view.findViewById(R.id.inputBox1);
        resultBoxKey = (TextView) view.findViewById(R.id.resultBox1);

        inputBoxKey.setTextSize(Utils.getDisplayTextSize(getActivity()));
        resultBoxKey.setTextSize(Utils.getDisplayTextSize(getActivity()));
        blinkingText.setTextSize(Utils.getDisplayTextSize(getActivity()));

        inputBoxKey.setTextColor(currentTheme.getDisplayTextColor());

        inputBoxKey.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                final int textViewWidth = right - left;
                Utils.shouldResizeText(getActivity(), textViewWidth);
            }
        });


        resultBoxKey.setTextColor(currentTheme.getDisplayTextColor());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Bundle extraBundle = null;

        try {
            extraBundle = getArguments();
        } catch (Exception ex) {
        }

        if (extraBundle != null) {
            textInputBox1 = extraBundle.getString("logResult");
            inputBoxKey.setText(Html.fromHtml(textInputBox1));
        } else {
            textInputBox1 = "";

        }

        instantiateColors();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        private Fragment mFragment;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                mFragment = mKeypadFunctionsFragment;
            } else if (position == 1) {
                mFragment = mKeypadFragment;
            }
            return mFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    @Override
    public void onClick(View v) {
        handleKeypad(v);
    }

    /**
     * Handles the keypad press.
     *
     * @param view The View being pressed.
     */
    public void handleKeypad(View view) {

        int id = view.getId();
        parCounter = 0;
        boolean isAnswerInserted = false;
        String str;

        if (clearResult && Utils.isButtonOperator(id)) {
            //Log.i(LOG_TAG, "Here Inside Operators!!");
            switch (id) {
                case R.id.plus:
                    textInputBox1 = getString(R.string.ans) + getString(R.string.plus);
                    break;
                case R.id.minus:
                    textInputBox1 = getString(R.string.ans) + getString(R.string.minus_simbol);
                    break;
                case R.id.multiply:
                    textInputBox1 = getString(R.string.ans) + getString(R.string.multiply);
                    break;
                case R.id.divide:
                    textInputBox1 = getString(R.string.ans) + getString(R.string.divide);
                    break;
                case R.id.keypadPwr:
                    textInputBox1 = getString(R.string.ans) + getString(R.string.exp_symbol);
                    break;
                case R.id.keypadSqrt:
                    textInputBox1 = getString(R.string.sqrt) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>" + getString(R.string.ans);
                    break;
            }
            isAnswerInserted = true;
            clearResult = false;
        } else if (clearResult && id != R.id.keypadStore && id != R.id.keypadRelease) {
            clearDisplay();
            clearResult = false;
        }

        for (int i = 0; i < textInputBox1.length(); i++) {
            if (textInputBox1.charAt(i) == '(') {
                parCounter++;
            }

            if (textInputBox1.charAt(i) == ')') {
                parCounter--;
            }
        }

        if (!isAnswerInserted) {
            switch (id) {
                case R.id.equal:
                    switch (resizeType) {
                        case Utils.RESIZE_ONCE:
                            resultBoxKey.setTextSize(Utils.getDisplayResizedText(getActivity()));
                            break;
                        case Utils.RESIZE_TWICE:
                            resultBoxKey.setTextSize(Utils.getDisplayResizedTextTwice(getActivity()));;
                            break;
                        case Utils.RESIZE_THIRD:
                            resultBoxKey.setTextSize(Utils.getDisplayResizedTextThird(getActivity()));
                            break;
                        case Utils.RESIZE_FOURTH:
                            resultBoxKey.setTextSize(Utils.getDisplayResizedTextFourth(getActivity()));
                            break;
                    }

                    evaluateExpression();
                    break;
                case R.id.keypadDel:
                    deleteFromDisplay();
                    break;
                case R.id.keypad0:
                    textInputBox1 += getString(R.string._0);
                    stackExpression.push(new ExpressionElement(getString(R.string._0), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad1:
                    textInputBox1 += getString(R.string._1);
                    stackExpression.push(new ExpressionElement(getString(R.string._1), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad2:
                    textInputBox1 += getString(R.string._2);
                    stackExpression.push(new ExpressionElement(getString(R.string._2), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad3:
                    textInputBox1 += getString(R.string._3);
                    stackExpression.push(new ExpressionElement(getString(R.string._3), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad4:
                    textInputBox1 += getString(R.string._4);
                    stackExpression.push(new ExpressionElement(getString(R.string._4), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad5:
                    textInputBox1 += getString(R.string._5);
                    stackExpression.push(new ExpressionElement(getString(R.string._5), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad6:
                    textInputBox1 += getString(R.string._6);
                    stackExpression.push(new ExpressionElement(getString(R.string._6), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad7:
                    textInputBox1 += getString(R.string._7);
                    stackExpression.push(new ExpressionElement(getString(R.string._7), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad8:
                    textInputBox1 += getString(R.string._8);
                    stackExpression.push(new ExpressionElement(getString(R.string._8), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.keypad9:
                    textInputBox1 += getString(R.string._9);
                    stackExpression.push(new ExpressionElement(getString(R.string._9), ExpressionElement.ExpressionType.NUMBER));
                    break;
                case R.id.plus:
                    textInputBox1 += getString(R.string.plus);
                    stackExpression.push(new ExpressionElement(getString(R.string.plus), ExpressionElement.ExpressionType.OPERATOR));
                    break;
                case R.id.minus:
                    textInputBox1 += getString(R.string.minus_simbol);
                    stackExpression.push(new ExpressionElement(getString(R.string.minus_simbol), ExpressionElement.ExpressionType.OPERATOR));
                    break;
                case R.id.multiply:
                    textInputBox1 += getString(R.string.multiply);
                    stackExpression.push(new ExpressionElement(getString(R.string.multiply), ExpressionElement.ExpressionType.OPERATOR));
                    break;
                case R.id.divide:
                    textInputBox1 += getString(R.string.divide);
                    stackExpression.push(new ExpressionElement(getString(R.string.divide), ExpressionElement.ExpressionType.OPERATOR));
                    break;
                case R.id.varE:
                    textInputBox1 += getString(R.string.var_e);
                    stackExpression.push(new ExpressionElement(getString(R.string.var_e), ExpressionElement.ExpressionType.CONSTANT));
                    break;
                case R.id.varPi:
                    textInputBox1 += getString(R.string.pi);
                    stackExpression.push(new ExpressionElement(getString(R.string.pi), ExpressionElement.ExpressionType.CONSTANT));
                    break;
                case R.id.keypadDot:
                    textInputBox1 += getString(R.string.dot);
                    stackExpression.push(new ExpressionElement(getString(R.string.dot), ExpressionElement.ExpressionType.DOT));
                    break;
                case R.id.keypadStore:
                    startDialog(VariablesDialog.STORE_DIALOG);
                    break;
                case R.id.keypadRelease:
                    startDialog(VariablesDialog.RELEASE_DIALOG);
                    break;
                case R.id.keypadSqrt:
                    str = getString(R.string.sqrt) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnCos:
                    str = "cos" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnSin:
                    str = "sin" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnTan:
                    str = "tan" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnFact:
                    str = getString(R.string.factorial) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.keypadPwr:
                    textInputBox1 += getString(R.string.exp_symbol);
                    stackExpression.push(new ExpressionElement(getString(R.string.exp_symbol), ExpressionElement.ExpressionType.OPERATOR));
                    break;
                case R.id.parLeft:
                    //Log.i("PARSIZE", "" + ("<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>").length());
                    str = "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.parRight:
                    if (parCounter > 0) {
                        str = "<font color=" + colors[parCounter - 1] + ">" + getString(R.string.par_right) + "</font>";
                        textInputBox1 += str;
                        stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    } else {
                        return;
                    }
                    break;
                case R.id.btnPercent:
                    textInputBox1 += "%";
                    stackExpression.push(new ExpressionElement("%", ExpressionElement.ExpressionType.OPERATOR));
                    break;
                case R.id.btnLog:
                    str = "log" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnLn:
                    str = "ln" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.degreeRad:
                    if (angleType == EvaluateExpression.DEGREE) {
                        angleType = EvaluateExpression.RADIANS;
                        ((DefaultButton) view).setText(getString(R.string.radians_button));
                        view.setSelected(true);
                    } else {
                        angleType = EvaluateExpression.DEGREE;
                        ((DefaultButton) view).setText(getString(R.string.degree_button));
                        view.setSelected(false);
                    }
                    return;
                case R.id.answer:
                    if (previousResult != null) {
                        textInputBox1 += getResources().getString(R.string.ans);
                    }
                    break;
                case R.id.keypadTax:
                    str = getString(R.string.tax_input) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.cubeRoot:
                    str = getString(R.string.cube_root) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnArcSin:
                    str = getString(R.string.input_arcsin) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnArcCos:
                    str = getString(R.string.input_arccos) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
                case R.id.btnArcTan:
                    str = getString(R.string.input_arctan) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    textInputBox1 += str;
                    stackExpression.push(new ExpressionElement(str, ExpressionElement.ExpressionType.FUNCTION));
                    break;
            }
        }

        if(Utils.shouldResizeText(getActivity(), inputBoxKey.getWidth())) {
            if(resizeType == Utils.NOT_RESIZE) {
                resizeType = Utils.RESIZE_ONCE;
                inputBoxKey.setTextSize(Utils.getDisplayResizedText(getActivity()));
                blinkingText.setTextSize(Utils.getDisplayResizedText(getActivity()));
            } else if (resizeType == Utils.RESIZE_ONCE) {
                resizeType = Utils.RESIZE_TWICE;
                inputBoxKey.setTextSize(Utils.getDisplayResizedTextTwice(getActivity()));
                blinkingText.setTextSize(Utils.getDisplayResizedTextTwice(getActivity()));
            } else if(resizeType == Utils.RESIZE_TWICE) {
                resizeType = Utils.RESIZE_THIRD;
                inputBoxKey.setTextSize(Utils.getDisplayResizedTextThird(getActivity()));
                blinkingText.setTextSize(Utils.getDisplayResizedTextThird(getActivity()));
            } else if(resizeType == Utils.RESIZE_THIRD) {
                resizeType = Utils.RESIZE_FOURTH;
                inputBoxKey.setTextSize(Utils.getDisplayResizedTextFourth(getActivity()));
                blinkingText.setTextSize(Utils.getDisplayResizedTextFourth(getActivity()));
            }
        }
        inputBoxKey.setText(Html.fromHtml(textInputBox1));
        Log.d("FRAGMENT MAIN: ", "Text Input: " + textInputBox1);
    }

    /**
     * Handles the evaluate button.
     */
    public void evaluateExpression() {

        EvaluateExpression expression = new EvaluateExpression(textInputBox1, this, angleType);
        expression.setPreviousResult(previousResult);
        String result = expression.evaluate();
        Log.i("STACK EXPRESSION", stackExpression.toString());
        if (result == null) {
            //Log.i(LOG_TAG, "Empty Input");
            inputBoxKey.setText("");
            resultBoxKey.setText("");
            return;
        }

        if (textInputBox1.equals(getString(R.string._0))) {
            textInputBox1 = "";
        }

        if (!result.equals(getResources().getString(R.string.input_error))) {
            ResultLog resultLog = new ResultLog(textInputBox1, result);
            dbHelper.createResultLog(resultLog);
        }

        previousResult = result;
        resultBoxKey.setText(result);

        if (!result.equals(getResources().getString(R.string.input_error))) {
            clearResult = true;
        }
    }

    /**
     * Clears the Display.
     */
    public void clearDisplay() {
        textInputBox1 = "";
        resizeType = Utils.NOT_RESIZE;
        resultBoxKey.setText("");
        inputBoxKey.setTextSize(Utils.getDisplayTextSize(getActivity()));
        blinkingText.setTextSize(Utils.getDisplayTextSize(getActivity()));
        clearResult = false;
    }

    /**
     * Handles the DEL key
     */
    public void deleteFromDisplay() {

        boolean par = false;
        //Log.i("TEXT SIZE BEFORE!", "" + textInputBox1.length());

        if (clearResult) {
            //Log.i(LOG_TAG, "Here?");
            clearDisplay();
            return;
        }

        if (textInputBox1.length() <= 1) {
            textInputBox1 = "";
        } else {
            if (textInputBox1.charAt(textInputBox1.length() - 1) == '>') {
                //Log.i("INSIDE PAR", "I'M A PAR!");
                textInputBox1 = textInputBox1.substring(0, textInputBox1.length() - 28);
                par = true;
            }
            if (textInputBox1.length() > 0 && !par) {
                textInputBox1 = textInputBox1.substring(0, textInputBox1.length() - 1);
            }
            //Log.i("TEXT SIZE!", "" + textInputBox1.length());
        }
    }

    /**
     * Helper function to instantiate the array of colors for the parenthesis.
     */
    public void instantiateColors() {

        String[] keys = Utils.getParenthesisKeys(getActivity());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String[] referenceColors = new String[8];

        for(int i = 0; i < keys.length; i++) {
            String s = preferences.getString(keys[i], "#FF000000");
            referenceColors[i] = s.substring(0, 1) + s.substring(3, s.length());
        }

        colors = new String[112];
        int counter = 0;
        for (int i = 0; i < colors.length; i++) {
            colors[i] = referenceColors[counter];
            counter++;
            if (counter == 8) {
                counter = 0;
            }
        }
    }

    /**
     *
     * @param result
     */
    public void getVarFromDialog(String result) {
        if (result != null) {
            if (textInputBox1.equals(getString(R.string._0))) {
                textInputBox1 = "";
            }
            textInputBox1 += result;
            inputBoxKey.setText(Html.fromHtml(textInputBox1));
        }
    }

    /**
     * Helper method to start a dialog to store or release variables.
     *
     * @param type the type of dialog
     */
    public void startDialog(int type) {
        varDialog = new VariablesDialog();
        Bundle dialogStuff = new Bundle();
        dialogStuff.putString("result", resultBoxKey.getText().toString());
        dialogStuff.putInt("type", type);
        varDialog.setArguments(dialogStuff);
        varDialog.show(getFragmentManager(), "dialog");
    }

    @Override
    public int getType() {
        return FragmentWithKeypad.CALCULATOR_FRAGMENT;
    }

    /**
     * @return The Functions Keypad of this Fragment
     */
    public KeypadFunctionsFragment getKeypadFunctionsFragment() {
        return mKeypadFunctionsFragment;
    }

    /**
     * @return The Keypad of this Fragment
     */
    public KeypadFragment getKeypadFragment() {
        return mKeypadFragment;
    }

    /**
     * @return the active rootView of this Fragment
     */
    public View getRootView() {
        return rootView;
    }

    /**
     * @return the LinearLayout that holds the display
     */
    public LinearLayout getDisplay() {
        return resultLayoutKey;
    }

    /**
     * @return gets the inputBox for this fragment
     */
    public static TextView getInputBoxKey() {
        return inputBoxKey;
    }

    public String getTextInputBox() {
        return textInputBox1;
    }

    /**
     * @return gets the result box for this fragment
     */
    public static TextView getResultBoxKey() {
        return resultBoxKey;
    }

    /**
     * @return Returns the current theme used by this fragment
     */
    public Theme getCurrentTheme() {
        return currentTheme;
    }

    /**
     *
     * Gets the view that is currently blinking while waiting for input
     *
     * @return
     *      The TextView that represents the blinking view
     */
    public static TextView getBlinkingText() {
        return blinkingText;
    }
}