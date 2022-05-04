package com.soccerconnect.database.queries.user;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class RequestsQueries implements RequestsQueriesInterface {

    public Connection conn;

    public RequestsQueries(Connection conn) {
        this.conn = conn;
    }

    public HashMap<String, String> getRequests(String fromId){
        HashMap<String, String> teams=new HashMap<>();
        String query = "SELECT Name,Status from requests JOIN users ON To_ID=User_ID AND From_ID='"+fromId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teams.put(rs.getString("Name"), rs.getString("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teams;
    }

    public void addRequest(String fromId, String toId){
        String query = "INSERT INTO requests (From_ID, To_ID, Status) " +
                "VALUES ('" + fromId + "','" + toId + "','Request Sent');";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public HashMap<String, String> getReceivedRequests(String toId){
        HashMap<String, String> teams=new HashMap<>();
        String query = "SELECT User_ID,Name from requests JOIN users ON From_ID=User_ID AND To_ID='"+toId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teams.put(rs.getString("User_ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teams;
    }
}
