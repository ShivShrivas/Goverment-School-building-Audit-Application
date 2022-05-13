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

import com.bsn.buildingaudit.Model.GetQuaterType;
import com.bsn.buildingaudit.R;

import java.util.List;

public class QuaterTypeAdapter extends ArrayAdapter<String> {
private Context applicationContext;
private  int spinner_class_item;
private List selectSite;
private Resources res;
        LayoutInflater inflater;
        GetQuaterType tempValues=null;
public QuaterTypeAdapter(Context applicationContext, int spinner_class_item, List selectSite, Resources res) {
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
        tempValues= (GetQuaterType) selectSite.get(position);
        TextView text_cname        = (TextView)row.findViewById(R.id.text_cname);
        TextView text_cID          = (TextView)row.findViewById(R.id.text_cID);
        text_cname.setText(tempValues.getPeriodText());
        text_cID.setText(tempValues.getPeriodId());

        return row;
        }
        }

