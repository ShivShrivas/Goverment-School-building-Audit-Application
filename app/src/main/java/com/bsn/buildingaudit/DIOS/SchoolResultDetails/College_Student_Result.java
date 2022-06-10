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

import com.bsn.buildingaudit.Adapters.StudentCollegeWiseResultAdapter;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class College_Student_Result extends AppCompatActivity {
RecyclerView recyclerViewStudent_college_result;
StudentCollegeWiseResultAdapter adapter;
Button approveBtnCollegeResult,rejectBtnCollegeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_student_result);
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
        recyclerViewStudent_college_result=findViewById(R.id.recyclerViewStudent_college_result);
        rejectBtnCollegeResult=findViewById(R.id.rejectBtnCollegeResult);
        approveBtnCollegeResult=findViewById(R.id.approveBtnCollegeResult);
        recyclerViewStudent_college_result.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> classList=new ArrayList<>();
        classList.add("6");
        classList.add("7");
        classList.add("8");
        classList.add("9");
        classList.add("10");
        classList.add("11");
        classList.add("12");
        recyclerViewStudent_college_result.setAdapter(new StudentCollegeWiseResultAdapter(this,classList));


        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("Student Data is not correct");
        myImageNameList2.add("Toppers Data is not correct");
        myImageNameList2.add("No. of attached All Data is not correct");


        approveBtnCollegeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogApprove(College_Student_Result.this,myImageNameList);
            }
        });

        rejectBtnCollegeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogReject(College_Student_Result.this,myImageNameList2);
            }
        });
    }
}