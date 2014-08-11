package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
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
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.HashMap;
import java.util.List;


public class CalculatorFragment extends Fragment implements FragmentWithKeypad {

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
    private static TextView inputBoxKey, resultBoxKey;
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

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public CalculatorFragment() {
        mKeypadFragment = new KeypadFragment();
        mKeypadFunctionsFragment = new KeypadFunctionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());

        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        ActionBar mActionBar = getActivity().getActionBar();
        mActionBar.setIcon(R.drawable.ic_launcher);

        SharedPreferences mPreferences = getActivity().getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        currentTheme = dbHelper.getThemeByName(mPreferences.getString(MainActivity.CURRENT_THEME, MainActivity.CURRENT_THEME));

        LinearLayout mLayout = (LinearLayout) view.findViewById(R.id.calculatorFrag);
        Utils.setInsets(getActivity(), mLayout);

        angleType = EvaluateExpression.DEGREE;
        rootView = view;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        resultLayoutKey = (LinearLayout) view.findViewById(R.id.resultLayoutKey);
        resultLayoutKey.setBackgroundColor(currentTheme.getDisplayColor());
        inputBoxKey = (TextView) view.findViewById(R.id.inputBox1);
        resultBoxKey = (TextView) view.findViewById(R.id.resultBox1);

        inputBoxKey.setTextColor(currentTheme.getDisplayTextColor());
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
            textInputBox1 = String.valueOf(extraBundle.getDouble("logResult"));
            inputBoxKey.setText(textInputBox1);
        } else {
            textInputBox1 = getString(R.string._0);

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
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

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

    /**
     * Handles the keypad press.
     *
     * @param view The View being pressed.
     */
    @Override
    public void handleKeypad(View view) {

        int id = view.getId();
        parCounter = 0;
        boolean isAnswerInserted = false;

        if (clearResult && Utils.isButtonOperator(id)) {
            Log.i(LOG_TAG, "Here Inside Operators!!");
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
                /*
                case R.id.fraction:
                    if (previousResult != null) {
                        FractionBuilder mFractionBuilder = new FractionBuilder();
                        try {
                            double res = Double.parseDouble(previousResult);
                            Log.i(LOG_TAG, "Res: " + res);
                            Fraction mFraction = mFractionBuilder.createFraction(res);
                            resultBoxKey.setText(mFraction.toString());
                        } catch (IrrationalNumberException ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                */
            }
            isAnswerInserted = true;
            clearResult = false;
        } else if (clearResult && id != R.id.keypadStore) {
            clearDisplay();
            clearResult = false;
        }

        int inputSize = Utils.stripHTML(textInputBox1).length();

        if (inputSize > 15) {
            inputBoxKey.setTextSize(25);
            Log.i("INPUT SIZE: ", "" + inputSize);
        }

        if (inputSize > 25) {
            inputBoxKey.setTextSize(20);
            Log.i("INPUT SIZE: ", "" + inputSize);
        }

        if (inputSize > 35) {
            inputBoxKey.setTextSize(15);
            Log.i("INPUT SIZE: ", "" + inputSize);
        }

        if (textInputBox1.equals(getString(R.string._0))) {
            textInputBox1 = "";
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
                    evaluateExpression();
                    break;
                case R.id.keypadDel:
                    deleteFromDisplay();
                    break;
                case R.id.keypad0:
                    textInputBox1 += getString(R.string._0);
                    break;
                case R.id.keypad1:
                    textInputBox1 += getString(R.string._1);
                    break;
                case R.id.keypad2:
                    textInputBox1 += getString(R.string._2);
                    break;
                case R.id.keypad3:
                    textInputBox1 += getString(R.string._3);
                    break;
                case R.id.keypad4:
                    textInputBox1 += getString(R.string._4);
                    break;
                case R.id.keypad5:
                    textInputBox1 += getString(R.string._5);
                    break;
                case R.id.keypad6:
                    textInputBox1 += getString(R.string._6);
                    break;
                case R.id.keypad7:
                    textInputBox1 += getString(R.string._7);
                    break;
                case R.id.keypad8:
                    textInputBox1 += getString(R.string._8);
                    break;
                case R.id.keypad9:
                    textInputBox1 += getString(R.string._9);
                    break;
                case R.id.plus:
                    textInputBox1 += getString(R.string.plus);
                    break;
                case R.id.minus:
                    textInputBox1 += getString(R.string.minus_simbol);
                    break;
                case R.id.multiply:
                    textInputBox1 += getString(R.string.multiply);
                    break;
                case R.id.divide:
                    textInputBox1 += getString(R.string.divide);
                    break;
                case R.id.varE:
                    textInputBox1 += getString(R.string.var_e);
                    break;
                case R.id.varPi:
                    textInputBox1 += getString(R.string.pi);
                    break;
                case R.id.keypadDot:
                    textInputBox1 += getString(R.string.dot);
                    break;
                case R.id.keypadStore:
                    startDialog(VariablesDialog.STORE_DIALOG);
                    break;
                case R.id.keypadRelease:
                    startDialog(VariablesDialog.RELEASE_DIALOG);
                    break;
                case R.id.keypadSqrt:
                    textInputBox1 += getString(R.string.sqrt) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    break;
                case R.id.btnCos:
                    textInputBox1 += "cos" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    break;
                case R.id.btnSin:
                    textInputBox1 += "sin" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    break;
                case R.id.btnTan:
                    textInputBox1 += "tan" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    break;
                case R.id.btnFact:
                    textInputBox1 += getString(R.string.factorial) + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    break;
                case R.id.keypadPwr:
                    textInputBox1 += getString(R.string.exp_symbol);
                    break;
                case R.id.parLeft:
                    //Log.i("PARSIZE", "" + ("<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>").length());
                    textInputBox1 += "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    break;
                case R.id.parRight:
                    if (parCounter > 0) {
                        textInputBox1 += "<font color=" + colors[parCounter - 1] + ">" + getString(R.string.par_right) + "</font>";
                    } else {
                        return;
                    }
                    break;
                case R.id.btnPercent:
                    textInputBox1 += "%";
                    break;
                case R.id.btnLog:
                    textInputBox1 += "log" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                    break;
                case R.id.btnLn:
                    textInputBox1 += "ln(" + "<font color=" + colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
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

        if (result == null) {
            Log.i(LOG_TAG, "Empty Input");
            inputBoxKey.setText(getString(R.string._0));
            resultBoxKey.setText("");
            return;
        }

        int resultSize = result.length();

        if (resultSize > 15) {
            resultBoxKey.setTextSize(25);
            Log.i("INPUT SIZE: ", "" + resultSize);
        }

        if (resultSize > 25) {
            resultBoxKey.setTextSize(20);
            Log.i("INPUT SIZE: ", "" + resultSize);
        }

        if (resultSize > 35) {
            resultBoxKey.setTextSize(15);
            Log.i("INPUT SIZE: ", "" + resultSize);
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
        textInputBox1 = getString(R.string._0);
        resultBoxKey.setText("");
        inputBoxKey.setTextSize(35);
        clearResult = false;
    }

    /**
     * Handles the DEL key
     */
    public void deleteFromDisplay() {

        boolean par = false;
        Log.i("TEXT SIZE BEFORE!", "" + textInputBox1.length());

        if (clearResult) {
            Log.i(LOG_TAG, "Here?");
            clearDisplay();
            return;
        }

        if (textInputBox1.length() <= 1) {
            textInputBox1 = getString(R.string._0);
        } else {
            if (textInputBox1.charAt(textInputBox1.length() - 1) == '>') {
                Log.i("INSIDE PAR", "I'M A PAR!");
                textInputBox1 = textInputBox1.substring(0, textInputBox1.length() - 28);
                par = true;
            }
            if (textInputBox1.length() > 0 && !par) {
                textInputBox1 = textInputBox1.substring(0, textInputBox1.length() - 1);
            }
            //Log.i("TEXT SIZE!", "" + textInputBox1.length());
            //inputBoxKey.setText(Html.fromHtml(textInputBox1));
        }
    }

    /**
     * Helper function to instantiate the array of colors for the parenthesis.
     */
    public void instantiateColors() {
        String[] referenceColors = {"#2ecc71", "#e74c3c", "#9b59b6", "#f1c40f", "#ecf0f1", "#e67e22"};
        colors = new String[100];
        int counter = 0;
        for (int i = 0; i < colors.length; i++) {
            colors[i] = referenceColors[counter];
            //Log.i("COLORS", "" + referenceColors[counter]);
            counter++;
            if (counter == 6) {
                counter = 0;
            }
        }
    }

    /**
     * @param view
     */
    public void handleVar(View view) {
        String result = varDialog.handleVar(view);

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
}