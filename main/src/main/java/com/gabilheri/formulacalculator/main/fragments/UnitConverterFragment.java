package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.adapters.UnitSpinnerAdapter;
import com.gabilheri.formulacalculator.main.adapters.UnitTypeSpinnerAdapter;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.logic.unit.Unit;
import com.gabilheri.formulacalculator.main.logic.unit.UnitSpinnerItem;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;
import java.util.HashMap;

import conversionUnits.ArrayConstants;
import conversionUnits.ConversionUnit;
import conversionUnits.UnitConstants;
import conversionUnits.mass.Mass;
import conversionUnits.speed.Speed;
import conversionUnits.temperature.Temperature;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/29/14
 */
public class UnitConverterFragment extends Fragment implements View.OnClickListener {

    private static final String LOG_TAG = "UnitConverter";

    private DefaultButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private DefaultButton btnDot, btnPlusMinus, btnConvert, btnDel;

    private ArrayList<DefaultButton> mDefaultButtons;
    private ArrayList<DefaultButton> mSecondaryButtons;
    private ActionBar mActionBar;
    private Spinner typeSpinner, fromSpinner, toSpinner;
    private TextView fromType, toType;
    private int groupType, fromUnit, toUnit;
    private ConversionUnit conversionUnit;
    private BaseInputConnection textFieldInputConnection;
    private HashMap<Integer, UnitSpinnerAdapter> adapterMap;
    private boolean isNegative = false;


    private UnitSpinnerAdapter massAdapter, speedAdapter, lengthAdapter, temperatureAdapter, volumeAdapter, areaAdapter, dataAdapter;

    public UnitConverterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_unit_converter, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        LinearLayout settingsFrag = (LinearLayout) view.findViewById(R.id.unitConverter);
        Utils.setInsets(getActivity(), settingsFrag);

        Theme currentTheme = Utils.getCurrentTheme(getActivity());
        view.setBackgroundColor(currentTheme.getDisplayColor());

        mActionBar = getActivity().getActionBar();
        mActionBar.setIcon(R.drawable.ic_convert);

        mDefaultButtons = new ArrayList<>();
        mSecondaryButtons = new ArrayList<>();

        fromType = (TextView) view.findViewById(R.id.from_unit_input);
        fromType.setTextColor(currentTheme.getDisplayTextColor());
        fromType.setHintTextColor(currentTheme.getDisplayTextColor());
        toType = (TextView) view.findViewById(R.id.to_unit_input);
        toType.setHintTextColor(currentTheme.getDisplayTextColor());
        toType.setTextColor(currentTheme.getDisplayTextColor());

        //fromType.setInputType(InputType.TYPE_NULL);
        //toType.setInputType(InputType.TYPE_NULL);

        typeSpinner = (Spinner) view.findViewById(R.id.unit_type);
        fromSpinner = (Spinner) view.findViewById(R.id.from_unit);
        toSpinner = (Spinner) view.findViewById(R.id.to_unit);

        String[] unitNames = getResources().getStringArray(R.array.unit_types);
        TypedArray unitIcons = getResources().obtainTypedArray(R.array.unit_convert_icons);
        int[] unitTypes = ArrayConstants.UNIT_TYPES;
        ArrayList<UnitSpinnerItem> spinnerItems = new ArrayList<>();


        for (int i = 0; i < unitNames.length; i++) {
            Log.i(LOG_TAG, unitNames[i]);
            spinnerItems.add(new UnitSpinnerItem(unitNames[i], unitIcons.getResourceId(i, -1), unitTypes[i]));
        }

        UnitTypeSpinnerAdapter typeSpinnerAdapter = new UnitTypeSpinnerAdapter(getActivity(), spinnerItems);
        typeSpinner.setAdapter(typeSpinnerAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setGroupType(((UnitSpinnerItem) typeSpinner.getSelectedItem()).getUnitType());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn0 = (DefaultButton) view.findViewById(R.id.btn0);
        mDefaultButtons.add(btn0);

        btn1 = (DefaultButton) view.findViewById(R.id.btn1);
        mDefaultButtons.add(btn1);

        btn2 = (DefaultButton) view.findViewById(R.id.btn2);
        mDefaultButtons.add(btn2);

        btn3 = (DefaultButton) view.findViewById(R.id.btn3);
        mDefaultButtons.add(btn3);

        btn4 = (DefaultButton) view.findViewById(R.id.btn4);
        mDefaultButtons.add(btn4);

        btn5 = (DefaultButton) view.findViewById(R.id.btn5);
        mDefaultButtons.add(btn5);

        btn6 = (DefaultButton) view.findViewById(R.id.btn6);
        mDefaultButtons.add(btn6);

        btn7 = (DefaultButton) view.findViewById(R.id.btn7);
        mDefaultButtons.add(btn7);

        btn8 = (DefaultButton) view.findViewById(R.id.btn8);
        mDefaultButtons.add(btn8);

        btn9 = (DefaultButton) view.findViewById(R.id.btn9);
        mDefaultButtons.add(btn9);

        btnDot = (DefaultButton) view.findViewById(R.id.btnDot);
        mDefaultButtons.add(btnDot);

        btnPlusMinus = (DefaultButton) view.findViewById(R.id.btnpLusMinus);
        mDefaultButtons.add(btnPlusMinus);

        btnConvert = (DefaultButton) view.findViewById(R.id.btnConvert);
        mSecondaryButtons.add(btnConvert);
        btnConvert.setTextSize(32);

        btnDel = (DefaultButton) view.findViewById(R.id.btnDel);
        mSecondaryButtons.add(btnDel);

        Typeface mFont = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "icons.ttf");
        btnDel.setTypeface(mFont);
        btnDel.setTextSize(60);

        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fromType.setText("");
                toType.setText("");
                return true;
            }
        });

        //textFieldInputConnection = new BaseInputConnection(fromType, true);
        for (DefaultButton b : mDefaultButtons) {
            b.setOnClickListener(this);
            b.setCustomTextColor(currentTheme.getPrimaryButtonTextColor());
            b.setCustomBackgroundColor(currentTheme.getPrimaryColor());
            b.setCustomHighlightColor(currentTheme.getPrimaryHighlightColor());
        }

        for (DefaultButton b : mSecondaryButtons) {
            b.setOnClickListener(this);
            b.setCustomTextColor(currentTheme.getSecondaryButtonTextColor());
            b.setCustomBackgroundColor(currentTheme.getSecondaryColor());
            b.setCustomHighlightColor(currentTheme.getSecondaryHighlightColor());
        }

        initAdapters();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
            case R.id.btnDot:
                fromType.append(((DefaultButton) v).getText().toString());
                break;
            case R.id.btnDel:
                if(fromType.getText().length() > 0) {
                    fromType.setText(fromType.getText().toString().substring(0, fromType.getText().toString().length() - 1));
                }
                break;
            case R.id.btnpLusMinus:
                if(isNegative) {
                    fromType.setText(fromType.getText().toString().substring(1, fromType.getText().toString().length()));
                } else {
                    if(fromType.getText().toString().length() > 0) {
                        fromType.setText("-" + fromType.getText().toString());
                    } else {
                        fromType.append("-");
                    }
                    isNegative = true;
                }
                break;
            case R.id.btnConvert:
                if(fromType.getText().toString().length() == 0) {
                    return;
                }
                Unit fromUnit = (Unit) fromSpinner.getSelectedItem();
                Unit toUnit = (Unit) toSpinner.getSelectedItem();
                //Log.i(LOG_TAG, "Title: " + fromUnit.getName());
                //Log.i(LOG_TAG, "Type: " + fromUnit.getType());
                //Log.i(LOG_TAG, "To Unit Title: " + toUnit.getName());
                //Log.i(LOG_TAG, "To Unit Type: " + toUnit.getType());
                conversionUnit = instantiateConversionUnit(fromUnit.getType(), Double.parseDouble(fromType.getText().toString()));
                double result = conversionUnit.convertTo(toUnit.getType());
                Log.i(LOG_TAG, "Result: " + result);
                toType.setText("" + result);
                break;
            default:
                break;
        }
    }

    private void setGroupType(int groupType) {
        fromSpinner.setAdapter(adapterMap.get(groupType));
        toSpinner.setAdapter(adapterMap.get(groupType));
        this.groupType = groupType;
    }

    private void initAdapters() {
        adapterMap = new HashMap<>();
        /**
         * Mass Adapter
         */
        int[] massUnits = ArrayConstants.MASS_UNITS;
        String[] massUnitNames = getResources().getStringArray(R.array.mass_units);
        ArrayList<Unit> massUnitsList = new ArrayList<>();

        for(int i = 0; i < massUnitNames.length; i++) {
            massUnitsList.add(new Unit(massUnitNames[i], massUnits[i], UnitConstants.MASS));
        }
        massAdapter = new UnitSpinnerAdapter(getActivity(), massUnitsList);
        adapterMap.put(UnitConstants.MASS, massAdapter);
        /**
         * Speed Adapter
         */
        int[] speedUnits = ArrayConstants.SPEED_UNITS;
        String[] speedUnitNames = getResources().getStringArray(R.array.speed_units);
        ArrayList<Unit> speedUnitsList = new ArrayList<>();

        for(int i = 0; i < speedUnits.length; i++) {
            speedUnitsList.add(new Unit(speedUnitNames[i], speedUnits[i], UnitConstants.SPEED));
        }
        speedAdapter = new UnitSpinnerAdapter(getActivity(), speedUnitsList);
        adapterMap.put(UnitConstants.SPEED, speedAdapter);

        /**
         * Temperature Adapter
         */
        int[] temperatureUnits = ArrayConstants.TEMPERATURE_UNITS;
        String[] temperatureUnitNames = getResources().getStringArray(R.array.temperature_units);
        ArrayList<Unit> temperatureUnitsList = new ArrayList<>();

        for(int i = 0; i < temperatureUnits.length; i++) {
            temperatureUnitsList.add(new Unit(temperatureUnitNames[i], temperatureUnits[i], UnitConstants.TEMPERATURE));
        }

        temperatureAdapter = new UnitSpinnerAdapter(getActivity(), temperatureUnitsList);
        adapterMap.put(UnitConstants.TEMPERATURE, temperatureAdapter);

        fromSpinner.setAdapter(massAdapter);
        toSpinner.setAdapter(massAdapter);
        setGroupType(UnitConstants.MASS);
    }

    private ConversionUnit instantiateConversionUnit(int unitType, double value) {
        switch (groupType) {
            case UnitConstants.MASS:
                return new Mass(unitType, value).getConversionUnit();
            case UnitConstants.SPEED:
                return new Speed(unitType, value).getConversionUnit();
            case UnitConstants.TEMPERATURE:
                return new Temperature(unitType, value).getConversionUnit();
            default:
                return null;
        }
    }
}
