package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.buildingaudit.R;

public class UpdateDetailsFurnitures extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_furnitures);
        getSupportActionBar().setTitle("Furnitures");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}