package com.bsn.buildingaudit.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class StudentResultAdapter  extends RecyclerView.Adapter<StudentResultAdapter.StudentResultViewHolder> {
    Context context;
    ArrayList<String[]> result;
    public StudentResultAdapter(Context context, ArrayList<String[]> result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public StudentResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student_result,parent,false);
        return new StudentResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentResultViewHolder holder, int position) {
        holder.yeartxt.setText(result.get(position)[0]);
        holder.classTxt.setText("Class: "+result.get(position)[1]+"th");


        holder.districLevelTopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(context);
            }
        });


        holder.comptativeExamSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog2(context);
            }
        });
    }
    public void showDialog(Context activity) {
        Dialog  dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.student_list_dialog);

        Button btndialog = (Button) dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("Aman Kumar");
        myImageNameList.add("Radhika Awasthi");
        myImageNameList.add("Prashant Gupta");
        myImageNameList.add("Zareen Khan");
        myImageNameList.add("Piyush Dubey");
        myImageNameList.add("Raj Bharti");
        myImageNameList.add("Shiv Sharma");
        myImageNameList.add("Rajesh Gaur");
        myImageNameList.add("Ritu Shrivastava");
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AdapterStudentGameDetails adapterRe = new AdapterStudentGameDetails(context, myImageNameList);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        dialog.show();
    }


    public void showDialog2(Context activity) {
        Dialog  dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.student_list_dialog2);

        Button btndialog = (Button) dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("Aman Kumar");
        myImageNameList.add("Radhika Awasthi");
        myImageNameList.add("Prashant Gupta");
        myImageNameList.add("Zareen Khan");
        myImageNameList.add("Piyush Dubey");
        myImageNameList.add("Raj Bharti");
        myImageNameList.add("Shiv Sharma");
        myImageNameList.add("Rajesh Gaur");
        myImageNameList.add("Ritu Shrivastava");
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AdapterStudentComptativeExamDetails adapterRe = new AdapterStudentComptativeExamDetails(context, myImageNameList);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        dialog.show();
    }
    @Override
    public int getItemCount() {
        return result.size();
    }

    public class StudentResultViewHolder extends RecyclerView.ViewHolder{
        TextView classTxt,yeartxt;
        ImageView comptativeExamSelection,districLevelTopper;
        public StudentResultViewHolder(@NonNull View itemView) {
            super(itemView);
            classTxt=itemView.findViewById(R.id.classTxt);
            yeartxt=itemView.findViewById(R.id.yeartxt);
            comptativeExamSelection=itemView.findViewById(R.id.comptativeExamSelection);
            districLevelTopper=itemView.findViewById(R.id.districLevelTopper);
        }
    }
}
