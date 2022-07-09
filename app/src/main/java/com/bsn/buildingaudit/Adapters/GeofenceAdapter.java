package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public void onBindViewHolder(@NonNull GeoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUri = "http://maps.google.com/maps?q=loc:" + arrayList.get(position).getLattitude() + "," + arrayList.get(position).getLongitute() + " (" + "Your Location of This point" + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                context.startActivity(intent);
            }
        });
        holder.txtDateTime.setText(arrayList.get(position).getDateAndTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GeoViewHolder extends RecyclerView.ViewHolder {
        TextView txtDateTime;
        ImageView my_location;
        public GeoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDateTime=itemView.findViewById(R.id.txtDateTime);
            my_location=itemView.findViewById(R.id.my_location);
        }
    }
}
