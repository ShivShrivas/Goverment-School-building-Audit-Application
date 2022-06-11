package com.bsn.buildingaudit.Model;

public class StudentAttendanceByMonths {
    String month,girlsCount,BoysCount;

    public StudentAttendanceByMonths() {
    }

    public StudentAttendanceByMonths(String month, String girlsCount, String boysCount) {
        this.month = month;
        this.girlsCount = girlsCount;
        BoysCount = boysCount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getGirlsCount() {
        return girlsCount;
    }

    public void setGirlsCount(String girlsCount) {
        this.girlsCount = girlsCount;
    }

    public String getBoysCount() {
        return BoysCount;
    }

    public void setBoysCount(String boysCount) {
        BoysCount = boysCount;
    }
}

