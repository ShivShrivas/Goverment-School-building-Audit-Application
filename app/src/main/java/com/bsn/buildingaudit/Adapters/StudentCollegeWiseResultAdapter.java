package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.CurrentYearResultModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class StudentCollegeWiseResultAdapter extends RecyclerView.Adapter<StudentCollegeWiseResultAdapter.CollegeWiseResultViewholder> {
    Context context;
    ArrayList<CurrentYearResultModel> classList;
    public StudentCollegeWiseResultAdapter(Context context, ArrayList<CurrentYearResultModel> classList) {
        this.classList=classList;
        this.context=context;
    }

    @NonNull
    @Override
    public CollegeWiseResultViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.college_result_card,parent,false);
        return new CollegeWiseResultViewholder(view);

    }
//"SCHOOLID": 2033,
//        "PERIODID": 29,
//        "CLASSNAME": "CLASS 9",
//        "TOTALENROLLED": 0,
//        "FIRSTMAPPERED": 0,
//        "FIRSTMPASSED": 0,
//        "SECONDMAPPERED": 0,
//        "SECONDMPASSED": 0,
//        "HALFAPPEARED": 0,
//        "HALFPASSED": 0,
//        "YEARAPPEARED": 0,
//        "YEARPASSED": 0,
//        "STATUSID": null,
//        "REMARKS": null
    @Override
    public void onBindViewHolder(@NonNull CollegeWiseResultViewholder holder, int position) {
        holder.classText.setText(classList.get(position).getClassname()+"th");
        holder.appreaderInFirstMonthTxt.setText(classList.get(position).getFirstmappered().toString());
        holder.appreaderInSecondMonthTxt.setText(classList.get(position).getSecondmappered().toString());
        holder.appreaderInHalfYearlyTxt.setText(classList.get(position).getHalfappeared().toString());
        holder.appreaderInYearlyTxt.setText(classList.get(position).getYearappeared().toString());
        holder.passesInFirstMonth.setText(classList.get(position).getFirstmpassed().toString());
        holder.passesInSecMonth.setText(classList.get(position).getSecondmpassed().toString());
        holder.passesInHalfYearlyMonth.setText(classList.get(position).getHalfpassed().toString());
        holder.passesInYearlyExamMonth.setText(classList.get(position).getYearpassed().toString());
        holder.totalEnrolledtxt.setText(classList.get(position).getTotalenrolled().toString());

    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public class CollegeWiseResultViewholder extends RecyclerView.ViewHolder {
        TextView classText,passesInYearlyExamMonth,passesInSecMonth,passesInFirstMonth,
                appreaderInYearlyTxt,appreaderInHalfYearlyTxt,appreaderInSecondMonthTxt,
                appreaderInFirstMonthTxt,passesInHalfYearlyMonth,totalEnrolledtxt;
        public CollegeWiseResultViewholder(@NonNull View itemView) {
            super(itemView);
            classText=itemView.findViewById(R.id.classText);
            passesInYearlyExamMonth=itemView.findViewById(R.id.passesInYearlyExamMonth);
            passesInHalfYearlyMonth=itemView.findViewById(R.id.passesInHalfYearlyMonth);
            totalEnrolledtxt=itemView.findViewById(R.id.totalEnrolledtxt);

            passesInSecMonth=itemView.findViewById(R.id.passesInSecMonth);
            passesInFirstMonth=itemView.findViewById(R.id.passesInFirstMonth);
            appreaderInYearlyTxt=itemView.findViewById(R.id.appreaderInYearlyTxt);
            appreaderInHalfYearlyTxt=itemView.findViewById(R.id.appreaderInHalfYearlyTxt);

            appreaderInSecondMonthTxt=itemView.findViewById(R.id.appreaderInSecondMonthTxt);
            appreaderInFirstMonthTxt=itemView.findViewById(R.id.appreaderInFirstMonthTxt);
        }
    }
}
