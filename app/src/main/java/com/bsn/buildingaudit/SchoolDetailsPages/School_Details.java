package com.bsn.buildingaudit.SchoolDetailsPages;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.SubjectRecviewAdapter;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class School_Details extends AppCompatActivity {
RecyclerView recViewForPermittedSubjects,recViewForRunningClasses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ArrayList<String> subjectArray=new ArrayList<>();
        subjectArray.add("Hindi");
        subjectArray.add("ENGLISH");
        subjectArray.add("SANSKRIT");
        subjectArray.add("SCIENCE");
        subjectArray.add("Mathematics");
        subjectArray.add("DRAWING");
        subjectArray.add("HOME SCIENCE");
        subjectArray.add("SOCIAL SCIENCE");
        subjectArray.add("COMPUTER SCIENCE");

        ArrayList<String> classesArrayList =new ArrayList<>();
        classesArrayList.add("Class6");
        classesArrayList.add("Class7");
        classesArrayList.add("Class8");
        classesArrayList.add("Class9");
        classesArrayList.add("Class10");
        classesArrayList.add("Class11");
        classesArrayList.add("Class12");

        recViewForRunningClasses=findViewById(R.id.recViewForRunningClasses);
        recViewForPermittedSubjects=findViewById(R.id.recViewForPermittedSubjects);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        GridLayoutManager layoutManager1=new GridLayoutManager(this,2);

        recViewForRunningClasses.setLayoutManager(layoutManager);
        recViewForPermittedSubjects.setLayoutManager(layoutManager1);

        recViewForRunningClasses.setAdapter(new SubjectRecviewAdapter(this,classesArrayList));
        recViewForPermittedSubjects.setAdapter(new SubjectRecviewAdapter(this,subjectArray));

    }
}