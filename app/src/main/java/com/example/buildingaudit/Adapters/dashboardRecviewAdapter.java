package com.example.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Activies.UpdateDetailTypeOne;
import com.example.buildingaudit.Activies.UpdateDetailsTypeFour;
import com.example.buildingaudit.Activies.UpdateDetailsTypeTwo;
import com.example.buildingaudit.Activies.UpdatedetailsTypeThree;
import com.example.buildingaudit.DashBoard;
import com.example.buildingaudit.Model.RecModel;
import com.example.buildingaudit.R;

import java.util.ArrayList;

public class dashboardRecviewAdapter extends RecyclerView.Adapter<dashboardRecviewAdapter.dashboardRecViewHolder> {
    Context context;
    ArrayList<RecModel> arrayList=new ArrayList();
    public dashboardRecviewAdapter(Context context, ArrayList<RecModel> arrayList) {
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
        holder.roomTypetxt.setText(arrayList.get(position).getRoomtype());
        holder.updateOntxt.setText(arrayList.get(position).getUpdatedOn());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position%4==0){
                    context.startActivity(new Intent(context, UpdateDetailTypeOne.class));
                }else if(position%4==1){
                    context.startActivity(new Intent(context, UpdateDetailsTypeTwo.class));
                }else if(position%4==3){
                    context.startActivity(new Intent(context, UpdatedetailsTypeThree.class));
                }else context.startActivity(new Intent(context, UpdateDetailsTypeFour.class));


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
