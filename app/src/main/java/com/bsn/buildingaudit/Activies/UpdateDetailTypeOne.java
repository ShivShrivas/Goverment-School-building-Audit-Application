package com.bsn.buildingaudit.Activies;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.ImageAdapter;
import com.bsn.buildingaudit.Adapters.ImageAdapter5;
import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable;
import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable1;
import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable2;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.CompressLib.Compressor;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

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

public class UpdateDetailTypeOne extends AppCompatActivity {
RecyclerView recyclerViewTwoTypeOne,recyclerViewThreeTypeOne,recyclerViewFourTypeOne,recyclerViewTwoTypeOneGoodConditionFromServer,recyclerViewMinorConditionFromServer,recyclerViewMjorRepairingFromServer;
Spinner spinnerBoardClass;
EditText edtPodiumClass,greenBoardCount,whiteBoardCont,blackBoardCount;
    TextView userName,schoolAddress,schoolName;

ImageView totalRoomImageUploadBtn,goodConditionImageUploadBtn,minorRepairingUploadImageBtn,majorRepairingUploadImageBtn;
int cameraType;
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    public ArrayList<File> arrayListImages2 = new ArrayList<>();
    public ArrayList<File> arrayListImages3 = new ArrayList<>();
    public ArrayList<File> arrayListImages4 = new ArrayList<>();
    ImageAdapter adapter;
    ImageAdapter5 adapter2;
    String action;
    Dialog dialog,dialog2;
    String currentImagePath=null;
    ImageAdapter5 adapter3;
    ApplicationController applicationController;
    ImageAdapter5 adapter4;
    ConstraintLayout constratinflayout;
    EditText majorRepairingClassroom,minorRepairingClassroom,goodCondtionClassroom,totalClassRooms;
    Button classRoomSubmitbtn;
    File imageFile=null;
    ArrayAdapter<String> arrayAdapter2;
    ArrayAdapter<String> arrayAdapter;
    String[] goodConditionPathList,minorPhotoPath,majorPhotoPath;
    ArrayList<String> aList=new ArrayList<>();
    ArrayList<String> bList=new ArrayList<>();
    ArrayList<String> cList=new ArrayList<>();
    @Override
    protected void onStart() {
        super.onStart();

        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
     onBackPressed();
     return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail_type_one);
        applicationController= (ApplicationController) getApplication();
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2 = new Dialog(this);
        Intent i=getIntent();
        action=i.getStringExtra("Action");
        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        recyclerViewFourTypeOne=findViewById(R.id.recyclerViewMajorRepairing);
        recyclerViewTwoTypeOne=findViewById(R.id.recyclerViewTwoTypeOneGoodCondition);
        recyclerViewMjorRepairingFromServer=findViewById(R.id.recyclerViewMjorRepairingFromServer);
        recyclerViewMinorConditionFromServer=findViewById(R.id.recyclerViewMinorConditionFromServer);
        recyclerViewTwoTypeOneGoodConditionFromServer=findViewById(R.id.recyclerViewTwoTypeOneGoodConditionFromServer);
        constratinflayout=findViewById(R.id.constratinflayout);
        edtPodiumClass =findViewById(R.id.edtPodiumClass);
        blackBoardCount =findViewById(R.id.blackBoardCount);
        greenBoardCount =findViewById(R.id.greenBoardCount);
        whiteBoardCont =findViewById(R.id.whiteBoardCont);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        recyclerViewThreeTypeOne=findViewById(R.id.recyclerViewMinorCondition);
        majorRepairingClassroom=findViewById(R.id.majorRepairingClassroom);
        minorRepairingClassroom=findViewById(R.id.minorRepairingClassroom);
        goodCondtionClassroom=findViewById(R.id.goodCondtionClassroom);
        totalClassRooms=findViewById(R.id.totalClassRooms);
        totalRoomImageUploadBtn=findViewById(R.id.totalRoomImageUploadBtn);
        classRoomSubmitbtn=findViewById(R.id.classRoomSubmitbtn);
        goodConditionImageUploadBtn=findViewById(R.id.goodConditionImageUploadBtn);
        minorRepairingUploadImageBtn=findViewById(R.id.minorRepairingUploadImageBtn);
        majorRepairingUploadImageBtn=findViewById(R.id.majorRepairingUploadImageBtn);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());

        goodCondtionClassroom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (goodCondtionClassroom.getText().toString().equals("0")){
                    goodConditionImageUploadBtn.setVisibility(View.GONE);
                }else
                {
                    goodConditionImageUploadBtn.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        majorRepairingClassroom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (majorRepairingClassroom.getText().toString().equals("0")){
                    majorRepairingUploadImageBtn.setVisibility(View.GONE);
                }else {
                    majorRepairingUploadImageBtn.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        minorRepairingClassroom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (minorRepairingClassroom.getText().toString().equals("0")){
                    minorRepairingUploadImageBtn.setVisibility(View.GONE);
                }else {
                    minorRepairingUploadImageBtn.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        classRoomSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                try {
                    int totalClassRoom=Integer.parseInt(goodCondtionClassroom.getText().toString())+Integer.parseInt(minorRepairingClassroom.getText().toString())+Integer.parseInt(majorRepairingClassroom.getText().toString());
                    if (totalClassRoom!=Integer.parseInt(totalClassRooms.getText().toString().trim())){
                        dialog2.dismiss();
                        Log.d("TAG", "onClick: "+totalClassRoom+"//////"+totalClassRooms.getText().toString());
                        Snackbar.make(constratinflayout, "Please provide correct number of rooms", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .show();

                    }else {
                        if (action.equals("3")){
                            if (arrayListImages3.size()== 0 && arrayListImages2.size()==0&& arrayListImages4.size() == 0 && aList.size()==0&& bList.size()==0&& cList.size()==0 ) {
                                dialog2.dismiss();

                                Toast.makeText(UpdateDetailTypeOne.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                            }else{
                                runService(totalClassRoom);
                            }

                        }else{
                        }if ( !checkValidations(arrayListImages2,Integer.valueOf(goodCondtionClassroom.getText().toString()))){
                            dialog2.dismiss();
                            Toast.makeText(UpdateDetailTypeOne.this, "Please add minimum one image in good condition classes", Toast.LENGTH_SHORT).show();
                        }
                        else if(!checkValidations(arrayListImages3,Integer.valueOf(minorRepairingClassroom.getText().toString()))){
                            dialog2.dismiss();
                            Toast.makeText(UpdateDetailTypeOne.this, "Please add minimum one image in minor repairing classes ", Toast.LENGTH_SHORT).show();
                        }   else if(!checkValidations(arrayListImages4,Integer.valueOf(majorRepairingClassroom.getText().toString()))){
                            dialog2.dismiss();
                            Toast.makeText(UpdateDetailTypeOne.this, "Please add minimum one image in major repairing classes ", Toast.LENGTH_SHORT).show();
                        } else {
                            runService(totalClassRoom);
                        }
                        }



                }catch (Exception e){
                    Log.d("TAG", "onClick: "+e.getMessage());
                    Toast.makeText(UpdateDetailTypeOne.this, "please fill all room count properly", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();

                }

            }
        });
        totalRoomImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=1;
                Dexter.withActivity(UpdateDetailTypeOne.this)
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
        goodConditionImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=2;
                Dexter.withContext(UpdateDetailTypeOne.this)
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
                                            arrayListImages2.add(imageFile);
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailTypeOne.this,"com.bsn.buildingaudit.provider",imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailTypeOne.this);

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
        minorRepairingUploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=3;
                Dexter.withContext(UpdateDetailTypeOne.this)
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
                                            arrayListImages3.add(imageFile);
                                            Uri imageUri=FileProvider.getUriForFile(UpdateDetailTypeOne.this,"com.bsn.buildingaudit.provider",imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailTypeOne.this);

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
        majorRepairingUploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=4;
                Dexter.withContext(UpdateDetailTypeOne.this)
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
                                            arrayListImages4.add(imageFile);
                                            Uri imageUri=FileProvider.getUriForFile(UpdateDetailTypeOne.this,"com.bsn.buildingaudit.provider",imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailTypeOne.this);

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

        recyclerViewTwoTypeOne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2 = new ImageAdapter5(this, arrayListImages2);
        recyclerViewTwoTypeOne.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        recyclerViewThreeTypeOne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter3 = new ImageAdapter5(this, arrayListImages3);
        recyclerViewThreeTypeOne.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();
        recyclerViewFourTypeOne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter4 = new ImageAdapter5(this, arrayListImages4);
        recyclerViewFourTypeOne.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();

        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);



        ArrayList<String> arrayListBoard=new ArrayList<>();
        arrayListBoard.add("White Board");
        arrayListBoard.add("Green Board");
        arrayListBoard.add("Black Board");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListBoard);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);




    }

    private boolean checkValidations(ArrayList<File> imagearayList, Integer classRoomCount) {
        if (classRoomCount>0){
            return imagearayList.size() > 0;

        }else
            return true;
    }

    private void runService(int totalClassRoom) {

        RestClient restClient = new RestClient();
        ApiService apiService = restClient.getApiService();

        MultipartBody.Part[] fileDataGood = new MultipartBody.Part[arrayListImages2.size()];
        for (int i = 0; i < arrayListImages2.size(); i++) {
            Log.d("TAG", "requestUploadSurvey: survey image " + i + "  " + arrayListImages2.get(i).getPath());
            File compressFile = filrCompressor(arrayListImages2.get(i));
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"),
                    compressFile);
            fileDataGood[i] = MultipartBody.Part.createFormData("FileDataGood", compressFile.getName(), surveyBody);

        }


        MultipartBody.Part[] fileDataMinor = new MultipartBody.Part[arrayListImages3.size()];
        for (int i = 0; i < arrayListImages3.size(); i++) {
            Log.d("TAG", "requestUploadSurvey: survey image " + i + "  " + arrayListImages3.get(i).getPath());
            File compressFile = filrCompressor(arrayListImages3.get(i));
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"),
                    compressFile);
            fileDataMinor[i] = MultipartBody.Part.createFormData("FileDataMinor", compressFile.getName(), surveyBody);

        }

        MultipartBody.Part[] fileDataMajor = new MultipartBody.Part[arrayListImages4.size()];
        for (int i = 0; i < arrayListImages4.size(); i++) {
            Log.d("TAG", "requestUploadSurvey: survey image " + i + "  " + arrayListImages4.get(i).getPath());
            File compressFile = filrCompressor(arrayListImages4.get(i));
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"),
                    compressFile);
            fileDataMajor[i] = MultipartBody.Part.createFormData("FileDataMajor", compressFile.getName(), surveyBody);

        }
        RequestBody PhotoDeleteGood, PhotoDeleteMajor, PhotoDeleteMinor;
        if (action.equals("3")) {
            Log.d("TAG", "onClick: "+paraDeletUlrs());
            Log.d("TAG", "onClick: "+paraDeletUlrs1());
            Log.d("TAG", "onClick: "+paraDeletUlrs2());
            PhotoDeleteGood = RequestBody.create(MediaType.parse("multipart/form-data"), paraDeletUlrs());
            PhotoDeleteMinor = RequestBody.create(MediaType.parse("multipart/form-data"), paraDeletUlrs1());
            PhotoDeleteMajor = RequestBody.create(MediaType.parse("multipart/form-data"), paraDeletUlrs2());
        } else {
            PhotoDeleteGood = null;
            PhotoDeleteMinor = null;
            PhotoDeleteMajor = null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), paraClassRoomDetails(action, "1", "ClassRoomDetails", totalClassRoom, goodCondtionClassroom.getText().toString(), majorRepairingClassroom.getText().toString(), minorRepairingClassroom.getText().toString(), edtPodiumClass.getText().toString(),
                blackBoardCount.getText().toString(), whiteBoardCont.getText().toString(), greenBoardCount.getText().toString(), applicationController.getLatitude(), applicationController.getLongitude(), applicationController.getSchoolId(), applicationController.getPeriodID(), applicationController.getUsertypeid(), applicationController.getUserid(), "GoodConditionPhotos", arrayListImages2, "MajorRepairingPhotos", arrayListImages3, "MinorRepairingPhotos", arrayListImages4));
        Log.d("TAG", "onClick: " + paraClassRoomDetails(action, "1", "ClassRoomDetails", totalClassRoom, goodCondtionClassroom.getText().toString(), majorRepairingClassroom.getText().toString(), minorRepairingClassroom.getText().toString(), edtPodiumClass.getText().toString(),
                blackBoardCount.getText().toString(), whiteBoardCont.getText().toString(), greenBoardCount.getText().toString(), applicationController.getLatitude(), applicationController.getLongitude(), applicationController.getSchoolId(), applicationController.getPeriodID(), applicationController.getUsertypeid(), applicationController.getUserid(), "GoodConditionPhotos", arrayListImages2, "MajorRepairingPhotos", arrayListImages3, "MinorRepairingPhotos", arrayListImages4));
        Call<List<JsonObject>> call = apiService.uploadClassRoomDetails(fileDataGood, fileDataMinor, fileDataMajor, description,PhotoDeleteGood,PhotoDeleteMajor,PhotoDeleteMinor);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: " + response.body());
                TextView textView = dialog.findViewById(R.id.dialogtextResponse);
                Button button = dialog.findViewById(R.id.BtnResponseDialoge);
                try {
                    if (response.body().get(0).get("Status").getAsString().equals("E")) {
                        textView.setText("You already uploaded details ");

                    } else if (response.body().get(0).get("Status").getAsString().equals("S")) {
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
                } catch (Exception e) {
                    dialog2.dismiss();

                    Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
                dialog2.dismiss();

            }
        });
    }

    private String paraDeletUlrs() {
        JsonArray jsonArray=new JsonArray();

        Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable.deletedUrls.size());

        for (int i = 0; i < OnlineImageRecViewAdapterEditable.deletedUrls.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable.deletedUrls.get(i));
            String newUrl2=OnlineImageRecViewAdapterEditable.deletedUrls.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2);
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }
    private String paraDeletUlrs1() {
        JsonArray jsonArray=new JsonArray();

        Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable1.deletedUrls.size());

        for (int i = 0; i < OnlineImageRecViewAdapterEditable1.deletedUrls.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable1.deletedUrls.get(i));
            String newUrl2=OnlineImageRecViewAdapterEditable1.deletedUrls.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2);
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }
    private String paraDeletUlrs2() {
        JsonArray jsonArray=new JsonArray();

        Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable2.deletedUrls.size());

        for (int i = 0; i < OnlineImageRecViewAdapterEditable2.deletedUrls.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            Log.d("TAG", "paraDeletUlrs: "+OnlineImageRecViewAdapterEditable2.deletedUrls.get(i));
            String newUrl2=OnlineImageRecViewAdapterEditable2.deletedUrls.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2);
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }
    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkDetailsOfRooms(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"1"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
               blackBoardCount.setText(response.body().get(0).get("BlackBoard").getAsString());
               whiteBoardCont.setText(response.body().get(0).get("WhiteBoard").getAsString());
               greenBoardCount.setText(response.body().get(0).get("GreenBoard").getAsString());
               edtPodiumClass.setText(response.body().get(0).get("ClassRoomsWithPodium").getAsString());
                majorRepairingClassroom.setText(response.body().get(0).get("MajorRepairing").getAsString());
               minorRepairingClassroom.setText(response.body().get(0).get("MinorRepairing").getAsString());
               goodCondtionClassroom.setText(response.body().get(0).get("GoodCondition").getAsString());
               totalClassRooms.setText(response.body().get(0).get("TotalRooms").getAsString());




                recyclerViewTwoTypeOneGoodConditionFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailTypeOne.this,LinearLayoutManager.HORIZONTAL,false));

                goodConditionPathList=response.body().get(0).get("GoodConditionPhotos").toString().split(",");
                aList = new ArrayList<String>(Arrays.asList(goodConditionPathList));

                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailTypeOne.this,aList);
                recyclerViewTwoTypeOneGoodConditionFromServer.setAdapter(onlineImageRecViewAdapter);



                recyclerViewMinorConditionFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailTypeOne.this,LinearLayoutManager.HORIZONTAL,false));
                minorPhotoPath=response.body().get(0).get("MinorRepairingPhotos").toString().split(",");
               bList = new ArrayList<String>(Arrays.asList(minorPhotoPath));
                OnlineImageRecViewAdapterEditable1 onlineImageRecViewAdapter2=new OnlineImageRecViewAdapterEditable1(UpdateDetailTypeOne.this,bList);
                recyclerViewMinorConditionFromServer.setAdapter(onlineImageRecViewAdapter2);


                recyclerViewMjorRepairingFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailTypeOne.this,LinearLayoutManager.HORIZONTAL,false));
                majorPhotoPath=response.body().get(0).get("MajorRepairingPhotos").toString().split(",");
               cList = new ArrayList<String>(Arrays.asList(majorPhotoPath));
                OnlineImageRecViewAdapterEditable2 onlineImageRecViewAdapter3=new OnlineImageRecViewAdapterEditable2(UpdateDetailTypeOne.this,cList);
                recyclerViewMjorRepairingFromServer.setAdapter(onlineImageRecViewAdapter3);


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
    private File filrCompressor(File file) {
        File compressedImage = new Compressor.Builder(UpdateDetailTypeOne.this)
                .setMaxWidth(720)
                .setMaxHeight(720)
                .setQuality(75)
                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build()
                .compressToFile(new File(file.getPath()));
        return  compressedImage;
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
    private String paraClassRoomDetails(String action, String paramId, String paramName, int totalClassRoom, String goodConRoom, String majorRooms, String minorRoom, String podium, String blackBoard,
                                            String whiteBoard, String greenBoard, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String goodConditionPhotos, ArrayList<File> arrayListImages2, String majorRepairingPhotos, ArrayList<File> arrayListImages3, String minorRepairingPhotos, ArrayList<File> arrayListImages4) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("ParamName",paramName);
        jsonObject.addProperty("TotalRooms",totalClassRoom);
        jsonObject.addProperty("Condition",goodConRoom);
        jsonObject.addProperty("MajorRepairing",majorRooms);
        jsonObject.addProperty("MinorRepairing",minorRoom);
        jsonObject.addProperty("ClassRoomsWithPodium",podium);
        jsonObject.addProperty("BlackBoard",blackBoard);
        jsonObject.addProperty("WhiteBoard",whiteBoard);
        jsonObject.addProperty("GreenBoard",greenBoard);
        jsonObject.addProperty("Lat",latitude);
        jsonObject.addProperty("Long",longitude);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("CreatedBy",usertypeid);
        jsonObject.addProperty("UserCode",userid);
//        JsonArray jsonArray = new JsonArray();
//        for (int i = 0; i < arrayListImages2.size(); i++) {
//            jsonArray.add(paraGetImageBase64( arrayListImages2.get(i), i));
//
//        }
//        jsonObject.add("GoodConditionPhotos", (JsonElement) jsonArray);
//
//        JsonArray jsonArray2 = new JsonArray();
//        for (int i = 0; i < arrayListImages3.size(); i++) {
//            jsonArray2.add(paraGetImageBase64( arrayListImages3.get(i), i));
//
//        }
//
//        jsonObject.add("MajorRepairingPhotos", (JsonElement) jsonArray2);
//
//        JsonArray jsonArray3 = new JsonArray();
//        for (int i = 0; i < arrayListImages4.size(); i++) {
//            jsonArray3.add(paraGetImageBase64( arrayListImages4.get(i), i));
//
//        }
//        jsonObject.add("MinorRepairingPhotos", (JsonElement) jsonArray3);

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
        // return "/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEhUQEhIVFRUVFRUVFRUVFRUQEBUVFRUWFhUVFRUYHSggGBolHRUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGBAQGy0fHSAtLS0tLS0tLS0tLSstLS0tLSstLS0tLS0tLS0tLS0tLSstLS0tLS0tLS0tLS0tKystLf/AABEIAP8AxgMBIgACEQEDEQH/xAAbAAABBQEBAAAAAAAAAAAAAAAAAQIDBAUGB//EADwQAAEDAgMFBgUCBAUFAAAAAAEAAhEDBBIhMQVBUWFxBiKBkaGxEzLR4fBCwQcUUvEjJGJyshVDgpKi/8QAGQEBAAMBAQAAAAAAAAAAAAAAAAECBAMF/8QAIREBAQACAwEAAgMBAAAAAAAAAAECEQMSITEEQSIyURP/2gAMAwEAAhEDEQA/APRUIQoSEIQgEiEIBCEIBIhCASpAUFwGqJ0EJMYSoBCEKAJClSFAiEIQCEqRSgIQiVCdBCEKUJEJEqBEISoEQhBRITXvAzJgeSr39/TotL6jg0D15DiV5r2r7UvuDgoS1kka/MefADgqZ5zFbHG5Os272zo25DR/iOO5pBHmuZv/AOIdd/dpUQyf1OOKBx3LlHWBGZO7vO06nr6BZm0rsRgbk2YJHrnvKz/9csq69JG4/tFcSSKr5OZcHFonkFQrbZrOPeqPc55gAvLsM9dNJyVN1Uub3e6BkeJDT6BQ7PjGeLdTuk6qm7+19NkbYrCAK1QCSCC4xkIE74MZFWrftNdNcMNV5MfqdiYRoufp1pdUaZzkt04SI8R6pNmOHeac5GIdZGXlCndNO62Z/EC4a+axY9hHyxgI5h2/oV3+x+0NvctBp1BiI+QmHjlG9eMvt6b2NcTBkQ7dnmJ4g6JtajUogGmcJDhGWbZzGY0zylXx5bPqmXHHvqRcJ2d7axhpXWRMAVNAMs8f1XdU3hwDgZB0I0K0Y5TKeOVxs+lQhClUiEIhAIQhAIQhEHoQiVIEJUIEVTal82iwvcY4cSeAVl7gBJ3LzvtBtF9xWwtEU2+bju8N658mfWOmGPZm7cval07UgT3R7ZePsoKGzw3CP6RDRruzJ/N/NaNK1h3TN3ll5D1dyU7LUuBMQAP7BY7ltpkc1tZ4+X7rnbq1LoJEcBw4eK7G6sp089Tnu5LNvdmuyOg/PqqTLS/VzF3VjuaxAMaZplOiZPOJ9fotX/pgbOWpnn+ZJKlM4ZAzy9yJV+8+RXrWdWtzORzk/tl6FStZEHQkTlpibvVx1KHNJG/3hQV2OL8pgnw0JHv6J22izR1V2Oi6mIzcCBzGeHzlbGyK/wAWjgdm9rcidXNH6TzjPwWE+kQcQ3YTrzg/t5rRszERxOYyMHPx+6W+DYbbZgGHBzQORLdPMEhaux9r1bQdwl9IZGmc8I/08PusbZVyXUnA6tfGeRzP9lat7jXFo6AepMSkysvhZt6lsvaNO4pipTORGm8ciri827O3/wDKVSCJpvOgywk7x9F6NRqBwDhoRK2YZ9ozZY6p6EiFdQJClSIBCEIJEIQpCpEIKDG7QV3YcDSROp4BcuyiGgkDMkAcZOnvK6badEudr+dFVdRaCI0bpzcd/wB1j5N3Jq4/IqMsdG79XE7t+fPkkrHEC0ZMblP9R4hWboFrT+SSizoEgAacePNcb/jvjFGls/ERlA3D9yo9o7Pk4QOvn+eS6W3tYCcy0kklOvi7z+/2Z3tNB7qrb7LxRA/JXdXGz8TnHoPqktdmgRlqZ8FTVTqPPdo7KcN2ipO2fv3Eff2nzXpm1NnAjTisKjYbo5j2+nkp+K3GVxla1J3aiD1EKNtGI/8AGf8A6C7S42aCCQIggnwB+yrHY8nwn3TavVz1lIcQcsUz4fgU1J8vjktB9gQ6QNPwot9nHFij+ybR1W3WZeJGRIkczrHmuy7J3mOlhdOJuRB91zjBhwkfZaWynH44cMpkO5rvxZarjyY+OsSFAQtjKEJEIFQkQglQhCAQkSSgytqVQ2TvWE26Jdr+fkqftJcw8t5D2WVZmXD8zWLkv8m3ix8dBVGKRzELQtKQAACpURvWhbqn7d5NRap05UrGRMJlJWGrrFapm2Mfm9SMo5zGghW4CAE6xFrNvbfLmsB9ocxGhyXX1AqdagCqZ4bWxyc0yhkQd5z8EGln4H1K2KtnkeJUIteI3eqppbxlm0E6aj2SMpNGXX6q9ctzBVOqo+FxN+C0tI4e357JtgCx7TwOfHqq9WsQZUtG4Bhw4hXxrhyY6deCgptM5BOW9iCRCEQEIQgklCalQJCAhJKDgO1Fb/MOE8J8phRbNdJVbtU6Ll3h7K32cpyS5efyf2r0eL5HU0BktCgIVOkMlDc7apslo7zhkQNB1Kn59dPvxtNOana5cmdvOGgJ5AE+yWn2grEmKfnIPsrTOI6V17XKQLnbTbLjk9mFadteYlaZyq5YVdqKBwT3vVSrcAKajGVIQonqjc7bpsyMk8s1n1u0rBq1wHGJ9AVzuUX1WhWYsy4Ymt7QUXjJwPTVMq3THjuuB5b/ACXO6WjOutFn7Pu4dHOPNaFzouWFaKjhwM+SjD6pyx67Z/I3oFNKp7LM0mHi0K0vSnx5t+lQklEqUAlCRCCWUSkRKBUiFFcVgxpceXTNRbqbqZLbqPNe2x/zRjl7LU7K5hL2v2cXRVEHiQP3TOx5+bwXn2y5bejjhcZJXSXIOHCMp37/AAUdjs1jdfVXCxZe1bl7WwwEu4ZgeLtwVrqerTd8aNxfW9Ed8tEeapM7R2jtKjd+7hquR7R7CxWprVHmo8uAdBIZTYTmGNHh3jJXO7Ks6bquFttgPxKYpy5r2uENDmwBJl2IyToQIWnHitm2bPnmN1qvWqNek8Swg9FZtmiZGqy7zZbKLsVLMZYmtOJzSf6eXJXrNhY8jXRccsbK0Y5S47jYjJYO06sHVbzn91cjtiS8xoAT5KM9mF9PbbMObzA6wpRa2x/U0+P3WTc2DnWtW4ky1ktE9/Pe4/pAmYGeWq87vsIe5jK9wXANmcYEGniqOxTBGPuwBmM5XTDgtm3Lk/ImN09TuNj2zhLWt6t181iXllgIcwmRv1/us4UrigynUY9zqbmtJbA+IzKYyyLfCeq2adx8RrXZZ8NFw5MdXTthl2m4Wo6RK49wxV4G9wHmYXYXDYaei5LZ1Bz7hoAkl4OWv5kq4z1TO+PYbRmFjW7w0A+AUiSkchPBOXpPPIkSoRACRLKRA+UkoCVAKrtKjjpObxHtmrSQhVyx7Y2f6vhl1ymX+OVoUDhNMzhI04EcOCpbBpfDq1GcDl0W/gh5B3Ss60pHHUqEzLoHlovNwx149jlvb+ToKGYTnWoO5MsTkr7AtEZWZW2a05x9+qjo7MY0yGNk74ElbkBAYryl2oBuEZABQNEGVoXDFnuzdCrnVsZ6tu+RYVVvenmug+H3Y5LCrDC+CqZVaROKLXDNozyO6RzWfX7N27v+2Bv5T0W7askKY0VeZKXFzlXZs6y4cCcvBM/kQ3RdA9oWfctXPL1ZgbVMMd0Ko7E2eym016hjgTw+qubW+R2u4cTqE/8Ak8RAOeQwjdmdwXHO2Txfjx3fXT7Frl9IOzgk4SdS0HI+6uyo7emGNawaNAHknr08JZjJXl52XK2FSJJQrKFQkSoHpEAoQCJSJQgoXrYdP9TSPH8hZdpU7rmEEHESJ3rdu6WJvTNUDTy4789Vj5ceue3pcGfbj6/4msTktOmVjW7oyWnRqKJVtL9NqeYVZtRMq11bZcTb6tDeuSyq19RpODXVGB50aXAOPQErSfTDhDt6o1tkUSZcxrzMgkDED1VcpU46/a6doNjcufrXtOpVcwPbjb+kEFw6jcrlTZZn5zhG4a+ar09l0we60NAJPdEEk6klUy7VadY1tlVcTc1oOasu2AbpktBtaQrz4536hrtWXcuWhcVFi31XNUtX0p1GgkzzP7BaGzqWKqDHytn6fnJZ1WgXAnEQANBvWzsOnDXOjUgCdYaPuVPHj2zinJevFb+60kiUpsre8wqESkQCEFKgehIhAFEoSBAoKrV7ac2mORzH2VhCrljMpqr4Z5YXeLNdSLXQYk55KwxyW+ZkHD9PsdVHTcsuePW6b+HPvjtOKiibUkzuUzGyq13buPymPWFV2vq2LgcUGqFytzQumkxWbG6aefoVAwXhy+Kz/wBD9VbtV8fxt/t17q4gqq6uOS5pwvCINWmOYaSfJRstbk5fFnowAeqrurX8az9uiqXjRvUtK5B8VhWmxqszUrOJ4DC0egXRWluAM1HrjZMUFd6zK+q1LzVUqdDE8M4mT0Gv5zVdbujLKSbNok5BgJP7/st2hSwtDeA9d6kRK2cfFMHn8vN/01NaIkQULq4BCEkoFQklCB6EShAIBSJUCpEIlALPqMwGNx0+i06bZPIZnwXH/wDWjVruboASAOYXLlk00cFsvjpaNRThY9KtCvU7gFZpW36kq01C6gDujorjBKdgU6WmdjObZdU804y0WkYhVqjEsLnb9U0prwElw6FmVrmFzVWKlVGxb+m6q+jPfyz/AGWa+qSuP2NeudtbA06vDfAAT+66cV/k5cs3i9cSKW7EO8lCtrzylIhAQCEiJQKhCEDiUBJKJQKhCRAqEBKgsWrZxf7V5Ia/wrh8nR7v+S9l2bS4714b28ijeVWCSS8kDTXPM+K58s8jR+PfbHf0nSJSioW9FndnKxdQpzrhAPhktOo1Y21oWl4DvVw1wubc0jMZFRvvKjeB9FPfSdbdL8dQ1a4XMna9XTD6obXqv17o5aqLyROmhf3YGQzPD6qiykT3nfYKajbR+Zqw1ipbahUqjJcj/Dy0+Jtx7tzG1H/8Wj/kuyvRDT0VH+D1hNze3X+oUwened7tXX8b+7jz/wBHoj6QcSFQqUy0wVsUWor2wdqvQsedtiJSr1TZ53H6KrVt3DUHwzCqlClSJUSRCEIgqUFJKJRJ0pCgK5bbPe/OIHEohUCu2tmSRiEDWN60rWxYzmeOqmjv+CtMUbV2iHBeV/xd2FF0y5aMqjQ13+5mnmD6L1iu1Utv7Mbd27qRjFEtPBw0P7eKpzYdsLI6cOfXKWvNOzhgYeQXSClK5rZ4LKmBwgjIg6gjULp6Lsl5+L0sogqUY1+yiNrK0y0EKDAQpsRKoG05JRRK0D0Kjwk7lXqttDTpJxYrDKaKgU6V2wtu1sFNzjuBXS/w62YbfZ7MQh9Uuqu4zUMgHo2B4LDp7N/m7htA/I3v1OBaDk3xPpK9BqZQwaNWj8XD7kzflZ+TEtFqdhStGSBwWxiIeSc3mntam1BKlKKrSY7VoPoVUfs+mdC5vqFfwpj+CjQyKuznj5YcOWviChaXwpzSKujbCV+12aSMTzhG4fqPhuU2z7QN77hLtwOjeZHFadOlOZUyG0NCixvytHU95ymgnVONJKArIOYFG7JzTxken2U1PRMqjTqEDajZUdAwVMmVGIOX7X9npP8ANUh3h84H6h/UOYWXZOkL0KnmFz219iQTVpDXNzRv5j6LLy8PvaNnDz+dcmUxOc1DM1YFNZ9NNVoRhVl1FIKSjSVcNVe7fGQEk5ADUk6BXa2QWtsfZYb/AIj/AJtw/pH1V8OO5XTnnyTCbpOz+zBb0y53zvzcee4DkFcp5meKK9TEYGgUlMQJW7HGSajz8srld0VDuUlMb1DTEn3VkKypQE2E8pqBrlC4ZdSpqmiac3AcAiTsMZITiEqIQsbmrDQo6YUwQDhkmKUKMDMhEikUlwMkjNVKRKCrTqKaVVLcJjduUjXIJ2KvdXsd1gxO6wB1Ud1VdGFuR4qnaNz+vEbkTIivbJzx8QMh28D9X3Vek0jIgjqIXRM0SZHJccuKW7jthzWTVYuFNeEl07A4t4H+yRpxENGpWfXumrfm1S1firNbEiZ6Rv6K5Xljw6YGpPH7K/TtG0h3fmdqfzcql7YGpAxaeS08eFxjLyZzLLz4vUm71JXfA/NVW2fScBg3DQq02nLp3BdHD9n0GYRz3qUBNTwpQCkSpCiTBmU1mbin0022HzHiSiEpQmnPohEikZUhUTu66eP4VO4SEAFGfm8E9hUdT5ggV2qkCY8JzUDK9OQq85cwriaWIKzaSa+hBy3+4VhvBOdmOYzQRUzl9uCA3fGaf8Oc2mD6KN1aDBGaaGdtLZ+N4eXBrY73EkcPBSUGtaIp589B9yrZpYnQVK9oblCrMJLta52zSph4pzAnxKmYyFZXZWtgJE4piIK1PSBCJCQpU0oBuibT+VOfomUj6Ig9KmjNCD//2Q==";
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
//            if (cameraType==1){
//                arrayListImages1.add(bitmap);
//            }else if(cameraType==2){
//                arrayListImages2.add(bitmap);
//            }else if(cameraType==3){
//                arrayListImages3.add(bitmap);
//            }else if (cameraType==4){
//                arrayListImages4.add(bitmap);
//            }

        }
    }
}
