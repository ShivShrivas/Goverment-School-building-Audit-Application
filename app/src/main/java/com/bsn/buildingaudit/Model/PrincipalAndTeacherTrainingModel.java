package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PrincipalAndTeacherTrainingModel {

    @SerializedName("P")
    @Expose
    private ArrayList<P> p = null;
    @SerializedName("T")
    @Expose
    private ArrayList<T> t = null;

    public ArrayList<P> getP() {
        return p;
    }

    public void setP(ArrayList<P> p) {
        this.p = p;
    }

    public ArrayList<T> getT() {
        return t;
    }

    public void setT(ArrayList<T> t) {
        this.t = t;
    }

}