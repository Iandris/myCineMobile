package com.youngmike.mycinemobile.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;

/**
 * Created by Mike on 2/26/17.
 */

public class MovieDetailFragment extends Fragment {
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

    /**
     * onCreate override - creates list for headlines using built in android simple list,
     * accesses the Array list from Singleton for Headline titles
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mCoverArt = (ImageView) v.findViewById(R.id.img_disk_cover);
        mFriendIcon = (ImageView) v.findViewById(R.id.img_friend_profile_pic);
        mMovieTitle = (TextView) v.findViewById(R.id.txt_movie_title);
        mMovieSynopsis = (TextView) v.findViewById(R.id.txt_movie_synopsis);
        mMovieRating = (RatingBar) v.findViewById(R.id.rating_movie_stars);
        mCheckInOut = (ToggleButton) v.findViewById(R.id.tgl_move_check_in_out);
        mFriendsSpinner = (Spinner) v.findViewById(R.id.spnr_movie_friends);

        //TODO add handler for spinner
        //TODO add datasource/refresh for spinner content

        mFriendName = (TextView) v.findViewById(R.id.txt_friend_name);
        mFriendAddress1 = (TextView) v.findViewById(R.id.txt_friend_address_1);
        mFriendAddress2 = (TextView) v.findViewById(R.id.txt_friend_address_2);
        mFriendPhone = (TextView) v.findViewById(R.id.txt_friend_phone);
        mFriendEmail = (TextView) v.findViewById(R.id.txt_friend_email);

        MainActivity main = (MainActivity)getActivity();
        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        return v;
    }

    //TODO add method to pull friend data on spinner selection
}
