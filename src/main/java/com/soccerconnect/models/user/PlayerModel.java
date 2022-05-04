package com.soccerconnect.models.user;

import com.soccerconnect.Constants;

public class PlayerModel extends UserModel {
    public PlayerModel(String userId, String name, String email , String mobile, String password, String category) {
        super(userId, Constants.playerRole, email, name, mobile, password, category);
    }

    public PlayerModel(String userId, String name) {
        super(userId, Constants.playerRole, null, name, null, null, null);
    }

    public PlayerModel(String userId, String name,String email,String mobile,String category) {
        super(userId, Constants.playerRole, email, name, mobile, null, category);
    }
}
