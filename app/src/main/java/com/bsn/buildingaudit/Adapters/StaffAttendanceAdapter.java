package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.Model.AttendanceType;
import com.bsn.buildingaudit.Model.StaffAttendanceSubmitModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffAttendanceAdapter extends RecyclerView.Adapter<StaffAttendanceAdapter.StaffAttendanceViewHolder> {
    Context  context;
    List<AttendanceType> arrayList1;
    List<AttendanceStaff> arrayList;
    String formattedDate;
    public  static List<StaffAttendanceSubmitModel> staffAttendanceSubmitModels=new ArrayList<>();
    public StaffAttendanceAdapter(Context context, List<AttendanceStaff> arrayList, String formattedDate) {
        this.arrayList=arrayList;
        this.context=context;
        this.formattedDate=formattedDate;
    }

    @NonNull
    @Override
    public StaffAttendanceAdapter.StaffAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_attendance_card,parent,false);
        return new StaffAttendanceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StaffAttendanceAdapter.StaffAttendanceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.staffname.setText(arrayList.get(position).getStaffName());
        holder.staffDeg.setText(arrayList.get(position).getStaffDesignation());
        holder.textView5.setText(String.valueOf(position+1));
        holder.spinnerGrantUnderScheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String id = arrayList.get(position).getRecordID();
                int position1 = -1;
                for (int j = 0; j < staffAttendanceSubmitModels.size(); j++) {
                    if (staffAttendanceSubmitModels.get(j).getRecordID().equals(id) ) {
                        position1 = j;
                        break;  // uncomment to get the first instance
                    }
                }

                AttendanceType attendanceType= (AttendanceType) adapterView.getSelectedItem();
                StaffAttendanceSubmitModel staffAttendanceSubmitModel=new StaffAttendanceSubmitModel();
                staffAttendanceSubmitModel.setAttendenceStatusID(attendanceType.getRecordID());
                staffAttendanceSubmitModel.setAttendenceDate(formattedDate);
                staffAttendanceSubmitModel.setRecordID(arrayList.get(position).getRecordID());
                if (position1 == -1) {
                    Log.e("TAG", "Object not found in List");
                } else {
                   staffAttendanceSubmitModels.remove(position1);
                }

                staffAttendanceSubmitModels.add(staffAttendanceSubmitModel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StaffAttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView staffname,staffDeg,textView5;
        EditText desc;
        AttendanceTypeAdapter arrayAdapter4;
        Spinner spinnerGrantUnderScheme;
        public StaffAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            spinnerGrantUnderScheme =itemView.findViewById(R.id.spinnerGrantUnderScheme);
            staffDeg=itemView.findViewById(R.id.staffDeg);
            staffname=itemView.findViewById(R.id.staffname);
            textView5=itemView.findViewById(R.id.textView5);
            RestClient restClient=new RestClient();
            ApiService apiService=restClient.getApiService();
            Call<List<AttendanceType>> call=apiService.getAttendanceType();
            call.enqueue(new Callback<List<AttendanceType>>() {
                @Override
                public void onResponse(Call<List<AttendanceType>> call, Response<List<AttendanceType>> response) {
                    arrayList1=  response.body();
                    Resources res = context.getResources();
                    arrayAdapter4=new AttendanceTypeAdapter(context, android.R.layout.simple_spinner_item,arrayList1 ,res);
                    arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
                    spinnerGrantUnderScheme.setAdapter(arrayAdapter4);
                    arrayAdapter4.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<AttendanceType>> call, Throwable t) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
