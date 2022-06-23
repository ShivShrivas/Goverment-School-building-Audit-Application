package com.bsn.buildingaudit.Model;

public class AttendanceStaff {
    String RecordID,StaffName,StaffDesignation,DateofDay,AttendenceStatusID,Lati,Longi;

    public AttendanceStaff() {
    }

    public AttendanceStaff(String recordID, String staffName, String staffDesignation, String dateofDay, String attendenceStatusID, String lati, String longi) {
        RecordID = recordID;
        StaffName = staffName;
        StaffDesignation = staffDesignation;
        DateofDay = dateofDay;
        AttendenceStatusID = attendenceStatusID;
        Lati = lati;
        Longi = longi;
    }

    public String getLati() {
        return Lati;
    }

    public void setLati(String lati) {
        Lati = lati;
    }

    public String getLongi() {
        return Longi;
    }

    public void setLongi(String longi) {
        Longi = longi;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public String getStaffDesignation() {
        return StaffDesignation;
    }

    public void setStaffDesignation(String staffDesignation) {
        StaffDesignation = staffDesignation;
    }

    public String getDateofDay() {
        return DateofDay;
    }

    public void setDateofDay(String dateofDay) {
        DateofDay = dateofDay;
    }

    public String getAttendenceStatusID() {
        return AttendenceStatusID;
    }

    public void setAttendenceStatusID(String attendenceStatusID) {
        AttendenceStatusID = attendenceStatusID;
    }
}
