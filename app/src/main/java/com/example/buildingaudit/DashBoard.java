package com.example.buildingaudit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.buildingaudit.Activies.UpdateDetailTypeOne;
import com.example.buildingaudit.Activies.UpdateDetailsBioMetric;
import com.example.buildingaudit.Activies.UpdateDetailsBoundryWall;
import com.example.buildingaudit.Activies.UpdateDetailsBoysToilet;
import com.example.buildingaudit.Activies.UpdateDetailsCCTV;
import com.example.buildingaudit.Activies.UpdateDetailsCWSNRamp;
import com.example.buildingaudit.Activies.UpdateDetailsComputerlab;
import com.example.buildingaudit.Activies.UpdateDetailsCycleStand;
import com.example.buildingaudit.Activies.UpdateDetailsDrinkingWater;
import com.example.buildingaudit.Activies.UpdateDetailsElectricityArrangment;
import com.example.buildingaudit.Activies.UpdateDetailsFireFighting;
import com.example.buildingaudit.Activies.UpdateDetailsFurnitures;
import com.example.buildingaudit.Activies.UpdateDetailsGirlsToilet;
import com.example.buildingaudit.Activies.UpdateDetailsGym;
import com.example.buildingaudit.Activies.UpdateDetailsMultipurposeHall;
import com.example.buildingaudit.Activies.UpdateDetailsOfExtraThings;
import com.example.buildingaudit.Activies.UpdateDetailsPlayground;
import com.example.buildingaudit.Activies.UpdateDetailsRainHarvest;
import com.example.buildingaudit.Activies.UpdateDetailsSmartClass;
import com.example.buildingaudit.Activies.UpdateDetailsSolarPanel;
import com.example.buildingaudit.Activies.UpdateDetailsSoundSystem;
import com.example.buildingaudit.Activies.UpdateDetailsTypeFour;
import com.example.buildingaudit.Activies.UpdateDetailsTypeTwo;
import com.example.buildingaudit.Activies.UpdatedetailsTypeThree;
import com.example.buildingaudit.Adapters.dashboardRecviewAdapter;
import com.example.buildingaudit.Model.RecModel;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    RecyclerView dashboardRecview;
    LinearLayout dashBoardCWSNRamp,dashBoardCCTV,dashBoardSoundSystem, dashBoardMultipurposeHall,dashBoardCycleStand,dashBoardExtraThings,dashBoardDrinkingWater,dashBoardGym,dashBoardPlayground,dashBoardLibrary,dashBoardPracticalLabs,staffRoom,dashboardClassRoom,
            dashBoardSmartClass,dashBoardComputerLab,dashBoardFurnitures,dashBoardBioMetric,dashBoardElectricityArrangement,
            dashBoardFireFighting,dashBoardRainHarvestingAndRamp, dashBoardSolarPanel,dashBoardBoysToilet,dashBoardGirlsToilet,dashBoardBoundaryWall;




    dashboardRecviewAdapter adapter;
    ArrayList<RecModel> arrayList=new ArrayList<>();
    String[] arrayListRoomType= {"Classroom Details","Staff Room","Practical Labs","Library Room","Playground","Gym",
            "Drinking Water","Smart Class","Computer Lab","Furnitures","Bio Metric And CCTV",
            "Electricity Arrangement","Fire Fighting","Rain Harvesting And CWSN Ramp","Solar Panel And Boundary Wall","Boys Toilet","Girls Toilet"};
    String[] UpdatedOn= {"12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM",
            "13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM",
            "12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM",
            "12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM",
            "13 Mar 2022 02:42 PM","12 mar 2022 02:42 PM","13 Mar 2022 02:42 PM","13 Mar 2022 02:42 PM","13 Mar 2022 02:42 PM"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("School Dashboard");
      //  dashboardRecview=findViewById(R.id.dashboardRecview);
        dashBoardGirlsToilet=findViewById(R.id.dashBoardGirlsToilet);
        dashBoardCCTV=findViewById(R.id.dashBoardCCTV);
        dashBoardSoundSystem=findViewById(R.id.dashBoardSoundSystem);
        dashBoardMultipurposeHall=findViewById(R.id.dashBoardMultipurposeHall);
        dashBoardCycleStand=findViewById(R.id.dashBoardCycleStand);
        dashBoardBoysToilet=findViewById(R.id.dashBoardBoysToilet);
        dashBoardSolarPanel =findViewById(R.id.dashBoardSolarPanel);
        dashBoardBoundaryWall=findViewById(R.id.dashBoardBoundaryWall);
        dashBoardCWSNRamp=findViewById(R.id.dashBoardCWSNRamp);
        dashBoardRainHarvestingAndRamp=findViewById(R.id.dashBoardRainHarvestingAndRamp);
        dashBoardFireFighting=findViewById(R.id.dashBoardFireFighting);
        dashBoardElectricityArrangement=findViewById(R.id.dashBoardElectricityArrangement);
        dashBoardBioMetric=findViewById(R.id.dashBoardBioMetric);
        dashBoardFurnitures=findViewById(R.id.dashBoardFurnitures);
        dashBoardComputerLab=findViewById(R.id.dashBoardComputerLab);
        dashBoardSmartClass=findViewById(R.id.dashBoardSmartClass);
        dashboardClassRoom=findViewById(R.id.dashboardClassRoom);
        staffRoom=findViewById(R.id.staffRoom);
        dashBoardPracticalLabs=findViewById(R.id.dashBoardPracticalLabs);
        dashBoardLibrary=findViewById(R.id.dashBoardLibrary);
        dashBoardPlayground=findViewById(R.id.dashBoardPlayground);
        dashBoardGym=findViewById(R.id.dashBoardGym);
        dashBoardDrinkingWater=findViewById(R.id.dashBoardDrinkingWater);
        dashBoardExtraThings=findViewById(R.id.dashBoardWifi);


        dashBoardMultipurposeHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsMultipurposeHall.class));
            }
        });

        dashBoardSoundSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsSoundSystem.class));
            }
        });
        dashBoardCCTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsCCTV.class));
            }
        });   dashBoardCWSNRamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsCWSNRamp.class));
            }
        });

        dashBoardGirlsToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsGirlsToilet.class));
            }
        });
        dashBoardCycleStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsCycleStand.class));
            }
        });
        dashBoardBoundaryWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsBoundryWall.class));
            }
        });
        dashBoardExtraThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsOfExtraThings.class));
            }
        });
                dashBoardBoysToilet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsBoysToilet.class));
                    }
                });
        dashBoardSolarPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsSolarPanel.class));
            }
        });
                dashBoardRainHarvestingAndRamp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsRainHarvest.class));
                    }
                });
        dashBoardFireFighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsFireFighting.class));
            }
        });
                dashBoardElectricityArrangement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsElectricityArrangment.class));
                    }
                });
        dashBoardBioMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsBioMetric.class));
            }
        });
                dashBoardFurnitures.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsFurnitures.class));
                    }
                });
        dashBoardComputerLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsComputerlab.class));
            }
        });
                dashBoardSmartClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsSmartClass.class));
                    }
                });
        dashboardClassRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailTypeOne.class));
            }
        });
                staffRoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsTypeTwo.class));
                    }
                });
        dashBoardPracticalLabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdatedetailsTypeThree.class));
            }
        });
                dashBoardLibrary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsTypeFour.class));
                    }
                });
        dashBoardPlayground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsPlayground.class));
            }
        });
                dashBoardGym.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoard.this, UpdateDetailsGym.class));
                    }
                });
        dashBoardDrinkingWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UpdateDetailsDrinkingWater.class));
            }
        });
    }

}