package com.youngmike.mycinemobile.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.R;

/**
 * LoginFragment for MyCineMobile
 * Created by Mike on 2/22/17.
 */

public class LoginFragment extends Fragment {

    Button mLogin;
    EditText mUserName;
    EditText mPassword;
    CheckBox mRemember;

    /**
     * onCreateView method override - wires up widgets within the fragment view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
        }

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mUserName = (EditText) v.findViewById(R.id.et_user_name);
        mPassword = (EditText) v.findViewById(R.id.et_password);
        mRemember = (CheckBox) v.findViewById(R.id.cbx_remember_me);

        mLogin = (Button) v.findViewById(R.id.btn_login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method override - inner method for logon button action
             * @param v
             */
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getActivity().getApplicationContext());

                String username = sharedPreferences.getString("Username", "");
                String pwrd = sharedPreferences.getString("Password", "");

                MainActivity main = (MainActivity)getActivity();

                if (username == "") {
                    main.selectItem(4);
                    //main.invalidateOptionsMenu();
                } else {

                    if (mUserName.getText().toString().equals(username) && mPassword.getText().toString().equals(pwrd)) {
                        main.mIsLoggedIn = true;
                        savePreferences("Remember_Login", mRemember.isChecked());
                        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
                        main.selectItem(0);
                        //main.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                    } else {
                        Toast.makeText(main.getApplicationContext(), "Incorrect Username/Password", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        return v;
    }
    /**
     * savePreferences method - requires two parameters String for key, and boolean for value
     * @param key
     * @param value
     */
    private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
