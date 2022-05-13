package com.example.buildingaudit.Activies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildingaudit.R;

public class NoInternetConnection extends AppCompatActivity {
Button noIntenetBtn;
    public static void triggerRebirth(Context context, Class myClass) {
        Intent intent = new Intent(context, myClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        Runtime.getRuntime().exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);
        noIntenetBtn=findViewById(R.id.noIntenetBtn);
        noIntenetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triggerRebirth(getApplicationContext(), SplashScreen.class);
            }
        });
    }
}