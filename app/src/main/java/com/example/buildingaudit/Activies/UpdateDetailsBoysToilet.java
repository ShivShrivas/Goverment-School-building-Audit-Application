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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class UpdateDetailsBoysToilet extends AppCompatActivity {
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
    Button submitBtnboysToilet;
    EditText edtUrinalWithFlushTotalB,edtUrinalWithoutFlushB,edtUrinalWithFlushB,edtCSWNfriendlyTotalB,edtCSWNwithoutfriendlyB,edtCSWNfriendlyB,
            edtwithflushTotal,edtWithoutFlushClean,edtWithFlushClean;
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    ImageAdapter4 adapter;
    Dialog dialog;
    LinearLayout linearLayoutCWSNfriendlyToilet;
Spinner spinnerCWSNBoysAvailability,spinnerBoysDoors,spinnerBoysDustbin,spinnerBoysIncinerator;
ImageView boysToiletImageUploadBtn;
RecyclerView recyclerViewBoysToilet;
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_boys_toilet);
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
        spinnerCWSNBoysAvailability=findViewById(R.id.spinnerCWSNBoysAvailability);
        spinnerBoysDoors=findViewById(R.id.spinnerBoysDoors);
        boysToiletImageUploadBtn=findViewById(R.id.boysToiletImageUploadBtn);
        recyclerViewBoysToilet=findViewById(R.id.recyclerViewBoysToilet);
        spinnerBoysDustbin=findViewById(R.id.spinnerBoysDustbin);
        linearLayoutCWSNfriendlyToilet=findViewById(R.id.linearLayoutCWSNfriendlyToilet);
        edtUrinalWithFlushTotalB=findViewById(R.id.edtUrinalWithFlushTotalB);
        edtUrinalWithoutFlushB=findViewById(R.id.edtUrinalWithoutFlushB);
        edtUrinalWithFlushB=findViewById(R.id.edtUrinalWithFlushB);
        edtCSWNfriendlyTotalB=findViewById(R.id.edtCSWNfriendlyTotalB);
        edtCSWNwithoutfriendlyB=findViewById(R.id.edtCSWNwithoutfriendlyB);
        edtCSWNfriendlyB=findViewById(R.id.edtCSWNfriendlyB);
        edtwithflushTotal=findViewById(R.id.edtwithflushTotal);
        edtWithoutFlushClean=findViewById(R.id.edtWithoutFlushClean);
        edtWithFlushClean=findViewById(R.id.edtWithFlushClean);
        submitBtnboysToilet=findViewById(R.id.submitBtnboysToilet);
        edtwithflushTotal.setEnabled(false);
        edtCSWNfriendlyTotalB.setEnabled(false);
        edtUrinalWithFlushTotalB.setEnabled(false);

        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);

        spinnerCWSNBoysAvailability.setAdapter(arrayAdapter);
                spinnerBoysDoors.setAdapter(arrayAdapter);
        spinnerBoysDustbin.setAdapter(arrayAdapter);


        boysToiletImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsBoysToilet.this)
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
        recyclerViewBoysToilet.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter4(this, arrayListImages1);
        recyclerViewBoysToilet.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        spinnerCWSNBoysAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        submitBtnboysToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayListImages1.size()==0){
                    Toast.makeText(UpdateDetailsBoysToilet.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                }else {
                RestClient restClient=new RestClient();
                ApiService apiService=restClient.getApiService();
                Log.d("TAG", "onClick: "+paraBoysToilet("1","16","BoysToiletPhoto",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),edtWithFlushClean.getText().toString(),edtWithoutFlushClean.getText().toString(),edtwithflushTotal.getText().toString(),spinnerCWSNBoysAvailability.getSelectedItem().toString(),edtCSWNfriendlyB.getText().toString(),edtCSWNwithoutfriendlyB.getText().toString(),edtCSWNfriendlyTotalB.getText().toString(),edtUrinalWithFlushB.getText().toString(),edtUrinalWithoutFlushB.getText().toString(),edtUrinalWithFlushTotalB.getText().toString(),spinnerBoysDoors.getSelectedItem().toString(),spinnerBoysDustbin.getSelectedItem().toString(),arrayListImages1));
                Call<List<JsonObject>> call=apiService.uploadBoysToilet(paraBoysToilet("1","16","BoysToiletPhoto",applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),edtWithFlushClean.getText().toString(),edtWithoutFlushClean.getText().toString(),edtwithflushTotal.getText().toString(),spinnerCWSNBoysAvailability.getSelectedItem().toString(),edtCSWNfriendlyB.getText().toString(),edtCSWNwithoutfriendlyB.getText().toString(),edtCSWNfriendlyTotalB.getText().toString(),edtUrinalWithFlushB.getText().toString(),edtUrinalWithoutFlushB.getText().toString(),edtUrinalWithFlushTotalB.getText().toString(),spinnerBoysDoors.getSelectedItem().toString(),spinnerBoysDustbin.getSelectedItem().toString(),arrayListImages1));
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

    private JsonObject paraBoysToilet(String s, String s1, String boundaryWallPhoto, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, String noOfSeatsExcludingCWSNWithFlush, String noOfSeatsExcludingCWSNWithOutFlush, String totalExcludingCWSN, String availabilityCWSN, String noOfSeatsCWSNWithFlush, String noOfSeatsCWSNWithOutFlush, String totalCWSN, String noOfUrinalsWithFlush, String noOfUrinalsWithOutFlush, String totalUrinals, String door, String dustbin, ArrayList<Bitmap> arrayListImages1) {

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
            jsonObject.addProperty("NoOfSeatsCWSNWithFlush","null");
            jsonObject.addProperty("NoOfSeatsCWSNWithOutFlush","null");
            jsonObject.addProperty("TotalCWSN","null");
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

        JsonArray jsonArray2 = new JsonArray();
        for (int i = 0; i < arrayListImages1.size(); i++) {
            jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));

        }
        jsonObject.add("BoysToiletPhoto", (JsonElement) jsonArray2);
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