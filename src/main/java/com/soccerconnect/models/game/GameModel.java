package com.soccerconnect.models.game;

public class GameModel {

    String gameId;
    String category;
    String team1Id;
    String team2Id;
    String groundId;
    String date;
    String time;
    String team1Name;
    String team2Name;
    String groundName;

    public GameModel(String gameId, String category, String team1Id, String team2Id, String groundId,
                     String date, String time, String team1Name, String team2Name, String groundName) {
        this.gameId = gameId;
        this.category = category;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.groundId = groundId;
        this.date = date;
        this.time = time;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.groundName = groundName;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
    }

    public String getGroundId() {
        return groundId;
    }

    public void setGroundId(String groundId) {
        this.groundId = groundId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }
}
