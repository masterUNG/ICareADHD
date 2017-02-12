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
 * Created by Kamon on 27/1/2560.
 */

public class ViewUser extends Activity {
    DatabasePatient mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listUser;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewuser);

        mHelper = new DatabasePatient(this);
        mDb = mHelper.getReadableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseUser.TABLE_NAME, null);

        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast() ){
            arr_list.add("Name : " + mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_FNAME))
                    + "\t\t" + mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_LNAME))
                    + "\nType : " + mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_TYPE))
                    + "\nEmail : " + mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_EMAIL))
                    + "\nPassword : " + mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_PASS)));
            mCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext(), R.layout.my_listview, arr_list);

        listUser = (ListView)findViewById(R.id.listUser);
        listUser.setAdapter(adapterDir);
        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                String name = mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_FNAME));
                String lastname = mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_LNAME));
                String type = mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_TYPE));
                String email = mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_EMAIL));
                String password = mCursor.getString(mCursor.getColumnIndex(DatabaseUser.COL_PASS));

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewUser.this);
                builder.setTitle("User profile");
                builder.setMessage("Firstname : " + name + "\n\nSurname : " + lastname + "\n\nType : " + type + "\n\nEmail : " + email+ "\n\nPassword : " + password);
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
