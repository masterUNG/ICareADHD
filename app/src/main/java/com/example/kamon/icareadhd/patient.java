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
 * Created by Kamon on 13/1/2560.
 */

public class patient extends Activity {
    DatabasePatient mHelper;
    SQLiteDatabase mDb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpatient);

        mHelper = new DatabasePatient(this);
        mDb = mHelper.getWritableDatabase();

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editLastName = (EditText)findViewById(R.id.editLastName);
        final EditText editIllness = (EditText)findViewById(R.id.editIll);

        Button buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = editName.getText().toString();
                String lastname = editLastName.getText().toString();
                String illness = editIllness.getText().toString();

                if(name.length() != 0 && lastname.length() != 0 && illness.length() != 0 ) {

                    Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePatient.TABLE_NAME
                            + " WHERE " + DatabasePatient.COL_NAME + "='" + name + "'"
                            + " AND " + DatabasePatient.COL_LASTNAME + "='" + lastname + "'"
                            + " AND " + DatabasePatient.COL_ILL + "='" + illness + "'", null);

                    if(mCursor.getCount() == 0) {
                        mDb.execSQL("INSERT INTO " + DatabasePatient.TABLE_NAME + " ("
                                + DatabasePatient.COL_NAME + ", " + DatabasePatient.COL_LASTNAME
                                + ", " + DatabasePatient.COL_ILL + ") VALUES ('" + name
                                + "', '" + lastname + "', '" + illness + "');");

                        editName.setText("");
                        editLastName.setText("");
                        editIllness.setText("");

                        Toast.makeText(getApplicationContext(), "Add Patient Successful"
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "This patient is already have"
                                , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill out all the fields"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}
