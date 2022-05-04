package com.soccerconnect.database.queries.user;

import com.soccerconnect.models.stats.PlayerStatsModel;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.stats.TeamStatsModel;
import com.soccerconnect.models.user.TeamModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerQueries implements PlayerQueriesInterface {

    public Connection conn;

    public PlayerQueries(Connection conn) {
        this.conn = conn;
    }

    public PlayerStatsModel getPlayerStats(String playerId){

        PlayerStatsModel playerStats =null;
        String query = "SELECT sum(NOM) as totalNOM, sum(goals) as totalGoals,sum(assists) as totalAssists,sum(goals_saved) as totalGoalsSaved," +
                "sum(yellow_Card) as totalYellowCard,sum(red_card) as totalRedCard,sum(MOM) as totalMOM" +
                " from PlayerStats where player_id ="+playerId +";";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            playerStats = new PlayerStatsModel(playerId, rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5),
                            rs.getString(6), rs.getString(7));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return playerStats;
    }

    public ArrayList<PlayerModel> getPlayers(String teamId){
        ArrayList<PlayerModel> players=new ArrayList<>();
        String query = "SELECT User_ID,Name from users where Role_Id='1' AND User_ID NOT IN " +
                "(SELECT Player_ID from PlayerStats where Team_ID='"+ teamId +"' UNION SELECT From_ID from " +
                "requests where To_ID='"+ teamId +"');";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                players.add(new PlayerModel(rs.getString("User_ID"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return players;
    }

    public ArrayList<TeamStatsModel> getEachTeamStats(String playerId){

        ArrayList<TeamStatsModel> teamStats=new ArrayList<>();
        String query = "SELECT TeamStats.Team_ID, TeamStats.NOM, TeamStats.Goals, Wins, Losses, Draws " +
                "FROM TeamStats INNER JOIN PlayerStats ON PlayerStats.Team_ID=TeamStats.Team_ID where " +
                "PlayerStats.Player_ID = "+ playerId + ";";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String query2 = "SELECT Name from users where user_id ="+rs.getString("Team_ID") +";";
                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(query2);
                rs2.next();

                teamStats.add(new TeamStatsModel(rs.getString("Team_ID"),
                        rs.getString("NOM"), rs.getString("Goals"),
                        rs.getString("Wins"), rs.getString("Losses"),
                        rs.getString("Draws"), rs2.getString(1)));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamStats;
    }

    public ArrayList<TeamModel> getPlayersTeams(String playerId){
        ArrayList<TeamModel> playerTeams = new ArrayList<>();
        String query = "SELECT Player_ID,Team_ID,users.Name from PlayerStats JOIN users ON " +
                "Team_id=User_ID AND Player_ID='"+playerId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                playerTeams.add(new TeamModel(rs.getString("Team_ID"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return playerTeams;
    }

    public void removeTeam(String teamId, String playerId){
        String query = "DELETE FROM PlayerStats " +
                "WHERE PLAYER_ID='" + playerId + "' AND TEAM_ID='" + teamId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void acceptRequest(String teamId, String playerId) {
       
        String query = "INSERT INTO PlayerStats (Player_ID, Team_ID) " +
                "VALUES ('" + playerId + "','" + teamId + "');";
        String deleteQuery = "DELETE FROM requests where To_ID='" + playerId + "' AND From_ID='" + teamId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.executeUpdate(deleteQuery);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void rejectRequest(String teamId, String playerId) {

        String rejectQuery = "DELETE FROM requests where To_ID='" + playerId + "' AND From_ID='" + teamId + "';";

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(rejectQuery);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addPlayerStats(String playerId,String teamId) {

        String query = "INSERT INTO PlayerStats (Player_Id, Team_id, NOM, Goals,Assists,Goals_saved,Yellow_card,Red_card,MOM) VALUES ('\"\n" +playerId
                +"," + teamId +  " \"','0','0','0','0','0','0','0');";

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("executed ");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
