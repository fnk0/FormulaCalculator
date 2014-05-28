package com.gabilheri.formulacalculator.main;

import android.app.Fragment;
import android.app.FragmentManager;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.gabilheri.formulacalculator.main.adapters.NavDrawerListAdapter;
import com.gabilheri.formulacalculator.main.fragments.MainActivityFragment;
import com.gabilheri.formulacalculator.main.fragments.SettingsFragment;
import com.gabilheri.formulacalculator.main.navDrawer.NavDrawerItem;
import com.revmob.RevMob;
import com.revmob.RevMobTestingMode;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.ads.fullscreen.RevMobFullscreen;

import java.util.ArrayList;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May 2014.
 */

public class MainActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // Nav Drawer and app Title
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    // Nav Drawer Elements
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter navAdapter;
    private Fragment activeFragment;
    private MainActivityFragment mainFragment;
    private RevMob revMob;
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
            displayView(0);
        }

        revMob = RevMob.start(this, APPLICATION_ID);
        revMob.setTestingMode(RevMobTestingMode.WITH_ADS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RevMobBanner banner = revMob.createBanner(this);
        ViewGroup group = (ViewGroup) findViewById(R.id.banner);
        group.addView(banner);
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
            displayView(position);
        }
    }

    /**
     * Displaying fragment view for selected nav drawer list item
     *
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        activeFragment = null;
        switch (position) {
            case 0:
                activeFragment = new MainActivityFragment();
                mainFragment = (MainActivityFragment) activeFragment;
                break;
            case 1:
                activeFragment = new SettingsFragment();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }

        if (activeFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, activeFragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
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
        mainFragment.handleKeypad(view);
    }

    /**
     *
     * @param view
     */
    public void evaluateExpression(View view) {
        mainFragment.evaluateExpression(view);
    }

    /**
     *
     * @param view
     */
    public void clearDisplay(View view) {
        mainFragment.clearDisplay();
    }

    /**
     *
     * @param view
     */
    public void deleteFromDisplay(View view) {
        mainFragment.deleteFromDisplay();
    }

    /**
     *
     * @param view
     */
    public void showDialog(View view) {
        mainFragment.handleKeypad(view);
    }

    /**
     *
     * @param view
     */
    public void handleVar(View view) {
        mainFragment.handleVar(view);
    }
}
