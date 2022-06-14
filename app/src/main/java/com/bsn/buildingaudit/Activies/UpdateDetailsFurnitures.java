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
import com.bsn.buildingaudit.Model.FurnitureDetails;
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

public class UpdateDetailsFurnitures extends AppCompatActivity {
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
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    ImageAdapter5 adapter6;
    ImageView furniutresImageUploadBtn;
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    ArrayList<FurnitureDetails> arrayList=new ArrayList<>();
    EditText edtFurnitureRequired;
    Spinner spinnerTripleSeatesStatus,spinnersingleSeatesStatus,spinnerDoubleSeatesStatus;
    RecyclerView recyclerViewFurnitures,recyclerViewFurnituresFromServer;
    Button submitBtnFurniture;
    ArrayAdapter<String> arrayAdapter2;
    EditText edtTrippelSeated,edtDoubleSeated,edtSingleSeated;
    Dialog dialog2;
    TextView edtTotalFurnirtureStrenght;
    TextView userName,schoolAddress,schoolName;
    EditText edtMajorConditionForSingle,edtMajorConditionForDouble,edtMajorConditionForTripple,edtMinorConditionForSingle,edtMinorConditionForDouble,edtMinorConditionForTripple,edtgoodConditionForSingle,
            edtgoodConditionForDouble,edtgoodConditionForTripple;
    LinearLayout constraintLayout9;
    Dialog dialog;
    ApplicationController applicationController;
    String currentImagePath=null;
    File imageFile=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_furnitures);
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
        Intent i=getIntent();
        action=i.getStringExtra("Action");
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
        furniutresImageUploadBtn=findViewById(R.id.furniutresImageUploadBtn);
        recyclerViewFurnitures=findViewById(R.id.recyclerViewFurnitures);
        edtTotalFurnirtureStrenght=findViewById(R.id.edtTotalFurnirtureStrenght);
        edtTrippelSeated=findViewById(R.id.edtTrippelSeated);
        edtDoubleSeated=findViewById(R.id.edtDoubleSeated);
        edtSingleSeated=findViewById(R.id.edtSingleSeated);
        spinnerDoubleSeatesStatus=findViewById(R.id.spinnerDoubleSeatesStatus);
        spinnersingleSeatesStatus=findViewById(R.id.spinnersingleSeatesStatus);
        spinnerTripleSeatesStatus=findViewById(R.id.spinnerTripleSeatesStatus);
        edtFurnitureRequired=findViewById(R.id.edtFurnitureRequired);
        submitBtnFurniture=findViewById(R.id.submitBtnFurniture);
        constraintLayout9=findViewById(R.id.constraintLayout9);
        recyclerViewFurnituresFromServer=findViewById(R.id.recyclerViewFurnituresFromServer);
        edtMajorConditionForSingle=findViewById(R.id.edtMajorConditionForSingle);
        edtMajorConditionForDouble=findViewById(R.id.edtMajorConditionForDouble);
        edtMajorConditionForTripple=findViewById(R.id.edtMajorConditionForTripple);
        edtMinorConditionForDouble=findViewById(R.id.edtMinorConditionForDouble);
        edtMinorConditionForSingle=findViewById(R.id.edtMinorConditionForSingle);
        edtMinorConditionForTripple=findViewById(R.id.edtMinorConditionForTripple);
        edtgoodConditionForSingle=findViewById(R.id.edtgoodConditionForSingle);
        edtgoodConditionForTripple=findViewById(R.id.edtgoodConditionForTripple);
        edtgoodConditionForDouble=findViewById(R.id.edtgoodConditionForDouble);

        if (action.equals("3")){
            fetchAllDataFromServer();
        }
        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Good Condition");
        arrayListSpinner2.add("Minor Repairing");
        arrayListSpinner2.add("Major repairing");

        arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner2);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);

        spinnerDoubleSeatesStatus.setAdapter(arrayAdapter2);
                spinnersingleSeatesStatus.setAdapter(arrayAdapter2);
        spinnerTripleSeatesStatus.setAdapter(arrayAdapter2);
        edtSingleSeated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int singleSeated=0;
                int tripleSeated=0;
                int doubleSeated=0;

                try {
                     singleSeated=Integer.parseInt(edtSingleSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    tripleSeated=Integer.parseInt(edtTrippelSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    doubleSeated=Integer.parseInt(edtDoubleSeated.getText().toString().trim());

                }catch (Exception e){

                }

                edtTotalFurnirtureStrenght.setText(String.valueOf(singleSeated+(tripleSeated*3)+(doubleSeated*2)));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtDoubleSeated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int singleSeated=0;
                int tripleSeated=0;
                int doubleSeated=0;

                try {
                     singleSeated=Integer.parseInt(edtSingleSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    tripleSeated=Integer.parseInt(edtTrippelSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    doubleSeated=Integer.parseInt(edtDoubleSeated.getText().toString().trim());

                }catch (Exception e){

                }

                edtTotalFurnirtureStrenght.setText(String.valueOf(singleSeated+(tripleSeated*3)+(doubleSeated*2)));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        edtTrippelSeated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int singleSeated=0;
                int tripleSeated=0;
                int doubleSeated=0;

                try {
                     singleSeated=Integer.parseInt(edtSingleSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    tripleSeated=Integer.parseInt(edtTrippelSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    doubleSeated=Integer.parseInt(edtDoubleSeated.getText().toString().trim());

                }catch (Exception e){

                }

                edtTotalFurnirtureStrenght.setText(String.valueOf(singleSeated+(tripleSeated*3)+(doubleSeated*2)));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        furniutresImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(UpdateDetailsFurnitures.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsFurnitures.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsFurnitures.this);

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
        recyclerViewFurnitures.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter5(this, arrayListImages1);
        recyclerViewFurnitures.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();

        FurnitureDetails furnitureDetailssingle=new FurnitureDetails();
        furnitureDetailssingle.setFurnitureType("Single");
        furnitureDetailssingle.setCondition(spinnersingleSeatesStatus.getSelectedItem().toString());
        furnitureDetailssingle.setTotalCnt(edtSingleSeated.getText().toString());
        furnitureDetailssingle.setSrno("1");

        FurnitureDetails furnitureDetailsDouble=new FurnitureDetails();
        furnitureDetailsDouble.setFurnitureType("Double");
        furnitureDetailsDouble.setCondition(spinnerDoubleSeatesStatus.getSelectedItem().toString());
        furnitureDetailsDouble.setTotalCnt(edtDoubleSeated.getText().toString());
        furnitureDetailsDouble.setSrno("2");
        FurnitureDetails furnitureDetailsTriple=new FurnitureDetails();
        furnitureDetailssingle.setFurnitureType("Tripple");
        furnitureDetailssingle.setCondition(spinnerTripleSeatesStatus.getSelectedItem().toString());
        furnitureDetailssingle.setTotalCnt(edtTrippelSeated.getText().toString());
        furnitureDetailssingle.setSrno("3");
        submitBtnFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
dialog2.show();


                arrayList.add(furnitureDetailssingle);
                arrayList.add(furnitureDetailsDouble);
                arrayList.add(furnitureDetailsTriple);

                Log.d("TAG", "onClick: "+   arrayList.get(1).getFurnitureType());
                if ( Integer.parseInt(edtTrippelSeated.getText().toString().trim())==0 && Integer.parseInt(edtTotalFurnirtureStrenght.getText().toString().trim())==0 && Integer.parseInt(edtFurnitureRequired.getText().toString().trim())==0 && Integer.parseInt(edtSingleSeated.getText().toString().trim())==0 && Integer.parseInt(edtDoubleSeated.getText().toString().trim())==0){
                    runService();
                }else{ if (!checkValidationOnCondition(Integer.valueOf(edtSingleSeated.getText().toString().trim()),Integer.valueOf(edtgoodConditionForSingle.getText().toString().trim()),Integer.valueOf(edtMinorConditionForSingle.getText().toString().trim()),Integer.valueOf(edtMajorConditionForSingle.getText().toString().trim())))
                {
                    dialog2.dismiss();
                    Toast.makeText(UpdateDetailsFurnitures.this, "Single seats count is incorrect", Toast.LENGTH_SHORT).show();
                }else if(!checkValidationOnCondition(Integer.valueOf(edtDoubleSeated.getText().toString().trim()),Integer.valueOf(edtgoodConditionForDouble.getText().toString().trim()),Integer.valueOf(edtMinorConditionForDouble.getText().toString().trim()),Integer.valueOf(edtMajorConditionForDouble.getText().toString().trim())))
                {
                    dialog2.dismiss();

                    Toast.makeText(UpdateDetailsFurnitures.this, "Double seats count is incorrect", Toast.LENGTH_SHORT).show();

                }else if(!checkValidationOnCondition(Integer.valueOf(edtTrippelSeated.getText().toString().trim()),Integer.valueOf(edtgoodConditionForTripple.getText().toString().trim()),Integer.valueOf(edtMinorConditionForTripple.getText().toString().trim()),Integer.valueOf(edtMajorConditionForTripple.getText().toString().trim())))
                {
                    dialog2.dismiss();

                    Toast.makeText(UpdateDetailsFurnitures.this, "Triple seats count is incorrect", Toast.LENGTH_SHORT).show();

                }else if (action.equals("3")){
                        if (arrayListImages1.size()==0 && aList.size()==0){
                            dialog2.dismiss();

                            Toast.makeText(UpdateDetailsFurnitures.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                        }else {
                            runService();

                        }
                    }else{
                        if (arrayListImages1.size()==0){
                            dialog2.dismiss();

                            Toast.makeText(UpdateDetailsFurnitures.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                        }else {
                            runService();

                        }
                    }

                }

            }
        });
    }

    private boolean checkValidationOnCondition(Integer total, Integer goodCondition, Integer minorCond, Integer MajorCond) {
        if (total!=goodCondition+minorCond+MajorCond){
            return false;
        }else return true;
    }

    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkFurniture(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"18"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerPositionForCondition0 = arrayAdapter2.getPosition(response.body().get(0).get("Condition").getAsString());
                int spinnerPositionForCondition1 = arrayAdapter2.getPosition(response.body().get(1).get("Condition").getAsString());
                int spinnerPositionForCondition2 = arrayAdapter2.getPosition(response.body().get(2).get("Condition").getAsString());

                spinnersingleSeatesStatus.setSelection(spinnerPositionForCondition0);
                spinnerDoubleSeatesStatus.setSelection(spinnerPositionForCondition1);
                spinnerTripleSeatesStatus.setSelection(spinnerPositionForCondition2);
                edtgoodConditionForSingle.setText(response.body().get(0).get("GoodCount").getAsString());
                edtMinorConditionForSingle.setText(response.body().get(0).get("MinorCount").getAsString());
                edtMajorConditionForSingle.setText(response.body().get(0).get("MajorCount").getAsString());

                edtgoodConditionForDouble.setText(response.body().get(1).get("GoodCount").getAsString());
                edtMinorConditionForDouble.setText(response.body().get(1).get("MinorCount").getAsString());
                edtMajorConditionForDouble.setText(response.body().get(1).get("MajorCount").getAsString());

                edtgoodConditionForTripple.setText(response.body().get(2).get("GoodCount").getAsString());
                edtMinorConditionForTripple.setText(response.body().get(2).get("MinorCount").getAsString());
                edtMajorConditionForTripple.setText(response.body().get(2).get("MajorCount").getAsString());


                edtFurnitureRequired.setText(response.body().get(0).get("AdditionalFurniture").getAsString());
                edtTotalFurnirtureStrenght.setText(response.body().get(0).get("TotalStrength").getAsString());
                edtSingleSeated.setText(response.body().get(0).get("TotalCnt").getAsString());
                edtDoubleSeated.setText(response.body().get(1).get("TotalCnt").getAsString());
                edtTrippelSeated.setText(response.body().get(0).get("TotalCnt").getAsString());


                recyclerViewFurnituresFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsFurnitures.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsFurnitures.this,aList);
                recyclerViewFurnituresFromServer.setAdapter(onlineImageRecViewAdapter);
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
            File compressedImage = new Compressor.Builder(UpdateDetailsFurnitures.this)
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
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraFurniture(action,"18","FurniturePhoto", applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayList,edtTotalFurnirtureStrenght.getText().toString(),edtFurnitureRequired.getText().toString(),arrayListImages1));
        Log.d("TAG", "onClick: "+paraFurniture(action,"18","FurniturePhoto", applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayList,edtTotalFurnirtureStrenght.getText().toString(),edtFurnitureRequired.getText().toString(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadFurniture(surveyImagesParts,description,deletUrl);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response+response.body());
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
    private String paraFurniture(String s, String s1, String furniturePhoto, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<FurnitureDetails> furnitureDetailsarray, String toString, String toString1, ArrayList<File> arrayListImages1) {
        JsonObject jsonObject=new JsonObject();
        Log.d("TAG", "paraFurniture: "+furnitureDetailsarray.get(2).getFurnitureType());
        jsonObject.addProperty("Action",s);
        jsonObject.addProperty("ParamId",s1);
        jsonObject.addProperty("ParamName",furniturePhoto);
        jsonObject.addProperty("Lat",latitude);
        jsonObject.addProperty("Long",longitude);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("CreatedBy",usertypeid);
        jsonObject.addProperty("UserCode",userid);
        JsonArray jsonArray=new JsonArray();
            JsonObject jsonObject1=new JsonObject();
            jsonObject1.addProperty("FurnitureType","Single");
        if ( Integer.parseInt(edtSingleSeated.getText().toString().trim())==0){
            jsonObject1.addProperty("Condition","0");
            jsonObject1.addProperty("GoodCount","0");
            jsonObject1.addProperty("MajorCount","0");
            jsonObject1.addProperty("MinorCount","0");
            jsonObject1.addProperty("TotalCnt",edtSingleSeated.getText().toString());
        }else{
            jsonObject1.addProperty("Condition","");
            jsonObject1.addProperty("GoodCount",edtgoodConditionForSingle.getText().toString());
            jsonObject1.addProperty("MajorCount",edtMajorConditionForSingle.getText().toString());
            jsonObject1.addProperty("MinorCount",edtMinorConditionForSingle.getText().toString());
            jsonObject1.addProperty("TotalCnt",edtSingleSeated.getText().toString());
        }

            jsonObject1.addProperty("Srno","1");

            JsonObject jsonObject2=new JsonObject();
            jsonObject2.addProperty("FurnitureType","Double");
        if ( Integer.parseInt(edtDoubleSeated.getText().toString().trim())==0){
            jsonObject2.addProperty("Condition","0");
            jsonObject2.addProperty("GoodCount","0");
            jsonObject2.addProperty("MajorCount","0");
            jsonObject2.addProperty("MinorCount","0");
            jsonObject2.addProperty("TotalCnt",edtDoubleSeated.getText().toString());
        }else{
            jsonObject2.addProperty("Condition","");
            jsonObject2.addProperty("GoodCount",edtgoodConditionForDouble.getText().toString());
            jsonObject2.addProperty("MajorCount",edtMajorConditionForDouble.getText().toString());
            jsonObject2.addProperty("MinorCount",edtMinorConditionForDouble.getText().toString());
            jsonObject2.addProperty("TotalCnt",edtDoubleSeated.getText().toString());
        }

            jsonObject2.addProperty("Srno","2");

        JsonObject jsonObject3=new JsonObject();
        jsonObject3.addProperty("FurnitureType","Tripple");
            if ( Integer.parseInt(edtTrippelSeated.getText().toString().trim())==0){
                jsonObject3.addProperty("Condition","0");
                jsonObject3.addProperty("GoodCount","0");
                jsonObject3.addProperty("MajorCount","0");
                jsonObject3.addProperty("MinorCount","0");
                jsonObject3.addProperty("TotalCnt",edtTrippelSeated.getText().toString());
            }else{
                jsonObject3.addProperty("Condition","");
                jsonObject3.addProperty("GoodCount",edtgoodConditionForTripple.getText().toString());
                jsonObject3.addProperty("MajorCount",edtMajorConditionForTripple.getText().toString());
                jsonObject3.addProperty("MinorCount",edtMinorConditionForTripple.getText().toString());
                jsonObject3.addProperty("TotalCnt",edtTrippelSeated.getText().toString());
            }

        jsonObject3.addProperty("Srno","3");




        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);


        jsonObject.add("FurnitureClassData",(JsonElement) jsonArray);
        jsonObject.addProperty("TotalStrength",toString);
        jsonObject.addProperty("AdditionalFurniture",toString1);
//
//        JsonArray jsonArray2 = new JsonArray();
//        for (int i = 0; i < arrayListImages1.size(); i++) {
//            jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//        }
//        jsonObject.add("FurniturePhoto", (JsonElement) jsonArray2);
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