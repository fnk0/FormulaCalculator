package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.adapters.SpinnerAdapter;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/29/14
 */
public class UnitConverterFragment extends Fragment implements FragmentWithKeypad {

    private static final String LOG_TAG = "UnitConverter";

    private DefaultButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private DefaultButton btnDot, btnPlusMinus, btnConvert, btnDel;

    private ArrayList<DefaultButton> mDefaultButtons;
    private ArrayList<DefaultButton> mSecondaryButtons;
    private ActionBar mActionBar;
    private Spinner typeSpinner, fromSpinner, toSpinner;

    public UnitConverterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_unit_converter, container, false);


        return rootView;
    }

    /**
     * @param view
     *         The View returned by {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}.
     * @param savedInstanceState
     *         If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout settingsFrag = (LinearLayout) view.findViewById(R.id.unitConverter);
        Utils.setInsets(getActivity(), settingsFrag);

        Theme currentTheme = Utils.getCurrentTheme(getActivity());
        view.setBackgroundColor(currentTheme.getDisplayColor());

        mActionBar = getActivity().getActionBar();
        mActionBar.setIcon(R.drawable.ic_convert);

        mDefaultButtons = new ArrayList<>();
        mSecondaryButtons = new ArrayList<>();

        typeSpinner = (Spinner) view.findViewById(R.id.unit_type);
        fromSpinner = (Spinner) view.findViewById(R.id.from_unit);
        toSpinner = (Spinner) view.findViewById(R.id.to_unit);

        //typeSpinner.setBackgroundColor(currentTheme.getDisplayColor());

        SpinnerAdapter typeSpinnerAdapter = new SpinnerAdapter(getActivity(), getResources().getStringArray(R.array.unit_types));
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

        for(DefaultButton b : mDefaultButtons) {
            b.setBackgroundColor(currentTheme.getPrimaryColor());
        }

        for(DefaultButton b : mSecondaryButtons) {
            b.setBackgroundColor(currentTheme.getSecondaryColor());
        }

    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public void handleKeypad(View view) {
        Log.i(LOG_TAG, ((DefaultButton) view).getText().toString());
    }
}
