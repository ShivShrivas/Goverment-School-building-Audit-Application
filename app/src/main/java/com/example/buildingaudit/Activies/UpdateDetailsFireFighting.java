package com.example.buildingaudit.Activies;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Adapters.ImageAdapter5;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.CompressLib.Compressor;
import com.example.buildingaudit.ConstantValues.ConstantFile;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailsFireFighting extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter6.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter6.notifyDataSetChanged();

    }
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    ImageAdapter5 adapter6;
    DatePickerDialog datePickerDialog;
    ImageView firefightingImageUploadBtn;
    RecyclerView recyclerViewFireFightning,recyclerViewFireFightningFromServer;
    TextView userName,schoolAddress,schoolName;
    String todayDate,fromdate;
    ArrayAdapter<String> adapter,arrayAdapter3,arrayAdapter2,arrayAdapter1;
    Dialog dialog;
    ConstraintLayout constraintLayout30;
    Button btnUpdateFireFighting;
    ApplicationController applicationController;
    Spinner spinnerFireFightTraining,spinnerFireFightRenewalStatus,spinnerFireFightWorkingStatus,spinnerFireFightingInstallationYear,spinnerFireFightAvailabelty;
    TextView edtrenewalDate;
    Dialog dialog2;
    String action;
    String currentImagePath=null;
    EditText edtNotWorkingFF,edtWorkingFF,edtTotalFF;
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    File imageFile=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_fire_fighting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
            Intent i1=getIntent();
            action=i1.getStringExtra("Action");
        dialog2 = new Dialog(this);
        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        spinnerFireFightTraining=findViewById(R.id.spinnerFireFightTraining);
        recyclerViewFireFightning=findViewById(R.id.recyclerViewFireFightning);
        firefightingImageUploadBtn=findViewById(R.id.firefightingImageUploadBtn);
        spinnerFireFightRenewalStatus=findViewById(R.id.spinnerFireFightRenewalStatus);
        spinnerFireFightWorkingStatus=findViewById(R.id.spinnerFireFightWorkingStatus);
        spinnerFireFightingInstallationYear=findViewById(R.id.spinnerFireFightingInstallationYear);
        spinnerFireFightAvailabelty=findViewById(R.id.spinnerFireFightAvailabelty);
        btnUpdateFireFighting=findViewById(R.id.btnUpdateFireFighting);
        constraintLayout30=findViewById(R.id.constraintLayout30);
        edtrenewalDate=findViewById(R.id.edtrenewalDate);
        edtTotalFF=findViewById(R.id.edtTotalFF);
        edtWorkingFF=findViewById(R.id.edtWorkingFF);
        edtNotWorkingFF=findViewById(R.id.edtNotWorkingFF);
        recyclerViewFireFightningFromServer=findViewById(R.id.recyclerViewFireFightningFromServer);

        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
      adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightAvailabelty.setAdapter(adapter);


        ArrayList<String> arrayListInstallationYear=new ArrayList<>();
        for (int i = 1990; i <=2022; i++) {
            arrayListInstallationYear.add(String.valueOf(i));
        }
     arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightingInstallationYear.setAdapter(arrayAdapter1);



        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

         arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightWorkingStatus.setAdapter(arrayAdapter2);



        ArrayList<String> arrayListRenewalStatus=new ArrayList<>();
        arrayListRenewalStatus.add("Valid");
        arrayListRenewalStatus.add("Invalid");

         arrayAdapter3=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListRenewalStatus);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightRenewalStatus.setAdapter(arrayAdapter3);
        spinnerFireFightTraining.setAdapter(adapter);




        firefightingImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(UpdateDetailsFireFighting.this)
                        .withPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                if (multiplePermissionsReport.areAllPermissionsGranted()){
                                    Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if (i.resolveActivity(getPackageManager())!=null){

                                        try {
                                            imageFile =getImageFile();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        if (imageFile!=null){
//                                            File compressedImage = new Compressor.Builder(UpdateDetailsBioMetric.this)
//                                                    .setMaxWidth(720)
//                                                    .setMaxHeight(720)
//                                                    .setQuality(75)
//                                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
//                                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
//                                                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
//                                                    .build()
//                                                    .compressToFile(imageFile);
                                            arrayListImages1.add(imageFile);
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsFireFighting.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsFireFighting.this);

                                    // below line is the title
                                    // for our alert dialog.
                                    builder.setTitle("Need Permissions");

                                    // below line is our message for our dialog
                                    builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
                                    builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // this method is called on click on positive
                                            // button and on clicking shit button we
                                            // are redirecting our user from our app to the
                                            // settings page of our app.
                                            dialog.cancel();
                                            // below is the intent from which we
                                            // are redirecting our user.
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            startActivityForResult(intent, 101);
                                        }
                                    });
                                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // this method is called when
                                            // user click on negative button.
                                            dialog.cancel();
                                        }
                                    });
                                    // below line is used
                                    // to display our dialog
                                    builder.show();
                                }
                            }


                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        edtrenewalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();
                final int mYear = c.get(java.util.Calendar.YEAR);
                final int mDay = c.get(Calendar.MONTH);
                final int cDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateDetailsFireFighting.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String todaysDate=dayOfMonth+ "-"+ (monthOfYear + 1) + "-" + year;
                                SimpleDateFormat spf=new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat spf1=new SimpleDateFormat("dd-MM-yyyy");
                                Date newDate= null;
                                Date newDate1= null;
                                try {
                                    newDate = spf.parse(todaysDate);
                                    newDate1 = spf.parse(todaysDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                spf= new SimpleDateFormat("yyyy-MM-dd");
                                spf1= new SimpleDateFormat("dd-MM-yyyy");
                                fromdate= spf.format(newDate);
                                edtrenewalDate.setText( fromdate);

                            }
                        }, mYear, mDay, cDay);
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());

                datePickerDialog.show();
            }
        });
        recyclerViewFireFightning.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter5(this, arrayListImages1);
        recyclerViewFireFightning.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();
            spinnerFireFightAvailabelty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (spinnerFireFightAvailabelty.getSelectedItem().toString().equals("No")){
                        constraintLayout30.setVisibility(View.GONE);
                    }else {
                        constraintLayout30.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        btnUpdateFireFighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if (spinnerFireFightAvailabelty.getSelectedItem().equals("No")){
                    runService();
                }else{
                    if ( !checkValidation(Integer.parseInt(edtTotalFF.getText().toString().trim()),Integer.parseInt(edtWorkingFF.getText().toString().trim())
                            ,Integer.parseInt(edtNotWorkingFF.getText().toString().trim()))){
                        dialog2.dismiss();
                        Toast.makeText(UpdateDetailsFireFighting.this, "Please enter correct count of fire fighting", Toast.LENGTH_SHORT).show();
                    }else if (!spinnerFireFightAvailabelty.getSelectedItem().toString().equals("No")){
                        if (action.equals("3")){
                            if (arrayListImages1.size()==0 && aList.size()==0){
                                dialog2.dismiss();

                                Toast.makeText(UpdateDetailsFireFighting.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                            }else {
                                runService();

                            }
                        }else{
                            if (arrayListImages1.size()==0){
                                dialog2.dismiss();

                                Toast.makeText(UpdateDetailsFireFighting.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                            }else {
                                runService();

                            }
                        }

                    }else{
                        runService();
                    }

                }

            }
        });
    }

    private boolean checkValidation(int total, int working, int notworking) {
        if (total!=working+notworking){
            return false;
        }else{
            return true;
        }
    }
    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkFireFighting(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"12"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerPositionForAvailabilty = adapter.getPosition(response.body().get(0).get("Availabilty").getAsString())==-1?0:adapter.getPosition(response.body().get(0).get("Availabilty").getAsString());
                int spinnforTraining= adapter.getPosition(response.body().get(0).get("Training").getAsString())==-1?0:adapter.getPosition(response.body().get(0).get("Training").getAsString());
                int spinnerPositionForRenewalStatus = arrayAdapter3.getPosition(response.body().get(0).get("RenewalStatus").getAsString())==-1?0:arrayAdapter3.getPosition(response.body().get(0).get("RenewalStatus").getAsString());
                int spinnerPositionForWorkingStatus = arrayAdapter2.getPosition(response.body().get(0).get("WorkingStatus").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("WorkingStatus").getAsString());
                int spinnerPositionForInstallationYear = arrayAdapter2.getPosition(response.body().get(0).get("InstallationYear").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("InstallationYear").getAsString());

                spinnerFireFightAvailabelty.setSelection(spinnerPositionForAvailabilty);
                spinnerFireFightTraining.setSelection(spinnforTraining);
                spinnerFireFightRenewalStatus.setSelection(spinnerPositionForRenewalStatus);
                spinnerFireFightWorkingStatus.setSelection(spinnerPositionForWorkingStatus);
                spinnerFireFightingInstallationYear.setSelection(spinnerPositionForInstallationYear);

                int totalRo=Integer.parseInt(response.body().get(0).get("NonWorkingCount").getAsString())+Integer.parseInt(response.body().get(0).get("WorkingCount").getAsString());

                edtNotWorkingFF.setText(response.body().get(0).get("NonWorkingCount").getAsString());
                edtWorkingFF.setText(response.body().get(0).get("WorkingCount").getAsString());
                edtTotalFF.setText(String.valueOf(totalRo));
                String[] time=(response.body().get(0).get("RenewalDateStatus").getAsString()).split("T");

                edtrenewalDate.setText(time[0]);


                recyclerViewFireFightningFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsFireFighting.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsFireFighting.this,aList);
                recyclerViewFireFightningFromServer.setAdapter(onlineImageRecViewAdapter);
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }
    private JsonObject paraGetDetails2(String action, String schoolId, String periodId, String paramId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }

    private File getImageFile() throws IOException{
        String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageName="jpg+"+timeStamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageName,".jpg",storageDir);

        currentImagePath=imageFile.getAbsolutePath();
        Log.d("TAG", "getImageFile: "+currentImagePath);
        return imageFile;
    }
    private void runService() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListImages1.size()];
        for (int i = 0; i < arrayListImages1.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListImages1.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdateDetailsFireFighting.this)
                    .setMaxWidth(720)
                    .setMaxHeight(720)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(new File(arrayListImages1.get(i).getPath()));
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"),
                    compressedImage);
            surveyImagesParts[i] = MultipartBody.Part.createFormData("FileData",compressedImage.getName(),surveyBody);

        }
        RequestBody deletUrl;
        Log.d("TAG", "runService: "+paraDeletUlrs());
        if (action.equals("3")){
            deletUrl = RequestBody.create(MediaType.parse("multipart/form-data"),paraDeletUlrs());
        }else {
            deletUrl=null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraFireFight(action,"12","FireFighting",spinnerFireFightWorkingStatus.getSelectedItem().toString(),
                spinnerFireFightRenewalStatus.getSelectedItem().toString(),edtrenewalDate.getText().toString(),spinnerFireFightTraining.getSelectedItem().toString(),spinnerFireFightingInstallationYear.getSelectedItem().toString(),spinnerFireFightAvailabelty.getSelectedItem().toString(), applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Log.d("TAG", "onClick: "+paraFireFight(action,"12","FireFighting",spinnerFireFightWorkingStatus.getSelectedItem().toString(),
                spinnerFireFightRenewalStatus.getSelectedItem().toString(),edtrenewalDate.getText().toString(),spinnerFireFightTraining.getSelectedItem().toString(),spinnerFireFightingInstallationYear.getSelectedItem().toString(),spinnerFireFightAvailabelty.getSelectedItem().toString(), applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadFireFighting(surveyImagesParts,description,deletUrl);
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

    private String paraDeletUlrs() {
        JsonArray jsonArray=new JsonArray();

        Log.d("TAG", "paraDeletUlrs: "+ OnlineImageRecViewAdapterEditable.deletedUrls.size());

        for (int i = 0; i < OnlineImageRecViewAdapterEditable.deletedUrls.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable.deletedUrls.get(i));
            String newUrl2=OnlineImageRecViewAdapterEditable.deletedUrls.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2);
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }
    private String paraFireFight(String s, String s1, String fireFighting, String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<File> arrayListImages1) {

        JsonObject jsonObject=new JsonObject();
        if (toString5.equals("No")){
            jsonObject.addProperty("Action",s);
            jsonObject.addProperty("ParamId",s1);
            jsonObject.addProperty("ParamName",fireFighting);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("InstallationYear","0");
            jsonObject.addProperty("RenewalStatus","");
            jsonObject.addProperty("RenewalDateStatus","1900-01-01");
            jsonObject.addProperty("Training","");
            jsonObject.addProperty("WorkingCount","0");
            jsonObject.addProperty("NonWorkingCount","0");

            jsonObject.addProperty("WorkingStatus","");
            jsonObject.addProperty("Availabilty",toString5);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);

//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("FireFightingPhoto", (JsonElement) jsonArray2);
        }else{
            jsonObject.addProperty("Action",s);
            jsonObject.addProperty("ParamId",s1);
            jsonObject.addProperty("ParamName",fireFighting);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("InstallationYear",toString4);
            jsonObject.addProperty("RenewalStatus",toString1);
            jsonObject.addProperty("RenewalDateStatus",toString2);
            jsonObject.addProperty("WorkingCount",edtWorkingFF.getText().toString().trim());
            jsonObject.addProperty("NonWorkingCount",edtNotWorkingFF.getText().toString().trim());

            jsonObject.addProperty("Training",toString3);
            jsonObject.addProperty("WorkingStatus",toString);
            jsonObject.addProperty("Availabilty",toString5);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);

//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("FireFightingPhoto", (JsonElement) jsonArray2);
        }

        return jsonObject.toString();
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
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//            arrayListImages1.add(bitmap);


        }
    }
}