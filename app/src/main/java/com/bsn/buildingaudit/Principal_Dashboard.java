package com.bsn.buildingaudit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.DIOS.DIOS_Dashboard;
import com.bsn.staffAttendance.Dashboard_StaffAttendance;

public class Principal_Dashboard extends AppCompatActivity {
    TextView userName, schoolAddress, schoolName;
Button button1,btnStaffAttendance;
LinearLayout diosLay;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_dashboard);
        applicationController = (ApplicationController) getApplication();

        schoolAddress = findViewById(R.id.schoolAddress);
        schoolName = findViewById(R.id.schoolName);
        button1 = findViewById(R.id.button);
        diosLay=findViewById(R.id.diosLay);
        btnStaffAttendance = findViewById(R.id.btnStaffAttendance);

        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal_Dashboard.this,DashBoard.class));

            }
        });

        btnStaffAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal_Dashboard.this, Dashboard_StaffAttendance.class));

            }
        });

        diosLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal_Dashboard.this, DIOS_Dashboard.class));
            }
        });
    }
}