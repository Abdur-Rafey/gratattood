package com.gratattood.gratattood.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Rafey Sheikh on 10/2/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CREATED_ON = "createdon";
    private static final String KEY_UPDATE_ON = "updateon";
    private static final String KEY_FCM_ID = "fcm_id";
    private static final String KEY_IMAGE = "image_id";
    private static final String KEY_TOTAL_VIEWS = "total_viwes";
    private static final String KEY_TOTAL_POSTS = "totsl_posts";
    private static final String KEY_TOTAL_UPVOTES= "total_upvotes";
    private static final String KEY_USER_BALANCE = "user_balance";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_WEBSITE = "website";
    private static final String KEY_FACEBOOK_PROFILE = "facebook_profile";
    private static final String KEY_TWITTER_PROFILE = "twitter_profile";
    private static final String KEY_INSTAGRAM_PROFILE = "instagram_profile";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_UID + " INTEGER,"
                + KEY_EMAIL + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_IMAGE + " TEXT,"
                + KEY_USER_BALANCE + " INTEGER,"
                + KEY_DESCRIPTION + " TEXT"
                +");";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(int uid ,String email, String name,String image,int user_balance ,String
            description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UID, uid);
        values.put(KEY_EMAIL, email);
        values.put(KEY_NAME, name);
        values.put(KEY_IMAGE, image);
        values.put(KEY_USER_BALANCE, user_balance);
        values.put(KEY_DESCRIPTION, description);

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("id", cursor.getString(1));
            user.put("uid", cursor.getString(2));
            user.put("email", cursor.getString(3));
            user.put("name", cursor.getString(4));
            user.put("image", cursor.getString(5));
            user.put("user_balance", cursor.getString(6));
            user.put("description", cursor.getString(7));


        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}