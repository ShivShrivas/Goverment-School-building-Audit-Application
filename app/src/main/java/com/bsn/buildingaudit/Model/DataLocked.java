package com.bsn.buildingaudit.Model;

public class DataLocked {
    String StatusName;
    int STATUS;

    public DataLocked() {
    }

    public DataLocked(String statusName, int STATUS) {
        StatusName = statusName;
        this.STATUS = STATUS;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }
}
