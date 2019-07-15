package com.example.weatherforecast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountDB.db";
    public static final String ACCOUNTS = "Accounts";
    public static final String COLUMM_USERNAME = "Username";
    public static final String COLUMM_PASSWORD = "Password";

    public  MyDBHandler(Context context ,String name ,SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,DATABASE_NAME, factory, DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS +
                "(" + COLUMM_USERNAME + " TEXT," +
                COLUMM_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addUser( UserData userData)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMM_USERNAME,userData.getUsername());
        values.put(COLUMM_PASSWORD,userData.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(ACCOUNTS,null,values);
        db.close();
    }
    public UserData findUSER(String username){
        String query = "SELECT * FROM " + ACCOUNTS +" WHERE " +
                COLUMM_USERNAME + "= \""  + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        UserData queryData = new UserData();
        if(cursor.moveToFirst()){
            queryData.setUsername(cursor.getString(0));
            queryData.setPassword(cursor.getString(1));
            cursor.close();
        }
        else{
            queryData = null;
        }
        db.close();
        return queryData;


    }
}
