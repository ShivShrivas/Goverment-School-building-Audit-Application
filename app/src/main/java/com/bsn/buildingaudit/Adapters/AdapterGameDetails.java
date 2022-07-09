package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.GameStudentName;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class AdapterGameDetails extends RecyclerView.Adapter<AdapterGameDetails.StudentDetailsViewHolder> {
    Context context;
    ArrayList<GameStudentName> myImageNameList;
    public AdapterGameDetails(Context context, ArrayList<GameStudentName> myImageNameList) {
        this.context=context;
        this.myImageNameList=myImageNameList;

    }

    @NonNull
    @Override
    public StudentDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_details_card1,parent,false);
        return new StudentDetailsViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull StudentDetailsViewHolder holder, int position) {
    holder.studName.setText(myImageNameList.get(position).getStudentname());
    holder.sporstName.setText(myImageNameList.get(position).getSportname());
    holder.positioninGame.setText(myImageNameList.get(position).getMedalposition());
    }

    @Override
    public int getItemCount() {
        return myImageNameList.size();
    }

    public class StudentDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView studName,sporstName,positioninGame;
        public StudentDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            studName=itemView.findViewById(R.id.studName);
            sporstName=itemView.findViewById(R.id.sporstName);
            positioninGame=itemView.findViewById(R.id.positioninGame);
        }
    }
}
