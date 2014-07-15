package com.gabilheri.formulacalculator.main.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.Theme;
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
public class FragmentThemeCreator extends CalculatorFragment implements FragmentWithKeypad {

    private String LOG_TAG = "ThemeCreator";
    public static final String VIEW = "view";
    public static final String COLOR = "color";
    public static final String BUTTON_TYPE = "buttonType";
    public static final int PRIMARY_KEYPAD = 0;
    public static final int SECONDARY_KEYPAD = 1;
    public static final int PRIMARY_FUNCTIONS = 2;
    public static final int SECONDARY_FUNCTIONS = 3;
    public static final int BACKGROUND_EDIT = 100;
    public static final int HIGHLIGHT_EDIT = 101;
    public static final int TEXT_EDIT = 102;
    public static final int DISPLAY_EDIT = 103;
    public int editMode = 0;
    private int buttonGroup = -1;
    private Theme newTheme;
    private KeypadFragment mKeypad;
    private KeypadFunctionsFragment mFunctionsKeypad;
    private ArrayList<DefaultButton> primaryKeypadButtons, secondaryKeypadButtons, primaryFunctionButtons, secondaryFunctionButtons;

    /**
     *
     */
    public FragmentThemeCreator() {
        super();
        newTheme = new Theme();
        mKeypad = getKeypadFragment();
        mFunctionsKeypad = getKeypadFunctionsFragment();
        primaryKeypadButtons = mKeypad.getPrimaryButtonsArray();
        secondaryKeypadButtons = mKeypad.getSecondButtonsArray();
        primaryFunctionButtons = mFunctionsKeypad.getPrimaryButtonsArray();
        secondaryFunctionButtons = mFunctionsKeypad.getSecondaryButtonsArray();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ColorPickDialog.COLORPICK_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    if(editMode == BACKGROUND_EDIT) {
                        for(DefaultButton mButton : getArrayForType(buttonGroup)) {
                            mButton.setCustomBackgroundColor(mBundle.getInt(ColorPickDialog.COLOR));
                        }
                    } else if(editMode == HIGHLIGHT_EDIT) {
                        for(DefaultButton mButton : getArrayForType(buttonGroup)) {
                            mButton.setCustomHighlightColor(mBundle.getInt(ColorPickDialog.COLOR));
                        }
                    }
                }
                break;
        }
    }

    /**
     * Helper method to show a dialog with a colorPicker
     *
     * @param viewID
     *          The ID of the selected view
     * @param buttonColor
     *          The color of the selected Button
     */
    public void showPickerDialog(int viewID, int buttonColor) {
        ColorPickDialog pickerDialog = new ColorPickDialog();
        pickerDialog.setTargetFragment(this, ColorPickDialog.COLORPICK_CODE);
        Bundle extras = new Bundle();
        extras.putInt(VIEW, viewID);
        extras.putInt(COLOR, buttonColor);
        buttonGroup = getButtonType(viewID);
        pickerDialog.setArguments(extras);
        pickerDialog.show(getFragmentManager(), "pickerDialog");
    }

    /**
     * Returns the group to which the pressed button belongs
     *
     * @param viewID
     *      The ID of the selected view
     * @return
     *      The group to which the selected view belongs to
     */
    public int getButtonType(int viewID) {
        DefaultButton mButton = (DefaultButton) getRootView().findViewById(viewID);
        Log.i(LOG_TAG, "Button: " + mButton.getText().toString());
        if(primaryKeypadButtons.contains(mButton)) {
            Log.i(LOG_TAG, "Group: " + PRIMARY_KEYPAD);
            return PRIMARY_KEYPAD;
        } else if(secondaryKeypadButtons.contains(mButton)) {
            Log.i(LOG_TAG, "Group: " + SECONDARY_KEYPAD);
            return SECONDARY_KEYPAD;
        } else if(primaryFunctionButtons.contains(mButton)) {
            Log.i(LOG_TAG, "Group: " + PRIMARY_FUNCTIONS);
            return PRIMARY_FUNCTIONS;
        } else if(secondaryFunctionButtons.contains(mButton)) {
            Log.i(LOG_TAG, "Group: " + SECONDARY_FUNCTIONS);
            return SECONDARY_FUNCTIONS;
        }
        return -1;
    }

    public ArrayList<DefaultButton> getArrayForType(int buttonType) {
        ArrayList mButtons = new ArrayList();

        switch (buttonType) {
            case PRIMARY_KEYPAD:
                mButtons = primaryKeypadButtons;
                break;
            case SECONDARY_KEYPAD:
                mButtons = secondaryKeypadButtons;
                break;
            case PRIMARY_FUNCTIONS:
                mButtons = primaryFunctionButtons;
                break;
            case SECONDARY_FUNCTIONS:
                mButtons = secondaryFunctionButtons;
                break;
            default:
                mButtons = new ArrayList();
                break;
        }
        return mButtons;
    }


    @Override
    public int getType() {
        return FragmentWithKeypad.THEME_CREATOR_FRAGMENT;
    }

    @Override
    public void handleKeypad(View view) {
        if(view == getDisplay()) {

        }
        editMode = BACKGROUND_EDIT;
        showPickerDialog(view.getId(), ((DefaultButton) view).getCustomBackgroundColor());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDisplay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "Clicked Display!");
            }
        });
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
        //inflater.inflate(R.menu.theme_creator, menu);
        MenuItem menuItem = menu.add(Menu.NONE, R.id.saveTheme, 10, R.string.save_theme);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(android.R.drawable.ic_menu_save);
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
