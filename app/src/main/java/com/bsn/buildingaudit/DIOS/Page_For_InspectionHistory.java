package com.bsn.buildingaudit.DIOS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.R;

public class Page_For_InspectionHistory extends AppCompatActivity {
CardView chemistryLabBodyCard;
    ApplicationController applicationController;
    TextView textView8,schoolAddress,schoolName;
    String localSchoolId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_for_inspection_history);
        applicationController= (ApplicationController) getApplication();
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
        Log.d("TAG", "onCreate: "+localSchoolId);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        textView8=findViewById(R.id.textView8);
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());
        findViewById(R.id.chemistryLabBodyCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Page_For_InspectionHistory.this, Diios_Panel_Under_School_dashboard.class);
                startActivity(i);
            }
        });
    }
}