package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.DIOS.DIOS_Dashboard;
import com.bsn.buildingaudit.DIOS.Page_For_InspectionHistory;
import com.bsn.buildingaudit.Model.SchoolListModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class DIOS_DashboardAdapter extends RecyclerView.Adapter<DIOS_DashboardAdapter.dashboardRecViewHolder> {
    Context context;
    ArrayList<SchoolListModel> arrayListSchool;
    ArrayList<String> itemsCopy;
    ApplicationController applicationController;


    public DIOS_DashboardAdapter(Context context, ArrayList<SchoolListModel> arrayListSchool) {
        this.context=context;
        this.arrayListSchool=arrayListSchool;
        applicationController= (ApplicationController) context.getApplicationContext();
    }

    @NonNull
    @Override
    public dashboardRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_dios_itemcard,parent,false);
        return new dashboardRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dashboardRecViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.roomTypetxt.setText(arrayListSchool.get(position).getSchoolname());

        holder.updateOntxt.setText(arrayListSchool.get(position).getJdstatusdate()==null?"Not Available":arrayListSchool.get(position).getJdstatusdate().replace("T"," "));
      try{
          if ((arrayListSchool.get(position).getJdstatus()==1)){

          holder.penddingAndSDoneBtn.setImageDrawable(context.getDrawable(R.drawable.ic_right_icon));
      }
      else{

          holder.penddingAndSDoneBtn.setImageDrawable(context.getDrawable(R.drawable.ic_wron_icon));
      }
      }catch (Exception e ){
          holder.penddingAndSDoneBtn.setImageDrawable(context.getDrawable(R.drawable.ic_wron_icon));
      }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if ((arrayListSchool.get(position).getJdstatus()==1)){
                        Toast.makeText(context, "this School inspection is done", Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(context, Page_For_InspectionHistory.class);
                        applicationController.setSchoolName(arrayListSchool.get(position).getSchoolname());
                        applicationController.setSchoolAddress(arrayListSchool.get(position).getBlockname()+" "+arrayListSchool.get(position).getDistrictname()+" "+arrayListSchool.get(position).getDivisionname());
                        applicationController.setSchoolId(arrayListSchool.get(position).getSchoolid().toString());
                        context.startActivity(i);
                    }else{
                        if ( DIOS_Dashboard.setSchoolIdInContoller(arrayListSchool.get(position).getSchoolid())){
                            Intent i=new Intent(context, Page_For_InspectionHistory.class);
                            context.startActivity(i);
                        }



                    }
                }catch (Exception e){
                    if ( DIOS_Dashboard.setSchoolIdInContoller(arrayListSchool.get(position).getSchoolid())){
                        Intent i=new Intent(context, Page_For_InspectionHistory.class);
                        context.startActivity(i);
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListSchool.size();
    }
//    public void filter(String text) {
//        arrayListSchool.clear();
//        if(text.isEmpty()){
//            arrayListSchool.addAll(itemsCopy);
//        } else{
//            text = text.toLowerCase();
//            for(String item: itemsCopy){
//                if(item.toLowerCase().contains(text)){
//                    itemsCopy.add(item);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }

//    public void filterList(ArrayList<String> filteredList) {
//        arrayListSchool = filteredList;
//        notifyDataSetChanged();
//    }

    public class dashboardRecViewHolder extends RecyclerView.ViewHolder {
        TextView updateOntxt,roomTypetxt;
        ImageView penddingAndSDoneBtn;

        public dashboardRecViewHolder(@NonNull View itemView) {
            super(itemView);
            updateOntxt=itemView.findViewById(R.id.updateOntxt);
            roomTypetxt=itemView.findViewById(R.id.roomTypetxt);
            penddingAndSDoneBtn=itemView.findViewById(R.id.penddingAndSDoneBtn);
        }
    }
}

