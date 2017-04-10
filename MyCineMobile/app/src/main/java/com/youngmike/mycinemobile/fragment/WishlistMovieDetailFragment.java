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
import com.youngmike.mycinemobile.entity.UserMovieLink;
import com.youngmike.mycinemobile.entity.Wishlist;

/**
 * LibraryMovieDetailFragment for MyCineMobile
 * Created by Mike on 2/26/17.
 */

public class WishlistMovieDetailFragment extends Fragment {
    ImageView mCoverArt;
    ImageView mFriendIcon;
    TextView mMovieTitle;
    TextView mMovieSynopsis;
    MainActivity main;

    /**
     * onCreate override - creates list for headlines using built in android simple list,
     * accesses the Array list from Singleton for Headline titles
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        main = (MainActivity) getActivity();
        View v = inflater.inflate(R.layout.fragment_wishlist_movie_detail, container, false);

        mCoverArt = (ImageView) v.findViewById(R.id.img_disk_cover);
        mFriendIcon = (ImageView) v.findViewById(R.id.img_friend_profile_pic);
        mMovieTitle = (TextView) v.findViewById(R.id.txt_movie_title);
        mMovieSynopsis = (TextView) v.findViewById(R.id.txt_movie_synopsis);

        loadMovieData();

        MainActivity main = (MainActivity)getActivity();
        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        return v;
    }

    public void loadMovieData() {
        Bundle extras = getArguments();
        int position = extras.getInt("listID");

        Wishlist link = main.getDbHandler().getWishList(position + 1);

        if (link != null) {
            mMovieTitle.setText(link.getMovieTitle());
            mMovieSynopsis.setText(link.getMovieSynopsis());
        }
    }
}
