package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;

/**
 * Created by marcus on 5/7/14.
 */
public class SettingsFragment extends Fragment{

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        TextView myTextView = (TextView) rootView.findViewById(R.id.myTextView);

        //myTextView.setText(Html.fromHtml(getString(R.string.settings_dummy)));

        return rootView;
    }
}
