package com.example.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Activies.UpdatedetailsTypeThree;
import com.example.buildingaudit.R;

import java.util.ArrayList;

public class updateDetailsTypeThreeAdapter extends RecyclerView.Adapter<updateDetailsTypeThreeAdapter.UpdateDetailsThreeHolder> {
    Context context;
    String[] subjectUpdateDetailsThree;
    public updateDetailsTypeThreeAdapter(Context context, String[] subjectUpdateDetailsThree) {
        this.context=context;
        this.subjectUpdateDetailsThree=subjectUpdateDetailsThree;
    }

    @NonNull
    @Override
    public UpdateDetailsThreeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.update_details_three_item_card,parent,false);
      return new UpdateDetailsThreeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateDetailsThreeHolder holder, int position) {
        holder.subject.setText(subjectUpdateDetailsThree[position]);
        ArrayList<String> arrayListSpinner = new ArrayList<>();

        arrayListSpinner.add("Yes");
        arrayListSpinner.add("No");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        holder.spinnerRoomAvailabel.setAdapter(arrayAdapter);

        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Good Condition");
        arrayListSpinner2.add("Minor Repairing");
        arrayListSpinner2.add("Major repairing");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, arrayListSpinner2);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);

        holder.spinnerRoomStatus.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListSpinner3 = new ArrayList<>();
        arrayListSpinner3.add("Fully Equipped");
        arrayListSpinner3.add("Partially equipped");
        arrayListSpinner3.add("Not Equipped");

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, arrayListSpinner3);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);

        holder.spinnerRoomCondition.setAdapter(arrayAdapter3);
    }

    @Override
    public int getItemCount() {
        return subjectUpdateDetailsThree.length;
    }

    public class UpdateDetailsThreeHolder extends RecyclerView.ViewHolder {
        TextView subject;
        Spinner spinnerRoomAvailabel,spinnerRoomStatus,spinnerRoomCondition;
        public UpdateDetailsThreeHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.subjectTxt);
            spinnerRoomAvailabel=itemView.findViewById(R.id.spinnerRoomAvailabel);
            spinnerRoomStatus=itemView.findViewById(R.id.spinnerRoomStatus);
            spinnerRoomCondition=itemView.findViewById(R.id.spinnerRoomCondition);
        }
    }
}
