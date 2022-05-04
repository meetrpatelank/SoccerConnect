package com.soccerconnect.database.queries.user;

import java.util.HashMap;

public interface RequestsQueriesInterface {
    /**
     *
     * @param fromId
     * @return
     */
    public HashMap<String, String> getRequests(String fromId);

    /**
     *
     * @param fromId
     * @param toId
     */
    public void addRequest(String fromId, String toId);

    /**
     *
     * @param toId
     * @return
     */
    public HashMap<String, String> getReceivedRequests(String toId);
}
