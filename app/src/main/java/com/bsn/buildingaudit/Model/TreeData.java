package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreeData {

    @SerializedName("CAMPUSBEAUTYID")
    @Expose
    private Integer campusbeautyid;
    @SerializedName("NOOFTREES")
    @Expose
    private Integer nooftrees;
    @SerializedName("TREENAME")
    @Expose
    private String treename;

    public Integer getCampusbeautyid() {
        return campusbeautyid;
    }

    public void setCampusbeautyid(Integer campusbeautyid) {
        this.campusbeautyid = campusbeautyid;
    }

    public Integer getNooftrees() {
        return nooftrees;
    }

    public void setNooftrees(Integer nooftrees) {
        this.nooftrees = nooftrees;
    }

    public String getTreename() {
        return treename;
    }

    public void setTreename(String treename) {
        this.treename = treename;
    }

}
