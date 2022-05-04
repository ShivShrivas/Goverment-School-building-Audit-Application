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

import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.Model.FurnitureDetails;
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
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    ImageAdapter4 adapter6;
    ImageView furniutresImageUploadBtn;
    ArrayList<FurnitureDetails> arrayList=new ArrayList<>();
    EditText edtFurnitureRequired;
    Spinner spinnerTripleSeatesStatus,spinnersingleSeatesStatus,spinnerDoubleSeatesStatus;
    RecyclerView recyclerViewFurnitures;
    Button submitBtnFurniture;
    EditText edtTrippelSeated,edtDoubleSeated,edtSingleSeated;
    TextView edtTotalFurnirtureStrenght;
    TextView userName,schoolAddress,schoolName;
    Dialog dialog;
    ApplicationController applicationController;
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

        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Good Condition");
        arrayListSpinner2.add("Minor Repairing");
        arrayListSpinner2.add("Major repairing");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner2);
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

                Dexter.withActivity(UpdateDetailsFurnitures.this)
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
        recyclerViewFurnitures.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter4(this, arrayListImages1);
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




                arrayList.add(furnitureDetailssingle);
                arrayList.add(furnitureDetailsDouble);
                arrayList.add(furnitureDetailsTriple);

                Log.d("TAG", "onClick: "+   arrayList.get(1).getFurnitureType());
                if (arrayListImages1.size()==0){
                    Toast.makeText(UpdateDetailsFurnitures.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                }else {
                RestClient restClient=new RestClient();
                ApiService apiService=restClient.getApiService();
                Log.d("TAG", "onClick: "+paraFurniture("1","18","FurniturePhoto", applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayList,edtTotalFurnirtureStrenght.getText().toString(),edtFurnitureRequired.getText().toString(),arrayListImages1));
                Call<List<JsonObject>> call=apiService.uploadFurniture(paraFurniture("1","18","FurniturePhoto", applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayList,edtTotalFurnirtureStrenght.getText().toString(),edtFurnitureRequired.getText().toString(),arrayListImages1));
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
        });
    }

    private JsonObject paraFurniture(String s, String s1, String furniturePhoto, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<FurnitureDetails> furnitureDetailsarray, String toString, String toString1, ArrayList<Bitmap> arrayListImages1) {
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
                jsonObject1.addProperty("Condition",spinnersingleSeatesStatus.getSelectedItem().toString());
            jsonObject1.addProperty("TotalCnt",edtSingleSeated.getText().toString());
            jsonObject1.addProperty("Srno","1");

            JsonObject jsonObject2=new JsonObject();
            jsonObject2.addProperty("FurnitureType","Double");

                jsonObject2.addProperty("Condition",spinnerDoubleSeatesStatus.getSelectedItem().toString());
            jsonObject2.addProperty("TotalCnt",edtDoubleSeated.getText().toString());
            jsonObject2.addProperty("Srno","2");

        JsonObject jsonObject3=new JsonObject();
        jsonObject3.addProperty("FurnitureType","Tripple");

            jsonObject3.addProperty("Condition",spinnerTripleSeatesStatus.getSelectedItem().toString());
        jsonObject3.addProperty("TotalCnt",edtTrippelSeated.getText().toString());
        jsonObject3.addProperty("Srno","3");




        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);


        jsonObject.add("FurnitureClassData",(JsonElement) jsonArray);
        jsonObject.addProperty("TotalStrength",toString);
        jsonObject.addProperty("AdditionalFurniture",toString1);

        JsonArray jsonArray2 = new JsonArray();
        for (int i = 0; i < arrayListImages1.size(); i++) {
            jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));

        }
        jsonObject.add("FurniturePhoto", (JsonElement) jsonArray2);
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

            arrayListImages1.add(bitmap);


        }
    }
}