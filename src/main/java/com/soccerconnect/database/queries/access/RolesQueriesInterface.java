package com.soccerconnect.database.queries.access;

public interface RolesQueriesInterface {
    /**
     *
     * @param email
     * @return
     */
    public int getRoleFromEmail(String email);

    /**
     *
     * @param userId
     * @return
     */
    public int getRoleFromUserId(String userId);

    /**
     *
     * @param email
     * @return
     */
    public String getUserId(String email);
}
