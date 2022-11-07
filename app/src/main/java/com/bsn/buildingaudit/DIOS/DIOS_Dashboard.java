package com.bsn.buildingaudit.DIOS;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.DIOS_DashboardAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.SchoolListModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DIOS_Dashboard extends AppCompatActivity {
RecyclerView recyclerViewForDiosDashboard;
SearchView searchView;
    public static ApplicationController applicationController;
    private TextView textView8;
    DIOS_DashboardAdapter adapter;
    ArrayList<SchoolListModel> arrayListSchool=new ArrayList<>();

    public static boolean setSchoolIdInContoller(Integer schoolid) {
        applicationController.setDiosLocalSchoolId(schoolid.toString());
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dios_dashboard);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        recyclerViewForDiosDashboard=findViewById(R.id.recyclerViewForDiosDashboard);
        searchView=findViewById(R.id.searchView);
        recyclerViewForDiosDashboard.setLayoutManager(new LinearLayoutManager(this));
        textView8=findViewById(R.id.textView8);
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("DistrictID",applicationController.getDistid());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<SchoolListModel>> call=apiService.getSchoolListDistricWise(jsonObject);
        call.enqueue(new Callback<List<SchoolListModel>>() {
            @Override
            public void onResponse(Call<List<SchoolListModel>> call, Response<List<SchoolListModel>> response) {
               
                arrayListSchool.addAll(response.body());
                adapter=new DIOS_DashboardAdapter(DIOS_Dashboard.this,arrayListSchool);
                recyclerViewForDiosDashboard.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SchoolListModel>> call, Throwable t) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               //filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              // filter(newText);
                return true;
            }
        });



    }

//    private void filter(String text) {
//        ArrayList<String> filteredList = new ArrayList<>();
//
//
//        for (String item : arrayListSchool) {
//            if ( item.contains(text.toLowerCase())) {
//                filteredList.add(item);
//            }else if( item.contains(text.toUpperCase())) {
//                filteredList.add(item);
//            }
//        }
//        adapter.filterList(filteredList);
//        adapter.notifyDataSetChanged();
//    }

}