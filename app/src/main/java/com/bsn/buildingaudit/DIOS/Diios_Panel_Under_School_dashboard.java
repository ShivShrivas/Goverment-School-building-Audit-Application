package com.bsn.buildingaudit.DIOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.DIOS.SchoolResultDetails.Dashboard_School_Result_Detail;
import com.bsn.buildingaudit.DIOS.SchoolStaff.Dios_StaffDetails;
import com.bsn.buildingaudit.R;

public class Diios_Panel_Under_School_dashboard extends AppCompatActivity {
CardView dashCardBtn_SchoolDetails,dashCardBtn_SchoolStaff,dashCardBtn_SchoolResultDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diios_panel_under_school_dashboard);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        dashCardBtn_SchoolDetails=findViewById(R.id.dashCardBtn_SchoolDetails);
        dashCardBtn_SchoolStaff=findViewById(R.id.dashCardBtn_SchoolStaff);
        dashCardBtn_SchoolResultDetails=findViewById(R.id.dashCardBtn_SchoolResultDetails);


        dashCardBtn_SchoolDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Diios_Panel_Under_School_dashboard.this, "This module is not ready yet!!", Toast.LENGTH_SHORT).show();
            }
        });
        dashCardBtn_SchoolStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Dios_StaffDetails.class));
            }
        });
        dashCardBtn_SchoolResultDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Dashboard_School_Result_Detail.class));
            }
        });


    }
}