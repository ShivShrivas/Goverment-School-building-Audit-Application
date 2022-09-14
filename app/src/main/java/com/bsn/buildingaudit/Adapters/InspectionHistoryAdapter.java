package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.DIOS.Diios_Panel_Under_School_dashboard;
import com.bsn.buildingaudit.Model.AllInspectionDataModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class InspectionHistoryAdapter extends RecyclerView.Adapter<InspectionHistoryAdapter.InspectionHistoryViewHolder> {
    Context context;
    ArrayList<AllInspectionDataModel> arrayList;
    public InspectionHistoryAdapter(Context context, ArrayList<AllInspectionDataModel> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public InspectionHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_inspection_history,parent,false);
        return new InspectionHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionHistoryViewHolder holder, @SuppressLint ("RecyclerView") int position) {
        holder.startOnTxt.setText(arrayList.get(position).getStartDate());
        if (arrayList.get(position).getStatus().toString().equals("Pending")){
            holder.imageView4.setImageDrawable(context.getDrawable(R.drawable.ic_wron_icon));
        }else{
            holder.imageView4.setImageDrawable(context.getDrawable(R.drawable.ic_right_icon));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Diios_Panel_Under_School_dashboard.class);
                i.putExtra("InspectionId",arrayList.get(position).getInsRecordId().toString());
                context.startActivity(i);
            }
        });
        Log.d("TAG", "onBindViewHolder: "+arrayList.get(position).getStatus());
        holder.statusTxt.setText(arrayList.get(position).getStatus());
        holder.inspectionIdTxt.setText(arrayList.get(position).getInsId());
        holder.timeDurationTxt.setText(arrayList.get(position).getTotalDuration());
        holder.endsOnTxt.setText(arrayList.get(position).getEndDate());
        holder.completeTxt.setText(arrayList.get(position).getTotalInsCompleted()+" Completed");
        holder.pendingTxt.setText(arrayList.get(position).getTotalInsPending()+" Pending");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class InspectionHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView statusTxt,completeTxt,pendingTxt,timeDurationTxt,endsOnTxt,startOnTxt,inspectionIdTxt,inspectionNoTxt;
        ImageView imageView4;
        public InspectionHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            statusTxt=itemView.findViewById(R.id.statusTxt);
            completeTxt=itemView.findViewById(R.id.completeTxt);
            pendingTxt=itemView.findViewById(R.id.pendingTxt);
            timeDurationTxt=itemView.findViewById(R.id.timeDurationTxt);
            endsOnTxt=itemView.findViewById(R.id.endsOnTxt);
            startOnTxt=itemView.findViewById(R.id.startOnTxt);
            inspectionIdTxt=itemView.findViewById(R.id.inspectionIdTxt);
            inspectionNoTxt=itemView.findViewById(R.id.inspectionNoTxt);
            imageView4=itemView.findViewById(R.id.imageView4);
        }
    }
}
