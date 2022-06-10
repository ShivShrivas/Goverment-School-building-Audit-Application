package com.bsn.buildingaudit.DIOS.SchoolResultDetails;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.StudentResultAdapter;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class Student_Result_Details_last_three_years extends AppCompatActivity {
RecyclerView recyclerViewFOrStudentResult;
StudentResultAdapter adapter;
Button last_threeYearResultApproveBtn,last_threeYearResultRejectBtn;
ArrayList<String[]> result=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result_details);
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
        recyclerViewFOrStudentResult=findViewById(R.id.recyclerViewFOrStudentResult);
        last_threeYearResultRejectBtn=findViewById(R.id.last_threeYearResultRejectBtn);
        last_threeYearResultApproveBtn=findViewById(R.id.last_threeYearResultApproveBtn);
        result.add(new String[]{"2020", "10"});
        result.add(new String[]{"2020", "12"});
        result.add(new String[]{"2021", "10"});
        result.add(new String[]{"2021", "12"});
        result.add(new String[]{"2022", "10"});
        result.add(new String[]{"2022", "12"});

        recyclerViewFOrStudentResult.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFOrStudentResult.setAdapter(new StudentResultAdapter(this,result));

        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("Student Data is not correct");
        myImageNameList2.add("Toppers Data is not correct");
        myImageNameList2.add("No. of attached All Data is not correct");


        last_threeYearResultApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogApprove(Student_Result_Details_last_three_years.this,myImageNameList);
            }
        });

        last_threeYearResultRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogReject(Student_Result_Details_last_three_years.this,myImageNameList2);
            }
        });
    }
}