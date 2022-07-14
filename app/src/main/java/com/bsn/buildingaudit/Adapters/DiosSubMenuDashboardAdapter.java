package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.GetDashboardMenuDataModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class DiosSubMenuDashboardAdapter extends RecyclerView.Adapter<DiosSubMenuDashboardAdapter.DiosSubmenuViewHolder> {
    Context context;
    ArrayList<GetDashboardMenuDataModel> arrayList;
    public DiosSubMenuDashboardAdapter(Context context, ArrayList<GetDashboardMenuDataModel> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public DiosSubmenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_review_itemcard,parent,false);
        return new DiosSubmenuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DiosSubmenuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.roomTypetxt.setText(arrayList.get(position).getMenuname());
        holder.updateOntxt.setText("");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.d("TAG", "onClick: "+arrayList.get(position).getAppClassName());
                    Intent i=new Intent(context, Class.forName(arrayList.get(position).getAppClassName()));

                    context.startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DiosSubmenuViewHolder extends RecyclerView.ViewHolder {
        TextView updateOntxt,roomTypetxt;
        public DiosSubmenuViewHolder(@NonNull View itemView) {
            super(itemView);
            updateOntxt=itemView.findViewById(R.id.updateOntxt);
            roomTypetxt=itemView.findViewById(R.id.roomTypetxt);
        }
    }
}
