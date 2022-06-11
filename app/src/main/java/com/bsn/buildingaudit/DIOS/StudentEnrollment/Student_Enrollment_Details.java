package com.bsn.buildingaudit.DIOS.StudentEnrollment;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.Student_PresenceAdapter;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class Student_Enrollment_Details extends AppCompatActivity {
    Student_PresenceAdapter adapter;
    RecyclerView recyclerViewStudentpresence;
    Button btnApprovalStudentpresence,btnRejectStudentpresence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enrollment_details);
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
        recyclerViewStudentpresence=findViewById(R.id.recyclerViewStudentpresence);
        btnRejectStudentpresence=findViewById(R.id.btnRejectStudentpresence);
        btnApprovalStudentpresence=findViewById(R.id.btnApprovalStudentpresence);
        ArrayList<String> arrayList2=new ArrayList<>();
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");

        adapter=new Student_PresenceAdapter(this,arrayList2);
        recyclerViewStudentpresence.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStudentpresence.setAdapter(adapter);
        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All things are in good position");
        myImageNameList.add("some things are in good position");
        myImageNameList.add("most of things are in good position");
        myImageNameList.add("noting is in good position but data is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("All things are not in good position");
        myImageNameList2.add("some things are not in good position");
        myImageNameList2.add("most of things are not in good position");
        myImageNameList2.add("All things are in good position but data is not correct");
        btnApprovalStudentpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogApprove(Student_Enrollment_Details.this,myImageNameList);
            }
        });


        btnRejectStudentpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogReject(Student_Enrollment_Details.this, myImageNameList2);
            }
        });
    }

}
