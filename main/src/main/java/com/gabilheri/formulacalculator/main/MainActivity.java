package com.gabilheri.formulacalculator.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.gabilheri.formulacalculator.main.adapters.NavDrawerListAdapter;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.fragments.CalculatorFragment;
import com.gabilheri.formulacalculator.main.fragments.CardsFormulasFragment;
import com.gabilheri.formulacalculator.main.fragments.FragmentThemeCreator;
import com.gabilheri.formulacalculator.main.fragments.LogFragment;
import com.gabilheri.formulacalculator.main.fragments.SettingsFragment;
import com.gabilheri.formulacalculator.main.fragments.ThemesFragment;
import com.gabilheri.formulacalculator.main.fragments.UnitConverterFragment;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.navDrawer.NavDrawerItem;
import com.gabilheri.formulacalculator.main.tests.TestFragment;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May 2014.
 */

public class MainActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /**
     * DEBUG VARIABLE IN CONTROL OF DEBUG FOR THE ENTIRE APP
     */
    public static final boolean DEBUG = true;

    public static final String LOG_TAG = "MAIN-ACTIVITY";

    /**
     * Google Plus Sign-In Variables
     */
    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleServices;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;

    /**
     * Drawer G+ Signed-IN Stuff
     */
    private static final int PROFILE_PIC_SIZE = 250;
    private ImageView imageProfilePic;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // Nav Drawer and app Title
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    //Constants for the Fragments
    public static final int CALCULATOR_FRAG = 0;
    public static final int LOG_FRAG = 1;
    public static final int FORMULAS_FRAG = 2;
    public static final int UNIT_CONVERTER_FRAG = 3;
    public static final int THEMES_FRAG = 4;
    public static final int SETTINGS_FRAG = 5;
    public static final int ABOUT_FRAG = 6;
    public static final int GOOGLE_PLUS = 7;
    public static final int DEBUG_FRAG = 8;
    public static final int THEME_CREATOR = 15;

    // Shared Pref stuff
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String CURRENT_THEME = "current_theme";

    // Nav Drawer Elements
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter navAdapter;
    private Fragment activeFragment;
    private FragmentWithKeypad keypadFragment;
    private Intent mIntent;

    private DatabaseHelper mHelper;
    private SystemBarTintManager tintManager;

    //private RevMob revMob;
    private static String APPLICATION_ID = "537d798281d7eed52d9822b7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIntent = getIntent();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        mPreferences = getSharedPreferences(CURRENT_THEME, MODE_PRIVATE);
        mEditor = mPreferences.edit();

        mHelper = new DatabaseHelper(this.getApplicationContext());
        mPreferences = getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);

        if(mHelper.getAllThemesForUser(null).size() == 0) {
            Theme defaultTheme = new Theme();
            defaultTheme.setUsername(null);
            defaultTheme.setThemeName(Theme.DEFAULT_THEME);
            defaultTheme.setThemeType(Theme.THEME_SYSTEM);
            defaultTheme.setPrimaryColor(getResources().getColor(R.color.def_button));
            defaultTheme.setPrimaryHighlightColor(getResources().getColor(R.color.def_button_pressed));
            defaultTheme.setPrimaryButtonTextColor(getResources().getColor(R.color.def_button_text));
            defaultTheme.setSecondaryColor(getResources().getColor(R.color.button_2));
            defaultTheme.setSecondaryHighlightColor(getResources().getColor(R.color.def_button_pressed));
            defaultTheme.setSecondaryButtonTextColor(getResources().getColor(R.color.def_button_text));
            defaultTheme.setDisplayColor(getResources().getColor(R.color.display_color));
            defaultTheme.setDisplayTextColor(getResources().getColor(R.color.def_button_text));
            defaultTheme.setSelectedColor(getResources().getColor(R.color.light_orange));
            mHelper.createTheme(defaultTheme);

            Theme secondaryTheme = new Theme();
            secondaryTheme.setUsername(null);
            secondaryTheme.setThemeName(Theme.SECONDARY_THEME);
            secondaryTheme.setThemeType(Theme.THEME_SYSTEM);
            secondaryTheme.setPrimaryColor(getResources().getColor(R.color.green));
            secondaryTheme.setPrimaryHighlightColor(getResources().getColor(R.color.green_highlight));
            secondaryTheme.setPrimaryButtonTextColor(getResources().getColor(R.color.def_button_text));
            secondaryTheme.setSecondaryColor(getResources().getColor(R.color.button_2));
            secondaryTheme.setSecondaryHighlightColor(getResources().getColor(R.color.green_highlight));
            secondaryTheme.setSecondaryButtonTextColor(getResources().getColor(R.color.def_button_text));
            secondaryTheme.setDisplayColor(getResources().getColor(R.color.display_color2));
            secondaryTheme.setDisplayTextColor(getResources().getColor(R.color.def_button_text));
            secondaryTheme.setSelectedColor(getResources().getColor(R.color.def_button));
            mHelper.createTheme(secondaryTheme);

            mEditor.putString(CURRENT_THEME, Theme.DEFAULT_THEME);
            mEditor.apply();
        }

        Theme currentTheme = Utils.getCurrentTheme(this);

        // Enabling action bar app and Icon , and behaving it as a toggle button.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(new ColorDrawable(currentTheme.getDisplayColor()));
        getActionBar().setHomeButtonEnabled(true);


        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(currentTheme.getDisplayColor());

        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        // load nav drawer menu items
        navMenuTitles = getResources().getStringArray(R.array.drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.drawer_icons);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navDrawerItems = new ArrayList<>();

        /**
         * Builder for the Google+ Sign IN
         */
        mGoogleServices = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();

        if(mGoogleServices.isConnected()) {
            navMenuTitles[7] = "Sign Out";
        }

        for(int i = 0; i < navMenuTitles.length; i++) {

            if(i == navMenuTitles.length - 1 && !DEBUG) {
                navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
            } else {
                navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
            }

        }

        // Recycle the typed array to avoid wast resources
        // Recycle tells the garbage collector that the array is ready to be collected
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new DrawerListener());

        // setting the nav drawer list adapter
        navAdapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(navAdapter);


        /**
         * Handles the Drawer close and drawer open.
         */
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_navigation_drawer, // Nav drawer icon
                R.string.app_name, // Nav Drawer open - description for accessibility
                R.string.app_name // Nav drawer close.
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // Calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mTitle);
                // Calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if(savedInstanceState == null) {
            displayView(0, null);
        }

        //revMob = RevMob.start(this, APPLICATION_ID);
        //revMob.setTestingMode(RevMobTestingMode.WITH_ADS);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //RevMobBanner banner = revMob.createBanner(this);
        //ViewGroup group = (ViewGroup) findViewById(R.id.banner);
        //group.addView(banner);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Inner class to for the Drawer Layout listener
     */
    private class DrawerListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayView(position, null);
        }
    }

    /**
     * Displaying fragment view for selected nav drawer list item
     *
     */
    public void displayView(int position, Bundle fragBundle) {
        // update the main content by replacing fragments
        Theme currentTheme = Utils.getCurrentTheme(this);
        activeFragment = null;
        switch (position) {
            case CALCULATOR_FRAG:

                getActionBar().setBackgroundDrawable(new ColorDrawable(currentTheme.getDisplayColor()));
                tintManager.setTintColor(currentTheme.getDisplayColor());
                activeFragment = new CalculatorFragment();

                if(fragBundle != null) {
                    activeFragment.setArguments(fragBundle);
                }
                keypadFragment = (CalculatorFragment) activeFragment;
                break;
            case FORMULAS_FRAG:
                getActionBar().setBackgroundDrawable(new ColorDrawable(currentTheme.getDisplayColor()));
                tintManager.setTintColor(currentTheme.getDisplayColor());
                activeFragment = new CardsFormulasFragment();
                break;
            case LOG_FRAG:
                activeFragment = new LogFragment();
                getActionBar().setBackgroundDrawable(new ColorDrawable(currentTheme.getSecondaryColor()));
                tintManager.setTintColor(currentTheme.getSecondaryColor());
                break;
            case UNIT_CONVERTER_FRAG:
                getActionBar().setBackgroundDrawable(new ColorDrawable(currentTheme.getDisplayColor()));
                tintManager.setTintColor(currentTheme.getDisplayColor());
                activeFragment = new UnitConverterFragment();
                keypadFragment = (UnitConverterFragment) activeFragment;
                break;
            case SETTINGS_FRAG:
                activeFragment = new SettingsFragment();
                break;
            case THEMES_FRAG:
                getActionBar().setBackgroundDrawable(new ColorDrawable(currentTheme.getPrimaryColor()));
                tintManager.setTintColor(currentTheme.getPrimaryColor());
                activeFragment = new ThemesFragment();
                break;
            case GOOGLE_PLUS:
                if(mGoogleServices.isConnected()) {
                    signOutFromGplus();
                    //navDrawerItems.get(7).setTitle("Sign In");
                } else {
                    signInWithGPlus();
                    //navDrawerItems.get(7).setTitle("Sign Out");
                }
                break;
            case DEBUG_FRAG:
                activeFragment = new TestFragment();
                break;
            case THEME_CREATOR:
                activeFragment = new FragmentThemeCreator();
                keypadFragment = (FragmentThemeCreator) activeFragment;
                break;
            default:
                break;
        }

        if(position != GOOGLE_PLUS) {
            if (activeFragment != null) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, activeFragment).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                if(position == THEME_CREATOR) {
                    setTitle("Theme Creator");
                } else {
                    setTitle(navMenuTitles[position]);
                }
            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }
        } else {
            mDrawerList.setItemChecked(position, false);
            //navDrawerItems.get(7).setTitle("Sign Out");
            if(!mGoogleServices.isConnected()) {
                navDrawerItems.get(7).setTitle("Sign In");
            } else {
                navDrawerItems.get(7).setTitle("Sign Out");
            }
        }
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstance has occurred
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     *
     * @param view
     */
    public void handleKeypad(View view) {
        keypadFragment.handleKeypad(view);
    }

    public void clearDisplay() {
        ((CalculatorFragment) activeFragment).clearDisplay();
    }

    /**
     *
     * @param view
     */
    public void handleVar(View view) {
        ((CalculatorFragment) keypadFragment).handleVar(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleServices.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleServices.isConnected()) {
            mGoogleServices.disconnect();
        }
    }

    private void resolveSignInError() {
        if(mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException ex) {
                mIntentInProgress = false;
                mGoogleServices.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;
        getProfileInformation();
        updateUI(true);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleServices.connect();
        updateUI(false);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(!connectionResult.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
            return;
        }

        if(!mIntentInProgress) {
            // Store the result for later usage
            mConnectionResult = connectionResult;

            if(mSignInClicked) {
                // The user has already clicked sign-in so we attempt to resolve all errors
                // until the user is signed in, or cancel.
                resolveSignInError();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_SIGN_IN) {
            if(resultCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if(!mGoogleServices.isConnecting()) {
                mGoogleServices.connect();
            }
        }
    }

    private void updateUI(boolean updateUI) {
        Log.i(LOG_TAG, "Updating UI....");
        if(updateUI) {
            //navDrawerItems.get(7).setTitle("Sign Out");
            navMenuTitles[7] = "Sign Out";
            Log.i(LOG_TAG, "Updating UI.... TRUE");
        } else {
            //navDrawerItems.get(7).setTitle("Sign In");
            navMenuTitles[7] = "Sign In";
            Log.i(LOG_TAG, "Updating UI.... FALSE!");
        }
    }

    /**
     * Sign In with G-Plus
     */
    private void signInWithGPlus() {
        if(!mGoogleServices.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }

    /**
     *  Sign Out with GPLUS
     */
    private void signOutFromGplus() {
        if(mGoogleServices.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleServices);
            mGoogleServices.disconnect();
            mGoogleServices.connect();
            updateUI(false);
        }
    }

    /**
     * Revoce access from Gplus
     */
    private void revokeGplusAccess() {
        if(mGoogleServices.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleServices);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleServices)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            Log.i(LOG_TAG, "User access revoked");
                            mGoogleServices.connect();
                            updateUI(false);
                        }
                    });
        }
    }

    /**
     * Gets the information for the current signed in User.
     *
     * @return
     *      A HasHMap with the information for the signed in user
     */
    public HashMap<String, Object> getProfileInformation() {
        HashMap<String, Object> userProfile = new HashMap<>();
        try {
            if(Plus.PeopleApi.getCurrentPerson(mGoogleServices) != null) {
                Person mCurrentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleServices);
                String personName = mCurrentPerson.getDisplayName();
                String personPhotoUrl = mCurrentPerson.getImage().getUrl();
                String personGooglePlusProfile = mCurrentPerson.getUrl();
                final String personEmail = Plus.AccountApi.getAccountName(mGoogleServices);

                Log.i(LOG_TAG, "Person Name: " + personName);
                Log.i(LOG_TAG, "Person Photo URL: " + personPhotoUrl);
                Log.i(LOG_TAG, "Person G+ Profile: " + personGooglePlusProfile);
                Log.i(LOG_TAG, "Person E-Mail: " + personEmail);

                userProfile.put("personName", personName);
                userProfile.put("personPhotoUrl", personPhotoUrl);
                userProfile.put("personG+", personGooglePlusProfile);
                userProfile.put("personEmail", personEmail);

                // by default the profile url gives 50x50 px image only
                // we can replace the value with whatever dimension we 2want by
                // replacing sz=X
                //personPhotoUrl = personPhotoUrl.substring(0, personPhotoUrl.length() -2) + PROFILE_PIC_SIZE;
                //new LoadProfileImage(imageProfilePic).execute(personPhotoUrl);
            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return userProfile;
    }
}