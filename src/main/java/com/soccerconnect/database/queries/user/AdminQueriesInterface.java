package com.soccerconnect.database.queries.user;

import com.soccerconnect.models.stats.PlayerStatsModel;
import com.soccerconnect.models.stats.TeamStatsModel;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.user.TeamModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminQueriesInterface {

    public ArrayList<PlayerModel> getAllPlayers();

    /**
     *
     * @param userId
     */
    public void deleteUser(String userId);
    public ArrayList<TeamModel> getAllTeams();
    public HashMap<String, String> getTeamIdToName();
    public HashMap<String, String> getGroundIdToName();
    /**
     *
     * @param deleteGameId
     */
    public void deleteGame(String deleteGameId);

    /**
     *
     * @param team1Stats
     */
    public void updateTeamStats(TeamStatsModel team1Stats);

    /**
     *
     * @param playerId
     * @param teamId
     * @return
     */
    public PlayerStatsModel getPlayerStatsByTeam(String playerId, String teamId);

    /**
     *
     * @param existingPlayerStat
     * @param teamId
     */
    public void updatePlayerStats(PlayerStatsModel existingPlayerStat, String teamId);
    public ArrayList<TeamStatsModel> getTeamRanking();
    public HashMap<String, String> getPlayerIdToName();

}
