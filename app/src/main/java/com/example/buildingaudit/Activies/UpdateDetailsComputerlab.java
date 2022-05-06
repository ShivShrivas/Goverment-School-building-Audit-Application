package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailsComputerlab extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.notifyDataSetChanged();

    }
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    ImageAdapter4 adapter;
    Dialog dialog;
Spinner spinnerPrinterAvailable,spinnerScannerAvailable,spinnerComputeLabAvailabelty,spinnerInstallationYear,spinnerGrantUnderScheme,spinnerinternet,spinnerPowerBackup,spinnerFurniture,spinnerComputerOperator;
    ImageView ComputerLabImageUploadBtn;
    RecyclerView recyclerViewComputerLab;
    Dialog dialog2;
    TextView userName,schoolAddress,schoolName;
    Button submitComputerLabBtn;
    ConstraintLayout constraintLayout36;
    EditText edtNoOfLab,edtNoOfWorkingComputer,edtNoOfComputer;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_computerlab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);

         dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);

        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        spinnerComputeLabAvailabelty=findViewById(R.id.spinnerComputeLabAvailabelty);
        spinnerScannerAvailable=findViewById(R.id.spinnerScannerAvailable);
        spinnerPrinterAvailable=findViewById(R.id.spinnerPrinterAvailable);
        ComputerLabImageUploadBtn=findViewById(R.id.ComputerLabImageUploadBtn);
        recyclerViewComputerLab=findViewById(R.id.recyclerViewComputerLab);
        spinnerInstallationYear=findViewById(R.id.spinnerInstallationYear);
        spinnerGrantUnderScheme=findViewById(R.id.spinnerGrantUnderScheme);
        spinnerinternet=findViewById(R.id.spinnerinternet);
        edtNoOfComputer=findViewById(R.id.edtNoOfComputer);
        edtNoOfWorkingComputer=findViewById(R.id.edtNoOfWorkingComputer);
        edtNoOfLab=findViewById(R.id.edtNoOfLab);
        spinnerPowerBackup=findViewById(R.id.spinnerPowerBackup);
        spinnerFurniture=findViewById(R.id.spinnerFurniture);
        submitComputerLabBtn=findViewById(R.id.submitComputerLabBtn);
        spinnerComputerOperator=findViewById(R.id.spinnerComputerOperator);
        constraintLayout36=findViewById(R.id.constraintLayout36);


        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerComputeLabAvailabelty.setAdapter(arrayAdapter);

        ArrayList<String> arrayListInstallationYear=new ArrayList<>();
        arrayListInstallationYear.add("2015");
        arrayListInstallationYear.add("2016");
        arrayListInstallationYear.add("2017");
        arrayListInstallationYear.add("2018");
        arrayListInstallationYear.add("2019");
        arrayListInstallationYear.add("2020");
        arrayListInstallationYear.add("2021");
        arrayListInstallationYear.add("2022");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerInstallationYear.setAdapter(arrayAdapter1);

        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerGrantUnderScheme.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListInternet =new ArrayList<>();
        arrayListInternet.add("Yes");
        arrayListInternet.add("No");
        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListInternet);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerinternet.setAdapter(arrayAdapter3);

        ArrayList<String> arrayListPowerbackup =new ArrayList<>();
        arrayListPowerbackup.add("Generator");
        arrayListPowerbackup.add("Invertor");
        arrayListPowerbackup.add("UPS");
        arrayListPowerbackup.add("None");
        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListPowerbackup);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerPowerBackup.setAdapter(arrayAdapter4);

        ArrayList<String> arrayListFurnitures=new ArrayList<>();
        arrayListFurnitures.add("Yes");
        arrayListFurnitures.add("No");
        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListFurnitures);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFurniture.setAdapter(arrayAdapter5);
        spinnerComputerOperator.setAdapter(arrayAdapter5);
        spinnerPrinterAvailable.setAdapter(arrayAdapter5);
        spinnerScannerAvailable.setAdapter(arrayAdapter5);

        ArrayList<String> arrayListComputerOperator =new ArrayList<>();
        arrayListComputerOperator.add("Yes");
        arrayListComputerOperator.add("No");
        ArrayAdapter<String> arrayAdapter6=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListComputerOperator);
        arrayAdapter6.setDropDownViewResource(R.layout.custom_text_spiiner);


        ComputerLabImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsComputerlab.this)
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                // permission is granted, open the camera

                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 7);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                // check for permanent denial of permission
                                if (response.isPermanentlyDenied()) {

                                    // navigate user to app settings
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        recyclerViewComputerLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter4(this, arrayListImages1);
        recyclerViewComputerLab.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinnerComputeLabAvailabelty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerComputeLabAvailabelty.getSelectedItem().toString().equals("No")){
                        constraintLayout36.setVisibility(View.GONE);
                    }else{
                    constraintLayout36.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitComputerLabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if (!spinnerComputeLabAvailabelty.getSelectedItem().toString().equals("No")){
                    if (arrayListImages1.size()==0){
                        dialog2.dismiss();
                        Toast.makeText(UpdateDetailsComputerlab.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                    }else {
                        runService();


                    }
                }else{
                  runService();
                }

            }
        });

    }

    private void runService() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "onClick: "+paraComputerLab("1","19","ComputerLab",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerInstallationYear.getSelectedItem().toString(),edtNoOfComputer.getText().toString(),edtNoOfWorkingComputer.getText().toString(),spinnerGrantUnderScheme.getSelectedItem().toString(),spinnerinternet.getSelectedItem().toString(),spinnerPowerBackup.getSelectedItem().toString(),spinnerFurniture.getSelectedItem().toString(),spinnerComputerOperator.getSelectedItem().toString(),edtNoOfLab.getText().toString(),spinnerPrinterAvailable.getSelectedItem().toString(),spinnerScannerAvailable.getSelectedItem().toString(),spinnerComputeLabAvailabelty.getSelectedItem().toString(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadComputerLab(paraComputerLab("1","19","ComputerLab",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerInstallationYear.getSelectedItem().toString(),edtNoOfComputer.getText().toString(),edtNoOfWorkingComputer.getText().toString(),spinnerGrantUnderScheme.getSelectedItem().toString(),spinnerinternet.getSelectedItem().toString(),spinnerPowerBackup.getSelectedItem().toString(),spinnerFurniture.getSelectedItem().toString(),spinnerComputerOperator.getSelectedItem().toString(),edtNoOfLab.getText().toString(),spinnerPrinterAvailable.getSelectedItem().toString(),spinnerScannerAvailable.getSelectedItem().toString(),spinnerComputeLabAvailabelty.getSelectedItem().toString(),arrayListImages1));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                TextView textView=dialog.findViewById(R.id.dialogtextResponse);
                Button button=dialog.findViewById(R.id.BtnResponseDialoge);
                try {
                    if (response.body().get(0).get("Status").getAsString().equals("E")){
                        textView.setText("You already uploaded details ");

                    }else if(response.body().get(0).get("Status").getAsString().equals("S")){
                        textView.setText("Your details Submitted successfully ");
                    }
                    dialog2.dismiss();

                    dialog.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                            dialog.dismiss();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
    }

    private JsonObject paraComputerLab(String action,String paramId,String paramName,String lat,String longt,String schoolId,String periodId,String Usertypeid,String getUserid,String InstallationYear,String NoOfComputer, String NoOfWorkingComputer,String GrantUnderScheme,String internet,String PowerBackup, String Furniture,String computerOperator ,String NoOfLab,String PrinterAvailable, String ScannerAvailable,String availabale ,ArrayList<Bitmap> arrayListImages1 ){

        JsonObject jsonObject=new JsonObject();
        if (availabale.equals("No")){
            jsonObject.addProperty("Action",action);
            jsonObject.addProperty("ParamId",paramId);
            jsonObject.addProperty("ParamName",paramName);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodId);
            jsonObject.addProperty("InstallationYear","0");
            jsonObject.addProperty("NoOfComputers","0");
            jsonObject.addProperty("NoOfWorkingComputers","0");
            jsonObject.addProperty("Scheme","");
            jsonObject.addProperty("Internet","");
            jsonObject.addProperty("PowerBackUp","");
            jsonObject.addProperty("Furnitures","");
            jsonObject.addProperty("ComputerOperator","");
            jsonObject.addProperty("NoOfComputerLab","0");
            jsonObject.addProperty("PrinterStatus","");
            jsonObject.addProperty("ScannerAvl","");
            jsonObject.addProperty("Availabilty",availabale);
            jsonObject.addProperty("Lat",lat);
            jsonObject.addProperty("Long",longt);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("CreatedBy",Usertypeid);
            jsonObject.addProperty("UserCode",getUserid);


            JsonArray jsonArray2 = new JsonArray();
            for (int i = 0; i < arrayListImages1.size(); i++) {
                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));

            }
            jsonObject.add("ComputerLabPhoto", (JsonElement) jsonArray2);
        }else{
            jsonObject.addProperty("Action",action);
            jsonObject.addProperty("ParamId",paramId);
            jsonObject.addProperty("ParamName",paramName);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodId);
            jsonObject.addProperty("InstallationYear",InstallationYear);
            jsonObject.addProperty("NoOfComputers",NoOfComputer);
            jsonObject.addProperty("NoOfWorkingComputers",NoOfWorkingComputer);
            jsonObject.addProperty("Scheme",GrantUnderScheme);
            jsonObject.addProperty("Internet",internet);
            jsonObject.addProperty("PowerBackUp",PowerBackup);
            jsonObject.addProperty("Furnitures",Furniture);
            jsonObject.addProperty("ComputerOperator",computerOperator);
            jsonObject.addProperty("NoOfComputerLab",NoOfLab);
            jsonObject.addProperty("PrinterStatus",PrinterAvailable);
            jsonObject.addProperty("ScannerAvl",ScannerAvailable);
            jsonObject.addProperty("Availabilty",availabale);
            jsonObject.addProperty("Lat",lat);
            jsonObject.addProperty("Long",longt);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("CreatedBy",Usertypeid);
            jsonObject.addProperty("UserCode",getUserid);


            JsonArray jsonArray2 = new JsonArray();
            for (int i = 0; i < arrayListImages1.size(); i++) {
                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));

            }
            jsonObject.add("ComputerLabPhoto", (JsonElement) jsonArray2);
        }

        return jsonObject;
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }


    private JsonObject paraGetImageBase64( Bitmap bitmap, int i) {
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.addProperty("id", String.valueOf(i + 1));
            jsonObject.addProperty("photos", BitMapToString(getResizedBitmap(bitmap, 300)));
//            Log.d("TAG", "paraGetImageBase64: "+BitMapToString(bitmap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            arrayListImages1.add(bitmap);


        }
    }
}