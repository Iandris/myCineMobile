package com.youngmike.mycinemobile.fragment;

import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.Wishlist;
import com.youngmike.mycinemobile.util.ImageGrabber;

/**
 * LibraryMovieDetailFragment for MyCineMobile
 * Created by Mike on 2/26/17.
 */

public class WishlistMovieDetailFragment extends Fragment {
    ImageView mCoverArt;
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
        mMovieTitle = (TextView) v.findViewById(R.id.txt_movie_title);
        mMovieSynopsis = (TextView) v.findViewById(R.id.txt_movie_synopsis);

        loadMovieData();

        MainActivity main = (MainActivity)getActivity();
        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        return v;
    }

    /**
     * loadMovieData method, pulls in movie data from database, creates async call to ImageGrabber
     * to download image on the fly
     */
    public void loadMovieData() {
        Bundle extras = getArguments();
        int position = extras.getInt("listID");

        Wishlist link = main.getDbHandler().getWishList(position + 1);

        if (link != null) {
            new ImageGrabberTask().execute(link.getImagePath());
            mMovieTitle.setText(link.getMovieTitle());
            mMovieSynopsis.setText(link.getMovieSynopsis());


        }
    }

    /**
     * ImageGrabberTask - inner class async instantiation and execution of the ImageGrabber.java class
     */
    private class ImageGrabberTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... path) {
            return new ImageGrabber().fetchItem(path[0]);
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            // showing a progress bar in the user interface
            super.onPreExecute();
        }

        /**
         * Runs on the UI thread after {@link #publishProgress} is invoked.
         * The specified values are the values passed to {@link #publishProgress}.
         *
         * @param values The values indicating progress.
         * @see #publishProgress
         * @see #doInBackground
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * onPostExecute - upon completion of async task, update ui
         * @param item
         */
        @Override
        protected void onPostExecute(Bitmap item) {
            if (item != null) {
                mCoverArt.setImageBitmap(item);
            } else {
                mCoverArt.setImageResource(R.drawable.ic_not_found_web);
            }
        }
    }
}
