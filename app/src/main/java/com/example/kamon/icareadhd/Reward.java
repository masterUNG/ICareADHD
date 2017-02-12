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

public class Reward extends Activity {
    DatabaseReward mHelper;
    DatabaseTask mHelper2;
    SQLiteDatabase mDb;
    SQLiteDatabase mDb2;
    Cursor mCursor;
    Cursor mCursor2;
    ListView listReward;
    ListView listTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward);

        mHelper = new DatabaseReward(this);
        mDb = mHelper.getReadableDatabase();
        mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseReward.TABLE_NAME, null);

        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast() ){
            arr_list.add("Name : " + mCursor.getString(mCursor.getColumnIndex(DatabaseReward.COL_RNAME))
                    + "\t\t\t" + mCursor.getString(mCursor.getColumnIndex(DatabaseReward.COL_POINT))+"points");
            mCursor.moveToNext();
        }
        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_reward, arr_list);

        listReward = (ListView)findViewById(R.id.listReward);
        listReward.setAdapter(adapterDir);
        listReward.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);
                String rewardname = mCursor.getString(mCursor.getColumnIndex(DatabaseReward.COL_RNAME));
                String rewardpoints = mCursor.getString(mCursor.getColumnIndex(DatabaseReward.COL_POINT));
                String status = mCursor.getString(mCursor.getColumnIndex(DatabaseReward.COL_STA));

                AlertDialog.Builder builder = new AlertDialog.Builder(Reward.this);
                builder.setTitle("Reward information");
                builder.setMessage("Name : " + rewardname + "\n\nPoints : " + rewardpoints + "\n\nStatus : " + status);
                builder.setNeutralButton("OK", null);
                builder.show();
            }
        });

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

        listTask = (ListView)findViewById(R.id.listtaskR);
        listTask.setAdapter(adapterDirT);
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor2.moveToPosition(arg2);
                String taskname = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_TNAME));
                String taskpoints = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_POINT));
                String status = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_STA));
                String category = mCursor2.getString(mCursor2.getColumnIndex(DatabaseTask.COL_CAT));

                AlertDialog.Builder builderT = new AlertDialog.Builder(Reward.this);
                builderT.setTitle("Task information");
                builderT.setMessage("Name : " + taskname + "\n\nPoints : " + taskpoints + "\n\nCategory : " + category);
                builderT.setNeutralButton("OK", null);
                builderT.show();
            }
        });

        Button patient = (Button)findViewById(R.id.Patient_Button);
        Button home = (Button)findViewById(R.id.Home_button);
        Button followup = (Button)findViewById(R.id.FollowUp_button);
        Button addreward = (Button)findViewById(R.id.Reward_addreward);
        Button addtask = (Button)findViewById(R.id.button_addnewtaskR);

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

        addreward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Addreward.class);
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
    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
        mHelper2.close();
        mDb2.close();
    }
}