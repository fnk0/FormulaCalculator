package com.gabilheri.formulacalculator.main.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.dialogs.ColorPickDialog;
import com.gabilheri.formulacalculator.main.dialogs.SaveDialog;
import com.gabilheri.formulacalculator.main.dialogs.ThemePartDialog;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;

/**
 *
 * Created by marcus on 5/9/14.
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May 2014
 *
 */
public class FragmentThemeCreator extends CalculatorFragment implements FragmentWithKeypad, View.OnClickListener {

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
    private View selectedView;
    private int editView = 0;
    public int editMode = 0;
    private int buttonGroup = -1;
    private Theme newTheme;
    private KeypadFragment mKeypad;
    private KeypadFunctionsFragment mFunctionsKeypad;
    private ArrayList<DefaultButton> primaryKeypadButtons, secondaryKeypadButtons, primaryFunctionButtons, secondaryFunctionButtons;
    private MainActivity mActivity;

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

        primaryKeypadButtons.addAll(primaryFunctionButtons);
        secondaryKeypadButtons.addAll(secondaryFunctionButtons);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ColorPickDialog.COLORPICK_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    if(editMode == BACKGROUND_EDIT) {
                        if(editView == DISPLAY_EDIT) {
                            getDisplay().setBackgroundColor(mBundle.getInt(ColorPickDialog.COLOR));
                            mActivity.updateActionBar(mBundle.getInt(ColorPickDialog.COLOR), newTheme.getDisplayTextColor());
                            mActivity.setActionBarColor();
                            mActivity.setActionBarColor();
                            buttonGroup = DISPLAY_EDIT;
                        } else {
                            for(DefaultButton mButton : getArrayForType(buttonGroup)) {
                                mButton.setCustomBackgroundColor(mBundle.getInt(ColorPickDialog.COLOR));
                            }
                        }
                    } else if(editMode == HIGHLIGHT_EDIT) {
                        for(DefaultButton mButton : getArrayForType(buttonGroup)) {
                            mButton.setCustomHighlightColor(mBundle.getInt(ColorPickDialog.COLOR));
                        }
                    } else if(editMode == TEXT_EDIT) {
                        if(editView == DISPLAY_EDIT) {
                            Log.i(LOG_TAG, "Is this working? !");
                            buttonGroup = DISPLAY_EDIT;
                            mActivity.updateActionBar(newTheme.getDisplayColor(), mBundle.getInt(ColorPickDialog.COLOR));
                            mActivity.setActionBarColor();
                            getInputBoxKey().setTextColor(mBundle.getInt(ColorPickDialog.COLOR));
                            getResultBoxKey().setTextColor(mBundle.getInt(ColorPickDialog.COLOR));
                            getBlinkingText().setBackgroundColor(mBundle.getInt(ColorPickDialog.COLOR));
                        } else {
                            for(DefaultButton mButton : getArrayForType(buttonGroup)) {
                                mButton.setCustomTextColor(mBundle.getInt(ColorPickDialog.COLOR));
                            }
                        }
                    }
                    setThemeColorForType(buttonGroup, mBundle.getInt(ColorPickDialog.COLOR));
                }
                break;
            case ThemePartDialog.THEME_PART_DIALOG_CODE:

                if(resultCode == Activity.RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    editMode =  mBundle.getInt(ThemePartDialog.EDIT_TYPE);
                    getPickerDialog(selectedView);
                }
                break;
            case SaveDialog.SAVE_DIALOG_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    newTheme.setThemeName(mBundle.getString(SaveDialog.THEME_NAME));
                    newTheme.setThemeType(Theme.THEME_PUBLIC);
                    newTheme.setUsername("user");
                    DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
                    db.createTheme(newTheme);

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.displayView(MainActivity.THEMES_FRAG, null);
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
    private void showPickerDialog(int viewID, int buttonColor) {
        ColorPickDialog pickerDialog = new ColorPickDialog();
        pickerDialog.setTargetFragment(this, ColorPickDialog.COLORPICK_CODE);
        Bundle extras = new Bundle();
        extras.putInt(VIEW, viewID);
        extras.putInt(COLOR, buttonColor);
        buttonGroup = getButtonType(viewID);
        pickerDialog.setArguments(extras);

        pickerDialog.show(getFragmentManager(), "pickerDialog");
    }

    private void showSaveDialog() {
        SaveDialog mSave = new SaveDialog();
        mSave.setTargetFragment(this, SaveDialog.SAVE_DIALOG_CODE);
        mSave.show(getFragmentManager(), "saveDialog");
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
        if(viewID == R.id.resultLayoutKey) {
            Log.i(LOG_TAG, "Button Type is Display!!!");
            return -1;
        }
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

    /**
     *
     * @param buttonType
     *          The type of Button that needs to be get
     * @return
     *     The ArrayList for the type of buttons that needs to be changed
     */
    public ArrayList<DefaultButton> getArrayForType(int buttonType) {
        ArrayList mButtons;

        switch (buttonType) {
            case PRIMARY_KEYPAD:
            case PRIMARY_FUNCTIONS:
                mButtons = primaryKeypadButtons;
                mButtons.addAll(primaryFunctionButtons);
                break;
            case SECONDARY_KEYPAD:
            case SECONDARY_FUNCTIONS:
                mButtons = secondaryKeypadButtons;
                mButtons.addAll(secondaryFunctionButtons);
                break;
            default:
                mButtons = new ArrayList();
                break;
        }
        return mButtons;
    }

    /**
     * Sets the selected element to the new Theme to be saved
     * @param buttonType
     *          The type/group of the Button or text to be added
     * @param color
     *          The color to  be set to this element
     */
    private void setThemeColorForType(int buttonType, int color) {
        switch (buttonType) {
            case PRIMARY_KEYPAD:
            case PRIMARY_FUNCTIONS:
                if(editMode == BACKGROUND_EDIT) {
                    newTheme.setPrimaryColor(color);
                } else if(editMode == HIGHLIGHT_EDIT) {
                    newTheme.setPrimaryHighlightColor(color);
                } else if(editMode == TEXT_EDIT) {
                    newTheme.setPrimaryButtonTextColor(color);
                }
                break;
            case SECONDARY_KEYPAD:
            case SECONDARY_FUNCTIONS:
                if(editMode == BACKGROUND_EDIT) {
                    newTheme.setSecondaryColor(color);
                } else if(editMode == HIGHLIGHT_EDIT) {
                    newTheme.setSecondaryHighlightColor(color);
                } else if(editMode == TEXT_EDIT) {
                    newTheme.setSecondaryButtonTextColor(color);
                }
                break;
            case DISPLAY_EDIT:
                if(editMode == BACKGROUND_EDIT) {
                    newTheme.setDisplayColor(color);
                } else if (editMode == TEXT_EDIT) {
                    Log.i(LOG_TAG, "Text editing...!!");
                    newTheme.setDisplayTextColor(color);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getType() {
        return FragmentWithKeypad.THEME_CREATOR_FRAGMENT;
    }

    @Override
    public void onClick(View v) {
        handleKeypad(v);
    }

    @Override
    public void handleKeypad(View view) {
        selectedView = view;
        ThemePartDialog mDialog = new ThemePartDialog();
        Bundle mBundle = new Bundle();
        if(view.getId() == getDisplay().getId()) {
            mBundle.putBoolean(ThemePartDialog.DISPLAY, true);
        } else {
            mBundle.putBoolean(ThemePartDialog.DISPLAY, false);
        }
        mDialog.setArguments(mBundle);
        mDialog.setTargetFragment(this, ThemePartDialog.THEME_PART_DIALOG_CODE);
        mDialog.show(getFragmentManager(), "Theme Part Dialog");

    }

    private void getPickerDialog(View view) {
        if(view.getId() == R.id.resultLayoutKey) {
            editView = DISPLAY_EDIT;
            int color = Color.TRANSPARENT;
            if(editMode == BACKGROUND_EDIT) {
                Log.i(LOG_TAG, "Display Edit!!");
                Drawable background = view.getBackground();
                if (background instanceof PaintDrawable) {
                    color = ((PaintDrawable) background).getPaint().getColor();
                    Log.i(LOG_TAG, "Paint Drawable!! --> color: " + color);
                } else if(background instanceof ColorDrawable) {
                    color = ((ColorDrawable) background).getColor();
                    Log.i(LOG_TAG, "Color Drawable!! --> color: " + color);
                }
            } else if(editMode == TEXT_EDIT) {
                Log.i(LOG_TAG, "TextView Edit!!");
                color = newTheme.getDisplayTextColor();
            }

            showPickerDialog(view.getId(), color);
        } else {
            editView = 0;
            if(editMode == BACKGROUND_EDIT) {
                showPickerDialog(view.getId(), ((DefaultButton) view).getCustomBackgroundColor());
            } else if(editMode == HIGHLIGHT_EDIT) {
                showPickerDialog(view.getId(), ((DefaultButton) view).getCustomHighlightColor());
            } else if(editMode == TEXT_EDIT) {
                showPickerDialog(view.getId(), ((DefaultButton) view).getCustomTextColor());
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getDisplay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleKeypad(getDisplay());
            }
        });
        mActivity = (MainActivity) getActivity();
        newTheme = getCurrentTheme();
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
        MenuItem menuItem = menu.add(Menu.NONE, R.id.saveTheme, 10, R.string.save_theme);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setTitle("Save");
        menuItem.setIcon(R.drawable.ic_action_save);
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
                showSaveDialog();
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
