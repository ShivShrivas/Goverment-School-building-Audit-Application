package com.bsn.buildingaudit.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectWiseSyllabusModel implements Parcelable {

    @SerializedName("ClassName")
    @Expose
    private String className;
    @SerializedName("SubjectTopicID")
    @Expose
    private Integer subjectTopicID;
    @SerializedName("SubjectTopicName")
    @Expose
    private String subjectTopicName;
    @SerializedName("SubjectSubTopic")
    @Expose
    private String subjectSubTopic;
    @SerializedName("SubjectID")
    @Expose
    private Integer subjectID;
    @SerializedName("SubjectName")
    @Expose
    private String subjectName;
    @SerializedName("DateCovered")
    @Expose
    private Integer dateCovered;
    @SerializedName("entflag")
    @Expose
    private String entflag;
    @SerializedName("dataentryflag")
    @Expose
    private String dataentryflag;
    @SerializedName("SyllabusCoveredID")
    @Expose
    private Integer syllabusCoveredID;
    @SerializedName("remarks")
    @Expose
    private Object remarks;
    @SerializedName("StatusId")
    @Expose
    private Object statusId;
    @SerializedName("VerifiedOn")
    @Expose
    private Object verifiedOn;
    @SerializedName("StatusChangeDate")
    @Expose
    private Object statusChangeDate;
    @SerializedName("flagCovered")
    @Expose
    private Boolean flagCovered;
    @SerializedName("MonthOfSubject")
    @Expose
    private Integer monthOfSubject;
    @SerializedName("MonthNames")
    @Expose
    private String monthNames;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSubjectTopicID() {
        return subjectTopicID;
    }

    public void setSubjectTopicID(Integer subjectTopicID) {
        this.subjectTopicID = subjectTopicID;
    }

    public String getSubjectTopicName() {
        return subjectTopicName;
    }

    public void setSubjectTopicName(String subjectTopicName) {
        this.subjectTopicName = subjectTopicName;
    }

    public String getSubjectSubTopic() {
        return subjectSubTopic;
    }

    public void setSubjectSubTopic(String subjectSubTopic) {
        this.subjectSubTopic = subjectSubTopic;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getDateCovered() {
        return dateCovered;
    }

    public void setDateCovered(Integer dateCovered) {
        this.dateCovered = dateCovered;
    }

    public String getEntflag() {
        return entflag;
    }

    public void setEntflag(String entflag) {
        this.entflag = entflag;
    }

    public String getDataentryflag() {
        return dataentryflag;
    }

    public void setDataentryflag(String dataentryflag) {
        this.dataentryflag = dataentryflag;
    }

    public Integer getSyllabusCoveredID() {
        return syllabusCoveredID;
    }

    public void setSyllabusCoveredID(Integer syllabusCoveredID) {
        this.syllabusCoveredID = syllabusCoveredID;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public Object getStatusId() {
        return statusId;
    }

    public void setStatusId(Object statusId) {
        this.statusId = statusId;
    }

    public Object getVerifiedOn() {
        return verifiedOn;
    }

    public void setVerifiedOn(Object verifiedOn) {
        this.verifiedOn = verifiedOn;
    }

    public Object getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Object statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public Boolean getFlagCovered() {
        return flagCovered;
    }

    public void setFlagCovered(Boolean flagCovered) {
        this.flagCovered = flagCovered;
    }

    public Integer getMonthOfSubject() {
        return monthOfSubject;
    }

    public void setMonthOfSubject(Integer monthOfSubject) {
        this.monthOfSubject = monthOfSubject;
    }

    public String getMonthNames() {
        return monthNames;
    }

    public void setMonthNames(String monthNames) {
        this.monthNames = monthNames;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}