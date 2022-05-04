package com.soccerconnect.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.soccerconnect.database.queries.user.PlayerQueries;
import com.soccerconnect.models.stats.TeamStatsModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TestViewTeamStats {
    
    PlayerQueries playerQueries;

    @BeforeEach
    void setup() {
        playerQueries = new PlayerQueries(mock(Connection.class));
    }

   @Test
   void TestViewTeamsPositive() throws Exception{
    playerQueries.conn = mock(Connection.class);
    Statement stmt = mock(Statement.class);
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.getString("Team_ID")).thenReturn("1");
    when(resultSetMock.getString("NOM")).thenReturn("2");
    when(resultSetMock.getString("Goals")).thenReturn("1");
    when(resultSetMock.getString("Wins")).thenReturn("1");
    when(resultSetMock.getString("Losses")).thenReturn("1");
    when(resultSetMock.getString("Draws")).thenReturn("1");
    when(resultSetMock.getString("TeamName")).thenReturn("Meet");



    when(resultSetMock.next()).thenReturn(true).thenReturn(false);
    when(playerQueries.conn.createStatement()).thenReturn(stmt);
    when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

    ArrayList<TeamStatsModel> expected_teamsStats = new ArrayList<>();
    expected_teamsStats.add(new TeamStatsModel("1","2","1","1","1","1","Meet"));
    ArrayList<TeamStatsModel> actual_teamsStats = playerQueries.getEachTeamStats("1");

    assertEquals(expected_teamsStats.size(), actual_teamsStats.size());

    for(int i = 0; i < expected_teamsStats.size() ; i++ ){
        TeamStatsModel expected_teamstat = expected_teamsStats.get(i);
        TeamStatsModel actual_teamstat = actual_teamsStats.get(i);
        assertEquals(expected_teamstat.getTeamId(), actual_teamstat.getTeamId());
        assertEquals(expected_teamstat.getNom(), actual_teamstat.getNom());
        assertEquals(expected_teamstat.getGoals(), actual_teamstat.getGoals());
        assertEquals(expected_teamstat.getWins(), actual_teamstat.getWins());
        assertEquals(expected_teamstat.getLosses(), actual_teamstat.getLosses());
        assertEquals(expected_teamstat.getDraws(), actual_teamstat.getDraws());

    }
   }
}

