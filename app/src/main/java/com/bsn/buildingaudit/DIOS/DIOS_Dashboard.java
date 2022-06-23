package com.bsn.buildingaudit.DIOS;

import android.os.Bundle;
import android.util.Log;
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
DIOS_DashboardAdapter adapter;
private TextView textView8;
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
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("DistrictID",applicationController.getDistid());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        recyclerViewForDiosDashboard=findViewById(R.id.recyclerViewForDiosDashboard);
        searchView=findViewById(R.id.searchView);
        textView8=findViewById(R.id.textView8);
        recyclerViewForDiosDashboard.setLayoutManager(new LinearLayoutManager(this));
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());

        Call<List<SchoolListModel>> call=apiService.getSchoolListDistricWise(jsonObject);
        call.enqueue(new Callback<List<SchoolListModel>>() {
            @Override
            public void onResponse(Call<List<SchoolListModel>> call, Response<List<SchoolListModel>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                arrayListSchool.addAll(response.body());
                adapter=new DIOS_DashboardAdapter(DIOS_Dashboard.this,arrayListSchool);
                recyclerViewForDiosDashboard.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SchoolListModel>> call, Throwable t) {

            }
        });
//
//        arrayListSchool.add("PS K RAM NAGAR");
//        arrayListSchool.add("SP BAL VIDYA MANDIR");
//        arrayListSchool.add("PRI. V. HORA KACHHAR");
//        arrayListSchool.add("POORV M. V. KURSAULI");
//        arrayListSchool.add("PS RAM NAGAR II");
//        arrayListSchool.add("B.M.P. SCHOOL PRI. V.");
//        arrayListSchool.add("PRI. V. HORA BANGER");
//        arrayListSchool.add("POORV M. V. MANDHANA I");
//        arrayListSchool.add("POORV M. V. MANDHANA II");
//        arrayListSchool.add("PRI. V. MANDHANA I");
//        arrayListSchool.add("M.S.D.VID.M. JHS RAMDEVIPURAM");
//        arrayListSchool.add("PRI. V. BAGDAUDHI KACHHAR");
//        arrayListSchool.add("KANYA VIDHYALAY JH SCHOOL");
//        arrayListSchool.add("SWAMI VIVEKA NAND PV LODHAR");
//        arrayListSchool.add("RAM CHANDRA ADARSH M.V.MANDHNA");
//        arrayListSchool.add("PRI. V. GORHA");
//        arrayListSchool.add("NEW BAAN USHA DEEP P SCHOOL");
//        arrayListSchool.add("MASTER MIND PUBLIC SCHOOL");
//        arrayListSchool.add("PT. SATISH CHANDRA C.V. MANDIR");
//        arrayListSchool.add("R PATAP VIDYALAY MANDHANA");
//        arrayListSchool.add("PRI. V. LODHER");
//        arrayListSchool.add("UPS HORA KACHHAR");
//        arrayListSchool.add("PRI. V. MANDHANA II");
//        arrayListSchool.add("PRI. V. PERGAHI");
//        arrayListSchool.add("ACHARYA KISHORI DAS S. B. V.");
//        arrayListSchool.add("UPS HORA BANGAR");
//        arrayListSchool.add("CAMBREGE SCHOOL");
//        arrayListSchool.add("PRI. V. KURSAULI");
//        arrayListSchool.add("PS BAGDAUNDHI BANGAR");
//        arrayListSchool.add("MATRI BHARTI B. J. H.S. MANDHANA");
//        arrayListSchool.add("B.P.M.G. INTER COLLEGE");
//        arrayListSchool.add("N. V. USADEEP PUBLIC SCHOOL");
//        arrayListSchool.add("PRI. V. CHAKRATANPUR");
//        arrayListSchool.add("PRI. V. RAM NAGAR I");
//        arrayListSchool.add("S. P. B. J. H. S. MANDHANA");

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