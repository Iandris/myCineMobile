package com.youngmike.mycinemobile.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.Wishlist;

import java.util.ArrayList;

/**
 * Created by Mike on 3/8/17.
 */

public class WishlistFragment extends Fragment {
    OnWishListItemSelected mCallback;
    public WishListArrayAdapter mWishlistAdapter;
    MainActivity main;
    ListView mListView;

    /**
     * onFriendItemSelected interface - creates a callback to MainActivity for when friends list
     * selection occurs
     */
    public interface OnWishListItemSelected {
        /**
         * Called by HeadlinesFragment when a list_display item is selected
         */
        public void onWishlistItemSelected(int position);
    }


    /**
     * onCreate override - creates list for headlines using built in android simple list,
     * accesses the Array list from Singleton for Headline titles
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        main = (MainActivity) getActivity();

        View v = inflater.inflate(R.layout.fragment_wishlist, container, false);

        mListView = (ListView) v.findViewById(R.id.android_list);
        mListView.setEmptyView(v.findViewById(R.id.android_empty));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * onItemClick override for mListView Project3 ContactListFragment.java class,
             * loads the selected list view item into the intent to pass to ContactActivity.java
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.onWishlistItemSelected(i);
            }
        });

        MainActivity main = (MainActivity)getActivity();
        main.findViewById(R.id.fab).setVisibility(View.VISIBLE);

        return v;
    }

    /**
     * onAttach method override - establishes the callback to MainActivity
     * @param activity
     */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (WishlistFragment.OnWishListItemSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFriendItemSelectedListener");
        }
    }

    /**
     * onStart method overide - creates an array adapter and list for Friends data
     */
    @Override
    public void onStart() {
        super.onStart();
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;


        mWishlistAdapter = new WishListArrayAdapter(getActivity(),
                layout, main.getDbHandler().getAllWishlist() );

        mListView.setAdapter(mWishlistAdapter);
        mWishlistAdapter.notifyDataSetChanged();
    }

    /**
     * ContactArrayAdapter inner class
     * Extends ArrayAdapter class forcing Contact class as list object type
     */
    public class WishListArrayAdapter extends ArrayAdapter<Wishlist> {

        private ArrayList<Wishlist> objects;

        /**
         *  Override the constructor for ArrayAdapter
         *  The only variable we care about now ArrayList<PlatformVersion> objects
         *  it is the list of the objects we want to display
         *
         * @param context
         * @param resource
         * @param objects
         */
        public WishListArrayAdapter(Context context, int resource, ArrayList<Wishlist> objects) {
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
                view = inflater.inflate(R.layout.list_item_wishlist, null);
            }

            Wishlist contactObject = objects.get(position);

            if (contactObject != null) {
                TextView mMovieTitle = (TextView) view.findViewById(R.id.txt_wishlist_movie_title);
                TextView mMovieSynopsis = (TextView) view.findViewById(R.id.txt_wishlist_movie_synopsis);

                if (mMovieTitle != null) {
                    mMovieTitle.setText(contactObject.getMovieTitle());
                }
                if (mMovieSynopsis != null) {
                    mMovieSynopsis.setText(contactObject.getMovieSynopsis());
                }
            }

            return view;
        }
    }
}
