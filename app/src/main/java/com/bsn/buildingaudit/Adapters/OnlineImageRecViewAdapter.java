package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bsn.buildingaudit.ConstantValues.ConstantFile;
import com.bsn.buildingaudit.R;

public class OnlineImageRecViewAdapter extends RecyclerView.Adapter<OnlineImageRecViewAdapter.HistoryItemViewHolder> {
    Context context;
    String[] imageUrlString;
    public OnlineImageRecViewAdapter(Context context, String[] imageUrlString) {
        this.context=context;
        this.imageUrlString=imageUrlString;
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_image_card,parent,false);
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
        String dummyUrl=imageUrlString[position];
        String newImgUrl=dummyUrl.replace(" ../wwwroot", ConstantFile.IMAGE_BASE_URL);
        String newUrl2=newImgUrl.replaceAll("\"","");
        Glide.with(holder.imageMain1).load(newUrl2).placeholder(R.drawable.background).into(holder.imageMain1);


    }

    @Override
    public int getItemCount() {
        return imageUrlString.length;
    }

    public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMain1;
        public HistoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMain1=itemView.findViewById(R.id.imageMain1);
        }
    }
}
