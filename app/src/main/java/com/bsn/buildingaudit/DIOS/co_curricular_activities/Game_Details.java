package com.bsn.buildingaudit.DIOS.co_curricular_activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.AdapterGameDetails;
import com.bsn.buildingaudit.Adapters.GamesAdapter;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class Game_Details extends AppCompatActivity {
    RecyclerView recViewIndoorGames, recViewOutDoorGames;

    GamesAdapter adapter, adapter2;
    Dialog dialog;
    ImageView showDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recViewOutDoorGames = findViewById(R.id.recViewOutDoorGames);
        showDetails = findViewById(R.id.showDetails);
        recViewIndoorGames = findViewById(R.id.recViewIndoorGames);
        ArrayList<String> indoorGames = new ArrayList<>();
        indoorGames.add("CHESS");
        indoorGames.add("WRESTLING");
        indoorGames.add("BADMINTON");
        indoorGames.add("GYMNASTICS");
        indoorGames.add("YOGA");

        ArrayList<String> outDoorGames = new ArrayList<>();
        outDoorGames.add("HOCKEY");
        outDoorGames.add("ATHLETICS");
        outDoorGames.add("KABADDI");
        outDoorGames.add("CRICKET");
        outDoorGames.add("FOOTBALL");
        outDoorGames.add("VOLLYBALL");
        outDoorGames.add("BASKETBALL");
        outDoorGames.add("DISCUS");
        outDoorGames.add("HAMMER");
        outDoorGames.add("JEWELIN");

        recViewOutDoorGames.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recViewIndoorGames.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapter = new GamesAdapter(this, indoorGames);
        adapter2 = new GamesAdapter(this, outDoorGames);
        recViewIndoorGames.setAdapter(adapter);
        recViewOutDoorGames.setAdapter(adapter2);

        showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(Game_Details.this);
            }
        });
    }

    public void showDialog(Activity activity) {

        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.student_list_dialog);

        Button btndialog = (Button) dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("Aman Kumar");
        myImageNameList.add("Radhika Awasthi");
        myImageNameList.add("Prashant Gupta");
        myImageNameList.add("Zareen Khan");
        myImageNameList.add("Piyush Dubey");
        myImageNameList.add("Raj Bharti");
        myImageNameList.add("Shiv Sharma");
        myImageNameList.add("Rajesh Gaur");
        myImageNameList.add("Ritu Shrivastava");
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AdapterGameDetails adapterRe = new AdapterGameDetails(Game_Details.this, myImageNameList);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        dialog.show();
    }
}