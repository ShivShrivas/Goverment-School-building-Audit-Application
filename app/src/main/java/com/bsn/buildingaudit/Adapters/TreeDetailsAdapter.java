package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.TreeData;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class TreeDetailsAdapter extends RecyclerView.Adapter<TreeDetailsAdapter.TreeDetailsViewHolder> {
    Context context;
    ArrayList<TreeData> arrayList;
    public TreeDetailsAdapter(Context context, ArrayList<TreeData> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public TreeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tree_item_card,parent,false);
        return new TreeDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeDetailsViewHolder holder, int position) {
        holder.nameOftreeTxt.setText(arrayList.get(position).getTreename().toString());
        holder.numberOftrees.setText(arrayList.get(position).getNooftrees().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class TreeDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView numberOftrees,nameOftreeTxt;
        public TreeDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOftreeTxt=itemView.findViewById(R.id.nameOftreeTxt);
            numberOftrees=itemView.findViewById(R.id.numberOftrees);
        }
    }
}
