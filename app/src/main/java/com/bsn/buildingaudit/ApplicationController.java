package com.bsn.buildingaudit;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.bsn.buildingaudit.Model.AttendanceType;
import com.bsn.buildingaudit.Model.BoundryType;
import com.bsn.buildingaudit.Model.InstallationYear;

import java.util.List;

public class ApplicationController extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
    List<BoundryType> boundryTypes;
    List<InstallationYear> installationYears;
    String longitude;
    String Usertypeid;
    String latitude;
    String Usertype;
String PeriodID;
String SchoolId;
String userid;
String username;
String divid;
String distid;
String blockid;
String  schoolName;
String schoolAddress;
String phoneNumber;
int DataLocked;
public  static List<AttendanceType> attendanceTypeList;

    public static List<AttendanceType> getAttendanceTypeList() {
        return attendanceTypeList;
    }

    public void setAttendanceTypeList(List<AttendanceType> attendanceTypeList) {
        ApplicationController.attendanceTypeList = attendanceTypeList;
    }

    public int getDataLocked() {
        return DataLocked;
    }

    public void setDataLocked(int dataLocked) {
        DataLocked = dataLocked;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<BoundryType> getBoundryTypes() {
        return boundryTypes;
    }

    public void setBoundryTypes(List<BoundryType> boundryTypes) {
        this.boundryTypes = boundryTypes;
    }


    public List<InstallationYear> getInstallationYears() {
        return installationYears;
    }

    public void setInstallationYears(List<InstallationYear> installationYears) {
        this.installationYears = installationYears;
    }

    public String getUsertypeid() {
        return Usertypeid;
    }

    public void setUsertypeid(String usertypeid) {
        Usertypeid = usertypeid;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getUsertype() {
        return Usertype;
    }

    public void setUsertype(String usertype) {
        Usertype = usertype;
    }

    public String getPeriodID() {
        return "26";
    }

    public void setPeriodID(String periodID) {
        PeriodID = periodID;
    }

    public String getSchoolId() {
        return SchoolId;
    }

    public void setSchoolId(String schoolId) {
        SchoolId = schoolId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDivid() {
        return divid;
    }

    public void setDivid(String divid) {
        this.divid = divid;
    }

    public String getDistid() {
        return distid;
    }

    public void setDistid(String distid) {
        this.distid = distid;
    }

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }
}
