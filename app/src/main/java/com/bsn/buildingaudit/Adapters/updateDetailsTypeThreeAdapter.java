package com.bsn.buildingaudit.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class updateDetailsTypeThreeAdapter extends RecyclerView.Adapter<updateDetailsTypeThreeAdapter.UpdateDetailsThreeHolder> {

    Context context;
    String[] subjectUpdateDetailsThree;
    Activity activity;
    public updateDetailsTypeThreeAdapter(Context context, String[] subjectUpdateDetailsThree, Activity activity) {
        this.context=context;
        this.subjectUpdateDetailsThree=subjectUpdateDetailsThree;
        this.activity=activity;
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
        holder.imageUpoadPracticalSubjectWise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(context)
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                // permission is granted, open the camera

                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                context.startActivity(intent);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                // check for permanent denial of permission
                                if (response.isPermanentlyDenied()) {

                                    // navigate user to app settings
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                    intent.setData(uri);
                                    activity.startActivityForResult(intent,7);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectUpdateDetailsThree.length;
    }

    public class UpdateDetailsThreeHolder extends RecyclerView.ViewHolder {

        TextView subject;
        ImageView imageUpoadPracticalSubjectWise;
        RecyclerView recyclerPracticalLabSubjectWise;
        Spinner spinnerRoomAvailabel,spinnerRoomStatus,spinnerRoomCondition;
        public UpdateDetailsThreeHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.subjectTxt);
            spinnerRoomAvailabel=itemView.findViewById(R.id.spinnerPhysicslabAvailability);
            spinnerRoomStatus=itemView.findViewById(R.id.spinnerRoomStatus);
            imageUpoadPracticalSubjectWise=itemView.findViewById(R.id.imageUpoadPracticalSubjectWise);
            recyclerPracticalLabSubjectWise=itemView.findViewById(R.id.recyclerPracticalLabSubjectWise);
            spinnerRoomCondition=itemView.findViewById(R.id.spinnerRoomCondition);
        }
    }
}
