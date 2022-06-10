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

public class StudentCollegeWiseResultAdapter extends RecyclerView.Adapter<StudentCollegeWiseResultAdapter.CollegeWiseResultViewholder> {
    Context context;
    ArrayList<String> classList;
    public StudentCollegeWiseResultAdapter(Context context, ArrayList<String> classList) {
        this.classList=classList;
        this.context=context;
    }

    @NonNull
    @Override
    public CollegeWiseResultViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.college_result_card,parent,false);
        return new CollegeWiseResultViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CollegeWiseResultViewholder holder, int position) {
        holder.classText.setText("Class: "+classList.get(position)+"th");
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public class CollegeWiseResultViewholder extends RecyclerView.ViewHolder {
        TextView classText;
        public CollegeWiseResultViewholder(@NonNull View itemView) {
            super(itemView);
            classText=itemView.findViewById(R.id.classText);
        }
    }
}
