package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllInspectionDataModel {

    @SerializedName("InsRecordId")
    @Expose
    private Integer insRecordId;
    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;
    @SerializedName("InsId")
    @Expose
    private String insId;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("TotalDuration")
    @Expose
    private String totalDuration;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("TotalInsCompleted")
    @Expose
    private Integer totalInsCompleted;
    @SerializedName("TotalInsPending")
    @Expose
    private Integer totalInsPending;

    public Integer getInsRecordId() {
        return insRecordId;
    }

    public void setInsRecordId(Integer insRecordId) {
        this.insRecordId = insRecordId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalInsCompleted() {
        return totalInsCompleted;
    }

    public void setTotalInsCompleted(Integer totalInsCompleted) {
        this.totalInsCompleted = totalInsCompleted;
    }

    public Integer getTotalInsPending() {
        return totalInsPending;
    }

    public void setTotalInsPending(Integer totalInsPending) {
        this.totalInsPending = totalInsPending;
    }
}
