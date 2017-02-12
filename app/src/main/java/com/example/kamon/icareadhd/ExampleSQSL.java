/**
package com.example.kamon.icareadhd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExampleSQSL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_sqsl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        kamonZa();
    }
    public void kamonZa(){

        Log.d("Kuy"," 01");
        Connection conn = null;
//        String url = "jdbc:jtds:sqlserver://122.155.18.18/";
//        String dbName = "TEST;Instance=icareadhdi_adhd;";

        String userName = "icareadhdi";
        String password = "pzVCj!-7-h";
        String url = "jdbc:jtds:sqlserver://122.155.18.18:21/icareadhdi_adhd;instance=SQLEXPRESS;";

        TextView tv = (TextView) findViewById(R.id.txt);

        Log.d("Kuy"," koko1");
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Log.d("Kuy"," 1");
            conn = DriverManager.getConnection(url,userName,password);
            Log.d("Kuy"," 2");
            Statement stmt = conn.createStatement();
            Log.d("Kuy"," 3");
            ResultSet rs = stmt.executeQuery("SELECT Name FROM member");
            Log.d("Kuy"," 4");

            while (rs.next()) {
                String lastName = rs.getString("Name");
                tv.setText(lastName);
            }

            conn.close();
        } catch (Exception e) {
            Log.d("Kuy",e.getMessage()+" kokoba");
            tv.setText("KUY");
        }
    }

}
*/