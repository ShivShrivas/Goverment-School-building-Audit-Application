package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.StudentAttendanceByMonths;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class Student_PresenceAdapter extends RecyclerView.Adapter<Student_PresenceAdapter.Student_PersenceViewHolder> {
    Context context;
    ArrayList<String> arrayList2;
    public Student_PresenceAdapter(Context context, ArrayList<String> arrayList2) {
        this.context=context;
        this.arrayList2=arrayList2;
    }

    @NonNull
    @Override
    public Student_PersenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student_presence,parent,false);
        return new Student_PersenceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Student_PersenceViewHolder holder, int position) {
        holder.textView6.setText("Class: "+(position+6)+"th");
    }

    @Override
    public int getItemCount() {
        return arrayList2.size();
    }

    public class Student_PersenceViewHolder extends RecyclerView.ViewHolder {
        RecyclerView nastedRecViewMonths;
        ArrayList<StudentAttendanceByMonths> arrayList=new ArrayList<>();
        NastedAttendanceAdapter adapter;
        TextView textView6;
        public Student_PersenceViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentAttendanceByMonths studentAttendanceByMonths=new StudentAttendanceByMonths("April","222","456");
            StudentAttendanceByMonths studentAttendanceByMonths1=new StudentAttendanceByMonths("May","234","642");
            StudentAttendanceByMonths studentAttendanceByMonths2=new StudentAttendanceByMonths("June","526","987");
            nastedRecViewMonths=itemView.findViewById(R.id.nastedRecViewMonths);
            textView6=itemView.findViewById(R.id.textView6);

            arrayList.add(studentAttendanceByMonths);
            arrayList.add(studentAttendanceByMonths1);
            arrayList.add(studentAttendanceByMonths2);

            adapter=new NastedAttendanceAdapter(context,arrayList);
            nastedRecViewMonths.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
            nastedRecViewMonths.setAdapter(adapter);


        }
    }
}
