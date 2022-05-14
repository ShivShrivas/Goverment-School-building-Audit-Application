package com.example.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Model.SmartClassesCard;
import com.example.buildingaudit.R;

import java.util.ArrayList;

public class SmartClassAdapterForEdit extends RecyclerView.Adapter<SmartClassAdapterForEdit.CardViewHolder> {
    Context context;
    ArrayList<SmartClassesCard> smartClassesCards;
    public SmartClassAdapterForEdit(Context context, ArrayList<SmartClassesCard> smartClassesCards) {
        this.context=context;
        this.smartClassesCards=smartClassesCards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.editable_smart_class_card,parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.edtSmartClassCompanyNameAdd.setText(smartClassesCards.get(position).getCompanyName());

        holder.spinnerInstallationYearSmartClassAdd.setText(smartClassesCards.get(position).getInstallationYear());


        holder.spinnerUnderSchemeSmartClassAdd.setText(smartClassesCards.get(position).getScheme());

        holder.spinnerWorkingStatusSmartClassAdd.setText(smartClassesCards.get(position).getWorkingStatus());
        holder.txtSmartRoomName.setText("Smart Classroom "+smartClassesCards.get(position).getSrno());

        holder.deleteRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartClassesCards.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return smartClassesCards.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView deleteRoomBtn;
        TextView txtSmartRoomName;
        TextView spinnerWorkingStatusSmartClassAdd,spinnerUnderSchemeSmartClassAdd,spinnerInstallationYearSmartClassAdd;
        TextView edtSmartClassCompanyNameAdd;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteRoomBtn=itemView.findViewById(R.id.deleteRoomBtnedtable);
            txtSmartRoomName=itemView.findViewById(R.id.txtSmartRoomNameedtable);
            spinnerWorkingStatusSmartClassAdd=itemView.findViewById(R.id.spinnerWorkingStatusSmartClassAddedtable);
            spinnerUnderSchemeSmartClassAdd=itemView.findViewById(R.id.spinnerUnderSchemeSmartClassAddedtable);
            spinnerInstallationYearSmartClassAdd=itemView.findViewById(R.id.spinnerInstallationYearSmartClassAddedtable);
            edtSmartClassCompanyNameAdd=itemView.findViewById(R.id.edtSmartClassCompanyNameAddedtable);




        }
    }
}
