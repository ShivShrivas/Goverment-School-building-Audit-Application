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

public class AdpterResonsApproved  extends RecyclerView.Adapter<AdpterResonsApproved.ApproverdViewHolder> {
    Context context;
    ArrayList<String> myImageNameList;
    public AdpterResonsApproved(Context context, ArrayList<String> myImageNameList) {
        this.context=context;
        this.myImageNameList=myImageNameList;

    }

    @NonNull
    @Override
    public ApproverdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reseaon_card_with_checkbox,parent,false);
        return new ApproverdViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ApproverdViewHolder holder, int position) {
        holder.resontext.setText(myImageNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return myImageNameList.size();
    }

    public class ApproverdViewHolder extends RecyclerView.ViewHolder{
        TextView resontext;
        public ApproverdViewHolder(@NonNull View itemView) {
            super(itemView);
            resontext=itemView.findViewById(R.id.resontext);

        }
    }
}
