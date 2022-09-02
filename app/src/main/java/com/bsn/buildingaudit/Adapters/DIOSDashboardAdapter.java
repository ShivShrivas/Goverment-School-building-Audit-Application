package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.GetDashboardMenuDataModel;
import com.bsn.buildingaudit.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DIOSDashboardAdapter extends RecyclerView.Adapter<DIOSDashboardAdapter.DiosMenuViewHolder> {
    Context context;
    String InspectionId;
    ArrayList<GetDashboardMenuDataModel> arrayList;
    public DIOSDashboardAdapter(Context context, ArrayList<GetDashboardMenuDataModel> arrayList,String InspectionId) {
        this.context=context;
        this.arrayList=arrayList;
        this.InspectionId=InspectionId;

    }

    public DIOSDashboardAdapter(Context context, ArrayList<GetDashboardMenuDataModel> arrayList) {
        this.context=context;
        this.arrayList=arrayList;

    }



    @NonNull
    @Override
    public DiosMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dios_dashboard_menu_item_card,parent,false);
        return new DiosMenuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DiosMenuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.diosDashboardMenuItemTxt.setText(arrayList.get(position).getMenuname());
        Glide.with(holder.diosDashboardMenuItemImg).load(arrayList.get(position).getAppIcon().trim()).into(holder.diosDashboardMenuItemImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.d("TAG", "onClick: "+arrayList.get(position).getAppClassName());
                    Intent i=new Intent(context, Class.forName(arrayList.get(position).getAppClassName()));
                        i.putExtra("ParentID",arrayList.get(position).getMenuid().toString());
                        i.putExtra("ParamId",arrayList.get(position).getParamid().toString());
                        i.putExtra("InspectionId",InspectionId.toString());
                    context.startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DiosMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView diosDashboardMenuItemImg;
        TextView diosDashboardMenuItemTxt;
        public DiosMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            diosDashboardMenuItemTxt=itemView.findViewById(R.id.diosDashboardMenuItemTxt);
            diosDashboardMenuItemImg=itemView.findViewById(R.id.diosDashboardMenuItemImg);

        }
    }
}
