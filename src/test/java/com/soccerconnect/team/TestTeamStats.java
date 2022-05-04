package com.soccerconnect.team;

import com.soccerconnect.database.queries.user.TeamsQueries;
import com.soccerconnect.models.stats.TeamStatsModel;
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

public class TestTeamStats {
    TeamsQueries teamsQueries;
    @BeforeEach
    void setup() {
        teamsQueries = new TeamsQueries(mock(Connection.class));
    }

    @Test
    void TestViewTeamPlayers() throws SQLException {

        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("NOM")).thenReturn("76");
        when(resultSetMock.getString("Goals")).thenReturn("111");
        when(resultSetMock.getString("Wins")).thenReturn("17");
        when(resultSetMock.getString("Losses")).thenReturn("28");
        when(resultSetMock.getString("Draws")).thenReturn("31");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(teamsQueries.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        TeamStatsModel teamStats = teamsQueries.getTeamStats("5");

        assertEquals(teamStats.getNom(),resultSetMock.getString("NOM"));
        assertEquals(teamStats.getGoals(),resultSetMock.getString("Goals"));
        assertEquals(teamStats.getWins(),resultSetMock.getString("Wins"));
        assertEquals(teamStats.getLosses(),resultSetMock.getString("Losses"));
        assertEquals(teamStats.getDraws(),resultSetMock.getString("Draws"));

    }
}

