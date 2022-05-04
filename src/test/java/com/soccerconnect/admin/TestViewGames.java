package com.soccerconnect.admin;

import com.soccerconnect.database.queries.user.AdminQueries;
import com.soccerconnect.database.queries.game.GamesQueries;
import com.soccerconnect.models.game.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestViewGames {

    // Class to test view games functionality of the admin

    GamesQueries gq;
    AdminQueries aq;

    @BeforeEach
    void setup() {
        gq = new GamesQueries(mock(Connection.class));
        aq = mock(AdminQueries.class);
    }

    @Test
    void TestViewAllGames() throws SQLException {
        // Base case to view all the games
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("game_ID")).thenReturn("400");
        when(resultSetMock.getString("category")).thenReturn("male");
        when(resultSetMock.getString("team1")).thenReturn("200");
        when(resultSetMock.getString("team2")).thenReturn("250");
        when(resultSetMock.getString("ground")).thenReturn("300");
        when(resultSetMock.getString("date")).thenReturn("2021-11-24");
        when(resultSetMock.getString("time")).thenReturn("09:00:00");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(gq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        HashMap<String, String> teamIdToName = new HashMap<>();
        teamIdToName.put("200", "Avengers");
        teamIdToName.put("250", "Justice League");
        HashMap<String, String> groundIdToName = new HashMap<>();
        groundIdToName.put("300", "Halifax Commons");

        when(aq.getTeamIdToName()).thenReturn(teamIdToName);
        when(aq.getGroundIdToName()).thenReturn(groundIdToName);

        ArrayList<GameModel> expected_games = new ArrayList<>();
        expected_games.add(new GameModel("400", "male", "200", "250",
                "300", "2021-11-24", "09:00:00", "Avengers",
                "Justice League","Halifax Commons"));
        ArrayList<GameModel> actual_games =  gq.getGames(aq);

        assertEquals(expected_games.size(), actual_games.size());
        for (int i = 0; i<expected_games.size(); i++){
            GameModel expected_game = expected_games.get(i);
            GameModel actual_game = actual_games.get(i);
            assertEquals(expected_game.getGameId(), actual_game.getGameId());
            assertEquals(expected_game.getCategory(), actual_game.getCategory());
            assertEquals(expected_game.getTeam1Id(), actual_game.getTeam1Id());
            assertEquals(expected_game.getTeam2Id(), actual_game.getTeam2Id());
            assertEquals(expected_game.getGroundId(), actual_game.getGroundId());
            assertEquals(expected_game.getDate(), actual_game.getDate());
            assertEquals(expected_game.getTime(), actual_game.getTime());
        }
    }

    @Test
    void TestViewAllGamesEmptyData() throws SQLException {
        // View games in case of no data in the DB
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(false);
        when(gq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<GameModel> actual_games =  gq.getGames(aq);
        assertEquals(0, actual_games.size());
    }
}
