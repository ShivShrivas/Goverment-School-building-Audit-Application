package com.bsn.buildingaudit.DIOS.SchoolStaff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.R;

public class Dios_StaffDetails extends AppCompatActivity {
CardView staffsenctionAndWorking,allStaffTraing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dios_staff_details);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        staffsenctionAndWorking=findViewById(R.id.staffsenctionAndWorking);
        allStaffTraing=findViewById(R.id.allStaffTraing);
        staffsenctionAndWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dios_StaffDetails.this, Staff_Sanction_and_Working.class));
            }
        });
        allStaffTraing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dios_StaffDetails.this, All_Staff_Traing_Details.class));
            }
        });
    }
}