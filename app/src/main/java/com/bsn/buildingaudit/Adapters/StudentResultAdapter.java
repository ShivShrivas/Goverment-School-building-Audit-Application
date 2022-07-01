package com.bsn.buildingaudit.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.LastThreeYearsModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class StudentResultAdapter  extends RecyclerView.Adapter<StudentResultAdapter.StudentResultViewHolder> {
    Context context;
    ArrayList<LastThreeYearsModel> result;
    public StudentResultAdapter(Context context, ArrayList<LastThreeYearsModel> result) {
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
        holder.yeartxt.setText(result.get(position).getYear().toString());
        holder.classTxt.setText(result.get(position).getClass_()+"th");
        holder.firstDivisionTxt.setText(result.get(position).getFirstDivStudent().toString());
        holder.secondDivisionTxt.setText(result.get(position).getSecDivStudent().toString());
        holder.thirdDivisionTxt.setText(result.get(position).getThirdDivStudent().toString());
        holder.numberOfAppearedStudent.setText(result.get(position).getAppearedStudent().toString());
        holder.percentageOfPassedStudent.setText(result.get(position).getPassedStudentPert().toString());
        holder.numberOfPassedStudent.setText(result.get(position).getPassedStudent().toString());



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
        TextView classTxt,yeartxt,numberOfAppearedStudent,numberOfPassedStudent,percentageOfPassedStudent,
                firstDivisionTxt,
        secondDivisionTxt,thirdDivisionTxt;

        public StudentResultViewHolder(@NonNull View itemView) {
            super(itemView);
            classTxt=itemView.findViewById(R.id.classTxt);
            yeartxt=itemView.findViewById(R.id.yeartxt);
            firstDivisionTxt=itemView.findViewById(R.id.firstDivisionTxt);
            numberOfAppearedStudent=itemView.findViewById(R.id.numberOfAppearedStudent);
            numberOfPassedStudent=itemView.findViewById(R.id.numberOfPassedStudent);
            percentageOfPassedStudent=itemView.findViewById(R.id.percentageOfPassedStudent);
            secondDivisionTxt=itemView.findViewById(R.id.secondDivisionTxt);
            thirdDivisionTxt=itemView.findViewById(R.id.thirdDivisionTxt);

        }
    }
}
