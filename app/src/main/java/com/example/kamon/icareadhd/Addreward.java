package com.example.kamon.icareadhd;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Kamon on 1/2/2560.
 */

public class Addreward extends Activity {
    DatabaseReward mHelper;
    SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addreward);
        mHelper = new DatabaseReward(this);
        mDb = mHelper.getWritableDatabase();

        final EditText editRewardname = (EditText) findViewById(R.id.editRewardname);
        final EditText editRewardpoint = (EditText) findViewById(R.id.editRewardpoint);
        final EditText editRewarddescription = (EditText) findViewById(R.id.editRewarddescription);

        final Button buttonAdd = (Button) findViewById(R.id.button_Addnewreward);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = editRewardname.getText().toString();
                String point = editRewardpoint.getText().toString();
                String des = editRewarddescription.getText().toString();

                if (name.length() != 0 && point.length() != 0 && des.length() != 0) {

                    Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseReward.TABLE_NAME
                            + " WHERE " + DatabaseReward.COL_RNAME + "='" + name + "'"
                            + " AND " + DatabaseReward.COL_POINT + "='" + point + "'"
                            + " AND " + DatabaseReward.COL_DES + "='" + des + "'", null);

                    if (mCursor.getCount() == 0) {
                        mDb.execSQL("INSERT INTO " + DatabaseReward.TABLE_NAME + " ("
                                + DatabaseReward.COL_RNAME + ", " + DatabaseReward.COL_POINT
                                + ", " + DatabaseReward.COL_DES + ") VALUES ('" + name
                                + "', '" + point + "', '" + des + "');");

                        editRewardname.setText("");
                        editRewardpoint.setText("");
                        editRewarddescription.setText("");

                        Toast.makeText(getApplicationContext(), "Add Reward Successful"
                                , Toast.LENGTH_SHORT).show();
                        buttonAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getApplicationContext(),Home.class);
                                startActivity(i);
                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), "This Reward is already have"
                                , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill out all the fields"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
};
