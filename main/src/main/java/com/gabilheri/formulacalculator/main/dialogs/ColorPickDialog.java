package com.gabilheri.formulacalculator.main.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class ColorPickDialog extends DialogFragment implements View.OnClickListener, ColorPicker.OnColorChangedListener {

    private Button getColorButton;
    private ColorPicker colorPicker;
    private SVBar svBar;
    private OpacityBar opacityBar;
    private ValueBar valueBar;
    private SaturationBar saturationBar;
    private EditText hexValue;
    private String toDisplay;
    private int selectedView;
    public static final String VIEW = "view";
    public static final String COLOR = "color";
    public static final String BUTTON_TYPE = "buttonType";
    public static final int COLORPICK_CODE = 999;
    public static final String LOG_TAG = "ColorPicker";


    /**
     *
     */
    public ColorPickDialog() { }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_colorpicker, null);
        Bundle extras = getArguments();
        selectedView = extras.getInt("view");
        getColorButton = (Button) layout.findViewById(R.id.selColor);
        hexValue = (EditText) layout.findViewById(R.id.hexValue);
        colorPicker = (ColorPicker) layout.findViewById(R.id.colorPicker);
        svBar = (SVBar) layout.findViewById(R.id.svBar);
        opacityBar = (OpacityBar) layout.findViewById(R.id.opacityBar);
        valueBar = (ValueBar) layout.findViewById(R.id.valueBar);
        saturationBar = (SaturationBar) layout.findViewById(R.id.saturationBar);
        colorPicker.setColor(extras.getInt("color"));
        colorPicker.setOldCenterColor(extras.getInt("color"));
        colorPicker.addSVBar(svBar);
        colorPicker.addOpacityBar(opacityBar);
        colorPicker.addValueBar(valueBar);
        colorPicker.addSaturationBar(saturationBar);
        colorPicker.setOnColorChangedListener(this);
        toDisplay = "#" + Integer.toHexString(colorPicker.getColor());
        hexValue.setText(toDisplay.toUpperCase());
        getColorButton.setOnClickListener(this);

        /**
        hexValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                colorPicker.setColor(new BigInteger(s.subSequence(1, s.length()).toString(), 16).intValue());
            }
        });
         **/


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selColor:
                Log.i(LOG_TAG, "" + colorPicker.getColor());
                colorPicker.setOldCenterColor(colorPicker.getColor());
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(COLOR, Utils.getHexColor(hexValue.getText().toString()));
                extras.putInt(VIEW, selectedView);
                intent.putExtras(extras);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                dismiss();
                break;
        }
    }

    @Override
    public void onColorChanged(int i) {
        toDisplay = "#" + Integer.toHexString(i);
        hexValue.setText(toDisplay.toUpperCase());
    }
}
