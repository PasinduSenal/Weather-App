package com.anioncode.wheatheraplication.User;


import android.provider.BaseColumns;

public final class User{
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private User() {}

    /* Inner class that defines the table contents */
    public static class user implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1= "Phone";
        public static final String COLUMN_2= "UserName";
        public static final String COLUMN_3= "DateofBirth";
        public static final String COLUMN_4= "Password";


    }
}