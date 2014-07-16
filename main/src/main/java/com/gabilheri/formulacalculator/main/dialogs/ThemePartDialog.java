package com.gabilheri.formulacalculator.main.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.fragments.FragmentThemeCreator;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/15/14.
 */
public class ThemePartDialog extends DialogFragment implements View.OnClickListener {

    private static final String LOG_TAG = "Theme Part Dialog";
    public static final String EDIT_TYPE = "EditType";
    public static final int THEME_PART_DIALOG_CODE = 1000;

    private RadioGroup mGroup;

    public ThemePartDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_theme_element, null);
        Bundle extras = getArguments();

        Button mButton = (Button) layout.findViewById(R.id.selectPart);
        mButton.setOnClickListener(this);

        mGroup = (RadioGroup) layout.findViewById(R.id.themePartGroup);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putInt(EDIT_TYPE, getSelectedType(mGroup.getCheckedRadioButtonId()));
        intent.putExtras(extras);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();
    }

    private int getSelectedType(int id) {
        switch (id) {
            case R.id.backgroundEdit:
                return FragmentThemeCreator.BACKGROUND_EDIT;
            case R.id.highLightEdit:
                return FragmentThemeCreator.HIGHLIGHT_EDIT;
            case R.id.textColorEdit:
                return FragmentThemeCreator.TEXT_EDIT;
            default:
                return -1;
        }
    }
}
