package com.bsn.buildingaudit.Model;

public class Staff_Details_Model
{
    String StaffName;
    String StaffDesignation;

    public Staff_Details_Model() {
    }

    public Staff_Details_Model(String staffName, String staffDesignation) {
        StaffName = staffName;
        StaffDesignation = staffDesignation;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public String getStaffDesignation() {
        return StaffDesignation;
    }

    public void setStaffDesignation(String staffDesignation) {
        StaffDesignation = staffDesignation;
    }
}
