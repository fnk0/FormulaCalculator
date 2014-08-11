package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.utils.Utils;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/29/14
 */
public class UnitConverterFragment extends Fragment {

    private static final String LOG_TAG = "UnitConverter";

    private ActionBar mActionBar;

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

        view.setBackgroundColor(Utils.getCurrentTheme(getActivity()).getDisplayColor());

        mActionBar = getActivity().getActionBar();
        mActionBar.setIcon(R.drawable.ic_convert);
    }
}
