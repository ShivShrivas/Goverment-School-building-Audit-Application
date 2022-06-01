package com.bsn.buildingaudit.Model;

public class AttendanceType {
    String  RecordID ,AttendenceStatus;

    public AttendanceType() {
    }

    public AttendanceType(String recordID, String attendenceStatus) {
        RecordID = recordID;
        AttendenceStatus = attendenceStatus;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }

    public String getAttendenceStatus() {
        return AttendenceStatus;
    }

    public void setAttendenceStatus(String attendenceStatus) {
        AttendenceStatus = attendenceStatus;
    }
}
