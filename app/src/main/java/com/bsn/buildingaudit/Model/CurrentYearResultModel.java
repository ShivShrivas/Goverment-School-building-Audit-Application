package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentYearResultModel {

    @SerializedName("SCHOOLID")
    @Expose
    private Integer schoolid;
    @SerializedName("PERIODID")
    @Expose
    private Integer periodid;
    @SerializedName("CLASSNAME")
    @Expose
    private String classname;
    @SerializedName("TOTALENROLLED")
    @Expose
    private Integer totalenrolled;
    @SerializedName("FIRSTMAPPERED")
    @Expose
    private Integer firstmappered;
    @SerializedName("FIRSTMPASSED")
    @Expose
    private Integer firstmpassed;
    @SerializedName("SECONDMAPPERED")
    @Expose
    private Integer secondmappered;
    @SerializedName("SECONDMPASSED")
    @Expose
    private Integer secondmpassed;
    @SerializedName("HALFAPPEARED")
    @Expose
    private Integer halfappeared;
    @SerializedName("HALFPASSED")
    @Expose
    private Integer halfpassed;
    @SerializedName("YEARAPPEARED")
    @Expose
    private Integer yearappeared;
    @SerializedName("YEARPASSED")
    @Expose
    private Integer yearpassed;
    @SerializedName("STATUSID")
    @Expose
    private Object statusid;
    @SerializedName("REMARKS")
    @Expose
    private Object remarks;

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public Integer getPeriodid() {
        return periodid;
    }

    public void setPeriodid(Integer periodid) {
        this.periodid = periodid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getTotalenrolled() {
        return totalenrolled;
    }

    public void setTotalenrolled(Integer totalenrolled) {
        this.totalenrolled = totalenrolled;
    }

    public Integer getFirstmappered() {
        return firstmappered;
    }

    public void setFirstmappered(Integer firstmappered) {
        this.firstmappered = firstmappered;
    }

    public Integer getFirstmpassed() {
        return firstmpassed;
    }

    public void setFirstmpassed(Integer firstmpassed) {
        this.firstmpassed = firstmpassed;
    }

    public Integer getSecondmappered() {
        return secondmappered;
    }

    public void setSecondmappered(Integer secondmappered) {
        this.secondmappered = secondmappered;
    }

    public Integer getSecondmpassed() {
        return secondmpassed;
    }

    public void setSecondmpassed(Integer secondmpassed) {
        this.secondmpassed = secondmpassed;
    }

    public Integer getHalfappeared() {
        return halfappeared;
    }

    public void setHalfappeared(Integer halfappeared) {
        this.halfappeared = halfappeared;
    }

    public Integer getHalfpassed() {
        return halfpassed;
    }

    public void setHalfpassed(Integer halfpassed) {
        this.halfpassed = halfpassed;
    }

    public Integer getYearappeared() {
        return yearappeared;
    }

    public void setYearappeared(Integer yearappeared) {
        this.yearappeared = yearappeared;
    }

    public Integer getYearpassed() {
        return yearpassed;
    }

    public void setYearpassed(Integer yearpassed) {
        this.yearpassed = yearpassed;
    }

    public Object getStatusid() {
        return statusid;
    }

    public void setStatusid(Object statusid) {
        this.statusid = statusid;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

}