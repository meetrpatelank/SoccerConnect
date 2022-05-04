package com.soccerconnect.database.queries.user;

import com.soccerconnect.models.stats.TeamStatsModel;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.user.TeamModel;

import java.util.ArrayList;

public interface TeamsQueriesInterface {
    /**
     *
     * @param playerId
     * @return
     */
    public ArrayList<TeamModel> getTeams(String playerId);

    /**
     *
     * @param playerId
     * @param teamId
     */
    public void acceptRequest(String playerId, String teamId);

    /**
     *
     * @param playerId
     * @param teamId
     */
    public void rejectRequest(String playerId, String teamId);

    /**
     *
     * @param teamId
     * @return
     */
    public TeamStatsModel getTeamStats(String teamId);

    /**
     *
     * @param teamId
     * @return
     */
    public ArrayList<PlayerModel> getTeamPlayers(String teamId);

    /**
     *
     * @param playerId
     * @param teamId
     */
    public void deletePlayer(String playerId, String teamId);

}
