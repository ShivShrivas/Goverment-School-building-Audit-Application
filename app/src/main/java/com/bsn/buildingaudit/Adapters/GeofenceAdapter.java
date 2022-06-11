package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.GeoTags;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class GeofenceAdapter extends RecyclerView.Adapter<GeofenceAdapter.GeoViewHolder> {
    Context context;
    ArrayList<GeoTags> arrayList;
    public GeofenceAdapter(Context context, ArrayList<GeoTags> arrayList) {
        this.context=context;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public GeoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.geofench_card_layout,parent,false);
        return new GeoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoViewHolder holder, int position) {
        holder.txtLat.setText(arrayList.get(position).getLattitude());
        holder.txtLongt.setText(arrayList.get(position).getLongitute());
        holder.txtDateTime.setText(arrayList.get(position).getDateAndTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GeoViewHolder extends RecyclerView.ViewHolder {
        TextView txtDateTime,txtLongt,txtLat;
        public GeoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDateTime=itemView.findViewById(R.id.txtDateTime);
            txtLat=itemView.findViewById(R.id.txtLat);
            txtLongt=itemView.findViewById(R.id.txtLongt);
        }
    }
}
