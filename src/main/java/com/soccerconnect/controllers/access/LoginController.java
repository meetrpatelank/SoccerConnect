package com.soccerconnect.controllers.access;

import com.soccerconnect.Constants;
import com.soccerconnect.controllers.MasterController;
import com.soccerconnect.database.queries.access.LoginQueries;
import com.soccerconnect.database.queries.access.LoginQueriesInterface;
import com.soccerconnect.models.user.UserModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController extends MasterController {

    // Controller to handle user login. Login can be admin, user and team, and welcome page is loaded based on the role.

    LoginQueriesInterface lq = new LoginQueries(conn);

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String loginForm() {
        // Form for login
        return "login";
    }

    @RequestMapping(value = "/loginsubmission")
    public String loginSubmit(@ModelAttribute UserModel user, HttpSession session) {
        // Method to authenticate user and allow access
        if (session != null) {
            String passwordDb = lq.getPasswordFromEmail(user.getEmail());
            if (BCrypt.checkpw(user.getPassword(), passwordDb)) {
                MasterController.currentUserId = rq.getUserId(user.getEmail());
                session.setAttribute(Constants.currentUser, currentUserId);
                return welcome();
            } else return "login";
        } else return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.currentUser);
        session.invalidate();
        return "login";
    }
}
