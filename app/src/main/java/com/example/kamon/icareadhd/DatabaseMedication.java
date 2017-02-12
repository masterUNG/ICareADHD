package com.example.kamon.icareadhd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamon on 9/2/2560.
 */

public class DatabaseMedication extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyMed";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Medication";
    public static final String COL_MNAME = "Medicationname";
    public static final String COL_DOSES = "Doses";
    public static final String COL_TYPE = "Type";
    public static final String COL_SDATE = "Startdate";
    public static final String COL_EDATE = "Enddate";


    public DatabaseMedication(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_MNAME + " TEXT, " + COL_DOSES + " INT, " + COL_TYPE + " TEXT, " + COL_SDATE + " DATE, " + COL_EDATE + " DATE);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_MNAME + ", " + COL_DOSES + ", "+ COL_TYPE + ", " + COL_SDATE + ", "
                + COL_EDATE + ") VALUES ('Am', '2', 'After eating', '9 Feb 2017', '16 Feb 2017');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
