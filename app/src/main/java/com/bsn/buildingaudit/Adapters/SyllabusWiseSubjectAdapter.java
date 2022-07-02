package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.SubjectWiseSyllabusModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class SyllabusWiseSubjectAdapter extends RecyclerView.Adapter<SyllabusWiseSubjectAdapter.SyllabusSubjectViewHolder> {
    Context context;
    ArrayList<SubjectWiseSyllabusModel> arrayListClassWise;
    public SyllabusWiseSubjectAdapter(Context context, ArrayList<SubjectWiseSyllabusModel> arrayListClassWise) {
        this.context=context;
        this.arrayListClassWise=arrayListClassWise;
    }

    @NonNull
    @Override
    public SyllabusSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_syllabus_item_card,parent,false);

        return new SyllabusSubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusSubjectViewHolder holder, int position) {
        holder.monthNametxt.setText(arrayListClassWise.get(position).getMonthNames().toString());
        holder.nameOfTopicTxt.setText(arrayListClassWise.get(position).getSubjectSubTopic().toString());
        holder.subjectNameTxt.setText(arrayListClassWise.get(position).getSubjectName().toString());
        if (arrayListClassWise.get(position).getDateCovered()==1){
            holder.completedTxt.setText("COMPLETED");
            holder.completedTxt.setBackground(context.getDrawable(R.drawable.verfied_card_bg));
            holder.completedTxt.setTextColor(context.getResources().getColor(R.color.completed_color));
        }else{
            holder.completedTxt.setText("INCOMPLETED");
            holder.completedTxt.setBackground(context.getDrawable(R.drawable.incompleted_card_bg));
            holder.completedTxt.setTextColor(context.getResources().getColor(R.color.incompleted_color));
        }
    }

    @Override
    public int getItemCount() {
        return arrayListClassWise.size();
    }

    public class SyllabusSubjectViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfTopicTxt,completedTxt,subjectNameTxt,monthNametxt;
        public SyllabusSubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            monthNametxt=itemView.findViewById(R.id.monthNametxt);
            nameOfTopicTxt=itemView.findViewById(R.id.nameOfTopicTxt);
            completedTxt=itemView.findViewById(R.id.completedTxt);
            subjectNameTxt=itemView.findViewById(R.id.subjectNameTxt);
        }
    }
}
