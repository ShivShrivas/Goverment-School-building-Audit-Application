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

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {
    Context context;
    ArrayList<String> outDoorGames;
    public GamesAdapter(Context context, ArrayList<String> outDoorGames) {
        this.context=context;
        this.outDoorGames=outDoorGames;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.games_card,parent,false);
        return new GamesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        holder.textView10.setText(outDoorGames.get(position));
    }

    @Override
    public int getItemCount() {
        return outDoorGames.size();
    }

    public class GamesViewHolder extends RecyclerView.ViewHolder{
        TextView textView10;
        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView10=itemView.findViewById(R.id.textView10);
        }
    }
}
