package com.bsn.buildingaudit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bsn.buildingaudit.Activies.NoInternetConnection;
import com.bsn.buildingaudit.Adapters.QuaterTypeAdapter;
import com.bsn.buildingaudit.Adapters.UserTyepAdapter;
import com.bsn.buildingaudit.Model.GetQuaterType;
import com.bsn.buildingaudit.Model.GetSchoolDetails;
import com.bsn.buildingaudit.Model.GetUserType;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
Button SubmitBtn;
ApplicationController applicationController;

    SharedPreferences sharedpreferences;
EditText password,username;
Spinner spinnerUserType,spinnerFinancialQuarter;
RestClient restClient;
ApiService apiService;
Dialog dialog;
ConstraintLayout loginLayout;
List<GetSchoolDetails> getSchoolDetails=new ArrayList<>();
    SharedPreferences.Editor editor;
CheckBox check_showpassword,check_remember;
    List<GetQuaterType> arrayListQuater =new ArrayList<>();
    List<GetUserType> arrayListUserType =new ArrayList<>();
    GetUserType getUserType;
    GetQuaterType getQuaterType;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart: runn");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: runn");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "onRestart: runn");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("TAG", "onPostResume: runnn");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: runn");
    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            // wifi is enabled

            connected = (nInfo != null && nInfo.isAvailable() && nInfo.isConnected()) ||wifiManager.isWifiEnabled();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isConnected()==false){
            startActivity(new Intent(MainActivity.this, NoInternetConnection.class));
        }
        dialog = new Dialog(this);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.progress_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog.setCancelable(false);
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        SubmitBtn=findViewById(R.id.SubmitBtnStaffRoom);
        applicationController= (ApplicationController) getApplication();
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        spinnerFinancialQuarter=findViewById(R.id.spinnerFinancialQuarter);
        spinnerUserType=findViewById(R.id.spinnerUserType);
        loginLayout=findViewById(R.id.loginLayout);
        check_showpassword=findViewById(R.id.check_showpassword);
        check_remember=findViewById(R.id.check_remember);
        restClient=new RestClient();
        apiService=restClient.getApiService();
        String userId_save=sharedpreferences.getString("userId_save","");
        String Password_save=sharedpreferences.getString("Password_save","");
        boolean check_box=sharedpreferences.getBoolean("checkbox",false);
        setDataInUserTypeSpinner();
        setQuaterTypeInSpinner();
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

                dialog.show();

                if (password.getText().toString().length()>0 && username.getText().length()>0){
                        RestClient restClient=new RestClient();
                        ApiService apiService=restClient.getApiService();
                        Log.d("TAG", "onClick: "+paraLogin(username.getText().toString(),applicationController.getUsertype(),password.getText().toString()));
                        Call<List<JsonObject>> call=apiService.getLogin(paraLogin(username.getText().toString(),applicationController.getUsertype(),password.getText().toString()));
                        call.enqueue(new Callback<List<JsonObject>>() {
                            @Override
                            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.body().size()!=0){
                                    try {
                                        if (response.body().get(0).get("pswd").getAsString().equals("0")){
                                            Toast.makeText(MainActivity.this, "Please Enter Correct Username And Password!!", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }catch (Exception e){

                                    }
                                    if(response.body().get(0).get("userid")!=null) {
                                        applicationController.setUserid(response.body().get(0).get("userid").getAsString());
                                        applicationController.setUsername(response.body().get(0).get("username").getAsString());
                                        applicationController.setSchoolId(response.body().get(0).get("schoolid").getAsString());
                                        getSchoolDetails();

                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                                Log.d("TAG", "onFailure: "+t);
                                dialog.dismiss();
                                Snackbar.make(loginLayout,"Restart App or Check your internet Connection", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                        .setAction("Retry", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                setDataInUserTypeSpinner();
                                                setQuaterTypeInSpinner();
                                                dialog.show();
                                            }
                                        });
                            }
                        });


                }else {
                    Toast.makeText(MainActivity.this, "Please enter username and password properly", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                }
            }
        });

    }
    private void getSchoolDetails() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "getSchoolDetails: "+paraSchoolDetails("4",applicationController.getPeriodID(),applicationController.getSchoolId()));
        Call<List<GetSchoolDetails>> call=apiService.getSchoolDetails(paraSchoolDetails("4",applicationController.getPeriodID(),applicationController.getSchoolId()));
        call.enqueue(new Callback<List<GetSchoolDetails>>() {
            @Override
            public void onResponse(Call<List<GetSchoolDetails>> call, Response<List<GetSchoolDetails>> response) {
                getSchoolDetails=response.body();
                Log.d("TAG", "onResponse: "+response.body());
                applicationController.setSchoolName(getSchoolDetails.get(0).getSCHOOL_NAME());
                applicationController.setSchoolAddress(getSchoolDetails.get(0).getADDRESS());
                applicationController.setUsername(getSchoolDetails.get(0).getDESIGNATION());
                startActivity(new Intent(MainActivity.this,DashBoard.class));
                finish();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<GetSchoolDetails>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
              
            }
        });

    }

    private JsonObject paraSchoolDetails(String s, String periodID, String schoolId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",s);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("SchoolId",schoolId);
        return  jsonObject;
    }
    private JsonObject paraLogin(String username, String usertype, String  password) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("LoginName",username);
        jsonObject.addProperty("Usertype",usertype);
        jsonObject.addProperty("Password",password);
        return jsonObject;
    }

    private void setQuaterTypeInSpinner() {
        dialog.show();

        RestClient restClient=new RestClient();
        ApiService service= restClient.getApiService();
        Call<List<GetQuaterType>> call=service.getPeriodList(paraGetActionObject("3"));
        call.enqueue(new Callback<List<GetQuaterType>>() {
            @Override
            public void onResponse(Call<List<GetQuaterType>> call, Response<List<GetQuaterType>> response) {
                if (response.body()!=null) {


                    arrayListQuater = response.body();
                    Resources res = getResources();

                    QuaterTypeAdapter adapter = new QuaterTypeAdapter(MainActivity.this, R.layout.spinner_card_orange, arrayListQuater, res);
                    spinnerFinancialQuarter.setAdapter(adapter);
                    dialog.dismiss();
                    spinnerFinancialQuarter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            getQuaterType = (GetQuaterType) adapterView.getSelectedItem();
                            applicationController.setPeriodID(getQuaterType.getPeriodId());


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<GetQuaterType>> call, Throwable t) {
                dialog.dismiss();

                Snackbar.make(loginLayout,"Please check internet and restart you app!!",BaseTransientBottomBar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onRestart();
                            }
                        });
            }
        });
    }

    private void setDataInUserTypeSpinner() {
        dialog.show();
        RestClient restClient=new RestClient();
        ApiService service= restClient.getApiService();
        Call<List<GetUserType>> call=service.getUserType(paraGetActionObject("4"));
        call.enqueue(new Callback<List<GetUserType>>() {
            @Override
            public void onResponse(Call<List<GetUserType>> call, Response<List<GetUserType>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                arrayListUserType=response.body();
                Resources res=getResources();
                UserTyepAdapter userTyepAdapter=new UserTyepAdapter(MainActivity.this,R.layout.spinner_card_orange,arrayListUserType,res);
                spinnerUserType.setAdapter(userTyepAdapter);
                dialog.dismiss();
                spinnerUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            getUserType= (GetUserType) adapterView.getSelectedItem();
                            applicationController.setUsertype(getUserType.getTypevalue());
                        applicationController.setUsertypeid(getUserType.getUsertypeid());

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<GetUserType>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t);

                Snackbar.make(loginLayout,"Please check internet and restart you app!!",BaseTransientBottomBar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onRestart();
                            }
                        });            }
        });

    }

    private JsonObject paraGetActionObject(String s) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",s);
        return jsonObject;
    }
}