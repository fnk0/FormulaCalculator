package com.gabilheri.formulacalculator.main.layouts;

import android.view.View;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com"> Marcus Gabilheri </a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 *          since 6/29/14.
 */
public class KeypadLayout {

    private DefaultButton btnClear, btnDel, btnParLeft, btnParRight, btnPlus, btnMinus, btnMultiply, btnDivide, btnPercent, btnPower;
    private DefaultButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnStr, btnRel, btnEqual, btnFactorial;
    private DefaultButton btnSqrt, btnLog,  btnLn, btnSin, btnCos, btnTan, btnE, btnPi, btnInf, btnArcSin, btnArcCos, btnArcTan;
    private HashMap<Integer, DefaultButton> mButtons;
    private ArrayList<DefaultButton> mButtonsArray;
    private View rootView;
    private String LOG_TAG = "KeypadLayout";

    public KeypadLayout(View rootView) {
        this.rootView = rootView;
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

        //Log.i(LOG_TAG, "" + btnClear.getCustomBackgroundColor());

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
