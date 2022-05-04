package com.soccerconnect.controllers.user;

import com.soccerconnect.controllers.MasterController;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.stats.TeamStatsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class TeamsController extends UserController {

    @GetMapping(value = "/viewPlayers")
    public String getPlayers(Model model) {
        // Method to get all players of the team
        ArrayList<PlayerModel> teamPlayers = tq.getTeamPlayers(MasterController.currentUserId);
        model.addAttribute("teamPlayers", teamPlayers);
        return "viewTeamPlayers";
    }

    @RequestMapping(value = "/deletePlayer")
    public String deletePlayer(@RequestParam(value = "player") String playerId) {
        // Method to delete a player
        tq.deletePlayer(playerId, MasterController.currentUserId);
        return welcome();
    }

    @GetMapping(value = "/players")
    public String viewPlayers(Model model) {
        // Method to view all available players registered to the application
        ArrayList<PlayerModel> players = pq.getPlayers(MasterController.currentUserId);
        model.addAttribute("players", players);
        return "viewPlayers";
    }

    @RequestMapping(value = "/sendPlayerRequest")
    public String getPlayerRequests(@RequestParam(value = "player") String playerID) {
        // Method to add a request to a player
        rqq.addRequest(MasterController.currentUserId, playerID);
        return welcome();
    }

    @GetMapping(value = "/teamStats")
    public String getTeamStats(Model model) {
        // Method to view the team stats
        TeamStatsModel teamStats = tq.getTeamStats(MasterController.currentUserId);
        model.addAttribute("teamStats", teamStats);
        return "viewTeamStats";
    }

    @GetMapping(value = "/pendingPlayerRequests")
    public String viewPendingPlayerRequests(Model model) {
        // Method to view pending requests to the team
        HashMap<String, String> requestsSent = rqq.getRequests(MasterController.currentUserId);
        HashMap<String, String> requestsReceived = rqq.getReceivedRequests(MasterController.currentUserId);
        model.addAttribute("requestsSent", requestsSent);
        model.addAttribute("requestsReceived", requestsReceived);
        return "viewPendingPlayerReqs";
    }

    @RequestMapping(value = "/acceptPlayerRequest")
    public String acceptTeamRequests(@RequestParam(value = "acceptReqId") String playerId) {
        // Method for team to accept players request
        tq.acceptRequest(playerId, MasterController.currentUserId);
        pq.addPlayerStats(playerId, MasterController.currentUserId);
        return welcome();
    }

    @RequestMapping(value = "/rejectPlayerRequest")
    public String rejectTeamRequests(@RequestParam(value = "rejectReqId") String playerId) {
        // Method to reject a player request
        tq.rejectRequest(playerId, MasterController.currentUserId);
        return welcome();
    }


}
