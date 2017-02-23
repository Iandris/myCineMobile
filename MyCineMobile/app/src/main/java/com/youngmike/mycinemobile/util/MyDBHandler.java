package com.youngmike.mycinemobile.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.youngmike.mycinemobile.R;
import com.youngmike.mycinemobile.activity.MainActivity;
import com.youngmike.mycinemobile.entity.Address;
import com.youngmike.mycinemobile.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 2/20/17.
 */

public class MyDBHandler extends SQLiteOpenHelper {
    private Context context;

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {

        super(context, "myCineLocal.db", factory, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADDRESSES_TABLE = "CREATE TABLE Addresses (idAddresses INTEGER " +
                " PRIMARY KEY NOT NULL , StreetAddress1 VARCHAR(45) NOT NULL, " +
                "StreetAddress2 VARCHAR(45), City VARCHAR(45) NOT NULL, State VARCHAR(45) NOT NULL, " +
                "ZipCode INTEGER NOT NULL);";

        String CREATE_USERS_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY NOT NULL , " +
                "fName VARCHAR(45) NOT NULL, lName VARCHAR(45) NOT NULL, " +
                "email VARCHAR(45) NOT NULL, reminderThreshold INTEGER NOT NULL, " +
                "defaultRentalPeriod INTEGER NOT NULL, id_address INTEGER NOT NULL, " +
                "cell_number VARCHAR(45) NOT NULL, firebaseUID VARCHAR(45) NOT NULL, " +
                "CONSTRAINT addressID FOREIGN KEY (id_address) REFERENCES Addresses (idAddresses)); " +
                "CREATE INDEX addressID_idx ON users (id_address);";

        String CREATE_USERMOVIE_TABLE = "CREATE TABLE UserMovie (linkID INTEGER PRIMARY KEY NOT NULL, " +
                "userID INTEGER NOT NULL, movieID INTEGER NOT NULL, quantity INTEGER NOT NULL, " +
                "starRating INTEGER, CONSTRAINT ownerID FOREIGN KEY (userID) REFERENCES users (id));" +
                "CREATE UNIQUE INDEX linkID_UNIQUE ON UserMovie (linkID); " +
                "CREATE INDEX ownerID_idx ON UserMovie (userID);";

        String CREATE_USERFRIENDS_TABLE = "CREATE TABLE UserFriends (id INTEGER PRIMARY KEY NOT NULL, " +
                "friend_a INTEGER NOT NULL, friend_b INTEGER NOT NULL, " +
                "CONSTRAINT friend_a FOREIGN KEY (friend_a) REFERENCES users (id), " +
                "CONSTRAINT friend_b FOREIGN KEY (friend_b) REFERENCES users (id)); " +
                "CREATE INDEX friend_a_idx ON UserFriends (friend_a); " +
                "CREATE INDEX friend_b_idx ON UserFriends (friend_b);";

        String CREATE_RENTALS_TABLE = "CREATE TABLE Rentals (idRentals INTEGER PRIMARY KEY NOT NULL, " +
                "renterID INTEGER NOT NULL, movieID INTEGER NOT NULL, dueDate DATETIME NOT NULL, " +
                "CONSTRAINT renterID FOREIGN KEY (renterID) REFERENCES UserFriends (friend_b), " +
                "CONSTRAINT rentalMovieID FOREIGN KEY (movieID) REFERENCES UserMovie (linkID)); " +
                "CREATE INDEX rentalMovieID_idx ON Rentals (movieID); " +
                "CREATE INDEX renterID_idx ON Rentals (renterID);";

        String CREATE_WISHLIST_TABLE = "CREATE TABLE WishList (idWishListLink INTEGER PRIMARY KEY NOT NULL, " +
                "userID INTEGER NOT NULL, movieID INTEGER NOT NULL);" +
                "CREATE UNIQUE INDEX idWishListLink_UNIQUE ON WishList (idWishListLink);";


        //ADD to CRUD
        db.execSQL(CREATE_ADDRESSES_TABLE);

        //ADD to CRUD
        db.execSQL(CREATE_USERS_TABLE);

        //ADD to CRUD
        db.execSQL(CREATE_USERMOVIE_TABLE);

        //ADD to CRUD
        db.execSQL(CREATE_RENTALS_TABLE);

        //ADD to CRUD
        db.execSQL(CREATE_USERFRIENDS_TABLE);

        //ADD to CRUD
        db.execSQL(CREATE_WISHLIST_TABLE);

    }

    @Override
    //TODO finish adding on upgrade info for each table
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //TODO CRUD for each table/entity (equiv to DAO in HIBERNATE) 5 methods per entity

    public List<Address> getAllAddresses() {
        String query = "SELECT * FROM ADDRESSES";
        List<Address> addresses = null;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Address addr = new Address();
            addr.setStreetaddress1(cursor.getString(1));
            addr.setStreetaddress2(cursor.getString(2));
            addr.setCity(cursor.getString(3));
            addr.setState(Integer.parseInt(cursor.getString(4)));
            addr.setZipcode(Integer.parseInt(cursor.getString(5)));
            addresses.add(addr);
        }
            cursor.close();

        db.close();

        return addresses;
    }

    public Address getAddress(int addressID) {
        String query = "SELECT * FROM ADDRESSES WHERE idAddresses =  \"" + addressID + "\"";

        Address addr = new Address();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //only the first match will then be returned, contained within a
        // new instance of our Product data model class
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            addr.setStreetaddress1(cursor.getString(1));
            addr.setStreetaddress2(cursor.getString(2));
            addr.setCity(cursor.getString(3));
            addr.setState(Integer.parseInt(cursor.getString(4)));
            addr.setZipcode(Integer.parseInt(cursor.getString(5)));
            cursor.close();
        } else {
            addr = null;
        }
        db.close();

        return addr;
    }

    public void addAddress (Address addr) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("StreetAddress1", addr.getStreetaddress1());
        values.put("StreetAddress2", addr.getStreetaddress1());
        values.put("City", addr.getStreetaddress1());
        values.put("State", addr.getStreetaddress1());
        values.put("ZipCode", addr.getStreetaddress1());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.insert("Addresses", null, values);
        // close the database
        db.close();
    }

    public void updateAddress (Address addr) {
        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("StreetAddress1", addr.getStreetaddress1());
        values.put("StreetAddress2", addr.getStreetaddress1());
        values.put("City", addr.getStreetaddress1());
        values.put("State", addr.getStreetaddress1());
        values.put("ZipCode", addr.getStreetaddress1());

        db.update("Addresses", values, String.format("%s = ?", "idAddresses"), new String[]{String.valueOf(addr.getIdaddresses())});

        db.close();
    }

    public void deleteAddress (int addressID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM ADDRESSES WHERE idAddresses =  \"" + addressID + "\"";
        db.delete("Addresses", String.format("%s = ?", "idAddress"), new String[]{String.valueOf(addressID)});
        db.close();
    }

    public ArrayList<User> getAllUsers() {
        String query = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            User usr = new User();
            usr.setFname(cursor.getString(1));
            usr.setLname(cursor.getString(2));
            usr.setEmail(cursor.getString(3));
            usr.setReminderthreshold(Integer.parseInt(cursor.getString(4)));
            usr.setDefaultrentalperiod(Integer.parseInt(cursor.getString(5)));
            usr.setAddressid(Integer.parseInt(cursor.getString(6)));
            usr.setCellnumber(cursor.getString(7));
            usr.setFirebaseUID(cursor.getString(8));
            users.add(usr);
        }
        cursor.close();

        db.close();

        return users;
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("fName", user.getFname());
        values.put("lName", user.getLname());
        values.put("email", user.getEmail());
        values.put("reminderThreshold", user.getReminderthreshold());
        values.put("defaultRentalPeriod", user.getDefaultrentalperiod());
        values.put("id_address", user.getAddressid());
        values.put("cell_number", user.getCellnumber());
        values.put("firebaseUID", user.getFirebaseUID());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.insert("users", null, values);
        // close the database
        db.close();
    }
}
