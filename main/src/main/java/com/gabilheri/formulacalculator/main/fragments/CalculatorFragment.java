package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.R;

/**
 * Created by marcus on 5/6/14.
 */
public class CalculatorFragment extends Fragment {

    public CalculatorFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);

        return rootView;
    }
}
