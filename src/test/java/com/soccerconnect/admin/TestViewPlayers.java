package com.soccerconnect.admin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.soccerconnect.database.queries.user.AdminQueries;
import com.soccerconnect.models.user.PlayerModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@SpringBootTest
public class TestViewPlayers {

    // Class to test view players for an admin

    AdminQueries aq;

    @BeforeEach
    void setup() {
        aq = new AdminQueries(mock(Connection.class));
    }

    @Test
    void TestViewAllPlayers() throws SQLException {
        // Base method to test view players
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("User_ID")).thenReturn("100");
        when(resultSetMock.getString("Name")).thenReturn("Micheal Scott");
        when(resultSetMock.getString("email_id")).thenReturn("mikescott@gmail.com");
        when(resultSetMock.getString("phone_no")).thenReturn("9029891234");
        when(resultSetMock.getString("gender")).thenReturn("male");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(aq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<PlayerModel> expected_players = new ArrayList<>();
        expected_players.add(new PlayerModel("100", "Micheal Scott",
                                        "mikescott@gmail.com","9029891234","male"));
        ArrayList<PlayerModel> actual_players =  aq.getAllPlayers();

        assertEquals(expected_players.size(), actual_players.size());
        for (int i = 0; i<expected_players.size(); i++){
            PlayerModel expected_player = expected_players.get(i);
            PlayerModel actual_player = actual_players.get(i);
            assertEquals(expected_player.getUserId(), actual_player.getUserId());
            assertEquals(expected_player.getName(), actual_player.getName());
            assertEquals(expected_player.getEmail(), actual_player.getEmail());
            assertEquals(expected_player.getMobile(), actual_player.getMobile());
            assertEquals(expected_player.getCategory(), actual_player.getCategory());
        }
    }

    @Test
    void TestViewAllPlayersEmptyData() throws SQLException {
        // Test view all players when DB returns empty return
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(false);
        when(aq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<PlayerModel> actual_players =  aq.getAllPlayers();
        assertEquals(0, actual_players.size());

    }

}
