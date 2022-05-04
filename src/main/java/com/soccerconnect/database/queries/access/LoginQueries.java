package com.soccerconnect.database.queries.access;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginQueries implements LoginQueriesInterface {

    public Connection conn;

    public LoginQueries(Connection conn) {
        this.conn = conn;
    }

    public String getPasswordFromEmail(String email){
        // Method to get password from given email
        String password = "";
        String query = "SELECT Password from users where Email_ID='" + email + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                password = rs.getString("Password");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return password;
    }
}
