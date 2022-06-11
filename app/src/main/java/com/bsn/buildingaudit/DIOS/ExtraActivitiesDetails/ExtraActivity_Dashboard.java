package com.bsn.buildingaudit.DIOS.ExtraActivitiesDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.R;

public class ExtraActivity_Dashboard extends AppCompatActivity {
CardView ExtraActivitiesDetails,participationsCardView,Ncc_activiies_Details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_dashboard);
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
        ExtraActivitiesDetails=findViewById(R.id.ExtraActivitiesDetails);
        participationsCardView=findViewById(R.id.participationsCardView);
        Ncc_activiies_Details=findViewById(R.id.Ncc_activiies_Details);

        ExtraActivitiesDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExtraActivity_Dashboard.this,Extra_Activities_Details.class));
            }
        });

        participationsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExtraActivity_Dashboard.this,Participation_Details.class));
            }
        });

        Ncc_activiies_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExtraActivity_Dashboard.this,Ncc_Details.class));
            }
        });

    }
}