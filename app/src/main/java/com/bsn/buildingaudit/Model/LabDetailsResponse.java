package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabDetailsResponse {
    @SerializedName("SchoolID")
    @Expose
    private Integer schoolID;
    @SerializedName("PeriodID")
    @Expose
    private Integer periodID;
    @SerializedName("Srno")
    @Expose
    private Integer srno;
    @SerializedName("LabName")
    @Expose
    private String labName;
    @SerializedName("LabYN")
    @Expose
    private String labYN;
    @SerializedName("EquipmentStatus")
    @Expose
    private String equipmentStatus;
    @SerializedName("LabCondition")
    @Expose
    private String labCondition;
    @SerializedName("LabPhotoPath")
    @Expose
    private String labPhotoPath;
    @SerializedName("Lat")
    @Expose
    private String lat;
    @SerializedName("Long")
    @Expose
    private String _long;
    @SerializedName("UserCode")
    @Expose
    private String userCode;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("CreatedOn")


    @Expose
    private String createdOn;

    @Expose
    private String DataLocked;

    public String getDataLocked() {
        return DataLocked;
    }

    public void setDataLocked(String dataLocked) {
        DataLocked = dataLocked;
    }

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public Integer getPeriodID() {
        return periodID;
    }

    public void setPeriodID(Integer periodID) {
        this.periodID = periodID;
    }

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabYN() {
        return labYN;
    }

    public void setLabYN(String labYN) {
        this.labYN = labYN;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getLabCondition() {
        return labCondition;
    }

    public void setLabCondition(String labCondition) {
        this.labCondition = labCondition;
    }

    public String getLabPhotoPath() {
        return labPhotoPath;
    }

    public void setLabPhotoPath(String labPhotoPath) {
        this.labPhotoPath = labPhotoPath;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}