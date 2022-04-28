package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.buildingaudit.R;

public class OnSubmitClassRoomPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_class_room_page);
        Intent i=getIntent();
        String resonse=i.getStringExtra("ClassDetailsResponse");
        Log.d("TAG", "onCreate: "+resonse);
    }
}