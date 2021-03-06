package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.mydiary.NoteDatabase.DATABASE_VERSION;
import static com.example.mydiary.NoteDatabase.TABLE_NOTE;
import static com.example.mydiary.NoteDatabase.TAG;
import static com.example.mydiary.NoteDatabase.println;

class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        println("creating database [" + AppConstants.DATABASE_NAME + "].");

        println("creating table [" + TABLE_NOTE + "].");

        String DROP_SQL = "drop table if exists " + TABLE_NOTE;
        try {
            db.execSQL(DROP_SQL);
        } catch (Exception e) {
            Log.e(TAG, "Exception in DROP_SQL", e);
        }

        String CREATE_SQL = "create table " + TABLE_NOTE + "("
                + " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + " WEATHER TEXT DEFAULT '', "
                + " ADDRESS TEXT DEFAULT '', "
                + " LOCATION_X TEXT DEFAULT '', "
                + " LOCATION_Y TEXT DEFAULT '', "
                + " CONTENTS TEXT DEFAULT '', "
                + " MOOD TEXT, "
                + " PICTURE TEXT DEFAULT '', "
                + " CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + " MODIFY_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                + ")";

        try {
            db.execSQL(CREATE_SQL);
        } catch (Exception e) {
            Log.d(TAG, "Exception in CREATE_SQL", e);
        }

        String CREATE_INDEX_SQL = "create index " + TABLE_NOTE + "_IDX ON " + TABLE_NOTE + "(" + "CREATE_DATE" + ")";
        try {
            db.execSQL(CREATE_INDEX_SQL);
        } catch (Exception e) {
            Log.e(TAG, "Exception in CREATE_INDEX_SQL", e);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        println("opened database [" + AppConstants.DATABASE_NAME + "].");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("Upgrading database from version " + oldVersion + " to " + newVersion + ".");
    }
}
