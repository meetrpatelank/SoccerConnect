package com.soccerconnect.database.queries.user;

import com.soccerconnect.models.stats.PlayerStatsModel;
import com.soccerconnect.models.stats.TeamStatsModel;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.user.TeamModel;

import java.util.ArrayList;

public interface PlayerQueriesInterface {
    /**
     *
     * @param playerId
     * @return
     */
    public PlayerStatsModel getPlayerStats(String playerId);

    /**
     *
     * @param teamId
     * @return
     */
    public ArrayList<PlayerModel> getPlayers(String teamId);

    /**
     *
     * @param playerId
     * @return
     */
    public ArrayList<TeamStatsModel> getEachTeamStats(String playerId);

    /**
     *
     * @param playerId
     * @return
     */
    public ArrayList<TeamModel> getPlayersTeams(String playerId);

    /**
     *
     * @param teamId
     * @param playerId
     */
    public void removeTeam(String teamId, String playerId);

    /**
     *
     * @param teamId
     * @param playerId
     */
    public void acceptRequest(String teamId, String playerId);

    /**
     *
     * @param teamId
     * @param playerId
     */
    public void rejectRequest(String teamId, String playerId);

    /**
     *
     * @param playerId
     * @param teamId
     */
    public void addPlayerStats(String playerId,String teamId);
}
