package com.soccerconnect.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.soccerconnect.database.queries.user.TeamsQueries;
import com.soccerconnect.models.user.TeamModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TestViewTeams {
    TeamsQueries pq;

    @BeforeEach
    void setup() {
        pq = new TeamsQueries(mock(Connection.class));
    }

   @Test
   void TestViewTeamsPositive() throws Exception{
    pq.conn = mock(Connection.class);
    Statement stmt = mock(Statement.class);
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.getString("User_ID")).thenReturn("1");
    when(resultSetMock.getString("Name")).thenReturn("Meet");
    when(resultSetMock.next()).thenReturn(true).thenReturn(false);
    when(pq.conn.createStatement()).thenReturn(stmt);
    when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

    ArrayList<TeamModel> expected_teams = new ArrayList<>();
    expected_teams.add(new TeamModel("1", "Meet"));
    ArrayList<TeamModel> actual_teams = pq.getTeams("1");

    assertEquals(expected_teams.size(), actual_teams.size());

    for(int i = 0; i < expected_teams.size() ; i++ ){
        TeamModel expected_team = expected_teams.get(i);
        TeamModel actual_team = actual_teams.get(i);
        assertEquals(expected_team.getUserId(), actual_team.getUserId());
        assertEquals(expected_team.getName(), actual_team.getName());
    }
   }

}
