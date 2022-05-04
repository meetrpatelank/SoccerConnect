package com.soccerconnect.models.stats;

import java.util.ArrayList;

public class StatsModel {

    // Model class which holds the stats of a game. Holds player stats of each player of both the teams. Unique for a game.

    public ArrayList<PlayerStatsModel> team1PlayersStats;
    public ArrayList<PlayerStatsModel> team2PlayersStats;
    public String team1Id;
    public String team2Id;
    String team1Goals;
    String team2Goals;

    public StatsModel(ArrayList<PlayerStatsModel> team1PlayersStats, ArrayList<PlayerStatsModel> team2PlayersStats) {
        this.team1PlayersStats = team1PlayersStats;
        this.team2PlayersStats = team2PlayersStats;
    }

    public ArrayList<PlayerStatsModel> getTeam1PlayersStats() {
        return team1PlayersStats;
    }

    public void setTeam1PlayersStats(ArrayList<PlayerStatsModel> team1PlayersStats) {
        this.team1PlayersStats = team1PlayersStats;
    }

    public ArrayList<PlayerStatsModel> getTeam2PlayersStats() {
        return team2PlayersStats;
    }

    public void setTeam2PlayersStats(ArrayList<PlayerStatsModel> team2PlayersStats) {
        this.team2PlayersStats = team2PlayersStats;
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

    public String getTeam1Goals() {
        return team1Goals;
    }

    public void setTeam1Goals(String team1Goals) {
        this.team1Goals = team1Goals;
    }

    public String getTeam2Goals() {
        return team2Goals;
    }

    public void setTeam2Goals(String team2Goals) {
        this.team2Goals = team2Goals;
    }
}
