package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaffSanctionAndWorkingModel {


    @SerializedName("StaffDetailId")
    @Expose
    private Integer staffDetailId;
    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;
    @SerializedName("PeriodId")
    @Expose
    private Integer periodId;
    @SerializedName("StaffDesignationID")
    @Expose
    private Integer staffDesignationID;
    @SerializedName("StaffDesignation")
    @Expose
    private String staffDesignation;
    @SerializedName("SanctionedPost")
    @Expose
    private Integer sanctionedPost;
    @SerializedName("NoOfPostedStaff")
    @Expose
    private Integer noOfPostedStaff;
    @SerializedName("NoOfWorkingStaff")
    @Expose
    private Integer noOfWorkingStaff;
    @SerializedName("NoOfVacantPost")
    @Expose
    private Integer noOfVacantPost;
    @SerializedName("NoOfAttachedStaff")
    @Expose
    private Integer noOfAttachedStaff;

    public Integer getStaffDetailId() {
        return staffDetailId;
    }

    public void setStaffDetailId(Integer staffDetailId) {
        this.staffDetailId = staffDetailId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Integer getStaffDesignationID() {
        return staffDesignationID;
    }

    public void setStaffDesignationID(Integer staffDesignationID) {
        this.staffDesignationID = staffDesignationID;
    }

    public String getStaffDesignation() {
        return staffDesignation;
    }

    public void setStaffDesignation(String staffDesignation) {
        this.staffDesignation = staffDesignation;
    }

    public Integer getSanctionedPost() {
        return sanctionedPost;
    }

    public void setSanctionedPost(Integer sanctionedPost) {
        this.sanctionedPost = sanctionedPost;
    }

    public Integer getNoOfPostedStaff() {
        return noOfPostedStaff;
    }

    public void setNoOfPostedStaff(Integer noOfPostedStaff) {
        this.noOfPostedStaff = noOfPostedStaff;
    }

    public Integer getNoOfWorkingStaff() {
        return noOfWorkingStaff;
    }

    public void setNoOfWorkingStaff(Integer noOfWorkingStaff) {
        this.noOfWorkingStaff = noOfWorkingStaff;
    }

    public Integer getNoOfVacantPost() {
        return noOfVacantPost;
    }

    public void setNoOfVacantPost(Integer noOfVacantPost) {
        this.noOfVacantPost = noOfVacantPost;
    }

    public Integer getNoOfAttachedStaff() {
        return noOfAttachedStaff;
    }

    public void setNoOfAttachedStaff(Integer noOfAttachedStaff) {
        this.noOfAttachedStaff = noOfAttachedStaff;
    }

}