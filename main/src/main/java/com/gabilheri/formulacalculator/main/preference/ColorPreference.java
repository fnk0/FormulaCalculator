package com.gabilheri.formulacalculator.main.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.dialogs.ColorPickDialog;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/15/14.
 */
public class ColorPreference extends DialogPreference implements ColorPicker.OnColorChangedListener {

    private String color;
    private ColorPicker colorPicker;
    private SVBar svBar;
    private OpacityBar opacityBar;
    private ValueBar valueBar;
    private SaturationBar saturationBar;
    private EditText hexValue;
    private String toDisplay;
    private ShapeDrawable mDrawable;

    public ColorPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.setIntrinsicHeight(100);
        mDrawable.setIntrinsicWidth(100);
        setIcon(mDrawable);
        setDialogLayoutResource(R.layout.dialog_colorpicker);
        setDialogIcon(R.drawable.ic_themes);
        setPersistent(true);
    }

    @Override
    protected void onBindDialogView(View view) {
        hexValue = (EditText) view.findViewById(R.id.hexValue);
        colorPicker = (ColorPicker) view.findViewById(R.id.colorPicker);
        svBar = (SVBar) view.findViewById(R.id.svBar);
        opacityBar = (OpacityBar) view.findViewById(R.id.opacityBar);
        opacityBar.setVisibility(View.GONE);
        valueBar = (ValueBar) view.findViewById(R.id.valueBar);
        saturationBar = (SaturationBar) view.findViewById(R.id.saturationBar);
        colorPicker.addSVBar(svBar);
        colorPicker.addValueBar(valueBar);
        colorPicker.addSaturationBar(saturationBar);
        colorPicker.setOnColorChangedListener(this);
        colorPicker.setOldCenterColor(Utils.getHexColor(this.getPersistedString(getKey())));
        colorPicker.setNewCenterColor(Utils.getHexColor(this.getPersistedString(getKey())));
        colorPicker.setColor(Utils.getHexColor(this.getPersistedString(getKey())));
        toDisplay = "#" + Integer.toHexString(colorPicker.getColor());
        hexValue.setText(toDisplay.toUpperCase());
        setPositiveButtonText("Select Color");

        super.onBindDialogView(view);
    }

    @Override
    protected View onCreateDialogView() {
        return super.onCreateDialogView();
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if(positiveResult) {
            this.color = hexValue.getText().toString();
            if (callChangeListener(color)) {
                String summary = super.getSummary().toString();
                this.setSummary(String.format(summary, color));
                callChangeListener(color);
                persistString(color);
                mDrawable.getPaint().setColor(Utils.getHexColor(color));
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if(restorePersistedValue) {
            color = this.getPersistedString(getKey());
        } else {
            color = (String) defaultValue;
            persistString(color);
        }

        mDrawable.getPaint().setColor(Utils.getHexColor(color));
    }

    @Override
    public void onColorChanged(int i) {
        toDisplay = "#" + Integer.toHexString(i);
        hexValue.setText(toDisplay.toUpperCase());
    }

    @Override
    public CharSequence getSummary() {
        String summary = super.getSummary().toString();
        return String.format(summary, color);
    }
}
