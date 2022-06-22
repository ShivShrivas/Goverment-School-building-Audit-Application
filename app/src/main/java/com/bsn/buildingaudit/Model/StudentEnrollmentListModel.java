package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentEnrollmentListModel {

    @SerializedName("StudentStrengthId")
    @Expose
    private Integer studentStrengthId;


    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;


    @SerializedName("PeriodID")
    @Expose
    private Integer periodID;


    @SerializedName("YearId")
    @Expose
    private Integer yearId;


    @SerializedName("ClassID")
    @Expose
    private Integer classID;


    @SerializedName("ClassName")
    @Expose
    private String className;


    @SerializedName("NoofEnroBoy")
    @Expose
    private Integer noofEnroBoy;


    @SerializedName("NoofEnroGirl")
    @Expose
    private Integer noofEnroGirl;


    @SerializedName("AttenMonth1Boys")
    @Expose
    private Integer attenMonth1Boys;


    @SerializedName("AttenMonth2Boys")
    @Expose
    private Integer attenMonth2Boys;


    @SerializedName("AttenMonth3Boys")
    @Expose
    private Integer attenMonth3Boys;


    @SerializedName("AttenMonth1Girls")
    @Expose
    private Integer attenMonth1Girls;


    @SerializedName("AttenMonth2Girls")
    @Expose
    private Integer attenMonth2Girls;


    @SerializedName("AttenMonth3Girls")
    @Expose
    private Integer attenMonth3Girls;

    @SerializedName("AbsentFrom7Days")
    @Expose
    private Integer absentFrom7Days;


    @SerializedName("AbsentFrom15Days")
    @Expose
    private Integer absentFrom15Days;

    public Integer getStudentStrengthId() {
        return studentStrengthId;
    }

    public void setStudentStrengthId(Integer studentStrengthId) {
        this.studentStrengthId = studentStrengthId;
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

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getNoofEnroBoy() {
        return noofEnroBoy;
    }

    public void setNoofEnroBoy(Integer noofEnroBoy) {
        this.noofEnroBoy = noofEnroBoy;
    }

    public Integer getNoofEnroGirl() {
        return noofEnroGirl;
    }

    public void setNoofEnroGirl(Integer noofEnroGirl) {
        this.noofEnroGirl = noofEnroGirl;
    }

    public Integer getAttenMonth1Boys() {
        return attenMonth1Boys;
    }

    public void setAttenMonth1Boys(Integer attenMonth1Boys) {
        this.attenMonth1Boys = attenMonth1Boys;
    }

    public Integer getAttenMonth2Boys() {
        return attenMonth2Boys;
    }

    public void setAttenMonth2Boys(Integer attenMonth2Boys) {
        this.attenMonth2Boys = attenMonth2Boys;
    }

    public Integer getAttenMonth3Boys() {
        return attenMonth3Boys;
    }

    public void setAttenMonth3Boys(Integer attenMonth3Boys) {
        this.attenMonth3Boys = attenMonth3Boys;
    }

    public Integer getAttenMonth1Girls() {
        return attenMonth1Girls;
    }

    public void setAttenMonth1Girls(Integer attenMonth1Girls) {
        this.attenMonth1Girls = attenMonth1Girls;
    }

    public Integer getAttenMonth2Girls() {
        return attenMonth2Girls;
    }

    public void setAttenMonth2Girls(Integer attenMonth2Girls) {
        this.attenMonth2Girls = attenMonth2Girls;
    }

    public Integer getAttenMonth3Girls() {
        return attenMonth3Girls;
    }

    public void setAttenMonth3Girls(Integer attenMonth3Girls) {
        this.attenMonth3Girls = attenMonth3Girls;
    }

    public Integer getAbsentFrom7Days() {
        return absentFrom7Days;
    }

    public void setAbsentFrom7Days(Integer absentFrom7Days) {
        this.absentFrom7Days = absentFrom7Days;
    }

    public Integer getAbsentFrom15Days() {
        return absentFrom15Days;
    }

    public void setAbsentFrom15Days(Integer absentFrom15Days) {
        this.absentFrom15Days = absentFrom15Days;
    }

}