package com.soccerconnect.database.queries.ground;

import com.soccerconnect.models.ground.GroundModel;

import java.util.ArrayList;

public interface GroundsQueriesInterface {
    /**
     *
     * @param groundName
     * @param address
     * @param postalCode
     * @param phone
     * @param email
     */
    public void groundQuery(String groundName, String address, String postalCode, String phone, String email);

    /**
     *
     * @return
     */
    public ArrayList<GroundModel> getAllGrounds();

    /**
     *
     * @param groundId
     */
    public void deleteGround(String groundId);
}
