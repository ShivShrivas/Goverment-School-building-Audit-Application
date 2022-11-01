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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import java.io.ByteArrayOutputStream;
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

public class UpdateDetailsSoundSystem extends AppCompatActivity {
Spinner spinnerSoundSystem,spinnerSchoolBand,spinnerSchoolBandForGirls;
        ImageView soundSystemImageUploadBtn;
RecyclerView recyclerViewSoundSystm,recyclerViewSoundSystmFromServer;
Button submitSoundSystemBtn;
String action;
    Dialog dialog;
    Dialog dialog2;
    String currentImagePath=null;
    String[] StaffPhotoPathList;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> aList=new ArrayList<>();
    File imageFile=null;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
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
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_sound_system);
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
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewSoundSystm=findViewById(R.id.recyclerViewSoundSystm);
        soundSystemImageUploadBtn=findViewById(R.id.soundSystemImageUploadBtn);
        spinnerSoundSystem=findViewById(R.id.spinnerSoundSystem);
        spinnerSchoolBand=findViewById(R.id.spinnerSchoolBand);
        spinnerSchoolBandForGirls=findViewById(R.id.spinnerDialog_Lang);
        submitSoundSystemBtn=findViewById(R.id.submitSoundSystemBtn);
        recyclerViewSoundSystmFromServer=findViewById(R.id.recyclerViewSoundSystmFromServer);
        dialog = new Dialog(this);
        dialog.setCancelable(false);
        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog_onsave);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerSoundSystem.setAdapter(arrayAdapter);
        spinnerSchoolBand.setAdapter(arrayAdapter);
        spinnerSchoolBandForGirls.setAdapter(arrayAdapter);



        soundSystemImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(UpdateDetailsSoundSystem.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsSoundSystem.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsSoundSystem.this);

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
        recyclerViewSoundSystm.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter5(this, arrayListImages1);
        recyclerViewSoundSystm.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();

        submitSoundSystemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if (action.equals(3)){
                    if (!spinnerSchoolBand.getSelectedItem().toString().equals("No") ||!spinnerSoundSystem.getSelectedItem().toString().equals("No") ||!spinnerSchoolBandForGirls.getSelectedItem().toString().equals("No")  ){
                        if (arrayListImages1.size()==0 && aList.size()==0){
                            Toast.makeText(UpdateDetailsSoundSystem.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();
                        }else{
                            runService();
                        }


                    }else {
                        runService();

                    }
                }else{
                    if (!spinnerSchoolBand.getSelectedItem().toString().equals("No") ||!spinnerSoundSystem.getSelectedItem().toString().equals("No") ||!spinnerSchoolBandForGirls.getSelectedItem().toString().equals("No")  ){
                        if (arrayListImages1.size()==0){
                            Toast.makeText(UpdateDetailsSoundSystem.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();
                        }else{
                            runService();
                        }


                    }else {
                        runService();

                    }
                }

            }
        });
    }

    private File getImageFile() throws IOException {
        String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageName="jpg+"+timeStamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageName,".jpg",storageDir);

        currentImagePath=imageFile.getAbsolutePath();
       
        return imageFile;
    }

    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkSoundSystem(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"22"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
               
               
                int spinnerPositionForSoundSysAvailability = arrayAdapter.getPosition(response.body().get(0).get("SoundSysAvailability").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("SoundSysAvailability").getAsString());
                int spinnerPositionForSchoolBandInstAvlBoys = arrayAdapter.getPosition(response.body().get(0).get("SchoolBandInstAvlBoys").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("SchoolBandInstAvlBoys").getAsString());
                int spinnerPositionSchoolBandInstAvlGirls = arrayAdapter.getPosition(response.body().get(0).get("SchoolBandInstAvlGirls").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("SchoolBandInstAvlGirls").getAsString());

                spinnerSoundSystem.setSelection(spinnerPositionForSoundSysAvailability);
                spinnerSchoolBand.setSelection(spinnerPositionForSchoolBandInstAvlBoys);
                spinnerSchoolBandForGirls.setSelection(spinnerPositionSchoolBandInstAvlGirls);



                recyclerViewSoundSystmFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsSoundSystem.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").getAsString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                if (!aList.get(0).isEmpty()){
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsSoundSystem.this,aList);
                recyclerViewSoundSystmFromServer.setAdapter(onlineImageRecViewAdapter);
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
           
            File compressedImage = new Compressor.Builder(UpdateDetailsSoundSystem.this)
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
       
        if (action.equals("3")){
            if (spinnerSchoolBand.getSelectedItem().toString().equals("No") &&spinnerSchoolBandForGirls.getSelectedItem().toString().equals("No") &&spinnerSoundSystem.getSelectedItem().toString().equals("No")){
                deletUrl=RequestBody.create(MediaType.parse("multipart/form-data"),paraAllDeleteUrls());
            }else{
                deletUrl = RequestBody.create(MediaType.parse("multipart/form-data"),paraDeletUlrs());

            }
        }else {
            deletUrl=null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraSoundSystem(action,"22","SoundSystemDetails",spinnerSoundSystem.getSelectedItem().toString(),spinnerSchoolBand.getSelectedItem().toString(),spinnerSchoolBandForGirls.getSelectedItem().toString(), applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadSoundSystem(surveyImagesParts,description,deletUrl);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
               
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


    private String paraDeletUlrs() {
        JsonArray jsonArray=new JsonArray();

       

        for (int i = 0; i < OnlineImageRecViewAdapterEditable.deletedUrls.size(); i++) {
            JsonObject jsonObject=new JsonObject();
           
            String newUrl2=OnlineImageRecViewAdapterEditable.deletedUrls.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2);
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }
    private String paraSoundSystem(String action, String paramid, String paramName, String soundSysAvailability, String schoolBandInstAvlBoys, String schoolBandInstAvlGirls, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<File> arrayListImages1) {
        JsonObject jsonObject=new JsonObject();

        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramid);
        jsonObject.addProperty("ParamName",paramName);
        jsonObject.addProperty("SoundSysAvailability",soundSysAvailability);
        jsonObject.addProperty("SchoolBandInstAvlBoys",schoolBandInstAvlBoys);
        jsonObject.addProperty("SchoolBandInstAvlGirls",schoolBandInstAvlGirls);
        jsonObject.addProperty("Lat",latitude);
        jsonObject.addProperty("Long",longitude);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("CreatedBy",usertypeid);
        jsonObject.addProperty("UserCode",userid);
//
//        JsonArray jsonArray2 = new JsonArray();
//        for (int i = 0; i < arrayListImages1.size(); i++) {
//            jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//        }
//        jsonObject.add("SoundPhoto", (JsonElement) jsonArray2);
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
//           
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