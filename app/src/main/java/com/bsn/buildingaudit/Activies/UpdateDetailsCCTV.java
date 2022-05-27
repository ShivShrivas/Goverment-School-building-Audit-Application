package com.bsn.buildingaudit.Activies;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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

public class UpdateDetailsCCTV extends AppCompatActivity {
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

    public ArrayList<File> arrayListImages2 = new ArrayList<>();
    ImageAdapter5 adapter2;
    EditText EdtNoOfCCTV,EdtNoNonOfCCTV,edtTotalCCTV;

    Dialog dialog,dialog2;
    String action;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> arrayAdapter1;
    ArrayAdapter<String> arrayAdapter2;
    String currentImagePath=null;
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    File imageFile=null;
    Spinner spinnerCCTVWorkingStatus,spinnerCCTVInstallationYear,spinnerCCTVAvailabelty;
    ImageView CCTVImageUploadBtn;
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    RecyclerView recyclerViewCCTV,recyclerViewCCTVFromServer;
    ConstraintLayout constraintLayout33;
    Button submitBtnCCTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_cctv);
        applicationController= (ApplicationController) getApplication();
        Intent i1=getIntent();
        action=i1.getStringExtra("Action");
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
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
        spinnerCCTVWorkingStatus=findViewById(R.id.spinnerCCTVWorkingStatus);
        EdtNoOfCCTV=findViewById(R.id.EdtNoOfCCTV);
        EdtNoNonOfCCTV=findViewById(R.id.EdtNoNonOfCCTV);
        edtTotalCCTV=findViewById(R.id.edtTotalCCTV);
        spinnerCCTVInstallationYear=findViewById(R.id.spinnerCCTVInstallationYear);
        spinnerCCTVAvailabelty=findViewById(R.id.spinnerCCTVAvailabelty);
        CCTVImageUploadBtn=findViewById(R.id.CCTVImageUploadBtn);
        recyclerViewCCTV=findViewById(R.id.recyclerViewCCTV);
        submitBtnCCTV=findViewById(R.id.submitBtnCCTV);
        constraintLayout33=findViewById(R.id.constraintLayout33);
        recyclerViewCCTVFromServer=findViewById(R.id.recyclerViewCCTVFromServer);
        if (action.equals("3")){
            fetchAllDataFromServer();
        }


        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

         arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerCCTVWorkingStatus.setAdapter(arrayAdapter2);

        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);


        spinnerCCTVAvailabelty.setAdapter(adapter);



        ArrayList<String> arrayListInstallationYear=new ArrayList<>();
        for (int i = 1990; i <=2022; i++) {
            arrayListInstallationYear.add(String.valueOf(i));
        }
        arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerCCTVInstallationYear.setAdapter(arrayAdapter1);


        CCTVImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(UpdateDetailsCCTV.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsCCTV.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsCCTV.this);

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

        recyclerViewCCTV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2= new ImageAdapter5(this, arrayListImages2);
        recyclerViewCCTV.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        spinnerCCTVAvailabelty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerCCTVAvailabelty.getSelectedItem().toString().equals("No")){
                    constraintLayout33.setVisibility(View.GONE);
                }else {
                    constraintLayout33.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submitBtnCCTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if ( !checkValidation(Integer.parseInt(edtTotalCCTV.getText().toString().trim()),Integer.parseInt(EdtNoOfCCTV.getText().toString().trim())
                        ,Integer.parseInt(EdtNoNonOfCCTV.getText().toString().trim()))){
                    dialog2.dismiss();
                    Toast.makeText(UpdateDetailsCCTV.this, "CCTV count is incorrect!!", Toast.LENGTH_SHORT).show();
                }else if (action.equals("3")){
                    if (!spinnerCCTVAvailabelty.getSelectedItem().toString().equals("No")){
                        if (arrayListImages2.size()==0 && aList.size()==0){
                            Toast.makeText(UpdateDetailsCCTV.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();

                        }else {
                            runService();


                        }
                    }else {
                        runService();
                    }
                }else{
                    if (!spinnerCCTVAvailabelty.getSelectedItem().toString().equals("No")){
                        if (arrayListImages2.size()==0){
                            Toast.makeText(UpdateDetailsCCTV.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();

                        }else {
                            runService();


                        }
                    }else {
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
        Call<List<JsonObject>> call=apiService.checkCCTVDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"10"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerPositionForAvailabilty = adapter.getPosition(response.body().get(0).get("Availabilty").getAsString())==-1?0:adapter.getPosition(response.body().get(0).get("Availabilty").getAsString());
                int spinnforInstallationYear= arrayAdapter1.getPosition(response.body().get(0).get("InstallationYear").getAsString())==-1?0:arrayAdapter1.getPosition(response.body().get(0).get("InstallationYear").getAsString());
                int spinnerPositionForWorkingStatus = arrayAdapter2.getPosition(response.body().get(0).get("WorkingStatus").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("WorkingStatus").getAsString());
                spinnerCCTVAvailabelty.setSelection(spinnerPositionForAvailabilty);
                spinnerCCTVWorkingStatus.setSelection(spinnerPositionForWorkingStatus);
                spinnerCCTVInstallationYear.setSelection(spinnforInstallationYear);
                EdtNoOfCCTV.setText(response.body().get(0).get("WorkingCount").getAsString());
                EdtNoNonOfCCTV.setText(response.body().get(0).get("NonWorkingCount").getAsString());
                edtTotalCCTV.setText(response.body().get(0).get("NoOfCCTV").getAsString());

                recyclerViewCCTVFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsCCTV.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").getAsString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                if (!aList.get(0).isEmpty()){
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsCCTV.this,aList);
                recyclerViewCCTVFromServer.setAdapter(onlineImageRecViewAdapter);
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
        RestClient restClient =new RestClient();
        ApiService apiService=restClient.getApiService();

        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListImages2.size()];
        for (int i = 0; i < arrayListImages2.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListImages2.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdateDetailsCCTV.this)
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
            if (spinnerCCTVAvailabelty.getSelectedItem().toString().equals("No")){
                deletUrl=RequestBody.create(MediaType.parse("multipart/form-data"),paraAllDeleteUrls());
            }else{
                deletUrl = RequestBody.create(MediaType.parse("multipart/form-data"),paraDeletUlrs());

            }
        }else {
            deletUrl=null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraCCTV(action,"10","CCTV",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerCCTVInstallationYear.getSelectedItem().toString(),EdtNoOfCCTV.getText().toString(),spinnerCCTVWorkingStatus.getSelectedItem().toString(),spinnerCCTVAvailabelty.getSelectedItem().toString(),arrayListImages2));
        Call<List<JsonObject>> call=apiService.uploadCCTVDetails(surveyImagesParts,description,deletUrl);
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
                    Toast.makeText(UpdateDetailsCCTV.this, "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

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
    private String paraCCTV(String s, String s1, String cctv, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String installationYear, String nOOFcctv, String workingStatus, String availabilty, ArrayList<File> arrayListImages2) {
        JsonObject jsonObject=new JsonObject();
if (availabilty.equals("No")){
    jsonObject.addProperty("Action",s);
    jsonObject.addProperty("ParamId",s1);
    jsonObject.addProperty("ParamName",cctv);
    jsonObject.addProperty("SchoolId",schoolId);
    jsonObject.addProperty("PeriodID",periodID);
    jsonObject.addProperty("InstallationYear","0");
    jsonObject.addProperty("NoOfCCTV","0");
    jsonObject.addProperty("NonWorkingCount","0");
    jsonObject.addProperty("WorkingCount","0");
    jsonObject.addProperty("WorkingStatus","");
    jsonObject.addProperty("Availabilty",availabilty);
    jsonObject.addProperty("Lat",latitude);
    jsonObject.addProperty("Long",longitude);
    jsonObject.addProperty("CreatedBy",usertypeid);
    jsonObject.addProperty("UserCode",userid);
//
//    JsonArray jsonArray2 = new JsonArray();
//    for (int i = 0; i < arrayListImages2.size(); i++) {
//        jsonArray2.add(paraGetImageBase64( arrayListImages2.get(i), i));
//
//    }
//    jsonObject.add("CctvPhoto", (JsonElement) jsonArray2);
}else{
    jsonObject.addProperty("Action",s);
    jsonObject.addProperty("ParamId",s1);
    jsonObject.addProperty("ParamName",cctv);
    jsonObject.addProperty("SchoolId",schoolId);
    jsonObject.addProperty("PeriodID",periodID);
    jsonObject.addProperty("InstallationYear",installationYear);
    jsonObject.addProperty("NoOfCCTV",edtTotalCCTV.getText().toString().trim());
    jsonObject.addProperty("NonWorkingCount",EdtNoNonOfCCTV.getText().toString().trim());
    jsonObject.addProperty("WorkingCount",EdtNoOfCCTV.getText().toString().trim());
    jsonObject.addProperty("WorkingStatus",workingStatus);
    jsonObject.addProperty("Availabilty",availabilty);
    jsonObject.addProperty("Lat",latitude);
    jsonObject.addProperty("Long",longitude);
    jsonObject.addProperty("CreatedBy",usertypeid);
    jsonObject.addProperty("UserCode",userid);

//    JsonArray jsonArray2 = new JsonArray();
//    for (int i = 0; i < arrayListImages2.size(); i++) {
//        jsonArray2.add(paraGetImageBase64( arrayListImages2.get(i), i));
//
//    }
//    jsonObject.add("CctvPhoto", (JsonElement) jsonArray2);
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
       Bitmap bitmapNew=BITMAP_RESIZER(bitmap,330,480);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmapNew.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap BITMAP_RESIZER(Bitmap bitmap,int newWidth,int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;

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
//                Uri uri=data.getData();
//                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//
//            arrayListImages2.add(bitmap);



        }
    }
}