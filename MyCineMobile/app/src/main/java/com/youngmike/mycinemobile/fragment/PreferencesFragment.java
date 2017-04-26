package com.youngmike.mycinemobile.fragment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.User;

/**
 * PreferencesFragment for MyCineMobile
 * Created by Mike on 2/8/17.
 */

public class PreferencesFragment extends Fragment {
    TextView myText;
    Button mSaveButton;
    EditText mFirstName;
    EditText mLastName;
    CheckBox mTextNotifications;
    EditText mRentalPeriod;
    EditText mReminderThreshold;
    CheckBox mRememberMe;
    EditText mUsername;
    EditText mPassword;
    EditText mPhone;
    MainActivity main;

    /**
     * onCreateView method override - wires up widges
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prefs, container, false);

        myText = (TextView) v.findViewById(R.id.txt_settings);
        mSaveButton = (Button) v.findViewById(R.id.btn_savePref);
        mFirstName = (EditText) v.findViewById(R.id.et_firstName);
        mLastName = (EditText) v.findViewById(R.id.et_LastName);
        mTextNotifications = (CheckBox) v.findViewById(R.id.cbx_TextNotification);
        mRentalPeriod = (EditText) v.findViewById(R.id.et_RentalPeriod);
        mReminderThreshold = (EditText) v.findViewById(R.id.et_ReminderThreshold);
        mRememberMe = (CheckBox) v.findViewById(R.id.cbx_remember_me);
        mUsername = (EditText) v.findViewById(R.id.et_prefUser);
        mPassword = (EditText) v.findViewById(R.id.et_prefPassword);
        mPhone   = (EditText) v.findViewById(R.id.et_phone);


             mSaveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method override - validation for form entry and storage to shared preferences
             * @param v
             */
            @Override
            public void onClick(View v) {
                if (mFirstName.getText().toString().isEmpty() || mFirstName.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "First Name is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mLastName.getText().toString().isEmpty() || mLastName.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Last Name is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mPhone.getText().toString().isEmpty() || mPhone.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Phone Number is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mRentalPeriod.getText().toString().isEmpty() || mRentalPeriod.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Rental Period is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mReminderThreshold.getText().toString().isEmpty() || mReminderThreshold.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Reminder Threshold is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mUsername.getText().toString().isEmpty() || mUsername.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Username is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mPassword.getText().toString().isEmpty() || mPassword.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Password Threshold is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }


                saveMyPreferences();
            }
        });

        main = (MainActivity)getActivity();
        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        loadSavedPreferences();
        return v;
    }

    /**
     * loadSavedPreferences method - loads data from shared preferences file
     */
    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());

        mFirstName.setText(sharedPreferences.getString("First_Name", ""));
        mLastName.setText(sharedPreferences.getString("Last_Name", ""));
        mPhone.setText(sharedPreferences.getString("Phone", ""));
        mTextNotifications.setChecked(sharedPreferences.getBoolean("Text_Notifications", false));

        mRentalPeriod.setText(String.valueOf(sharedPreferences.getInt("Rental_Period", 7)));
        mReminderThreshold.setText(String.valueOf(sharedPreferences.getInt("Reminder_Threshold", 48)));
        mRememberMe.setChecked(sharedPreferences.getBoolean("Remember_Login", false));
        mUsername.setText(sharedPreferences.getString("Username", ""));
        mPassword.setText(sharedPreferences.getString("Password", ""));
    }

    /**
     * saveMyPreferences method - stores information from preferences screen into shared preferences file
     */
    public void saveMyPreferences() {
        int rental = 7;
        int reminder = 48;
        savePreferences("First_Name", mFirstName.getText().toString());
        savePreferences("Last_Name", mLastName.getText().toString());
        savePreferences("Phone", mPhone.getText().toString());


        savePreferences("Text_Notifications", mTextNotifications.isChecked());

        if (!mRentalPeriod.getText().toString().equals("")) {
            rental = Integer.parseInt(mRentalPeriod.getText().toString());
        }

        if (!mReminderThreshold.getText().toString().equals("")) {
            reminder = Integer.parseInt(mReminderThreshold.getText().toString());
        }

        savePreferences("Rental_Period", rental);
        savePreferences("Reminder_Threshold", reminder);
        savePreferences("Remember_Login", mRememberMe.isChecked());

        savePreferences("Username",mUsername.getText().toString());
        savePreferences("Password",mPassword.getText().toString());

        User primaryUser = main.getDbHandler().getUser(1);

        if (primaryUser == null) {
            User user = new User();
            user.setFname(mFirstName.getText().toString());
            user.setLname(mLastName.getText().toString());
            user.setEmail(mUsername.getText().toString());
            user.setReminderthreshold(Integer.parseInt(mReminderThreshold.getText().toString()));
            user.setDefaultrentalperiod(Integer.parseInt(mRentalPeriod.getText().toString()));
            user.setAddressid(1);
            user.setCellnumber(mPhone.getText().toString());
            user.setFirebaseUID("12345678909876543212345678");
            main.getDbHandler().addUser(user);
        }

        Toast.makeText(getActivity().getApplicationContext(), "Settings Saved.", Toast.LENGTH_SHORT).show();

        main.mIsLoggedIn = true;
        main.selectItem(0);

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

    /**
     * savePreferences method - requires two parameters String for key, and integer for value
     * @param key
     * @param value
     */
    private void savePreferences(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * savePreferences method - requires two parameters String for key, and String for value
     * @param key
     * @param value
     */
    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
