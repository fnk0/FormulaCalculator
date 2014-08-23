package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.preference.ColorPreference;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.Calendar;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/14.
 */
public class SettingsFragment extends XmlFragment implements FragmentManager.OnBackStackChangedListener {

    private ActionBar mActionBar;
    private MainActivity mainActivity;
    private static String LOG_TAG = "SettingsFragment";

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        getFragmentManager().addOnBackStackChangedListener(this);
        mainActivity = (MainActivity) getActivity();
        shouldDisplayHomeUp();
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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        FragmentManager fm = getFragmentManager();
        try {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.executePendingTransactions();
        } catch (Exception ex) {}

        FragmentTransaction xTransaction = fm.beginTransaction();
        PreferencesFrag placeDetailsFragment = new PreferencesFrag();
        xTransaction.replace(R.id.fragHolder, placeDetailsFragment);
        xTransaction.commit();

        mActionBar = getActivity().getActionBar();
        mActionBar.setIcon(R.drawable.ic_settings);
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        boolean canback = false;
        try {
            canback = getFragmentManager().getBackStackEntryCount() > 0;
        } catch (Exception ex) {};
        if (canback) {
            mainActivity.getDrawerToggle().setDrawerIndicatorEnabled(false);
        } else {
            mainActivity.getDrawerToggle().setDrawerIndicatorEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
