package com.soccerconnect.database.queries.access;

public interface RegistrationQueriesInterface {
    /**
     *
     * @param role String
     * @param email
     * @param name
     * @param mobile
     * @param password
     * @param category
     */
    public void registrationQuery(String role,
                                  String email,
                                  String name,
                                  String mobile,
                                  String password,
                                  String category);

    /**
     *
     * @param teamId
     */
    public void addTeamStats(String teamId);
}
