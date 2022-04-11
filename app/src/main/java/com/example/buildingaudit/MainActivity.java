package com.example.buildingaudit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button SubmitBtn;
EditText password,username;
CheckBox check_showpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("School Grading");
        SubmitBtn=findViewById(R.id.loginBtn);
        username=findViewById(R.id.username);
        check_showpassword=findViewById(R.id.check_showpassword);
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
        password=findViewById(R.id.password);
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password.getText().toString().length()>0 && username.getText().length()>0){
                    if (username.getText().toString().equals("Admin") && password.getText().toString().equals("Admin1")){
                        startActivity(new Intent(MainActivity.this,DashBoard.class));
                    }else {
                        Toast.makeText(MainActivity.this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(MainActivity.this, "Please enter username and password properly", Toast.LENGTH_SHORT).show();


            }
        });

    }
}