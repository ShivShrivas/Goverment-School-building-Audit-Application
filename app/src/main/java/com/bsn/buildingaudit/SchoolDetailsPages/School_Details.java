package com.bsn.buildingaudit.SchoolDetailsPages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.ClassesRecviewAdapter;
import com.bsn.buildingaudit.Adapters.SubjectRecviewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.ClassDatum;
import com.bsn.buildingaudit.Model.SchoolDetailsModel;
import com.bsn.buildingaudit.Model.SubjectDatum;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class School_Details extends AppCompatActivity {
    ApplicationController applicationController;
    TextView textView8,schoolAddress,schoolName;
ArrayList<String> subjectSixToEight=new ArrayList<>();
ArrayList<String> subjectNineToTenth=new ArrayList<>();
ArrayList<String> subjectEleventhToTwelveth=new ArrayList<>();
CardView cardView32,cardView35,cardView36;
TextView schoolAddTxt,pincodeTxt,mobTxt,dateOfRegisTxt,authorityContactTxt,cateGoryOfSchoolTxt,affiliationTxt,designationTxt,authorityTxt;
   List<ClassDatum> classesArrayList =new ArrayList<>();
   List<SubjectDatum> subjectsArrayList =new ArrayList<>();
   SchoolDetailsModel schoolDetailsList;
RecyclerView recViewForPermittedSubjects,recViewForRunningClasses,recViewForPermittedSubjectsElevenTwelve,recViewForPermittedSubjectsNinthToTenth,recViewForPermittedSubjectsSixthToEight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);
        applicationController= (ApplicationController) getApplication();
        GridLayoutManager layoutManager=new GridLayoutManager(this,2){
            @Override
            public boolean canScrollVertically() {
                return  false;
            }
        };
        schoolDetailsList=new SchoolDetailsModel();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        recViewForRunningClasses=findViewById(R.id.recViewForRunningClasses);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        schoolAddTxt=findViewById(R.id.schoolAddTxt);
        mobTxt=findViewById(R.id.mobTxt);
        pincodeTxt=findViewById(R.id.pincodeTxt);
        dateOfRegisTxt=findViewById(R.id.dateOfRegisTxt);
        cateGoryOfSchoolTxt=findViewById(R.id.cateGoryOfSchoolTxt);
        affiliationTxt=findViewById(R.id.affiliationTxt);
        authorityTxt=findViewById(R.id.authorityTxt);
        authorityContactTxt=findViewById(R.id.authorityContactTxt);
        cardView36=findViewById(R.id.cardView36);
        cardView35=findViewById(R.id.cardView35);
        cardView32=findViewById(R.id.cardView32);
        designationTxt=findViewById(R.id.designationTxt);
        recViewForPermittedSubjectsNinthToTenth=findViewById(R.id.recViewForPermittedSubjectsNinthToTenth);
        recViewForPermittedSubjectsElevenTwelve=findViewById(R.id.recViewForPermittedSubjectsElevenTwelve);
        recViewForPermittedSubjectsSixthToEight=findViewById(R.id.recViewForPermittedSubjectsSixthToEight);
        GridLayoutManager layoutManager1=new GridLayoutManager(School_Details.this,2){
            @Override
            public boolean canScrollVertically() {
                return  false;
            }
        };
        GridLayoutManager layoutManager2=new GridLayoutManager(School_Details.this,2){
            @Override
            public boolean canScrollVertically() {
                return  false;
            }
        };
        GridLayoutManager layoutManager3=new GridLayoutManager(School_Details.this,2){
            @Override
            public boolean canScrollVertically() {
                return  false;
            }
        };

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "onCreate: "+paraSchoolRequestJson("2033",applicationController.getPeriodID()));
        Call<SchoolDetailsModel> call=apiService.getSchoolDetailsForDIOS(paraSchoolRequestJson(applicationController.getSchoolId(),applicationController.getPeriodID()));
        call.enqueue(new Callback<SchoolDetailsModel>() {
            @Override
            public void onResponse(Call<SchoolDetailsModel> call, Response<SchoolDetailsModel> response) {
                Log.d("TAG", "onResponse: "+response.body());


                 schoolDetailsList=response.body();
                schoolAddTxt.setText(schoolDetailsList.getAddress()==null?"Not Avaialable":schoolDetailsList.getAddress());
                mobTxt.setText(schoolDetailsList.getPhoneNo());
                pincodeTxt.setText(schoolDetailsList.getPinCode().toString());
                dateOfRegisTxt.setText(schoolDetailsList.getDor().split("T")[0]);
                cateGoryOfSchoolTxt.setText(schoolDetailsList.getCategory());
                affiliationTxt.setText(schoolDetailsList.getAffiliationClass());
                authorityTxt.setText(schoolDetailsList.getAuthorityName());
                authorityContactTxt.setText(schoolDetailsList.getContactNo());
                designationTxt.setText(schoolDetailsList.getStaffDesignation());
                classesArrayList=schoolDetailsList.getClassData();
                subjectsArrayList=schoolDetailsList.getSubjectData();
                for (int i=0;i<subjectsArrayList.size();i++){
                    if (subjectsArrayList.get(i).getClassID()==15 ||subjectsArrayList.get(i).getClassID()==12  ){
                        subjectEleventhToTwelveth.add(subjectsArrayList.get(i).getSubjectName());
                        HashSet hs = new HashSet();

                        hs.addAll(subjectEleventhToTwelveth); // demoArrayList= name of arrayList from which u want to remove duplicates

                        subjectEleventhToTwelveth.clear();
                        subjectEleventhToTwelveth.addAll(hs);
                        recViewForPermittedSubjectsElevenTwelve.setLayoutManager(layoutManager1);
                        Log.d("TAG", "onResponse: "+subjectEleventhToTwelveth.size()+"//");
                        if (subjectEleventhToTwelveth.isEmpty()){
                            cardView36.setVisibility(View.GONE);
                        }else{
                            cardView36.setVisibility(View.VISIBLE);
                            recViewForPermittedSubjectsElevenTwelve.setAdapter(new SubjectRecviewAdapter(School_Details.this,subjectEleventhToTwelveth));
                        }

                    }else if(subjectsArrayList.get(i).getClassID()==11 ||subjectsArrayList.get(i).getClassID()==10  ){
                        subjectNineToTenth.add(subjectsArrayList.get(i).getSubjectName());
                        HashSet hs = new HashSet();

                        hs.addAll(subjectNineToTenth); // demoArrayList= name of arrayList from which u want to remove duplicates

                        subjectNineToTenth.clear();
                        subjectNineToTenth.addAll(hs);
                        recViewForPermittedSubjectsNinthToTenth.setLayoutManager(layoutManager2);
                        if (subjectNineToTenth.isEmpty()){
                            cardView35.setVisibility(View.GONE);
                        }else{
                            cardView35.setVisibility(View.VISIBLE);
                            recViewForPermittedSubjectsNinthToTenth.setAdapter(new SubjectRecviewAdapter(School_Details.this,subjectNineToTenth));
                        }

                    }else{
                        subjectSixToEight.add(subjectsArrayList.get(i).getSubjectName());
                        HashSet hs = new HashSet();

                        hs.addAll(subjectSixToEight); // demoArrayList= name of arrayList from which u want to remove duplicates

                        subjectSixToEight.clear();
                        subjectSixToEight.addAll(hs);
                        recViewForPermittedSubjectsSixthToEight.setLayoutManager(layoutManager3);
                        if (subjectSixToEight.isEmpty()){
                                cardView32.setVisibility(View.GONE);
                        }else{
                            cardView32.setVisibility(View.VISIBLE);

                            recViewForPermittedSubjectsSixthToEight.setAdapter(new SubjectRecviewAdapter(School_Details.this,subjectSixToEight));

                        }


                    }
                }

                recViewForRunningClasses.setLayoutManager(layoutManager);
                recViewForRunningClasses.setAdapter(new ClassesRecviewAdapter(School_Details.this,classesArrayList));
            }

            @Override
            public void onFailure(Call<SchoolDetailsModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });

        ArrayList<String> subjectArray=new ArrayList<>();
        subjectArray.add("Hindi");
        subjectArray.add("ENGLISH");
        subjectArray.add("SANSKRIT");
        subjectArray.add("SCIENCE");
        subjectArray.add("Mathematics");
        subjectArray.add("DRAWING");
        subjectArray.add("HOME SCIENCE");
        subjectArray.add("SOCIAL SCIENCE");
        subjectArray.add("COMPUTER SCIENCE");






    }

    private JsonObject paraSchoolRequestJson(String schoolId, String periodID) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        return jsonObject;
    }
}