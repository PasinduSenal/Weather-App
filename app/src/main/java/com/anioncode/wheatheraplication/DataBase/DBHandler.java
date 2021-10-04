package com.anioncode.wheatheraplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.anioncode.wheatheraplication.User.User;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.user.TABLE_NAME + " (" +
                    User.user._ID + " INTEGER PRIMARY KEY," +
                    User.user.COLUMN_1 + " TEXT," +
                   User.user.COLUMN_2 + " TEXT," +
                    User.user.COLUMN_3 + " TEXT," +
                    User.user.COLUMN_4 + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.user.TABLE_NAME;



    public long addUser(String phone, String uname, String dob, String pw) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(User.user.COLUMN_1, phone);
        values.put(User.user.COLUMN_2, uname);
        values.put(User.user.COLUMN_3, dob);
        values.put(User.user.COLUMN_4, pw);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(User.user.TABLE_NAME, null, values);
        return newRowId;
    }





    public Boolean loginUser(String phone, String pw){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                User.user.COLUMN_1,
                User.user.COLUMN_4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = User.user.COLUMN_1 + " = ? AND " + User.user.COLUMN_4 + " = ?";
        String[] selectionArgs = { phone,pw };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
               User.user.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                User.user.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        List validUser = new ArrayList();
        while(cursor.moveToNext()) {
            String validuser = cursor.getString(
                    cursor.getColumnIndexOrThrow(User.user.COLUMN_1));
            validUser.add(validuser);
        }
        cursor.close();

        if(validUser.isEmpty()){
            return  false;
        }

        else{
            return true;
        }


    }




}




