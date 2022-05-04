package com.soccerconnect.admin;

import com.soccerconnect.database.queries.ground.GroundQueries;
import com.soccerconnect.models.ground.GroundModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestViewGrounds {

    // Class to test View grounds of the admin

    GroundQueries gq;

    @BeforeEach
    void setup() {
        gq = new GroundQueries(mock(Connection.class));
    }

    @Test
    void TestViewAllGrounds() throws SQLException {
        // Base case to view all grounds
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("Ground_ID")).thenReturn("300");
        when(resultSetMock.getString("Ground_Name")).thenReturn("Halifax Commons");
        when(resultSetMock.getString("address")).thenReturn("5816 Cogswell St, Halifax, NS");
        when(resultSetMock.getString("postal_code")).thenReturn("B3H2Z4");
        when(resultSetMock.getString("contact_no")).thenReturn("9029899876");
        when(resultSetMock.getString("email")).thenReturn("hfxcom@gmail.com");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(gq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<GroundModel> expected_grounds = new ArrayList<>();
        expected_grounds.add(new GroundModel("300", "Halifax Commons",
                            "5816 Cogswell St, Halifax, NS","B3H2Z4",
                            "9029899876","hfxcom@gmail.com"));
        ArrayList<GroundModel> actual_grounds =  gq.getAllGrounds();

        assertEquals(expected_grounds.size(), actual_grounds.size());
        for (int i = 0; i<expected_grounds.size(); i++){
            GroundModel expected_ground = expected_grounds.get(i);
            GroundModel actual_ground = actual_grounds.get(i);
            assertEquals(expected_ground.getGroundId(), actual_ground.getGroundId());
            assertEquals(expected_ground.getGroundName(), actual_ground.getGroundName());
            assertEquals(expected_ground.getAddress(), actual_ground.getAddress());
            assertEquals(expected_ground.getPostalCode(), actual_ground.getPostalCode());
            assertEquals(expected_ground.getContact(), actual_ground.getContact());
            assertEquals(expected_ground.getEmail(), actual_ground.getEmail());
        }
    }

    @Test
    void TestViewAllGroundsEmptyData() throws SQLException {
        // Test case to view all grounds when DB is empty
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(false);
        when(gq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<GroundModel> actual_grounds =  gq.getAllGrounds();
        assertEquals(0, actual_grounds.size());

    }

}
