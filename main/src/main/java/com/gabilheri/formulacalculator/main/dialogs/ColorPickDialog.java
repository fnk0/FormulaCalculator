package com.gabilheri.formulacalculator.main.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
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
public class ColorPickDialog extends DialogFragment implements View.OnClickListener {

    private Button getColorButton, setColor;
    private ColorPicker colorPicker;
    private SVBar svBar;
    private OpacityBar opacityBar;
    private ValueBar valueBar;
    private SaturationBar saturationBar;
    private EditText hexValue;
    public static final String LOG_TAG = "ColorPicker";

    /**
     *
     */
    public ColorPickDialog() { }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_colorpicker, null);

        getColorButton = (Button) layout.findViewById(R.id.selColor);
        setColor = (Button) layout.findViewById(R.id.setColor);
        hexValue = (EditText) layout.findViewById(R.id.hexValue);
        colorPicker = (ColorPicker) layout.findViewById(R.id.colorPicker);
        svBar = (SVBar) layout.findViewById(R.id.svBar);
        opacityBar = (OpacityBar) layout.findViewById(R.id.opacityBar);
        valueBar = (ValueBar) layout.findViewById(R.id.valueBar);
        saturationBar = (SaturationBar) layout.findViewById(R.id.saturationBar);
        colorPicker.addSVBar(svBar);
        colorPicker.addOpacityBar(opacityBar);
        colorPicker.addValueBar(valueBar);
        colorPicker.addSaturationBar(saturationBar);
        getColorButton.setOnClickListener(this);
        setColor.setOnClickListener(this);
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
                break;
            case R.id.setColor:
                int colorValue = 0;
                if(hexValue.getText().toString().startsWith("#")) {
                    Log.i(LOG_TAG, "The Input Color is: " + Long.decode(hexValue.getText().toString()).intValue());
                    colorValue = Long.decode(hexValue.getText().toString()).intValue();
                } else {
                    colorValue = Long.decode(Utils.rgbaToHex(hexValue.getText().toString())).intValue();
                }

                colorPicker.setColor(colorValue);
                colorPicker.setOldCenterColor(colorPicker.getColor());
                break;
        }
    }


}
