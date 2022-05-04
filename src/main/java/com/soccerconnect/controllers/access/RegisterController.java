package com.soccerconnect.controllers.access;

import com.soccerconnect.Constants;
import com.soccerconnect.controllers.MasterController;
import com.soccerconnect.database.queries.access.RegistrationQueries;
import com.soccerconnect.database.queries.access.RegistrationQueriesInterface;
import com.soccerconnect.models.user.UserModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController extends MasterController {

    // Controller to handle regisration to the application

    RegistrationQueriesInterface req = new RegistrationQueries(conn);

    @GetMapping(value = "/register")
    public String registerForm() {
        return "register";
    }

    @RequestMapping(value = "/registersubmission")
    public String registerSubmit(@ModelAttribute UserModel user) {
        // Method to encrypt user password and store it along with other details into the DB
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        req.registrationQuery(user.getRole(), user.getEmail(), user.getName(),
                user.getMobile(), password, user.getCategory());
        if (Integer.parseInt(user.getRole()) == Constants.team) {
            String teamId = rq.getUserId(user.getEmail());
            req.addTeamStats(teamId);
        }
        return "login";
    }
}
