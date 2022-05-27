package com.bsn.buildingaudit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Principal_Dashboard extends AppCompatActivity {
    TextView userName, schoolAddress, schoolName;
Button button1;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_dashboard);
        applicationController = (ApplicationController) getApplication();

        schoolAddress = findViewById(R.id.schoolAddress);
        schoolName = findViewById(R.id.schoolName);
        button1 = findViewById(R.id.button);

        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal_Dashboard.this,DashBoard.class));

            }
        });
    }
}