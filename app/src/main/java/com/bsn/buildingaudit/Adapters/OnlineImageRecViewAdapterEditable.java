package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.ConstantValues.ConstantFile;
import com.bsn.buildingaudit.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OnlineImageRecViewAdapterEditable extends RecyclerView.Adapter<OnlineImageRecViewAdapterEditable.HistoryItemViewHolder> {
    Context context;
    ArrayList<String> imageUrlString;
    public static ArrayList<String> deletedUrls=new ArrayList<>();
    public OnlineImageRecViewAdapterEditable(Context context, ArrayList<String> staffPhotoPathList) {
        this.context=context;
        this.imageUrlString=staffPhotoPathList;

    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card_layout,parent,false);
        return new HistoryItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String dummyUrl=imageUrlString.get(position);
        try{
            Log.d("TAG", "onBindViewHolder: "+dummyUrl.equals("")+"///"+dummyUrl.isEmpty());
            String newImgUrl=dummyUrl.replace(" ../wwwroot", ConstantFile.IMAGE_BASE_URL);
            Log.d("TAG", "onBindViewHolder: "+ URLUtil.isValidUrl(newImgUrl));

                Log.d("TAG", "onBindViewHolder: "+ URLUtil.isValidUrl(newImgUrl));

                String newUrl2=newImgUrl.replaceAll("\"","");
                Glide.with(holder.imageMain1).load(newUrl2).into(holder.imageMain1);
                holder.ImageCross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deletedUrls.add(imageUrlString.get(position).trim());
                        imageUrlString.remove(imageUrlString.get(position));
                        notifyDataSetChanged();
                    }
                });


        }catch (Exception e){

        }

        holder.ImageCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedUrls.add(imageUrlString.get(position).trim());
                imageUrlString.remove(imageUrlString.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrlString.size();
    }


    public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMain1,ImageCross;
        public HistoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMain1=itemView.findViewById(R.id.imageMain);
            ImageCross=itemView.findViewById(R.id.ImageCross);
        }
    }
}
