package com.bsn.buildingaudit.Model;

public class BoundryType {
    String BWId,BWTypeName;

    public BoundryType() {
    }

    public BoundryType(String BWId, String BWTypeName) {
        this.BWId = BWId;
        this.BWTypeName = BWTypeName;
    }


    public String getBWId() {
        return BWId;
    }

    public void setBWId(String BWId) {
        this.BWId = BWId;
    }

    public String getBWTypeName() {
        return BWTypeName;
    }

    public void setBWTypeName(String BWTypeName) {
        this.BWTypeName = BWTypeName;
    }
}
