package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class P {

    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;
    @SerializedName("ProposedModule")
    @Expose
    private String proposedModule;
    @SerializedName("CompletedModule")
    @Expose
    private String completedModule;
    @SerializedName("TrainingFor")
    @Expose
    private String trainingFor;
    @SerializedName("PrincipalName")
    @Expose
    private String principalName;
    @SerializedName("TrainingID")
    @Expose
    private Integer trainingID;
    @SerializedName("TrainingName")
    @Expose
    private String trainingName;
    @SerializedName("TrainingDuration")
    @Expose
    private String trainingDuration;
    @SerializedName("AgencyName")
    @Expose
    private String agencyName;
    @SerializedName("ModeOfTraining")
    @Expose
    private String modeOfTraining;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getProposedModule() {
        return proposedModule;
    }

    public void setProposedModule(String proposedModule) {
        this.proposedModule = proposedModule;
    }

    public String getCompletedModule() {
        return completedModule;
    }

    public void setCompletedModule(String completedModule) {
        this.completedModule = completedModule;
    }

    public String getTrainingFor() {
        return trainingFor;
    }

    public void setTrainingFor(String trainingFor) {
        this.trainingFor = trainingFor;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public Integer getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(Integer trainingID) {
        this.trainingID = trainingID;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(String trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getModeOfTraining() {
        return modeOfTraining;
    }

    public void setModeOfTraining(String modeOfTraining) {
        this.modeOfTraining = modeOfTraining;
    }

}