package com.gabilheri.formulacalculator.main.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
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
import com.gabilheri.formulacalculator.main.dialogs.VariablesDialog;
import com.gabilheri.formulacalculator.main.logic.EvaluateExpression;


public class MainActivityFragment extends Fragment {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
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
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.fragment_calculator, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

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

        Log.d("IM HERE!!", "CREATING TEXT VIEWS!!!");

        textInputBox1 = getString(R.string._0);
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
            return 3;
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            int[] layouts = {R.layout.keypad_functions, R.layout.keypad_layout, R.layout.fragment_formulas};
            int position = getArguments().getInt(ARG_SECTION_NUMBER);


            View rootView = inflater.inflate(layouts[position], container, false);

            if(position == 0) {

            } else if(position == 1) {

            } else if(position == 2) {
                listView = (ExpandableListView) rootView.findViewById(R.id.awesomeList);

                generateListData();

                listAdapter = new FormulasListAdapter(this.getActivity(), formulaTitles, formulas);
                listView.setAdapter(listAdapter);
            }

            return rootView;

        }
    }

    /**
     * Generates the Data for the List.
     * Eventually will be replaced with objects
     */
    public static void generateListData() {

        formulaTitles = new ArrayList<String>();
        formulas = new HashMap<>();

        formulaTitles.add("Math Formulas");
        formulaTitles.add("Physics Formulas");
        formulaTitles.add("Finance Formulas");

        List<String> mathF = new ArrayList<>();
        mathF.add("Area Square");
        mathF.add("Area Triangle");
        mathF.add("Circumference");

        List<String> physicsF = new ArrayList<>();
        physicsF.add("Velocity");
        physicsF.add("Displacement");

        List<String> financeF = new ArrayList<>();
        financeF.add("Interest Ratio");
        financeF.add("Loan Calculator");

        formulas.put(formulaTitles.get(0), mathF);
        formulas.put(formulaTitles.get(1), physicsF);
        formulas.put(formulaTitles.get(2), financeF);
    }

    /**
     * Handles the keypad press.
     * @param view
     */
    public void handleKeypad(View view) {

        int id = view.getId();
        parCounter = 0;

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
        }

        inputBox1key.setText(Html.fromHtml(textInputBox1));
        //inputBox1fun.setText(textInputBox1);

        Log.d("FRAGMENT MAIN: ", "Text Input: " + textInputBox1);
    }

    /**
     * Handles the evaluate button.
     * @param view
     */
    public void evaluateExpression(View view) {
        int id = view.getId();
        if(id == R.id.equal) {
            EvaluateExpression expression = new EvaluateExpression(textInputBox1, this);
            resultBoxKey.setText(expression.evaluate());
        }
    }

    /**
     * Clears the Display.
     */
    public void clearDisplay() {
        textInputBox1 = getString(R.string._0);
        inputBox1key.setText(textInputBox1);
    }

    /**
     * Handles the DEL key
     */
    public void deleteFromDisplay() {

        boolean par = false;
        //Log.i("TEXT SIZE BEFORE!", "" + textInputBox1.length());

        if(textInputBox1.length() == 1) {
            inputBox1key.setText(getString(R.string._0));
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
            inputBox1key.setText(Html.fromHtml(textInputBox1));
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
}
