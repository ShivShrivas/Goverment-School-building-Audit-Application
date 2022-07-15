package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.GetReciptExpDetailsModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class RecieptAndExpenditureAdapter extends RecyclerView.Adapter<RecieptAndExpenditureAdapter.RecieptAndExpenditureViewHolder> {
    Context context;
    ArrayList<GetReciptExpDetailsModel> arrayList;
    public RecieptAndExpenditureAdapter(Context context, ArrayList<GetReciptExpDetailsModel> arrayList) {
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
     holder.MADNAME.setText(arrayList.get(position).getMadname());
     holder.REMAMT.setText(arrayList.get(position).getRemamt().toString());
     holder.QUARTERLYAJ.setText(arrayList.get(position).getQuarterlyaj().toString());
     holder.QUARTERLYJS.setText(arrayList.get(position).getQuarterlyjs().toString());
     holder.QUARTERLYOD.setText(arrayList.get(position).getQuarterlyod().toString());
     holder.QUARTERLYJM.setText(arrayList.get(position).getQuarterlyjm().toString());
     holder.TOTALAMTRECEIVEDCURRENTFIN.setText(arrayList.get(position).getTotalamtreceivedcurrentfin().toString());
     holder.TOTALAMTAVAILABLECURRENTFIN.setText(arrayList.get(position).getTotalamtavailablecurrentfin().toString());
     holder.SPENTQUARTERLYAJ.setText(arrayList.get(position).getSpentquarterlyaj().toString());
     holder.SPENTQUARTERLYJS.setText(arrayList.get(position).getSpentquarterlyjs().toString());
     holder.SPENTQUARTERLYOD.setText(arrayList.get(position).getSpentquarterlyod().toString());
     holder.SPENTQUARTERLYJM.setText(arrayList.get(position).getSpentquarterlyjm().toString());
     holder.TOTALAMTSPENTCURRENTFIN.setText(arrayList.get(position).getTotalamtspentcurrentfin().toString());
     holder.REMBALANCE.setText(arrayList.get(position).getRembalance().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class  RecieptAndExpenditureViewHolder extends RecyclerView.ViewHolder{
        TextView MADNAME,REMAMT,QUARTERLYAJ,QUARTERLYJS,QUARTERLYOD,QUARTERLYJM, TOTALAMTRECEIVEDCURRENTFIN
       , TOTALAMTAVAILABLECURRENTFIN, SPENTQUARTERLYAJ,SPENTQUARTERLYJS, SPENTQUARTERLYOD,SPENTQUARTERLYJM
            ,    TOTALAMTSPENTCURRENTFIN, REMBALANCE;
        public RecieptAndExpenditureViewHolder(@NonNull View itemView) {
            super(itemView);
          MADNAME=itemView.findViewById(R.id.MADNAME);
          REMAMT=itemView.findViewById(R.id.REMAMT);
          QUARTERLYAJ=itemView.findViewById(R.id.QUARTERLYAJ);
          QUARTERLYJS=itemView.findViewById(R.id.QUARTERLYJS);
          QUARTERLYOD=itemView.findViewById(R.id.QUARTERLYOD);
          QUARTERLYJM=itemView.findViewById(R.id.QUARTERLYJM);
          TOTALAMTRECEIVEDCURRENTFIN=itemView.findViewById(R.id.TOTALAMTRECEIVEDCURRENTFIN);
          TOTALAMTAVAILABLECURRENTFIN=itemView.findViewById(R.id.TOTALAMTAVAILABLECURRENTFIN);
          SPENTQUARTERLYAJ=itemView.findViewById(R.id.SPENTQUARTERLYAJ);
          SPENTQUARTERLYJS=itemView.findViewById(R.id.SPENTQUARTERLYJS);
          SPENTQUARTERLYOD=itemView.findViewById(R.id.SPENTQUARTERLYOD);
          SPENTQUARTERLYJM=itemView.findViewById(R.id.SPENTQUARTERLYJM);
          TOTALAMTSPENTCURRENTFIN=itemView.findViewById(R.id.TOTALAMTSPENTCURRENTFIN);
          REMBALANCE=itemView.findViewById(R.id.REMBALANCE);
        }
    }
}
