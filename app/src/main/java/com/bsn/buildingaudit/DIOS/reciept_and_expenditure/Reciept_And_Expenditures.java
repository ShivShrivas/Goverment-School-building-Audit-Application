package com.bsn.buildingaudit.DIOS.reciept_and_expenditure;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.RecieptAndExpenditureAdapter;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class Reciept_And_Expenditures extends AppCompatActivity {
RecyclerView recyclerViewRecieptAndExpenditure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept_and_expenditures);
        recyclerViewRecieptAndExpenditure=findViewById(R.id.recyclerViewRecieptAndExpenditure);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("क्रीडा");
        arrayList.add("विज्ञान");
        arrayList.add("स्काउटिंग");
        arrayList.add("रेडक्रास");
        arrayList.add("निर्धन शुल्क");
        arrayList.add("जलपान");
        arrayList.add("वाचनालय");
        arrayList.add("निश्रव्य दृश्य");
        arrayList.add("कला");
        arrayList.add("पत्रिका");
        arrayList.add("प्रगति पत्र");
        arrayList.add("अनुपस्थिति दण्ड");
        arrayList.add("गृह परीक्षा");
        arrayList.add("पत्रिका");

        recyclerViewRecieptAndExpenditure.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRecieptAndExpenditure.setAdapter(new RecieptAndExpenditureAdapter(this,arrayList));


    }
}