package com.bsn.buildingaudit.DIOS.CampusBeautification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.R;

public class CampusBeautificationDashboard extends AppCompatActivity {
CardView campusMantinance,campusBeautification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_beautification_dashboard);

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
        campusBeautification=findViewById(R.id.campusBeautification);
        campusMantinance=findViewById(R.id.campusMantinance);


        campusMantinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CampusBeautificationDashboard.this, WhiteWashPage.class));
            }
        });

        campusBeautification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CampusBeautificationDashboard.this, Campus_Beautification_Details.class));
            }
        });
    }
}