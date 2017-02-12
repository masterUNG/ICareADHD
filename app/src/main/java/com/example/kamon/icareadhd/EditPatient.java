package com.example.kamon.icareadhd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kamon on 20/1/2560.
 */

public class EditPatient extends Activity {
    DatabasePatient mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listStudent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpatient);
    }

    public void onResume() {
        super.onResume();

        mHelper = new DatabasePatient(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePatient.TABLE_NAME, null);

        listStudent = (ListView)findViewById(R.id.listPatient);
        listStudent.setAdapter(updateListView());
        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                String name = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_NAME));
                String lastname = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_LASTNAME));
                String illness = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_ILL));

                Intent intent = new Intent(getApplicationContext(), UpdatePatient.class);
                intent.putExtra("NAME", name);
                intent.putExtra("LASTNAME", lastname);
                intent.putExtra("ILLNESS", illness);
                startActivity(intent);
            }
        });

        listStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                AlertDialog.Builder builder = new AlertDialog.Builder(EditPatient.this);
                builder.setTitle("Patient Delete");
                builder.setMessage("Do you want to delete this profile?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String name = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_NAME));
                        String lastname = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_LASTNAME));
                        String illness = mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_ILL));

                        mDb.execSQL("DELETE FROM " + DatabasePatient.TABLE_NAME
                                + " WHERE " + DatabasePatient.COL_NAME + "='" + name + "'"
                                + " AND " + DatabasePatient.COL_LASTNAME + "='" + lastname + "'"
                                + " AND " + DatabasePatient.COL_ILL + "='" + illness + "';");

                        mCursor.requery();

                        listStudent.setAdapter(updateListView());

                        Toast.makeText(getApplicationContext(),"Delete successful"
                                , Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

                return true;
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }

    public ArrayAdapter<String> updateListView() {
        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast()){
            arr_list.add("Firstname : " + mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_NAME)) + "\t\t"
                    + mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_LASTNAME)) + "\n"
                    + "Illness : " + mCursor.getString(mCursor.getColumnIndex(DatabasePatient.COL_ILL)));
            mCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext()
                , R.layout.my_listview, arr_list);
        return adapterDir;
    }
}
