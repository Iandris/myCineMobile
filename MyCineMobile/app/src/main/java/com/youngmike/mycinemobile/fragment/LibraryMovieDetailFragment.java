package com.youngmike.mycinemobile.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.Rental;
import com.youngmike.mycinemobile.entity.User;
import com.youngmike.mycinemobile.entity.UserMovieLink;
import com.youngmike.mycinemobile.util.MyDBHandler;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * LibraryMovieDetailFragment for MyCineMobile
 * Created by Mike on 2/26/17.
 */

public class LibraryMovieDetailFragment extends Fragment {
    ImageView mCoverArt;
    TextView mMovieTitle;
    TextView mMovieSynopsis;
    RatingBar mMovieRating;
    ToggleButton mCheckInOut;
    Spinner mFriendsSpinner;
    Button mRentButton;
    ArrayAdapter mAdapter;
    MainActivity main;
    Rental thisRent;
    int movieID;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("movieID", movieID);
        super.onSaveInstanceState(outState);
    }

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
        thisRent = null;

        if(savedInstanceState != null) {
            movieID = savedInstanceState.getInt("movieID");
        }

        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mCoverArt = (ImageView) v.findViewById(R.id.img_disk_cover);
        mMovieTitle = (TextView) v.findViewById(R.id.txt_movie_title);
        mMovieSynopsis = (TextView) v.findViewById(R.id.txt_movie_synopsis);
        mMovieRating = (RatingBar) v.findViewById(R.id.rating_movie_stars);
        mCheckInOut = (ToggleButton) v.findViewById(R.id.tgl_move_check_in_out);
        mFriendsSpinner = (Spinner) v.findViewById(R.id.spnr_movie_friends);
        mRentButton = (Button) v.findViewById(R.id.btn_rent);

        mRentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler handler = main.getDbHandler();
                Rental rent = new Rental();
                rent.setRenterid(mFriendsSpinner.getSelectedItemPosition() + 1);
                rent.setMovieid(movieID + 1);
                DateTime due = DateTime.now().plusDays(handler.getUser(1).getDefaultrentalperiod());
                rent.setDuedate(due);
                handler.addRental(rent);

                Toast.makeText(getActivity().getApplicationContext(), "Successfully Rented", Toast.LENGTH_LONG).show();
                main.onBackPressed();
            }
        });

        mCheckInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkoutToggle();
            }
        });

        mMovieRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                MyDBHandler handler = main.getDbHandler();
                UserMovieLink link = handler.getUserMovieLink(movieID + 1);

                if (link != null) {
                    link.setStarrating(Math.round(mMovieRating.getRating()));
                    handler.updateUserMovieLink(link);
                }
            }
        });

        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        loadMovieData();
        populateFriendsList();

       checkoutToggle();

        return v;
    }

    public void checkoutToggle() {
        if (mCheckInOut.isChecked()) {
            mFriendsSpinner.setVisibility(View.VISIBLE);
            mRentButton.setVisibility(View.VISIBLE);
        } else {
            if(thisRent != null) {
                MyDBHandler handler = main.getDbHandler();
                if (handler.deleteRental(thisRent.getIdrentals())) {
                    Toast.makeText(getActivity().getApplicationContext(), "Rental Cancelled/Returned", Toast.LENGTH_LONG).show();
                    thisRent = null;
                    checkoutToggle();
                }
            } else {
                mFriendsSpinner.setVisibility(View.INVISIBLE);
                mRentButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void loadMovieData() {
        Bundle extras = getArguments();
        movieID = extras.getInt("listID");

        UserMovieLink link = main.getDbHandler().getUserMovieLink(movieID + 1);

        if (link != null) {
            mMovieTitle.setText(link.getMovieTitle());
            mMovieSynopsis.setText(link.getMovieSynopsis());
            mMovieRating.setRating(link.getStarrating());
        }

        ArrayList<Rental> rent = main.getDbHandler().getAllRentals();
        thisRent = null;

        for (Rental ren: rent) {
            if (ren.getMovieid() == movieID + 1) {
                thisRent = ren;
            }
        }

        if(thisRent != null) {
            mCheckInOut.setChecked(true);
        } else {
            mCheckInOut.setChecked(false);
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
                }
            }

            return view;
        }
    }

}
