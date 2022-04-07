package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.buildingaudit.Adapters.updateDetailsTypeThreeAdapter;
import com.example.buildingaudit.R;

public class UpdatedetailsTypeThree extends AppCompatActivity {
RecyclerView recyclerviewOfupdatedetailsTypeThree;
String[] subjectUpdateDetailsThree={"Physics","Chemistry","Biology","Vocational","Science","Geography","Home Science","Psychology","Music"};
    updateDetailsTypeThreeAdapter adapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Practical Lab");

        setContentView(R.layout.activity_updatedetails_type_three);
        recyclerviewOfupdatedetailsTypeThree=findViewById(R.id.recyclerviewOfupdatedetailsTypeThree);
        recyclerviewOfupdatedetailsTypeThree.setLayoutManager(new LinearLayoutManager(this));
        adapter=new updateDetailsTypeThreeAdapter(this,subjectUpdateDetailsThree);
        recyclerviewOfupdatedetailsTypeThree.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}