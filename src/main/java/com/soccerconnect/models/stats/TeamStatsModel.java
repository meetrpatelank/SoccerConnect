package com.soccerconnect.models.stats;

public class TeamStatsModel {

    // Model class which holds team stats of a team. Unique for a team.

    String teamId;
    String teamName;
    String nom;
    String goals;
    String wins;
    String losses;
    String draws;

    public TeamStatsModel(String teamId, String nom, String goals, String wins,
                          String losses, String draws, String teamName) {
        this.teamId = teamId;
        this.nom = nom;
        this.goals = goals;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.teamName = teamName;
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

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
