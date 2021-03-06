package com.gabilheri.formulacalculator.main.fragments.preferences;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.utils.FontsOverride;
import com.gabilheri.formulacalculator.main.utils.Utils;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 9/1/14.
 */
public class FontSizePrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private boolean mBindingPreference;
    private static String LOG_TAG = "FontSizePref";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.font_size_prefs);
        bindPreferenceSummaryToValue(findPreference(getResources().getString(R.string.buttons_font_size_key)));
        bindPreferenceSummaryToValue(findPreference(getResources().getString(R.string.display_font_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.font_family_key)));
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
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String stringValue = newValue.toString();
        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }

            if(preference.getKey().equals(getString(R.string.font_family_key))) {
                //Log.i(LOG_TAG, "Font family changed!!");
                FontsOverride.replaceFont("SERIF", Utils.getTypeface(getActivity(), stringValue));

            }

        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i(LOG_TAG, "On Resume is called!");
    }
}
