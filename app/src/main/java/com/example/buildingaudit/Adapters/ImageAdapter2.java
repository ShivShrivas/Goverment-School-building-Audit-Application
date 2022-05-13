package com.example.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Activies.UpdateDetailTypeOne;
import com.example.buildingaudit.R;

import java.util.ArrayList;

public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.ImageViewHolder2> {
    Context context;
    ArrayList<Bitmap> arrayList=new ArrayList<>();

    public ImageAdapter2(Context context, ArrayList<Bitmap> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ImageAdapter2.ImageViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_card_layout,parent,false);
        return new ImageAdapter2.ImageViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter2.ImageViewHolder2 holder, @SuppressLint("RecyclerView") int position) {
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

    public class ImageViewHolder2 extends RecyclerView.ViewHolder {
        ImageView mainImageView,CrossImageView;
        public ImageViewHolder2(@NonNull View itemView) {
            super(itemView);
            mainImageView=itemView.findViewById(R.id.imageMain);
            CrossImageView=itemView.findViewById(R.id.ImageCross);

        }
    }
}

