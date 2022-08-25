package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class AdpterResonsApproved  extends RecyclerView.Adapter<AdpterResonsApproved.ApproverdViewHolder> {
    Context context;
    ArrayList<ApproveRejectRemarkModel> myImageNameList;
    public static JsonArray jsonArray=new JsonArray();
    String schoolId;
    String type;
    String periodID;
    String id;
    String[] arrayRemark={"Total Class Room number is wrong"," Good condition Class Room number is wrong", "Major repairing Class Room number is wrong"," Minor repairing Class Room number is wrong", "Black Board number is wrong", "White Board number is wrong, Green Board number is wrong"};


    public AdpterResonsApproved(Context context, ArrayList<ApproveRejectRemarkModel> myImageNameList, String schoolId, String periodID, String type, String id) {
        this.context=context;
        this.myImageNameList=myImageNameList;
        this.schoolId=schoolId;
        this.periodID=periodID;
        this.type=type;
        this.id=id;

    }

    @NonNull
    @Override
    public ApproverdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reseaon_card_with_checkbox,parent,false);
        return new ApproverdViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ApproverdViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.resontext.setText(myImageNameList.get(position).getInsName());

        for (int i=0;i<arrayRemark.length;i++){
            if (holder.resontext.getText().toString().equals(arrayRemark[i])){
                holder.reasonCheck.setChecked(true);
            }
        }
        holder.reasonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.resontext.getText().equals("Other")){
                    if (holder.reasonCheck.isChecked()){
                        StaticFunctions.showTextBox(type);
                    }else {
                        StaticFunctions.hideTextBox(type);

                    }
                }else{
                    JsonObject jsonObject=new JsonObject();
                    jsonObject.addProperty("SchoolId",schoolId);
                    jsonObject.addProperty("PeriodId",periodID);
                    jsonObject.addProperty("ParamId",id);
                    jsonObject.addProperty("OtherRemarks","");
                    jsonObject.addProperty("InsId",myImageNameList.get(position).getInsId());
                    jsonObject.addProperty("CreatedBy","");
                    jsonArray.add(jsonObject);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return myImageNameList.size();
    }

    public class ApproverdViewHolder extends RecyclerView.ViewHolder{
        TextView resontext;
        CheckBox reasonCheck;
        public ApproverdViewHolder(@NonNull View itemView) {
            super(itemView);
            resontext=itemView.findViewById(R.id.resontext);
            reasonCheck=itemView.findViewById(R.id.reasonCheck);

        }
    }
}
