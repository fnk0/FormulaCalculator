package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/5/14.
 */
public class KeypadFunctionsFragment extends Fragment {

    private DefaultButton btnSqrt, btnLog,  btnLn, btnSin, btnCos, btnTan, btnE, btnPi, btnInf, btnArcSin, btnArcCos, btnArcTan, btnFactorial;
    private DefaultButton degreeRad;
    private HashMap<Integer, DefaultButton> mKeypadFunctionButtons;
    private ArrayList<DefaultButton> mButtonsArray;


    public KeypadFunctionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.keypad_functions, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mKeypadFunctionButtons = new HashMap<>();

        btnFactorial = (DefaultButton) view.findViewById(R.id.btnFact);
        mKeypadFunctionButtons.put(R.id.btnFact, btnFactorial);
        btnSqrt = (DefaultButton) view.findViewById(R.id.keypadSqrt);
        mKeypadFunctionButtons.put(R.id.keypadSqrt, btnSqrt);
        btnLog = (DefaultButton) view.findViewById(R.id.btnLog);
        mKeypadFunctionButtons.put(R.id.btnLog, btnLog);
        btnLn = (DefaultButton) view.findViewById(R.id.btnLn);
        mKeypadFunctionButtons.put(R.id.btnLn, btnLn);
        btnSin = (DefaultButton) view.findViewById(R.id.btnSin);
        mKeypadFunctionButtons.put(R.id.btnSin, btnSin);
        btnCos = (DefaultButton) view.findViewById(R.id.btnCos);
        mKeypadFunctionButtons.put(R.id.btnCos, btnCos);
        btnTan = (DefaultButton) view.findViewById(R.id.btnTan);
        mKeypadFunctionButtons.put(R.id.btnTan, btnTan);
        btnE = (DefaultButton) view.findViewById(R.id.varE);
        mKeypadFunctionButtons.put(R.id.varE, btnE);
        btnPi = (DefaultButton) view.findViewById(R.id.varPi);
        mKeypadFunctionButtons.put(R.id.varPi, btnPi);
        btnInf = (DefaultButton) view.findViewById(R.id.keypadInfinity);
        mKeypadFunctionButtons.put(R.id.keypadInfinity, btnInf);

        degreeRad = (DefaultButton) view.findViewById(R.id.degreeRad);
        mKeypadFunctionButtons.put(R.id.degreeRad, degreeRad);

        mButtonsArray = new ArrayList<>();

        for(Map.Entry<Integer, DefaultButton> entry : mKeypadFunctionButtons.entrySet()) {
            mButtonsArray.add(entry.getValue());
        }
    }

    public ArrayList<DefaultButton> getmButtonsArray() {
        return mButtonsArray;
    }
}
