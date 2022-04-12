package com.example.buildingaudit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
Button SubmitBtn;
    SharedPreferences sharedpreferences;
EditText password,username;
ConstraintLayout loginLayout;
    SharedPreferences.Editor editor;
CheckBox check_showpassword,check_remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("School Grading");
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        SubmitBtn=findViewById(R.id.loginBtn);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginLayout=findViewById(R.id.loginLayout);
        check_showpassword=findViewById(R.id.check_showpassword);
        check_remember=findViewById(R.id.check_remember);
        String userId_save=sharedpreferences.getString("userId_save","");
        String Password_save=sharedpreferences.getString("Password_save","");
        boolean check_box=sharedpreferences.getBoolean("checkbox",false);
        if(userId_save.length()>2){
            username.setText(userId_save);
            password.setText(Password_save);
            check_remember.setChecked(check_box);
        }
        check_showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    check_showpassword.setText("Hide password");
                    // edtx_password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    check_showpassword.setText("Show password");
                    // edtx_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        check_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("userId_save", username.getText().toString().trim());
                    editor.putString("Password_save", password.getText().toString().trim());
                    editor.putBoolean("checkbox",true);
                    editor.apply();
                    String userId_save=sharedpreferences.getString("userId_save","");
                    if(userId_save.length()<1){
                        check_remember.setChecked(false);
                        Snackbar snackbar = Snackbar
                                .make(loginLayout, "Enter User Id", Snackbar.LENGTH_LONG)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });
                        snackbar.setActionTextColor(Color.RED);
                        snackbar.show();
                    }
                }else{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("userId_save", "");
                    editor.putString("Password_save", "");
                    editor.putBoolean("checkbox",false);
                    editor.commit();
                    username.setText("");
                    password.setText("");
                }
            }
        });

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password.getText().toString().length()>0 && username.getText().length()>0){
                    if (username.getText().toString().equals("Admin") && password.getText().toString().equals("Admin1")){
                        startActivity(new Intent(MainActivity.this,DashBoard.class));
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(MainActivity.this, "Please enter username and password properly", Toast.LENGTH_SHORT).show();


            }
        });

    }
}