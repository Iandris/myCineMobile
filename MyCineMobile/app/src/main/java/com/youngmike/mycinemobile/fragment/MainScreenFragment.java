package com.youngmike.mycinemobile.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;

/**
 * MainScreenFragment for MyCineMobile
 * Created by Mike on 2/8/17.
 */

public class MainScreenFragment extends Fragment {
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

        TextView myText = (TextView) v.findViewById(R.id.txt_main);

        MainActivity main = (MainActivity)getActivity();
        main.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
        return v;
    }
}
