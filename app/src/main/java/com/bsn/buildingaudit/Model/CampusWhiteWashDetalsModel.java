package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class CampusWhiteWashDetalsModel {

    @SerializedName("SCHOOLID")
    @Expose
    private Integer schoolid;
    @SerializedName("PERIODID")
    @Expose
    private Integer periodid;
    @SerializedName("LASTDONEFY")
    @Expose
    private Integer lastdonefy;
    @SerializedName("WHITEWASHSTATUS")
    @Expose
    private String whitewashstatus;
    @SerializedName("BUDGET")
    @Expose
    private String budget;
    @SerializedName("SANCTIONAMT")
    @Expose
    private String sanctionamt;
    @SerializedName("EXPENDITUREAMT")
    @Expose
    private String expenditureamt;
    @SerializedName("MAINWINDOWDOORS")
    @Expose
    private String mainwindowdoors;
    @SerializedName("BLACKBOARDPAINTING")
    @Expose
    private String blackboardpainting;
    @SerializedName("FURNITUREREPAIR")
    @Expose
    private String furniturerepair;
    @SerializedName("WATERTANKCLEANING")
    @Expose
    private String watertankcleaning;
    @SerializedName("DRAINAGECLEANING")
    @Expose
    private String drainagecleaning;
    @SerializedName("TOILETSCLEANING")
    @Expose
    private String toiletscleaning;
    @SerializedName("EEMAINTENANCE")
    @Expose
    private String eemaintenance;
    @SerializedName("BOARDHONURSTUDENT")
    @Expose
    private String boardhonurstudent;
    @SerializedName("BOARDHONURTEACHER")
    @Expose
    private String boardhonurteacher;
    @SerializedName("SIGNBOARD")
    @Expose
    private String signboard;
    @SerializedName("REASON")
    @Expose
    private Object reason;
    @SerializedName("STATUSID")
    @Expose
    private Integer statusid;
    @SerializedName("REJECT_REASON")
    @Expose
    private String rejectReason;
    @SerializedName("STATUS_DATE")
    @Expose
    private String statusDate;
    @SerializedName("ELECT")
    @Expose
    private String elect;
    @SerializedName("WHETHERREPAIRWORKOFWALL")
    @Expose
    private String whetherrepairworkofwall;
    @SerializedName("YEAROFFINALREPAIR")
    @Expose
    private Integer yearoffinalrepair;
    @SerializedName("REPAIRWORKOFWINDOWS")
    @Expose
    private String repairworkofwindows;
    @SerializedName("YEAROFLASTREPAIRWORK")
    @Expose
    private Integer yearoflastrepairwork;
    @SerializedName("REPAIRWORKRELATEDTOTOILETSDRINKING")
    @Expose
    private String repairworkrelatedtotoiletsdrinking;
    @SerializedName("YEAROFLASTREPAIRWORKRELATEDTOTOILET")
    @Expose
    private Integer yearoflastrepairworkrelatedtotoilet;
    @SerializedName("HASTHEBOUNDARYWALLREPAIRDONE")
    @Expose
    private String hastheboundarywallrepairdone;
    @SerializedName("YEAROFFINALREPAIRWORKOFBOUNDARYWALL")
    @Expose
    private Integer yearoffinalrepairworkofboundarywall;
    @SerializedName("HASTHEREPAIRWORKOFTHEGATEBEENDONE")
    @Expose
    private String hastherepairworkofthegatebeendone;
    @SerializedName("YEAROFLASTREPAIRWORKOFGATE")
    @Expose
    private Integer yearoflastrepairworkofgate;
    @SerializedName("RAMPAVAILABLEFORTHEHANDICAPPED")
    @Expose
    private String rampavailableforthehandicapped;
    @SerializedName("REPAIRWORKOFRAMPFORDIFFERENTLY")
    @Expose
    private Object repairworkoframpfordifferently;
    @SerializedName("YEAROFLASTREPAIRFORDIVYANGJAN")
    @Expose
    private Integer yearoflastrepairfordivyangjan;

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public Integer getPeriodid() {
        return periodid;
    }

    public void setPeriodid(Integer periodid) {
        this.periodid = periodid;
    }

    public Integer getLastdonefy() {
        return lastdonefy;
    }

    public void setLastdonefy(Integer lastdonefy) {
        this.lastdonefy = lastdonefy;
    }

    public String getWhitewashstatus() {
        return whitewashstatus;
    }

    public void setWhitewashstatus(String whitewashstatus) {
        this.whitewashstatus = whitewashstatus;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getSanctionamt() {
        return sanctionamt;
    }

    public void setSanctionamt(String sanctionamt) {
        this.sanctionamt = sanctionamt;
    }

    public String getExpenditureamt() {
        return expenditureamt;
    }

    public void setExpenditureamt(String expenditureamt) {
        this.expenditureamt = expenditureamt;
    }

    public String getMainwindowdoors() {
        return mainwindowdoors;
    }

    public void setMainwindowdoors(String mainwindowdoors) {
        this.mainwindowdoors = mainwindowdoors;
    }

    public String getBlackboardpainting() {
        return blackboardpainting;
    }

    public void setBlackboardpainting(String blackboardpainting) {
        this.blackboardpainting = blackboardpainting;
    }

    public String getFurniturerepair() {
        return furniturerepair;
    }

    public void setFurniturerepair(String furniturerepair) {
        this.furniturerepair = furniturerepair;
    }

    public String getWatertankcleaning() {
        return watertankcleaning;
    }

    public void setWatertankcleaning(String watertankcleaning) {
        this.watertankcleaning = watertankcleaning;
    }

    public String getDrainagecleaning() {
        return drainagecleaning;
    }

    public void setDrainagecleaning(String drainagecleaning) {
        this.drainagecleaning = drainagecleaning;
    }

    public String getToiletscleaning() {
        return toiletscleaning;
    }

    public void setToiletscleaning(String toiletscleaning) {
        this.toiletscleaning = toiletscleaning;
    }

    public String getEemaintenance() {
        return eemaintenance;
    }

    public void setEemaintenance(String eemaintenance) {
        this.eemaintenance = eemaintenance;
    }

    public String getBoardhonurstudent() {
        return boardhonurstudent;
    }

    public void setBoardhonurstudent(String boardhonurstudent) {
        this.boardhonurstudent = boardhonurstudent;
    }

    public String getBoardhonurteacher() {
        return boardhonurteacher;
    }

    public void setBoardhonurteacher(String boardhonurteacher) {
        this.boardhonurteacher = boardhonurteacher;
    }

    public String getSignboard() {
        return signboard;
    }

    public void setSignboard(String signboard) {
        this.signboard = signboard;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getElect() {
        return elect;
    }

    public void setElect(String elect) {
        this.elect = elect;
    }

    public String getWhetherrepairworkofwall() {
        return whetherrepairworkofwall;
    }

    public void setWhetherrepairworkofwall(String whetherrepairworkofwall) {
        this.whetherrepairworkofwall = whetherrepairworkofwall;
    }

    public Integer getYearoffinalrepair() {
        return yearoffinalrepair;
    }

    public void setYearoffinalrepair(Integer yearoffinalrepair) {
        this.yearoffinalrepair = yearoffinalrepair;
    }

    public String getRepairworkofwindows() {
        return repairworkofwindows;
    }

    public void setRepairworkofwindows(String repairworkofwindows) {
        this.repairworkofwindows = repairworkofwindows;
    }

    public Integer getYearoflastrepairwork() {
        return yearoflastrepairwork;
    }

    public void setYearoflastrepairwork(Integer yearoflastrepairwork) {
        this.yearoflastrepairwork = yearoflastrepairwork;
    }

    public String getRepairworkrelatedtotoiletsdrinking() {
        return repairworkrelatedtotoiletsdrinking;
    }

    public void setRepairworkrelatedtotoiletsdrinking(String repairworkrelatedtotoiletsdrinking) {
        this.repairworkrelatedtotoiletsdrinking = repairworkrelatedtotoiletsdrinking;
    }

    public Integer getYearoflastrepairworkrelatedtotoilet() {
        return yearoflastrepairworkrelatedtotoilet;
    }

    public void setYearoflastrepairworkrelatedtotoilet(Integer yearoflastrepairworkrelatedtotoilet) {
        this.yearoflastrepairworkrelatedtotoilet = yearoflastrepairworkrelatedtotoilet;
    }

    public String getHastheboundarywallrepairdone() {
        return hastheboundarywallrepairdone;
    }

    public void setHastheboundarywallrepairdone(String hastheboundarywallrepairdone) {
        this.hastheboundarywallrepairdone = hastheboundarywallrepairdone;
    }

    public Integer getYearoffinalrepairworkofboundarywall() {
        return yearoffinalrepairworkofboundarywall;
    }

    public void setYearoffinalrepairworkofboundarywall(Integer yearoffinalrepairworkofboundarywall) {
        this.yearoffinalrepairworkofboundarywall = yearoffinalrepairworkofboundarywall;
    }

    public String getHastherepairworkofthegatebeendone() {
        return hastherepairworkofthegatebeendone;
    }

    public void setHastherepairworkofthegatebeendone(String hastherepairworkofthegatebeendone) {
        this.hastherepairworkofthegatebeendone = hastherepairworkofthegatebeendone;
    }

    public Integer getYearoflastrepairworkofgate() {
        return yearoflastrepairworkofgate;
    }

    public void setYearoflastrepairworkofgate(Integer yearoflastrepairworkofgate) {
        this.yearoflastrepairworkofgate = yearoflastrepairworkofgate;
    }

    public String getRampavailableforthehandicapped() {
        return rampavailableforthehandicapped;
    }

    public void setRampavailableforthehandicapped(String rampavailableforthehandicapped) {
        this.rampavailableforthehandicapped = rampavailableforthehandicapped;
    }

    public Object getRepairworkoframpfordifferently() {
        return repairworkoframpfordifferently;
    }

    public void setRepairworkoframpfordifferently(Object repairworkoframpfordifferently) {
        this.repairworkoframpfordifferently = repairworkoframpfordifferently;
    }

    public Integer getYearoflastrepairfordivyangjan() {
        return yearoflastrepairfordivyangjan;
    }

    public void setYearoflastrepairfordivyangjan(Integer yearoflastrepairfordivyangjan) {
        this.yearoflastrepairfordivyangjan = yearoflastrepairfordivyangjan;
    }

}
