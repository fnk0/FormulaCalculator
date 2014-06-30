package com.gabilheri.formulacalculator.main.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.gabilheri.formulacalculator.main.R;
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

    /**
     * @param savedInstanceState
     *         If the fragment is being re-created from
     *         a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param menu
     *         The options menu in which you place your items.
     * @param inflater
     *          The inflater for this menu
     * @see #setHasOptionsMenu
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.theme_creator_menu, menu);

    }

    /**
     *
     * @param item
     *         The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     *
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.saveTheme:
                //getActivity().runOnUiThread(run);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Destroys this fragment
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
