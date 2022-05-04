package com.soccerconnect.database.queries.access;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationQueries implements RegistrationQueriesInterface {

    public Connection conn;

    public RegistrationQueries(Connection conn) {
        this.conn = conn;
    }

    public void registrationQuery(String role,
                                  String email,
                                  String name,
                                  String mobile,
                                  String password,
                                  String category){

        String query = "INSERT INTO users (Name, Email_ID, Password, Phone_No, Gender, Role_ID) VALUES ('"
                + name + "','" + email + "','" + password + "','" + mobile + "','" + category + "','" + role + "');";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addTeamStats(String teamId) {
        String query = "INSERT INTO TeamStats (Team_ID, NOM, Goals, Wins, Losses, Draws) VALUES ('"
                + teamId + "','0','0','0','0','0');";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
