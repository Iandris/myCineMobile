package com.youngmike.mycinemobile.util;

import android.net.Uri;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngmike.mycinemobile.api.Items;
import com.youngmike.mycinemobile.api.ItemsItem;
import com.youngmike.mycinemobile.com.mycine.Titles;
import com.youngmike.mycinemobile.com.mycine.TitlesItem;
import com.youngmike.mycinemobile.com.omdbapi.Title;
import com.youngmike.mycinemobile.entity.UserMovieLink;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Mike on 4/6/17.
 */

public class UPCLookup {
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public UserMovieLink checkCode(String upc) {

        UserMovieLink items = null;

        try {
            //TODO REMOVE TEMP UPC
            //upc = "025192251344";
            String url = Uri.parse("http://52.14.69.207:8080/mycine/titles/upc/" + upc).buildUpon()
                    .build().toString();

            ObjectMapper mapper = new ObjectMapper();
            Titles results = mapper.readValue(getUrl(url), Titles.class);

            Log.i("HMM", getUrl(url));

            if (results != null) {
                for (TitlesItem item : results.getTitles()
                        ) {
                    items = lookupMovie(item.getIMDBId());
                }
            } else {
                items = null;
            }

        } catch (IOException ioe) {
            Log.e("HMM", "Failed to fetch items", ioe);

        }
        return items;
//        ArrayList<ItemsItem> items = new ArrayList<>();
//        ArrayList<TitlesItem> titles = new ArrayList<>();
//
//        try {
//            upc = "025192251344";
//            String url = Uri.parse("http://api.walmartlabs.com/v1/items?apiKey=qwu7hnegpwvtxavz8fsr492q&upc=" + upc + "&format=json").buildUpon()
//                    .build().toString();
//
//            ObjectMapper mapper = new ObjectMapper();
//            Items results = mapper.readValue(getUrl(url), Items.class);
//
//            Log.i("HMM", getUrl(url));
//
//            for (ItemsItem item: results.getItems()
//                 ) {
//                String namenoSpaces = item.getName().replace(" ", "%20");
//                titles.addAll(lookupMovie(namenoSpaces));
//                //items.add(item);
//            }
//
//        } catch (IOException ioe) {
//            Log.e("HMM", "Failed to fetch items", ioe);
//
//        }
//        return titles;
    }

    public UserMovieLink lookupMovie(String imdb) {
        UserMovieLink item = null;

        try {
            String url = Uri.parse("http://www.omdbapi.com/?r=json&type=movie&plot=long&i=" + imdb).buildUpon()
                    .build().toString();

            ObjectMapper mapper = new ObjectMapper();
            Title results = mapper.readValue(getUrl(url), Title.class);

            item = new UserMovieLink();
            item.setMovieTitle(results.getTitle());
            item.setMovieSynopsis(results.getPlot());
            item.setQuantity(1);
            item.setStarrating(1);
            item.setUserid(1);

        } catch (IOException ioe) {
            Log.e("HMM", "Failed to fetch items", ioe);

        }
        return item;
    }
}
