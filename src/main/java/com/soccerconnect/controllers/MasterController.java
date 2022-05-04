package com.soccerconnect.controllers;

import com.soccerconnect.Constants;
import com.soccerconnect.database.DBConnectionApp;
import com.soccerconnect.database.queries.access.RolesQueries;
import com.soccerconnect.database.queries.access.RolesQueriesInterface;
import com.soccerconnect.utils.ConfigReader;
import com.soccerconnect.utils.ConfigReaderInterface;
import org.springframework.stereotype.Controller;

import java.sql.Connection;


@Controller
public class MasterController {

    String configFileName = "application.properties";
    ConfigReaderInterface configReader = new ConfigReader(configFileName);
    // Usage of singleton pattern to get DB app
    DBConnectionApp db = DBConnectionApp.getDbApp(configReader);
    protected Connection conn = db.getConnection();

    protected static String currentUserId;
    // Dependency inversion by passing object of Connection interface
    protected RolesQueriesInterface rq = new RolesQueries(conn);


    public String welcome() {
        // Method to load welcome page based on the role
        if (currentUserId != null) {
            int RoleId = rq.getRoleFromUserId(currentUserId);
            if (RoleId == Constants.admin) {
                return "welcomeAdmin";
            } else if (RoleId == Constants.player) {
                return "welcomePlayer";
            } else if (RoleId == Constants.team) {
                return "welcomeTeam";
            } else {
                return "login";
            }
        }
        else{
            return "login";
        }
    }
}
