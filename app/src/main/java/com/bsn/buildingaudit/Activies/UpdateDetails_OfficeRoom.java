package com.bsn.buildingaudit.Activies;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import com.bsn.buildingaudit.Adapters.ImageAdapter5;
import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.CompressLib.Compressor;
import com.bsn.buildingaudit.ConstantValues.ConstantFile;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetails_OfficeRoom extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    Dialog dialog,dialog2;
    String action;
    String currentImagePath=null;
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    File imageFile=null;
    ImageAdapter5 adapter6;
    Spinner officeRoomWorkingStatus,spinnerOfficeRoomAvailabelty,spinnerFurnitureAvailabiltyOR,spinnerAlmiraAndRacksAvailabiltyOR;
    ConstraintLayout constraintLayoutPR;
    ImageView officeImageUploadBtn;
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter,arrayAdapter2,arrayAdapter6;
    RecyclerView recyclerViewOffice,recyclerViewOfficeFromServer;
    Button officesubmitLabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_office_room);
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
        Intent i1=getIntent();
        action=i1.getStringExtra("Action");
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
        officeRoomWorkingStatus=findViewById(R.id.OfficeRoomWorkingStatus);
        constraintLayoutPR=findViewById(R.id.constraintLayoutPR);
        officesubmitLabBtn=findViewById(R.id.officesubmitLabBtn);
        officeImageUploadBtn=findViewById(R.id.officeImageUploadBtn);
        spinnerAlmiraAndRacksAvailabiltyOR=findViewById(R.id.spinnerAlmiraAndRacksAvailabiltyOR);
        spinnerFurnitureAvailabiltyOR=findViewById(R.id.spinnerFurnitureAvailabiltyOR);
        recyclerViewOffice=findViewById(R.id.recyclerViewOffice);
        recyclerViewOfficeFromServer=findViewById(R.id.recyclerViewofficeFromServer);
        spinnerOfficeRoomAvailabelty=findViewById(R.id.spinnerOfficeRoomAvailabelty);
        if (action.equals("3")){
            fetchAllDataFromServer();
        }

        ArrayList<String> arrayListAvailbilty6=new ArrayList<>();
        arrayListAvailbilty6.add("Yes");
        arrayListAvailbilty6.add("No");
        arrayListAvailbilty6.add("Alternate Room");
        arrayAdapter6=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty6);
        arrayAdapter6.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerOfficeRoomAvailabelty.setAdapter(arrayAdapter6);


        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFurnitureAvailabiltyOR.setAdapter(arrayAdapter);
        spinnerAlmiraAndRacksAvailabiltyOR.setAdapter(arrayAdapter);

        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Good Condition");
        arrayListSpinner2.add("Minor Repairing");
        arrayListSpinner2.add("Major repairing");
        arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner2);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        officeRoomWorkingStatus.setAdapter(arrayAdapter2);

        spinnerOfficeRoomAvailabelty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerOfficeRoomAvailabelty.getSelectedItem().toString().equals("No")){
                    constraintLayoutPR.setVisibility(View.GONE);
                }else{
                    constraintLayoutPR.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        officeImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(UpdateDetails_OfficeRoom.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetails_OfficeRoom.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetails_OfficeRoom.this);

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
        recyclerViewOffice.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter5(this, arrayListImages1);
        recyclerViewOffice.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();

        officesubmitLabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if (action.equals("3")){
                    if (!spinnerOfficeRoomAvailabelty.getSelectedItem().toString().equals("No")){ if (arrayListImages1.size()==0 && aList.size()==0){
                        Toast.makeText(UpdateDetails_OfficeRoom.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();

                    }else {
                        runService();

                    }}else {
                        runService();
                    }
                }else {
                    if (!spinnerOfficeRoomAvailabelty.getSelectedItem().toString().equals("No")){ if (arrayListImages1.size()==0){
                        Toast.makeText(UpdateDetails_OfficeRoom.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();

                    }else {
                        runService();

                    }}else {
                        runService();
                    }
                }

            }
        });

    }

    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkOfficeRoom(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"27"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerPositionForFurnitureAvl=0,spinnerPositionForAlmirahRacksAvl=0;
                int spinnerPositionForRainHarvestingAvl = arrayAdapter6.getPosition(response.body().get(0).get("SeperateRoomsAvl").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                try{

                    spinnerPositionForFurnitureAvl = arrayAdapter.getPosition(response.body().get(0).get("FurnitureAvl").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("FurnitureAvl").getAsString());
                }catch (Exception e){

                }
                try {
                    spinnerPositionForAlmirahRacksAvl = arrayAdapter.getPosition(response.body().get(0).get("AlmirahRacksAvl").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("AlmirahRacksAvl").getAsString());

                }catch (Exception e){

                }
                int spinnerPositionForWorkingStatus = arrayAdapter2.getPosition(response.body().get(0).get("Status").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("Status").getAsString());

                spinnerOfficeRoomAvailabelty.setSelection(spinnerPositionForRainHarvestingAvl);
                officeRoomWorkingStatus.setSelection(spinnerPositionForWorkingStatus);
                spinnerAlmiraAndRacksAvailabiltyOR.setSelection(spinnerPositionForAlmirahRacksAvl);
                spinnerFurnitureAvailabiltyOR.setSelection(spinnerPositionForFurnitureAvl);




                recyclerViewOfficeFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetails_OfficeRoom.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").getAsString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                if (!aList.get(0).isEmpty()){
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetails_OfficeRoom.this,aList);
                recyclerViewOfficeFromServer.setAdapter(onlineImageRecViewAdapter);
            }
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
    private void runService() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListImages1.size()];
        for (int i = 0; i < arrayListImages1.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListImages1.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdateDetails_OfficeRoom.this)
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
            if (spinnerOfficeRoomAvailabelty.getSelectedItem().toString().equals("No")){
                deletUrl=RequestBody.create(MediaType.parse("multipart/form-data"),paraAllDeleteUrls());
            }else{
                deletUrl = RequestBody.create(MediaType.parse("multipart/form-data"),paraDeletUlrs());

            }
        }else {
            deletUrl=null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraoffice(action,"27","OfficeRoomDetails",spinnerOfficeRoomAvailabelty.getSelectedItem().toString(),officeRoomWorkingStatus.getSelectedItem().toString(),spinnerAlmiraAndRacksAvailabiltyOR.getSelectedItem().toString(),spinnerFurnitureAvailabiltyOR.getSelectedItem().toString(),applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(),applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Log.d("TAG", "onClick: "+paraoffice(action,"27","OfficeRoomDetails",spinnerOfficeRoomAvailabelty.getSelectedItem().toString(),officeRoomWorkingStatus.getSelectedItem().toString(),spinnerAlmiraAndRacksAvailabiltyOR.getSelectedItem().toString(),spinnerFurnitureAvailabiltyOR.getSelectedItem().toString(),applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(),applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadOfficeRoom(surveyImagesParts,description,deletUrl);
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

    private String paraAllDeleteUrls() {
        JsonArray jsonArray=new JsonArray();



        for (int i = 0; i < aList.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            String newUrl2=aList.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2.trim());
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }
    private String paraoffice(String action, String s, String principaofficeRoomDetails, String toString, String toString1,String AlmirahRacksAvl,String FurnitureAvl, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<File> arrayListImages1) {
        JsonObject jsonObject=new JsonObject();
        if (toString.equals("no")){
            jsonObject.addProperty("Action",action);
            jsonObject.addProperty("ParamId",s);
            jsonObject.addProperty("ParamName",principaofficeRoomDetails);
            jsonObject.addProperty("SeperateRoomsAvl",toString);
            jsonObject.addProperty("Status","");
            jsonObject.addProperty("FurnitureAvl","");
            jsonObject.addProperty("AlmirahRacksAvl","");
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);
//            JsonArray jsonArray = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("PrincipalPhotos", (JsonElement) jsonArray);
        }else{
            jsonObject.addProperty("Action",action);
            jsonObject.addProperty("ParamId",s);
            jsonObject.addProperty("ParamName",principaofficeRoomDetails);
            jsonObject.addProperty("SeperateRoomsAvl",toString);
            jsonObject.addProperty("Status",toString1);
            jsonObject.addProperty("FurnitureAvl",FurnitureAvl);
            jsonObject.addProperty("AlmirahRacksAvl",AlmirahRacksAvl);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);
//            JsonArray jsonArray = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("PrincipalPhotos", (JsonElement) jsonArray);
        }

        return jsonObject.toString();
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

    private File getImageFile() throws IOException{
        String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageName="jpg+"+timeStamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageName,".jpg",storageDir);

        currentImagePath=imageFile.getAbsolutePath();
        Log.d("TAG", "getImageFile: "+currentImagePath);
        return imageFile;
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
}