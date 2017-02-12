package com.example.kamon.icareadhd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamon on 1/2/2560.
 */

public class DatabaseReward extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyReward";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Reward";
    public static final String COL_RNAME = "Rewardname";
    public static final String COL_POINT = "Point";
    public static final String COL_STA = "Status";
    public static final String COL_DES = "Description";


    public DatabaseReward(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_RNAME + " TEXT, " + COL_POINT + " INT, "+ COL_STA + " TEXT, "+ COL_DES + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_RNAME + ", " + COL_POINT + ", "+ COL_STA + ", "
                + COL_DES + ") VALUES ('Disney Doll', '500', 'In stock', 'Get your favorite disney doll');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
