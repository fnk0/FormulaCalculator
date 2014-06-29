package com.gabilheri.formulacalculator.main.layouts;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.gabilheri.formulacalculator.main.R;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com"> Marcus Gabilheri </a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 *          since 6/29/14.
 */
public class KeypadLayout implements View.OnTouchListener {

    private Button btnClear, btnDel, btnParLeft, btnParRight, btnPlus, btnMinus, btnMultiply, btnDivide, btnPercent, btnPower;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnStr, btnRel, btnEqual, btnFactorial;
    private Button btnSqrt, btnLog,  btnLn, btnSin, btnCos, btnTan, btnE, btnPi, btnInf, btnArcSin, btnArcCos, btnArcTan;
    private ArrayList<Button> mButtons;
    private View rootView;

    public KeypadLayout(View rootView) {
        this.rootView = rootView;
        mButtons = new ArrayList<>();

        btnClear = (Button) rootView.findViewById(R.id.keypadClear);
        mButtons.add(btnClear);
        btnDel = (Button) rootView.findViewById(R.id.keypadDel);
        mButtons.add(btnDel);
        btnParLeft = (Button) rootView.findViewById(R.id.parLeft);
        mButtons.add(btnParLeft);
        btnParRight = (Button) rootView.findViewById(R.id.parRight);
        mButtons.add(btnParRight);
        btnPlus = (Button) rootView.findViewById(R.id.plus);
        mButtons.add(btnPlus);
        btnMinus = (Button) rootView.findViewById(R.id.minus);
        mButtons.add(btnMultiply);
        btnMultiply = (Button) rootView.findViewById(R.id.multiply);
        mButtons.add(btnMultiply);
        btnDivide = (Button) rootView.findViewById(R.id.divide);
        mButtons.add(btnDivide);
        btnPercent = (Button) rootView.findViewById(R.id.btnPercent);
        mButtons.add(btnPercent);
        btnPower = (Button) rootView.findViewById(R.id.keypadPwr);
        mButtons.add(btnPower);
        btn1 = (Button) rootView.findViewById(R.id.keypad1);
        mButtons.add(btn1);
        btn2 = (Button) rootView.findViewById(R.id.keypad2);
        mButtons.add(btn2);
        btn3 = (Button) rootView.findViewById(R.id.keypad3);
        mButtons.add(btn3);
        btn4 = (Button) rootView.findViewById(R.id.keypad4);
        mButtons.add(btn4);
        btn5 = (Button) rootView.findViewById(R.id.keypad5);
        mButtons.add(btn5);
        btn6 = (Button) rootView.findViewById(R.id.keypad6);
        mButtons.add(btn6);
        btn7 = (Button) rootView.findViewById(R.id.keypad7);
        mButtons.add(btn7);
        btn8 = (Button) rootView.findViewById(R.id.keypad8);
        mButtons.add(btn8);
        btn9 = (Button) rootView.findViewById(R.id.keypad9);
        mButtons.add(btn9);
        btn0 = (Button) rootView.findViewById(R.id.keypad0);
        mButtons.add(btn0);
        btnDot = (Button) rootView.findViewById(R.id.keypadDot);
        mButtons.add(btnDot);
        btnStr = (Button) rootView.findViewById(R.id.keypadStore);
        mButtons.add(btnStr);
        btnRel = (Button) rootView.findViewById(R.id.keypadRelease);
        mButtons.add(btnRel);
        btnEqual = (Button) rootView.findViewById(R.id.equal);
        mButtons.add(btnEqual);
        btnFactorial = (Button) rootView.findViewById(R.id.btnFact);
        mButtons.add(btnFactorial);
        btnSqrt = (Button) rootView.findViewById(R.id.keypadSqrt);
        mButtons.add(btnSqrt);
        btnLog = (Button) rootView.findViewById(R.id.btnLog);
        mButtons.add(btnLog);
        btnLn = (Button) rootView.findViewById(R.id.btnLn);
        mButtons.add(btnLn);
        btnSin = (Button) rootView.findViewById(R.id.btnSin);
        mButtons.add(btnSin);
        btnCos = (Button) rootView.findViewById(R.id.btnCos);
        mButtons.add(btnCos);
        btnTan = (Button) rootView.findViewById(R.id.btnTan);
        mButtons.add(btnTan);
        btnE = (Button) rootView.findViewById(R.id.varE);
        mButtons.add(btnE);
        btnPi = (Button) rootView.findViewById(R.id.varPi);
        mButtons.add(btnPi);
        btnInf = (Button) rootView.findViewById(R.id.keypadInfinity);
        mButtons.add(btnInf);

        for(Button b : mButtons) {
            b.setOnTouchListener(this);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        } else if(event.getAction() == MotionEvent.ACTION_UP) {

        }

        return true;
    }
}
