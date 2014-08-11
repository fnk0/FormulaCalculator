package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.utils.Utils;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/14.
 */
public class SettingsFragment extends Fragment {

    private ActionBar mActionBar;

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        TextView myTextView = (TextView) rootView.findViewById(R.id.myTextView);

        //myTextView.setText(Html.fromHtml(getString(R.string.settings_dummy)));

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
        LinearLayout settingsFrag = (LinearLayout) view.findViewById(R.id.settingsFrag);
        Utils.setInsets(getActivity(), settingsFrag);

        mActionBar = getActivity().getActionBar();
        mActionBar.setIcon(R.drawable.ic_settings);
    }
}
