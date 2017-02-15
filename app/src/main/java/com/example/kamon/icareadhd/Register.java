package com.example.kamon.icareadhd;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class Register extends Activity {

    //Explicit
    private DatabaseUser mHelper;
    private SQLiteDatabase mDb;
    private GoogleApiClient client;
    private int intLanguage = 0, typeAnInt = 0;
    private MyConstant myConstant;
    private String[] userStrings, creatingStings, firstStrings, lastStrings, emailStrings, passStrings, doneStrings;
    private EditText editFName, editLName, editEMail, editPass;
    private Spinner spinner;
    private String nameString, surnameString, emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //Bind Widget
        spinner = (Spinner) findViewById(R.id.spinner);
        editFName = (EditText) findViewById(R.id.edit_fname);
        editLName = (EditText) findViewById(R.id.edit_lname);
        editEMail = (EditText) findViewById(R.id.edit_email);
        editPass = (EditText) findViewById(R.id.edit_pass);
        final TextView creating = (TextView) findViewById(R.id.Register_head);

        //For Spinner
        setupSpinner();


        mHelper = new DatabaseUser(this);
        mDb = mHelper.getWritableDatabase();




        //set up
        myConstant = new MyConstant();
        userStrings = myConstant.getUserTypeButtonRegister();
        creatingStings = myConstant.getCreatingButtonRegister();
        firstStrings = myConstant.getFirstNameButtonRegister();
        lastStrings = myConstant.getLastNameButtonRegister();
        emailStrings = myConstant.getEmailButtonRegister();
        passStrings = myConstant.getPasswordButtonRegister();
        doneStrings = myConstant.getDoneButtonRegister();
        creatingStings = myConstant.getCreatingButtonRegister();

        //show view
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DatabaseUser.DB_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM languageTABLE", null);
        cursor.moveToFirst();
        intLanguage = Integer.parseInt(cursor.getString(1));


        editFName.setHint(firstStrings[intLanguage]);
        editLName.setHint(lastStrings[intLanguage]);
        editEMail.setHint(emailStrings[intLanguage]);
        editPass.setHint(passStrings[intLanguage]);
        creating.setText(creatingStings[intLanguage]);


        Button buttonDone = (Button) findViewById(R.id.Register_buttondone);

        buttonDone.setText(doneStrings[intLanguage]);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Value from Edit Text
                nameString = editFName.getText().toString().trim();
                surnameString = editLName.getText().toString().trim();
                emailString = editEMail.getText().toString().trim();
                passwordString = editPass.getText().toString().trim();

                //Check Space
                if (nameString.equals("") || surnameString.equals("") ||
                        emailString.equals("") || passwordString.equals("")) {
                    //Have Space
                    Toast.makeText(Register.this, "Have Space", Toast.LENGTH_SHORT).show();
                } else {
                    //No Space
                    uploadValueToServer();
                }

            }   // onClick
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    } //Main Class

    private void uploadValueToServer() {

        try {

            MyUploadUser myUploadUser = new MyUploadUser(Register.this, emailString,
                    passwordString, Integer.toString(typeAnInt),
                    nameString, surnameString, "testTime");
            myUploadUser.execute();

            String s = myUploadUser.get();
            Log.d("12febV1", "Result ==> " + s);

        } catch (Exception e) {
            Log.d("12febV1", "e upload ==> " + e.toString());
        }

    }   // upload

    private void setupSpinner() {
        String[] strings = new String[]{"Parents", "Teacher", "Doctor"};

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeAnInt = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                typeAnInt = 0;
            }
        });

    }   //setupSpinner


    public void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        mHelper.close();
        mDb.close();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }
}