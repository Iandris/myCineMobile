package com.youngmike.mycinemobile.fragment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.youngmike.mycinemobile.R;

/**
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


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFirstName.getText().toString().isEmpty() || mFirstName.getText().toString() == "") {
                    Toast.makeText(getActivity().getApplicationContext(), "First Name is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mLastName.getText().toString().isEmpty() || mLastName.getText().toString() == "") {
                    Toast.makeText(getActivity().getApplicationContext(), "Last Name is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mRentalPeriod.getText().toString().isEmpty() || mRentalPeriod.getText().toString() == "") {
                    Toast.makeText(getActivity().getApplicationContext(), "Rental Period is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mReminderThreshold.getText().toString().isEmpty() || mReminderThreshold.getText().toString() == "") {
                    Toast.makeText(getActivity().getApplicationContext(), "Reminder Threshold is a required field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveMyPreferences();
            }
        });

        loadSavedPreferences();
        // Inflate the layout for this fragment
        return v;
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());

        mFirstName.setText(sharedPreferences.getString("First_Name", ""));
        mLastName.setText(sharedPreferences.getString("Last_Name", ""));
        mTextNotifications.setChecked(sharedPreferences.getBoolean("Text_Notifications", false));

        mRentalPeriod.setText(String.valueOf(sharedPreferences.getInt("Rental_Period", 7)));
        mReminderThreshold.setText(String.valueOf(sharedPreferences.getInt("Reminder_Threshold", 48)));
        mRememberMe.setChecked(sharedPreferences.getBoolean("Remember_Login", false));

    }

    public void saveMyPreferences() {
        int rental = 7;
        int reminder = 48;
        savePreferences("First_Name", mFirstName.getText().toString());
        savePreferences("Last_Name", mLastName.getText().toString());
        savePreferences("Text_Notifications", mTextNotifications.isChecked());

        if (mRentalPeriod.getText().toString() != null) {
            rental = Integer.parseInt(mRentalPeriod.getText().toString());
        }

        if (mReminderThreshold.getText().toString() != null && mReminderThreshold.getText().toString() != "") {
            reminder = Integer.parseInt(mReminderThreshold.getText().toString());
        }

        savePreferences("Rental_Period", rental);
        savePreferences("Reminder_Threshold", reminder);
        savePreferences("Remember_Login", mRememberMe.isChecked());

        Toast.makeText(getActivity().getApplicationContext(), "Settings Saved.", Toast.LENGTH_SHORT).show();


        // Create fragment and give it an argument for the selected article
        MainScreenFragment newFragment = new MainScreenFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void savePreferences(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
