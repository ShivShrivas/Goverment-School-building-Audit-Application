package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.R;

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

    }

    @Override
    public int getItemCount() {
        return attendanceStaff.size();
    }

    public class FaceRecViewHolder extends RecyclerView.ViewHolder {
        public FaceRecViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
