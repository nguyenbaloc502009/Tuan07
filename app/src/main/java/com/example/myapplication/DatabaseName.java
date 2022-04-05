package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseName extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "nameManager";
    private static final String TABLE_NAMES = "names";
    private static final String KEY_NAME = "name";

    public DatabaseName(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAMES + "(" + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES);

        // Create tables again
        onCreate(db);
    }


    void addName(Name name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name.getName()); // Contact Name

        // Inserting Row
        db.insert(TABLE_NAMES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }




    public List<Name> getAllName() {
        List<Name> nameList = new ArrayList<Name>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Name name = new Name();

                name.setName(cursor.getString(0));
                nameList.add(name);
            } while (cursor.moveToNext());
        }

        return nameList;
    }


    public void deleteName(Name name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAMES, KEY_NAME + " like ?",
                new String[] { name.getName() });
        db.close();
    }


}