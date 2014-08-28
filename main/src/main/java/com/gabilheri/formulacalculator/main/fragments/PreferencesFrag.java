package com.gabilheri.formulacalculator.main.fragments;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import com.gabilheri.formulacalculator.main.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/16/14.
 */
public class PreferencesFrag extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private static String LOG_TAG = "PreferencesFrag";
    private boolean mBindingPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        bindPreferenceSummaryToValue(findPreference(getResources().getString(R.string.calc_layout_key)));
        bindPreferenceSummaryToValue(findPreference(getResources().getString(R.string.tax_key)));
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        super.onPreferenceTreeClick(preferenceScreen, preference);

        if (preference.getKey().equals(getActivity().getResources().getString(R.string.par_key))) {
            // Display the fragment as the main content.
            // If the user has clicked on a preference screen, set up the action bar
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.alpha_in, R.animator.alpha_out, R.animator.alpha_in, R.animator.alpha_out ).replace(R.id.fragHolder, new ParenthesisPrefs())
                    .addToBackStack(null).commit();
            return true;
        }
        return false;
    }

    /**
     * Attaches a listener so the summary is always updated with the preference value.
     * Also fires the listener once, to initialize the summary (so it shows up before the value
     * is changed.)
     */
    private void bindPreferenceSummaryToValue(Preference preference) {
        mBindingPreference = true;

        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));

        mBindingPreference = false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {

            if(preference.getKey().equals(getString(R.string.tax_key))) {
                preference.setSummary(stringValue + "%");
            } else {
                // For other preferences, set the summary to the value's simple string representation.
                preference.setSummary(stringValue);
            }
        }
        return true;
    }
}
