package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.StudentAbsentDetailsModel;
import com.bsn.buildingaudit.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AdapterStudentAbsentDetails extends RecyclerView.Adapter<AdapterStudentAbsentDetails.AbsentDetailsViewHolder> {
    Context context;
    ArrayList<StudentAbsentDetailsModel> arrayList;
    public AdapterStudentAbsentDetails(Context context, ArrayList<StudentAbsentDetailsModel> arrayList) {
        this.context=context;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public AbsentDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_for_absent_details,parent,false);
        return new AbsentDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsentDetailsViewHolder holder, int position) {
        holder.absentCount.setText(arrayList.get(position).getAbsentDays().toString());
        holder.studentnameAbsent.setText(arrayList.get(position).getNameOfStudent().toString());
        holder.parentNameAbsent.setText(arrayList.get(position).getNameOfFather().toString());
        holder.absentreason.setText(arrayList.get(position).getReason().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AbsentDetailsViewHolder extends RecyclerView.ViewHolder{
        TextInputEditText absentCount,parentNameAbsent,studentnameAbsent,absentreason;

        public AbsentDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            absentCount=itemView.findViewById(R.id.absentCount);
            parentNameAbsent=itemView.findViewById(R.id.parentNameAbsent);
            studentnameAbsent=itemView.findViewById(R.id.studentnameAbsent);
            absentreason=itemView.findViewById(R.id.absentreason);
        }
    }
}
