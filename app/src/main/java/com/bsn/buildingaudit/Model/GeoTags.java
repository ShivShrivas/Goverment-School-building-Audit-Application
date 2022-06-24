package com.bsn.buildingaudit.Model;

public class GeoTags {

    String lattitude,longitute,dateAndTime;

    public GeoTags() {
    }

    public GeoTags(String lattitude, String longitute, String dateAndTime) {
        this.lattitude = lattitude;
        this.longitute = longitute;
        this.dateAndTime = dateAndTime;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitute() {
        return longitute;
    }

    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
