package com.youngmike.mycinemobile.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.youngmike.mycinemobile.entity.User;
import com.youngmike.mycinemobile.entity.UserMovieLink;
import com.youngmike.mycinemobile.entity.Wishlist;
import com.youngmike.mycinemobile.fragment.FriendsFragment;
import com.youngmike.mycinemobile.fragment.LibraryFragment;
import com.youngmike.mycinemobile.fragment.LoginFragment;
import com.youngmike.mycinemobile.fragment.MainScreenFragment;
import com.youngmike.mycinemobile.fragment.MovieDetailFragment;
import com.youngmike.mycinemobile.fragment.PreferencesFragment;
import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.fragment.WishlistFragment;
import com.youngmike.mycinemobile.util.MyDBHandler;

/**
 * MainActivity class for MyCineMobile
 */

public class MainActivity extends AppCompatActivity implements
        FriendsFragment.OnFriendItemSelected,
        WishlistFragment.OnWishListItemSelected,
        LibraryFragment.OnLibraryItemSelected {
    private CoordinatorLayout coordinatorLayout;
    private MyDBHandler dbHandler;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mNavigationOptions;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    public boolean mIsLoggedIn = false;
    private FloatingActionButton fab;

    /**
     * onCreate method override, establishes coordinator/drawer layouts and uses fragment manager to
     * swap fragments in/out
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        mIsLoggedIn = sharedPreferences.getBoolean("Remember_Login", false);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
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
            /**
             * onDrawerClosed method, occurs when drawer close occurs, resets title of action bar
             * and invalidates options menu to force redraw
             *
             * @param view
             */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /**
             * onDrawerOpened method, occurs when drawer opens occurs, resets title of action bar
             * and invalidates options menu to force redraw
             *
             * @param drawerView
             */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Replace with add Friend functionality, use snack bar to show success/failure
                Snackbar.make(view, "LOOK MA A SNACK BAR", Snackbar
                        .LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (dbHandler == null) {
            dbHandler = new MyDBHandler(this, null, null, 1);
        }

        //TODO replace with actual user info table population
//        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
        if (dbHandler.getAllUsers().size() < 1) {
            User user = new User();
            user.setFname("Mike");
            user.setLname("Young");
            user.setEmail("mtyoung@mail.com");
            user.setReminderthreshold(1);
            user.setDefaultrentalperiod(1);
            user.setAddressid(1);
            user.setCellnumber("0987654321");
            user.setFirebaseUID("12345678909876543212345678");
            dbHandler.addUser(user);
        }

        if(dbHandler.getAllUserMovies().size() < 1) {
            UserMovieLink link = new UserMovieLink();
            link.setQuantity(1);
            link.setMovieid(1);
            link.setStarrating(5);
            link.setUserid(1);
            link.setMovieSynopsis("Synopsis here");
            link.setMovieTitle("Title here");
            dbHandler.addUserMovieLink(link);
        }

        if (dbHandler.getAllWishlist().size() < 1) {
            Wishlist list = new Wishlist();
            list.setMovieTitle("A Wishlist Title here");
            list.setMovieSynopsis("Wishlist synopsis here");
            list.setUserid(1);
            list.setMovieid(1);
            dbHandler.addWishList(list);
        }

        if (savedInstanceState == null) {

            if (mIsLoggedIn) {
                selectItem(0);
                invalidateOptionsMenu();
            } else {
                LoginFragment firstFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            }
        }

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
        // If the nav drawer is open, hide action items related to the content view
        //TODO replace R.id.action_movieDetail with future reference to barcode reader
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        if (!drawerOpen && !mIsLoggedIn) {
            menu.findItem(R.id.action_movieDetail).setVisible(false);
        } else if (!drawerOpen || mIsLoggedIn) {
            menu.findItem(R.id.action_movieDetail).setVisible(true);
        }

        TextView friendFrag = (TextView) findViewById(R.id.txtName);

        if (drawerOpen) {
            fab.setVisibility(View.INVISIBLE);
        } else if (!drawerOpen && friendFrag != null){
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.INVISIBLE);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * selectItem method - requires integer for position of item clicked, swaps fragments based on
     * selection
     * @param position
     */
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

            TextView libraryFrag = (TextView) findViewById(R.id.txt_library_movie_synopsis);

            if (libraryFrag == null) {

                LibraryFragment newFragment = new LibraryFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
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

            TextView wishlistFrag = (TextView) findViewById(R.id.txt_wishlist_movie_synopsis);

            if (wishlistFrag == null) {

                WishlistFragment newFragment = new WishlistFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }

        } else if (position == 4) {
            TextView prefFrag = (TextView) findViewById(R.id.txt_settings);

            if (prefFrag == null) {

                PreferencesFragment newFragment = new PreferencesFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }

        } else if (position == 5) {
            //TODO create logout method
            mIsLoggedIn = false;

            savePreferences("Remember_Login", mIsLoggedIn);

            getSupportActionBar().setDisplayHomeAsUpEnabled(mIsLoggedIn);
            getSupportActionBar().setHomeButtonEnabled(mIsLoggedIn);
            LoginFragment firstFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, firstFragment).commit();

        }

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavigationOptions[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
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
        editor.commit();
    }

    /**
     * setTitle override - updates the title text on action bar
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
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

    /**
     * onFriendSelected method override - handles logic when a friend is selected from the list
     * @param position
     */
    @Override
    public void onFriendSelected(int position) {
            //TODO add some functionality to selecting friend

    }

    @Override
    public void onWishlistItemSelected(int position){
        //TODO add some functionality to selecting wishlist item
    }

    @Override
    public void onLibraryItemSelected(int position){
        //TODO add some functionality to selecting library item
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
}
