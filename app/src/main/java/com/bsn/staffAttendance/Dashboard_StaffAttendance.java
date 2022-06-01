package com.bsn.staffAttendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.R;

public class Dashboard_StaffAttendance extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    Button buttontake_attendance,btnStaffAttendance_monthwise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_staff_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Staff Attendance Dashboard");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        buttontake_attendance=findViewById(R.id.buttontake_attendance);
        btnStaffAttendance_monthwise=findViewById(R.id.btnStaffAttendance_monthwise);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());

        buttontake_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard_StaffAttendance.this,UpdateDetails_StaffAttendance.class));
            }
        });

        btnStaffAttendance_monthwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard_StaffAttendance.this,Date_Wise_Attendance.class));
            }
        });
    }
}