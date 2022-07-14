package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDashboardMenuDataModel {

    @SerializedName("MENUID")
    @Expose
    private String menuid;
    @SerializedName("MENUNAME")
    @Expose
    private String menuname;
    @SerializedName("APP_ICON")
    @Expose
    private String appIcon;
    @SerializedName("APP_CLASS_NAME")
    @Expose
    private String appClassName;
    @SerializedName("PARAMID")
    @Expose
    private Integer paramid;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppClassName() {
        return appClassName;
    }

    public void setAppClassName(String appClassName) {
        this.appClassName = appClassName;
    }

    public Integer getParamid() {
        return paramid;
    }

    public void setParamid(Integer paramid) {
        this.paramid = paramid;
    }

}