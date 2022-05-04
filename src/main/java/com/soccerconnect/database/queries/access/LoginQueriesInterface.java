package com.soccerconnect.database.queries.access;

public interface LoginQueriesInterface {
    /**
     *
     * @param email
     * @return
     */
    public String getPasswordFromEmail(String email);
}
