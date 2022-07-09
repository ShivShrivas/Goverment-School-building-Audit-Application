package com.bsn.buildingaudit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameDetailsModel {

    @SerializedName("GameData")
    @Expose
    private List<GameDatum> gameData = null;
    @SerializedName("GameName")
    @Expose
    private List<GameName> gameName = null;
    @SerializedName("GameStudentName")
    @Expose
    private List<GameStudentName> gameStudentName = null;

    public List<GameDatum> getGameData() {
        return gameData;
    }

    public void setGameData(List<GameDatum> gameData) {
        this.gameData = gameData;
    }

    public List<GameName> getGameName() {
        return gameName;
    }

    public void setGameName(List<GameName> gameName) {
        this.gameName = gameName;
    }

    public List<GameStudentName> getGameStudentName() {
        return gameStudentName;
    }

    public void setGameStudentName(List<GameStudentName> gameStudentName) {
        this.gameStudentName = gameStudentName;
    }

}
