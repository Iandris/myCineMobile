package com.youngmike.mycinemobile.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youngmike.mycinemobile.fragment.FriendsFragment;
import com.youngmike.mycinemobile.fragment.LoginFragment;
import com.youngmike.mycinemobile.fragment.MainScreenFragment;
import com.youngmike.mycinemobile.fragment.MovieDetailFragment;
import com.youngmike.mycinemobile.fragment.PreferencesFragment;
import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.util.MyDBHandler;

public class MainActivity extends AppCompatActivity implements
        FriendsFragment.OnFriendItemSelected {
    private MyDBHandler dbHandler;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mNavigationOptions;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    public boolean mIsLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        mIsLoggedIn = sharedPreferences.getBoolean("Remember_Login", false);

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(mIsLoggedIn);
        getSupportActionBar().setHomeButtonEnabled(mIsLoggedIn);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (dbHandler == null) {
            dbHandler = new MyDBHandler(this, null, null, 1);
        }

        LoginFragment firstFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();

    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //TODO replace R.id.action_movieDetail with future reference to barcode reader
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        if (!drawerOpen && !mIsLoggedIn) {
            menu.findItem(R.id.action_movieDetail).setVisible(false);
        } else if (!drawerOpen || mIsLoggedIn) {
            menu.findItem(R.id.action_movieDetail).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    public void selectItem(int position) {
        // update the main content by replacing fragments
        getSupportActionBar().setDisplayHomeAsUpEnabled(mIsLoggedIn);
        getSupportActionBar().setHomeButtonEnabled(mIsLoggedIn);

        if (position == 0) {
            //TODO try auto-login when developed - on fail redirect to login fragment
            MainScreenFragment firstFragment = new MainScreenFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, firstFragment).commit();

        } else if (position == 1) {
            //TODO add fragment for my library
            Toast.makeText(getApplicationContext(), "Future Implementation of My Friends", Toast.LENGTH_LONG).show();

        } else if (position ==2) {
            TextView friendFrag = (TextView) findViewById(R.id.txtName);

            if (friendFrag == null) {

                FriendsFragment newFragment = new FriendsFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        } else if (position == 3) {
            //TODO add fragment for my wishlist
            Toast.makeText(getApplicationContext(), "Future Implementation of My Wishlist", Toast.LENGTH_LONG).show();

        } else if (position == 4) {
            TextView prefFrag = (TextView) findViewById(R.id.txt_settings);

            if (prefFrag == null) {
                // Create fragment and give it an argument for the selected article
                PreferencesFragment newFragment = new PreferencesFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }

        } else if (position == 5) {
            //TODO create logout method
            mIsLoggedIn = false;
            // update the main content by replacing fragments
            getSupportActionBar().setDisplayHomeAsUpEnabled(mIsLoggedIn);
            getSupportActionBar().setHomeButtonEnabled(mIsLoggedIn);
            LoginFragment firstFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, firstFragment).commit();

        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mNavigationOptions[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.action_movieDetail:
                TextView movieTitle = (TextView) findViewById(R.id.txt_movie_title);

                if (movieTitle == null) {
                    MovieDetailFragment newFragment = new MovieDetailFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFriendSelected(int position) {
            //TODO add some functionality to selecting friend

    }

    /** When using the ActionBarDrawerToggle, you must call it during
    * onPostCreate() and onConfigurationChanged()...
     * */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
