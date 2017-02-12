package com.example.kamon.icareadhd;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Kamon on 27/1/2560.
 */

public class Selfassign extends Activity {
    DatabaseQuestion mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfassign);
        mHelper = new DatabaseQuestion(this);
        mDb = mHelper.getReadableDatabase();
        mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseQuestion.TABLE_NAME, null);
        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast() ){
            arr_list.add("Question : " + mCursor.getString(mCursor.getColumnIndex(DatabaseQuestion.COL_Q)));
            mCursor.moveToNext();
        }
        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext(), R.layout.my_listview, arr_list);
        listQuestion = (ListView)findViewById(R.id.listQuestion);
        listQuestion.setAdapter(adapterDir);
        listQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);
                String question = mCursor.getString(mCursor.getColumnIndex(DatabaseQuestion.COL_Q));
            }
        });
    }
    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}
