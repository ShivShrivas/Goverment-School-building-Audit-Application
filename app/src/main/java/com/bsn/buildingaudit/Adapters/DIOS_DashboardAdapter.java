package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.DIOS.Diios_Panel_Under_School_dashboard;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class DIOS_DashboardAdapter extends RecyclerView.Adapter<DIOS_DashboardAdapter.dashboardRecViewHolder> {
    Context context;
    ArrayList<String> arrayListSchool;
    ArrayList<String> itemsCopy;
    public DIOS_DashboardAdapter(Context context, ArrayList<String> arrayListSchool) {
        this.context=context;
        this.arrayListSchool=arrayListSchool;
    }

    @NonNull
    @Override
    public dashboardRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_dios_itemcard,parent,false);
        return new dashboardRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dashboardRecViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.roomTypetxt.setText(arrayListSchool.get(position));
        holder.updateOntxt.setText("12-15-2022 02:30:28 PM");
        if (position%2==0){

            holder.penddingAndSDoneBtn.setImageDrawable(context.getDrawable(R.drawable.ic_right_icon));
        }
        else{
            holder.penddingAndSDoneBtn.setImageDrawable(context.getDrawable(R.drawable.ic_wron_icon));

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position%2==1){

                  context.startActivity(new Intent(context, Diios_Panel_Under_School_dashboard.class));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListSchool.size();
    }
    public void filter(String text) {
        arrayListSchool.clear();
        if(text.isEmpty()){
            arrayListSchool.addAll(itemsCopy);
        } else{
            text = text.toLowerCase();
            for(String item: itemsCopy){
                if(item.toLowerCase().contains(text)){
                    itemsCopy.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<String> filteredList) {
        arrayListSchool = filteredList;
        notifyDataSetChanged();
    }

    public class dashboardRecViewHolder extends RecyclerView.ViewHolder {
        TextView updateOntxt,roomTypetxt;
        ImageView penddingAndSDoneBtn;

        public dashboardRecViewHolder(@NonNull View itemView) {
            super(itemView);
            updateOntxt=itemView.findViewById(R.id.updateOntxt);
            roomTypetxt=itemView.findViewById(R.id.roomTypetxt);
            penddingAndSDoneBtn=itemView.findViewById(R.id.penddingAndSDoneBtn);
        }
    }
}

