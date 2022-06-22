package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchoolListModel {

    @SerializedName("DISTRICTID")
    @Expose
    private Integer districtid;
    @SerializedName("DIVISIONID")
    @Expose
    private Integer divisionid;
    @SerializedName("BLOCKID")
    @Expose
    private Integer blockid;
    @SerializedName("SCHOOLID")
    @Expose
    private Integer schoolid;
    @SerializedName("SCHOOLNAME")
    @Expose
    private String schoolname;
    @SerializedName("DISTRICTNAME")
    @Expose
    private String districtname;
    @SerializedName("DIVISIONNAME")
    @Expose
    private String divisionname;
    @SerializedName("BLOCKNAME")
    @Expose
    private String blockname;
    @SerializedName("JDSTATUSDATE")
    @Expose
    private String jdstatusdate;
    @SerializedName("JDSTATUS")
    @Expose
    private Integer jdstatus;

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public Integer getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(Integer divisionid) {
        this.divisionid = divisionid;
    }

    public Integer getBlockid() {
        return blockid;
    }

    public void setBlockid(Integer blockid) {
        this.blockid = blockid;
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getDivisionname() {
        return divisionname;
    }

    public void setDivisionname(String divisionname) {
        this.divisionname = divisionname;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getJdstatusdate() {
        return jdstatusdate;
    }

    public void setJdstatusdate(String jdstatusdate) {
        this.jdstatusdate = jdstatusdate;
    }

    public Integer getJdstatus() {
        return jdstatus;
    }

    public void setJdstatus(Integer jdstatus) {
        this.jdstatus = jdstatus;
    }

}