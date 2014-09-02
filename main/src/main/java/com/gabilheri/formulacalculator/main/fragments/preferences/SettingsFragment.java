package com.gabilheri.formulacalculator.main.fragments.preferences;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.fragments.XmlFragment;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithDrawable;
import com.gabilheri.formulacalculator.main.utils.Utils;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/14.
 */
public class SettingsFragment extends XmlFragment implements FragmentManager.OnBackStackChangedListener, FragmentWithDrawable {

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

    /**
     * Gets the drawable for this fragment
     * @return
     */
    @Override
    public Drawable getDrawable() {
        return getResources().getDrawable(R.drawable.ic_settings);
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
                getActivity().getActionBar().setIcon(getResources().getDrawable(R.drawable.ic_settings));
                getActivity().getActionBar().setTitle(getString(R.string.settings_title));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
