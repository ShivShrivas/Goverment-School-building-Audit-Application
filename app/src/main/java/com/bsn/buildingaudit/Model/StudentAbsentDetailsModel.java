package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StudentAbsentDetailsModel {

    @SerializedName("ClassName")
    @Expose
    private String className;
    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;
    @SerializedName("PeriodID")
    @Expose
    private Integer periodID;
    @SerializedName("NameOfStudent")
    @Expose
    private String nameOfStudent;
    @SerializedName("NameOfFather")
    @Expose
    private String nameOfFather;
    @SerializedName("AbsentDays")
    @Expose
    private Integer absentDays;
    @SerializedName("Reason")
    @Expose
    private String reason;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getPeriodID() {
        return periodID;
    }

    public void setPeriodID(Integer periodID) {
        this.periodID = periodID;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public void setNameOfStudent(String nameOfStudent) {
        this.nameOfStudent = nameOfStudent;
    }

    public String getNameOfFather() {
        return nameOfFather;
    }

    public void setNameOfFather(String nameOfFather) {
        this.nameOfFather = nameOfFather;
    }

    public Integer getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(Integer absentDays) {
        this.absentDays = absentDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}