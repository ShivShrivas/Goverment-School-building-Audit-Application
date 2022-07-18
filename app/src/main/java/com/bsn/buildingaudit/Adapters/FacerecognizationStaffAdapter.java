package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.R;
import com.bsn.staffAttendance.Face_Recog_Activity;

import java.util.List;

public class FacerecognizationStaffAdapter extends RecyclerView.Adapter<FacerecognizationStaffAdapter.FaceRecViewHolder> {
    Context context;
    List<AttendanceStaff> attendanceStaff;
    public FacerecognizationStaffAdapter(Context context, List<AttendanceStaff> attendanceStaff) {
        this.context=context;
        this.attendanceStaff=attendanceStaff;
    }

    @NonNull
    @Override
    public FaceRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.facerecoge_item_card,parent,false);
        return new FaceRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaceRecViewHolder holder, int position) {
        holder.staffname.setText(attendanceStaff.get(position).getStaffName());
        holder.staffDeg.setText(attendanceStaff.get(position).getStaffDesignation());
        holder.textView5.setText(String.valueOf(position+1));
        holder.recognize_face_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Face_Recog_Activity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return attendanceStaff.size();
    }

    public class FaceRecViewHolder extends RecyclerView.ViewHolder {
        TextView staffname,staffDeg,textView5;
        CardView recognize_face_btn;
        public FaceRecViewHolder(@NonNull View itemView) {
            super(itemView);
            staffDeg=itemView.findViewById(R.id.staffDeg);
            staffname=itemView.findViewById(R.id.staffname);
            textView5=itemView.findViewById(R.id.textView5);
            recognize_face_btn=itemView.findViewById(R.id.recognize_face_btn);
        }
    }
}
