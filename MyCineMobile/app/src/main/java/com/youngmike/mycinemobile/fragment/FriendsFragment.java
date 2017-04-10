package com.youngmike.mycinemobile.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.User;
import com.youngmike.mycinemobile.util.MyDBHandler;

import java.util.ArrayList;

/**
 * FriendsFragment for MyCineMobile
 * Created by Mike on 2/22/17.
 */
public class FriendsFragment extends Fragment {
    OnFriendItemSelected mCallback;
    UserArrayAdapter mUserArrayAdapter;
    ListView mListView;

    /**
     * onFriendItemSelected interface - creates a callback to MainActivity for when friends list
     * selection occurs
     */
    public interface OnFriendItemSelected {
        /**
         * Called by HeadlinesFragment when a list_display item is selected
         */
        public void onFriendSelected(int position);
    }


    /**
     * onCreate override - creates list for headlines using built in android simple list,
     * accesses the Array list from Singleton for Headline titles
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_friends, container, false);

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
                mCallback.onFriendSelected(i);
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
            mCallback = (OnFriendItemSelected) activity;
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

        MyDBHandler db = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);

        mUserArrayAdapter = new UserArrayAdapter(getActivity(),
                layout, db.getAllUsers());

        mListView.setAdapter(mUserArrayAdapter);
        mUserArrayAdapter.notifyDataSetChanged();
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
                view = inflater.inflate(R.layout.list_item_friend, null);
            }

            User contactObject = objects.get(position);

            if (contactObject != null) {
                TextView mContactName = (TextView) view.findViewById(R.id.txtName);
                TextView mContactPhone = (TextView) view.findViewById(R.id.txtPhone);
                TextView mContactEmail = (TextView) view.findViewById(R.id.txtEmail);

                if (mContactName != null) {
                    mContactName.setText(contactObject.getLname() + ", " + contactObject.getFname());
                }
                if (mContactEmail != null) {
                    mContactEmail.setText(contactObject.getEmail());
                }
                if (mContactPhone != null) {
                    mContactPhone.setText(contactObject.getCellnumber());
                }

            }

            return view;
        }
    }
}



