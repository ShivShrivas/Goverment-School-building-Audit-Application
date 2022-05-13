package com.bsn.buildingaudit.Model;

public class FurnitureDetails {
    String FurnitureType,Condition,TotalCnt,Srno;

    public FurnitureDetails() {
    }

    public FurnitureDetails(String furnitureType, String condition, String totalCnt, String srno) {
        FurnitureType = furnitureType;
        Condition = condition;
        TotalCnt = totalCnt;
        Srno = srno;
    }

    public String getFurnitureType() {
        return FurnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        FurnitureType = furnitureType;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getTotalCnt() {
        return TotalCnt;
    }

    public void setTotalCnt(String totalCnt) {
        TotalCnt = totalCnt;
    }

    public String getSrno() {
        return Srno;
    }

    public void setSrno(String srno) {
        Srno = srno;
    }
}
