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
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    ImageAdapter5 adapter;
    Dialog dialog;
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter,arrayAdapter1,arrayAdapter2,arrayAdapter5,arrayAdapter4,arrayAdapter6,arrayAdapter9;
    String action;
Spinner spinnerPrinterAvailable,spinnerScannerAvailable,spinnerComputeLabAvailabelty,spinnerInstallationYear,spinnerGrantUnderScheme,spinnerinternet,spinnerPowerBackup,spinnerFurniture,spinnerComputerOperator;
    ImageView ComputerLabImageUploadBtn;
    RecyclerView recyclerViewComputerLab,recyclerViewComputerLabFromServer;
    Dialog dialog2;
    EditText edtComputerLabOtherScheme;
    TextView userName,schoolAddress,schoolName;
    Button submitComputerLabBtn;
    ConstraintLayout constraintLayout36;
    String currentImagePath=null;

    File imageFile=null;

    EditText edtNoOfLab,edtNoOfWorkingComputer,edtNoOfComputer;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationController= (ApplicationController) getApplication();
        setContentView(R.layout.activity_update_details_computerlab);
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
        edtComputerLabOtherScheme=findViewById(R.id.edtComputerLabOtherScheme);
        recyclerViewComputerLabFromServer=findViewById(R.id.recyclerViewComputerLabFromServer);
        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        ArrayList<String> arrayListFurnitures3=new ArrayList<>();
        arrayListFurnitures3.add("Yes");
        arrayListFurnitures3.add("No");
        arrayAdapter9=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListFurnitures3);
        arrayAdapter9.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFurniture.setAdapter(arrayAdapter9);
        spinnerComputeLabAvailabelty.setAdapter(arrayAdapter9);


        ArrayList<String> arrayListInstallationYear=new ArrayList<>();
        for (int i = 1990; i <=2022; i++) {
            arrayListInstallationYear.add(String.valueOf(i));
        }
         arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerInstallationYear.setAdapter(arrayAdapter1);

        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

       arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerGrantUnderScheme.setAdapter(arrayAdapter2);


        ArrayList<String> arrayListPowerbackup =new ArrayList<>();
        arrayListPowerbackup.add("Generator");
        arrayListPowerbackup.add("Invertor");
        arrayListPowerbackup.add("UPS");
        arrayListPowerbackup.add("None");
         arrayAdapter4=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListPowerbackup);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerPowerBackup.setAdapter(arrayAdapter4);

        ArrayList<String> arrayListFurnitures=new ArrayList<>();
        arrayListFurnitures.add("Yes");
        arrayListFurnitures.add("No");
         arrayAdapter5=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListFurnitures);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFurniture.setAdapter(arrayAdapter5);
        spinnerComputerOperator.setAdapter(arrayAdapter5);
        spinnerPrinterAvailable.setAdapter(arrayAdapter5);
        spinnerScannerAvailable.setAdapter(arrayAdapter5);
        spinnerinternet.setAdapter(arrayAdapter5);


        ArrayList<String> arrayListFurnitures2=new ArrayList<>();
        arrayListFurnitures2.add("Yes");
        arrayListFurnitures2.add("No");
        arrayListFurnitures2.add("Alternate Room");
        arrayAdapter6=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListFurnitures2);
        arrayAdapter6.setDropDownViewResource(R.layout.custom_text_spiiner);

        ComputerLabImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(UpdateDetailsComputerlab.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsComputerlab.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsComputerlab.this);

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
        recyclerViewComputerLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter5(this, arrayListImages1);
        recyclerViewComputerLab.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinnerGrantUnderScheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerGrantUnderScheme.getSelectedItem().toString().equals("Others")){
                    edtComputerLabOtherScheme.setVisibility(View.VISIBLE);
                }else{
                    edtComputerLabOtherScheme.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                if (Integer.valueOf(edtNoOfComputer.getText().toString())<Integer.valueOf(edtNoOfWorkingComputer.getText().toString())){
                    edtNoOfComputer.setError("Total computer count is always greater or equal to working computer");
                    dialog2.dismiss();
                }else{

                if (action.equals("3")){
                    if (!spinnerComputeLabAvailabelty.getSelectedItem().toString().equals("No")){
                        if (arrayListImages1.size()==0 && aList.size()==0){
                            dialog2.dismiss();
                            Toast.makeText(UpdateDetailsComputerlab.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                        }else {
                            runService();


                        }
                    }else{
                        runService();
                    }
                }else{
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

            }
            }
        });

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
    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkComputerLab(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"19"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerPositionForAvailabilty = arrayAdapter9.getPosition(response.body().get(0).get("Availabilty").getAsString())==-1?0:arrayAdapter9.getPosition(response.body().get(0).get("Availabilty").getAsString());
                int spinnerPositionForInstallationYear = arrayAdapter1.getPosition(response.body().get(0).get("InstallationYear").getAsString())==-1?0:arrayAdapter1.getPosition(response.body().get(0).get("InstallationYear").getAsString());
                int spinnerPositionForScheme = arrayAdapter2.getPosition(response.body().get(0).get("Scheme").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("Scheme").getAsString());
                int spinnerPositionForInternet = arrayAdapter4.getPosition(response.body().get(0).get("Internet").getAsString())==-1?0:arrayAdapter4.getPosition(response.body().get(0).get("Internet").getAsString());
                int spinnerPositionForPowerBackUp= arrayAdapter2.getPosition(response.body().get(0).get("PowerBackUp").getAsString())==-1?0:arrayAdapter2.getPosition(response.body().get(0).get("PowerBackUp").getAsString());
                int spinnerPositionForFurnitures = arrayAdapter5.getPosition(response.body().get(0).get("Furnitures").getAsString())==-1?0:arrayAdapter5.getPosition(response.body().get(0).get("Furnitures").getAsString());
                int spinnerPositionForComputerOperator = arrayAdapter5.getPosition(response.body().get(0).get("ComputerOperator").getAsString())==-1?0:arrayAdapter5.getPosition(response.body().get(0).get("ComputerOperator").getAsString());
                int spinnerPositionForPrinterStatus = arrayAdapter5.getPosition(response.body().get(0).get("PrinterStatus").getAsString())==-1?0:arrayAdapter5.getPosition(response.body().get(0).get("PrinterStatus").getAsString());
                int spinnerPositionForScannerAvl = arrayAdapter5.getPosition(response.body().get(0).get("ScannerAvl").getAsString())==-1?0:arrayAdapter5.getPosition(response.body().get(0).get("ScannerAvl").getAsString());

               spinnerGrantUnderScheme.setSelection(spinnerPositionForScheme);
               spinnerInstallationYear.setSelection(spinnerPositionForInstallationYear);
               spinnerComputeLabAvailabelty.setSelection(spinnerPositionForAvailabilty);
               spinnerinternet.setSelection(spinnerPositionForInternet);
               spinnerPowerBackup.setSelection(spinnerPositionForPowerBackUp);
               spinnerFurniture.setSelection(spinnerPositionForFurnitures);
               spinnerComputerOperator.setSelection(spinnerPositionForComputerOperator);
               spinnerPrinterAvailable.setSelection(spinnerPositionForPrinterStatus);
               spinnerScannerAvailable.setSelection(spinnerPositionForScannerAvl);
               edtNoOfComputer.setText(response.body().get(0).get("NoOfComputers").getAsString());
               edtNoOfWorkingComputer.setText(response.body().get(0).get("NoOfWorkingComputers").getAsString());
               edtNoOfLab.setText(response.body().get(0).get("NoOfComputerLab").getAsString());



                recyclerViewComputerLabFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsComputerlab.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").getAsString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                if (!aList.get(0).isEmpty()){
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsComputerlab.this,aList);
                recyclerViewComputerLabFromServer.setAdapter(onlineImageRecViewAdapter);
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
        String sheme;
        if (spinnerGrantUnderScheme.getSelectedItem().toString().equals("Others")){
            sheme=edtComputerLabOtherScheme.getText().toString();
        }else {
            sheme=spinnerGrantUnderScheme.getSelectedItem().toString();
        }
        String OtherScheme;
        if(spinnerGrantUnderScheme.getSelectedItem().toString().equals("Others")){
            OtherScheme="Y";
        }else{
            OtherScheme="N";
        }
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListImages1.size()];
        for (int i = 0; i < arrayListImages1.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListImages1.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdateDetailsComputerlab.this)
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
            if (spinnerComputeLabAvailabelty.getSelectedItem().toString().equals("No")){
                deletUrl=RequestBody.create(MediaType.parse("multipart/form-data"),paraAllDeleteUrls());
            }else{
                deletUrl = RequestBody.create(MediaType.parse("multipart/form-data"),paraDeletUlrs());

            }
        }else {
            deletUrl=null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraComputerLab(action,"19","ComputerLab",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerInstallationYear.getSelectedItem().toString(),edtNoOfComputer.getText().toString(),edtNoOfWorkingComputer.getText().toString(),sheme,OtherScheme,spinnerinternet.getSelectedItem().toString(),spinnerPowerBackup.getSelectedItem().toString(),spinnerFurniture.getSelectedItem().toString(),spinnerComputerOperator.getSelectedItem().toString(),edtNoOfLab.getText().toString(),spinnerPrinterAvailable.getSelectedItem().toString(),spinnerScannerAvailable.getSelectedItem().toString(),spinnerComputeLabAvailabelty.getSelectedItem().toString(),arrayListImages1));
                Log.d("TAG", "onClick: "+paraComputerLab(action,"19","ComputerLab",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerInstallationYear.getSelectedItem().toString(),edtNoOfComputer.getText().toString(),edtNoOfWorkingComputer.getText().toString(),sheme,OtherScheme,spinnerinternet.getSelectedItem().toString(),spinnerPowerBackup.getSelectedItem().toString(),spinnerFurniture.getSelectedItem().toString(),spinnerComputerOperator.getSelectedItem().toString(),edtNoOfLab.getText().toString(),spinnerPrinterAvailable.getSelectedItem().toString(),spinnerScannerAvailable.getSelectedItem().toString(),spinnerComputeLabAvailabelty.getSelectedItem().toString(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadComputerLab(surveyImagesParts,description,deletUrl);
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

    private String paraComputerLab(String action,String paramId,String paramName,String lat,String longt,String schoolId,String periodId,String Usertypeid,String getUserid,String InstallationYear,String NoOfComputer, String NoOfWorkingComputer,String GrantUnderScheme,String OtherString,String internet,String PowerBackup, String Furniture,String computerOperator ,String NoOfLab,String PrinterAvailable, String ScannerAvailable,String availabale ,ArrayList<File> arrayListImages1 ){

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
            jsonObject.addProperty("OtherSchemeYN","");
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


//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("ComputerLabPhoto", (JsonElement) jsonArray2);
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
            jsonObject.addProperty("OtherSchemeYN",OtherString);
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

//
//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("ComputerLabPhoto", (JsonElement) jsonArray2);
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