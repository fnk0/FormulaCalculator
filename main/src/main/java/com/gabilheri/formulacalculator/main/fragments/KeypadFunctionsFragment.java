package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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
    private HashMap<Integer, DefaultButton> mKeypadFunctionButtons;
    private ArrayList<DefaultButton> mButtonsArray;
    private ArrayList<DefaultButton> mSecondaryButtonsArray;
    private Theme currentTheme;


    public KeypadFunctionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.keypad_functions, container, false);

        SharedPreferences mPreferences = getActivity().getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        currentTheme = dbHelper.getThemeByName(mPreferences.getString(MainActivity.CURRENT_THEME, MainActivity.CURRENT_THEME));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButtonsArray = new ArrayList<>();
        mSecondaryButtonsArray = new ArrayList<>();
        mKeypadFunctionButtons = new HashMap<>();

        btnDel = (DefaultButton) view.findViewById(R.id.keypadDel);
        mKeypadFunctionButtons.put(R.id.keypadDel, btnDel);
        mSecondaryButtonsArray.add(btnDel);

        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) getActivity()).clearDisplay();
                return false;
            }
        });

        btnFactorial = (DefaultButton) view.findViewById(R.id.btnFact);
        mKeypadFunctionButtons.put(R.id.btnFact, btnFactorial);
        mSecondaryButtonsArray.add(btnFactorial);

        btnSqrt = (DefaultButton) view.findViewById(R.id.keypadSqrt);
        mKeypadFunctionButtons.put(R.id.keypadSqrt, btnSqrt);
        mButtonsArray.add(btnSqrt);

        btnLog = (DefaultButton) view.findViewById(R.id.btnLog);
        mKeypadFunctionButtons.put(R.id.btnLog, btnLog);
        mButtonsArray.add(btnLog);

        btnLn = (DefaultButton) view.findViewById(R.id.btnLn);
        mKeypadFunctionButtons.put(R.id.btnLn, btnLn);
        mButtonsArray.add(btnLn);

        btnSin = (DefaultButton) view.findViewById(R.id.btnSin);
        mKeypadFunctionButtons.put(R.id.btnSin, btnSin);
        mButtonsArray.add(btnSin);

        btnCos = (DefaultButton) view.findViewById(R.id.btnCos);
        mKeypadFunctionButtons.put(R.id.btnCos, btnCos);
        mButtonsArray.add(btnCos);

        btnTan = (DefaultButton) view.findViewById(R.id.btnTan);
        mKeypadFunctionButtons.put(R.id.btnTan, btnTan);
        mButtonsArray.add(btnTan);

        btnE = (DefaultButton) view.findViewById(R.id.varE);
        mKeypadFunctionButtons.put(R.id.varE, btnE);
        mSecondaryButtonsArray.add(btnE);

        btnPi = (DefaultButton) view.findViewById(R.id.varPi);
        mKeypadFunctionButtons.put(R.id.varPi, btnPi);
        mSecondaryButtonsArray.add(btnPi);

        btnInf = (DefaultButton) view.findViewById(R.id.keypadInfinity);
        mKeypadFunctionButtons.put(R.id.keypadInfinity, btnInf);
        mSecondaryButtonsArray.add(btnInf);

        degreeRad = (DefaultButton) view.findViewById(R.id.degreeRad);
        mKeypadFunctionButtons.put(R.id.degreeRad, degreeRad);
        mButtonsArray.add(degreeRad);

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
    }

    public ArrayList<DefaultButton> getPrimaryButtonsArray() {
        return mButtonsArray;
    }

    public ArrayList<DefaultButton> getSecondaryButtonsArray() {
        return mSecondaryButtonsArray;
    }
}
