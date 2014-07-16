package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/5/14.
 */
public class KeypadFunctionsFragment extends Fragment {

    private DefaultButton btnSqrt, btnLog,  btnLn, btnSin, btnCos, btnTan, btnE, btnPi, btnInf, btnArcSin, btnArcCos, btnArcTan, btnFactorial;
    private DefaultButton degreeRad, btnDel;
    private DefaultButton empty1, empty2, empty3, empty4, empty5, empty6, empty7, empty8, empty9, empty10, empty11, empty12, empty13;
    private HashMap<Integer, DefaultButton> mKeypadFunctionButtons;
    private ArrayList<DefaultButton> mButtonsArray;
    private ArrayList<DefaultButton> mSecondaryButtonsArray;
    private Theme currentTheme;


    public KeypadFunctionsFragment() {
        mButtonsArray = new ArrayList<>();
        mSecondaryButtonsArray = new ArrayList<>();
        mKeypadFunctionButtons = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.keypad_functions, container, false);
        SharedPreferences mPreferences = getActivity().getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        currentTheme = dbHelper.getThemeByName(mPreferences.getString(MainActivity.CURRENT_THEME, MainActivity.CURRENT_THEME));


        btnDel = (DefaultButton) rootView.findViewById(R.id.keypadDel);
        mKeypadFunctionButtons.put(R.id.keypadDel, btnDel);
        mSecondaryButtonsArray.add(btnDel);

        Typeface mFont = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "icons.ttf");
        btnDel.setTypeface(mFont);
        btnDel.setTextSize(35);

        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) getActivity()).clearDisplay();
                return false;
            }
        });

        btnFactorial = (DefaultButton) rootView.findViewById(R.id.btnFact);
        mKeypadFunctionButtons.put(R.id.btnFact, btnFactorial);
        mSecondaryButtonsArray.add(btnFactorial);

        btnSqrt = (DefaultButton) rootView.findViewById(R.id.keypadSqrt);
        mKeypadFunctionButtons.put(R.id.keypadSqrt, btnSqrt);
        mButtonsArray.add(btnSqrt);

        btnLog = (DefaultButton) rootView.findViewById(R.id.btnLog);
        mKeypadFunctionButtons.put(R.id.btnLog, btnLog);
        mButtonsArray.add(btnLog);

        btnLn = (DefaultButton) rootView.findViewById(R.id.btnLn);
        mKeypadFunctionButtons.put(R.id.btnLn, btnLn);
        mButtonsArray.add(btnLn);

        btnSin = (DefaultButton) rootView.findViewById(R.id.btnSin);
        mKeypadFunctionButtons.put(R.id.btnSin, btnSin);
        mButtonsArray.add(btnSin);

        btnCos = (DefaultButton) rootView.findViewById(R.id.btnCos);
        mKeypadFunctionButtons.put(R.id.btnCos, btnCos);
        mButtonsArray.add(btnCos);

        btnTan = (DefaultButton) rootView.findViewById(R.id.btnTan);
        mKeypadFunctionButtons.put(R.id.btnTan, btnTan);
        mButtonsArray.add(btnTan);

        btnE = (DefaultButton) rootView.findViewById(R.id.varE);
        mKeypadFunctionButtons.put(R.id.varE, btnE);
        mSecondaryButtonsArray.add(btnE);

        btnPi = (DefaultButton) rootView.findViewById(R.id.varPi);
        mKeypadFunctionButtons.put(R.id.varPi, btnPi);
        mSecondaryButtonsArray.add(btnPi);

        btnInf = (DefaultButton) rootView.findViewById(R.id.keypadInfinity);
        mKeypadFunctionButtons.put(R.id.keypadInfinity, btnInf);
        mSecondaryButtonsArray.add(btnInf);

        degreeRad = (DefaultButton) rootView.findViewById(R.id.degreeRad);
        mKeypadFunctionButtons.put(R.id.degreeRad, degreeRad);
        mButtonsArray.add(degreeRad);

        empty1 = (DefaultButton) rootView.findViewById(R.id.emptyBtn1);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty1);
        mButtonsArray.add(empty1);

        empty2 = (DefaultButton) rootView.findViewById(R.id.emptyBtn2);
        mKeypadFunctionButtons.put(R.id.emptyBtn2, empty2);
        mButtonsArray.add(empty2);

        empty3 = (DefaultButton) rootView.findViewById(R.id.emptyBtn3);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty3);
        mButtonsArray.add(empty3);

        empty4 = (DefaultButton) rootView.findViewById(R.id.emptyBtn4);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty4);
        mButtonsArray.add(empty4);

        empty5 = (DefaultButton) rootView.findViewById(R.id.emptyBtn5);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty5);
        mButtonsArray.add(empty5);

        empty6 = (DefaultButton) rootView.findViewById(R.id.emptyBtn6);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty6);
        mButtonsArray.add(empty6);

        empty7 = (DefaultButton) rootView.findViewById(R.id.emptyBtn7);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty7);
        mButtonsArray.add(empty7);

        empty8 = (DefaultButton) rootView.findViewById(R.id.emptyBtn8);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty8);
        mButtonsArray.add(empty8);

        empty9 = (DefaultButton) rootView.findViewById(R.id.emptyBtn9);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty9);
        mButtonsArray.add(empty9);

        empty10 = (DefaultButton) rootView.findViewById(R.id.emptyBtn10);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty10);
        mButtonsArray.add(empty10);

        empty11 = (DefaultButton) rootView.findViewById(R.id.emptyBtn11);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty11);
        mButtonsArray.add(empty11);

        empty12 = (DefaultButton) rootView.findViewById(R.id.emptyBtn12);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty12);
        mButtonsArray.add(empty12);

        empty13 = (DefaultButton) rootView.findViewById(R.id.emptyBtn13);
        mKeypadFunctionButtons.put(R.id.emptyBtn1, empty13);
        mButtonsArray.add(empty13);

        for(DefaultButton mButton : mButtonsArray) {
            mButton.setCustomTextColor(currentTheme.getPrimaryButtonTextColor());
            mButton.setCustomBackgroundColor(currentTheme.getPrimaryColor());
            mButton.setCustomHighlightColor(currentTheme.getPrimaryHighlightColor());
        }

        for(DefaultButton mButton: mSecondaryButtonsArray) {
            mButton.setCustomTextColor(currentTheme.getSecondaryButtonTextColor());
            mButton.setCustomBackgroundColor(currentTheme.getSecondaryColor());
            mButton.setHighlightColor(currentTheme.getSecondaryHighlightColor());
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public ArrayList<DefaultButton> getPrimaryButtonsArray() {
        return mButtonsArray;
    }

    public ArrayList<DefaultButton> getSecondaryButtonsArray() {
        return mSecondaryButtonsArray;
    }
}
