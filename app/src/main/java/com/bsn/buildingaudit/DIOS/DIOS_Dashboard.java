package com.bsn.buildingaudit.DIOS;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.DIOS_DashboardAdapter;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class DIOS_Dashboard extends AppCompatActivity {
RecyclerView recyclerViewForDiosDashboard;
SearchView searchView;

DIOS_DashboardAdapter adapter;
    ArrayList<String> arrayListSchool=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dios_dashboard);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        arrayListSchool.add("PS K RAM NAGAR");
        arrayListSchool.add("SP BAL VIDYA MANDIR");
        arrayListSchool.add("PRI. V. HORA KACHHAR");
        arrayListSchool.add("POORV M. V. KURSAULI");
        arrayListSchool.add("PS RAM NAGAR II");
        arrayListSchool.add("B.M.P. SCHOOL PRI. V.");
        arrayListSchool.add("PRI. V. HORA BANGER");
        arrayListSchool.add("POORV M. V. MANDHANA I");
        arrayListSchool.add("POORV M. V. MANDHANA II");
        arrayListSchool.add("PRI. V. MANDHANA I");
        arrayListSchool.add("M.S.D.VID.M. JHS RAMDEVIPURAM");
        arrayListSchool.add("PRI. V. BAGDAUDHI KACHHAR");
        arrayListSchool.add("KANYA VIDHYALAY JH SCHOOL");
        arrayListSchool.add("SWAMI VIVEKA NAND PV LODHAR");
        arrayListSchool.add("RAM CHANDRA ADARSH M.V.MANDHNA");
        arrayListSchool.add("PRI. V. GORHA");
        arrayListSchool.add("NEW BAAN USHA DEEP P SCHOOL");
        arrayListSchool.add("MASTER MIND PUBLIC SCHOOL");
        arrayListSchool.add("PT. SATISH CHANDRA C.V. MANDIR");
        arrayListSchool.add("R PATAP VIDYALAY MANDHANA");
        arrayListSchool.add("PRI. V. LODHER");
        arrayListSchool.add("UPS HORA KACHHAR");
        arrayListSchool.add("PRI. V. MANDHANA II");
        arrayListSchool.add("PRI. V. PERGAHI");
        arrayListSchool.add("ACHARYA KISHORI DAS S. B. V.");
        arrayListSchool.add("UPS HORA BANGAR");
        arrayListSchool.add("CAMBREGE SCHOOL");
        arrayListSchool.add("PRI. V. KURSAULI");
        arrayListSchool.add("PS BAGDAUNDHI BANGAR");
        arrayListSchool.add("MATRI BHARTI B. J. H.S. MANDHANA");
        arrayListSchool.add("B.P.M.G. INTER COLLEGE");
        arrayListSchool.add("N. V. USADEEP PUBLIC SCHOOL");
        arrayListSchool.add("PRI. V. CHAKRATANPUR");
        arrayListSchool.add("PRI. V. RAM NAGAR I");
        arrayListSchool.add("S. P. B. J. H. S. MANDHANA");
        recyclerViewForDiosDashboard=findViewById(R.id.recyclerViewForDiosDashboard);
        searchView=findViewById(R.id.searchView);
        recyclerViewForDiosDashboard.setLayoutManager(new LinearLayoutManager(this));
        adapter=new DIOS_DashboardAdapter(this,arrayListSchool);
        recyclerViewForDiosDashboard.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               filter(newText);
                return true;
            }
        });

    }

    private void filter(String text) {
        ArrayList<String> filteredList = new ArrayList<>();


        for (String item : arrayListSchool) {
            if ( item.contains(text.toLowerCase())) {
                filteredList.add(item);
            }else if( item.contains(text.toUpperCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
        adapter.notifyDataSetChanged();
    }

}