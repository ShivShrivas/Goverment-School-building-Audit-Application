package com.example.buildingaudit.Activies;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class UpdateDetailsBoundryWall extends AppCompatActivity {
Spinner spinnerWallCondition,spinnerWhiteWash,spinnerTypeBoundaryWall,spinnerBoundaryWallAvail,spinnerBoundryScheme;
ImageView boundaryWallImageUploadBtn;
EditText edtAreaofSchool,edtLengthofWall;
    ArrayAdapter<String> arrayAdapter2,arrayAdapter,adapter,arrayAdapter4;
Button bntBoundaryWallUpload;
Dialog dialog;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter2.notifyDataSetChanged();
    }

    String currentImagePath=null;
    File imageFile=null;
    String action;
    String[] StaffPhotoPathList;
    public ArrayList<File> arrayListImages2 = new ArrayList<>();
    int btnType;
    ImageAdapter5 adapter1;
    Dialog dialog2;
    ImageAdapter5 adapter2;
    ConstraintLayout constraintLayout34;
        RecyclerView recyclerViewTwoTypeBoundarywall,recyclerViewTwoTypeBoundarywallFromServer;
EditText edtBoundryWallOtherScheme;
ArrayList<String> aList=new ArrayList<>();
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_boundry_wall);
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
        toolbar.setTitle("Boundary Walls");
        spinnerWallCondition=findViewById(R.id.spinnerWallCondition);
        spinnerWhiteWash=findViewById(R.id.spinnerWhiteWash);
        spinnerTypeBoundaryWall=findViewById(R.id.spinnerTypeBoundaryWall);
        spinnerBoundaryWallAvail=findViewById(R.id.spinnerBoundaryWallAvail);
        boundaryWallImageUploadBtn=findViewById(R.id.boundaryWallImageUploadBtn);
        recyclerViewTwoTypeBoundarywall=findViewById(R.id.recyclerViewTwoTypeBoundarywall);
        edtLengthofWall=findViewById(R.id.edtLengthofWall);
        edtAreaofSchool=findViewById(R.id.edtAreaofSchool);
        bntBoundaryWallUpload=findViewById(R.id.bntBoundaryWallUpload);
        spinnerBoundryScheme=findViewById(R.id.spinnerBoundryScheme);
        constraintLayout34=findViewById(R.id.constraintLayout34);
        edtBoundryWallOtherScheme=findViewById(R.id.edtBoundryWallOtherScheme);
        recyclerViewTwoTypeBoundarywallFromServer=findViewById(R.id.recyclerViewTwoTypeBoundarywallFromServer);
        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
         adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerBoundaryWallAvail.setAdapter(adapter);
        spinnerWhiteWash.setAdapter(adapter);

        ArrayList<String> arrayListSpinner = new ArrayList<>();

        arrayListSpinner.add("Good Condition");
        arrayListSpinner.add("Minor repairing");
        arrayListSpinner.add("major repairing");

         arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerWallCondition.setAdapter(arrayAdapter);
        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

        arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerBoundryScheme.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListWallType = new ArrayList<>();
        for (int i=0;i<applicationController.getBoundryTypes().size();i++){
            arrayListWallType.add(applicationController.getBoundryTypes().get(i).getBWTypeName());
        }




       arrayAdapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListWallType);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerTypeBoundaryWall.setAdapter(arrayAdapter4);


        boundaryWallImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(UpdateDetailsBoundryWall.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsBoundryWall.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsBoundryWall.this);

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

        recyclerViewTwoTypeBoundarywall.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
       adapter2= new ImageAdapter5(this, arrayListImages2);
        recyclerViewTwoTypeBoundarywall.setAdapter(adapter2);

        spinnerBoundryScheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerBoundryScheme.getSelectedItem().toString().equals("Others")){
                    edtBoundryWallOtherScheme.setVisibility(View.VISIBLE);
                }else{
                    edtBoundryWallOtherScheme.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerBoundaryWallAvail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerBoundaryWallAvail.getSelectedItem().toString().equals("No")){
                    constraintLayout34.setVisibility(View.GONE);
                }else {
                    constraintLayout34.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bntBoundaryWallUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if (!spinnerBoundaryWallAvail.getSelectedItem().toString().equals("No")){
                    if (edtLengthofWall.length() == 0 || edtAreaofSchool.length()==0) {
                        Toast.makeText(UpdateDetailsBoundryWall.this, "Please fill all details properly!!", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    } else {
                        if (action.equals("3")){
                            if (arrayListImages2.size()==0 && aList.size()==0){
                                Toast.makeText(UpdateDetailsBoundryWall.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                                dialog2.dismiss();
                            }else {
                                runService();



                            }
                        }else{
                            if (arrayListImages2.size()==0){
                                Toast.makeText(UpdateDetailsBoundryWall.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                                dialog2.dismiss();
                            }else {
                                runService();



                            }
                        }

                    }
                }else {
                  runService();
                }

            }
        });
    }


    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkBoundryWall(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"15"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerPositionForAvailabilty = adapter.getPosition(response.body().get(0).get("Availabilty").getAsString())==-1?0:adapter.getPosition(response.body().get(0).get("Availabilty").getAsString());
                int spinnforBoundaryWallType= arrayAdapter4.getPosition(response.body().get(0).get("BoundaryWallType").getAsString())==-1?0:arrayAdapter4.getPosition(response.body().get(0).get("BoundaryWallType").getAsString());
                int spinnerPositionForWhiteWashStatus = adapter.getPosition(response.body().get(0).get("WhiteWashStatus").getAsString())==-1?0:adapter.getPosition(response.body().get(0).get("WhiteWashStatus").getAsString());
                int spinnerPositionForCondition = arrayAdapter.getPosition(response.body().get(0).get("Condition").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("Condition").getAsString());
                int spinnerPositionForScheme = arrayAdapter2.getPosition(response.body().get(0).get("Scheme").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("Scheme").getAsString());

                spinnerBoundaryWallAvail.setSelection(spinnerPositionForAvailabilty);
                spinnerBoundryScheme.setSelection(spinnerPositionForScheme);
                spinnerTypeBoundaryWall.setSelection(spinnforBoundaryWallType);
                spinnerWallCondition.setSelection(spinnerPositionForCondition);
                spinnerWhiteWash.setSelection(spinnerPositionForWhiteWashStatus);


                edtAreaofSchool.setText(response.body().get(0).get("SchoolArea").getAsString());
                edtLengthofWall.setText(response.body().get(0).get("BoundaryWallLength").getAsString());

                recyclerViewTwoTypeBoundarywallFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsBoundryWall.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsBoundryWall.this,aList);
                recyclerViewTwoTypeBoundarywallFromServer.setAdapter(onlineImageRecViewAdapter);
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
    private File getImageFile() throws IOException {
        String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageName="jpg+"+timeStamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageName,".jpg",storageDir);

        currentImagePath=imageFile.getAbsolutePath();
        Log.d("TAG", "getImageFile: "+currentImagePath);
        return imageFile;
    }
    private void runService() {
        String sheme;
        if (spinnerBoundryScheme.getSelectedItem().toString().equals("Others")){
            sheme=edtBoundryWallOtherScheme.getText().toString();
        }else {
            sheme=spinnerBoundryScheme.getSelectedItem().toString();
        }
        String OtherScheme;
      if(spinnerBoundryScheme.getSelectedItem().toString().equals("Others")){
          OtherScheme="Y";
        }else{
          OtherScheme="N";
      }
        RestClient restClient = new RestClient();
        ApiService apiService = restClient.getApiService();
        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListImages2.size()];
        for (int i = 0; i < arrayListImages2.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListImages2.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdateDetailsBoundryWall.this)
                    .setMaxWidth(720)
                    .setMaxHeight(720)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(new File(arrayListImages2.get(i).getPath()));
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
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraBoundry(action, "15", "BoundaryWallPhoto", applicationController.getLatitude(), applicationController.getLongitude(), applicationController.getSchoolId(), applicationController.getPeriodID(), applicationController.getUsertypeid(), applicationController.getUserid(), spinnerBoundaryWallAvail.getSelectedItem().toString(), edtAreaofSchool.getText().toString(), edtLengthofWall.getText().toString(), spinnerTypeBoundaryWall.getSelectedItem().toString(), spinnerWhiteWash.getSelectedItem().toString(), spinnerWallCondition.getSelectedItem().toString(),sheme, OtherScheme,arrayListImages2));
        Log.d("TAG", "onClick: " + paraBoundry(action, "15", "BoundaryWallPhoto", applicationController.getLatitude(), applicationController.getLongitude(), applicationController.getSchoolId(), applicationController.getPeriodID(), applicationController.getUsertypeid(), applicationController.getUserid(), spinnerBoundaryWallAvail.getSelectedItem().toString(), edtAreaofSchool.getText().toString(), edtLengthofWall.getText().toString(), spinnerTypeBoundaryWall.getSelectedItem().toString(), spinnerWhiteWash.getSelectedItem().toString(), spinnerWallCondition.getSelectedItem().toString(), sheme,OtherScheme, arrayListImages2));
        Call<List<JsonObject>> call = apiService.uploadBoundryWall(surveyImagesParts,description,deletUrl);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: " + response.body());

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
    private String paraBoundry(String s, String s1, String boundaryWallPhoto, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6,String toString7, ArrayList<File> arrayListImages2) {
        JsonObject jsonObject=new JsonObject();
        if (toString.equals("No")){
            jsonObject.addProperty("Action",s);
            jsonObject.addProperty("ParamId",s1);
            jsonObject.addProperty("ParamName",boundaryWallPhoto);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);
            jsonObject.addProperty("Availabilty",toString);
            jsonObject.addProperty("SchoolArea","0");
            jsonObject.addProperty("BoundaryWallLength","0");
            jsonObject.addProperty("BoundaryWallType","");
            jsonObject.addProperty("WhiteWashStatus","");
            jsonObject.addProperty("Condition","");
            jsonObject.addProperty("Scheme","");
            jsonObject.addProperty("OtherSchemeYN","");
//
//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages2.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages2.get(i), i));
//
//            }
//            jsonObject.add("BoundaryWallPhoto", (JsonElement) jsonArray2);
        }else{
            jsonObject.addProperty("Action",s);
            jsonObject.addProperty("ParamId",s1);
            jsonObject.addProperty("ParamName",boundaryWallPhoto);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);
            jsonObject.addProperty("Availabilty",toString);
            jsonObject.addProperty("SchoolArea",toString1);
            jsonObject.addProperty("BoundaryWallLength",toString2);
            jsonObject.addProperty("BoundaryWallType",toString3);
            jsonObject.addProperty("WhiteWashStatus",toString4);
            jsonObject.addProperty("Condition",toString5);
            jsonObject.addProperty("Scheme",toString6);
            jsonObject.addProperty("OtherSchemeYN",toString7);
//
//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages2.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages2.get(i), i));
//
//            }
//            jsonObject.add("BoundaryWallPhoto", (JsonElement) jsonArray2);
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
//
//            arrayListImages2.add(bitmap);



        }
    }
}