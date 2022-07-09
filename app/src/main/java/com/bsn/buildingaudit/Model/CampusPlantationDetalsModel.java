package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CampusPlantationDetalsModel {

    @SerializedName("RECORDID")
    @Expose
    private Integer recordid;
    @SerializedName("SCHOOLID")
    @Expose
    private Integer schoolid;
    @SerializedName("PERIODID")
    @Expose
    private Integer periodid;
    @SerializedName("PLANTATION")
    @Expose
    private String plantation;
    @SerializedName("WALLSLOGAN")
    @Expose
    private String wallslogan;
    @SerializedName("DUSTBINS")
    @Expose
    private String dustbins;
    @SerializedName("ECHOCLUB")
    @Expose
    private String echoclub;
    @SerializedName("DISPLAYNOTICE")
    @Expose
    private String displaynotice;
    @SerializedName("PLANTTARGET")
    @Expose
    private String planttarget;
    @SerializedName("PLANTED")
    @Expose
    private String planted;
    @SerializedName("SURVIVE")
    @Expose
    private String survive;
    @SerializedName("STATUSID")
    @Expose
    private Object statusid;
    @SerializedName("REJECT_REASON")
    @Expose
    private Object rejectReason;
    @SerializedName("STATUS_DATE")
    @Expose
    private Object statusDate;
    @SerializedName("KYARI")
    @Expose
    private String kyari;
    @SerializedName("TreeDatas")
    @Expose
    private List<TreeData> treeDatas = null;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

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

    public String getPlantation() {
        return plantation;
    }

    public void setPlantation(String plantation) {
        this.plantation = plantation;
    }

    public String getWallslogan() {
        return wallslogan;
    }

    public void setWallslogan(String wallslogan) {
        this.wallslogan = wallslogan;
    }

    public String getDustbins() {
        return dustbins;
    }

    public void setDustbins(String dustbins) {
        this.dustbins = dustbins;
    }

    public String getEchoclub() {
        return echoclub;
    }

    public void setEchoclub(String echoclub) {
        this.echoclub = echoclub;
    }

    public String getDisplaynotice() {
        return displaynotice;
    }

    public void setDisplaynotice(String displaynotice) {
        this.displaynotice = displaynotice;
    }

    public String getPlanttarget() {
        return planttarget;
    }

    public void setPlanttarget(String planttarget) {
        this.planttarget = planttarget;
    }

    public String getPlanted() {
        return planted;
    }

    public void setPlanted(String planted) {
        this.planted = planted;
    }

    public String getSurvive() {
        return survive;
    }

    public void setSurvive(String survive) {
        this.survive = survive;
    }

    public Object getStatusid() {
        return statusid;
    }

    public void setStatusid(Object statusid) {
        this.statusid = statusid;
    }

    public Object getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(Object rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Object getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Object statusDate) {
        this.statusDate = statusDate;
    }

    public String getKyari() {
        return kyari;
    }

    public void setKyari(String kyari) {
        this.kyari = kyari;
    }

    public List<TreeData> getTreeDatas() {
        return treeDatas;
    }

    public void setTreeDatas(List<TreeData> treeDatas) {
        this.treeDatas = treeDatas;
    }

}
