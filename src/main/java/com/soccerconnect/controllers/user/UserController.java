package com.soccerconnect.controllers.user;

import com.soccerconnect.controllers.MasterController;
import com.soccerconnect.database.queries.user.*;

public abstract class UserController extends MasterController {

    // Parent controller for Teams and Players

    PlayerQueriesInterface pq = new PlayerQueries(conn);
    TeamsQueriesInterface tq = new TeamsQueries(conn);
    RequestsQueriesInterface rqq = new RequestsQueries(conn);
}
