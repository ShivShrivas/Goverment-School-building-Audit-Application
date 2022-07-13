package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.ConstantValues.ConstantFile;
import com.bsn.buildingaudit.R;
import com.bumptech.glide.Glide;

public class OnlineImageRecViewAdapter extends RecyclerView.Adapter<OnlineImageRecViewAdapter.HistoryItemViewHolder> {
    Context context;
    String[] imageUrlString;
    String usertype;
    public OnlineImageRecViewAdapter(Context context, String[] imageUrlString, String usertype) {
        this.context=context;
        this.imageUrlString=imageUrlString;
        this.usertype=usertype;
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
        Log.d("TAG", "onBindViewHolder: "+usertype);
        if (usertype.equals("VA")){
            String newImgUrl=dummyUrl.replace(" ../wwwroot", ConstantFile.IMAGE_BASE_URL);
            String newUrl2=newImgUrl.replaceAll("\"","");
            Glide.with(holder.imageMain1).load(newUrl2.trim()).placeholder(R.drawable.background).into(holder.imageMain1);
        }else{
            String newUrl2=dummyUrl.replaceAll("\"","");
            Log.d("TAG", "onBindViewHolder: "+newUrl2 +"////"+dummyUrl);
            Glide.with(holder.imageMain1).load(newUrl2.trim()).placeholder(R.drawable.background).into(holder.imageMain1);

        }



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
