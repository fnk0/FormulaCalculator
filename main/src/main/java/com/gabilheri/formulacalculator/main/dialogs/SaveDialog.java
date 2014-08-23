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
import android.widget.EditText;

import com.gabilheri.formulacalculator.main.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/16/14.
 */
public class SaveDialog extends DialogFragment implements View.OnClickListener {

    private static final String LOG_TAG = "Save Dialog";
    public static final String THEME_NAME = "themeName";
    public static final int SAVE_DIALOG_CODE = 1001;
    private EditText themeName;

    public SaveDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_save_theme, null);

        themeName = (EditText) layout.findViewById(R.id.themeName);
        Button mButton = (Button) layout.findViewById(R.id.saveThemeFromDialog);
        mButton.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveThemeFromDialog:
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                extras.putString(THEME_NAME, themeName.getText().toString());
                intent.putExtras(extras);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                dismiss();
                break;
        }
    }
}
