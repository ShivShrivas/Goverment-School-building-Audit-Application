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
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.ImageAdapter5;
import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable;
import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable1;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.CompressLib.Compressor;
import com.bsn.buildingaudit.ConstantValues.ConstantFile;
import com.bsn.buildingaudit.Model.VocationalRoom;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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

public class UpdateDetails_VocationalEducationRoom extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    Dialog dialog,dialog2;
    String action;
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    public ArrayList<File> arrayListImages2 = new ArrayList<>();
    String currentImagePath=null;
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    String[] StaffPhotoPathList1;
    ArrayList<String> bList=new ArrayList<>();
    File imageFile=null;
    ArrayAdapter<String> arrayAdapter,arrayAdapter2,arrayAdapter3;
    ImageAdapter5 adapter6,adapter7;
    CardView scienceLabBodyCard, physicsLabImageCard,physicsLabBodyCard,scienceLabImageCard;
    Spinner spinnerVocalRoomHSAvailability,spinnerVocalHSEquipmentStatus,
            spinnerVocalHSLabCondition,spinnerVocalRoomISAvailability,spinnerVocalISEquipmentStatus,
            spinnerVocalISCondition;
    ImageView imageUpoadVocalHS,imageUpoadeViewVocalIS;
    Button vocalRoomUploadBtn;
    RecyclerView recyclerViewVocalHs,recyclerViewVocalHsromServer,recyclerViewVocalIS,
            recyclerViewVocalISFromServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_vocational_education_room);
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
        dialog.setContentView (R.layout.respons_dialog_onsave);
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
        spinnerVocalRoomHSAvailability=findViewById(R.id.spinnerVocalRoomHSAvailability);
        spinnerVocalHSEquipmentStatus=findViewById(R.id.spinnerVocalHSEquipmentStatus);
        scienceLabBodyCard=findViewById(R.id.scienceLabBodyCardVocal);
        physicsLabBodyCard=findViewById(R.id.physicsLabBodyCardVocal);
        physicsLabImageCard=findViewById(R.id.physicsLabImageCardVocal);
        scienceLabImageCard=findViewById(R.id.scienceLabImageCardVocal);
        spinnerVocalHSLabCondition=findViewById(R.id.spinnerVocalHSLabCondition);
        spinnerVocalISEquipmentStatus=findViewById(R.id.spinnerVocalISEquipmentStatus);
        spinnerVocalISCondition=findViewById(R.id.spinnerVocalISCondition);
        spinnerVocalRoomHSAvailability=findViewById(R.id.spinnerVocalRoomHSAvailability);
        spinnerVocalRoomHSAvailability=findViewById(R.id.spinnerVocalRoomHSAvailability);
        spinnerVocalRoomHSAvailability=findViewById(R.id.spinnerVocalRoomHSAvailability);
        spinnerVocalRoomHSAvailability=findViewById(R.id.spinnerVocalRoomHSAvailability);
        spinnerVocalRoomISAvailability=findViewById(R.id.spinnerVocalRoomISAvailability);
        imageUpoadVocalHS=findViewById(R.id.imageUpoadVocalHS);
        recyclerViewVocalISFromServer=findViewById(R.id.recyclerViewVocalISFromServer);
        recyclerViewVocalIS=findViewById(R.id.recyclerViewVocalIS);
        recyclerViewVocalHsromServer=findViewById(R.id.recyclerViewVocalHsromServer);
        recyclerViewVocalHs=findViewById(R.id.recyclerViewVocalHs);
        vocalRoomUploadBtn=findViewById(R.id.vocalRoomUploadBtn);
        imageUpoadeViewVocalIS=findViewById(R.id.imageUpoadeViewVocalIS);
        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        arrayListAvailbilty.add("Alternate Room");
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerVocalRoomHSAvailability.setAdapter(arrayAdapter);
        spinnerVocalRoomISAvailability.setAdapter(arrayAdapter);

        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Good Condition");
        arrayListSpinner2.add("Minor Repairing");
        arrayListSpinner2.add("Major repairing");

        arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner2);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerVocalHSLabCondition.setAdapter(arrayAdapter2);
        spinnerVocalISCondition.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListSpinner3 = new ArrayList<>();
        arrayListSpinner3.add("Fully Equipped");
        arrayListSpinner3.add("Partially Equipped");
        arrayListSpinner3.add("Not Equipped");

        arrayAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner3);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerVocalHSEquipmentStatus.setAdapter(arrayAdapter3);
        spinnerVocalISEquipmentStatus.setAdapter(arrayAdapter3);
        spinnerVocalRoomISAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerVocalRoomISAvailability.getSelectedItem().toString().equals("No")){
                    physicsLabBodyCard.setVisibility(View.GONE);
                    physicsLabImageCard.setVisibility(View.GONE);
                }else{
                    physicsLabBodyCard.setVisibility(View.VISIBLE);
                    physicsLabImageCard.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerVocalRoomHSAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerVocalRoomHSAvailability.getSelectedItem().toString().equals("No")){
                    scienceLabBodyCard.setVisibility(View.GONE);
                    scienceLabImageCard.setVisibility(View.GONE);
                }else{
                    scienceLabBodyCard.setVisibility(View.VISIBLE);
                    scienceLabImageCard.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imageUpoadVocalHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(UpdateDetails_VocationalEducationRoom.this)
                        .withPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                if (multiplePermissionsReport.areAllPermissionsGranted()){
                                    Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if (i.resolveActivity(getPackageManager())!=null){

                                        try {
                                            imageFile =getImageFile(10);
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetails_VocationalEducationRoom.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetails_VocationalEducationRoom.this);

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
        recyclerViewVocalHs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter5(this, arrayListImages1);
        recyclerViewVocalHs.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();


        imageUpoadeViewVocalIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(UpdateDetails_VocationalEducationRoom.this)
                        .withPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                if (multiplePermissionsReport.areAllPermissionsGranted()){
                                    Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if (i.resolveActivity(getPackageManager())!=null){

                                        try {
                                            imageFile =getImageFile(12);
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
                                            arrayListImages2.add(imageFile);
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetails_VocationalEducationRoom.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetails_VocationalEducationRoom.this);

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
        recyclerViewVocalIS.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter7 = new ImageAdapter5(this, arrayListImages2);
        recyclerViewVocalIS.setAdapter(adapter7);
        adapter6.notifyDataSetChanged();

        vocalRoomUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if (action.equals("3")){
                    if (!spinnerVocalRoomHSAvailability.getSelectedItem().toString().equals("No") || !spinnerVocalRoomISAvailability.getSelectedItem().toString().equals("No")){
                        if (arrayListImages1.size()==0 && arrayListImages2.size()==0 && aList.size()==0 && bList.size()==0){
                            Toast.makeText(UpdateDetails_VocationalEducationRoom.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();

                        }else {
                            runService();

                        }}else {
                        runService();
                    }
                }else{
                    if (!spinnerVocalRoomHSAvailability.getSelectedItem().toString().equals("No") || !spinnerVocalRoomISAvailability.getSelectedItem().toString().equals("No")){
                        if (arrayListImages1.size()==0 && arrayListImages2.size()==0){
                            Toast.makeText(UpdateDetails_VocationalEducationRoom.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
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
        Call<List<JsonObject>> call=apiService.checkVocalRoom(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"26"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("RoomAvailable").getAsString().equals("No")){
                    int spinnerPositionForSeperateRoomsAvlHS = arrayAdapter.getPosition(response.body().get(0).get("RoomAvailable").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("RoomAvailable").getAsString());
                    spinnerVocalRoomHSAvailability.setSelection(spinnerPositionForSeperateRoomsAvlHS);
                    scienceLabImageCard.setVisibility(View.GONE);
                    scienceLabBodyCard.setVisibility(View.GONE);
                }else{
                    int spinnerPositionForSeperateRoomsAvlHS = arrayAdapter.getPosition(response.body().get(0).get("RoomAvailable").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("RoomAvailable").getAsString());
                    spinnerVocalRoomHSAvailability.setSelection(spinnerPositionForSeperateRoomsAvlHS);
                    int spinnerPositionForConditionHS = arrayAdapter2.getPosition(response.body().get(0).get("RoomCondition").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("RoomCondition").getAsString());
                    spinnerVocalHSLabCondition.setSelection(spinnerPositionForConditionHS);
                    int spinnerPositionFoEquipmentHS = arrayAdapter3.getPosition(response.body().get(0).get("EquipmentStatus").getAsString())==-1?0:arrayAdapter3.getPosition(response.body().get(0).get("EquipmentStatus").getAsString());
                    spinnerVocalHSEquipmentStatus.setSelection(spinnerPositionFoEquipmentHS);

                }
                if (response.body().get(1).get("RoomAvailable").getAsString().equals("No")){
                    int spinnerPositionForSeperateRoomsAvlIS = arrayAdapter.getPosition(response.body().get(1).get("RoomAvailable").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(1).get("RoomAvailable").getAsString());
                    spinnerVocalRoomISAvailability.setSelection(spinnerPositionForSeperateRoomsAvlIS);
                        physicsLabBodyCard.setVisibility(View.GONE);
                        physicsLabImageCard.setVisibility(View.GONE);
                }else{
                    int spinnerPositionForSeperateRoomsAvlIS = arrayAdapter.getPosition(response.body().get(1).get("RoomAvailable").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(1).get("RoomAvailable").getAsString());
                    int spinnerPositionForConditionIS = arrayAdapter2.getPosition(response.body().get(1).get("RoomCondition").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(1).get("RoomCondition").getAsString());
                    int spinnerPositionFoEquipmentIS = arrayAdapter3.getPosition(response.body().get(1).get("EquipmentStatus").getAsString())==-1?0:arrayAdapter3.getPosition(response.body().get(1).get("EquipmentStatus").getAsString());


                    spinnerVocalRoomISAvailability.setSelection(spinnerPositionForSeperateRoomsAvlIS);
                    spinnerVocalISCondition.setSelection(spinnerPositionForConditionIS);
                    spinnerVocalISEquipmentStatus.setSelection(spinnerPositionFoEquipmentIS);
                }




                recyclerViewVocalHsromServer.setLayoutManager(new LinearLayoutManager(UpdateDetails_VocationalEducationRoom.this,LinearLayoutManager.HORIZONTAL,false));

                try {
                    StaffPhotoPathList = response.body().get(0).get("ClassPhotoPath") == null ? new String[]{} : response.body().get(0).get("ClassPhotoPath").getAsString().split(",");
                    aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                    UpdateDetailsOfExtraThings obj = new UpdateDetailsOfExtraThings();
                    if (!aList.get(0).isEmpty()) {
                        OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter = new OnlineImageRecViewAdapterEditable(UpdateDetails_VocationalEducationRoom.this, aList);
                        recyclerViewVocalHsromServer.setAdapter(onlineImageRecViewAdapter);
                    }
                    recyclerViewVocalISFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetails_VocationalEducationRoom.this, LinearLayoutManager.HORIZONTAL, false));
                }catch (Exception e){

                }
                try{
                    StaffPhotoPathList1 = response.body().get(1).get("ClassPhotoPath") == null ? new String[]{} : response.body().get(1).get("ClassPhotoPath").getAsString().split(",");
                    bList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList1));
                    if (!bList.get(0).isEmpty()) {
                        OnlineImageRecViewAdapterEditable1 onlineImageRecViewAdapter1 = new OnlineImageRecViewAdapterEditable1(UpdateDetails_VocationalEducationRoom.this, bList);
                        recyclerViewVocalISFromServer.setAdapter(onlineImageRecViewAdapter1);
                    }
            }catch (Exception e){

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
        ArrayList<File> arrayListFinalImage=new ArrayList<>();
        arrayListFinalImage.addAll(arrayListImages1);
        arrayListFinalImage.addAll(arrayListImages2);

        ArrayList<VocationalRoom> arrayList=new ArrayList<>();
        VocationalRoom vocationalRoomHS=new VocationalRoom();
        vocationalRoomHS.setRoomAvailable(spinnerVocalRoomHSAvailability.getSelectedItem().toString());
        vocationalRoomHS.set_class("10");
        vocationalRoomHS.setRoomCondition(spinnerVocalHSLabCondition.getSelectedItem().toString());
        vocationalRoomHS.setEquipmentStatus(spinnerVocalHSEquipmentStatus.getSelectedItem().toString());


        VocationalRoom vocationalRoomIC=new VocationalRoom();

        vocationalRoomIC.setRoomAvailable(spinnerVocalRoomISAvailability.getSelectedItem().toString());
        vocationalRoomIC.set_class("12");
        vocationalRoomIC.setRoomCondition(spinnerVocalISCondition.getSelectedItem().toString());
        vocationalRoomIC.setEquipmentStatus(spinnerVocalISEquipmentStatus.getSelectedItem().toString());

        arrayList.add(vocationalRoomHS);
        arrayList.add(vocationalRoomIC);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListFinalImage.size()];
        for (int i = 0; i < arrayListFinalImage.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListFinalImage.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdateDetails_VocationalEducationRoom.this)
                    .setMaxWidth(720)
                    .setMaxHeight(720)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(new File(arrayListFinalImage.get(i).getPath()));
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
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraVocal(action,"26","VocationalRoom",arrayList,applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(),applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Log.d("TAG", "onClick: "+paraVocal(action,"26","VocationalRoom",arrayList,applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(),applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadVocalRoom(surveyImagesParts,description,deletUrl);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
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

    private String paraVocal(String action, String s, String vocationalRoom, ArrayList<VocationalRoom> arrayList, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<File> arrayListImages1) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",s);
        jsonObject.addProperty("ParamName",vocationalRoom);
        JsonArray jsonArray=new JsonArray();
        for (int i=0;i<arrayList.size();i++){
            JsonObject jsonObject1=new JsonObject();
            jsonObject1.addProperty("Srno",i+1);
            jsonObject1.addProperty("Class",arrayList.get(i).get_class());
            jsonObject1.addProperty("RoomAvailable",arrayList.get(i).getRoomAvailable());
            jsonObject1.addProperty("EquipmentStatus",arrayList.get(i).getEquipmentStatus());
            jsonObject1.addProperty("RoomCondition",arrayList.get(i).getRoomCondition());
            jsonArray.add(jsonObject1);
        }
        jsonObject.add("VRoomRecords",(JsonElement)jsonArray);
        jsonObject.addProperty("Lat",latitude);
        jsonObject.addProperty("Long",longitude);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("CreatedBy",usertypeid);
        jsonObject.addProperty("UserCode",userid);
        return jsonObject.toString();
    }

    private String paraDeletUlrs() {
        JsonArray jsonArray=new JsonArray();
        ArrayList<String> deleteUrlsFinal=new ArrayList<String>();

        if (spinnerVocalRoomHSAvailability.getSelectedItem().toString().equals("No")){
            deleteUrlsFinal.addAll(aList); }else{ deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable.deletedUrls); }

        if (spinnerVocalRoomISAvailability.getSelectedItem().toString().equals("No")){
            deleteUrlsFinal.addAll(bList); }else{ deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable1.deletedUrls); }



        for (int i = 0; i < deleteUrlsFinal.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable.deletedUrls.get(i));
            String newUrl2=deleteUrlsFinal.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2.trim());
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }

    private File getImageFile(int i) throws IOException{
        String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageName=i+"_"+timeStamp+"_";
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
        adapter7.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();


        adapter6.notifyDataSetChanged();
        adapter7.notifyDataSetChanged();

    }
}