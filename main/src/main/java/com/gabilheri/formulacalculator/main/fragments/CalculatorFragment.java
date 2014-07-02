package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.adapters.FormulasListAdapter;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.ResultLog;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.dialogs.ColorPickDialog;
import com.gabilheri.formulacalculator.main.dialogs.VariablesDialog;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.logic.EvaluateExpression;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


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
    private static TextView inputBox1key, resultBoxKey;
    private String textInputBox1;
    private int parCounter = 0;
    private String[] colors;
    private VariablesDialog varDialog;
    private boolean clearResult = false;
    private DatabaseHelper dbHelper;
    private View rootView;
    private DefaultButton mDefault;
    private DefaultButton btnClear, btnDel, btnParLeft, btnParRight, btnPlus, btnMinus, btnMultiply, btnDivide, btnPercent, btnPower;
    private DefaultButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnStr, btnRel, btnEqual, btnFactorial;
    private DefaultButton btnSqrt, btnLog,  btnLn, btnSin, btnCos, btnTan, btnE, btnPi, btnInf, btnArcSin, btnArcCos, btnArcTan;
    private HashMap<Integer, DefaultButton> mButtons;
    private ArrayList<DefaultButton> mButtonsArray;



    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

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

        Theme mTheme = new Theme();

        rootView = view;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        //mDefault = (DefaultButton) view.findViewById(R.id.insertedButton);
        initButtons(view);
        ArrayList<DefaultButton> mButtonsArray = getmButtonsArray();
        try {
            for (DefaultButton mButton : mButtonsArray) {
                mButton.setBackgroundColor(mButton.getCustomBackgroundColor());
                Log.i(LOG_TAG, mButton.getText().toString());
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }


        resultLayoutKey = (LinearLayout) view.findViewById(R.id.resultLayoutKey);
        inputBox1key = (TextView) view.findViewById(R.id.inputBox1);
        resultBoxKey = (TextView) view.findViewById(R.id.resultBox1);

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
                if(position == 2) {
                    resultLayoutKey.setVisibility(View.GONE);
                } else {
                    resultLayoutKey.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Bundle extraBundle = null;

        try {
            extraBundle = getArguments();
        } catch (Exception ex) {}

        if(extraBundle != null) {
            textInputBox1 = String.valueOf(extraBundle.getDouble("logResult"));
            inputBox1key.setText(textInputBox1);
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
     /**
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            mFragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(PlaceholderFragment.ARG_SECTION_NUMBER, position);
            mFragment.setArguments(args);

            return mFragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return null;
            }
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int[] layouts = {R.layout.keypad_functions, R.layout.keypad_layout, R.layout.fragment_formulas};
            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView = inflater.inflate(layouts[position], container, false);
            return rootView;
        }
    }

    /**
     * Handles the keypad press.
     * @param view
     */
    @Override
    public void handleKeypad(View view) {

        int id = view.getId();
        parCounter = 0;

        if(clearResult && id != R.id.keypadStore) {
            clearDisplay();
            clearResult = false;
        }

        int inputSize = textInputBox1.replaceAll("\\<.*?>", "").length();

        if(inputSize> 15) {
            inputBox1key.setTextSize(25);
            Log.i("INPUT SIZE: ", "" + inputSize);
        }

        if(inputSize > 25) {
            inputBox1key.setTextSize(20);
            Log.i("INPUT SIZE: ", "" + inputSize);
        }

        if(inputSize > 35) {
            inputBox1key.setTextSize(15);
            Log.i("INPUT SIZE: ", "" + inputSize);
        }

        if(textInputBox1.equals(getString(R.string._0))) {
            textInputBox1 = "";
        }

        for(int i = 0; i < textInputBox1.length(); i++) {
            if(textInputBox1.charAt(i) == '(') {
                parCounter++;
            }

            if(textInputBox1.charAt(i) == ')') {
                parCounter--;
            }
        }

        //Log.i("PAR COUNTER", "" + parCounter);
        switch (id) {
            case R.id.equal:
                evaluateExpression();
                break;
            case R.id.keypadClear:
                clearDisplay();
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
                textInputBox1 += getString(R.string.sqrt) + "<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                break;
            case R.id.btnCos:
                textInputBox1 += "cos" + "<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                break;
            case R.id.btnSin:
                textInputBox1 += "sin" + "<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                break;
            case R.id.btnTan:
                textInputBox1 += "tan" + "<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                break;
            case R.id.btnFact:
                textInputBox1 += getString(R.string.factorial) + "<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                break;
            case R.id.keypadPwr:
                textInputBox1 += getString(R.string.exp_symbol);
                break;
            case R.id.parLeft:
                //Log.i("PARSIZE", "" + ("<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>").length());
                textInputBox1 += "<font color="+ colors[parCounter] + ">" + getString(R.string.par_left) + "</font>";
                break;
            case R.id.parRight:
                textInputBox1 += "<font color="+ colors[parCounter - 1] + ">" + getString(R.string.par_right) + "</font>";
                break;
            case R.id.btnPercent:
                textInputBox1 += "%";
                break;
            case R.id.btnLog:
                textInputBox1 += "log";
                break;
            case R.id.btnLn:
                textInputBox1 += "ln";
                break;
            case R.id.insertedButton:
                Log.i("I'M A BUTTON!", "That uses include!");
                showPickerDialog(view.getId(), ((DefaultButton) view).getCustomBackgroundColor());
                break;
        }

        inputBox1key.setText(Html.fromHtml(textInputBox1));

        //inputBox1fun.setText(textInputBox1);

        Log.d("FRAGMENT MAIN: ", "Text Input: " + textInputBox1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ColorPickDialog.COLORPICK_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    DefaultButton mButton = (DefaultButton) rootView.findViewById(mBundle.getInt("view"));
                    mButton.setCustomBackgroundColor(mBundle.getInt(ColorPickDialog.SELECTED_COLOR));
                }
                break;
        }
    }

    /**
     * Handles the evaluate button.
     */
    public void evaluateExpression() {
        EvaluateExpression expression = new EvaluateExpression(textInputBox1, this);

        String result = expression.evaluate();

        int resultSize = result.length();

        if(resultSize> 15) {
            resultBoxKey.setTextSize(25);
            Log.i("INPUT SIZE: ", "" + resultSize);
        }

        if(resultSize > 25) {
            resultBoxKey.setTextSize(20);
            Log.i("INPUT SIZE: ", "" + resultSize);
        }

        if(resultSize > 35) {
            resultBoxKey.setTextSize(15);
            Log.i("INPUT SIZE: ", "" + resultSize);
        }

        if(textInputBox1.equals(getString(R.string._0))) {
            textInputBox1 = "";
        }

        if(!result.equals(getResources().getString(R.string.input_error))) {
            ResultLog resultLog = new ResultLog(textInputBox1, result);
            dbHelper.createResultLog(resultLog);
        }

        resultBoxKey.setText(result);

        if(!result.equals(getResources().getString(R.string.input_error))) {
            clearResult = true;
        }

    }

    /**
     * Clears the Display.
     */
    public void clearDisplay() {
        textInputBox1 = getString(R.string._0);
        resultBoxKey.setText("");
        inputBox1key.setTextSize(35);
        clearResult = false;
    }

    /**
     * Handles the DEL key
     */
    public void deleteFromDisplay() {

        boolean par = false;
        Log.i("TEXT SIZE BEFORE!", "" + textInputBox1.length());

        if(clearResult) {
            Log.i(LOG_TAG, "Here?");
            clearDisplay();
            return;
        }

        if(textInputBox1.length() <= 1) {
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
            //inputBox1key.setText(Html.fromHtml(textInputBox1));
        }
    }

    /**
     * Helper function to instantiate the array of colors for the parenthesis.
     */
    public void instantiateColors() {
        String[] referenceColors = {"#2ecc71", "#e74c3c", "#9b59b6", "#f1c40f", "#ecf0f1", "#e67e22"};
        colors = new String[100];
        int counter = 0;
        for(int i = 0; i < colors.length; i++) {
            colors[i] = referenceColors[counter];
            //Log.i("COLORS", "" + referenceColors[counter]);
            counter++;

            if(counter == 6) {
                counter = 0;
            }
        }
    }

    /**
     *
     * @param view
     */
    public void handleVar(View view) {
        String result = varDialog.handleVar(view);

        if(result != null) {
            if(textInputBox1.equals(getString(R.string._0))) {
                textInputBox1 = "";
            }
            textInputBox1 += result;
            inputBox1key.setText(Html.fromHtml(textInputBox1));
        }
    }

    /**
     * Helper method to start a dialog to store or release variables.
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

    public void showPickerDialog(int viewId, int buttonColor) {
        ColorPickDialog pickerDialog = new ColorPickDialog();
        pickerDialog.setTargetFragment(this, ColorPickDialog.COLORPICK_CODE);
        Bundle extras = new Bundle();
        extras.putInt("view", viewId);
        extras.putInt("color", buttonColor);
        pickerDialog.setArguments(extras);
        pickerDialog.show(getFragmentManager(), "pickerDialog");
    }

    @Override
    public int getType() {
        return FragmentWithKeypad.CALCULATOR_FRAGMENT;
    }


    public void initButtons(View rootView) {
        mButtons = new HashMap<>();

        btnClear = (DefaultButton) rootView.findViewById(R.id.keypadClear);
        mButtons.put(R.id.keypadClear, btnClear);
        btnDel = (DefaultButton) rootView.findViewById(R.id.keypadDel);
        mButtons.put(R.id.keypadDel, btnDel);
        btnParLeft = (DefaultButton) rootView.findViewById(R.id.parLeft);
        mButtons.put(R.id.parLeft, btnParLeft);
        btnParRight = (DefaultButton) rootView.findViewById(R.id.parRight);
        mButtons.put(R.id.parRight, btnParRight);
        btnPlus = (DefaultButton) rootView.findViewById(R.id.plus);
        mButtons.put(R.id.plus, btnPlus);
        btnMinus = (DefaultButton) rootView.findViewById(R.id.minus);
        mButtons.put(R.id.minus, btnMinus);
        btnMultiply = (DefaultButton) rootView.findViewById(R.id.multiply);
        mButtons.put(R.id.multiply, btnMultiply);
        btnDivide = (DefaultButton) rootView.findViewById(R.id.divide);
        mButtons.put(R.id.divide, btnDivide);
        btnPercent = (DefaultButton) rootView.findViewById(R.id.btnPercent);
        mButtons.put(R.id.btnPercent, btnPercent);
        btnPower = (DefaultButton) rootView.findViewById(R.id.keypadPwr);
        mButtons.put(R.id.keypadPwr, btnPower);
        btn1 = (DefaultButton) rootView.findViewById(R.id.keypad1);
        mButtons.put(R.id.keypad1, btn1);
        btn2 = (DefaultButton) rootView.findViewById(R.id.keypad2);
        mButtons.put(R.id.keypad2, btn2);
        btn3 = (DefaultButton) rootView.findViewById(R.id.keypad3);
        mButtons.put(R.id.keypad3, btn3);
        btn4 = (DefaultButton) rootView.findViewById(R.id.keypad4);
        mButtons.put(R.id.keypad4, btn4);
        btn5 = (DefaultButton) rootView.findViewById(R.id.keypad5);
        mButtons.put(R.id.keypad5, btn5);
        btn6 = (DefaultButton) rootView.findViewById(R.id.keypad6);
        mButtons.put(R.id.keypad6, btn6);
        btn7 = (DefaultButton) rootView.findViewById(R.id.keypad7);
        mButtons.put(R.id.keypad7, btn7);
        btn8 = (DefaultButton) rootView.findViewById(R.id.keypad8);
        mButtons.put(R.id.keypad8, btn8);
        btn9 = (DefaultButton) rootView.findViewById(R.id.keypad9);
        mButtons.put(R.id.keypad9, btn9);
        btn0 = (DefaultButton) rootView.findViewById(R.id.keypad0);
        mButtons.put(R.id.keypad0, btn0);
        btnDot = (DefaultButton) rootView.findViewById(R.id.keypadDot);
        mButtons.put(R.id.keypadDot, btnDot);
        btnStr = (DefaultButton) rootView.findViewById(R.id.keypadStore);
        mButtons.put(R.id.keypadStore, btnStr);
        btnRel = (DefaultButton) rootView.findViewById(R.id.keypadRelease);
        mButtons.put(R.id.keypadRelease, btnRel);
        btnEqual = (DefaultButton) rootView.findViewById(R.id.equal);
        mButtons.put(R.id.equal, btnEqual);
        btnFactorial = (DefaultButton) rootView.findViewById(R.id.btnFact);
        mButtons.put(R.id.btnFact, btnFactorial);
        btnSqrt = (DefaultButton) rootView.findViewById(R.id.keypadSqrt);
        mButtons.put(R.id.keypadSqrt, btnSqrt);
        btnLog = (DefaultButton) rootView.findViewById(R.id.btnLog);
        mButtons.put(R.id.btnLog, btnLog);
        btnLn = (DefaultButton) rootView.findViewById(R.id.btnLn);
        mButtons.put(R.id.btnLn, btnLn);
        btnSin = (DefaultButton) rootView.findViewById(R.id.btnSin);
        mButtons.put(R.id.btnSin, btnSin);
        btnCos = (DefaultButton) rootView.findViewById(R.id.btnCos);
        mButtons.put(R.id.btnCos, btnCos);
        btnTan = (DefaultButton) rootView.findViewById(R.id.btnTan);
        mButtons.put(R.id.btnTan, btnTan);
        btnE = (DefaultButton) rootView.findViewById(R.id.varE);
        mButtons.put(R.id.varE, btnE);
        btnPi = (DefaultButton) rootView.findViewById(R.id.varPi);
        mButtons.put(R.id.varPi, btnPi);
        btnInf = (DefaultButton) rootView.findViewById(R.id.keypadInfinity);
        mButtons.put(R.id.keypadInfinity, btnInf);
    }

    public HashMap<Integer, DefaultButton> getmButtons() {
        return mButtons;
    }

    public void setmButtons(HashMap<Integer, DefaultButton> mButtons) {
        this.mButtons = mButtons;
    }

    public ArrayList<DefaultButton> getmButtonsArray() {

        mButtonsArray = new ArrayList<>();

        for(Map.Entry<Integer, DefaultButton> entry : mButtons.entrySet()) {
            mButtonsArray.add(entry.getValue());
        }

        return mButtonsArray;
    }
}