package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.utils.Utils;
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

    private DefaultButton btnDel, btnParLeft, btnParRight, btnPlus, btnMinus, btnMultiply, btnDivide;
    private DefaultButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnEqual, btnAns;
    private HashMap<Integer, DefaultButton> mKeypadButtons;
    private ArrayList<DefaultButton> mButtonsArray;
    private ArrayList<DefaultButton> mSecondButtonsArray;
    private static final String LOG_TAG = "Keypad Fragment";
    private View.OnClickListener listener;

    public KeypadFragment() {
        mKeypadButtons = new HashMap<>();
        mButtonsArray = new ArrayList<>();
        mSecondButtonsArray = new ArrayList<>();
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences mPreferences = getActivity().getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        Theme currentTheme = dbHelper.getThemeByName(mPreferences.getString(MainActivity.CURRENT_THEME, MainActivity.CURRENT_THEME));

        Log.i(LOG_TAG, "Current Theme: " + currentTheme.getThemeName());

        View rootView = null;

        if(Utils.isKeypadStandard(getActivity())) {
            rootView = inflater.inflate(R.layout.keypad_layout_standard, container, false);
        } else {
            rootView = inflater.inflate(R.layout.keypad_layout, container, false);
        }

        btnDel = (DefaultButton) rootView.findViewById(R.id.keypadDel);
        mKeypadButtons.put(R.id.keypadDel, btnDel);
        mSecondButtonsArray.add(btnDel);

        Typeface mFont = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "icons.ttf");
        btnDel.setTypeface(mFont);
        btnDel.setLongClickable(true);
        btnDel.setTextSize(Utils.getDeleteButtonTextSize(getActivity()));
        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) getActivity()).clearDisplay();
                return false;
            }
        });

        btnAns = (DefaultButton) rootView.findViewById(R.id.answer);
        mKeypadButtons.put(R.id.answer, btnAns);
        mButtonsArray.add(btnAns);
        btnAns.setTextSize(Utils.getAnswerButtonTextSize(getActivity()));

        btnParLeft = (DefaultButton) rootView.findViewById(R.id.parLeft);
        mKeypadButtons.put(R.id.parLeft, btnParLeft);
        mButtonsArray.add(btnParLeft);

        btnParRight = (DefaultButton) rootView.findViewById(R.id.parRight);
        mKeypadButtons.put(R.id.parRight, btnParRight);
        mButtonsArray.add(btnParRight);

        btnPlus = (DefaultButton) rootView.findViewById(R.id.plus);
        mKeypadButtons.put(R.id.plus, btnPlus);
        mSecondButtonsArray.add(btnPlus);

        btnMinus = (DefaultButton) rootView.findViewById(R.id.minus);
        mKeypadButtons.put(R.id.minus, btnMinus);
        mSecondButtonsArray.add(btnMinus);

        btnMultiply = (DefaultButton) rootView.findViewById(R.id.multiply);
        mKeypadButtons.put(R.id.multiply, btnMultiply);
        mSecondButtonsArray.add(btnMultiply);

        btnDivide = (DefaultButton) rootView.findViewById(R.id.divide);
        mKeypadButtons.put(R.id.divide, btnDivide);
        mSecondButtonsArray.add(btnDivide);

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

        btnEqual = (DefaultButton) rootView.findViewById(R.id.equal);
        mKeypadButtons.put(R.id.equal, btnEqual);
        mButtonsArray.add(btnEqual);

        for(DefaultButton mButton : mButtonsArray) {
            mButton.setCustomTextColor(currentTheme.getPrimaryButtonTextColor());
            mButton.setCustomBackgroundColor(currentTheme.getPrimaryColor());
            mButton.setCustomHighlightColor(currentTheme.getPrimaryHighlightColor());
            mButton.setOnClickListener(listener);
        }

        for(DefaultButton mButton: mSecondButtonsArray) {
            mButton.setCustomTextColor(currentTheme.getSecondaryButtonTextColor());
            mButton.setCustomBackgroundColor(currentTheme.getSecondaryColor());
            mButton.setCustomHighlightColor(currentTheme.getSecondaryHighlightColor());
            mButton.setOnClickListener(listener);
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

    public ArrayList<DefaultButton> getPrimaryButtonsArray() {
        return mButtonsArray;
    }

    public ArrayList<DefaultButton> getSecondButtonsArray() {
        return mSecondButtonsArray;
    }
}
