package com.example.kamon.icareadhd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by User on 19/1/2560.
 */

public class Medication extends Activity {
    DatabaseMedication mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listMed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication);

        mHelper = new DatabaseMedication(this);
        mDb = mHelper.getReadableDatabase();
        mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseMedication.TABLE_NAME, null);

        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast() ){
            arr_list.add("Name : " + mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_MNAME))
                    + "\n" + mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_DOSES))+" doses"
                    + "\t\t\t" + mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_TYPE))
                    + "\n" + "Date :" + mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_SDATE))
                    + "-" + "\t\t" + mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_EDATE)));
            mCursor.moveToNext();
        }
        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_reward, arr_list);

        listMed = (ListView)findViewById(R.id.listview_medM);
        listMed.setAdapter(adapterDir);
        listMed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);
                String medicationname = mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_MNAME));
                String doses = mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_DOSES));
                String type = mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_TYPE));
                String sdate = mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_SDATE));
                String edate = mCursor.getString(mCursor.getColumnIndex(DatabaseMedication.COL_EDATE));

                AlertDialog.Builder builder = new AlertDialog.Builder(Medication.this);
                builder.setTitle("Medication Information");
                builder.setMessage("Name : " + medicationname + "\n\nDoses : " + doses + type + "\n\nDate : " + sdate + edate );
                builder.setNeutralButton("OK", null);
                builder.show();
            }
        });

        Button patient = (Button)findViewById(R.id.Patient_Button);
        Button home = (Button)findViewById(R.id.Home_button);
        Button followup = (Button)findViewById(R.id.FollowUp_button);
        Button reward = (Button)findViewById(R.id.Reward_button);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PatientMenu.class);
                startActivity(i);
            }
        });
        followup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Follow_up.class);
                startActivity(i);
            }
        });
        reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Reward.class);
                startActivity(i);
            }
        });
    }
}
