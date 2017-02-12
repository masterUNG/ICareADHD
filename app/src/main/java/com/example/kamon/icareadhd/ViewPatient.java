package com.example.kamon.icareadhd;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Kamon on 20/1/2560.
 */

public class ViewPatient extends Activity {
    DatabasePatient mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listStudent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpatient);

        mHelper = new DatabasePatient(this);
        mDb = mHelper.getReadableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePatient.TABLE_NAME, null);

        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast() ){
            arr_list.add("Firstname : " + mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_NAME))
                    + "\t\t" + mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_LASTNAME))
                    + "\nIllness : " + mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_ILL)));
            mCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext(), R.layout.my_listview, arr_list);

        listStudent = (ListView)findViewById(R.id.listPatient);
        listStudent.setAdapter(adapterDir);
        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                String name = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_NAME));
                String lastname = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_LASTNAME));
                String illness = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_ILL));

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewPatient.this);
                builder.setTitle("Patient profile");
                builder.setMessage("Firstname : " + name + "\n\nSurname : " + lastname + "\n\nIllness : " + illness);
                builder.setNeutralButton("OK", null);
                builder.show();
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}