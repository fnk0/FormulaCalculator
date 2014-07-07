package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since since 7/5/14.
 */
public class KeypadFragment extends Fragment {

    private DefaultButton btnClear, btnDel, btnParLeft, btnParRight, btnPlus, btnMinus, btnMultiply, btnDivide, btnPercent, btnPower;
    private DefaultButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnStr, btnRel, btnEqual;
    private HashMap<Integer, DefaultButton> mKeypadButtons;
    private ArrayList<DefaultButton> mButtonsArray;

    public KeypadFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.keypad_layout, container, false);
        mKeypadButtons = new HashMap<>();
        mButtonsArray = new ArrayList<>();

        btnClear = (DefaultButton) rootView.findViewById(R.id.keypadClear);
        btnClear.setCustomBackgroundColor(getResources().getColor(R.color.button_2));
        btnClear.setCustomHighlightColor(getResources().getColor(R.color.button_2_pressed));
        mKeypadButtons.put(R.id.keypadClear, btnClear);
        mButtonsArray.add(btnClear);

        btnDel = (DefaultButton) rootView.findViewById(R.id.keypadDel);
        mKeypadButtons.put(R.id.keypadDel, btnDel);
        mButtonsArray.add(btnDel);

        btnParLeft = (DefaultButton) rootView.findViewById(R.id.parLeft);
        mKeypadButtons.put(R.id.parLeft, btnParLeft);
        mButtonsArray.add(btnParLeft);

        btnParRight = (DefaultButton) rootView.findViewById(R.id.parRight);
        mKeypadButtons.put(R.id.parRight, btnParRight);
        mButtonsArray.add(btnParRight);

        btnPlus = (DefaultButton) rootView.findViewById(R.id.plus);
        mKeypadButtons.put(R.id.plus, btnPlus);
        mButtonsArray.add(btnPlus);

        btnMinus = (DefaultButton) rootView.findViewById(R.id.minus);
        mKeypadButtons.put(R.id.minus, btnMinus);
        mButtonsArray.add(btnMinus);

        btnMultiply = (DefaultButton) rootView.findViewById(R.id.multiply);
        mKeypadButtons.put(R.id.multiply, btnMultiply);
        mButtonsArray.add(btnMultiply);

        btnDivide = (DefaultButton) rootView.findViewById(R.id.divide);
        mKeypadButtons.put(R.id.divide, btnDivide);
        mButtonsArray.add(btnDivide);

        btnPercent = (DefaultButton) rootView.findViewById(R.id.btnPercent);
        mKeypadButtons.put(R.id.btnPercent, btnPercent);
        mButtonsArray.add(btnPercent);

        btnPower = (DefaultButton) rootView.findViewById(R.id.keypadPwr);
        mKeypadButtons.put(R.id.keypadPwr, btnPower);
        mButtonsArray.add(btnPower);

        btn1 = (DefaultButton) rootView.findViewById(R.id.keypad1);
        mKeypadButtons.put(R.id.keypad1, btn1);
        mButtonsArray.add(btn1);

        btn2 = (DefaultButton) rootView.findViewById(R.id.keypad2);
        mKeypadButtons.put(R.id.keypad2, btn2);
        mButtonsArray.add(btn2);

        btn3 = (DefaultButton) rootView.findViewById(R.id.keypad3);
        mKeypadButtons.put(R.id.keypad3, btn3);
        mButtonsArray.add(btn3);

        btn4 = (DefaultButton) rootView.findViewById(R.id.keypad4);
        mKeypadButtons.put(R.id.keypad4, btn4);
        mButtonsArray.add(btn4);

        btn5 = (DefaultButton) rootView.findViewById(R.id.keypad5);
        mKeypadButtons.put(R.id.keypad5, btn5);
        mButtonsArray.add(btn5);

        btn6 = (DefaultButton) rootView.findViewById(R.id.keypad6);
        mKeypadButtons.put(R.id.keypad6, btn6);
        mButtonsArray.add(btn6);

        btn7 = (DefaultButton) rootView.findViewById(R.id.keypad7);
        mKeypadButtons.put(R.id.keypad7, btn7);
        mButtonsArray.add(btn7);

        btn8 = (DefaultButton) rootView.findViewById(R.id.keypad8);
        mKeypadButtons.put(R.id.keypad8, btn8);
        mButtonsArray.add(btn8);

        btn9 = (DefaultButton) rootView.findViewById(R.id.keypad9);
        mKeypadButtons.put(R.id.keypad9, btn9);
        mButtonsArray.add(btn9);

        btn0 = (DefaultButton) rootView.findViewById(R.id.keypad0);
        mKeypadButtons.put(R.id.keypad0, btn0);
        mButtonsArray.add(btn0);

        btnDot = (DefaultButton) rootView.findViewById(R.id.keypadDot);
        mKeypadButtons.put(R.id.keypadDot, btnDot);
        mButtonsArray.add(btnDot);

        btnStr = (DefaultButton) rootView.findViewById(R.id.keypadStore);
        mKeypadButtons.put(R.id.keypadStore, btnStr);
        mButtonsArray.add(btnStr);

        btnRel = (DefaultButton) rootView.findViewById(R.id.keypadRelease);
        mKeypadButtons.put(R.id.keypadRelease, btnRel);
        mButtonsArray.add(btnRel);

        btnEqual = (DefaultButton) rootView.findViewById(R.id.equal);
        mKeypadButtons.put(R.id.equal, btnEqual);
        mButtonsArray.add(btnEqual);

        for(DefaultButton mButton: mButtonsArray) {
            Log.i("BUTTONS", mButton.getText().toString());
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public HashMap<Integer, DefaultButton> getmKeypadButtons() {
        return mKeypadButtons;
    }

    public ArrayList<DefaultButton> getmButtonsArray() {
        return mButtonsArray;
    }
}