package com.soccerconnect.database.queries.game;

import com.soccerconnect.database.queries.user.AdminQueriesInterface;
import com.soccerconnect.models.game.GameModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GamesQueries implements GamesQueriesInterface {

    public Connection conn;

    public GamesQueries(Connection conn) {
        this.conn = conn;
    }

    public void organize(String category, String team1, String team2, String ground, String date, String  time) {
        String query = "INSERT INTO games(category,team1,team2,ground,date,time) " + "VALUES ('" + category
                + "','" + team1 + "','" + team2 + "','" + ground + "','" + date + "','" + time + "');";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<GameModel> getGames(AdminQueriesInterface aq) {
        // Method to get all the available games
        ArrayList<GameModel> games= new ArrayList<>();
        HashMap<String, String> teamIdToName = aq.getTeamIdToName();
        HashMap<String, String> groundIdToName = aq.getGroundIdToName();
        String query = "SELECT * from games;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                games.add(new GameModel(rs.getString("game_ID"),
                        rs.getString("category"), rs.getString("team1"),
                        rs.getString("team2"), rs.getString("ground"),
                        rs.getString("date"), rs.getString("time"),
                        teamIdToName.get(rs.getString("team1")),
                        teamIdToName.get(rs.getString("team2")),
                        groundIdToName.get(rs.getString("ground"))));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return games;
    }

    public GameModel getGameDetails(String scoreGameId, AdminQueriesInterface aq) {
        // Method to get game details for a given game id
        GameModel game = null;
        HashMap<String, String> teamIdToName = aq.getTeamIdToName();
        String query = "SELECT * from games where game_ID='" + scoreGameId + "';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                game = new GameModel(scoreGameId,
                        rs.getString("category"), rs.getString("team1"),
                        rs.getString("team2"), rs.getString("ground"),
                        rs.getString("date"), rs.getString("time"),
                        teamIdToName.get(rs.getString("team1")),
                        teamIdToName.get(rs.getString("team2")), null);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return game;
    }

    public void addGameInfo(String gameId, String team1Goals, String team2Goals, String mom) {
        // Method to add game level info
        String query = "INSERT INTO GameInfo(game_id,team1_goals,team2_goals,mom) " + "VALUES ('" + gameId
                + "','"+team1Goals+"','"+team2Goals+"','"+mom+"');";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public HashMap<String, String> getGameScore(String scoreGameId, AdminQueriesInterface aq) {
        // Method to get game level info for a given game Id
        HashMap<String, String> gameScore = new HashMap<>();
        HashMap<String, String> playerIdToName = aq.getPlayerIdToName();
        String query = "SELECT * from GameInfo where game_id='" + scoreGameId + "';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                gameScore.put("team1Goals", rs.getString("team1_goals"));
                gameScore.put("team2Goals", rs.getString("team2_goals"));
                gameScore.put("mom", playerIdToName.get(rs.getString("mom")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println(gameScore);
        return gameScore;
    }

}
