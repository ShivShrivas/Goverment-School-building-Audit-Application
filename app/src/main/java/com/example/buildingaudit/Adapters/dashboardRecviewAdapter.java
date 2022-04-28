package com.example.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Activies.UpdateDetailTypeOne;
import com.example.buildingaudit.Activies.UpdateDetailsBioMetric;
import com.example.buildingaudit.Activies.UpdateDetailsBoundryWall;
import com.example.buildingaudit.Activies.UpdateDetailsBoysToilet;
import com.example.buildingaudit.Activies.UpdateDetailsCCTV;
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
import com.example.buildingaudit.Model.GetAllRoomsList;
import com.example.buildingaudit.Model.RecModel;
import com.example.buildingaudit.R;

import java.util.ArrayList;
import java.util.List;

public class dashboardRecviewAdapter extends RecyclerView.Adapter<dashboardRecviewAdapter.dashboardRecViewHolder> {
    Context context;
    List<GetAllRoomsList> arrayList=new ArrayList();
    public dashboardRecviewAdapter(Context context, List<GetAllRoomsList> arrayList) {
   this.context=context;
   this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public dashboardRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_review_itemcard,parent,false);
        return new dashboardRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dashboardRecViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.roomTypetxt.setText(arrayList.get(position).getParamName());
        if (arrayList.get(position).getLastUpdateDateTime().toString().equals("0")){
            holder.updateOntxt.setText("Not Available");
        }else {
            holder.updateOntxt.setText(arrayList.get(position).getLastUpdateDateTime());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (arrayList.get(position).getParamId().toString()){
                    case "1":
                        context.startActivity(new Intent(context, UpdateDetailTypeOne.class));
                        break;
                        case "2":
                        context.startActivity(new Intent(context, UpdateDetailsTypeTwo.class));
                        break;
                        case "3":
                        context.startActivity(new Intent(context, UpdatedetailsTypeThree.class));
                        break;
                        case "4":
                        context.startActivity(new Intent(context, UpdateDetailsTypeFour.class));
                        break;
                        case "5":
                        context.startActivity(new Intent(context, UpdateDetailsPlayground.class));
                        break;
                        case "6":
                        context.startActivity(new Intent(context, UpdateDetailsGym.class));
                        break;
                        case "7":
                        context.startActivity(new Intent(context, UpdateDetailsDrinkingWater.class));
                        break;
                        case "8":
                        context.startActivity(new Intent(context, UpdateDetailsSmartClass.class));
                        break;
                        case "9":
                        context.startActivity(new Intent(context, UpdateDetailsBioMetric.class));
                        break;
                        case "10":
                        context.startActivity(new Intent(context, UpdateDetailsCCTV.class));
                        break;
                        case "11":
                        context.startActivity(new Intent(context, UpdateDetailsElectricityArrangment.class));
                        break;
                        case "12":
                        context.startActivity(new Intent(context, UpdateDetailsFireFighting.class));
                        break;
                        case "13":
                        context.startActivity(new Intent(context, UpdateDetailsRainHarvest.class));
                        break;
                        case "14":
                        context.startActivity(new Intent(context, UpdateDetailsSolarPanel.class));
                        break;

                        case "15":
                        context.startActivity(new Intent(context, UpdateDetailsBoundryWall.class));
                        break;

                        case "16":
                        context.startActivity(new Intent(context, UpdateDetailsBoysToilet.class));
                        break;

                        case "17":
                        context.startActivity(new Intent(context, UpdateDetailsGirlsToilet.class));
                        break;

                        case "18":
                        context.startActivity(new Intent(context, UpdateDetailsFurnitures.class));
                        break;

                        case "19":
                        context.startActivity(new Intent(context, UpdateDetailsComputerlab.class));
                        break;

                        case "20":
                        context.startActivity(new Intent(context, UpdateDetailsOfExtraThings.class));
                        break;

                        case "21":
                        context.startActivity(new Intent(context, UpdateDetailsCycleStand.class));
                        break;

                        case "22":
                        context.startActivity(new Intent(context, UpdateDetailsSoundSystem.class));
                        break;

                        case "23":
                        context.startActivity(new Intent(context, UpdateDetailsMultipurposeHall.class));
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class dashboardRecViewHolder extends RecyclerView.ViewHolder {
        TextView updateOntxt,roomTypetxt;

        public dashboardRecViewHolder(@NonNull View itemView) {
            super(itemView);
            updateOntxt=itemView.findViewById(R.id.updateOntxt);
            roomTypetxt=itemView.findViewById(R.id.roomTypetxt);
        }
    }
}
