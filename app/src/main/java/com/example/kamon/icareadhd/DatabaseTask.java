package com.example.kamon.icareadhd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamon on 7/2/2560.
 */

public class DatabaseTask extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyTask";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Task";
    public static final String COL_TNAME = "Taskname";
    public static final String COL_POINT = "Point";
    public static final String COL_STA = "Status";
    public static final String COL_CAT = "Category";


    public DatabaseTask(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TNAME + " TEXT, " + COL_POINT + " INT, "+ COL_STA + " TEXT, "+ COL_CAT + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_TNAME + ", " + COL_POINT + ", "+ COL_STA + ", "
                + COL_CAT + ") VALUES ('Do homework', '100', 'No', 'Academic');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
