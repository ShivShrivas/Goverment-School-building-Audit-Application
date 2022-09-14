package com.bsn.buildingaudit.DIOS.physicalStatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.dashboardRoomListAdapterDIOS;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.GetAllRoomsList;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class Physical_Status_DIOS_Dashboard extends AppCompatActivity {
RecyclerView recyclerViewForDiosDashboardPhysicalStatus;
    TextView textView8,schoolAddress,schoolName;
    ApplicationController applicationController;
    dashboardRoomListAdapterDIOS adapter;
    String InspectionId;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_status_dios_dashboard);
        applicationController= (ApplicationController) getApplication();
        recyclerViewForDiosDashboardPhysicalStatus=findViewById(R.id.recyclerViewForDiosDashboardPhysicalStatus);
        Window window = getWindow();
        i=getIntent();
        InspectionId=i.getStringExtra("InspectionId");
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        textView8=findViewById(R.id.textView8);
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());
        ArrayList<GetAllRoomsList> arrayList=new ArrayList<>();
        GetAllRoomsList getAllRoomsList=new GetAllRoomsList("1","Class Room Details","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList1=new GetAllRoomsList("2","Staff Room Details","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList2=new GetAllRoomsList("3","Practical Labs","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList3=new GetAllRoomsList("4","Library","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList4=new GetAllRoomsList("5","Play Ground","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList5=new GetAllRoomsList("6","Open Gym","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList6=new GetAllRoomsList("7","Drinking Water","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList7=new GetAllRoomsList("8","Smart Class","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList8=new GetAllRoomsList("9","Bio Metric","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList9=new GetAllRoomsList("10","CCTV","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList10=new GetAllRoomsList("11","Electricity Arrangement","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList11=new GetAllRoomsList("12","Fire Fighting","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList12=new GetAllRoomsList("13","Rain Harvesting","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList13=new GetAllRoomsList("14","Solar Panel","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList14=new GetAllRoomsList("15","Boundary Walls","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList15=new GetAllRoomsList("16","Boys Toilet","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList16=new GetAllRoomsList("17","Girls Toilet","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList17=new GetAllRoomsList("18","Furniture","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList18=new GetAllRoomsList("19","Computer Lab","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList19=new GetAllRoomsList("20","WiFi","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList20=new GetAllRoomsList("21","Cycle Stand","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList21=new GetAllRoomsList("22","Band and Sound System","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList22=new GetAllRoomsList("23","Multipurpose Hall","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList23=new GetAllRoomsList("24","Principal Room","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList24=new GetAllRoomsList("25","Art And Craft Room","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList25=new GetAllRoomsList("26","Vocational Education Room","12-15-2022 02:30:28 PM","2057","26");
        GetAllRoomsList getAllRoomsList26=new GetAllRoomsList("27","Office Room","12-15-2022 02:30:28 PM","2057","26");

        arrayList.add(getAllRoomsList);
        arrayList.add(getAllRoomsList1);
        arrayList.add(getAllRoomsList2);
        arrayList.add(getAllRoomsList3);
        arrayList.add(getAllRoomsList4);
        arrayList.add(getAllRoomsList5);
        arrayList.add(getAllRoomsList6);
        arrayList.add(getAllRoomsList7);
        arrayList.add(getAllRoomsList8);
        arrayList.add(getAllRoomsList9);
        arrayList.add(getAllRoomsList10);
        arrayList.add(getAllRoomsList11);
        arrayList.add(getAllRoomsList12);
        arrayList.add(getAllRoomsList13);
        arrayList.add(getAllRoomsList14);
        arrayList.add(getAllRoomsList15);
        arrayList.add(getAllRoomsList16);
        arrayList.add(getAllRoomsList17);
        arrayList.add(getAllRoomsList18);
        arrayList.add(getAllRoomsList19);
        arrayList.add(getAllRoomsList20);
        arrayList.add(getAllRoomsList21);
        arrayList.add(getAllRoomsList22);
        arrayList.add(getAllRoomsList23);
        arrayList.add(getAllRoomsList24);
        arrayList.add(getAllRoomsList25);
        arrayList.add(getAllRoomsList26);

        recyclerViewForDiosDashboardPhysicalStatus.setLayoutManager(new LinearLayoutManager(this));
        adapter=new dashboardRoomListAdapterDIOS(this,arrayList,"2057","26",InspectionId);
        recyclerViewForDiosDashboardPhysicalStatus.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}