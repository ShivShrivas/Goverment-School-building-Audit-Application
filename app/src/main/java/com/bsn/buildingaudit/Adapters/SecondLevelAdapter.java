
package com.bsn.buildingaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bsn.buildingaudit.R;

import java.util.List;


public class SecondLevelAdapter extends BaseExpandableListAdapter {
    private Context context;


    List<String[]> data;

    String[] headers;

    ImageView ivGroupIndicator;


    public SecondLevelAdapter(Context context, String[] headers, List<String[]> data) {
        this.context = context;
        this.data = data;
        this.headers = headers;

    }

    @Override
    public Object getGroup(int groupPosition) {

        return headers[groupPosition];
    }

    @Override
    public int getGroupCount() {

        return headers.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.list_item_new, null);
        TextView text = (TextView) convertView.findViewById(R.id.expandedListItem);
        text.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float, 0,0, 0);
        String groupText = getGroup(groupPosition).toString();
        text.setText(groupText);
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        String[] childData;

        childData = data.get(groupPosition);


        return childData[childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group, null);

        TextView textView = (TextView) convertView.findViewById(R.id.listTitle);

        String[] childArray = data.get(0);

        String text = childArray[childPosition];

        textView.setText(text);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String[] children = data.get(0);


        return children.length;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}