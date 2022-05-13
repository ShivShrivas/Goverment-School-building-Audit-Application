package com.example.buildingaudit.Model;

public class InstallationYear {

    String YearId;
    String Year;

    public InstallationYear() {
    }

    public InstallationYear(String yearId, String year) {
        YearId = yearId;
        Year = year;
    }

    public String getYearId() {
        return YearId;
    }

    public void setYearId(String yearId) {
        YearId = yearId;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}
