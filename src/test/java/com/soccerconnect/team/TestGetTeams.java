package com.soccerconnect.team;


import com.soccerconnect.database.queries.user.TeamsQueries;
import com.soccerconnect.models.user.TeamModel;
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

public class TestGetTeams {
    TeamsQueries teamsQueries;
    @BeforeEach
    void setup() {
        teamsQueries = new TeamsQueries(mock(Connection.class));
    }

    @Test
    void TestViewTeamPlayers() throws SQLException {

        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("User_ID")).thenReturn("16");
        when(resultSetMock.getString("Name")).thenReturn("Team1");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(teamsQueries.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);


        ArrayList<TeamModel> expectedTeams = new ArrayList<>();
        expectedTeams.add(new TeamModel("16","Team1"));
        ArrayList<TeamModel> actualTeams = teamsQueries.getTeams("23");

        for(int i = 0; i < actualTeams.size();i++){

            String expectedUser = expectedTeams.get(i).getUserId();
            String expectedName = expectedTeams.get(i).getName();
            String actualUser = actualTeams.get(i).getUserId();
            String actualName = actualTeams.get(i).getName();

            assertEquals(expectedName,actualName);
            assertEquals(expectedUser,actualUser);

        }

    }
}
