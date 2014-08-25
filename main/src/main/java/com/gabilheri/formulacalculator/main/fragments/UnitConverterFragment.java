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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.adapters.UnitSpinnerAdapter;
import com.gabilheri.formulacalculator.main.adapters.UnitTypeSpinnerAdapter;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.logic.unit.Unit;
import com.gabilheri.formulacalculator.main.logic.unit.UnitSpinnerItem;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;

import conversionUnits.ArrayConstants;
import conversionUnits.ConversionUnit;
import conversionUnits.UnitConstants;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/29/14
 */
public class UnitConverterFragment extends Fragment implements FragmentWithKeypad, View.OnClickListener {

    private static final String LOG_TAG = "UnitConverter";

    private DefaultButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private DefaultButton btnDot, btnPlusMinus, btnConvert, btnDel;

    private ArrayList<DefaultButton> mDefaultButtons;
    private ArrayList<DefaultButton> mSecondaryButtons;
    private ActionBar mActionBar;
    private Spinner typeSpinner, fromSpinner, toSpinner;
    private EditText fromType, toType;
    private int unitGroup, fromUnit, toUnit;
    private ConversionUnit conversionUnit;
    private BaseInputConnection textFieldInputConnection;


    private UnitSpinnerAdapter massAdapter, speedAdapter, lengthAdapter, temperatureAdapter, volumeAdapter, areaAdapter, dataAdapter;

    public UnitConverterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_unit_converter, container, false);

        return rootView;
    }

    /**
     * @param view               The View returned by {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
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

        fromType = (EditText) view.findViewById(R.id.from_unit_input);
        toType = (EditText) view.findViewById(R.id.to_unit_input);

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
        mSecondaryButtons.add(btnDot);

        btnPlusMinus = (DefaultButton) view.findViewById(R.id.btnpLusMinus);
        mSecondaryButtons.add(btnPlusMinus);

        btnConvert = (DefaultButton) view.findViewById(R.id.btnConvert);
        mSecondaryButtons.add(btnConvert);

        btnDel = (DefaultButton) view.findViewById(R.id.btnDel);
        mSecondaryButtons.add(btnDel);

        Typeface mFont = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "icons.ttf");
        btnDel.setTypeface(mFont);
        btnDel.setTextSize(35);

        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fromType.setText("");
                return true;
            }
        });

        //textFieldInputConnection = new BaseInputConnection(fromType, true);

        for (DefaultButton b : mDefaultButtons) {
            b.setBackgroundColor(currentTheme.getPrimaryColor());
        }

        for (DefaultButton b : mSecondaryButtons) {
            b.setBackgroundColor(currentTheme.getSecondaryColor());
        }

        initAdapters();

    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public void handleKeypad(View view) {
        switch (view.getId()) {
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
                fromType.append(((DefaultButton) view).getText().toString());
                break;
            case R.id.btnDel:
                fromType.setText(fromType.getText().toString().substring(0, fromType.getText().toString().length() - 1));
                break;
            default:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unit_type:
                setUnitType(((UnitSpinnerItem)typeSpinner.getSelectedItem()).getUnitType());
                break;
        }
    }


    private void setUnitType(int unitType) {

        switch (unitType) {

        }
    }

    private void initAdapters() {

        int[] massUnits = ArrayConstants.MASS_UNITS;
        String[] massUnitNames = getResources().getStringArray(R.array.mass_units);
        ArrayList<Unit> massUnitsList = new ArrayList<>();

        for(int i = 0; i < massUnitNames.length; i++) {
            massUnitsList.add(new Unit(massUnitNames[i], massUnits[i], UnitConstants.MASS));
        }

        massAdapter = new UnitSpinnerAdapter(getActivity(), massUnitsList);

        fromSpinner.setAdapter(massAdapter);
        toSpinner.setAdapter(massAdapter);
    }

}
