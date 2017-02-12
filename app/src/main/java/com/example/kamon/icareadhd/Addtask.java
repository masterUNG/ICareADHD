package com.example.kamon.icareadhd;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Kamon on 7/2/2560.
 */

public class Addtask extends Activity {
    DatabaseTask mHelper;
    SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask);
        mHelper = new DatabaseTask(this);
        mDb = mHelper.getWritableDatabase();

        final EditText editTaskname = (EditText) findViewById(R.id.editTaskname);
        final EditText editTaskpoint = (EditText) findViewById(R.id.editTaskpoint);
        final EditText editTaskCategory = (EditText) findViewById(R.id.editTaskCategory);

        final Button buttonAdd = (Button) findViewById(R.id.button_Addnewtask);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = editTaskname.getText().toString();
                String point = editTaskpoint.getText().toString();
                String cat = editTaskCategory.getText().toString();

                if (name.length() != 0 && point.length() != 0 && cat.length() != 0) {

                    Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseTask.TABLE_NAME
                            + " WHERE " + DatabaseTask.COL_TNAME + "='" + name + "'"
                            + " AND " + DatabaseTask.COL_POINT + "='" + point + "'"
                            + " AND " + DatabaseTask.COL_CAT + "='" + cat + "'", null);

                    if (mCursor.getCount() == 0) {
                        mDb.execSQL("INSERT INTO " + DatabaseTask.TABLE_NAME + " ("
                                + DatabaseTask.COL_TNAME + ", " + DatabaseTask.COL_POINT
                                + ", " + DatabaseTask.COL_CAT + ", " + DatabaseTask.COL_STA + ") VALUES ('" + name
                                + "', '" + point + "', '" + cat + "', '" + "Yes" + "');");

                        editTaskname.setText("");
                        editTaskpoint.setText("");
                        editTaskCategory.setText("");

                        Toast.makeText(getApplicationContext(), "Add New Task Successful"
                                , Toast.LENGTH_SHORT).show();
                        buttonAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), "This task is already have"
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

