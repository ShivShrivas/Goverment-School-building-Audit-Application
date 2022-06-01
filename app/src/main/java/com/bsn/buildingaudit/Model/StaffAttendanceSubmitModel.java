package com.bsn.buildingaudit.Model;

public class StaffAttendanceSubmitModel {
    String RecordID,AttendenceDate,AttendenceStatusID;

    public StaffAttendanceSubmitModel() {
    }

    public StaffAttendanceSubmitModel(String recordID, String attendenceDate, String attendenceStatusID) {

        RecordID = recordID;
        AttendenceDate = attendenceDate;
        AttendenceStatusID = attendenceStatusID;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }

    public String getAttendenceDate() {
        return AttendenceDate;
    }

    public void setAttendenceDate(String attendenceDate) {
        AttendenceDate = attendenceDate;
    }

    public String getAttendenceStatusID() {
        return AttendenceStatusID;
    }

    public void setAttendenceStatusID(String attendenceStatusID) {
        AttendenceStatusID = attendenceStatusID;
    }
}
