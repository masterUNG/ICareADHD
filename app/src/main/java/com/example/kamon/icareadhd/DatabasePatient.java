package com.example.kamon.icareadhd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamon on 20/1/2560.
 */

public class DatabasePatient extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyPatient";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Patient";
    public static final String COL_NAME = "name";
    public static final String COL_LASTNAME = "last_name";
    public static final String COL_ILL = "illness";

    public DatabasePatient(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, " + COL_LASTNAME + " TEXT, " + COL_ILL + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_NAME + ", " + COL_LASTNAME + ", "
                + COL_ILL + ") VALUES ('Ping'" + ", 'Bawanpak', 'Cold');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
