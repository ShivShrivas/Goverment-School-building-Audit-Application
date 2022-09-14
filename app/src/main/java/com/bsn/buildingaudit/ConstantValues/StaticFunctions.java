package com.bsn.buildingaudit.ConstantValues;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.AdpterResonsApproved;
import com.bsn.buildingaudit.DIOS.StudentEnrollment.Student_Enrollment_Details;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaticFunctions {

    private static Dialog dialog;
    private static TextInputEditText inputRemarksForApproval;
    private static TextInputEditText inputRejectRemark;
    private static ConstraintLayout remarkTextinputLayout;
    private static ConstraintLayout rejectTextintputLayout;

    public static void showDialogApprove(Activity activity, ArrayList<ApproveRejectRemarkModel> myImageNameList, String periodID, String schoolId, String id, ArrayList<Datum> arrayListRemarks, Boolean remarkAlreadyDoneFlag) {
        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.approved_button_card);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Button btndialog = (Button) dialog.findViewById(R.id.approovedBtnDialoge);
         inputRemarksForApproval = (TextInputEditText) dialog.findViewById(R.id.inputRemarksForApproval);
        remarkTextinputLayout = (ConstraintLayout) dialog.findViewById(R.id.remarkTextinputLayout);
        ImageView btndialogClose = (ImageView) dialog.findViewById(R.id.dilogeCloseBtnApprove);
        RecyclerView recyclerView = dialog.findViewById(R.id.recycleForReason);
        AdpterResonsApproved adapterRe = new AdpterResonsApproved(activity, myImageNameList,schoolId,periodID,"A",id,arrayListRemarks);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        dialog.show();
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remarkTextinputLayout.getVisibility()==View.VISIBLE){
                    if (inputRemarksForApproval.getText().toString().equals("")){
                        inputRemarksForApproval.setError("Please Enter Remarks");
                        Toast.makeText(activity, "Please Enter Remarks", Toast.LENGTH_SHORT).show();
                    }else {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("SchoolId", schoolId);
                        jsonObject.addProperty("PeriodId", periodID);
                        jsonObject.addProperty("ParamId", id);
                        jsonObject.addProperty("OtherRemarks", inputRemarksForApproval.getText().toString());
                        jsonObject.addProperty("InsId", "270");
                        jsonObject.addProperty("CreatedBy", schoolId);
                        AdpterResonsApproved.jsonArray.add(jsonObject);
                        Log.d("TAG", "onClick: " + AdpterResonsApproved.jsonArray);
                        if (remarkAlreadyDoneFlag){
                            Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                        }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }else{
                            Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }


                        dialog.dismiss();
                    }
                }else {
                    if (remarkAlreadyDoneFlag){
                        Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }

                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }else{
                        Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }
                    dialog.dismiss();
                }
                AdpterResonsApproved.jsonArray=new JsonArray();

                Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);


            }
        });
        btndialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });



    }


    public static void showDialogReject(Activity activity, ArrayList<ApproveRejectRemarkModel> myImageNameList, String periodID, String schoolId, String id, ArrayList<Datum> arrayListRemarks, Boolean remarkAlreadyDoneFlag) {

        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.rejection_button_card);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Button btndialog = (Button) dialog.findViewById(R.id.rejectdBtnDialoge);
        Log.d("TAG", "showDialogReject: "+remarkAlreadyDoneFlag);
         inputRejectRemark = (TextInputEditText) dialog.findViewById(R.id.inputRejectRemark);
         rejectTextintputLayout = dialog.findViewById(R.id.rejectTextintputLayout);
        ImageView btndialogClose = (ImageView) dialog.findViewById(R.id.dilogeCloseBtn);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rejectTextintputLayout.getVisibility()==View.VISIBLE){
                    if (inputRejectRemark.getText().toString().equals("")){
                        inputRejectRemark.setError("Please Enter Remarks");
                        Toast.makeText(activity, "Please Enter Remarks", Toast.LENGTH_SHORT).show();

                    }else{
                        JsonObject jsonObject=new JsonObject();
                        jsonObject.addProperty("SchoolId",schoolId);
                        jsonObject.addProperty("PeriodId",periodID);
                        jsonObject.addProperty("ParamId",id);
                        jsonObject.addProperty("OtherRemarks",inputRejectRemark.getText().toString());
                        jsonObject.addProperty("InsId","270");
                        jsonObject.addProperty("CreatedBy",schoolId);
                        AdpterResonsApproved.jsonArray.add(jsonObject);
                        Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);
                        Log.d("TAG", "onClick: " +remarkAlreadyDoneFlag);
                        if (remarkAlreadyDoneFlag){
                            Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }else{
                            Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }
                        dialog.dismiss();
                    }


                }else {
                    Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);

                    if (remarkAlreadyDoneFlag){
                        Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }

                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }else{
                        Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }

                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }
                    dialog.dismiss();

                }
                AdpterResonsApproved.jsonArray=new JsonArray();

                Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);


            }
        });
        btndialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        RecyclerView recyclerView = dialog.findViewById(R.id.recycleForReasonreject);
        AdpterResonsApproved adapterRe = new AdpterResonsApproved(activity, myImageNameList, schoolId, periodID,"R",id,arrayListRemarks);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        dialog.show();
    }

    public static void showTextBox(String type) {
        if(type.equals("A")){
            remarkTextinputLayout.setVisibility(View.VISIBLE);

        }else{
            rejectTextintputLayout.setVisibility(View.VISIBLE);

        }
    }   public static void setText(String type,String remark) {
        if(type.equals("A")){
            remarkTextinputLayout.setVisibility(View.VISIBLE);
            inputRemarksForApproval.setText(remark);

        }else{
            rejectTextintputLayout.setVisibility(View.VISIBLE);
            inputRejectRemark.setText(remark);

        }
    }

    public static void hideTextBox(String type) {
        if (type.equals("A")){
            remarkTextinputLayout.setVisibility(View.GONE);

        }else {
            rejectTextintputLayout.setVisibility(View.GONE);

        }
    }

    public static void showDialogReject(Activity activity, ArrayList<ApproveRejectRemarkModel> arrayList, String periodID, String schoolId, String parentID, ArrayList<Datum> arrayListRemarks, Boolean remarkAlreadyDoneFlag, String inspectionId) {

        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.rejection_button_card);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Button btndialog = (Button) dialog.findViewById(R.id.rejectdBtnDialoge);
        Log.d("TAG", "showDialogReject: "+remarkAlreadyDoneFlag);
        inputRejectRemark = (TextInputEditText) dialog.findViewById(R.id.inputRejectRemark);
        rejectTextintputLayout = dialog.findViewById(R.id.rejectTextintputLayout);
        ImageView btndialogClose = (ImageView) dialog.findViewById(R.id.dilogeCloseBtn);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rejectTextintputLayout.getVisibility()==View.VISIBLE){
                    if (inputRejectRemark.getText().toString().equals("")){
                        inputRejectRemark.setError("Please Enter Remarks");
                        Toast.makeText(activity, "Please Enter Remarks", Toast.LENGTH_SHORT).show();

                    }else{
                        JsonObject jsonObject=new JsonObject();
                        jsonObject.addProperty("SchoolId",schoolId);
                        jsonObject.addProperty("PeriodId",periodID);
                        jsonObject.addProperty("ParamId",parentID);
                        jsonObject.addProperty("OtherRemarks",inputRejectRemark.getText().toString());
                        jsonObject.addProperty("InsId","270");
                        jsonObject.addProperty("CreatedBy",schoolId);
                        AdpterResonsApproved.jsonArray.add(jsonObject);
                        Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);
                        Log.d("TAG", "onClick: " +remarkAlreadyDoneFlag);
                        if (remarkAlreadyDoneFlag){
                            Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }else{
                            Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }
                        dialog.dismiss();
                    }


                }else {
                    Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);

                    if (remarkAlreadyDoneFlag){
                        Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }

                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }else{
                        Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }

                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }
                    dialog.dismiss();

                }
                AdpterResonsApproved.jsonArray=new JsonArray();

                Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);


            }
        });
        btndialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        RecyclerView recyclerView = dialog.findViewById(R.id.recycleForReasonreject);
        AdpterResonsApproved adapterRe = new AdpterResonsApproved(activity, arrayList, schoolId, periodID,"R",parentID,arrayListRemarks,inspectionId);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        dialog.show();
    }

    public static void showDialogApprove(Activity activity, ArrayList<ApproveRejectRemarkModel> arrayList, String periodID, String schoolId, String parentID, ArrayList<Datum> arrayListRemarks, Boolean remarkAlreadyDoneFlag, String inspectionId) {
        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.approved_button_card);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Button btndialog = (Button) dialog.findViewById(R.id.approovedBtnDialoge);
        inputRemarksForApproval = (TextInputEditText) dialog.findViewById(R.id.inputRemarksForApproval);
        remarkTextinputLayout = (ConstraintLayout) dialog.findViewById(R.id.remarkTextinputLayout);
        ImageView btndialogClose = (ImageView) dialog.findViewById(R.id.dilogeCloseBtnApprove);
        RecyclerView recyclerView = dialog.findViewById(R.id.recycleForReason);
        AdpterResonsApproved adapterRe = new AdpterResonsApproved(activity, arrayList,schoolId,periodID,"A",parentID,arrayListRemarks,inspectionId);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        dialog.show();
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remarkTextinputLayout.getVisibility()==View.VISIBLE){
                    if (inputRemarksForApproval.getText().toString().equals("")){
                        inputRemarksForApproval.setError("Please Enter Remarks");
                        Toast.makeText(activity, "Please Enter Remarks", Toast.LENGTH_SHORT).show();
                    }else {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("SchoolId", schoolId);
                        jsonObject.addProperty("PeriodId", periodID);
                        jsonObject.addProperty("ParamId", parentID);
                        jsonObject.addProperty("OtherRemarks", inputRemarksForApproval.getText().toString());
                        jsonObject.addProperty("InsId", "270");
                        jsonObject.addProperty("CreatedBy", schoolId);
                        AdpterResonsApproved.jsonArray.add(jsonObject);
                        Log.d("TAG", "onClick: " + AdpterResonsApproved.jsonArray);
                        if (remarkAlreadyDoneFlag){
                            Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }else{
                            Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                            call.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    Log.d("TAG", "onResponse: "+response.body());
                                    if (response.isSuccessful()){
                                        Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                        activity.onBackPressed();
                                    }

                                }

                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {

                                }
                            });
                        }


                        dialog.dismiss();
                    }
                }else {
                    if (remarkAlreadyDoneFlag){
                        Call<JsonArray> call=apiService.updateRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }

                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }else{
                        Call<JsonArray> call=apiService.submitRemarkByDios(AdpterResonsApproved.jsonArray);
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Log.d("TAG", "onResponse: "+response.body());
                                if (response.isSuccessful()){
                                    Toast.makeText(activity, ""+response.body().get(0).getAsJsonObject().get("Status"), Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {

                            }
                        });
                    }
                    dialog.dismiss();
                }
                AdpterResonsApproved.jsonArray=new JsonArray();

                Log.d("TAG", "onClick: "+AdpterResonsApproved.jsonArray);


            }
        });
        btndialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });



    }
}
