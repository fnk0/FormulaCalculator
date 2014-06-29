package com.gabilheri.formulacalculator.main.fragments;

import android.util.Log;
import android.view.View;

import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;

/**
 * Created by marcus on 5/9/14.
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May 2014
 */
public class FragmentThemeCreator extends CalculatorFragment implements FragmentWithKeypad {

    private String LOG_TAG = "ThemeCreator";

    @Override
    public int getType() {
        return FragmentWithKeypad.THEME_CREATOR_FRAGMENT;
    }

    @Override
    public void handleKeypad(View view) {
        Log.i(LOG_TAG, "Override");
    }

}
