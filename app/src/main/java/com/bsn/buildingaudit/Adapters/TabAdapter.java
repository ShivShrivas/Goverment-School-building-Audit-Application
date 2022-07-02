package com.bsn.buildingaudit.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bsn.buildingaudit.DIOS.SubjectWiseSyllabus.DynamicFragment;
import com.bsn.buildingaudit.Model.SubjectWiseSyllabusModel;

import java.util.ArrayList;

public class TabAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    ArrayList<SubjectWiseSyllabusModel> arrayList=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();

    public TabAdapter(FragmentManager fm, int NumOfTabs, ArrayList<SubjectWiseSyllabusModel> arrayList, ArrayList<String> arrayList1) {
        super(fm);
        this.arrayList=arrayList;
        this.arrayList1=arrayList1;
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return DynamicFragment.addfrag(position,arrayList,arrayList1);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}