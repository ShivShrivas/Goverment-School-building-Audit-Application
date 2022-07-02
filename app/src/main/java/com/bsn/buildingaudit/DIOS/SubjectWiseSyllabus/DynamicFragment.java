package com.bsn.buildingaudit.DIOS.SubjectWiseSyllabus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.SyllabusWiseSubjectAdapter;
import com.bsn.buildingaudit.Model.SubjectWiseSyllabusModel;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;


public class DynamicFragment extends Fragment {
    View view;
    int val;
    TextView c;
    RecyclerView subjectRecViewFrag;
    ArrayList<SubjectWiseSyllabusModel> arrayList=new ArrayList<>();
    ArrayList<SubjectWiseSyllabusModel> arrayListClassWise=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();
    SyllabusWiseSubjectAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        val = getArguments().getInt("someInt", 0);
        arrayList = getArguments().getParcelableArrayList("array");
        arrayList1 = getArguments().getStringArrayList("array1");
        subjectRecViewFrag = view.findViewById(R.id.subjectRecViewFrag);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getClassName().equals(arrayList1.get(val))){
                arrayListClassWise.add(arrayList.get(i));
            }
        }
        subjectRecViewFrag.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new SyllabusWiseSubjectAdapter(getActivity(),arrayListClassWise);
        subjectRecViewFrag.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
    public static DynamicFragment addfrag(int val, ArrayList<SubjectWiseSyllabusModel> arrayList, ArrayList<String> arrayList1) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", val);
        args.putParcelableArrayList("array",arrayList);
        args.putStringArrayList("array1",arrayList1);
        fragment.setArguments(args);
        return fragment;
    }
}