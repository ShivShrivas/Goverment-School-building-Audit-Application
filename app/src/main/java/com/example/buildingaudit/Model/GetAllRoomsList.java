package com.example.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllRoomsList {


    @SerializedName("ParamId")
    @Expose
    String ParamId;


    @SerializedName("ParamName")
    @Expose
    String ParamName;


    @SerializedName("LastUpdateDateTime")
    @Expose
    String LastUpdateDateTime;


    @SerializedName("SchoolID")
    @Expose
    String SchoolID;


    @SerializedName("PeriodID")
    @Expose
    String PeriodID;


    public GetAllRoomsList() {
    }

    public GetAllRoomsList(String paramId, String paramName, String lastUpdateDateTime, String schoolID, String periodID) {
        ParamId = paramId;
        ParamName = paramName;
        LastUpdateDateTime = lastUpdateDateTime;
        SchoolID = schoolID;
        PeriodID = periodID;
    }


    public String getParamId() {
        return ParamId;
    }

    public void setParamId(String paramId) {
        ParamId = paramId;
    }

    public String getParamName() {
        return ParamName;
    }

    public void setParamName(String paramName) {
        ParamName = paramName;
    }

    public String getLastUpdateDateTime() {
        return LastUpdateDateTime;
    }

    public void setLastUpdateDateTime(String lastUpdateDateTime) {
        LastUpdateDateTime = lastUpdateDateTime;
    }

    public String getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(String schoolID) {
        SchoolID = schoolID;
    }

    public String getPeriodID() {
        return PeriodID;
    }

    public void setPeriodID(String periodID) {
        PeriodID = periodID;
    }
}
