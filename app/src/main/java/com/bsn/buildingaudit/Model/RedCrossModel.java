package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedCrossModel {

    @SerializedName("SCHOOLID")
    @Expose
    private Integer schoolid;
    @SerializedName("PERIODID")
    @Expose
    private Integer periodid;
    @SerializedName("FORMATIONSTATUS")
    @Expose
    private String formationstatus;
    @SerializedName("OFFICEFACILITIESSTATUS")
    @Expose
    private String officefacilitiesstatus;
    @SerializedName("TRAININGCAMPSTATUS")
    @Expose
    private String trainingcampstatus;
    @SerializedName("STOCKREGISTERSTATUS")
    @Expose
    private String stockregisterstatus;
    @SerializedName("INCOMEEXPSTATUS")
    @Expose
    private String incomeexpstatus;
    @SerializedName("PROJECTACTIVITYSTATUS")
    @Expose
    private String projectactivitystatus;
    @SerializedName("AMTAVAIINACC")
    @Expose
    private Double amtavaiinacc;
    @SerializedName("CURRENTDEPAMTWITHREGNO")
    @Expose
    private String currentdepamtwithregno;
    @SerializedName("DISTRICTLAMT")
    @Expose
    private String districtlamt;
    @SerializedName("SCHOOLBALANCEAMT")
    @Expose
    private String schoolbalanceamt;
    @SerializedName("REMARKS")
    @Expose
    private String remarks;
    @SerializedName("STATUSID")
    @Expose
    private Integer statusid;
    @SerializedName("REJECT_REASON")
    @Expose
    private String rejectReason;
    @SerializedName("STATUS_DATE")
    @Expose
    private String statusDate;

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

    public String getFormationstatus() {
        return formationstatus;
    }

    public void setFormationstatus(String formationstatus) {
        this.formationstatus = formationstatus;
    }

    public String getOfficefacilitiesstatus() {
        return officefacilitiesstatus;
    }

    public void setOfficefacilitiesstatus(String officefacilitiesstatus) {
        this.officefacilitiesstatus = officefacilitiesstatus;
    }

    public String getTrainingcampstatus() {
        return trainingcampstatus;
    }

    public void setTrainingcampstatus(String trainingcampstatus) {
        this.trainingcampstatus = trainingcampstatus;
    }

    public String getStockregisterstatus() {
        return stockregisterstatus;
    }

    public void setStockregisterstatus(String stockregisterstatus) {
        this.stockregisterstatus = stockregisterstatus;
    }

    public String getIncomeexpstatus() {
        return incomeexpstatus;
    }

    public void setIncomeexpstatus(String incomeexpstatus) {
        this.incomeexpstatus = incomeexpstatus;
    }

    public String getProjectactivitystatus() {
        return projectactivitystatus;
    }

    public void setProjectactivitystatus(String projectactivitystatus) {
        this.projectactivitystatus = projectactivitystatus;
    }

    public Double getAmtavaiinacc() {
        return amtavaiinacc;
    }

    public void setAmtavaiinacc(Double amtavaiinacc) {
        this.amtavaiinacc = amtavaiinacc;
    }

    public String getCurrentdepamtwithregno() {
        return currentdepamtwithregno;
    }

    public void setCurrentdepamtwithregno(String currentdepamtwithregno) {
        this.currentdepamtwithregno = currentdepamtwithregno;
    }

    public String getDistrictlamt() {
        return districtlamt;
    }

    public void setDistrictlamt(String districtlamt) {
        this.districtlamt = districtlamt;
    }

    public String getSchoolbalanceamt() {
        return schoolbalanceamt;
    }

    public void setSchoolbalanceamt(String schoolbalanceamt) {
        this.schoolbalanceamt = schoolbalanceamt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

}