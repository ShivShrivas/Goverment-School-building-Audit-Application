package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class StaffTrainingAdapter extends RecyclerView.Adapter<StaffTrainingAdapter.StafftrainingViewHolder> {
    Context context;
    ArrayList<String> arrayList;
    public StaffTrainingAdapter(Context context, ArrayList<String> arrayList) {
        this.context=context;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public StafftrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.training_card,parent,false);
        return new StafftrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StafftrainingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StafftrainingViewHolder extends RecyclerView.ViewHolder {
        public StafftrainingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
