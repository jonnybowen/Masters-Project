package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Declare table and column names
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "collection_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);

        Log.d(TAG, "addData: Adding " + item + " to" + TABLE_NAME);

        // represents whether data was or wasn't inserted correctly
        long result = db.insert(TABLE_NAME, null, contentValues);

        // Error-handling. Incorrect data insertion will cause 'result' to be -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all data fro the database
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + name + "'.";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
