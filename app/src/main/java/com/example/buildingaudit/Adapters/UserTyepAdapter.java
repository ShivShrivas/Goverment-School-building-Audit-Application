package com.example.buildingaudit.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buildingaudit.MainActivity;
import com.example.buildingaudit.Model.GetUserType;
import com.example.buildingaudit.R;

import java.util.List;

import retrofit2.Callback;

public class UserTyepAdapter  extends ArrayAdapter<String> {
    private Context applicationContext;
    private  int spinner_class_item;
    private List selectSite;
    private Resources res;
    LayoutInflater inflater;
    GetUserType tempValues=null;
    public UserTyepAdapter(Context applicationContext, int spinner_class_item, List selectSite, Resources res) {
        super(applicationContext,spinner_class_item,selectSite);
        this.applicationContext=applicationContext;
        this.res=res;
        this.selectSite=selectSite;
        this.spinner_class_item=spinner_class_item;
        inflater= (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



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
        tempValues= (GetUserType) selectSite.get(position);
        TextView text_cname        = (TextView)row.findViewById(R.id.text_cname);
        TextView text_cID          = (TextView)row.findViewById(R.id.text_cID);
        text_cname.setText(tempValues.getTypedesc());
        text_cID.setText(tempValues.getTypevalue());

        return row;
    }
}

