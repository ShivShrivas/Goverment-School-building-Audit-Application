package com.example.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassDetailsResponse {

    @SerializedName("SchoolID")
    @Expose
    String SchoolID;


    @SerializedName("PeriodID")
    @Expose
    String PeriodID;




    @SerializedName("TotalRooms")
    @Expose
    String TotalRooms;




    @SerializedName("GoodCondition")
    @Expose
    String GoodCondition;




    @SerializedName("GoodConditionPhotos")
    @Expose
    String GoodConditionPhotos;




    @SerializedName("MajorRepairingPhotos")
    @Expose
    String MajorRepairingPhotos;




    @SerializedName("MinorRepairingPhotos")
    @Expose
    String MinorRepairingPhotos;




    @SerializedName("MajorRepairing")
    @Expose
    String MajorRepairing;




    @SerializedName("MinorRepairing")
    @Expose
    String MinorRepairing;




    @SerializedName("ClassRoomsWithPodium")
    @Expose
    String ClassRoomsWithPodium;




    @SerializedName("BlackBoard")
    @Expose
    String BlackBoard;




    @SerializedName("WhiteBoard")
    @Expose
    String WhiteBoard;


    @SerializedName("GreenBoard")
    @Expose
    String GreenBoard;


    @SerializedName("Lat")
    @Expose
    String Lat;


    @SerializedName("Long")
    @Expose
    String _Long;


    @SerializedName("UserCode")
    @Expose
    String UserCode;



    @SerializedName("CreatedBy")
    @Expose
    String CreatedBy;



    @SerializedName("CreatedOn")
    @Expose
    String CreatedOn;


    public ClassDetailsResponse() {
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

    public String getTotalRooms() {
        return TotalRooms;
    }

    public void setTotalRooms(String totalRooms) {
        TotalRooms = totalRooms;
    }

    public String getGoodCondition() {
        return GoodCondition;
    }

    public void setGoodCondition(String goodCondition) {
        GoodCondition = goodCondition;
    }

    public String getGoodConditionPhotos() {
        return GoodConditionPhotos;
    }

    public void setGoodConditionPhotos(String goodConditionPhotos) {
        GoodConditionPhotos = goodConditionPhotos;
    }

    public String getMajorRepairingPhotos() {
        return MajorRepairingPhotos;
    }

    public void setMajorRepairingPhotos(String majorRepairingPhotos) {
        MajorRepairingPhotos = majorRepairingPhotos;
    }

    public String getMinorRepairingPhotos() {
        return MinorRepairingPhotos;
    }

    public void setMinorRepairingPhotos(String minorRepairingPhotos) {
        MinorRepairingPhotos = minorRepairingPhotos;
    }

    public String getMajorRepairing() {
        return MajorRepairing;
    }

    public void setMajorRepairing(String majorRepairing) {
        MajorRepairing = majorRepairing;
    }

    public String getMinorRepairing() {
        return MinorRepairing;
    }

    public void setMinorRepairing(String minorRepairing) {
        MinorRepairing = minorRepairing;
    }

    public String getClassRoomsWithPodium() {
        return ClassRoomsWithPodium;
    }

    public void setClassRoomsWithPodium(String classRoomsWithPodium) {
        ClassRoomsWithPodium = classRoomsWithPodium;
    }

    public String getBlackBoard() {
        return BlackBoard;
    }

    public void setBlackBoard(String blackBoard) {
        BlackBoard = blackBoard;
    }

    public String getWhiteBoard() {
        return WhiteBoard;
    }

    public void setWhiteBoard(String whiteBoard) {
        WhiteBoard = whiteBoard;
    }

    public String getGreenBoard() {
        return GreenBoard;
    }

    public void setGreenBoard(String greenBoard) {
        GreenBoard = greenBoard;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String get_Long() {
        return _Long;
    }

    public void set_Long(String _Long) {
        this._Long = _Long;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
