package com.bsn.buildingaudit.DIOS.SchoolStaff;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.StaffTrainingAdapter;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class All_Staff_Traing_Details extends AppCompatActivity {
RecyclerView recyclerView,recyclerView2;
    Button approveAllStaffTraing,rejectAllStaffTraing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_staff_traing_details);
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
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView2=findViewById(R.id.recyclerView2);
        approveAllStaffTraing=findViewById(R.id.approveAllStaffTraing);
        rejectAllStaffTraing=findViewById(R.id.rejectAllStaffTraing);
       recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView2.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("a");
        arrayList.add("a");

        ArrayList<String> arrayList2=new ArrayList<>();
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");
        arrayList2.add("a");

        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");



        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("for Principal no. of Proposed training details are not correct");
        myImageNameList2.add("for Principal no. of Completed training details are not correct");
        myImageNameList2.add("Principal training details are not correct");
        myImageNameList2.add("for Teachers no. of Proposed training details are not correct");
        myImageNameList2.add("for Teachers no. of Completed training details are not correct");
        myImageNameList2.add("Teachers training details are not correct");

        recyclerView.setAdapter(new StaffTrainingAdapter(All_Staff_Traing_Details.this,arrayList));
       recyclerView2.setAdapter(new StaffTrainingAdapter(All_Staff_Traing_Details.this,arrayList2));

        approveAllStaffTraing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogApprove(All_Staff_Traing_Details.this,myImageNameList);
            }
        });

        rejectAllStaffTraing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogReject(All_Staff_Traing_Details.this,myImageNameList2);
            }
        });
    }
}