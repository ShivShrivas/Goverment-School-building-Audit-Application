package com.bsn.buildingaudit.DIOS.ExtraActivitiesDetails;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.GetNCCParticipationDetailsModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Participation_Details extends AppCompatActivity {
ApplicationController applicationController;
TextView REPUBLICDAYSTATUS,THALSENASTATUS,NAUSENASTATUS,   VAYUSENASTATUS,MOUNTCAMPSTATUS, TREKINGCAMPSTATUS
           , EKBHARATSTATUS, SPECIALNICSTATUS, LOCALREPUBLICCAMPSTATUS,CADETAPPEREDA,  CADETPASSEDA, CADETAPPEREDB
            ,CADETPASSEDB,CADETAPPEREDC,  CADETPASSEDC, BESTCADETAWARDSTATUS, CMSCHOLARSHIPSTATUS, CWCSCHOLARSHIPSTATUS
           , CMMEDALSTATUS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_details);
        Window window = getWindow();
        applicationController= (ApplicationController) getApplication();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      REPUBLICDAYSTATUS=findViewById(R.id.REPUBLICDAYSTATUS);
      THALSENASTATUS=findViewById(R.id.THALSENASTATUS);
      NAUSENASTATUS=findViewById(R.id.NAUSENASTATUS);
      VAYUSENASTATUS=findViewById(R.id.VAYUSENASTATUS);
      MOUNTCAMPSTATUS=findViewById(R.id.MOUNTCAMPSTATUS);
      TREKINGCAMPSTATUS=findViewById(R.id.TREKINGCAMPSTATUS);
      EKBHARATSTATUS=findViewById(R.id.EKBHARATSTATUS);
      SPECIALNICSTATUS=findViewById(R.id.SPECIALNICSTATUS);
      LOCALREPUBLICCAMPSTATUS=findViewById(R.id.LOCALREPUBLICCAMPSTATUS);
      CADETAPPEREDA=findViewById(R.id.CADETAPPEREDA);
      CADETPASSEDA=findViewById(R.id.CADETPASSEDA);
      CADETAPPEREDB=findViewById(R.id.CADETAPPEREDB);
      CADETPASSEDB=findViewById(R.id.CADETPASSEDB);
      CADETAPPEREDC=findViewById(R.id.CADETAPPEREDC);
      CADETPASSEDC=findViewById(R.id.CADETPASSEDC);
      BESTCADETAWARDSTATUS=findViewById(R.id.BESTCADETAWARDSTATUS);
      CMSCHOLARSHIPSTATUS=findViewById(R.id.CMSCHOLARSHIPSTATUS);
      CWCSCHOLARSHIPSTATUS=findViewById(R.id.CWCSCHOLARSHIPSTATUS);
      CMMEDALSTATUS=findViewById(R.id.CMMEDALSTATUS);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        Log.d("TAG", "onCreate: "+jsonObject);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<GetNCCParticipationDetailsModel> call= apiService.getNCCParticipationDetails(jsonObject);
        call.enqueue(new Callback<GetNCCParticipationDetailsModel>() {
            @Override
            public void onResponse(Call<GetNCCParticipationDetailsModel> call, Response<GetNCCParticipationDetailsModel> response) {
                GetNCCParticipationDetailsModel getNCCParticipationDetailsModel=response.body();
              REPUBLICDAYSTATUS.setText(getNCCParticipationDetailsModel.getRepublicdaystatus()==null?"":getNCCParticipationDetailsModel.getRepublicdaystatus());
              THALSENASTATUS.setText(getNCCParticipationDetailsModel.getThalsenastatus()==null?"":getNCCParticipationDetailsModel.getThalsenastatus());
              NAUSENASTATUS.setText(getNCCParticipationDetailsModel.getNausenastatus()==null?"":getNCCParticipationDetailsModel.getNausenastatus());
              VAYUSENASTATUS.setText(getNCCParticipationDetailsModel.getVayusenastatus()==null?"":getNCCParticipationDetailsModel.getVayusenastatus());
              MOUNTCAMPSTATUS.setText(getNCCParticipationDetailsModel.getMountcampstatus()==null?"":getNCCParticipationDetailsModel.getMountcampstatus());
              TREKINGCAMPSTATUS.setText(getNCCParticipationDetailsModel.getTrekingcampstatus()==null?"":getNCCParticipationDetailsModel.getTrekingcampstatus());
              EKBHARATSTATUS.setText(getNCCParticipationDetailsModel.getEkbharatstatus()==null?"":getNCCParticipationDetailsModel.getEkbharatstatus());
              SPECIALNICSTATUS.setText(getNCCParticipationDetailsModel.getSpecialnicstatus()==null?"":getNCCParticipationDetailsModel.getSpecialnicstatus());
              LOCALREPUBLICCAMPSTATUS.setText(getNCCParticipationDetailsModel.getLocalrepubliccampstatus()==null?"":getNCCParticipationDetailsModel.getLocalrepubliccampstatus());
              CADETAPPEREDA.setText(getNCCParticipationDetailsModel.getCadetappereda()==null?"":getNCCParticipationDetailsModel.getCadetappereda());
              CADETPASSEDA.setText(getNCCParticipationDetailsModel.getCadetpasseda()==null?"":getNCCParticipationDetailsModel.getCadetpasseda());
              CADETAPPEREDB.setText(getNCCParticipationDetailsModel.getCadetappereda()==null?"":getNCCParticipationDetailsModel.getCadetappereda());
              CADETPASSEDB.setText(getNCCParticipationDetailsModel.getCadetpasseda()==null?"":getNCCParticipationDetailsModel.getCadetpasseda());
              CADETAPPEREDC.setText(getNCCParticipationDetailsModel.getCadetapperedc()==null?"":getNCCParticipationDetailsModel.getCadetapperedc());
              CADETPASSEDC.setText(getNCCParticipationDetailsModel.getCadetpasseda()==null?"":getNCCParticipationDetailsModel.getCadetpasseda());
              BESTCADETAWARDSTATUS.setText(getNCCParticipationDetailsModel.getBestcadetawardstatus()==null?"":getNCCParticipationDetailsModel.getBestcadetawardstatus());
              CMSCHOLARSHIPSTATUS.setText(getNCCParticipationDetailsModel.getCmscholarshipstatus()==null?"":getNCCParticipationDetailsModel.getCmscholarshipstatus());
              CWCSCHOLARSHIPSTATUS.setText(getNCCParticipationDetailsModel.getCwcscholarshipstatus()==null?"":getNCCParticipationDetailsModel.getCwcscholarshipstatus());
              CMMEDALSTATUS.setText(getNCCParticipationDetailsModel.getCmmedalstatus()==null?"":getNCCParticipationDetailsModel.getCmmedalstatus());
            }

            @Override
            public void onFailure(Call<GetNCCParticipationDetailsModel> call, Throwable t) {

            }
        });

    }

}