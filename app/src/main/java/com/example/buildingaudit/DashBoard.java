package com.example.buildingaudit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.buildingaudit.Adapters.dashboardRecviewAdapter;
import com.example.buildingaudit.Model.RecModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {
    RecyclerView dashboardRecview;
    dashboardRecviewAdapter adapter;
    ArrayList<RecModel> arrayList=new ArrayList<>();
    String[] arrayListRoomType= {"Classroom Details","Practical Lab","Principal Room","Staff Room","Practical Lab","Principal Room","Staff Room","Practical Lab","Principal Room","Staff Room","Principal Room","Staff Room"};
    String[] UpdatedOn= {"12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        dashboardRecview=findViewById(R.id.dashboardRecview);

        for (int i=0;i<arrayListRoomType.length;i++){
            RecModel recModel=new RecModel();
            recModel.setRoomtype(arrayListRoomType[i]);
            recModel.setUpdatedOn(UpdatedOn[i]);
            arrayList.add(recModel);
        }
        dashboardRecview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new dashboardRecviewAdapter(this,arrayList);
        dashboardRecview.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.notifyDataSetChanged();
    }
}