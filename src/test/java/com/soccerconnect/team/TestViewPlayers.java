package com.soccerconnect.team;


import com.soccerconnect.database.queries.game.GamesQueries;
import com.soccerconnect.database.queries.user.TeamsQueries;
import com.soccerconnect.models.user.PlayerModel;
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

public class TestViewPlayers {

    TeamsQueries teamsQueries;
    @BeforeEach
    void setup() {
        teamsQueries = new TeamsQueries(mock(Connection.class));

    }
    String teamId = "2";
    @Test
    void TestViewTeamPlayers() throws SQLException {

        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("Player_ID")).thenReturn("58");
        when(resultSetMock.getString("Name")).thenReturn("Player1");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(teamsQueries.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<PlayerModel> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new PlayerModel("58","Player1"));
        ArrayList<PlayerModel> actualPlayers = teamsQueries.getTeamPlayers(teamId);

        for(int i = 0; i < expectedPlayers.size();i++){

            String expectedUser = expectedPlayers.get(i).getUserId();
            String expectedName = expectedPlayers.get(i).getName();
            String actualUser = actualPlayers.get(i).getUserId();
            String actualName = actualPlayers.get(i).getName();

            assertEquals(expectedName,actualName);
            assertEquals(expectedUser,actualUser);

        }
    }


}






