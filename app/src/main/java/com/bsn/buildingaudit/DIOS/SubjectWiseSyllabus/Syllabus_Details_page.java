package com.bsn.buildingaudit.DIOS.SubjectWiseSyllabus;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.Adapters.ThreeLevelListAdapter;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Syllabus_Details_page extends AppCompatActivity {
ExpandableListView expandableListView;

    String[] parent = new String[]{"Class 12th", "Class 11th", "Class 10th","Class 9th", "Class 8th", "Class 7th","Class 6th"};
    String[] q1 = new String[]{"Hindi", "English","Math", "Science","Art"};
    String[] q2 = new String[]{"Chapter1", "Chapter2","Chapter3", "Chapter4","Chapter5"};



    LinkedHashMap<String, String[]> thirdLevelq1 = new LinkedHashMap<>();
    LinkedHashMap<String, String[]> thirdLevelq2 = new LinkedHashMap<>();
    LinkedHashMap<String, String[]> thirdLevelq3 = new LinkedHashMap<>();
    /**
     * Second level array list
     */
    List<String[]> secondLevel = new ArrayList<>();
    /**
     * Inner level data
     */
    List<LinkedHashMap<String, String[]>> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_details_page);
        expandableListView=findViewById(R.id.expandableListView);
        setUpAdapter();

    }

    private void setUpAdapter() {
        secondLevel.add(q1);
        secondLevel.add(q1);
        secondLevel.add(q1);
        thirdLevelq1.put(q1[0], q1);
        thirdLevelq1.put(q1[1], q1);
        thirdLevelq2.put(q1[2], q1);
        thirdLevelq2.put(q1[3], q1);
        thirdLevelq3.put(q1[4], q1);


        data.add(thirdLevelq1);
        data.add(thirdLevelq2);
        data.add(thirdLevelq3);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        //passing three level of information to constructor
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, parent, secondLevel, data);
        expandableListView.setAdapter(threeLevelListAdapterAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


    }
}
