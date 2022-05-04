package com.soccerconnect.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.soccerconnect.database.queries.user.PlayerQueries;
import com.soccerconnect.models.stats.PlayerStatsModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
public class TestViewPlayerStats{

    PlayerQueries playerq = null;

    @BeforeEach
    void setup(){
        playerq = new PlayerQueries(mock(Connection.class));
    }

    @Test
    void PlayerStats() throws SQLException {
        playerq.conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("playerId")).thenReturn("1");
        when(resultSetMock.getString(1)).thenReturn("4");
        when(resultSetMock.getString(2)).thenReturn("4");
        when(resultSetMock.getString(3)).thenReturn("0");
        when(resultSetMock.getString(4)).thenReturn("0");
        when(resultSetMock.getString(5)).thenReturn("0");
        when(resultSetMock.getString(6)).thenReturn("2");
        when(resultSetMock.getString(7)).thenReturn("2");

        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(playerq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        PlayerStatsModel testPlayerStats = playerq.getPlayerStats("1");

        assertEquals(testPlayerStats.getPlayerId(),resultSetMock.getString("playerId"));
        assertEquals(testPlayerStats.getNom(),resultSetMock.getString(1));
        assertEquals(testPlayerStats.getGoals(),resultSetMock.getString(2));
        assertEquals(testPlayerStats.getAsst(),resultSetMock.getString(3));
        assertEquals(testPlayerStats.getGoalsSaved(),resultSetMock.getString(4));
        assertEquals(testPlayerStats.getYellowCards(),resultSetMock.getString(5));
        assertEquals(testPlayerStats.getRedCards(),resultSetMock.getString(6));
        assertEquals(testPlayerStats.getMom(),resultSetMock.getString(7));

    }
    
}

