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

    private DefaultButton btnSqrt, btnLog,  btnLn, btnSin, btnCos, btnTan, btnE, btnPi, btnTax, btnArcSin, btnArcCos, btnArcTan, btnFactorial;
    private DefaultButton degreeRad, btnDel, btnStr, btnRel, btnPercent, btnPower, cubeRoot;
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
        mButtonsArray.add(btnFactorial);

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

        btnTax = (DefaultButton) rootView.findViewById(R.id.keypadTax);
        mKeypadFunctionButtons.put(R.id.keypadTax, btnTax);
        mSecondaryButtonsArray.add(btnTax);

        degreeRad = (DefaultButton) rootView.findViewById(R.id.degreeRad);
        mKeypadFunctionButtons.put(R.id.degreeRad, degreeRad);
        mButtonsArray.add(degreeRad);

        btnPercent = (DefaultButton) rootView.findViewById(R.id.btnPercent);
        mKeypadFunctionButtons.put(R.id.btnPercent, btnPercent);
        mSecondaryButtonsArray.add(btnPercent);

        btnPower = (DefaultButton) rootView.findViewById(R.id.keypadPwr);
        mKeypadFunctionButtons.put(R.id.keypadPwr, btnPower);
        mButtonsArray.add(btnPower);

        btnStr = (DefaultButton) rootView.findViewById(R.id.keypadStore);
        mKeypadFunctionButtons.put(R.id.keypadStore, btnStr);
        mButtonsArray.add(btnStr);

        btnRel = (DefaultButton) rootView.findViewById(R.id.keypadRelease);
        mKeypadFunctionButtons.put(R.id.keypadRelease, btnRel);
        mButtonsArray.add(btnRel);

        cubeRoot = (DefaultButton) rootView.findViewById(R.id.cubeRoot);
        mKeypadFunctionButtons.put(R.id.cubeRoot, cubeRoot);
        mButtonsArray.add(cubeRoot);

        btnArcSin = (DefaultButton) rootView.findViewById(R.id.btnArcSin);
        mKeypadFunctionButtons.put(R.id.cubeRoot, cubeRoot);
        mButtonsArray.add(btnArcSin);

        btnArcCos = (DefaultButton) rootView.findViewById(R.id.btnArcCos);
        mKeypadFunctionButtons.put(R.id.cubeRoot, cubeRoot);
        mButtonsArray.add(btnArcCos);

        btnArcTan = (DefaultButton) rootView.findViewById(R.id.btnArcTan);
        mKeypadFunctionButtons.put(R.id.cubeRoot, cubeRoot);
        mButtonsArray.add(btnArcTan);

        /*
        btnFrac = (DefaultButton) rootView.findViewById(R.id.fraction);
        mKeypadButtons.put(R.id.fraction, btnFrac);
        mButtonsArray.add(btnFrac);
        */

        for(DefaultButton mButton : mButtonsArray) {
            mButton.setCustomTextColor(currentTheme.getPrimaryButtonTextColor());
            mButton.setCustomBackgroundColor(currentTheme.getPrimaryColor());
            mButton.setCustomHighlightColor(currentTheme.getPrimaryHighlightColor());
        }

        for(DefaultButton mButton: mSecondaryButtonsArray) {
            mButton.setCustomTextColor(currentTheme.getSecondaryButtonTextColor());
            mButton.setCustomBackgroundColor(currentTheme.getSecondaryColor());
            mButton.setCustomHighlightColor(currentTheme.getSecondaryHighlightColor());
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
