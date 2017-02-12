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
 * Created by Kamon on 12/1/2560.
 */

public class Home extends Activity{
    DatabaseTask mHelper2;
    SQLiteDatabase mDb2;
    Cursor mCursor2;
    ListView listTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mHelper2 = new DatabaseTask(this);
        mDb2 = mHelper2.getReadableDatabase();
        mCursor2 = mDb2.rawQuery("SELECT * FROM " + DatabaseTask.TABLE_NAME, null);

        ArrayList<String> arr_listT = new ArrayList<String>();
        mCursor2.moveToFirst();
        while(!mCursor2.isAfterLast() ){
            arr_listT.add("Name : " + mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_TNAME))
                    + "\t\t\t" + mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_POINT))+"points");
            mCursor2.moveToNext();
        }
        ArrayAdapter<String> adapterDirT = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_reward, arr_listT);

        listTask = (ListView)findViewById(R.id.listtaskH);
        listTask.setAdapter(adapterDirT);
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor2.moveToPosition(arg2);
                String taskname = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_TNAME));
                String taskpoints = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_POINT));
                String status = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_STA));
                String category = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_CAT));

                AlertDialog.Builder builderT = new AlertDialog.Builder(Home.this);
                builderT.setTitle("Task information");
                builderT.setMessage("Name : " + taskname + "\n\nPoints : " + taskpoints + "\n\nCategory : " + category);
                builderT.setNeutralButton("OK", null);
                builderT.show();
            }
        });

        Button patient = (Button)findViewById(R.id.Patient_Button);
        Button home = (Button)findViewById(R.id.Home_button);
        Button followup = (Button)findViewById(R.id.FollowUp_button);
        Button reward = (Button)findViewById(R.id.Reward_button);
        Button addtask = (Button)findViewById(R.id.button_addtaskH);

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
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Addtask.class);
                startActivity(i);
            }
        });
    }
}
