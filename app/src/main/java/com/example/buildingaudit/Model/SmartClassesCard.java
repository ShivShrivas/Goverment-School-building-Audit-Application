package com.example.buildingaudit.Model;

public class SmartClassesCard {

    String InstallationYear,CompanyName,Scheme,OtherSchemeYN,Srno,Name,WorkingStatus;

    public SmartClassesCard() {
    }

    public SmartClassesCard(String installationYear, String companyName, String scheme, String otherSchemeYN, String srno, String name, String workingStatus) {
        InstallationYear = installationYear;
        CompanyName = companyName;
        Scheme = scheme;
        OtherSchemeYN = otherSchemeYN;
        Srno = srno;
        Name = name;
        WorkingStatus = workingStatus;
    }

    public String getInstallationYear() {
        return InstallationYear;
    }

    public void setInstallationYear(String installationYear) {
        InstallationYear = installationYear;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getScheme() {
        return Scheme;
    }

    public void setScheme(String scheme) {
        Scheme = scheme;
    }

    public String getOtherSchemeYN() {
        return OtherSchemeYN;
    }

    public void setOtherSchemeYN(String otherSchemeYN) {
        OtherSchemeYN = otherSchemeYN;
    }

    public String getSrno() {
        return Srno;
    }

    public void setSrno(String srno) {
        Srno = srno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWorkingStatus() {
        return WorkingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        WorkingStatus = workingStatus;
    }
}
