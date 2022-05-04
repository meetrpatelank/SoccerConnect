package com.soccerconnect.database.queries.ground;

import com.soccerconnect.models.ground.GroundModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroundQueries implements GroundsQueriesInterface {

    public Connection conn;

    public GroundQueries(Connection conn) {
        this.conn = conn;
    }

    public void groundQuery(String groundName, String address, String postalCode, String phone, String email) {
        String query = "INSERT INTO Grounds(Ground_Name, Address, Postal_Code, Contact_No, Email) " + "VALUES ('"
                + groundName + "','" + address + "','" + postalCode + "','" + phone + "','" + email + "');";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public ArrayList<GroundModel> getAllGrounds() {
        ArrayList<GroundModel> grounds=new ArrayList<>();
        String query = "SELECT Ground_ID,Ground_Name,address,postal_code,contact_no,email from Grounds;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                grounds.add(new GroundModel(rs.getString("Ground_ID"), rs.getString("Ground_Name"),
                        rs.getString("address"),rs.getString("postal_code"),rs.getString("contact_no"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return grounds;
    }

    public void deleteGround(String groundId) {
        String query = "DELETE FROM Grounds WHERE Ground_ID='" + groundId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
