package com.bsn.buildingaudit.Model;

public class RecModel {
    public RecModel(){}

    String roomtype;
    String updatedOn;

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public RecModel(String roomtype, String updatedOn) {
        this.roomtype = roomtype;
        this.updatedOn = updatedOn;
    }
}
