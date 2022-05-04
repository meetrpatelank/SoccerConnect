package com.soccerconnect.database.queries.access;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RolesQueries implements RolesQueriesInterface {

    public Connection conn;

    public RolesQueries(Connection conn) {
        this.conn = conn;
    }

    public int getRoleFromEmail(String email){
        // Method to get user role from email
        int roleID = -1;
        String query = "SELECT Role_ID from users where Email_ID='" + email + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roleID = rs.getInt("Role_ID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return roleID;
    }

    public int getRoleFromUserId(String userId){
        // Method to get user role from user Id
        int roleID = -1;
        String query = "SELECT Role_ID from users where User_ID='" + userId + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roleID = rs.getInt("Role_ID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return roleID;
    }

    public String getUserId(String email){
        //Method to get user Id for a given email
        String userID = "-1";
        String query = "SELECT User_ID from users where Email_ID='" + email + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                userID = rs.getString("User_ID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userID;
    }
}
