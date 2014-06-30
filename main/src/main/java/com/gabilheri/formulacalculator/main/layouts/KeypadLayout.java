package com.gabilheri.formulacalculator.main.layouts;

import android.view.View;
import android.widget.Button;

import com.gabilheri.formulacalculator.main.R;

import java.util.HashMap;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com"> Marcus Gabilheri </a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 *          since 6/29/14.
 */
public class KeypadLayout {

    private Button btnClear, btnDel, btnParLeft, btnParRight, btnPlus, btnMinus, btnMultiply, btnDivide, btnPercent, btnPower;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnStr, btnRel, btnEqual, btnFactorial;
    private Button btnSqrt, btnLog,  btnLn, btnSin, btnCos, btnTan, btnE, btnPi, btnInf, btnArcSin, btnArcCos, btnArcTan;
    private HashMap<Integer, Button> mButtons;
    private View rootView;

    public KeypadLayout(View rootView) {
        this.rootView = rootView;
        mButtons = new HashMap<>();

        btnClear = (Button) rootView.findViewById(R.id.keypadClear);
        mButtons.put(R.id.keypadClear, btnClear);
        btnDel = (Button) rootView.findViewById(R.id.keypadDel);
        mButtons.put(R.id.keypadDel, btnDel);
        btnParLeft = (Button) rootView.findViewById(R.id.parLeft);
        mButtons.put(R.id.parLeft, btnParLeft);
        btnParRight = (Button) rootView.findViewById(R.id.parRight);
        mButtons.put(R.id.parRight, btnParRight);
        btnPlus = (Button) rootView.findViewById(R.id.plus);
        mButtons.put(R.id.plus, btnPlus);
        btnMinus = (Button) rootView.findViewById(R.id.minus);
        mButtons.put(R.id.minus, btnMinus);
        btnMultiply = (Button) rootView.findViewById(R.id.multiply);
        mButtons.put(R.id.multiply, btnMultiply);
        btnDivide = (Button) rootView.findViewById(R.id.divide);
        mButtons.put(R.id.divide, btnDivide);
        btnPercent = (Button) rootView.findViewById(R.id.btnPercent);
        mButtons.put(R.id.btnPercent, btnPercent);
        btnPower = (Button) rootView.findViewById(R.id.keypadPwr);
        mButtons.put(R.id.keypadPwr, btnPower);
        btn1 = (Button) rootView.findViewById(R.id.keypad1);
        mButtons.put(R.id.keypad1, btn1);
        btn2 = (Button) rootView.findViewById(R.id.keypad2);
        mButtons.put(R.id.keypad2, btn2);
        btn3 = (Button) rootView.findViewById(R.id.keypad3);
        mButtons.put(R.id.keypad3, btn3);
        btn4 = (Button) rootView.findViewById(R.id.keypad4);
        mButtons.put(R.id.keypad4, btn4);
        btn5 = (Button) rootView.findViewById(R.id.keypad5);
        mButtons.put(R.id.keypad5, btn5);
        btn6 = (Button) rootView.findViewById(R.id.keypad6);
        mButtons.put(R.id.keypad6, btn6);
        btn7 = (Button) rootView.findViewById(R.id.keypad7);
        mButtons.put(R.id.keypad7, btn7);
        btn8 = (Button) rootView.findViewById(R.id.keypad8);
        mButtons.put(R.id.keypad8, btn8);
        btn9 = (Button) rootView.findViewById(R.id.keypad9);
        mButtons.put(R.id.keypad9, btn9);
        btn0 = (Button) rootView.findViewById(R.id.keypad0);
        mButtons.put(R.id.keypad0, btn0);
        btnDot = (Button) rootView.findViewById(R.id.keypadDot);
        mButtons.put(R.id.keypadDot, btnDot);
        btnStr = (Button) rootView.findViewById(R.id.keypadStore);
        mButtons.put(R.id.keypadStore, btnStr);
        btnRel = (Button) rootView.findViewById(R.id.keypadRelease);
        mButtons.put(R.id.keypadRelease, btnRel);
        btnEqual = (Button) rootView.findViewById(R.id.equal);
        mButtons.put(R.id.equal, btnEqual);
        btnFactorial = (Button) rootView.findViewById(R.id.btnFact);
        mButtons.put(R.id.btnFact, btnFactorial);
        btnSqrt = (Button) rootView.findViewById(R.id.keypadSqrt);
        mButtons.put(R.id.keypadSqrt, btnSqrt);
        btnLog = (Button) rootView.findViewById(R.id.btnLog);
        mButtons.put(R.id.btnLog, btnLog);
        btnLn = (Button) rootView.findViewById(R.id.btnLn);
        mButtons.put(R.id.btnLn, btnLn);
        btnSin = (Button) rootView.findViewById(R.id.btnSin);
        mButtons.put(R.id.btnSin, btnSin);
        btnCos = (Button) rootView.findViewById(R.id.btnCos);
        mButtons.put(R.id.btnCos, btnCos);
        btnTan = (Button) rootView.findViewById(R.id.btnTan);
        mButtons.put(R.id.btnTan, btnTan);
        btnE = (Button) rootView.findViewById(R.id.varE);
        mButtons.put(R.id.varE, btnE);
        btnPi = (Button) rootView.findViewById(R.id.varPi);
        mButtons.put(R.id.varPi, btnPi);
        btnInf = (Button) rootView.findViewById(R.id.keypadInfinity);
        mButtons.put(R.id.keypadInfinity, btnInf);
    }

    public HashMap<Integer, Button> getmButtons() {
        return mButtons;
    }

    public void setmButtons(HashMap<Integer, Button> mButtons) {
        this.mButtons = mButtons;
    }
}
