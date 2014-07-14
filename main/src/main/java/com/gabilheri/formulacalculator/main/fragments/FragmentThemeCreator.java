package com.gabilheri.formulacalculator.main.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.dialogs.ColorPickDialog;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;

/**
 * Created by marcus on 5/9/14.
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May 2014
 */
public class FragmentThemeCreator extends CalculatorFragment implements FragmentWithKeypad, View.OnLongClickListener{

    private String LOG_TAG = "ThemeCreator";
    public static final String VIEW = "view";
    public static final String COLOR = "color";
    public static final int PRIMARY_KEYPAD = 0;
    public static final int SECONDARY_KEYPAD = 1;
    public static final int PRIMARY_FUNCTIONS = 2;
    public static final int SECONDARY_FUNCTIONS = 3;
    private KeypadFragment mKeypad;
    private KeypadFunctionsFragment mFunctionsKeypad;
    private ArrayList<DefaultButton> primaryKeypadButtons, secondaryKeypadButtons, primaryFunctionButtons, secondaryFunctionButtons;

    /**
     *
     */
    public FragmentThemeCreator() {
        mKeypad = this.getmKeypadFragment();
        mFunctionsKeypad = this.getmKeypadFunctionsFragment();
        primaryKeypadButtons = mKeypad.getPrimaryButtonsArray();
        secondaryKeypadButtons = mKeypad.getSecondButtonsArray();
        primaryFunctionButtons = mFunctionsKeypad.getPrimaryButtonsArray();
        secondaryKeypadButtons = mFunctionsKeypad.getSecondaryButtonsArray();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ColorPickDialog.COLORPICK_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    DefaultButton mButton = (DefaultButton) getRootView().findViewById(mBundle.getInt(VIEW));
                    mButton.setCustomBackgroundColor(mBundle.getInt(ColorPickDialog.SELECTED_COLOR));
                }
                break;
        }
    }

    public void showPickerDialog(int viewID, int buttonColor) {

        ColorPickDialog pickerDialog = new ColorPickDialog();
        pickerDialog.setTargetFragment(this, ColorPickDialog.COLORPICK_CODE);
        Bundle extras = new Bundle();
        extras.putInt(VIEW, viewID);
        extras.putInt(COLOR, buttonColor);
        pickerDialog.setArguments(extras);
        pickerDialog.show(getFragmentManager(), "pickerDialog");
    }

    public int getButtonType(int viewID) {

        DefaultButton mButton = (DefaultButton) getRootView().findViewById(viewID);

        if(primaryKeypadButtons.contains(mButton)) {
            return PRIMARY_KEYPAD;
        } else if(secondaryKeypadButtons.contains(mButton)) {
            return SECONDARY_KEYPAD;
        } else if(primaryFunctionButtons.contains(mButton)) {
            return PRIMARY_FUNCTIONS;
        } else if(secondaryFunctionButtons.contains(mButton)) {
            
        }



        return -1;

    }


    @Override
    public int getType() {
        return FragmentWithKeypad.THEME_CREATOR_FRAGMENT;
    }

    @Override
    public void handleKeypad(View view) {
        showPickerDialog(view.getId(), ((DefaultButton) view).getCustomBackgroundColor());

    }


    @Override
    public boolean onLongClick(View view) {
        return true;
    }

    /**
     * @param savedInstanceState
     *         If the fragment is being re-created from
     *         a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        inflater.inflate(R.menu.menu_themes, menu);

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
