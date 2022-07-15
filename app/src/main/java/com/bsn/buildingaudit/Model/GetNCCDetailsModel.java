package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNCCDetailsModel {

    @SerializedName("NCCRUNNINGSTATUS")
    @Expose
    private String nccrunningstatus;
    @SerializedName("NCCYEARRAISINGSTATUS")
    @Expose
    private String nccyearraisingstatus;
    @SerializedName("NCCGROUNDSTATUS")
    @Expose
    private String nccgroundstatus;
    @SerializedName("TRAINEDANOSTATUS")
    @Expose
    private String trainedanostatus;
    @SerializedName("SSCDPARTICIPATION")
    @Expose
    private String sscdparticipation;
    @SerializedName("WINGSDYEAR")
    @Expose
    private String wingsdyear;
    @SerializedName("WINGSDNOCADETS")
    @Expose
    private Integer wingsdnocadets;
    @SerializedName("WINGSWYEAR")
    @Expose
    private String wingswyear;
    @SerializedName("WINGSWNOCADETS")
    @Expose
    private Integer wingswnocadets;
    @SerializedName("WINGJDYEAR")
    @Expose
    private String wingjdyear;
    @SerializedName("WINGJDNOCADETS")
    @Expose
    private Integer wingjdnocadets;
    @SerializedName("WINGARMYSTATUS")
    @Expose
    private String wingarmystatus;
    @SerializedName("WINGNAVALSTATUS")
    @Expose
    private String wingnavalstatus;
    @SerializedName("WINGAIRSTATUS")
    @Expose
    private String wingairstatus;

    public String getNccrunningstatus() {
        return nccrunningstatus;
    }

    public void setNccrunningstatus(String nccrunningstatus) {
        this.nccrunningstatus = nccrunningstatus;
    }

    public String getNccyearraisingstatus() {
        return nccyearraisingstatus;
    }

    public void setNccyearraisingstatus(String nccyearraisingstatus) {
        this.nccyearraisingstatus = nccyearraisingstatus;
    }

    public String getNccgroundstatus() {
        return nccgroundstatus;
    }

    public void setNccgroundstatus(String nccgroundstatus) {
        this.nccgroundstatus = nccgroundstatus;
    }

    public String getTrainedanostatus() {
        return trainedanostatus;
    }

    public void setTrainedanostatus(String trainedanostatus) {
        this.trainedanostatus = trainedanostatus;
    }

    public String getSscdparticipation() {
        return sscdparticipation;
    }

    public void setSscdparticipation(String sscdparticipation) {
        this.sscdparticipation = sscdparticipation;
    }

    public String getWingsdyear() {
        return wingsdyear;
    }

    public void setWingsdyear(String wingsdyear) {
        this.wingsdyear = wingsdyear;
    }

    public Integer getWingsdnocadets() {
        return wingsdnocadets;
    }

    public void setWingsdnocadets(Integer wingsdnocadets) {
        this.wingsdnocadets = wingsdnocadets;
    }

    public String getWingswyear() {
        return wingswyear;
    }

    public void setWingswyear(String wingswyear) {
        this.wingswyear = wingswyear;
    }

    public Integer getWingswnocadets() {
        return wingswnocadets;
    }

    public void setWingswnocadets(Integer wingswnocadets) {
        this.wingswnocadets = wingswnocadets;
    }

    public String getWingjdyear() {
        return wingjdyear;
    }

    public void setWingjdyear(String wingjdyear) {
        this.wingjdyear = wingjdyear;
    }

    public Integer getWingjdnocadets() {
        return wingjdnocadets;
    }

    public void setWingjdnocadets(Integer wingjdnocadets) {
        this.wingjdnocadets = wingjdnocadets;
    }

    public String getWingarmystatus() {
        return wingarmystatus;
    }

    public void setWingarmystatus(String wingarmystatus) {
        this.wingarmystatus = wingarmystatus;
    }

    public String getWingnavalstatus() {
        return wingnavalstatus;
    }

    public void setWingnavalstatus(String wingnavalstatus) {
        this.wingnavalstatus = wingnavalstatus;
    }

    public String getWingairstatus() {
        return wingairstatus;
    }

    public void setWingairstatus(String wingairstatus) {
        this.wingairstatus = wingairstatus;
    }

}
