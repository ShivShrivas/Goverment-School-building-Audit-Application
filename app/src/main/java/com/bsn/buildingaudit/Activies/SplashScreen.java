package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.BuildConfig;
import com.bsn.buildingaudit.MainActivity;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;
import java.util.Locale;

public class SplashScreen extends AppCompatActivity {
TextView versionTxt;
    Context context;
    Resources resources;

    ArrayAdapter<String> arrayAdapter;
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
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("English");
        arrayList.add("Hindi");

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        dialog = new Dialog(SplashScreen.this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.language_card);
        Spinner spinnerDialog_Lang=(Spinner) dialog.findViewById(R.id.spinnerDialog_Lang);
        spinnerDialog_Lang.setAdapter(arrayAdapter);

        Button changeLanguage = (Button) dialog.findViewById(R.id.changeLanguage);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerDialog_Lang.getSelectedItem().toString().equals("Hindi")){
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
                }else{
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

            }
        });

        dialog.show();
    }


}