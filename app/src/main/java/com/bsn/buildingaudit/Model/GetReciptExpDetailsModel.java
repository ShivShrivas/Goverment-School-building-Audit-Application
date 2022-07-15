package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReciptExpDetailsModel {

    @SerializedName("YOJNANAME")
    @Expose
    private String yojnaname;
    @SerializedName("MADNAME")
    @Expose
    private String madname;
    @SerializedName("REMAMT")
    @Expose
    private Double remamt;
    @SerializedName("QUARTERLYAJ")
    @Expose
    private Double quarterlyaj;
    @SerializedName("QUARTERLYJS")
    @Expose
    private Double quarterlyjs;
    @SerializedName("QUARTERLYOD")
    @Expose
    private Double quarterlyod;
    @SerializedName("QUARTERLYJM")
    @Expose
    private Double quarterlyjm;
    @SerializedName("TOTALAMTRECEIVEDCURRENTFIN")
    @Expose
    private Double totalamtreceivedcurrentfin;
    @SerializedName("TOTALAMTAVAILABLECURRENTFIN")
    @Expose
    private Double totalamtavailablecurrentfin;
    @SerializedName("SPENTQUARTERLYAJ")
    @Expose
    private Double spentquarterlyaj;
    @SerializedName("SPENTQUARTERLYJS")
    @Expose
    private Double spentquarterlyjs;
    @SerializedName("SPENTQUARTERLYOD")
    @Expose
    private Double spentquarterlyod;
    @SerializedName("SPENTQUARTERLYJM")
    @Expose
    private Double spentquarterlyjm;
    @SerializedName("TOTALAMTSPENTCURRENTFIN")
    @Expose
    private Double totalamtspentcurrentfin;
    @SerializedName("REMBALANCE")
    @Expose
    private Double rembalance;

    public String getYojnaname() {
        return yojnaname;
    }

    public void setYojnaname(String yojnaname) {
        this.yojnaname = yojnaname;
    }

    public String getMadname() {
        return madname;
    }

    public void setMadname(String madname) {
        this.madname = madname;
    }

    public Double getRemamt() {
        return remamt;
    }

    public void setRemamt(Double remamt) {
        this.remamt = remamt;
    }

    public Double getQuarterlyaj() {
        return quarterlyaj;
    }

    public void setQuarterlyaj(Double quarterlyaj) {
        this.quarterlyaj = quarterlyaj;
    }

    public Double getQuarterlyjs() {
        return quarterlyjs;
    }

    public void setQuarterlyjs(Double quarterlyjs) {
        this.quarterlyjs = quarterlyjs;
    }

    public Double getQuarterlyod() {
        return quarterlyod;
    }

    public void setQuarterlyod(Double quarterlyod) {
        this.quarterlyod = quarterlyod;
    }

    public Double getQuarterlyjm() {
        return quarterlyjm;
    }

    public void setQuarterlyjm(Double quarterlyjm) {
        this.quarterlyjm = quarterlyjm;
    }

    public Double getTotalamtreceivedcurrentfin() {
        return totalamtreceivedcurrentfin;
    }

    public void setTotalamtreceivedcurrentfin(Double totalamtreceivedcurrentfin) {
        this.totalamtreceivedcurrentfin = totalamtreceivedcurrentfin;
    }

    public Double getTotalamtavailablecurrentfin() {
        return totalamtavailablecurrentfin;
    }

    public void setTotalamtavailablecurrentfin(Double totalamtavailablecurrentfin) {
        this.totalamtavailablecurrentfin = totalamtavailablecurrentfin;
    }

    public Double getSpentquarterlyaj() {
        return spentquarterlyaj;
    }

    public void setSpentquarterlyaj(Double spentquarterlyaj) {
        this.spentquarterlyaj = spentquarterlyaj;
    }

    public Double getSpentquarterlyjs() {
        return spentquarterlyjs;
    }

    public void setSpentquarterlyjs(Double spentquarterlyjs) {
        this.spentquarterlyjs = spentquarterlyjs;
    }

    public Double getSpentquarterlyod() {
        return spentquarterlyod;
    }

    public void setSpentquarterlyod(Double spentquarterlyod) {
        this.spentquarterlyod = spentquarterlyod;
    }

    public Double getSpentquarterlyjm() {
        return spentquarterlyjm;
    }

    public void setSpentquarterlyjm(Double spentquarterlyjm) {
        this.spentquarterlyjm = spentquarterlyjm;
    }

    public Double getTotalamtspentcurrentfin() {
        return totalamtspentcurrentfin;
    }

    public void setTotalamtspentcurrentfin(Double totalamtspentcurrentfin) {
        this.totalamtspentcurrentfin = totalamtspentcurrentfin;
    }

    public Double getRembalance() {
        return rembalance;
    }

    public void setRembalance(Double rembalance) {
        this.rembalance = rembalance;
    }

}