package com.bsn.staffAttendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.StaffAttendanceAdapterOnView;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Date_Wise_Attendance extends AppCompatActivity {
CardView cardViewDataPicker;
    DatePickerDialog datePickerDialog;
    TextView txtdate;
    Button btn_GetAttendance;
    ApplicationController applicationController;
    ImageView calenderView;
    RecyclerView recyclerViewDateWiseAttednView;
    String todayDate,fromdate;
    Dialog dialog;
    List<AttendanceStaff> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_wise_attendance);
        applicationController= (ApplicationController) getApplication();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cardViewDataPicker=findViewById(R.id.cardViewDataPicker);
        txtdate=findViewById(R.id.txtdate);
        btn_GetAttendance=findViewById(R.id.btn_GetAttendance);
        calenderView=findViewById(R.id.calenderView);
        recyclerViewDateWiseAttednView=findViewById(R.id.recyclerViewDateWiseAttednView);
        dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        recyclerViewDateWiseAttednView.setLayoutManager(new LinearLayoutManager(this));
        calenderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        setDate();

            }
        });

        btn_GetAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStaffListWithAttendance();

            }
        });
    }

    private void getStaffListWithAttendance() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
       
       
        Call<List<AttendanceStaff>> listCall=apiService.getStaff(paraGetStaff(fromdate,applicationController.getSchoolId()));
        listCall.enqueue(new Callback<List<AttendanceStaff>>() {
            @Override
            public void onResponse(Call<List<AttendanceStaff>> call, Response<List<AttendanceStaff>> response) {
                arrayList=response.body();

                recyclerViewDateWiseAttednView.setAdapter(new StaffAttendanceAdapterOnView(Date_Wise_Attendance.this,arrayList,fromdate));
                recyclerViewDateWiseAttednView.getViewTreeObserver().addOnPreDrawListener(
                        new ViewTreeObserver.OnPreDrawListener() {

                            @Override
                            public boolean onPreDraw() {
                                recyclerViewDateWiseAttednView.getViewTreeObserver().removeOnPreDrawListener(this);

                                for (int i = 0; i < recyclerViewDateWiseAttednView.getChildCount(); i++) {
                                    View v = recyclerViewDateWiseAttednView.getChildAt(i);
                                    v.setAlpha(0.0f);
                                    v.animate().alpha(1.0f)
                                            .setDuration(800)
                                            .setStartDelay(i * 400)
                                            .start();
                                }

                                return true;
                            }
                        });
            }

            @Override
            public void onFailure(Call<List<AttendanceStaff>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date date1 = new Date();
        fromdate=dateFormat1.format(date1);
        txtdate.setText(dateFormat.format(date));

    }

    private boolean setDate() {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        final int mYear = c.get(java.util.Calendar.YEAR);
        final int mDay = c.get(Calendar.MONTH);
        final int cDay = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(Date_Wise_Attendance.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        String todaysDate=dayOfMonth+ "-"+ (monthOfYear + 1) + "-" + year;
                        SimpleDateFormat spf=new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat spf1=new SimpleDateFormat("dd-MM-yyyy");
                        Date newDate= null;
                        Date newDate1= null;
                        try {
                            newDate = spf.parse(todaysDate);
                            newDate1 = spf.parse(todaysDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        spf= new SimpleDateFormat("yyyy-MM-dd");
                        spf1= new SimpleDateFormat("dd-MM-yyyy");
                        fromdate= spf.format(newDate);
                        txtdate.setText( spf1.format(newDate1));
                        getStaffListWithAttendance();
                    }
                }, mYear, mDay, cDay);

        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        datePickerDialog.show();
        return true;
    }

    private JsonObject paraGetStaff(String formattedDate, String schoolId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("AttendenceDate",formattedDate);
        jsonObject.addProperty("SchoolId",schoolId);

        return jsonObject;
    }
}