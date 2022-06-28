package com.bsn.buildingaudit.DIOS.co_curricular_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.R;

public class Co_Curricular_Dashboard extends AppCompatActivity {
CardView game_details,redCrossSociety,scoutAndGuide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_curricular_dashboard);
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
        game_details=findViewById(R.id.ExtraActivitiesDetails);
        redCrossSociety=findViewById(R.id.Ncc_activiies_Details);
        scoutAndGuide=findViewById(R.id.participationsCardView);

        game_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Co_Curricular_Dashboard.this,Game_Details.class));
            }
        });

        redCrossSociety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Co_Curricular_Dashboard.this,Red_Cross_Society.class));
            }
        });
        scoutAndGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Co_Curricular_Dashboard.this,Scount_and_Guide.class));
            }
        });

    }
}