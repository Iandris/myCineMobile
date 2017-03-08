package com.youngmike.mycinemobile.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.youngmike.mycinemobile.entity.Address;
import com.youngmike.mycinemobile.entity.Rental;
import com.youngmike.mycinemobile.entity.User;
import com.youngmike.mycinemobile.entity.UserFriends;
import com.youngmike.mycinemobile.entity.UserMovieLink;
import com.youngmike.mycinemobile.entity.Wishlist;

import org.joda.time.DateTime;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * MyDBHandler class for MyCineMobile - using SQLiteOpenHelper creates local database if not exist
 * also houses the CRUD functions of said database
 * Created by Mike on 2/20/17.
 */

public class MyDBHandler extends SQLiteOpenHelper {
    private Context context;

    /**
     * constructor for MyDBHandler class, requires context, db name, factory and version for checking updates
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {

        super(context, "myCineLocal.db", factory, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    /**
     * onCreate for building local database
     * @param db
     */
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
                "starRating INTEGER, movieTitle VARCHAR(45) NOT NULL, movieSynopsis VARCHAR(128) NOT NULL, " +
                "CONSTRAINT ownerID FOREIGN KEY (userID) REFERENCES users (id));" +
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
                "userID INTEGER NOT NULL, movieID INTEGER NOT NULL, movieTitle VARCHAR(45) NOT NULL, movieSynopsis VARCHAR(128) NOT NULL);" +
                "CREATE UNIQUE INDEX idWishListLink_UNIQUE ON WishList (idWishListLink);";


        db.execSQL(CREATE_ADDRESSES_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_USERMOVIE_TABLE);
        db.execSQL(CREATE_USERFRIENDS_TABLE);
        db.execSQL(CREATE_RENTALS_TABLE);
        db.execSQL(CREATE_WISHLIST_TABLE);

    }

    /**
     * onUpgrade will drop and re-create tables as necessary for updated schema
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    //TODO finish adding on upgrade info for each table
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    /**
     * getAllAddresses method, returns a list of Address Entites found in local database
     * @return
     */
    public ArrayList<Address> getAllAddresses() {
        String query = "SELECT * FROM ADDRESSES";
        ArrayList<Address> addresses = null;
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

    /**
     * getAddress method, returns an Address entity by searching for ID
     * @param addressID
     * @return
     */
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

    /**
     * addAddress method, inserts an Address entity into local db
     * @param addr
     */
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

    /**
     * updateAddress method, updates an exisitng Address entity in local db
     * @param addr
     */
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

    /**
     * deleteAddress method, removeds an exising Address entity from local db
     * @param addressID
     */
    public void deleteAddress (int addressID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM ADDRESSES WHERE idAddresses =  \"" + addressID + "\"";
        db.delete("Addresses", String.format("%s = ?", "idAddress"), new String[]{String.valueOf(addressID)});
        db.close();
    }

    /**
     * getAllUsers method, returns a list of User entities int he db
     * @return
     */
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

    /**
     * getUser method, returns a single User entity fromlocal db based on ID column
     * @param userID
     * @return
     */
    public User getUser(int userID) {
        String query = "SELECT * FROM users WHERE id =  \"" + userID + "\"";

        User usr = new User();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //only the first match will then be returned, contained within a
        // new instance of our Product data model class
        if (cursor.moveToFirst()) {
            usr.setFname(cursor.getString(1));
            usr.setLname(cursor.getString(2));
            usr.setEmail(cursor.getString(3));
            usr.setReminderthreshold(Integer.parseInt(cursor.getString(4)));
            usr.setDefaultrentalperiod(Integer.parseInt(cursor.getString(5)));
            usr.setAddressid(Integer.parseInt(cursor.getString(6)));
            usr.setCellnumber(cursor.getString(7));
            usr.setFirebaseUID(cursor.getString(8));
            cursor.close();
        } else {
            usr = null;
        }
        db.close();

        return usr;
    }

    /**
     * addUser method, inserts a new User entity into local db
     * @param user
     */
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

    /**
     * updateUser method, updates an existing user entity in local db
     * @param user
     */
    public void updateUser (User user) {
        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

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

        db.update("users", values, String.format("%s = ?", "id"), new String[]{String.valueOf(user.getUuid())});

        db.close();
    }

    /**
     * deleteUser method, deletes an exising User entity from local db
     * @param userID
     */
    public void deleteUser (int userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM users WHERE id =  \"" + userID + "\"";
        db.delete("users", String.format("%s = ?", "id"), new String[]{String.valueOf(userID)});
        db.close();
    }

    /**
     * getAllUserMovies method, returns list of UserMovie entities from local db
     * @return
     */
    public ArrayList<UserMovieLink> getAllUserMovies () {
        String query = "SELECT * FROM UserMovie";
        ArrayList<UserMovieLink> links = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            UserMovieLink link = new UserMovieLink();
            link.setUserid(Integer.parseInt(cursor.getString(1)));
            link.setMovieid(Integer.parseInt(cursor.getString(2)));
            link.setQuantity(Integer.parseInt(cursor.getString(3)));
            link.setStarrating(Integer.parseInt(cursor.getString(4)));
            link.setMovieTitle(cursor.getString((5)));
            link.setMovieSynopsis(cursor.getString(6));
            links.add(link);
        }
        cursor.close();

        db.close();

        return links;
    }

    /**
     * getUserMovieLink method, returns a single UserMovie entity based on ID from local db
     * @param linkID
     * @return
     */
    public UserMovieLink getUserMovieLink (int linkID) {
        String query = "SELECT * FROM UserMovie WHERE id =  \"" + linkID + "\"";

        UserMovieLink link = new UserMovieLink();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //only the first match will then be returned, contained within a
        // new instance of our Product data model class
        if (cursor.moveToFirst()) {
            link.setUserid(Integer.parseInt(cursor.getString(1)));
            link.setMovieid(Integer.parseInt(cursor.getString(2)));
            link.setQuantity(Integer.parseInt(cursor.getString(3)));
            link.setStarrating(Integer.parseInt(cursor.getString(4)));
            link.setMovieTitle(cursor.getString((5)));
            link.setMovieSynopsis(cursor.getString(6));
            cursor.close();
        } else {
            link = null;
        }
        db.close();

        return link;
    }

    /**
     * addUserMovieLink method, creates and inserts a new UserMovie entity into local db
     * @param link
     */
    public void addUserMovieLink (UserMovieLink link) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("userID", link.getUserid());
        values.put("movieID", link.getMovieid());
        values.put("quantity", link.getQuantity());
        values.put("starRating", link.getStarrating());
        values.put("movieTitle", link.getMovieTitle());
        values.put("movieSynopsis", link.getMovieSynopsis());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.insert("UserMovie", null, values);
        // close the database
        db.close();
    }

    /**
     * updateUserMovieLink method, updates an exising UserMovie enitity in local db
     * @param link
     */
    public void updateUserMovieLink (UserMovieLink link) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("userID", link.getUserid());
        values.put("movieID", link.getMovieid());
        values.put("quantity", link.getQuantity());
        values.put("starRating", link.getStarrating());
        values.put("movieTitle", link.getMovieTitle());
        values.put("movieSynopsis", link.getMovieSynopsis());


        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.update("UserMovie", values, String.format("%s = ?", "linkID"), new String[]{String.valueOf(link.getLinkid())});
        // close the database
        db.close();
    }

    /**
     * deleteUserMovieLink method, deletes an exising UserMovie entity from local db
     * @param linkID
     */
    public void deleteUserMovieLink (int linkID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM UserMovie WHERE linkID =  \"" + linkID + "\"";
        db.delete("UserMovie", String.format("%s = ?", "linkID"), new String[]{String.valueOf(linkID)});
        db.close();
    }

    /**
     * getAllFriends method, returns a list of UserFriend entites from local db
     * @return
     */
    public ArrayList<UserFriends> getAllFriends() {
        String query = "SELECT * FROM UserFriends";
        ArrayList<UserFriends> friendLinks = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            UserFriends friend = new UserFriends();
            friend.setFriendidA(Integer.parseInt(cursor.getString(1)));
            friend.setFriendidB(Integer.parseInt(cursor.getString(2)));
            friendLinks.add(friend);
        }
        cursor.close();

        db.close();

        return friendLinks;
    }

    /**
     * getUserFriend method, returns a single UserFriend entity based on ID from local db
     * @param friendID
     * @return
     */
    public UserFriends getUserFriend (int friendID) {
        String query = "SELECT * FROM UserFriends WHERE idConnector =  \"" + friendID + "\"";

        UserFriends friend = new UserFriends();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //only the first match will then be returned, contained within a
        // new instance of our Product data model class
        if (cursor.moveToFirst()) {
            friend.setFriendidA(Integer.parseInt(cursor.getString(1)));
            friend.setFriendidB(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            friend = null;
        }
        db.close();

        return friend;
    }

    /**
     * addUserFriend method, inserts a new UserFriend entity into local db
     * @param friend
     */
    public void addUserFriend (UserFriends friend) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("friend_a", friend.getFriendidA());
        values.put("friend_b", friend.getFriendidB());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.insert("UserFriends", null, values);
        // close the database
        db.close();
    }

    /**
     * updateUserFriend method, updates an existing UserFriend entity in local db
     * @param friend
     */
    public void updateUserFriend (UserFriends friend) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("friend_a", friend.getFriendidA());
        values.put("friend_b", friend.getFriendidB());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.update("UserFriends", values, String.format("%s = ?", "idConnector"), new String[]{String.valueOf(friend.getIdConnector())});
        // close the database
        db.close();
    }

    /**
     * deleteUserFriend method, removes an existing UserFriend entity from local db
     * @param friendID
     */
    public void deleteUserFriend (int friendID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM UserFriends WHERE idConnector =  \"" + friendID + "\"";
        db.delete("UserFriends", String.format("%s = ?", "idConnector"), new String[]{String.valueOf(friendID)});
        db.close();
    }

    /**
     * getAllRentals method, returns a list of all Rental entities from local db
     * @return
     */
    public ArrayList<Rental> getAllRentals() {
        String query = "SELECT * FROM Rentals";
        ArrayList<Rental> rentals = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Rental rental = new Rental();
            rental.setRenterid(Integer.parseInt(cursor.getString(1)));
            rental.setMovieid(Integer.parseInt(cursor.getString(2)));
            rental.setDuedate(DateTime.parse(cursor.getString(3)));
            rentals.add(rental);
        }
        cursor.close();

        db.close();

        return rentals;
    }

    /**
     * getRental method, returns a single Rental entity based on ID from local db
     * @param rentalID
     * @return
     */
    public Rental getRental (int rentalID) {
        String query = "SELECT * FROM Rentals WHERE idRentals =  \"" + rentalID + "\"";

        Rental rental = new Rental();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //only the first match will then be returned, contained within a
        // new instance of our Product data model class
        if (cursor.moveToFirst()) {
            rental.setRenterid(Integer.parseInt(cursor.getString(1)));
            rental.setMovieid(Integer.parseInt(cursor.getString(2)));
            rental.setDuedate(DateTime.parse(cursor.getString(3)));
            cursor.close();
        } else {
            rental = null;
        }
        db.close();

        return rental;
    }

    /**
     * addRental method, inserts a new Rental entity into local db
     * @param rental
     */
    public void addRental (Rental rental) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("renterID", rental.getRenterid());
        values.put("movieID", rental.getMovieid());
        values.put("dueDate", rental.getDuedate().toString());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.insert("Rentals", null, values);
        // close the database
        db.close();
    }

    /**
     * updateRental method, updates an existing Rental Entity in local db
     * @param rental
     */
    public void updateRental (Rental rental) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("renterID", rental.getRenterid());
        values.put("movieID", rental.getMovieid());
        values.put("dueDate", rental.getDuedate().toString());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.update("Rentals", values, String.format("%s = ?", "idRentals"), new String[]{String.valueOf(rental.getIdrentals())});
        // close the database
        db.close();
    }

    /**
     * deleteRental method, removes an existing Rental entity from local db
     * @param rentalID
     */
    public void deleteRental (int rentalID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Rentals WHERE idRentals =  \"" + rentalID + "\"";
        db.delete("Rentals", String.format("%s = ?", "idRentals"), new String[]{String.valueOf(rentalID)});
        db.close();
    }

    /**
     * getAllWishList method, returns a list of WishList entities from local db
     * @return
     */
    public ArrayList<Wishlist> getAllWishlist () {
        String query = "SELECT * FROM WishList";
        ArrayList<Wishlist> rentals = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Wishlist wish = new Wishlist();
            wish.setUserid(Integer.parseInt(cursor.getString(1)));
            wish.setMovieid(Integer.parseInt(cursor.getString(2)));
            wish.setMovieTitle(cursor.getString((3)));
            wish.setMovieSynopsis(cursor.getString(4));
            rentals.add(wish);
        }
        cursor.close();

        db.close();

        return rentals;
    }

    /**
     * getWishList method, returns a single WishList entity based on ID from local db
     * @param wishlistID
     * @return
     */
    public Wishlist getWishList (int wishlistID) {
        String query = "SELECT * FROM WishList WHERE idwishlistlink =  \"" + wishlistID + "\"";

        Wishlist wish = new Wishlist();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //only the first match will then be returned, contained within a
        // new instance of our Product data model class
        if (cursor.moveToFirst()) {
            wish.setUserid(Integer.parseInt(cursor.getString(1)));
            wish.setMovieid(Integer.parseInt(cursor.getString(2)));
            wish.setMovieTitle(cursor.getString((3)));
            wish.setMovieSynopsis(cursor.getString(4));
            cursor.close();
        } else {
            wish = null;
        }
        db.close();

        return wish;
    }

    /**
     * addWishlist method, inserts a new wishlist entity into local db
     * @param wish
     */
    public void addWishList (Wishlist wish) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("userID", wish.getUserid());
        values.put("movieID", wish.getMovieid());
        values.put("movieTitle", wish.getMovieTitle());
        values.put("movieSynopsis", wish.getMovieSynopsis());

        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.insert("WishList", null, values);
        // close the database
        db.close();
    }

    /**
     * updateWishList method, updates an exising wishlist entity in local db
     * @param wish
     */
    public void updateWishList (Wishlist wish) {
        ContentValues values = new ContentValues();

        //content object primed with key-value pairs for the data columns extracted from the Product object
        values.put("userID", wish.getUserid());
        values.put("movieID", wish.getMovieid());
        values.put("movieTitle", wish.getMovieTitle());
        values.put("movieSynopsis", wish.getMovieSynopsis());


        //  a reference to the database will be obtained
        SQLiteDatabase db = this.getWritableDatabase();

        // insert the record
        db.update("WishList", values, String.format("%s = ?", "idwishlistlink"), new String[]{String.valueOf(wish.getIdwishlistlink())});
        // close the database
        db.close();
    }

    /**
     * deleteWishlist method, removes an existing wishlist entity from local db
     * @param wishID
     */
    public void deleteWishList (int wishID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM WishList WHERE idwishlistlink =  \"" + wishID + "\"";
        db.delete("WishList", String.format("%s = ?", "idwishlistlink"), new String[]{String.valueOf(wishID)});
        db.close();
    }
}
