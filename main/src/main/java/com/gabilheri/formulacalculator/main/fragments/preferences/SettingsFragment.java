package com.gabilheri.formulacalculator.main.fragments.preferences;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.fragments.XmlFragment;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/14.
 */
public class SettingsFragment extends XmlFragment implements FragmentManager.OnBackStackChangedListener {

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
        } catch (Exception ex) {}
        if (canback) {
            mainActivity.getDrawerToggle().setDrawerIndicatorEnabled(false);
            mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mainActivity.getSupportActionBar().setHomeButtonEnabled(true);
            mainActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            mainActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_arrow_back);
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
