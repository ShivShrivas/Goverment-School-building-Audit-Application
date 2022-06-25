
package com.bsn.buildingaudit.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ClassDatum {

    @SerializedName("ClassID")
    @Expose
    private Integer classID;
    @SerializedName("ClassName")
    @Expose
    private String className;

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
