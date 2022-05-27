package com.bsn.staffAttendance;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.StaffAttendanceAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.Staff_Details_Model;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetails_StaffAttendance extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    RecyclerView recyclerViewStaffAttendance;
    StaffAttendanceAdapter adapter;
    Toolbar toolbar;
    ArrayList<Staff_Details_Model> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_staff_attendance);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        recyclerViewStaffAttendance=findViewById(R.id.recyclerViewStaffAttendance);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewStaffAttendance.setLayoutManager(new LinearLayoutManager(this));
        Staff_Details_Model staff_details_model=new Staff_Details_Model("Pradeep","Teacher");
        Staff_Details_Model staff_details_model1=new Staff_Details_Model("Radha","Teacher");
        Staff_Details_Model staff_details_model2=new Staff_Details_Model("Shaym","Principal");
        Staff_Details_Model staff_details_model3=new Staff_Details_Model("Pankaj Tripathi","Teacher");
        Staff_Details_Model staff_details_model4=new Staff_Details_Model("Kuldeep","Teacher");
        Staff_Details_Model staff_details_model5=new Staff_Details_Model("Ravi Singh","Teacher");

        arrayList.add(staff_details_model);
        arrayList.add(staff_details_model1);
        arrayList.add(staff_details_model2);
        arrayList.add(staff_details_model3);
        arrayList.add(staff_details_model4);
        arrayList.add(staff_details_model5);

        recyclerViewStaffAttendance.setAdapter(new StaffAttendanceAdapter(this,arrayList));




    }
}