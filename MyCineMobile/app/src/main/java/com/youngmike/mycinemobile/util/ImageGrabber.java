package com.youngmike.mycinemobile.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ImageGrabber class for MyCineMobile
 * Performs async call to image url and returns bitmap image
 * Created by Mike on 4/12/17.
 */

public class ImageGrabber {

    /**
     * Performs http call to image url specified in imagePath param, returns a Bitmap object for rendering
     * in the ui thread
     * @param imagePath
     * @return
     */
    public Bitmap fetchItem(String imagePath) {
        Bitmap image = null;
    try {
        URL url = new URL(imagePath);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream in = connection.getInputStream();
        image = BitmapFactory.decodeStream(in);


    } catch (IOException ex) {

        }
        return image;
    }
}
