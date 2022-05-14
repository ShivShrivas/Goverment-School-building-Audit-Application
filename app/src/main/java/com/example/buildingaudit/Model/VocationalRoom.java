package com.example.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VocationalRoom {
    String Srno,RoomAvailable,EquipmentStatus,RoomCondition;
    @SerializedName("Class")
    @Expose
    String _class;

    public VocationalRoom() {
    }

    public VocationalRoom(String srno, String roomAvailable, String equipmentStatus, String roomCondition, String _class) {
        Srno = srno;
        RoomAvailable = roomAvailable;
        EquipmentStatus = equipmentStatus;
        RoomCondition = roomCondition;
        this._class = _class;
    }

    public String getSrno() {
        return Srno;
    }

    public void setSrno(String srno) {
        Srno = srno;
    }

    public String getRoomAvailable() {
        return RoomAvailable;
    }

    public void setRoomAvailable(String roomAvailable) {
        RoomAvailable = roomAvailable;
    }

    public String getEquipmentStatus() {
        return EquipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        EquipmentStatus = equipmentStatus;
    }

    public String getRoomCondition() {
        return RoomCondition;
    }

    public void setRoomCondition(String roomCondition) {
        RoomCondition = roomCondition;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}
