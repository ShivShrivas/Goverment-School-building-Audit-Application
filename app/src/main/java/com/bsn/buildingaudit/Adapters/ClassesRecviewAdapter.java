package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.ClassDatum;
import com.bsn.buildingaudit.R;

import java.util.List;

public class ClassesRecviewAdapter extends RecyclerView.Adapter<ClassesRecviewAdapter.SubjectViewHolder>{
        Context context;
        List<ClassDatum> subjectArray;
public ClassesRecviewAdapter(Context context, List<ClassDatum> subjectArray) {
        this.context=context;
        this.subjectArray=subjectArray;

        }

@NonNull
@Override
public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_ietm_card,parent,false);
        return new SubjectViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.subjecttextView.setText(subjectArray.get(position).getClassName());
        }

@Override
public int getItemCount() {
        return subjectArray.size();
        }

public class SubjectViewHolder extends RecyclerView.ViewHolder {
    TextView subjecttextView;
    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);
        subjecttextView=itemView.findViewById(R.id.subjecttextView);
    }
}
}
