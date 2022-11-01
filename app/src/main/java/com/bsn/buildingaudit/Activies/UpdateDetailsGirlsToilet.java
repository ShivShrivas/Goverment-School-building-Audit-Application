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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class UpdateDetailsGirlsToilet extends AppCompatActivity {
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
    String action;
    String currentImagePath=null;
    File imageFile=null;
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    ImageAdapter5 adapter6;
    Dialog dialog2;
    LinearLayout linearLayoutCWSNfriendlyToilet;
    ImageView girlsToiletImageUploadBtn;
    Button submitBtnGirlsToilet;
    ArrayAdapter<String> arrayAdapter;
    RecyclerView recyclerViewGirlsToilet,recyclerViewGirlsToiletFromServer;
    Dialog dialog;
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
Spinner spinnerGirlsSanetoryNapkin,spinnerGirlsIncinerator,spinnerGirlsDustbin,spinnerGirlsDoorFacility,spinnerCWSNGirlstoiletAvalabilty;
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    EditText edtUrinalWithFlushTotalB,edtUrinalWithoutFlushB,edtUrinalWithFlushB,edtCSWNfriendlyTotalB,edtCSWNwithoutfriendlyB,edtCSWNfriendlyB,
            edtwithflushTotal,edtWithoutFlushClean,edtWithFlushClean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_girls_toilet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent i=getIntent();
        action=i.getStringExtra("Action");
        dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog_onsave);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
         dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        edtUrinalWithFlushTotalB=findViewById(R.id.GedtUrinalWithFlushTotalB);
        edtUrinalWithoutFlushB=findViewById(R.id.GedtUrinalWithoutFlushB);
        edtUrinalWithFlushB=findViewById(R.id.GedtUrinalWithFlushB);
        edtCSWNfriendlyTotalB=findViewById(R.id.GedtCSWNfriendlyTotalB);
        edtCSWNwithoutfriendlyB=findViewById(R.id.GedtCSWNwithoutfriendlyB);
        edtCSWNfriendlyB=findViewById(R.id.GedtCSWNfriendlyB);
        edtwithflushTotal=findViewById(R.id.GedtwithflushTotal);
        edtWithoutFlushClean=findViewById(R.id.GedtWithoutFlushClean);
        edtWithFlushClean=findViewById(R.id.GedtWithFlushClean);
        spinnerGirlsIncinerator=findViewById(R.id.spinnergirlsIncinator);
        spinnerGirlsDustbin=findViewById(R.id.spinnerGirlsDustbin);
        spinnerGirlsSanetoryNapkin=findViewById(R.id.spinnerGirlsSanetoryNapkin);
        spinnerGirlsDoorFacility=findViewById(R.id.spinnerGirlsDoors);
        spinnerCWSNGirlstoiletAvalabilty=findViewById(R.id.spinnerCWSNGirlsAvailability);
        girlsToiletImageUploadBtn=findViewById(R.id.GirlsToiletImageUploadBtn);
        recyclerViewGirlsToilet=findViewById(R.id.recyclerViewGirlsToilet);
        submitBtnGirlsToilet=findViewById(R.id.submitBtnGirlsToilet);
        linearLayoutCWSNfriendlyToilet=findViewById(R.id.linearLayoutCWSNfriendlyToilet);
        recyclerViewGirlsToiletFromServer=findViewById(R.id.recyclerViewGirlsToiletFromServer);
        edtwithflushTotal.setEnabled(false);
        edtCSWNfriendlyTotalB.setEnabled(false);
        edtUrinalWithFlushTotalB.setEnabled(false);
        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
         arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);


        spinnerGirlsIncinerator.setAdapter(arrayAdapter);
       spinnerGirlsDustbin.setAdapter(arrayAdapter);
        spinnerGirlsDoorFacility.setAdapter(arrayAdapter);
        spinnerCWSNGirlstoiletAvalabilty.setAdapter(arrayAdapter);
        spinnerGirlsSanetoryNapkin.setAdapter(arrayAdapter);

        girlsToiletImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(UpdateDetailsGirlsToilet.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsGirlsToilet.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsGirlsToilet.this);

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
        recyclerViewGirlsToilet.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter5(this, arrayListImages1);
        recyclerViewGirlsToilet.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();
        edtCSWNfriendlyB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int with =0;
                int without =0;
                int total =0;
                try {
                    with = Integer.parseInt(edtCSWNfriendlyB.getText().toString());
                }catch (Exception e){

                }
                try {
                    without = Integer.parseInt(edtCSWNwithoutfriendlyB.getText().toString());
                }catch (Exception e){

                }
                total = with + without;
                edtCSWNfriendlyTotalB.setText(String.valueOf(total));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtCSWNwithoutfriendlyB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int with =0;
                int without =0;
                int total =0;
                try {
                    with = Integer.parseInt(edtCSWNfriendlyB.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    without = Integer.parseInt(edtCSWNwithoutfriendlyB.getText().toString().trim());
                }catch (Exception e){

                }
                total = with + without;
                edtCSWNfriendlyTotalB.setText(String.valueOf(total));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        edtUrinalWithFlushB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int with =0;
                int without =0;
                int total =0;
                try {
                    with = Integer.parseInt(edtUrinalWithFlushB.getText().toString());
                }catch (Exception e){

                }
                try {
                    without = Integer.parseInt(edtUrinalWithoutFlushB.getText().toString());
                }catch (Exception e){

                }
                total = with + without;
                edtUrinalWithFlushTotalB.setText(String.valueOf(total));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtUrinalWithoutFlushB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int with =0;
                int without =0;
                int total =0;
                try {
                    with = Integer.parseInt(edtUrinalWithFlushB.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    without = Integer.parseInt(edtUrinalWithoutFlushB.getText().toString().trim());
                }catch (Exception e){

                }
                total = with + without;
                edtUrinalWithFlushTotalB.setText(String.valueOf(total));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtWithFlushClean.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int with =0;
                int without =0;
                int total =0;
                try {
                    with = Integer.parseInt(edtWithFlushClean.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    without = Integer.parseInt(edtWithoutFlushClean.getText().toString().trim());
                }catch (Exception e){

                }
                total = with + without;
                edtwithflushTotal.setText(String.valueOf(total));
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtWithoutFlushClean.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int with =0;
                int without =0;
                int total =0;
                try {
                    with = Integer.parseInt(edtWithFlushClean.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    without = Integer.parseInt(edtWithoutFlushClean.getText().toString().trim());
                }catch (Exception e){

                }
                total = with + without;
                edtwithflushTotal.setText(String.valueOf(total));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinnerCWSNGirlstoiletAvalabilty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getSelectedItem().toString().equals("No")){
                    linearLayoutCWSNfriendlyToilet.setVisibility(View.GONE);

                }else if(adapterView.getSelectedItem().toString().equals("Yes")){

                    linearLayoutCWSNfriendlyToilet.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submitBtnGirlsToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog2.show();

                int withflushTotal=0;
                int UrinalWithFlushTotalB=0;
                try{
                    withflushTotal=Integer.parseInt(edtwithflushTotal.getText().toString().trim());
                    UrinalWithFlushTotalB=Integer.parseInt(edtUrinalWithFlushTotalB.getText().toString().trim());
                }catch (Exception e){

                }
                if ( UrinalWithFlushTotalB==0 &&  withflushTotal==0 && spinnerGirlsSanetoryNapkin.getSelectedItem().toString().equals("No") && spinnerCWSNGirlstoiletAvalabilty.getSelectedItem().toString().equals("No") && spinnerGirlsDoorFacility.getSelectedItem().toString().equals("No") && spinnerGirlsDustbin.getSelectedItem().toString().equals("No") && spinnerGirlsIncinerator.getSelectedItem().toString().equals("No") ){
                    runService();
                }else{
                    if (action.equals("3")){
                        if (arrayListImages1.size()==0 && aList.size()==0){
                            Toast.makeText(UpdateDetailsGirlsToilet.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();

                        }else {
                            runService();

                        }
                    }else{
                        if (arrayListImages1.size()==0){
                            Toast.makeText(UpdateDetailsGirlsToilet.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();

                        }else {
                            runService();

                        }
                    }


                }

            }
        });
    }


    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkGirlsToilet(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"17"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
               
               
                int spinnerPositionForAvailabilityCWSN = arrayAdapter.getPosition(response.body().get(0).get("AvailabilityCWSN").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("AvailabilityCWSN").getAsString());
                int spinnerPositionForDoor= arrayAdapter.getPosition(response.body().get(0).get("Door").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("Door").getAsString());
                int spinnerPositionForDustbin= arrayAdapter.getPosition(response.body().get(0).get("Dustbin").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("Dustbin").getAsString());
                int spinnerPositionForFreeSanitaryNapkins= arrayAdapter.getPosition(response.body().get(0).get("FreeSanitaryNapkins").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("FreeSanitaryNapkins").getAsString());
                int spinnerPositionForIncinerator= arrayAdapter.getPosition(response.body().get(0).get("Incinerator").getAsString())==-1?0:arrayAdapter.getPosition(response.body().get(0).get("Incinerator").getAsString());

                spinnerCWSNGirlstoiletAvalabilty.setSelection(spinnerPositionForAvailabilityCWSN);
                spinnerGirlsDoorFacility.setSelection(spinnerPositionForDoor);
                spinnerGirlsDustbin.setSelection(spinnerPositionForDustbin);
                spinnerGirlsIncinerator.setSelection(spinnerPositionForIncinerator);
                spinnerGirlsSanetoryNapkin.setSelection(spinnerPositionForFreeSanitaryNapkins);
                edtUrinalWithFlushTotalB.setText(response.body().get(0).get("TotalUrinals").getAsString());
                edtUrinalWithoutFlushB.setText(response.body().get(0).get("NoOfUrinalsWithOutFlush").getAsString());
                edtUrinalWithFlushB.setText(response.body().get(0).get("NoOfUrinalsWithFlush").getAsString());

                edtwithflushTotal.setText(response.body().get(0).get("TotalExcludingCWSN").getAsString());
                edtWithoutFlushClean.setText(response.body().get(0).get("NoOfSeatsExcludingCWSNWithOutFlush").getAsString());
                edtWithFlushClean.setText(response.body().get(0).get("NoOfSeatsExcludingCWSNWithFlush").getAsString());


                recyclerViewGirlsToiletFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsGirlsToilet.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsGirlsToilet.this,aList);
                recyclerViewGirlsToiletFromServer.setAdapter(onlineImageRecViewAdapter);
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
           
            File compressedImage = new Compressor.Builder(UpdateDetailsGirlsToilet.this)
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
            deletUrl = RequestBody.create(MediaType.parse("multipart/form-data"),paraDeletUlrs());
        }else {
            deletUrl=null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraGirlsToilet(action,"17","GirlsToiletPhoto",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),edtWithFlushClean.getText().toString(),edtWithoutFlushClean.getText().toString(),edtwithflushTotal.getText().toString(),spinnerCWSNGirlstoiletAvalabilty.getSelectedItem().toString(),edtCSWNfriendlyB.getText().toString(),edtCSWNwithoutfriendlyB.getText().toString(),edtCSWNfriendlyTotalB.getText().toString(),edtUrinalWithFlushB.getText().toString(),edtUrinalWithoutFlushB.getText().toString(),edtUrinalWithFlushTotalB.getText().toString(),spinnerGirlsDoorFacility.getSelectedItem().toString(),spinnerGirlsDustbin.getSelectedItem().toString(),spinnerGirlsIncinerator.getSelectedItem().toString(),spinnerGirlsSanetoryNapkin.getSelectedItem().toString(),arrayListImages1));

       
        Call<List<JsonObject>> call=apiService.uploadGirlsToilet(surveyImagesParts,description,deletUrl);
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

    private File getImageFile() throws IOException{
        String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageName="jpg+"+timeStamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageName,".jpg",storageDir);

        currentImagePath=imageFile.getAbsolutePath();
       
        return imageFile;
    }
    private String  paraGirlsToilet(String s, String s1, String boundaryWallPhoto, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String noOfSeatsExcludingCWSNWithFlush, String noOfSeatsExcludingCWSNWithOutFlush,
                                    String totalExcludingCWSN, String availabilityCWSN, String noOfSeatsCWSNWithFlush, String noOfSeatsCWSNWithOutFlush, String totalCWSN, String noOfUrinalsWithFlush, String noOfUrinalsWithOutFlush, String totalUrinals, String door,
                                    String dustbin,String incinator,String napkin, ArrayList<File> arrayListImages1) {
        JsonObject jsonObject=new JsonObject();

        jsonObject.addProperty("Action",s);
        jsonObject.addProperty("ParamId",s1);
        jsonObject.addProperty("ParamName",boundaryWallPhoto);
        jsonObject.addProperty("Lat",latitude);
        jsonObject.addProperty("Long",longitude);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("CreatedBy",usertypeid);
        jsonObject.addProperty("UserCode",userid);
        jsonObject.addProperty("NoOfSeatsExcludingCWSNWithFlush",noOfSeatsExcludingCWSNWithFlush);
        jsonObject.addProperty("NoOfSeatsExcludingCWSNWithOutFlush",noOfSeatsExcludingCWSNWithOutFlush);
        jsonObject.addProperty("TotalExcludingCWSN",totalExcludingCWSN);
        if (availabilityCWSN.equals("No")){
            jsonObject.addProperty("AvailabilityCWSN",availabilityCWSN);
            jsonObject.addProperty("NoOfSeatsCWSNWithFlush","0");
            jsonObject.addProperty("NoOfSeatsCWSNWithOutFlush","0");
            jsonObject.addProperty("TotalCWSN","0");
        }else if(availabilityCWSN.equals("Yes")){
            jsonObject.addProperty("AvailabilityCWSN",availabilityCWSN);
            jsonObject.addProperty("NoOfSeatsCWSNWithFlush",noOfSeatsCWSNWithFlush);
            jsonObject.addProperty("NoOfSeatsCWSNWithOutFlush",noOfSeatsCWSNWithOutFlush);
            jsonObject.addProperty("TotalCWSN",totalCWSN);
        }
        jsonObject.addProperty("NoOfUrinalsWithFlush",noOfUrinalsWithFlush);
        jsonObject.addProperty("NoOfUrinalsWithOutFlush",noOfUrinalsWithOutFlush);
        jsonObject.addProperty("TotalUrinals",totalUrinals);
        jsonObject.addProperty("Door",door);
        jsonObject.addProperty("Dustbin",dustbin);
        jsonObject.addProperty("Incinerator",incinator);
        jsonObject.addProperty("FreeSanitaryNapkins",napkin);
//
//        JsonArray jsonArray2 = new JsonArray();
//        for (int i = 0; i < arrayListImages1.size(); i++) {
//            jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//        }
//        jsonObject.add("GirlsToiletPhoto", (JsonElement) jsonArray2);
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