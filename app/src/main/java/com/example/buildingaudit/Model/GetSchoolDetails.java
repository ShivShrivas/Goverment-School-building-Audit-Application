package com.example.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSchoolDetails {
    String Expr1;

    @SerializedName("SCHOOL NAME")
    @Expose
    String SCHOOL_NAME;

    String ADDRESS;

    @SerializedName("PHONE NO.")
    @Expose
    String PHONE_NO;

    @SerializedName("DATE OF REGISTRATION")
    @Expose
    String DATE_Of_Registration;

    @SerializedName("COLLEGE TYPE")
    @Expose
    String COLLEGE_Type;

    @SerializedName("AFFILIATION CLASS")
    @Expose
    String AFFILIATION_Class;

    @SerializedName("NAME OF AUTHORITY")
    @Expose
    String NAME_Of_Authority;


    String DESIGNATION;

    @SerializedName("CONTACT NO")
    @Expose
    String CONTACT_No;

    String PRACTICALSUBJECTS;


    String SUBJECTS;

    public GetSchoolDetails() {
    }

    public GetSchoolDetails(String expr1, String SCHOOL_NAME, String ADDRESS, String PHONE_NO, String DATE_Of_Registration, String COLLEGE_Type, String AFFILIATION_Class, String NAME_Of_Authority, String DESIGNATION, String CONTACT_No, String PRACTICALSUBJECTS, String SUBJECTS) {
        Expr1 = expr1;
        this.SCHOOL_NAME = SCHOOL_NAME;
        this.ADDRESS = ADDRESS;
        this.PHONE_NO = PHONE_NO;
        this.DATE_Of_Registration = DATE_Of_Registration;
        this.COLLEGE_Type = COLLEGE_Type;
        this.AFFILIATION_Class = AFFILIATION_Class;
        this.NAME_Of_Authority = NAME_Of_Authority;
        this.DESIGNATION = DESIGNATION;
        this.CONTACT_No = CONTACT_No;
        this.PRACTICALSUBJECTS = PRACTICALSUBJECTS;
        this.SUBJECTS = SUBJECTS;
    }

    public String getExpr1() {
        return Expr1;
    }

    public void setExpr1(String expr1) {
        Expr1 = expr1;
    }

    public String getSCHOOL_NAME() {
        return SCHOOL_NAME;
    }

    public void setSCHOOL_NAME(String SCHOOL_NAME) {
        this.SCHOOL_NAME = SCHOOL_NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPHONE_NO() {
        return PHONE_NO;
    }

    public void setPHONE_NO(String PHONE_NO) {
        this.PHONE_NO = PHONE_NO;
    }

    public String getDATE_Of_Registration() {
        return DATE_Of_Registration;
    }

    public void setDATE_Of_Registration(String DATE_Of_Registration) {
        this.DATE_Of_Registration = DATE_Of_Registration;
    }

    public String getCOLLEGE_Type() {
        return COLLEGE_Type;
    }

    public void setCOLLEGE_Type(String COLLEGE_Type) {
        this.COLLEGE_Type = COLLEGE_Type;
    }

    public String getAFFILIATION_Class() {
        return AFFILIATION_Class;
    }

    public void setAFFILIATION_Class(String AFFILIATION_Class) {
        this.AFFILIATION_Class = AFFILIATION_Class;
    }

    public String getNAME_Of_Authority() {
        return NAME_Of_Authority;
    }

    public void setNAME_Of_Authority(String NAME_Of_Authority) {
        this.NAME_Of_Authority = NAME_Of_Authority;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
    }

    public String getCONTACT_No() {
        return CONTACT_No;
    }

    public void setCONTACT_No(String CONTACT_No) {
        this.CONTACT_No = CONTACT_No;
    }

    public String getPRACTICALSUBJECTS() {
        return PRACTICALSUBJECTS;
    }

    public void setPRACTICALSUBJECTS(String PRACTICALSUBJECTS) {
        this.PRACTICALSUBJECTS = PRACTICALSUBJECTS;
    }

    public String getSUBJECTS() {
        return SUBJECTS;
    }

    public void setSUBJECTS(String SUBJECTS) {
        this.SUBJECTS = SUBJECTS;
    }
}
