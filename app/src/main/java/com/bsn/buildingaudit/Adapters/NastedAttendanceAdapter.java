package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.StudentAttendanceByMonths;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class NastedAttendanceAdapter extends RecyclerView.Adapter<NastedAttendanceAdapter.NastedAdapterViewHolder> {
    Context context;
    ArrayList<StudentAttendanceByMonths> arrayList;
    public NastedAttendanceAdapter(Context context, ArrayList<StudentAttendanceByMonths> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public NastedAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.nasted_month_attendance_card,parent,false);
        return new NastedAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NastedAdapterViewHolder holder, int position) {
        holder.girlsCounttxt.setText(arrayList.get(position).getGirlsCount());
        holder.boysCountTxt.setText(arrayList.get(position).getBoysCount());
        holder.monthNameTxt.setText(arrayList.get(position).getMonth());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class NastedAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView monthNameTxt,boysCountTxt,girlsCounttxt;
        public NastedAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            monthNameTxt=itemView.findViewById(R.id.monthNameTxt);
            boysCountTxt=itemView.findViewById(R.id.boysCountTxt);
            girlsCounttxt=itemView.findViewById(R.id.girlsCounttxt);
        }
    }
}
