package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.BuildConfig;
import com.bsn.buildingaudit.MainActivity;
import com.bsn.buildingaudit.R;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {
TextView versionTxt;
    Context context;
    Resources resources;
    private static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        versionTxt=findViewById(R.id.versionTxt);
        versionTxt.setText("version("+BuildConfig.VERSION_NAME+")");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialogForLang();


            }


        }, 2000);
    }

    void showDialogForLang(){
        dialog = new Dialog(SplashScreen.this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.language_card);

        Button btndialoghindi = (Button) dialog.findViewById(R.id.btndialoghindi);
        Button btndialogenglish = (Button) dialog.findViewById(R.id.btndialogenglish);
        btndialogenglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageToLoad  = "en"; // your language code
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(SplashScreen.this, "Language English Enabled!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.putExtra("My_notification","2");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
                dialog.dismiss();
            }
        });
        btndialoghindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageToLoad  = "hi"; // your language code
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(SplashScreen.this, "Language Hindi Enabled!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.putExtra("My_notification","2");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}