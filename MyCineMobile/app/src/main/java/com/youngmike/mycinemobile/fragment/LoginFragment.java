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
 * Created by Mike on 2/22/17.
 */

public class LoginFragment extends Fragment {

    Button mLogin;
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
            @Override
            public void onClick(View v) {
                //TODO replace with actual user info table population
                MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
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

                MainActivity main = (MainActivity)getActivity();
                main.mIsLoggedIn = true;
                main.selectItem(0);
                main.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//
//                MainScreenFragment newFragment = new MainScreenFragment();
//
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                transaction.replace(R.id.fragment_container, newFragment);
//                transaction.addToBackStack(null);
//
//                transaction.commit();
            }
        });

        //TextView myText = (TextView) v.findViewById(R.id.txt_main);

        // Inflate the layout for this fragment
        return v;
    }

}
