package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class RecieptAndExpenditureAdapter extends RecyclerView.Adapter<RecieptAndExpenditureAdapter.RecieptAndExpenditureViewHolder> {
    Context context;
    ArrayList<String> arrayList;
    public RecieptAndExpenditureAdapter(Context context, ArrayList<String> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public RecieptAndExpenditureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reciepts_and_expenditure_item_card,parent,false);
            return new RecieptAndExpenditureViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecieptAndExpenditureViewHolder holder, int position) {
        holder.nametxt.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class  RecieptAndExpenditureViewHolder extends RecyclerView.ViewHolder{
        TextView nametxt;
        public RecieptAndExpenditureViewHolder(@NonNull View itemView) {
            super(itemView);
            nametxt=itemView.findViewById(R.id.nametxt);
        }
    }
}
