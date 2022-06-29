package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.StaffSanctionAndWorkingModel;
import com.bsn.buildingaudit.R;

import java.util.List;

public class StaffSanctionAndWorkingAdapter extends RecyclerView.Adapter<StaffSanctionAndWorkingAdapter.StaffSanctionAndWorkingViewHolder> {
    Context context;
    List<StaffSanctionAndWorkingModel> arrayList;
    public StaffSanctionAndWorkingAdapter(Context context, List<StaffSanctionAndWorkingModel> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public StaffSanctionAndWorkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_sanction_working_item_card,parent,false);
        return new StaffSanctionAndWorkingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffSanctionAndWorkingViewHolder holder, int position) {
        holder.noOfAttachedStaff.setText(arrayList.get(position).getNoOfAttachedStaff().toString());
        holder.noOfWorkingStaff.setText(arrayList.get(position).getNoOfWorkingStaff().toString());
        holder.noOfSanctionPosts.setText(arrayList.get(position).getSanctionedPost().toString());
        holder.noOfPostedStaff.setText(arrayList.get(position).getNoOfPostedStaff().toString());
        holder.designamtionName.setText(arrayList.get(position).getStaffDesignation().toString());
        holder.noOfVacantPost.setText(arrayList.get(position).getNoOfVacantPost().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StaffSanctionAndWorkingViewHolder extends RecyclerView.ViewHolder {
        TextView noOfWorkingStaff,noOfAttachedStaff,noOfPostedStaff,noOfSanctionPosts,designamtionName,noOfVacantPost;
        public StaffSanctionAndWorkingViewHolder(@NonNull View itemView) {
            super(itemView);
            noOfSanctionPosts=itemView.findViewById(R.id.noOfSanctionPosts);
            noOfPostedStaff=itemView.findViewById(R.id.noOfPostedStaff);
            noOfAttachedStaff=itemView.findViewById(R.id.noOfAttachedStaff);
            noOfWorkingStaff=itemView.findViewById(R.id.noOfWorkingStaff);
            designamtionName=itemView.findViewById(R.id.designamtionName);
            noOfVacantPost=itemView.findViewById(R.id.noOfNoOfVacantPost);
        }
    }
}
