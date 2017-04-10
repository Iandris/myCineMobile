package com.youngmike.mycinemobile.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.Address;
import com.youngmike.mycinemobile.entity.User;
import com.youngmike.mycinemobile.entity.UserFriends;
import com.youngmike.mycinemobile.entity.UserMovieLink;
import com.youngmike.mycinemobile.util.MyDBHandler;

import java.util.ArrayList;

/**
 * LibraryMovieDetailFragment for MyCineMobile
 * Created by Mike on 2/26/17.
 */

public class LibraryMovieDetailFragment extends Fragment {
    ImageView mCoverArt;
    ImageView mFriendIcon;
    TextView mMovieTitle;
    TextView mMovieSynopsis;
    RatingBar mMovieRating;
    ToggleButton mCheckInOut;
    Spinner mFriendsSpinner;
    TextView mFriendName;
    TextView mFriendAddress1;
    TextView mFriendAddress2;
    TextView mFriendPhone;
    TextView mFriendEmail;
    ArrayAdapter mAdapter;
    MainActivity main;

    /**
     * onCreate override - creates list for headlines using built in android simple list,
     * accesses the Array list from Singleton for Headline titles
     *
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        main = (MainActivity) getActivity();

        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mCoverArt = (ImageView) v.findViewById(R.id.img_disk_cover);
        mFriendIcon = (ImageView) v.findViewById(R.id.img_friend_profile_pic);
        mMovieTitle = (TextView) v.findViewById(R.id.txt_movie_title);
        mMovieSynopsis = (TextView) v.findViewById(R.id.txt_movie_synopsis);
        mMovieRating = (RatingBar) v.findViewById(R.id.rating_movie_stars);
        mCheckInOut = (ToggleButton) v.findViewById(R.id.tgl_move_check_in_out);
        mFriendsSpinner = (Spinner) v.findViewById(R.id.spnr_movie_friends);

        mFriendName = (TextView) v.findViewById(R.id.txt_friend_name);
        mFriendAddress1 = (TextView) v.findViewById(R.id.txt_friend_address_1);
        mFriendAddress2 = (TextView) v.findViewById(R.id.txt_friend_address_2);
        mFriendPhone = (TextView) v.findViewById(R.id.txt_friend_phone);
        mFriendEmail = (TextView) v.findViewById(R.id.txt_friend_email);

        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        loadMovieData();
        populateFriendsList();

        mFriendsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0) {
                    User user = ((MainActivity) getActivity()).getDbHandler().getUser(position + 1);
                    mFriendName.setText(user.getFname() + " " + user.getLname());
                    Address addr = ((MainActivity) getActivity()).getDbHandler().getAddress(user.getAddressid());

                    if (addr != null) {
                        mFriendAddress1.setText(addr.getStreetaddress1());
                        mFriendAddress2.setText(addr.getStreetaddress2());
                    } else {
                        mFriendAddress1.setText("unknown");
                        mFriendAddress2.setText("unknown");
                    }
                    mFriendPhone.setText(user.getCellnumber());
                    mFriendEmail.setText(user.getEmail());
                } else {
                    mFriendName.setText("");
                    mFriendAddress1.setText("");
                    mFriendAddress2.setText("");
                    mFriendPhone.setText("");
                    mFriendEmail.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mFriendName.setText("");
                mFriendAddress1.setText("");
                mFriendAddress2.setText("");
                mFriendPhone.setText("");
                mFriendEmail.setText("");
            }
        });

        return v;
    }

    public void loadMovieData() {
        Bundle extras = getArguments();
        int position = extras.getInt("listID");

        UserMovieLink link = main.getDbHandler().getUserMovieLink(position + 1);

        if (link != null) {
            mMovieTitle.setText(link.getMovieTitle());
            mMovieSynopsis.setText(link.getMovieSynopsis());
            mMovieRating.setRating(link.getStarrating());
        }
    }


    public void populateFriendsList() {

        ArrayList<User> myFriends = main.getDbHandler().getAllUsers();

        if (myFriends.size() > 0) {
            mAdapter = new UserArrayAdapter(main.getApplication(), android.R.layout.simple_spinner_item, myFriends);
            mAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            mFriendsSpinner.setAdapter(mAdapter);
        }
    }


    /**
     * ContactArrayAdapter inner class
     * Extends ArrayAdapter class forcing Contact class as list object type
     */
    public class UserArrayAdapter extends ArrayAdapter<User> {

        private ArrayList<User> objects;

        /**
         *  Override the constructor for ArrayAdapter
         *  The only variable we care about now ArrayList<PlatformVersion> objects
         *  it is the list of the objects we want to display
         *
         * @param context
         * @param resource
         * @param objects
         */
        public UserArrayAdapter(Context context, int resource, ArrayList<User> objects) {
            super(context, resource, objects);
            this.objects = objects;
        }

        /**
         * getView method creates custom view for list items
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.spinner_friends, null);
            }

            User contactObject = objects.get(position);

            if (contactObject != null) {
                TextView username = (TextView) view.findViewById(R.id.spnr_friend_name);

                if (username != null) {
                    username.setText(contactObject.getFname() + " " + contactObject.getLname());
                    Log.i("HMM", username.getText().toString());
                }
            }

            return view;
        }
    }

}
