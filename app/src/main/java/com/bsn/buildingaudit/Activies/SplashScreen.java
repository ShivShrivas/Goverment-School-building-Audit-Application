package com.bsn.buildingaudit.Activies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.BuildConfig;
import com.bsn.buildingaudit.MainActivity;
import com.bsn.buildingaudit.R;

public class SplashScreen extends AppCompatActivity {
TextView versionTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        versionTxt=findViewById(R.id.versionTxt);
        versionTxt.setText("version("+BuildConfig.VERSION_NAME+")");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.putExtra("My_notification","2");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, 2000);
    }
}