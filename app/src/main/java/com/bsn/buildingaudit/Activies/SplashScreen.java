package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
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
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import java.util.ArrayList;
import java.util.Locale;

public class SplashScreen extends AppCompatActivity {
TextView versionTxt;
    Context context;
    Resources resources;
    private AppUpdateManager appUpdateManager;
    private static final int IMMEDIATE_APP_UPDATE_REQ_CODE = 124;
    ArrayAdapter<String> arrayAdapter;
    private static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        versionTxt=findViewById(R.id.versionTxt);
        versionTxt.setText("version("+BuildConfig.VERSION_NAME+")");
        appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
        checkUpdate();
         }

    private void checkUpdate() {

        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
           
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                startUpdateFlow(appUpdateInfo);
            } else if  (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS){
                startUpdateFlow(appUpdateInfo);
            }else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        showDialogForLang();
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        intent.putExtra("My_notification","2");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
    }

    private void startUpdateFlow(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this, SplashScreen.IMMEDIATE_APP_UPDATE_REQ_CODE);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMMEDIATE_APP_UPDATE_REQ_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Update canceled by You! Please update App", Toast.LENGTH_LONG).show();
                checkUpdate();
            } else if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Update success! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Update Failed! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
                checkUpdate();
            }
        }
    }
    void showDialogForLang(){
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("English");
//        arrayList.add("Hindi");

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