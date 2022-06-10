package com.bsn.buildingaudit.DIOS.SchoolStaff;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class Staff_Sanction_and_Working extends AppCompatActivity {
Button btnApprovalStaffSanction,btnRejectStaffSanction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_sanction_and_working);
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
        btnRejectStaffSanction=findViewById(R.id.btnRejectStaffSanction);
        btnApprovalStaffSanction=findViewById(R.id.btnApprovalStaffSanction);

        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("Sanctioned Data is not correct");
        myImageNameList2.add("Posted Data is not correct");
        myImageNameList2.add("Working Data is not correct");
        myImageNameList2.add("No. of attached staff Data is not correct");


        btnApprovalStaffSanction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogApprove(Staff_Sanction_and_Working.this,myImageNameList);
            }
        });

        btnRejectStaffSanction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogReject(Staff_Sanction_and_Working.this,myImageNameList2);
            }
        });
    }
}