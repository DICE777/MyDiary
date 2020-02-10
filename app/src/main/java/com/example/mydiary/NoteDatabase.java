package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NoteDatabase {
    public static final String TAG = NoteDatabase.class.getCanonicalName();

    private static NoteDatabase database;
    public static String TABLE_NOTE = "NOTE";
    public static int DATABASE_VERSION = 1;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private NoteDatabase(Context context) {
        this.context = context;
    }

    public static NoteDatabase getInstance(Context context) {
        if (database == null) {
            database = new NoteDatabase(context);
        }
        return database;
    }

    public boolean open() {
        println("opening database [" + AppConstants.DATABASE_NAME + "]");

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    public static void println(String msg) {
        Log.d(TAG, msg);
    }
}
