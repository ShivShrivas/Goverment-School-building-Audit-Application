package com.bsn.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Model.StudentAbsentDetailsModel;
import com.bsn.buildingaudit.Model.StudentAttendanceByMonths;
import com.bsn.buildingaudit.Model.StudentEnrollmentListModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_PresenceAdapter extends RecyclerView.Adapter<Student_PresenceAdapter.Student_PersenceViewHolder> {
    Context context;
    ArrayList<StudentEnrollmentListModel> arrayList2;
    ArrayList<StudentAttendanceByMonths> arrayList1=new ArrayList<>();
    public Student_PresenceAdapter(Context context, ArrayList<StudentEnrollmentListModel> arrayList2) {
        this.context=context;
        this.arrayList2=arrayList2;
    }

    @NonNull
    @Override
    public Student_PersenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student_presence,parent,false);
        return new Student_PersenceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Student_PersenceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView6.setText(arrayList2.get(position).getClassName());
        holder.absentFromFifteenDays.setText(arrayList2.get(position).getAbsentFrom7Days().toString());
        holder.absentFromthisryDays.setText(arrayList2.get(position).getAbsentFrom15Days().toString());
        holder.boysEnrollmentCount.setText(arrayList2.get(position).getNoofEnroBoy().toString());
        holder.girlsEnrollmentCount.setText(arrayList2.get(position).getNoofEnroGirl().toString());
                arrayList1.clear();
            StudentAttendanceByMonths studentAttendanceByMonths=new StudentAttendanceByMonths("April",arrayList2.get(position).getAttenMonth1Girls().toString(),arrayList2.get(position).getAttenMonth1Boys().toString());
            StudentAttendanceByMonths studentAttendanceByMonths1=new StudentAttendanceByMonths("May",arrayList2.get(position).getAttenMonth2Girls().toString(),arrayList2.get(position).getAttenMonth2Boys().toString());
            StudentAttendanceByMonths studentAttendanceByMonths2=new StudentAttendanceByMonths("June",arrayList2.get(position).getAttenMonth3Girls().toString(),arrayList2.get(position).getAttenMonth3Boys().toString());
            arrayList1.add(studentAttendanceByMonths);
            arrayList1.add(studentAttendanceByMonths1);
            arrayList1.add(studentAttendanceByMonths2);
            holder.sevenDaysAbsentSheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog2(context,58);
                }
            });
            holder.fifteenDaysAbsentSheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

    }
    public void showDialog2(Context activity, Integer studentStrengthId) {
        Dialog dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.student_list_dialog_for_fifteendays);
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        Button btndialog = (Button) dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        ArrayList<StudentAbsentDetailsModel> arrayList=new ArrayList<StudentAbsentDetailsModel>();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("StudentStrengthID",studentStrengthId.toString());
        Log.d("TAG", "showDialog2: "+jsonObject);
        Call<List<StudentAbsentDetailsModel>> call=apiService.getStudentAbsentDetails(jsonObject);
        call.enqueue(new Callback<List<StudentAbsentDetailsModel>>() {
            @Override
            public void onResponse(Call<List<StudentAbsentDetailsModel>> call, Response<List<StudentAbsentDetailsModel>> response) {
                arrayList.addAll(response.body());
                Log.d("TAG", "onResponse: "+response.body());
                AdapterStudentAbsentDetails adapterRe = new AdapterStudentAbsentDetails(context, arrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapterRe);
            }

            @Override
            public void onFailure(Call<List<StudentAbsentDetailsModel>> call, Throwable t) {

            }
        });



        dialog.show();
    }
    @Override
    public int getItemCount() {
        return arrayList2.size();
    }

    public class Student_PersenceViewHolder extends RecyclerView.ViewHolder {
        RecyclerView nastedRecViewMonths;
        NastedAttendanceAdapter adapter;
        LinearLayout sevenDaysAbsentSheet,fifteenDaysAbsentSheet;
        TextView textView6,absentFromthisryDays,absentFromFifteenDays,girlsEnrollmentCount,boysEnrollmentCount;
        public Student_PersenceViewHolder(@NonNull View itemView) {
            super(itemView);
            nastedRecViewMonths=itemView.findViewById(R.id.nastedRecViewMonths);
            textView6=itemView.findViewById(R.id.textView6);
            absentFromthisryDays=itemView.findViewById(R.id.absentFromthisryDays);
            fifteenDaysAbsentSheet=itemView.findViewById(R.id.fifteenDaysAbsentSheet);
            sevenDaysAbsentSheet=itemView.findViewById(R.id.sevenDaysAbsentSheet);
            absentFromFifteenDays=itemView.findViewById(R.id.absentFromFifteenDays);
            girlsEnrollmentCount=itemView.findViewById(R.id.girlsEnrollmentCount);
            boysEnrollmentCount=itemView.findViewById(R.id.boysEnrollmentCount);


            adapter=new NastedAttendanceAdapter(context,arrayList1);
            nastedRecViewMonths.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
            nastedRecViewMonths.setAdapter(adapter);


        }
    }
}
