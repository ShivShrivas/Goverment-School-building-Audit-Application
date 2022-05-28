package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.Staff_Details_Model;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class StaffAttendanceAdapter extends RecyclerView.Adapter<StaffAttendanceAdapter.StaffAttendanceViewHolder> {
    Context  context;
    ArrayList<Staff_Details_Model> arrayList;

    public StaffAttendanceAdapter(Context  context, ArrayList<Staff_Details_Model> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public StaffAttendanceAdapter.StaffAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_attendance_card,parent,false);
        return new StaffAttendanceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StaffAttendanceAdapter.StaffAttendanceViewHolder holder, int position) {
        holder.staffname.setText(arrayList.get(position).getStaffName());
        holder.staffDeg.setText(arrayList.get(position).getStaffDesignation());
        holder.textView5.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StaffAttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView staffname,staffDeg,textView5;
        EditText desc;
        ArrayAdapter<String> arrayAdapter4;
        Spinner spinnerGrantUnderScheme;
        public StaffAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            spinnerGrantUnderScheme =itemView.findViewById(R.id.spinnerGrantUnderScheme);
            staffDeg=itemView.findViewById(R.id.staffDeg);
            staffname=itemView.findViewById(R.id.staffname);
            textView5=itemView.findViewById(R.id.textView5);

            ArrayList<String> arrayListPowerbackup =new ArrayList<>();
            arrayListPowerbackup.add("Present");
            arrayListPowerbackup.add("Absent");
            arrayListPowerbackup.add("Leave");
            arrayListPowerbackup.add("Transfer");
            arrayListPowerbackup.add("Retire");
            arrayListPowerbackup.add("Removal");

            arrayAdapter4=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,arrayListPowerbackup);
            arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
            spinnerGrantUnderScheme.setAdapter(arrayAdapter4);
        }
    }
}
