package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
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

public class ScoutAndGuideImageRecViewAdapter extends RecyclerView.Adapter<ScoutAndGuideImageRecViewAdapter.HistoryItemViewHolder> {
    Context context;
    String[]  imageUrlString;

    public ScoutAndGuideImageRecViewAdapter(Context context, String[] staffPhotoPathList) {
        this.context=context;
        this.imageUrlString=staffPhotoPathList;

    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.history_image_card,parent,false);
        return new HistoryItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, @SuppressLint("RecyclerView") int position) {

            String newImgUrl=ConstantFile.SCOUT_GUIDE_IMAGE_BASRURL+imageUrlString[position];
            Log.d("TAG", "onBindViewHolder: "+ newImgUrl);
                String newUrl2=newImgUrl.replaceAll("\"","");
                Glide.with(holder.imageMain1).load(newUrl2).centerCrop().placeholder(context.getDrawable(R.drawable.image_not_found)).into(holder.imageMain1);





    }

    @Override
    public int getItemCount() {
        return imageUrlString.length;
    }


    public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMain1,ImageCross;
        public HistoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMain1=itemView.findViewById(R.id.imageMain1);

        }
    }
}
