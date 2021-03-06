package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.buildingaudit.Adapters.ImageAdapter3;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

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
    public ArrayList<Bitmap> arrayListImages2 = new ArrayList<>();
    ImageAdapter3 adapter2;
    EditText EdtNoOfCCTV;
    Dialog dialog,dialog2;

    Spinner spinnerCCTVWorkingStatus,spinnerCCTVInstallationYear,spinnerCCTVAvailabelty;
    ImageView CCTVImageUploadBtn;
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    RecyclerView recyclerViewCCTV;
    ConstraintLayout constraintLayout33;
    Button submitBtnCCTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_cctv);
        applicationController= (ApplicationController) getApplication();
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
        spinnerCCTVInstallationYear=findViewById(R.id.spinnerCCTVInstallationYear);
        spinnerCCTVAvailabelty=findViewById(R.id.spinnerCCTVAvailabelty);
        CCTVImageUploadBtn=findViewById(R.id.CCTVImageUploadBtn);
        recyclerViewCCTV=findViewById(R.id.recyclerViewCCTV);
        submitBtnCCTV=findViewById(R.id.submitBtnCCTV);
        constraintLayout33=findViewById(R.id.constraintLayout33);


        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerCCTVWorkingStatus.setAdapter(arrayAdapter2);

        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);


        spinnerCCTVAvailabelty.setAdapter(adapter);



        ArrayList<String> arrayListInstallationYear=new ArrayList<>();
        arrayListInstallationYear.add("2015");
        arrayListInstallationYear.add("2016");
        arrayListInstallationYear.add("2017");
        arrayListInstallationYear.add("2018");
        arrayListInstallationYear.add("2019");
        arrayListInstallationYear.add("2020");
        arrayListInstallationYear.add("2021");
        arrayListInstallationYear.add("2022");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerCCTVInstallationYear.setAdapter(arrayAdapter1);


        CCTVImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsCCTV.this)
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

        recyclerViewCCTV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2= new ImageAdapter3(this, arrayListImages2);
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
        });
    }

    private void runService() {
        RestClient restClient =new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.uploadCCTVDetails(paraCCTV("1","10","CCTV",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),spinnerCCTVInstallationYear.getSelectedItem().toString(),EdtNoOfCCTV.getText().toString(),spinnerCCTVWorkingStatus.getSelectedItem().toString(),spinnerCCTVAvailabelty.getSelectedItem().toString(),arrayListImages2));
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

    private JsonObject paraCCTV(String s, String s1, String cctv, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String installationYear, String nOOFcctv, String workingStatus, String availabilty, ArrayList<Bitmap> arrayListImages2) {
        JsonObject jsonObject=new JsonObject();
if (availabilty.equals("No")){
    jsonObject.addProperty("Action",s);
    jsonObject.addProperty("ParamId",s1);
    jsonObject.addProperty("ParamName",cctv);
    jsonObject.addProperty("SchoolId",schoolId);
    jsonObject.addProperty("PeriodID",periodID);
    jsonObject.addProperty("InstallationYear","0");
    jsonObject.addProperty("NoOfCCTV","0");
    jsonObject.addProperty("WorkingStatus","");
    jsonObject.addProperty("Availabilty",availabilty);
    jsonObject.addProperty("Lat",latitude);
    jsonObject.addProperty("Long",longitude);
    jsonObject.addProperty("CreatedBy",usertypeid);
    jsonObject.addProperty("UserCode",userid);

    JsonArray jsonArray2 = new JsonArray();
    for (int i = 0; i < arrayListImages2.size(); i++) {
        jsonArray2.add(paraGetImageBase64( arrayListImages2.get(i), i));

    }
    jsonObject.add("CctvPhoto", (JsonElement) jsonArray2);
}else{
    jsonObject.addProperty("Action",s);
    jsonObject.addProperty("ParamId",s1);
    jsonObject.addProperty("ParamName",cctv);
    jsonObject.addProperty("SchoolId",schoolId);
    jsonObject.addProperty("PeriodID",periodID);
    jsonObject.addProperty("InstallationYear",installationYear);
    jsonObject.addProperty("NoOfCCTV",nOOFcctv);
    jsonObject.addProperty("WorkingStatus",workingStatus);
    jsonObject.addProperty("Availabilty",availabilty);
    jsonObject.addProperty("Lat",latitude);
    jsonObject.addProperty("Long",longitude);
    jsonObject.addProperty("CreatedBy",usertypeid);
    jsonObject.addProperty("UserCode",userid);

    JsonArray jsonArray2 = new JsonArray();
    for (int i = 0; i < arrayListImages2.size(); i++) {
        jsonArray2.add(paraGetImageBase64( arrayListImages2.get(i), i));

    }
    jsonObject.add("CctvPhoto", (JsonElement) jsonArray2);
}

        return jsonObject;
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
                Uri uri=data.getData();
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");


            arrayListImages2.add(bitmap);



        }
    }
}