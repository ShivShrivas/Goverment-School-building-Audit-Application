package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.DashBoard;
import com.bsn.buildingaudit.Model.DataLocked;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiMsg91;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.bsn.buildingaudit.RetrofitApi.RestClientMsg91;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Final_OTP_Submission extends AppCompatActivity {
    TextInputEditText inputOTP;
    Button SubmitBtnOTP;
    TextView resentOtptxt,mobnumberTxt,textView211;
    ApplicationController applicationController;
    Dialog dialog,dialog2;
    String mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_otp_submission);
        applicationController = (ApplicationController) getApplication();
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog_onsave);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2 = new Dialog(this);
        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        inputOTP=findViewById(R.id.inputOTP);
        SubmitBtnOTP=findViewById(R.id.SubmitBtnOTP);
        resentOtptxt=findViewById(R.id.resentOtptxt);
        mobnumberTxt=findViewById(R.id.mobnumberTxt);
        textView211=findViewById(R.id.textView211);
        RestClientMsg91 restClientMsg91=new RestClientMsg91();
        ApiMsg91 apiMsg91=restClientMsg91.getApiService();
         mob=applicationController.getPhoneNumber();
         if (mob.length()==10){
             mobnumberTxt.setText("+91"+mob);
         }else{
             textView211.setText("Your Mobile Number is \n "+"+91"+mob);
             mobnumberTxt.setText("Invalid!! contact to Head Office");
             SubmitBtnOTP.setEnabled(false);
         }
        SubmitBtnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                Call<JsonObject> call=apiMsg91.verifyOTP(inputOTP.getText().toString().trim(),"376489AgYgO9FDHy362711b3fP1","91"+applicationController.getPhoneNumber());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        if (response.body().get("type").getAsString().equals("success")){
                            RestClient restClient=new RestClient();
                            ApiService apiService=restClient.getApiService();

                            Call<List<DataLocked>> call2=apiService.checkLockedData(paraLocakedData("2",applicationController.getSchoolId(),applicationController.getPeriodID(),applicationController.getUserid()));
                            call2.enqueue(new Callback<List<DataLocked>>() {
                                @Override
                                public void onResponse(Call<List<DataLocked>> call, Response<List<DataLocked>> response) {
                                    List<DataLocked> lockeds=response.body();

                                    if (lockeds.get(0).getSTATUS()==1){
                                        TextView textView = dialog.findViewById(R.id.dialogtextResponse);
                                        Button button = dialog.findViewById(R.id.BtnResponseDialoge);
                                        try {

                                                textView.setText("Your Final Submission is Done!!");
                                            applicationController.setDataLocked(1);

                                            dialog2.dismiss();

                                            dialog.show();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    startActivity(new Intent(Final_OTP_Submission.this, DashBoard.class));

                                                    finish();                                                    dialog.dismiss();
                                                }
                                            });
                                        } catch (Exception e) {
                                            dialog2.dismiss();

                                            Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                                        }

                                    }else{
                                        TextView textView = dialog.findViewById(R.id.dialogtextResponse);
                                        Button button = dialog.findViewById(R.id.BtnResponseDialoge);
                                        try {

                                            textView.setText(response.body().get(0).getStatusName());

                                            dialog2.dismiss();

                                            dialog.show();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    startActivity(new Intent(Final_OTP_Submission.this, DashBoard.class));
                                                        applicationController.setDataLocked(1);
                                                    finish();
                                                    dialog.dismiss();
                                                }
                                            });
                                        } catch (Exception e) {
                                            dialog2.dismiss();

                                            Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<DataLocked>> call, Throwable t) {
                                    dialog2.dismiss();
                                }
                            });

                        }else{
                            Toast.makeText(Final_OTP_Submission.this, "Wrong OTP!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(Final_OTP_Submission.this, "Please check your network connection!!", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    }
                });
            }
        });


        resentOtptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
            Call<JsonObject> call=apiMsg91.resendOTP("376489AgYgO9FDHy362711b3fP1","text","91"+applicationController.getPhoneNumber());
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    dialog2.dismiss();
                    Toast.makeText(Final_OTP_Submission.this, ""+response.body().get("message").getAsString(), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                dialog2.dismiss();
                }
            });
            }
        });

    }
    private JsonObject paraLocakedData(String s, String schoolId, String periodID, String userid) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",s);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("userid",userid);

        return jsonObject;
    }
}