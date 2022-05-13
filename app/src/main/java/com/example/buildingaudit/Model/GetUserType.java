package com.example.buildingaudit.Model;

public class GetUserType {
    String Usertypeid;
    String typevalue;
    String typedesc;


    public GetUserType() {
    }

    public String getUsertypeid() {
        return Usertypeid;
    }

    public void setUsertypeid(String usertypeid) {
        Usertypeid = usertypeid;
    }

    public String getTypevalue() {
        return typevalue;
    }

    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue;
    }

    public String getTypedesc() {
        return typedesc;
    }

    public void setTypedesc(String typedesc) {
        this.typedesc = typedesc;
    }

    public GetUserType(String usertypeid, String typevalue, String typedesc) {
        Usertypeid = usertypeid;
        this.typevalue = typevalue;
        this.typedesc = typedesc;
    }
}
