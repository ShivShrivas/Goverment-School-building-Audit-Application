package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.Model.AttendanceType;
import com.bsn.buildingaudit.Model.StaffAttendanceSubmitModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;
import java.util.List;

public class StaffAttendanceAdapterOnView extends RecyclerView.Adapter<StaffAttendanceAdapterOnView.StaffAttendanceViewHolder> {
    Context  context;
    List<AttendanceType> arrayList1;
    List<AttendanceStaff> arrayList;
    String formattedDate;
    public  static List<StaffAttendanceSubmitModel> staffAttendanceSubmitModels=new ArrayList<>();
    public StaffAttendanceAdapterOnView(Context context, List<AttendanceStaff> arrayList, String formattedDate) {
        this.arrayList=arrayList;
        this.context=context;
        this.formattedDate=formattedDate;
    }

    @NonNull
    @Override
    public StaffAttendanceAdapterOnView.StaffAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_attendance_card_for_view,parent,false);
        return new StaffAttendanceAdapterOnView.StaffAttendanceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StaffAttendanceAdapterOnView.StaffAttendanceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.staffname.setText(arrayList.get(position).getStaffName());
        holder.staffDeg.setText(arrayList.get(position).getStaffDesignation());
        holder.textView5.setText(String.valueOf(position+1));
        try{
            if (arrayList.get(position).getAttendenceStatusID().equals("1")){
                holder.editGrantUnderScheme.setText("Present");
                holder.editGrantUnderScheme.setBackground(context.getResources().getDrawable(R.drawable.card_bg_green));

            }else if (arrayList.get(position).getAttendenceStatusID().equals("2")){
                holder.editGrantUnderScheme.setText("Absent");
                holder.editGrantUnderScheme.setBackground(context.getResources().getDrawable(R.drawable.card_bg_red));

            }else if (arrayList.get(position).getAttendenceStatusID().equals("3")){
                holder.editGrantUnderScheme.setText("Leave");
                holder.editGrantUnderScheme.setBackground(context.getResources().getDrawable(R.drawable.card_bg_blue));

            }else if (arrayList.get(position).getAttendenceStatusID().equals("4")){
                holder.editGrantUnderScheme.setText("Transfer");
                holder.editGrantUnderScheme.setBackground(context.getResources().getDrawable(R.drawable.card_bg_aqua));

            }else if (arrayList.get(position).getAttendenceStatusID().equals("5")){
                holder.editGrantUnderScheme.setText("Retire");
                holder.editGrantUnderScheme.setBackground(context.getResources().getDrawable(R.drawable.card_bg_cyan));

            }else if (arrayList.get(position).getAttendenceStatusID().equals("6")){
                holder.editGrantUnderScheme.setText("Removal");
                holder.editGrantUnderScheme.setBackground(context.getResources().getDrawable(R.drawable.card_bg_gray));

            }
        }catch (Exception e){
            holder.editGrantUnderScheme.setText("Not Taken");
            holder.editGrantUnderScheme.setTextColor(context.getResources().getColor(R.color.purple_700));

        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StaffAttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView staffname,staffDeg,textView5;
        EditText desc;
        AttendanceTypeAdapter arrayAdapter4;
        TextView editGrantUnderScheme;
        public StaffAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            editGrantUnderScheme =itemView.findViewById(R.id.editGrantUnderScheme);
            staffDeg=itemView.findViewById(R.id.staffDeg);
            staffname=itemView.findViewById(R.id.staffname);
            textView5=itemView.findViewById(R.id.textView5);



        }
    }
}
