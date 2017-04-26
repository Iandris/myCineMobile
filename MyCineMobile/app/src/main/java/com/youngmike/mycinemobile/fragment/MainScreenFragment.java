package com.youngmike.mycinemobile.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.UserMovieLink;

import java.util.ArrayList;

/**
 * MainScreenFragment for MyCineMobile
 * Created by Mike on 2/8/17.
 */

public class MainScreenFragment extends Fragment {

    MainActivity main;
    TextView mNew1;
    TextView mNew2;
    TextView mNew3;
    TextView mUp1;
    TextView mUp2;
    TextView mUp3;

    /**
     * onCreateView method override - builds view for fragment, wires up widgets
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

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mNew1 = (TextView) v.findViewById(R.id.txtNewTitle1);
        mNew2 = (TextView) v.findViewById(R.id.txtNewTitle2);
        mNew3 = (TextView) v.findViewById(R.id.txtNewTitle3);

        mUp1 = (TextView) v.findViewById(R.id.txtUpcomingTitle1);
        mUp2 = (TextView) v.findViewById(R.id.txtUpcomingTitle2);
        mUp3 = (TextView) v.findViewById(R.id.txtUpcomingTitle3);

        main = (MainActivity)getActivity();
        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
        populateNewUpcomingReleases();

        return v;
    }

    /**
     * populateNewUpcomingReleases - populates the home screen list of new/upcoming titles based on known
     * items in local db
     */
    public void populateNewUpcomingReleases() {
        ArrayList<UserMovieLink> movies = main.getDbHandler().getAllUserMovies();

            if (movies.size() > 0) {
                mNew1.setText(movies.get(0).getMovieTitle());
            }

            if (movies.size() > 1) {
                mNew2.setText(movies.get(1).getMovieTitle());
            }

            if (movies.size() > 2) {
                mNew3.setText(movies.get(2).getMovieTitle());
            }

            if (movies.size() > 3) {
                mUp1.setText(movies.get(3).getMovieTitle());
            }

            if (movies.size() > 4) {
                mUp2.setText(movies.get(4).getMovieTitle());
            }

            if (movies.size() > 5) {
                mUp3.setText(movies.get(5).getMovieTitle());
            }


    }
}
