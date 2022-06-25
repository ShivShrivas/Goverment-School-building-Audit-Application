
package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectDatum {

    @SerializedName("SubjectID")
    @Expose
    private Integer subjectID;
    @SerializedName("SchoolID")
    @Expose
    private Integer schoolID;
    @SerializedName("PeriodID")
    @Expose
    private Integer periodID;
    @SerializedName("SubjectName")
    @Expose
    private String subjectName;
    @SerializedName("ClassID")
    @Expose
    private Integer classID;
    @SerializedName("ClassName")
    @Expose
    private String className;

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
