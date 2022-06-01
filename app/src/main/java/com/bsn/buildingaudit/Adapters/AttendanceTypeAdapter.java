package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsn.buildingaudit.Model.AttendanceType;
import com.bsn.buildingaudit.R;

import java.util.List;

public class AttendanceTypeAdapter extends ArrayAdapter<String> {
    Context context;
    int simple_spinner_item;
    List attendanceTypeList;
    Resources res;
    LayoutInflater inflater;
    AttendanceType tempValues=null;
    public AttendanceTypeAdapter(Context context, int simple_spinner_item, List attendanceTypeList, Resources res) {
        super(context,simple_spinner_item,attendanceTypeList);
        this.attendanceTypeList=attendanceTypeList;
        this.context=context;
        this.simple_spinner_item=simple_spinner_item;
        this.res=res;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    private View getCustomView(int position, View convertView, ViewGroup parent) {
        View row=inflater.inflate(R.layout.spinner_card_orange,parent,false);
        tempValues= (AttendanceType) attendanceTypeList.get(position);
        TextView text_cname        = (TextView)row.findViewById(R.id.text_cname);
        TextView text_cID          = (TextView)row.findViewById(R.id.text_cID);
        text_cname.setText(tempValues.getAttendenceStatus());
        text_cID.setText(tempValues.getRecordID());

        return row;
    }
}
