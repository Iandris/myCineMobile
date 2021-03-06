package com.youngmike.mycinemobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youngmike.mycinemobile.entity.UserMovieLink;
import com.youngmike.mycinemobile.entity.Wishlist;
import com.youngmike.mycinemobile.fragment.FriendsFragment;
import com.youngmike.mycinemobile.fragment.LibraryFragment;
import com.youngmike.mycinemobile.fragment.LibraryMovieDetailFragment;
import com.youngmike.mycinemobile.fragment.LoginFragment;
import com.youngmike.mycinemobile.fragment.MainScreenFragment;
import com.youngmike.mycinemobile.fragment.PreferencesFragment;
import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.fragment.WishlistFragment;
import com.youngmike.mycinemobile.fragment.WishlistMovieDetailFragment;
import com.youngmike.mycinemobile.util.IntentIntegrator;
import com.youngmike.mycinemobile.util.MyDBHandler;
import com.youngmike.mycinemobile.util.UPCLookup;

/**
 * MainActivity class for MyCineMobile
 * Author: Mike Young
 */

public class MainActivity extends AppCompatActivity implements
        FriendsFragment.OnFriendItemSelected,
        WishlistFragment.OnWishListItemSelected,
        LibraryFragment.OnLibraryItemSelected {

    private ActionBar mActionBar;
    private CoordinatorLayout mCoordinatorLayout;
    private int mDestination;
    private MyDBHandler dbHandler;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mNavigationOptions;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    public boolean mIsLoggedIn = false;
    private FloatingActionButton fab;
    private String upc;

    /**
     * onCreate method override, establishes coordinator/drawer layouts and uses fragment manager to
     * swap fragments in/out
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (dbHandler == null) {
            dbHandler = new MyDBHandler(this, null, null, 1);
        }


        mActionBar = getSupportActionBar();

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        mIsLoggedIn = sharedPreferences.getBoolean("Remember_Login", false);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mNavigationOptions = getResources().getStringArray(R.array.drawer_options);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mNavigationOptions));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        mActionBar.setDisplayHomeAsUpEnabled(mIsLoggedIn);
        mActionBar.setHomeButtonEnabled(mIsLoggedIn);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            /**
             * onDrawerClosed method, occurs when drawer close occurs, resets title of action bar
             * and invalidates options menu to force redraw
             *
             * @param view
             */
            public void onDrawerClosed(View view) {
                updateActionBar(mTitle);
            }

            /**
             * onDrawerOpened method, occurs when drawer opens occurs, resets title of action bar
             * and invalidates options menu to force redraw
             *
             * @param drawerView
             */
            public void onDrawerOpened(View drawerView) {
                updateActionBar(mDrawerTitle);
            }
        };

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mDestination) {
                    case 0:
                        break;
                    case 1:
                        launchBarcodeScan();
                        break;
                    case 2:
                        Snackbar.make(view, "Future Implementation of add Friend", Snackbar
                            .LENGTH_LONG)
                            .setAction("Action", null).show();
                        break;
                    case 3:
                        launchBarcodeScan();
                        break;
                    default:
                        break;
                }
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            if (mIsLoggedIn) {
                selectItem(0);
                invalidateOptionsMenu();
            } else {
                if (sharedPreferences.getString("Username", "").equals("")) {
                    selectItem(4);

                } else {
                    LoginFragment firstFragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container, firstFragment).commit();
                }
            }
        }

    }

    /**
     * updateActionbar requires CharSequence, updates title for Actionbar and invalidates options menu
     * @param mDrawerTitle
     */
    private void updateActionBar(CharSequence mDrawerTitle) {
        mActionBar.setTitle(mDrawerTitle);
        invalidateOptionsMenu();
    }

    /**
     * DrawerItemClickListener class for MyCineMobile
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        /**
         * onItemClick method override - handles the click to list items
         * @param parent
         * @param view
         * @param position
         * @param id
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mDestination = position;
            selectItem(position);
        }
    }

    /**
     * onPrepareOptionsMenu method override - occurs on menu invalidate, redraws the drawer and
     * shows/hides the floating action button
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        FriendsFragment friendFrag = (FriendsFragment) getSupportFragmentManager().findFragmentByTag("FRIENDS_FRAGMENT");
        WishlistFragment wishlistFrag = (WishlistFragment)getSupportFragmentManager().findFragmentByTag("WISHLIST_FRAGMENT");
        LibraryFragment libraryFrag = (LibraryFragment)getSupportFragmentManager().findFragmentByTag("LIBRARY_FRAGMENT");

        if (drawerOpen) {
            fab.setVisibility(View.INVISIBLE);
        } else if (!drawerOpen) {
            if (friendFrag != null && friendFrag.isVisible()){
                fab.setVisibility(View.VISIBLE);
            } else if (wishlistFrag != null && wishlistFrag.isVisible()){
                fab.setVisibility(View.VISIBLE);
            } else if (libraryFrag != null && libraryFrag.isVisible()) {
                fab.setVisibility(View.VISIBLE);
            } else {
                fab.setVisibility(View.INVISIBLE);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * selectItem method - requires integer for position of item clicked, swaps fragments based on
     * selection
     * @param position
     */
    public void selectItem(int position) {
        mActionBar.setDisplayHomeAsUpEnabled(mIsLoggedIn);
        mActionBar.setHomeButtonEnabled(mIsLoggedIn);
        Fragment newFragment = null;
        String mFragmentTag = "";

        switch (position) {
            case 0:
                MainScreenFragment mainFrag = (MainScreenFragment)getSupportFragmentManager().findFragmentByTag("MAIN_FRAGMENT");

                if (mainFrag == null) {
                    newFragment = new MainScreenFragment();
                    mFragmentTag = "MAIN_FRAGMENT";
                } else {
                    newFragment = mainFrag;
                    mFragmentTag = "MAIN_FRAGMENT";
                }

                break;
            case 1:
                LibraryFragment libraryFrag = (LibraryFragment)getSupportFragmentManager().findFragmentByTag("LIBRARY_FRAGMENT");

                if (libraryFrag == null) {
                    newFragment = new LibraryFragment();
                    mFragmentTag = "LIBRARY_FRAGMENT";
                } else {
                    newFragment = libraryFrag;
                    mFragmentTag = "LIBRARY_FRAGMENT";
                }
                break;
            case 2:
                FriendsFragment friendFrag = (FriendsFragment) getSupportFragmentManager().findFragmentByTag("FRIENDS_FRAGMENT");

                if (friendFrag == null) {

                    newFragment = new FriendsFragment();
                    mFragmentTag = "FRIENDS_FRAGMENT";
                } else {
                    newFragment = friendFrag;
                    mFragmentTag = "FRIENDS_FRAGMENT";
                }
                break;
            case 3:
                WishlistFragment wishlistFrag = (WishlistFragment)getSupportFragmentManager().findFragmentByTag("WISHLIST_FRAGMENT");


                if (wishlistFrag == null) {
                    newFragment = new WishlistFragment();
                    mFragmentTag = "WISHLIST_FRAGMENT";
                } else {
                    newFragment = wishlistFrag;
                    mFragmentTag = "WISHLIST_FRAGMENT";
                }
                break;
            case 4:
                PreferencesFragment prefFrag = (PreferencesFragment) getSupportFragmentManager().findFragmentByTag("PREFERENCES_FRAGMENT");

                if (prefFrag == null) {
                    newFragment = new PreferencesFragment();
                    mFragmentTag = "PREFERENCES_FRAGMENT";
                } else {
                    newFragment = prefFrag;
                    mFragmentTag = "PREFERENCES_FRAGMENT";
                }
                break;
            case 5:
                mIsLoggedIn = false;

                savePreferences("Remember_Login", mIsLoggedIn);

                mActionBar.setDisplayHomeAsUpEnabled(mIsLoggedIn);
                mActionBar.setHomeButtonEnabled(mIsLoggedIn);
                newFragment = new LoginFragment();
                mFragmentTag = "LOGIN_FRAGMENT";
                break;
        }

        if (newFragment != null) {
            swapFragment(newFragment, mFragmentTag);
        }

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavigationOptions[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
        invalidateOptionsMenu();
    }

    /**
     * launchBarcodeScan creates the intent for the zxing barcode scanner
     */
    protected void launchBarcodeScan() {
         IntentIntegrator integrator = new IntentIntegrator(this);
         integrator.initiateScan(mDestination);
    }

    /**
     * swapFragment method, requires Fragment as parameter, uses Support Fragment Manager to swap
     * @param newFragment
     */
    private void swapFragment(Fragment newFragment, String mFragTag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment, mFragTag);
        transaction.addToBackStack(null);

        transaction.commitAllowingStateLoss();
    }

    /**
     * savePreferences method, stores key value pairs for shared preferences
     * @param key
     * @param value
     */
    private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * setTitle override - updates the title text on action bar
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        mActionBar.setTitle(mTitle);
    }

    /**
     * onCreateOptionsMenu method override - inflates the menu layout for menu in action bar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * onOptionsItemSelected method override - handles selection of menu items
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * onFriendSelected method override - handles logic when a friend is selected from the list
     * @param position
     */
    @Override
    public void onFriendSelected(int position) {
        Toast.makeText(this, "Future implementaion of Edit/Show Friend Details", Toast.LENGTH_LONG).show();
    }

    /**
     * onWishlistItemSelected handles the item selection from Wishlist list
     * @param position
     */
    @Override
    public void onWishlistItemSelected(int position){
        switchToMovieDetail(false, position);
    }

    /**
     * onLibraryItemSelected handles the item selection from library list
     * @param position
     */
    @Override
    public void onLibraryItemSelected(int position){
        switchToMovieDetail(true, position);
    }

    /**
     * onPostCreate method override - syncs the toggle state for drawer after restore instance state
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /**
     * onConfigurationChanged method override - passes configuration chagnes to the drawer
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * swaps out the library or wishlist movie detail fragment
     * @param library
     * @param position
     */
    public void switchToMovieDetail(Boolean library, int position) {

        TextView movieTitle = (TextView) findViewById(R.id.txt_movie_title);

            Fragment newFragment;
            if (movieTitle == null) {
                if (library) {
                    newFragment = new LibraryMovieDetailFragment();
                } else {
                    newFragment = new WishlistMovieDetailFragment();
                }

                Bundle data = new Bundle();
                data.putInt("listID", position);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
    }

    /**
     * onActivityResult occurs after intent for barcode scan, determines next action
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            if (resultCode == Activity.RESULT_OK) {
                upc = intent.getStringExtra("SCAN_RESULT");
                mDestination = requestCode;
                new UPCLookupTask().execute(upc);
            }
    }

    /**
     * getter for local dbhandler object
     * @return
     */
    public MyDBHandler getDbHandler() {
        return dbHandler;
    }

    /**
     * UPCLookupTask class, async task call to UPCLookup.java and executes in separate thread
     */
    private class UPCLookupTask extends AsyncTask<String,Void,UserMovieLink> {
        @Override
        protected UserMovieLink doInBackground(String... something) {
            return new UPCLookup().checkCode(upc);
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            // showing a progress bar in the user interface
            super.onPreExecute();
        }

        /**
         * Runs on the UI thread after {@link #publishProgress} is invoked.
         * The specified values are the values passed to {@link #publishProgress}.
         *
         * @param values The values indicating progress.
         * @see #publishProgress
         * @see #doInBackground
         */
        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }

        /**
         * onPostExecute method runs after async thread completes, used to update ui with changes
         * @param items
         */
        @Override
        protected void onPostExecute(UserMovieLink items) {
            if (items != null) {

                if (mDestination == 1) {

                    dbHandler.addUserMovieLink(items);


                    LibraryFragment library = (LibraryFragment) getSupportFragmentManager().findFragmentByTag("LIBRARY_FRAGMENT");
                    library.mLibrarylistAdapter.notifyDataSetChanged();

                } else if (mDestination == 3) {
                    Wishlist list = new Wishlist();
                    list.setMovieid(items.getMovieid());
                    list.setUserid(1);
                    list.setMovieTitle(items.getMovieTitle());
                    list.setMovieSynopsis(items.getMovieSynopsis());
                    list.setImagePath(items.getImagePath());

                    dbHandler.addWishList(list);


                    WishlistFragment wishlist = (WishlistFragment) getSupportFragmentManager().findFragmentByTag("WISHLIST_FRAGMENT");
                    wishlist.mWishlistAdapter.notifyDataSetChanged();

                }
                Toast.makeText(getApplicationContext(), items.getMovieTitle() + " added/updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "No title found for scanned barcode", Toast.LENGTH_LONG).show();
            }
            selectItem(0);
        }
    }
}
