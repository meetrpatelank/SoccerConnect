package com.soccerconnect.database.queries.game;

import com.soccerconnect.database.queries.user.AdminQueriesInterface;
import com.soccerconnect.models.game.GameModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface GamesQueriesInterface {
    /**
     *
     * @param category
     * @param team1
     * @param team2
     * @param ground
     * @param date
     * @param time
     */
    public void organize(String category, String team1, String team2, String ground, String date, String time);

    /**
     *
     * @param aq
     * @return
     */
    public ArrayList<GameModel> getGames(AdminQueriesInterface aq);

    /**
     *
     * @param scoreGameId
     * @param aq
     * @return
     */
    public GameModel getGameDetails(String scoreGameId, AdminQueriesInterface aq);

    /**
     *
     * @param gameId
     * @param team1Goals
     * @param team2Goals
     * @param mom
     */
    void addGameInfo(String gameId, String team1Goals, String team2Goals, String mom);

    /**
     *
     * @param scoreGameId
     * @param aq
     * @return
     */
    public HashMap<String, String> getGameScore(String scoreGameId, AdminQueriesInterface aq);

}
