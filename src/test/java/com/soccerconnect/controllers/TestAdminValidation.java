package com.soccerconnect.controllers;
import com.soccerconnect.controllers.user.AdminController;
import com.soccerconnect.models.stats.StatsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.soccerconnect.models.stats.PlayerStatsModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.stream.Stream;

class TestAdminValidation {

    StatsModel teamStats ;
    AdminController adminController;
    ArrayList<PlayerStatsModel> team1PlayerStats = new ArrayList<PlayerStatsModel>();
    ArrayList<PlayerStatsModel> team2PlayerStats = new ArrayList<PlayerStatsModel>();
    Model model;

    public void TestValidation()
    {

    }

    @BeforeEach
    void setup(){
        adminController = mock(AdminController.class);
        model = mock(Model.class);
        teamStats = mock(StatsModel.class);
        teamStats.setTeam1Goals("-1");
        teamStats.setTeam2Goals("-2");
        PlayerStatsModel team1Player1Stats = mock(PlayerStatsModel.class);
        PlayerStatsModel team2Player1Stats = mock(PlayerStatsModel.class);
        team1Player1Stats.setMom("1");
        team1Player1Stats.setPlayerId("1");
        team1Player1Stats.setGoals("2");
        team1Player1Stats.setPlayerName("abc");
        team1Player1Stats.setNom("1");
        team1Player1Stats.setTeamId("1");
        team1Player1Stats.setGoalsSaved("2");
        team1Player1Stats.setRedCards("1");
        team1Player1Stats.setYellowCards("2");
        team1Player1Stats.setAsst("1");
        team1PlayerStats.add(team1Player1Stats);
        teamStats.setTeam1PlayersStats(team1PlayerStats);
        team2Player1Stats.setMom("1");
        team2Player1Stats.setPlayerId("1");
        team2Player1Stats.setGoals("2");
        team2Player1Stats.setPlayerName("abc");
        team2Player1Stats.setNom("1");
        team2Player1Stats.setTeamId("121");
        team2Player1Stats.setGoalsSaved("2");
        team2Player1Stats.setRedCards("1");
        team2Player1Stats.setYellowCards("2");
        team2Player1Stats.setAsst("1");
        team2PlayerStats.add(team2Player1Stats);
        teamStats.setTeam2PlayersStats(team2PlayerStats);
        when(adminController.validationMOM(teamStats,model)).thenReturn("errorScoreGame");
    }

    public static Stream<Arguments> createTeamStats(){
        return Stream.of(Arguments.of(1,1));
    }

    @ParameterizedTest
    @MethodSource("createTeamStats")
    void validationScorePositiveTeamGoals(Integer team1Goal, Integer team2Goal) {
        assertTrue(team1Goal >= 0 && team2Goal >= 0);
    }

    @Test
    void validateMOM() {
        assertEquals("errorScoreGame",adminController.validationMOM(this.teamStats,model));
    }
}