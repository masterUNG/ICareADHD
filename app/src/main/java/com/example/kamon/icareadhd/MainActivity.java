package com.example.kamon.icareadhd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatabaseUser mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseUser(this);
        mDb = mHelper.getReadableDatabase();
        Button button = (Button)findViewById(R.id.login_buttoncreacc);
        Button button2 = (Button)findViewById(R.id.login_buttonlogin);
        final EditText Uname = (EditText) findViewById(R.id.login_uname);
        final EditText Password = (EditText) findViewById(R.id.login_password);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = Uname.getText().toString();
                String pass = Password.getText().toString();
                /**Cursor mCursor = mDb.rawQuery("SELECT * FROM my_table");
                 mCursor = db.rawQuery("SELECT email FROM" +DatabaseUser.TABLE_NAME+ "WHERE"+ DatabaseUser.COL_EMAIL + "='" +uname+ "AND" + DatabaseUser.COL_PASS + "='" + pass, new String[] {uname, pass});
                 if(mCursor.moveToFirst()) {
                 Toast.makeText(getApplicationContext(), "Log in Success", Toast.LENGTH_SHORT).show();*/
                Intent i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
                /**} else {
                 Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                 }*/
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
    }
}
