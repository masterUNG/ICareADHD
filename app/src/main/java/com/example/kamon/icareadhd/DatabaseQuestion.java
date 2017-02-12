package com.example.kamon.icareadhd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamon on 27/1/2560.
 */

public class DatabaseQuestion extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyQuestion";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "ConnerIV";
    public static final String COL_Q = "Question";

    public DatabaseQuestion(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_Q + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_Q + ") " +
                "VALUES ('Forgets to turn in completed work?');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_Q + ") " +
                "VALUES ('Is perfect in every way?');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_Q + ") " +
                "VALUES ('Fidgets or squirms in seat?');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_Q + ") " +
                "VALUES ('Is one of the last to be picked for teams or games?');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
