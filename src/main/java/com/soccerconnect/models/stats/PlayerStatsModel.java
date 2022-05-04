package com.soccerconnect.models.stats;

public class PlayerStatsModel {

    String playerId;
    String teamId;
    String nom;
    String goals;
    String asst;
    String goalsSaved;
    String yellowCards;
    String redCards;
    String mom;
    String playerName;

    public PlayerStatsModel(String playerId, String nom, String goals, String asst,
                            String goalsSaved, String yellowCard, String redCard, String mom) {
        this.playerId = playerId;
        this.nom = nom;
        this.goals = goals;
        this.asst = asst;
        this.goalsSaved = goalsSaved;
        this.yellowCards = yellowCard;
        this.redCards = redCard;
        this.mom = mom;
    }

    public PlayerStatsModel(String playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public PlayerStatsModel() {
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getAsst() {
        return asst;
    }

    public void setAsst(String asst) {
        this.asst = asst;
    }

    public String getGoalsSaved() {
        return goalsSaved;
    }

    public void setGoalsSaved(String goalsSaved) {
        this.goalsSaved = goalsSaved;
    }

    public String getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(String yellowCards) {
        this.yellowCards = yellowCards;
    }

    public String getRedCards() {
        return redCards;
    }

    public void setRedCards(String redCards) {
        this.redCards = redCards;
    }

    public String getMom() {
        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
