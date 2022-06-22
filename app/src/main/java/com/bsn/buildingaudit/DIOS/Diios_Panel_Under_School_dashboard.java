package com.bsn.buildingaudit.DIOS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.DIOS.CampusBeautification.Campus_Beautification_Details;
import com.bsn.buildingaudit.DIOS.ExtraActivitiesDetails.ExtraActivity_Dashboard;
import com.bsn.buildingaudit.DIOS.SchoolResultDetails.Dashboard_School_Result_Detail;
import com.bsn.buildingaudit.DIOS.SchoolStaff.Dios_StaffDetails;
import com.bsn.buildingaudit.DIOS.StudentEnrollment.Student_Enrollment_Details;
import com.bsn.buildingaudit.DIOS.SubjectWiseSyllabus.Syllabus_Details_page;
import com.bsn.buildingaudit.DIOS.co_curricular_activities.Co_Curricular_Dashboard;
import com.bsn.buildingaudit.DIOS.physicalStatus.Physical_Status_DIOS_Dashboard;
import com.bsn.buildingaudit.R;

public class Diios_Panel_Under_School_dashboard extends AppCompatActivity {
    ApplicationController applicationController;
CardView dashCardBtn_SchoolDetails,dashCardBtn_SchoolStaff,dashCardBtn_SchoolResultDetails,diosPhysicalStatus;
CardView coCurriculatCard,ExtraDetailsCard,campusBeautificationCard,dashCardBtn_SubjectWiseDetails;
TextView textView8;
String localSchoolId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diios_panel_under_school_dashboard);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));

        Log.d("TAG", "onCreate: "+localSchoolId);
        dashCardBtn_SchoolDetails=findViewById(R.id.dashCardBtn_StudentEnrollment);
        dashCardBtn_SubjectWiseDetails=findViewById(R.id.dashCardBtn_SubjectWiseDetails);
        dashCardBtn_SchoolStaff=findViewById(R.id.dashCardBtn_SchoolStaff);
        dashCardBtn_SchoolResultDetails=findViewById(R.id.dashCardBtn_SchoolResultDetails);
        textView8=findViewById(R.id.textView8);
        coCurriculatCard=findViewById(R.id.coCurriculatCard);
        ExtraDetailsCard=findViewById(R.id.ExtraDetailsCard);
        diosPhysicalStatus=findViewById(R.id.diosPhysicalStatus);
        campusBeautificationCard=findViewById(R.id.campusBeautificationCard);
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());

        dashCardBtn_SchoolDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Student_Enrollment_Details.class));
            }
        });
        dashCardBtn_SchoolStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Dios_StaffDetails.class));
            }
        });
        dashCardBtn_SchoolResultDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Dashboard_School_Result_Detail.class));
            }
        });
        coCurriculatCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Co_Curricular_Dashboard.class));
            }
        });

        ExtraDetailsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, ExtraActivity_Dashboard.class));
            }
        });


        diosPhysicalStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Physical_Status_DIOS_Dashboard.class));
            }
        });


        campusBeautificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Campus_Beautification_Details.class));
            }
        });



        dashCardBtn_SubjectWiseDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, Syllabus_Details_page.class));
            }
        });


    }
}