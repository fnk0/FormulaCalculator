package com.gabilheri.formulacalculator.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.gabilheri.formulacalculator.main.adapters.NavDrawerListAdapter;
import com.gabilheri.formulacalculator.main.fragments.CalculatorFragment;
import com.gabilheri.formulacalculator.main.fragments.CardsFormulasFragment;
import com.gabilheri.formulacalculator.main.fragments.FragmentThemeCreator;
import com.gabilheri.formulacalculator.main.fragments.LogFragment;
import com.gabilheri.formulacalculator.main.fragments.SettingsFragment;
import com.gabilheri.formulacalculator.main.interfaces.FragmentWithKeypad;
import com.gabilheri.formulacalculator.main.navDrawer.NavDrawerItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.util.ArrayList;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May 2014.
 */

public class MainActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

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
    public static final int FORMULAS_FRAG = 1;
    public static final int LOG_FRAG = 2;
    public static final int SETTINGS_FRAG = 3;
    public static final int THEME_CREATOR = 4;
    public static final int GOOGLE_PLUS = 7;

    // Nav Drawer Elements
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter navAdapter;
    private Fragment activeFragment;
    private FragmentWithKeypad keypadFragment;
    //private RevMob revMob;
    private static String APPLICATION_ID = "537d798281d7eed52d9822b7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        // load nav drawer menu items
        navMenuTitles = getResources().getStringArray(R.array.drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.drawer_icons);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navDrawerItems = new ArrayList<>();

        for(int i = 0; i < navMenuTitles.length; i++) {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
        }

        // Recycle the typed array to avoid wast resources
        // Recycle tells the garbage collector that the array is ready to be collected
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new DrawerListener());

        // setting the nav drawer list adapter
        navAdapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(navAdapter);

        // Enabling action bar app and Icon , and behaving it as a toggle button.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        /**
         * Handles the Drawer close and drawer open.
         */
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, // Nav drawer icon
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

        /**
         * Builder for the Google+ Sign IN
         */
        mGoogleServices = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();

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
        activeFragment = null;
        switch (position) {
            case CALCULATOR_FRAG:
                activeFragment = new CalculatorFragment();

                if(fragBundle != null) {
                    activeFragment.setArguments(fragBundle);
                }
                keypadFragment = (CalculatorFragment) activeFragment;
                break;
            case FORMULAS_FRAG:
                activeFragment = new CardsFormulasFragment();
                break;
            case LOG_FRAG:
                activeFragment = new LogFragment();
                break;
            case SETTINGS_FRAG:
                activeFragment = new SettingsFragment();
                break;
            case THEME_CREATOR:
                activeFragment = new FragmentThemeCreator();
                keypadFragment = (FragmentThemeCreator) activeFragment;
                break;
            case 5:
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
            default:
                break;
        }

        if(position != GOOGLE_PLUS) {
            if (activeFragment != null) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, activeFragment).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                setTitle(navMenuTitles[position]);

            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
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

    private void getProfileInformation() {
        try {
            if(Plus.PeopleApi.getCurrentPerson(mGoogleServices) != null) {
                Person mCurrentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleServices);
                String personName = mCurrentPerson.getDisplayName();
                String personPhotoUrl = mCurrentPerson.getImage().getUrl();
                String PersonGooglePlusProfile = mCurrentPerson.getUrl();
                final String personEmail = Plus.AccountApi.getAccountName(mGoogleServices);


                // by default the profile url gives 50x50 px image only
                // we can replace the value with whatever dimension we 2want by
                // replacing sz=X
                personPhotoUrl = personPhotoUrl.substring(0, personPhotoUrl.length() -2) + PROFILE_PIC_SIZE;
                //new LoadProfileImage(imageProfilePic).execute(personPhotoUrl);
            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}