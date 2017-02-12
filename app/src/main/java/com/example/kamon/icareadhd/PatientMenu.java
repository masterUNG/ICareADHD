package com.example.kamon.icareadhd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 16/1/2560.
 */

public class PatientMenu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientmenu);

        Button button = (Button)findViewById(R.id.CreateNewPatientProfile_button);
        Button medication = (Button)findViewById(R.id.Medication_button);
        Button viewpatient = (Button)findViewById(R.id.ViewPatient_button);
        Button editpatient = (Button)findViewById(R.id.EditPatientProfile_button);
        Button createpatient = (Button)findViewById(R.id.CreateNewPatientProfile_button);
        Button selfass = (Button)findViewById(R.id.SelfAssessment_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),patient.class);
                startActivity(i);
            }
        });
        medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Medication.class);
                startActivity(i);
            }
        });
        viewpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewPatient.class);
                startActivity(i);
            }
        });
        editpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditPatient.class);
                startActivity(i);
            }
        });
        createpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),patient.class);
                startActivity(i);
            }
        });
        selfass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Selfassign.class);
                startActivity(i);
            }
        });

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
