package com.youngmike.mycinemobile.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.youngmike.mycinemobile.entity.User;
import com.youngmike.mycinemobile.fragment.FriendsFragment;
import com.youngmike.mycinemobile.fragment.LoginFragment;
import com.youngmike.mycinemobile.fragment.MainScreenFragment;
import com.youngmike.mycinemobile.fragment.PreferencesFragment;
import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.util.MyDBHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());

            if (sharedPreferences.getBoolean("Remember_Login", true)) {
                //TODO try auto-login when developed - on fail redirect to login fragment
                MainScreenFragment firstFragment = new MainScreenFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            } else {
                LoginFragment firstFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_prefs:
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
                return true;
            case R.id.action_friends:

                TextView friendFrag = (TextView) findViewById(R.id.txtName);

                if (friendFrag == null) {

                    FriendsFragment newFragment = new FriendsFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
