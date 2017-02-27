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
 * Created by Mike on 2/8/17.
 */

public class MainScreenFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
        }

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        TextView myText = (TextView) v.findViewById(R.id.txt_main);


        // Inflate the layout for this fragment
        return v;
    }
}
