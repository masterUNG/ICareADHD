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
 * Created by Kamon on 20/1/2560.
 */

public class UpdatePatient extends Activity{
    DatabasePatient mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    String name, lastname, illness;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatepatient);

        name = getIntent().getExtras().getString("NAME");
        lastname = getIntent().getExtras().getString("LASTNAME");
        illness = getIntent().getExtras().getString("ILLNESS");

        mHelper = new DatabasePatient(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePatient.TABLE_NAME
                + " WHERE " + DatabasePatient.COL_NAME + "='" + name + "'"
                + " AND " + DatabasePatient.COL_LASTNAME  + "='" + lastname + "'"
                + " AND " + DatabasePatient.COL_ILL + "='" + illness  + "'", null);

        final EditText editName = (EditText)findViewById(R.id.editName);
        editName.setText(name);
        final EditText editLastName = (EditText)findViewById(R.id.editLastName);
        editLastName.setText(lastname);
        final EditText editIll = (EditText)findViewById(R.id.editIll);
        editIll.setText(illness);

        Button buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameUpdate = editName.getText().toString();
                String lastnameUpdate = editLastName.getText().toString();
                String illUpdate = editIll.getText().toString();

                if(name.length() != 0 && lastname.length() != 0 && illness.length() != 0 ) {
                    mDb.execSQL("UPDATE " + DatabasePatient.TABLE_NAME  + " SET "
                            + DatabasePatient.COL_NAME + "='" + nameUpdate + "', "
                            + DatabasePatient.COL_LASTNAME + "='" + lastnameUpdate + "', "
                            + DatabasePatient.COL_ILL + "='" + illUpdate
                            + "' WHERE " + DatabasePatient.COL_NAME + "='" + name + "'"
                            + " AND " + DatabasePatient.COL_LASTNAME + "='" + lastname + "'"
                            + " AND " + DatabasePatient.COL_ILL + "='" + illness + "';");

                    Toast.makeText(getApplicationContext(), "Edit Successful"
                            , Toast.LENGTH_SHORT).show();

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Please fill out all the fields"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonNameClear = (Button)findViewById(R.id.buttonNameClear);
        buttonNameClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editName.setText("");
            }
        });

        Button buttonLastNameClear = (Button)findViewById(R.id.buttonLastNameClear);
        buttonLastNameClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editLastName.setText("");
            }
        });

        Button buttonSchoolClear = (Button)findViewById(R.id.buttonIllClear);
        buttonSchoolClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editIll.setText("");
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}
