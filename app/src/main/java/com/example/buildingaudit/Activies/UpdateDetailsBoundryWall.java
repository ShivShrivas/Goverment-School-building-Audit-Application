package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
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

import com.example.buildingaudit.Adapters.ImageAdapter3;
import com.example.buildingaudit.Adapters.ImageAdapter4;
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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailsBoundryWall extends AppCompatActivity {
Spinner spinnerWallCondition,spinnerWhiteWash,spinnerTypeBoundaryWall,spinnerBoundaryWallAvail,spinnerBoundryScheme;
ImageView boundaryWallImageUploadBtn;
EditText edtAreaofSchool,edtLengthofWall;
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


    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages2 = new ArrayList<>();
    int btnType;
    ImageAdapter4 adapter1;
    ImageAdapter3 adapter2;
    RecyclerView recyclerViewTwoTypeBoundarywall;

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

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        Dialog dialog2 = new Dialog(this);

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
        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerBoundaryWallAvail.setAdapter(adapter);
        spinnerWhiteWash.setAdapter(adapter);

        ArrayList<String> arrayListSpinner = new ArrayList<>();

        arrayListSpinner.add("Good Condition");
        arrayListSpinner.add("Minor repairing requored");
        arrayListSpinner.add("major repairing required");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerWallCondition.setAdapter(arrayAdapter);
        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerBoundryScheme.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListWallType = new ArrayList<>();

        arrayListWallType.add("Pucca");
        arrayListWallType.add("Pucca but broken");
        arrayListWallType.add("Barbed Wire");
        arrayListWallType.add("Fencing");
        arrayListWallType.add("Hedges");
        arrayListWallType.add("No Boundary Walls");
        arrayListWallType.add("Partial");
        arrayListWallType.add("Under Construction");
        arrayListWallType.add("Others");


        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListWallType);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerTypeBoundaryWall.setAdapter(arrayAdapter4);


        boundaryWallImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsBoundryWall.this)
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

        recyclerViewTwoTypeBoundarywall.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2= new ImageAdapter3(this, arrayListImages2);
        recyclerViewTwoTypeBoundarywall.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        bntBoundaryWallUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtLengthofWall.length() == 0 || edtAreaofSchool.length()==0) {
                    Toast.makeText(UpdateDetailsBoundryWall.this, "Please fill all details properly!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (arrayListImages1.size()==0){
                        Toast.makeText(UpdateDetailsBoundryWall.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                    }else {
                    RestClient restClient = new RestClient();
                    ApiService apiService = restClient.getApiService();
                    Log.d("TAG", "onClick: " + paraBoundry("1", "15", "BoundaryWallPhoto", applicationController.getLatitude(), applicationController.getLongitude(), applicationController.getSchoolId(), applicationController.getPeriodID(), applicationController.getUsertypeid(), applicationController.getUserid(), spinnerBoundaryWallAvail.getSelectedItem().toString(), edtAreaofSchool.getText().toString(), edtLengthofWall.getText().toString(), spinnerTypeBoundaryWall.getSelectedItem().toString(), spinnerWhiteWash.getSelectedItem().toString(), spinnerWallCondition.getSelectedItem().toString(), spinnerBoundryScheme.getSelectedItem().toString(), arrayListImages2));
                    Call<List<JsonObject>> call = apiService.uploadBoundryWall(paraBoundry("1", "15", "BoundaryWallPhoto", applicationController.getLatitude(), applicationController.getLongitude(), applicationController.getSchoolId(), applicationController.getPeriodID(), applicationController.getUsertypeid(), applicationController.getUserid(), spinnerBoundaryWallAvail.getSelectedItem().toString(), edtAreaofSchool.getText().toString(), edtLengthofWall.getText().toString(), spinnerTypeBoundaryWall.getSelectedItem().toString(), spinnerWhiteWash.getSelectedItem().toString(), spinnerWallCondition.getSelectedItem().toString(), spinnerBoundryScheme.getSelectedItem().toString(), arrayListImages2));
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
                            }
                        }

                        @Override
                        public void onFailure(Call<List<JsonObject>> call, Throwable t) {

                        }
                    });


                }
                }
            }
        });
    }

    private JsonObject paraBoundry(String s, String s1, String boundaryWallPhoto, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6, ArrayList<Bitmap> arrayListImages2) {
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
        jsonObject.addProperty("Availabilty",toString);
        jsonObject.addProperty("SchoolArea",toString1);
        jsonObject.addProperty("BoundaryWallLength",toString2);
        jsonObject.addProperty("BoundaryWallType",toString3);
        jsonObject.addProperty("WhiteWashStatus",toString4);
        jsonObject.addProperty("Condition",toString5);
        jsonObject.addProperty("Scheme",toString6);

        JsonArray jsonArray2 = new JsonArray();
        for (int i = 0; i < arrayListImages2.size(); i++) {
            jsonArray2.add(paraGetImageBase64( arrayListImages2.get(i), i));

        }
        jsonObject.add("BoundaryWallPhoto", (JsonElement) jsonArray2);
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
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");


            arrayListImages2.add(bitmap);



        }
    }
}