
package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchoolDetailsModel {

    @SerializedName("SchoolDetailId")
    @Expose
    private Integer schoolDetailId;
    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;
    @SerializedName("PeriodId")
    @Expose
    private Integer periodId;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PinCode")
    @Expose
    private Integer pinCode;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("DOR")
    @Expose
    private String dor;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("AffiliationClass")
    @Expose
    private String affiliationClass;
    @SerializedName("AuthorityName")
    @Expose
    private String authorityName;
    @SerializedName("StaffDesignation")
    @Expose
    private String staffDesignation;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("School_Type")
    @Expose
    private String schoolType;
    @SerializedName("Builiding_Type")
    @Expose
    private String builidingType;
    @SerializedName("ClassData")
    @Expose
    private List<ClassDatum> classData;

    @SerializedName("SubjectData")
    @Expose
    private List<SubjectDatum> subjectData;

    public Integer getSchoolDetailId() {
        return schoolDetailId;
    }

    public void setSchoolDetailId(Integer schoolDetailId) {
        this.schoolDetailId = schoolDetailId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAffiliationClass() {
        return affiliationClass;
    }

    public void setAffiliationClass(String affiliationClass) {
        this.affiliationClass = affiliationClass;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getStaffDesignation() {
        return staffDesignation;
    }

    public void setStaffDesignation(String staffDesignation) {
        this.staffDesignation = staffDesignation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getBuilidingType() {
        return builidingType;
    }

    public void setBuilidingType(String builidingType) {
        this.builidingType = builidingType;
    }

    public List<ClassDatum> getClassData() {
        return classData;
    }

    public void setClassData(List<ClassDatum> classData) {
        this.classData = classData;
    }

    public List<SubjectDatum> getSubjectData() {
        return subjectData;
    }

    public void setSubjectData(List<SubjectDatum> subjectData) {
        this.subjectData = subjectData;
    }

}
