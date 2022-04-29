package com.example.buildingaudit.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.buildingaudit.Activies.OnSubmitClassRoomPage;
import com.example.buildingaudit.R;
import com.squareup.picasso.Picasso;

public class OnlineImageRecViewAdapter extends RecyclerView.Adapter<OnlineImageRecViewAdapter.OnlineImageVoewHolder> {
    Context context;
    String[] goodConditionsList;

    public OnlineImageRecViewAdapter(Context context, String[] goodConditionsList) {
        this.context=context;
        this.goodConditionsList=goodConditionsList;
    }

    @NonNull
    @Override
    public OnlineImageVoewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card_layout,parent,false);
       return new OnlineImageVoewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineImageVoewHolder holder, int position) {
        String imageUrl=goodConditionsList[position];
        Log.d("TAG", "onBindViewHolder: "+imageUrl);
        if (!imageUrl.equals("null") ){
            String newImageUrl=goodConditionsList[position].replace(" ../wwwroot/","http://schoolgradingapiservices.bsninfotech.net/");
            Log.d("TAG", "onBindViewHolder: "+newImageUrl);
            String newImageUrl2="http://schoolgradingapiservices.bsninfotech.net/UploadPhotos/ClassRoomDetails/GC1_1_0754202242910512476_205726.jpeg";
            Glide.with(context).load(newImageUrl2.trim()).into(holder.imageMain);
        }

    }

    @Override
    public int getItemCount() {
        return goodConditionsList.length;
    }

    public class OnlineImageVoewHolder extends RecyclerView.ViewHolder{
        ImageView imageMain;
        public OnlineImageVoewHolder(@NonNull View itemView) {
            super(itemView);
            imageMain=itemView.findViewById(R.id.imageMain);

        }
    }
}
