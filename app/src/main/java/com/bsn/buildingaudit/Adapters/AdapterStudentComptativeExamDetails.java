package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class AdapterStudentComptativeExamDetails extends RecyclerView.Adapter<AdapterStudentComptativeExamDetails.CompativeVieHolder> {
    Context context;
    ArrayList<String> myImageNameList;
    public AdapterStudentComptativeExamDetails(Context context, ArrayList<String> myImageNameList) {
        this.context=context;
        this.myImageNameList=myImageNameList;
    }

    @NonNull
    @Override
    public CompativeVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_details_card,parent,false);
        return new CompativeVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompativeVieHolder holder, int position) {
        if (position%4==0){
            holder.examNAme.setText("IIT JEE");
        }else if(position%4==1){
            holder.examNAme.setText("CLAT");
        }else if (position%4==1){
            holder.examNAme.setText("NEET");
        }else{
            holder.examNAme.setText("Others");
        }
        holder.studName.setText(myImageNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return myImageNameList.size();
    }

    public class CompativeVieHolder extends RecyclerView.ViewHolder {
        TextView examNAme,studName;
        public CompativeVieHolder(@NonNull View itemView) {
            super(itemView);
            studName= itemView.findViewById(R.id.studName);
            examNAme=itemView.findViewById(R.id.examNAme);
        }
    }
}
