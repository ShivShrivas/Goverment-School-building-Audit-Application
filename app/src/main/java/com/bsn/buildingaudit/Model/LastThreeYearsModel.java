package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastThreeYearsModel {

    @SerializedName("SchoolID")
    @Expose
    private Integer schoolID;
    @SerializedName("PeriodId")
    @Expose
    private Integer periodId;
    @SerializedName("DistrictLevelTopperStatus")
    @Expose
    private String districtLevelTopperStatus;
    @SerializedName("StateLevelTopperStatus")
    @Expose
    private String stateLevelTopperStatus;
    @SerializedName("CompExamSelection")
    @Expose
    private String compExamSelection;
    @SerializedName("OtherCompExamName")
    @Expose
    private Object otherCompExamName;
    @SerializedName("StatusId")
    @Expose
    private Object statusId;
    @SerializedName("ResultDetailsID")
    @Expose
    private Integer resultDetailsID;
    @SerializedName("AppearedStudent")
    @Expose
    private Integer appearedStudent;
    @SerializedName("PassedStudent")
    @Expose
    private Integer passedStudent;
    @SerializedName("PassedStudentPert")
    @Expose
    private Double passedStudentPert;
    @SerializedName("FirstDivStudent")
    @Expose
    private Integer firstDivStudent;
    @SerializedName("SecDivStudent")
    @Expose
    private Integer secDivStudent;
    @SerializedName("ThirdDivStudent")
    @Expose
    private Integer thirdDivStudent;
    @SerializedName("YearID")
    @Expose
    private Integer yearID;
    @SerializedName("Year")
    @Expose
    private Integer year;
    @SerializedName("Affiliation")
    @Expose
    private Integer affiliation;
    @SerializedName("Class")
    @Expose
    private String _class;

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public String getDistrictLevelTopperStatus() {
        return districtLevelTopperStatus;
    }

    public void setDistrictLevelTopperStatus(String districtLevelTopperStatus) {
        this.districtLevelTopperStatus = districtLevelTopperStatus;
    }

    public String getStateLevelTopperStatus() {
        return stateLevelTopperStatus;
    }

    public void setStateLevelTopperStatus(String stateLevelTopperStatus) {
        this.stateLevelTopperStatus = stateLevelTopperStatus;
    }

    public String getCompExamSelection() {
        return compExamSelection;
    }

    public void setCompExamSelection(String compExamSelection) {
        this.compExamSelection = compExamSelection;
    }

    public Object getOtherCompExamName() {
        return otherCompExamName;
    }

    public void setOtherCompExamName(Object otherCompExamName) {
        this.otherCompExamName = otherCompExamName;
    }

    public Object getStatusId() {
        return statusId;
    }

    public void setStatusId(Object statusId) {
        this.statusId = statusId;
    }

    public Integer getResultDetailsID() {
        return resultDetailsID;
    }

    public void setResultDetailsID(Integer resultDetailsID) {
        this.resultDetailsID = resultDetailsID;
    }

    public Integer getAppearedStudent() {
        return appearedStudent;
    }

    public void setAppearedStudent(Integer appearedStudent) {
        this.appearedStudent = appearedStudent;
    }

    public Integer getPassedStudent() {
        return passedStudent;
    }

    public void setPassedStudent(Integer passedStudent) {
        this.passedStudent = passedStudent;
    }

    public Double getPassedStudentPert() {
        return passedStudentPert;
    }

    public void setPassedStudentPert(Double passedStudentPert) {
        this.passedStudentPert = passedStudentPert;
    }

    public Integer getFirstDivStudent() {
        return firstDivStudent;
    }

    public void setFirstDivStudent(Integer firstDivStudent) {
        this.firstDivStudent = firstDivStudent;
    }

    public Integer getSecDivStudent() {
        return secDivStudent;
    }

    public void setSecDivStudent(Integer secDivStudent) {
        this.secDivStudent = secDivStudent;
    }

    public Integer getThirdDivStudent() {
        return thirdDivStudent;
    }

    public void setThirdDivStudent(Integer thirdDivStudent) {
        this.thirdDivStudent = thirdDivStudent;
    }

    public Integer getYearID() {
        return yearID;
    }

    public void setYearID(Integer yearID) {
        this.yearID = yearID;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Integer affiliation) {
        this.affiliation = affiliation;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

}

