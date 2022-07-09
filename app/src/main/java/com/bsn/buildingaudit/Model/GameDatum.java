package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameDatum {

    @SerializedName("RECORDID")
    @Expose
    private Integer recordid;
    @SerializedName("SCHOOLID")
    @Expose
    private Integer schoolid;
    @SerializedName("PERIODID")
    @Expose
    private Integer periodid;
    @SerializedName("GAMEFACILITYSTATUS")
    @Expose
    private String gamefacilitystatus;
    @SerializedName("GRANTRECIPTAMT")
    @Expose
    private Double grantreciptamt;
    @SerializedName("GRANTEXPENAMT")
    @Expose
    private Double grantexpenamt;
    @SerializedName("SCHEMENAME")
    @Expose
    private String schemename;
    @SerializedName("OTHERSCHEMENAME")
    @Expose
    private String otherschemename;
    @SerializedName("INDOORGAMELIST")
    @Expose
    private String indoorgamelist;
    @SerializedName("OUTDOORGAMELIST")
    @Expose
    private String outdoorgamelist;
    @SerializedName("SPORTTEACHERSTATUS")
    @Expose
    private String sportteacherstatus;
    @SerializedName("TEACHERNOMISTATUS")
    @Expose
    private String teachernomistatus;
    @SerializedName("EQUIAVAILSTATUS")
    @Expose
    private String equiavailstatus;
    @SerializedName("STATELEVELSTATUS")
    @Expose
    private String statelevelstatus;
    @SerializedName("DISTRICTLEVELSTATUS")
    @Expose
    private String districtlevelstatus;
    @SerializedName("NATIONALLEVELSTATUS")
    @Expose
    private String nationallevelstatus;
    @SerializedName("FITINDIASTATUS")
    @Expose
    private String fitindiastatus;
    @SerializedName("FITINDIASTDCOUNT")
    @Expose
    private Integer fitindiastdcount;
    @SerializedName("KHELOINDIASTATUS")
    @Expose
    private String kheloindiastatus;
    @SerializedName("KHELOINDIASTDCOUNT")
    @Expose
    private Integer kheloindiastdcount;
    @SerializedName("REMARKS")
    @Expose
    private Object remarks;
    @SerializedName("STATUSID")
    @Expose
    private Integer statusid;
    @SerializedName("REJECT_REASON")
    @Expose
    private String rejectReason;
    @SerializedName("STATUS_DATE")
    @Expose
    private String statusDate;

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

    public String getGamefacilitystatus() {
        return gamefacilitystatus;
    }

    public void setGamefacilitystatus(String gamefacilitystatus) {
        this.gamefacilitystatus = gamefacilitystatus;
    }

    public Double getGrantreciptamt() {
        return grantreciptamt;
    }

    public void setGrantreciptamt(Double grantreciptamt) {
        this.grantreciptamt = grantreciptamt;
    }

    public Double getGrantexpenamt() {
        return grantexpenamt;
    }

    public void setGrantexpenamt(Double grantexpenamt) {
        this.grantexpenamt = grantexpenamt;
    }

    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename;
    }

    public String getOtherschemename() {
        return otherschemename;
    }

    public void setOtherschemename(String otherschemename) {
        this.otherschemename = otherschemename;
    }

    public String getIndoorgamelist() {
        return indoorgamelist;
    }

    public void setIndoorgamelist(String indoorgamelist) {
        this.indoorgamelist = indoorgamelist;
    }

    public String getOutdoorgamelist() {
        return outdoorgamelist;
    }

    public void setOutdoorgamelist(String outdoorgamelist) {
        this.outdoorgamelist = outdoorgamelist;
    }

    public String getSportteacherstatus() {
        return sportteacherstatus;
    }

    public void setSportteacherstatus(String sportteacherstatus) {
        this.sportteacherstatus = sportteacherstatus;
    }

    public String getTeachernomistatus() {
        return teachernomistatus;
    }

    public void setTeachernomistatus(String teachernomistatus) {
        this.teachernomistatus = teachernomistatus;
    }

    public String getEquiavailstatus() {
        return equiavailstatus;
    }

    public void setEquiavailstatus(String equiavailstatus) {
        this.equiavailstatus = equiavailstatus;
    }

    public String getStatelevelstatus() {
        return statelevelstatus;
    }

    public void setStatelevelstatus(String statelevelstatus) {
        this.statelevelstatus = statelevelstatus;
    }

    public String getDistrictlevelstatus() {
        return districtlevelstatus;
    }

    public void setDistrictlevelstatus(String districtlevelstatus) {
        this.districtlevelstatus = districtlevelstatus;
    }

    public String getNationallevelstatus() {
        return nationallevelstatus;
    }

    public void setNationallevelstatus(String nationallevelstatus) {
        this.nationallevelstatus = nationallevelstatus;
    }

    public String getFitindiastatus() {
        return fitindiastatus;
    }

    public void setFitindiastatus(String fitindiastatus) {
        this.fitindiastatus = fitindiastatus;
    }

    public Integer getFitindiastdcount() {
        return fitindiastdcount;
    }

    public void setFitindiastdcount(Integer fitindiastdcount) {
        this.fitindiastdcount = fitindiastdcount;
    }

    public String getKheloindiastatus() {
        return kheloindiastatus;
    }

    public void setKheloindiastatus(String kheloindiastatus) {
        this.kheloindiastatus = kheloindiastatus;
    }

    public Integer getKheloindiastdcount() {
        return kheloindiastdcount;
    }

    public void setKheloindiastdcount(Integer kheloindiastdcount) {
        this.kheloindiastdcount = kheloindiastdcount;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
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

