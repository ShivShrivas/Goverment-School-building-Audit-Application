package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.P;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class PrincipalTrainingAdapter extends RecyclerView.Adapter<PrincipalTrainingAdapter.PrincipaltrainingViewHolder> {
    Context context;
    ArrayList<P> arrayList;
    public PrincipalTrainingAdapter(Context context, ArrayList<P> arrayList) {
        this.context=context;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public PrincipaltrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.training_card,parent,false);
        return new PrincipaltrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrincipaltrainingViewHolder holder, int position) {
        holder.nameTeacher.setText(arrayList.get(position).getPrincipalName());
        holder.modeTxt.setText(arrayList.get(position).getModeOfTraining());
        holder.agencyTxt.setText(arrayList.get(position).getAgencyName());
        holder.yojnaTxt.setText(arrayList.get(position).getTrainingName());
        holder.durationTxt.setText(arrayList.get(position).getTrainingDuration()+" Days");

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PrincipaltrainingViewHolder extends RecyclerView.ViewHolder {
        TextView modeTxt,agencyTxt,yojnaTxt,durationTxt,nameTeacher;

        public PrincipaltrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTeacher=itemView.findViewById(R.id.nameTeacher);
            modeTxt=itemView.findViewById(R.id.modeTxt);
            agencyTxt=itemView.findViewById(R.id.agencyTxt);
            yojnaTxt=itemView.findViewById(R.id.yojnaTxt);
            durationTxt=itemView.findViewById(R.id.durationTxt);
        }
    }
}
