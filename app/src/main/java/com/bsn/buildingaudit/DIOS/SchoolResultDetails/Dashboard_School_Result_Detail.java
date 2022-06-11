package com.bsn.buildingaudit.DIOS.SchoolResultDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.R;

public class Dashboard_School_Result_Detail extends AppCompatActivity {
CardView currentResult,last_threeYearResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        setContentView(R.layout.activity_dashboard_school_result_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        last_threeYearResult=findViewById(R.id.ExtraActivitiesDetails);
        currentResult=findViewById(R.id.Ncc_activiies_Details);
        currentResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard_School_Result_Detail.this,College_Student_Result.class));
            }
        });
 last_threeYearResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard_School_Result_Detail.this,Student_Result_Details_last_three_years.class));
            }
        });

    }
}