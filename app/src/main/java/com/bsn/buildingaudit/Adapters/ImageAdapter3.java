package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Activies.UpdateDetailTypeOne;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class ImageAdapter3 extends RecyclerView.Adapter<ImageAdapter3.ImageViewHolder3> {
    Context context;
    ArrayList<Bitmap> arrayList=new ArrayList<>();

    public ImageAdapter3(Context context, ArrayList<Bitmap> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ImageAdapter3.ImageViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_card_layout,parent,false);
        return new ImageAdapter3.ImageViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter3.ImageViewHolder3 holder, @SuppressLint("RecyclerView") int position) {
        holder.mainImageView.setImageBitmap(arrayList.get(position));
        holder.CrossImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateDetailTypeOne updateDetailTypeOne=new UpdateDetailTypeOne();
                arrayList.remove(position);
                updateDetailTypeOne.arrayListImages1=arrayList;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ImageViewHolder3 extends RecyclerView.ViewHolder {
        ImageView mainImageView,CrossImageView;
        public ImageViewHolder3(@NonNull View itemView) {
            super(itemView);
            mainImageView=itemView.findViewById(R.id.imageMain);
            CrossImageView=itemView.findViewById(R.id.ImageCross);

        }
    }
}

