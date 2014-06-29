package com.gabilheri.formulacalculator.main.interfaces;

import android.view.View;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 *          since 6/29/14.
 */
public interface FragmentWithKeypad {

    public static int CALCULATOR_FRAGMENT = 0;
    public static int THEME_CREATOR_FRAGMENT = 1;

    public int getType();
    public void handleKeypad(View view);

}
