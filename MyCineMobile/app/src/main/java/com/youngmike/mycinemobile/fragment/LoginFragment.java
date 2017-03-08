package com.youngmike.mycinemobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.User;
import com.youngmike.mycinemobile.util.MyDBHandler;

/**
 * LoginFragment for MyCineMobile
 * Created by Mike on 2/22/17.
 */

public class LoginFragment extends Fragment {

    Button mLogin;

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

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
        }

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        EditText mUserName = (EditText) v.findViewById(R.id.et_user_name);
        EditText mPassword = (EditText) v.findViewById(R.id.et_password);
        CheckBox mRemember = (CheckBox) v.findViewById(R.id.cbx_remember_me);

        mLogin = (Button) v.findViewById(R.id.btn_login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method override - inner method for logon button action
             * @param v
             */
            @Override
            public void onClick(View v) {


                MainActivity main = (MainActivity)getActivity();
                main.mIsLoggedIn = true;
                main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
                main.selectItem(0);
                main.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

            }
        });

        return v;
    }

}
