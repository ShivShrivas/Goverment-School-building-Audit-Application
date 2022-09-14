package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.Datum;
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
    String inspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();


    public AdpterResonsApproved(Context context, ArrayList<ApproveRejectRemarkModel> myImageNameList, String schoolId, String periodID, String type, String id, ArrayList<Datum> arrayListRemarks) {
        this.context=context;
        this.myImageNameList=myImageNameList;
        this.schoolId=schoolId;
        this.periodID=periodID;
        this.type=type;
        this.id=id;
        try{
            if (type.equals(arrayListRemarks.get(0).getInsType())){
                this.arrayListRemarks=arrayListRemarks;

                for (int i=0;i<arrayListRemarks.size();i++){

                    JsonObject jsonObject=new JsonObject();
                    jsonObject.addProperty("SchoolId",arrayListRemarks.get(i).getSchoolId());
                    jsonObject.addProperty("PeriodId",arrayListRemarks.get(i).getPeriodId());
                    jsonObject.addProperty("ParamId",arrayListRemarks.get(i).getParamId());
                    jsonObject.addProperty("OtherRemarks",arrayListRemarks.get(i).getOtherRemarks());
                    jsonObject.addProperty("InsId",arrayListRemarks.get(i).getInsId());
                    jsonObject.addProperty("CreatedBy","");
                    jsonObject.addProperty("InsRecordId",inspectionId);
                    jsonArray.add(jsonObject);
                }
            }
        }catch (Exception e){

        }


    }

    public AdpterResonsApproved(Context context, ArrayList<ApproveRejectRemarkModel> arrayList, String schoolId, String periodID, String type, String parentID, ArrayList<Datum> arrayListRemarks, String inspectionId) {
        this.context=context;
        this.myImageNameList=arrayList;
        this.schoolId=schoolId;
        this.periodID=periodID;
        this.type=type;
        this.inspectionId=inspectionId;
        this.id=parentID;
        try{
            if (type.equals(arrayListRemarks.get(0).getInsType())){
                this.arrayListRemarks=arrayListRemarks;

                for (int i=0;i<arrayListRemarks.size();i++){

                    JsonObject jsonObject=new JsonObject();
                    jsonObject.addProperty("SchoolId",arrayListRemarks.get(i).getSchoolId());
                    jsonObject.addProperty("PeriodId",arrayListRemarks.get(i).getPeriodId());
                    jsonObject.addProperty("ParamId",arrayListRemarks.get(i).getParamId());
                    jsonObject.addProperty("OtherRemarks",arrayListRemarks.get(i).getOtherRemarks());
                    jsonObject.addProperty("InsId",arrayListRemarks.get(i).getInsId());
                    jsonObject.addProperty("CreatedBy","");
                    jsonObject.addProperty("InsRecordId",inspectionId);
                    jsonArray.add(jsonObject);
                }
            }
        }catch (Exception e){
            Log.d("TAG", "AdpterResonsApproved: "+e.getMessage());

        }
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

        for (int i=0;i<arrayListRemarks.size();i++){
            if (holder.resontext.getText().toString().equals(arrayListRemarks.get(i).getInsName())){
                holder.reasonCheck.setChecked(true);

            }
            if (arrayListRemarks.get(i).getInsName().equals("Other")){

                    StaticFunctions.setText(type,arrayListRemarks.get(i).getOtherRemarks());


            }
        }
        holder.reasonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.reasonCheck.isChecked()){
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
                        jsonObject.addProperty("InsRecordId",inspectionId);
                        jsonArray.add(jsonObject);
                    }

                }else {
                    for (int i=0;i<jsonArray.size();i++){
                        Log.d("TAG", "onClick: "+jsonArray.get(i).getAsJsonObject().get("InsId"));
                        if(jsonArray.get(i).getAsJsonObject().get("InsId").toString().equals(myImageNameList.get(position).getInsId().toString())){
                            jsonArray.remove(i);
                            Log.d("TAG", "onClick: "+"removed");
                        }
                    }


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
