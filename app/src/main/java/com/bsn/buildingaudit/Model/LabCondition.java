package com.bsn.buildingaudit.Model;

public class LabCondition {

    String Srno,LabName,LabYN,EquipmentStatus,LabCondition;

    public LabCondition() {
    }

    public LabCondition(String srno, String labName, String labYN, String equipmentStatus, String labCondition) {
        Srno = srno;
        LabName = labName;
        LabYN = labYN;
        EquipmentStatus = equipmentStatus;
        LabCondition = labCondition;
    }

    public String getSrno() {
        return Srno;
    }

    public void setSrno(String srno) {
        Srno = srno;
    }

    public String getLabName() {
        return LabName;
    }

    public void setLabName(String labName) {
        LabName = labName;
    }

    public String getLabYN() {
        return LabYN;
    }

    public void setLabYN(String labYN) {
        LabYN = labYN;
    }

    public String getEquipmentStatus() {
        return EquipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        EquipmentStatus = equipmentStatus;
    }

    public String getLabCondition() {
        return LabCondition;
    }

    public void setLabCondition(String labCondition) {
        LabCondition = labCondition;
    }
}
