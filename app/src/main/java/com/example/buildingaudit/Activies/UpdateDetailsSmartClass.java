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
import android.widget.LinearLayout;
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
import com.example.buildingaudit.Adapters.SmartClassAdapter;
import com.example.buildingaudit.Adapters.SmartClassAdapterForEdit;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.CompressLib.Compressor;
import com.example.buildingaudit.ConstantValues.ConstantFile;
import com.example.buildingaudit.Model.SmartClassesCard;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
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

public class UpdateDetailsSmartClass extends AppCompatActivity {
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
    int smartClassNumber=1;
    String currentImagePath=null;
    File imageFile=null;
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    ImageAdapter5 adapter;
    ImageView smartClassImageUploadBtn,deleteRoomBtn;
    Button buttonForAddRooms;
    LinearLayout layoutForAddRooms;
    RecyclerView recyclerViewSmartClass,recyclerViewforViewFromServer,recyclerViewSmartClassFromServer;
Spinner spinnerInstallationYearSmartClass,spinnerUnderSchemeSmartClass,spinnerWorkingStatusSmartClass,
        spinnerTeacherAvailbilitySmartClass,spinnerProjectorSmartClass,spinnerLearningContentSmartClass,
        spinnerDigitalBoardSmartClass,smartClassAvailabilty;
    ApplicationController applicationController;
    EditText companyName1;
    TextView userName,schoolAddress,schoolName;
    ConstraintLayout constraintLayout24;
    Button submitSmartClassBtn;
    ArrayList<SmartClassesCard> smartClassesCards=new ArrayList<>();
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    EditText edtSmartOtherScheme;
    Dialog dialog;
    ArrayAdapter<String> arrayAdapter,arrayAdapter1,arrayAdapter2,arrayAdapter3,arrayAdapter5;
    Dialog dialog2;
    String action;
    String otherSchemeOnCard,otherScheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_smart_class);
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
        spinnerInstallationYearSmartClass=findViewById(R.id.spinnerInstallationYearSmartClass);
        companyName1=findViewById(R.id.companyName1);
        layoutForAddRooms=findViewById(R.id.layoutForAddRooms);
        spinnerUnderSchemeSmartClass=findViewById(R.id.spinnerUnderSchemeSmartClass);
        buttonForAddRooms=findViewById(R.id.buttonForAddRooms);
        submitSmartClassBtn=findViewById(R.id.submitSmartClassBtn);
        spinnerWorkingStatusSmartClass=findViewById(R.id.spinnerWorkingStatusSmartClass);
        spinnerTeacherAvailbilitySmartClass=findViewById(R.id.spinnerTeacherAvailbilitySmartClass);
        spinnerProjectorSmartClass=findViewById(R.id.spinnerProjectorSmartClass);
        spinnerLearningContentSmartClass=findViewById(R.id.spinnerLearningContentSmartClass);
        spinnerDigitalBoardSmartClass=findViewById(R.id.spinnerDigitalBoardSmartClass);
        smartClassAvailabilty=findViewById(R.id.smartClassAvailabilty);
        smartClassImageUploadBtn=findViewById(R.id.smartClassImageUploadBtn);
        recyclerViewSmartClass=findViewById(R.id.recyclerViewSmartClass);
        constraintLayout24=findViewById(R.id.constraintLayout24);
        edtSmartOtherScheme=findViewById(R.id.edtSmartOtherScheme);
        recyclerViewSmartClassFromServer=findViewById(R.id.recyclerViewSmartClassFromServer);
        recyclerViewforViewFromServer=findViewById(R.id.recyclerViewforViewFromServer);
            if (action.equals("3")){
                feychAllDataFromServer();
            }
        ArrayList<String> arrayListInstallationYear=new ArrayList<>();

        for (int i = 0; i < applicationController.getInstallationYears().size(); i++) {
         arrayListInstallationYear.add(applicationController.getInstallationYears().get(i).getYear());
        }
         arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerInstallationYearSmartClass.setAdapter(arrayAdapter1);


        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

       arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerUnderSchemeSmartClass.setAdapter(arrayAdapter2);

        spinnerUnderSchemeSmartClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerUnderSchemeSmartClass.getSelectedItem().toString().equals("Others")){
                    edtSmartOtherScheme.setVisibility(View.VISIBLE);
                }else{
                    edtSmartOtherScheme.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayListLevellingStatus=new ArrayList<>();
        arrayListLevellingStatus.add("Functional");
        arrayListLevellingStatus.add("Non Functional");

        arrayAdapter3=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListLevellingStatus);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerWorkingStatusSmartClass.setAdapter(arrayAdapter3);


        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
       arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerTeacherAvailbilitySmartClass.setAdapter(arrayAdapter);
        spinnerProjectorSmartClass.setAdapter(arrayAdapter);
        spinnerDigitalBoardSmartClass.setAdapter(arrayAdapter);
        spinnerLearningContentSmartClass.setAdapter(arrayAdapter);
        ArrayList<String> arrayListAvailbilty1=new ArrayList<>();
        arrayListAvailbilty1.add("Yes");
        arrayListAvailbilty1.add("No");
        arrayListAvailbilty1.add("Alternate Room");
        arrayAdapter5=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty1);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        smartClassAvailabilty.setAdapter(arrayAdapter5);

        smartClassImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(UpdateDetailsSmartClass.this)
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
                                            Uri imageUri= FileProvider.getUriForFile(UpdateDetailsSmartClass.this, ConstantFile.PROVIDER_STRING,imageFile);
                                            i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                            startActivityForResult(i,2);
                                        }
                                    }

                                }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDetailsSmartClass.this);

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
        recyclerViewSmartClass.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter5(this, arrayListImages1);
        recyclerViewSmartClass.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        smartClassAvailabilty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (smartClassAvailabilty.getSelectedItem().equals("No")){
                    constraintLayout24.setVisibility(View.GONE);
                }else {
                    constraintLayout24.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submitSmartClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
dialog2.show();
                if (spinnerUnderSchemeSmartClass.getSelectedItem().toString().equals("Others")){
                    otherScheme=edtSmartOtherScheme.getText().toString();
                }else{
                otherScheme=spinnerUnderSchemeSmartClass.getSelectedItem().toString();
                }
                SmartClassesCard[] array = new SmartClassesCard[layoutForAddRooms.getChildCount()+1];
                String OtherSchemeYN;
                if (spinnerUnderSchemeSmartClass.getSelectedItem().toString().equals("Others")){
                    OtherSchemeYN="Y";
                }else{
                    OtherSchemeYN="N";

                }
                    SmartClassesCard smartClassesCard1=new SmartClassesCard(spinnerInstallationYearSmartClass.getSelectedItem().toString(),
                            companyName1.getText().toString(),otherScheme,OtherSchemeYN,"1","SmartClass"+1,spinnerWorkingStatusSmartClass.getSelectedItem().toString());


                    if(smartClassAvailabilty.getSelectedItem().toString().equals("No")){

                    }else{

                        array[0]=smartClassesCard1;
                    }

                for (int i=0; i < layoutForAddRooms.getChildCount(); i++){
                    Spinner spinnerWorkingStatusSmartClassAdd=layoutForAddRooms.getChildAt(i).findViewById(R.id.spinnerWorkingStatusSmartClassAdd);
                    Spinner spinnerUnderSchemeSmartClassAdd=layoutForAddRooms.getChildAt(i).findViewById(R.id.spinnerUnderSchemeSmartClassAdd);
                    EditText edtSmartClassCompanyNameAdd=layoutForAddRooms.getChildAt(i).findViewById(R.id.edtSmartClassCompanyNameAdd);
                    Spinner spinnerInstallationYearSmartClassAdd=layoutForAddRooms.getChildAt(i).findViewById(R.id.spinnerInstallationYearSmartClassAdd);
                    EditText edtOtherSchemeSmartClassAdd=layoutForAddRooms.getChildAt(i).findViewById(R.id.edtSolarPanelOtherSchemeCard);
//                    spinnerInstallationYearSmartClassAdd.setAdapter(arrayAdapter1);
//                    spinnerUnderSchemeSmartClassAdd.setAdapter(arrayAdapter2);
//                    spinnerWorkingStatusSmartClassAdd.setAdapter(arrayAdapter3);
                    if (spinnerUnderSchemeSmartClassAdd.getSelectedItem().toString().equals("Others")){
                        otherSchemeOnCard =edtOtherSchemeSmartClassAdd.getText().toString();
                    }else{
                        otherSchemeOnCard =spinnerUnderSchemeSmartClassAdd.getSelectedItem().toString();
                    }
                    String OtherSchemeYNInAddCard;
                    if (spinnerUnderSchemeSmartClassAdd.getSelectedItem().toString().equals("Others")){
                        OtherSchemeYNInAddCard="Y";
                    }else{
                        OtherSchemeYNInAddCard="N";

                    }

                    SmartClassesCard smartClassesCard=new SmartClassesCard(spinnerInstallationYearSmartClassAdd.getSelectedItem().toString(),
                            edtSmartClassCompanyNameAdd.getText().toString(), otherSchemeOnCard,OtherSchemeYNInAddCard,String.valueOf(i+2),"SmartClass"+(i+2),spinnerWorkingStatusSmartClassAdd.getSelectedItem().toString());
                    if (smartClassAvailabilty.getSelectedItem().toString().equals("No")){

                    }else{

                        array[i+1]=smartClassesCard;
                    }
                }
                if (!smartClassAvailabilty.getSelectedItem().toString().equals("No")){

                    if (arrayListImages1.size()==0){
                        Toast.makeText(UpdateDetailsSmartClass.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();

                    }else {
                      runService(array);

                    }
                }else{
                    runService(array);
                }

            }
        });
        buttonForAddRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartClassNumber=smartClassNumber+1;
                final View addClsss=getLayoutInflater().inflate(R.layout.new_smart_class,null,false);
                ImageView deleteRoomBtn=addClsss.findViewById(R.id.deleteRoomBtn);
                TextView txtSmartRoomName=addClsss.findViewById(R.id.txtSmartRoomNameedtable);
                txtSmartRoomName.setText("Smart Classoom");
                Spinner spinnerInstallationYearSmartClassAdd=addClsss.findViewById(R.id.spinnerInstallationYearSmartClassAdd);
                Spinner spinnerWorkingStatusSmartClassAdd=addClsss.findViewById(R.id.spinnerWorkingStatusSmartClassAdd);
                Spinner spinnerUnderSchemeSmartClassAdd=addClsss.findViewById(R.id.spinnerUnderSchemeSmartClassAdd);
                EditText edtSmartClassCompanyNameAdd=addClsss.findViewById(R.id.edtSmartClassCompanyNameAdd);
                EditText edtOtherSchemeSmartClassAdd=addClsss.findViewById(R.id.edtSolarPanelOtherSchemeCard);

                spinnerInstallationYearSmartClassAdd.  setAdapter(arrayAdapter1);
                spinnerUnderSchemeSmartClassAdd.setAdapter(arrayAdapter2);
                spinnerWorkingStatusSmartClassAdd.setAdapter(arrayAdapter3);
                        spinnerUnderSchemeSmartClassAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (spinnerUnderSchemeSmartClassAdd.getSelectedItem().toString().equals("Others")){
                                    edtOtherSchemeSmartClassAdd.setVisibility(View.VISIBLE);
                                }else{
                                    edtOtherSchemeSmartClassAdd.setVisibility(View.GONE);

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                deleteRoomBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        layoutForAddRooms.removeView(addClsss);
                       
                    }
                });
                layoutForAddRooms.addView(addClsss);


            }
        });
    }


    private void feychAllDataFromServer() {

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkSmartClassDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"8"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerPositionForAvailablity = arrayAdapter5.getPosition(response.body().get(0).get("Availablity").getAsString());
                int spinnerPositionForDigitalBoard = arrayAdapter.getPosition(response.body().get(0).get("DigitalBoard").getAsString());
                int spinnerPositionForLearningContent = arrayAdapter.getPosition(response.body().get(0).get("LearningContent").getAsString());
                int spinnerPositionForProjector = arrayAdapter.getPosition(response.body().get(0).get("Projector").getAsString());
               response.body().get(0).get("Name").getAsString();
                int spinnerPositionForInstallationYear = arrayAdapter1.getPosition(response.body().get(0).get("InstallationYear").getAsString());

                int spinnerPositionForScheme = arrayAdapter2.getPosition(response.body().get(0).get("Scheme").getAsString());
                int spinnerPositionForWorkingStatus = arrayAdapter3.getPosition(response.body().get(0).get("WorkingStatus").getAsString());
                spinnerWorkingStatusSmartClass.setSelection(spinnerPositionForWorkingStatus);
                companyName1.setText(response.body().get(0).get("CompanyName").getAsString());
                spinnerInstallationYearSmartClass.setSelection(spinnerPositionForInstallationYear);
                smartClassAvailabilty.setSelection(spinnerPositionForAvailablity);
                spinnerDigitalBoardSmartClass.setSelection(spinnerPositionForDigitalBoard);
                spinnerProjectorSmartClass.setSelection(spinnerPositionForProjector);
                spinnerLearningContentSmartClass.setSelection(spinnerPositionForLearningContent);
                spinnerUnderSchemeSmartClass.setSelection(spinnerPositionForScheme);
                edtSmartOtherScheme.setVisibility(View.GONE);




                for (int i=1;i<response.body().size();i++){
                    SmartClassesCard smartClassesCard=new SmartClassesCard();
                    smartClassesCard.setCompanyName(response.body().get(i).get("CompanyName").getAsString());
                    smartClassesCard.setName(response.body().get(i).get("Name").getAsString());
                    smartClassesCard.setScheme(response.body().get(i).get("Scheme").getAsString());
                    smartClassesCard.setInstallationYear(response.body().get(i).get("InstallationYear").getAsString());
                    smartClassesCard.setWorkingStatus(response.body().get(i).get("WorkingStatus").getAsString());
                    smartClassesCard.setSrno(response.body().get(i).get("Srno").getAsString());
                    smartClassesCards.add(smartClassesCard);

                }
                recyclerViewforViewFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsSmartClass.this,RecyclerView.VERTICAL,false));
                recyclerViewforViewFromServer.setAdapter(new SmartClassAdapterForEdit(UpdateDetailsSmartClass.this,smartClassesCards));
                (new SmartClassAdapter(UpdateDetailsSmartClass.this,smartClassesCards)).notifyDataSetChanged();
                recyclerViewSmartClassFromServer.setLayoutManager(new LinearLayoutManager(UpdateDetailsSmartClass.this,LinearLayoutManager.HORIZONTAL,false));

                StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                aList = new ArrayList<String>(Arrays.asList(StaffPhotoPathList));
                UpdateDetailsOfExtraThings obj=new UpdateDetailsOfExtraThings();
                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdateDetailsSmartClass.this,aList);
                recyclerViewSmartClassFromServer.setAdapter(onlineImageRecViewAdapter);
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
    private void runService(SmartClassesCard[] array) {
        ArrayList<SmartClassesCard> aList = new ArrayList<SmartClassesCard>(Arrays.asList(array));
        aList.addAll(smartClassesCards);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListImages1.size()];
        for (int i = 0; i < arrayListImages1.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListImages1.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdateDetailsSmartClass.this)
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
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraSmartClass(action,"8","SmartClass",aList, applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerTeacherAvailbilitySmartClass.getSelectedItem().toString(),spinnerProjectorSmartClass.getSelectedItem().toString(),spinnerLearningContentSmartClass.getSelectedItem().toString(),spinnerDigitalBoardSmartClass.getSelectedItem().toString(),smartClassAvailabilty.getSelectedItem().toString(),arrayListImages1));
        Log.d("TAG", "onClick: "+paraSmartClass(action,"8","SmartClass",aList, applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerTeacherAvailbilitySmartClass.getSelectedItem().toString(),spinnerProjectorSmartClass.getSelectedItem().toString(),spinnerLearningContentSmartClass.getSelectedItem().toString(),spinnerDigitalBoardSmartClass.getSelectedItem().toString(),smartClassAvailabilty.getSelectedItem().toString(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadSmartClassDetails(surveyImagesParts,description);
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
    private String paraSmartClass(String action, String paramId, String smartClass, ArrayList<SmartClassesCard> array, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String teacherAvailabilty, String projector, String learningContent, String digitalBoard, String avaiabilty, ArrayList<File> arrayListImages1) {

        JsonObject jsonObject=new JsonObject();
        if (avaiabilty.equals("No")){
            jsonObject.addProperty("Action",action);
            jsonObject.addProperty("ParamId",paramId);
            jsonObject.addProperty("ParamName",smartClass);
            JsonArray jsonArray=new JsonArray();
            try{
                for ( int i=0;i<array.size();i++){
                    JsonObject jsonObject1=new JsonObject();
                    jsonObject1.addProperty("InstallationYear",array.get(i).getInstallationYear());
                    jsonObject1.addProperty("CompanyName",array.get(i).getCompanyName());
                    jsonObject1.addProperty("Scheme",array.get(i).getScheme());
                    jsonObject1.addProperty("OtherSchemeYN",array.get(i).getOtherSchemeYN());
                    jsonObject1.addProperty("Srno",i+1);
                    jsonObject1.addProperty("Name",array.get(i).getName());
                    jsonObject1.addProperty("WorkingStatus",array.get(i).getWorkingStatus());
                    jsonArray.add(jsonObject1);
                }
            }catch (Exception e){
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InstallationYear","0");
                jsonObject1.addProperty("CompanyName","");
                jsonObject1.addProperty("Scheme","");
                jsonObject1.addProperty("OtherSchemeYN","");
                jsonObject1.addProperty("Srno","0");
                jsonObject1.addProperty("Name","");
                jsonObject1.addProperty("WorkingStatus","");
                jsonArray.add(jsonObject1);
            }

            jsonObject.add("SmartClassData",(JsonElement)jsonArray);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);
            jsonObject.addProperty("TeacherAvl","");
            jsonObject.addProperty("Projector","");
            jsonObject.addProperty("LearningContent","");
            jsonObject.addProperty("DigitalBoard","");
            jsonObject.addProperty("Availablity",avaiabilty);
//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("SmartClassPhoto", (JsonElement) jsonArray2);
        }else{
            jsonObject.addProperty("Action",action);
            jsonObject.addProperty("ParamId",paramId);
            jsonObject.addProperty("ParamName",smartClass);
            JsonArray jsonArray=new JsonArray();
            for ( int i=0;i<array.size();i++){
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InstallationYear",array.get(i).getInstallationYear());
                jsonObject1.addProperty("CompanyName",array.get(i).getCompanyName());
                jsonObject1.addProperty("Scheme",array.get(i).getScheme());
                jsonObject1.addProperty("OtherSchemeYN",array.get(i).getOtherSchemeYN());
                jsonObject1.addProperty("Srno",i+1);
                jsonObject1.addProperty("Name",array.get(i).getName());
                jsonObject1.addProperty("WorkingStatus",array.get(i).getWorkingStatus());
                jsonArray.add(jsonObject1);
            }
            jsonObject.add("SmartClassData",(JsonElement)jsonArray);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);
            jsonObject.addProperty("TeacherAvl",teacherAvailabilty);
            jsonObject.addProperty("Projector",projector);
            jsonObject.addProperty("LearningContent",learningContent);
            jsonObject.addProperty("DigitalBoard",digitalBoard);
            jsonObject.addProperty("Availablity",avaiabilty);
//            JsonArray jsonArray2 = new JsonArray();
//            for (int i = 0; i < arrayListImages1.size(); i++) {
//                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));
//
//            }
//            jsonObject.add("SmartClassPhoto", (JsonElement) jsonArray2);
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

    private void addAnotherSmartClass() {



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