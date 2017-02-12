package com.example.kamon.icareadhd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 19/1/2560.
 */

public class Analytics extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);

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
