package com.example.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Activies.OnSubmit_SmartClassDetails;
import com.example.buildingaudit.Model.SmartClassesCard;
import com.example.buildingaudit.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class SmartClassAdapter extends RecyclerView.Adapter<SmartClassAdapter.SmartViewHolder>{
    Context context;
    ArrayList<SmartClassesCard> smartClassesCards;
    public SmartClassAdapter(Context context, ArrayList<SmartClassesCard> smartClassesCards) {
        this.context=context;
        this.smartClassesCards=smartClassesCards;
    }

    @NonNull
    @Override
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.smart_class_card,parent,false);
        return new SmartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartViewHolder holder, int position) {
        holder.edtCompanyName.setText(smartClassesCards.get(position).getCompanyName());
        holder.edtSmartInstalltionYear.setText(smartClassesCards.get(position).getInstallationYear());

        if (smartClassesCards.get(position).getWorkingStatus().equals("F")){
            holder.edtWorkingStatus.setText("Functional");
        }else if (smartClassesCards.get(position).getWorkingStatus().equals("NF")){
            holder.edtWorkingStatus.setText("Not Functional");
        }
        holder.edtUnderScheme.setText(smartClassesCards.get(position).getScheme());
        holder.txtSmartRoomName.setText("Smart Class"+smartClassesCards.get(position).getSrno());
    }

    @Override
    public int getItemCount() {
        return smartClassesCards.size();
    }

    public class SmartViewHolder extends RecyclerView.ViewHolder{
        TextView txtSmartRoomName;
        EditText edtWorkingStatus,edtUnderScheme,edtCompanyName,edtSmartInstalltionYear;
        public SmartViewHolder(@NonNull View itemView) {
            super(itemView);
            edtWorkingStatus=itemView.findViewById(R.id.edtWorkingStatus);
            txtSmartRoomName=itemView.findViewById(R.id.txtSmartRoomName);
            edtUnderScheme=itemView.findViewById(R.id.edtUnderScheme);
            edtCompanyName=itemView.findViewById(R.id.edtCompanyName);
            edtSmartInstalltionYear=itemView.findViewById(R.id.edtSmartInstalltionYear);
            edtWorkingStatus.setEnabled(false);
                    txtSmartRoomName.setEnabled(false);
            edtUnderScheme.setEnabled(false);
                    edtCompanyName.setEnabled(false);
            edtSmartInstalltionYear.setEnabled(false);
        }
    }
}
