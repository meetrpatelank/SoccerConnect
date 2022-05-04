package com.soccerconnect.database.queries.user;

import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.stats.TeamStatsModel;
import com.soccerconnect.models.user.TeamModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamsQueries implements TeamsQueriesInterface {

    public Connection conn;


    public TeamsQueries(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<TeamModel> getTeams(String playerId){
        // Method to get all the teams except for the team the player is in
        ArrayList<TeamModel> teams=new ArrayList<>();
        String query = "SELECT User_ID,Name from users where Role_Id='2' AND User_ID NOT IN " +
                "(SELECT From_id from requests where To_id=" + playerId + ");";

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                teams.add(new TeamModel(rs.getString("User_ID"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teams;
    }

    public void acceptRequest(String playerId, String teamId){
        // Method for team accepting a players request
        String query = "INSERT INTO PlayerStats (Player_ID, Team_ID) " +
                "VALUES ('" + playerId + "','" + teamId + "');";
        String deleteQuery = "DELETE FROM requests where To_ID='" + teamId + "' AND From_ID='" + playerId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.executeUpdate(deleteQuery);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void rejectRequest(String playerId, String teamId) {
        // Method for team to reject player request
        String rejectQuery = "DELETE FROM requests where To_ID='" + teamId + "' AND From_ID='" + playerId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(rejectQuery);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public TeamStatsModel getTeamStats(String teamId) {
        // Method to get the team stats of a team
        TeamStatsModel teamStats = null;
        String query = "SELECT * from TeamStats WHERE Team_ID='" + teamId + "';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teamStats = new TeamStatsModel(teamId, rs.getString("NOM"),
                        rs.getString("Goals"), rs.getString("Wins"),
                        rs.getString("Losses"), rs.getString("Draws"), null);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamStats;
    }

    public ArrayList<PlayerModel> getTeamPlayers(String teamId){
        // Method to get all the players of a team
        ArrayList<PlayerModel> teamPlayers = new ArrayList<>();
        String query = "SELECT Player_ID,Name from PlayerStats JOIN users ON Player_ID=User_ID AND Team_ID='"+teamId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teamPlayers.add(new PlayerModel(rs.getString("Player_ID"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamPlayers;
    }

    public void deletePlayer(String playerId, String teamId){
        // Method to delete a player from the team
        String query = "DELETE FROM PlayerStats " +
                "WHERE PLAYER_ID='" + playerId + "' AND TEAM_ID='" + teamId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
}
