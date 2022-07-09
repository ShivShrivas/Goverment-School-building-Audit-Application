package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameStudentName {

    @SerializedName("STUDENTNAME")
    @Expose
    private String studentname;
    @SerializedName("SPORTNAME")
    @Expose
    private String sportname;
    @SerializedName("MEDALPOSITION")
    @Expose
    private String medalposition;
    @SerializedName("PARTLEVEL")
    @Expose
    private String partlevel;

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public String getMedalposition() {
        return medalposition;
    }

    public void setMedalposition(String medalposition) {
        this.medalposition = medalposition;
    }

    public String getPartlevel() {
        return partlevel;
    }

    public void setPartlevel(String partlevel) {
        this.partlevel = partlevel;
    }

}
