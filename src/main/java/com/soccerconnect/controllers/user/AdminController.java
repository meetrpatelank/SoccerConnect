package com.soccerconnect.controllers.user;

import com.soccerconnect.controllers.MasterController;
import com.soccerconnect.database.queries.game.GamesQueriesInterface;
import com.soccerconnect.database.queries.ground.GroundsQueriesInterface;
import com.soccerconnect.database.queries.user.AdminQueries;
import com.soccerconnect.database.queries.ground.GroundQueries;
import com.soccerconnect.database.queries.game.GamesQueries;
import com.soccerconnect.database.queries.user.AdminQueriesInterface;
import com.soccerconnect.database.queries.user.TeamsQueries;
import com.soccerconnect.database.queries.user.TeamsQueriesInterface;
import com.soccerconnect.models.game.GameModel;
import com.soccerconnect.models.ground.GroundModel;
import com.soccerconnect.models.stats.PlayerStatsModel;
import com.soccerconnect.models.stats.StatsModel;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.user.TeamModel;
import com.soccerconnect.models.stats.TeamStatsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
public class AdminController extends MasterController {

    AdminQueriesInterface aq = new AdminQueries(conn);
    GroundsQueriesInterface gq = new GroundQueries(conn);
    GamesQueriesInterface gaq = new GamesQueries(conn);
    TeamsQueriesInterface tq = new TeamsQueries(conn);


    @GetMapping(value = "/viewAllPlayers")
    public String viewPlayers(Model model) {
        // Method to view all the players of the application
        ArrayList<PlayerModel> players = aq.getAllPlayers();
        model.addAttribute("players", players);
        return "viewAllPlayers";
    }

    @RequestMapping(value = "/adminDeletePlayer")
    public String deletePlayer(@RequestParam(value = "player") String playerId) {
        // Method to delete a player
        aq.deleteUser(playerId);
        return welcome();
    }

    @GetMapping(value = "/viewAllTeams")
    public String viewTeams(Model model) {
        // Method to view all teams of the application
        ArrayList<TeamModel> teams = aq.getAllTeams();
        model.addAttribute("teams", teams);
        return "viewAllTeams";
    }

    @RequestMapping(value = "/adminDeleteTeam")
    public String deleteTeam(@RequestParam(value = "team") String teamId) {
        // Method to delete a team
        aq.deleteUser(teamId);
        return welcome();
    }

    @GetMapping(value = "/addGround")
    public String groundForm() {
        return "addGround";
    }

    @RequestMapping(value = "/groundSubmission")
    public String addGround(@ModelAttribute GroundModel ground) {
        // Method to add a ground
        gq.groundQuery(ground.getGroundName(), ground.getAddress(),
                ground.getPostalCode(), ground.getContact(), ground.getEmail());
        return "welcomeAdmin";
    }

    @GetMapping(value = "/viewAllGrounds")
    public String viewGrounds(Model model) {
        // Method to view all the grounds
        ArrayList<GroundModel> grounds = gq.getAllGrounds();
        model.addAttribute("grounds", grounds);
        return "viewAllGrounds";
    }

    @RequestMapping(value = "/adminDeleteGround")
    public String deleteGround(@RequestParam(value = "ground") String groundId) {
        // Method to delete a ground
        gq.deleteGround(groundId);
        return welcome();
    }

    @GetMapping(value = "/organizeGame")
    public String organizeGame(Model model) {
        ArrayList<TeamModel> teams = aq.getAllTeams();
        ArrayList<GroundModel> grounds = gq.getAllGrounds();
        model.addAttribute("teams", teams);
        model.addAttribute("grounds", grounds);
        return "organizeGame";
    }

    @RequestMapping(value = "/organize")
    public String organize(@ModelAttribute GameModel game) {
        // Method to organize a game
        gaq.organize(game.getCategory(), game.getTeam1Id(), game.getTeam2Id(),
                game.getGroundId(), game.getDate(), game.getTime());
        return "welcomeAdmin";
    }

    @GetMapping(value = "/viewGames")
    public String viewGames(Model model) {
        // Method to view all games
        ArrayList<GameModel> games = gaq.getGames(aq);
        model.addAttribute("games", games);
        return "viewGames";
    }

    @RequestMapping(value = "/scoreGame")
    public String scoreGame(Model model,
                            @RequestParam(value = "view", defaultValue = "") String viewGameId,
                            @RequestParam(value = "score", defaultValue = "") String scoreGameId,
                            @RequestParam(value = "delete", defaultValue = "") String deleteGameId) {
        // Method to view, score or delete a game based on selected option
        if (!viewGameId.isEmpty()) {
            // Usage of dependency inversion by passing admin query interface
            HashMap<String, String> game = gaq.getGameScore(viewGameId, aq);
            model.addAttribute("game", game);
            return "viewGameInfo";
        }
        else if (deleteGameId.isEmpty()) {
            GameModel game = gaq.getGameDetails(scoreGameId, aq);
            ArrayList<PlayerModel> team1Players = tq.getTeamPlayers(game.getTeam1Id());
            ArrayList<PlayerModel> team2Players = tq.getTeamPlayers(game.getTeam2Id());

            ArrayList<PlayerStatsModel> team1PlayerStats = new ArrayList<>();
            for (PlayerModel player : team1Players) {
                team1PlayerStats.add(new PlayerStatsModel(player.getUserId(), player.getName()));
            }
            ArrayList<PlayerStatsModel> team2PlayerStats = new ArrayList<>();
            for (PlayerModel player : team2Players) {
                team2PlayerStats.add(new PlayerStatsModel(player.getUserId(), player.getName()));
            }
            StatsModel teamStats = new StatsModel(team1PlayerStats, team2PlayerStats);
            teamStats.setTeam1Id(game.getTeam1Id());
            teamStats.setTeam2Id(game.getTeam2Id());

            model.addAttribute("game", game);
            model.addAttribute("teamStats", teamStats);
            return "scoreGame";
        } else {
            aq.deleteGame(deleteGameId);
            return welcome();
        }
    }

    @RequestMapping(value = "/score")
    public String manageStats(@ModelAttribute StatsModel teamStats, Model model, @RequestParam(value = "gameId") String gameId) {
        int team1Goal = 0;
        int team2Goal = 0;
        String errorMsg = "";
        String validationString = "";
        String validationScoreString = "";
        List<Integer> totalGoalScoredTeam1 = new ArrayList<Integer>();
        List<Integer> totalGoalScoredTeam2 = new ArrayList<Integer>();
        validationString = validationMOM(teamStats, model);
        if (Objects.equals(validationString, "1")) {

            for (PlayerStatsModel playerStat : teamStats.getTeam1PlayersStats()) {

                totalGoalScoredTeam1.add(Integer.parseInt(playerStat.getGoals()));
            }
            for (PlayerStatsModel playerStat : teamStats.getTeam2PlayersStats()) {
                totalGoalScoredTeam2.add(Integer.parseInt(playerStat.getGoals()));
            }
        } else {
            errorMsg = "Duplicate Man of the match";
            model.addAttribute("errorMsg", errorMsg);
            return "errorScoreGame";
        }
        for (int i = 0; i < totalGoalScoredTeam1.size(); i++) {
            team1Goal = team1Goal + totalGoalScoredTeam1.get(i);
        }
        for (int i = 0; i < totalGoalScoredTeam2.size(); i++) {
            team2Goal = team2Goal + totalGoalScoredTeam2.get(i);
        }
        validationScoreString = validationScore(teamStats, team1Goal, team2Goal);
        if (validationScoreString == "errorScoreGame") {
            errorMsg = "Error in Score";
            model.addAttribute("errorMsg", errorMsg);
            return "errorScoreGame";
        } else {
            processTeamStats(teamStats);
            processTeamPlayerStats(teamStats.team1Id, teamStats.team1PlayersStats);
            processTeamPlayerStats(teamStats.team2Id, teamStats.team2PlayersStats);
            updateGameInfo(teamStats, gameId);

            ArrayList<Integer> teamGoal = new ArrayList<>();
            teamGoal.add(Integer.parseInt(teamStats.getTeam1Goals()));
            teamGoal.add(Integer.parseInt(teamStats.getTeam2Goals()));
            model.addAttribute("teamGoal", teamGoal);
            return "teamScoreBoard";
        }
    }

    private void updateGameInfo(StatsModel teamStats, String gameId) {
        // Adding game level info into the DB
        String mom = null;
        for (PlayerStatsModel playerStat : teamStats.getTeam1PlayersStats()) {
            if (playerStat.getMom().equals("1")) {
                mom = playerStat.getPlayerId();
            }
        }
        for (PlayerStatsModel playerStat : teamStats.getTeam2PlayersStats()) {
            if (playerStat.getMom().equals("1")) {
                mom = playerStat.getPlayerId();
            }
        }
        gaq.addGameInfo(gameId, teamStats.getTeam1Goals(), teamStats.getTeam2Goals(), mom);
    }

    public String validationScore(@ModelAttribute StatsModel teamStats, Integer team1Goal, Integer team2Goal) {
        if (Integer.parseInt(teamStats.getTeam1Goals()) >= team1Goal && Integer.parseInt(teamStats.getTeam2Goals()) >= team2Goal) {

            return "True";
        } else {
            return "errorScoreGame";
        }

    }

    public String validationMOM(@ModelAttribute StatsModel teamStats, Model model) {
        String momFlag = "0";
        String errorMsg = "";
        for (PlayerStatsModel playerStat : teamStats.getTeam1PlayersStats()) {
            if (playerStat.getMom().equals("1") && !momFlag.equals("1")) {
                momFlag = "1";
            } else if (playerStat.getMom().equals("1") && momFlag.equals("1")) {
                errorMsg = "Duplicate Man of the match";
                model.addAttribute("errorMsg", errorMsg);
                return "errorScoreGame";
            } else {
                continue;
            }
        }
        for (PlayerStatsModel playerStat : teamStats.getTeam2PlayersStats()) {

            if (playerStat.getMom().equals("1") && !momFlag.equals("1")) {
                momFlag = "1";
            } else if (playerStat.getMom().equals("1") && momFlag.equals("1")) {
                errorMsg = "Duplicate Man of the match";
                model.addAttribute("errorMsg", errorMsg);
                return "errorScoreGame";
            } else {
                continue;
            }
        }

        return momFlag;
    }

    private void processTeamPlayerStats(String teamId, ArrayList<PlayerStatsModel> teamPlayerStats) {
        // Method to compute team stats and add to DB
        for (PlayerStatsModel playerStat : teamPlayerStats) {
            PlayerStatsModel existingPlayerStat = aq.getPlayerStatsByTeam(playerStat.getPlayerId(), teamId);
            existingPlayerStat.setNom(String.valueOf(Integer.parseInt(existingPlayerStat.getNom()) + 1));
            existingPlayerStat.setGoals(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getGoals()) + Integer.parseInt(playerStat.getGoals())));
            existingPlayerStat.setAsst(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getAsst()) + Integer.parseInt(playerStat.getAsst())));
            existingPlayerStat.setGoalsSaved(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getGoalsSaved()) + Integer.parseInt(playerStat.getGoalsSaved())));
            existingPlayerStat.setYellowCards(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getYellowCards()) + Integer.parseInt(playerStat.getYellowCards())));
            existingPlayerStat.setRedCards(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getRedCards()) + Integer.parseInt(playerStat.getRedCards())));
            existingPlayerStat.setMom(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getMom()) + Integer.parseInt(playerStat.getMom())));
            aq.updatePlayerStats(existingPlayerStat, teamId);
        }
    }

    private void processTeamStats(StatsModel teamStats) {
        // Method to compute player stats and add to DB
        TeamStatsModel team1Stats = tq.getTeamStats(teamStats.getTeam1Id());
        TeamStatsModel team2Stats = tq.getTeamStats(teamStats.getTeam2Id());

        int team1goals = Integer.parseInt(teamStats.getTeam1Goals());
        int team2goals = Integer.parseInt(teamStats.getTeam2Goals());

        team1Stats.setNom(String.valueOf(Integer.parseInt(team1Stats.getNom()) + 1));
        team2Stats.setNom(String.valueOf(Integer.parseInt(team2Stats.getNom()) + 1));

        team1Stats.setGoals(String.valueOf(Integer.parseInt(team1Stats.getGoals()) + team1goals));
        team2Stats.setGoals(String.valueOf(Integer.parseInt(team2Stats.getGoals()) + team2goals));

        if (team1goals > team2goals) {
            team1Stats.setWins(String.valueOf(Integer.parseInt(team1Stats.getWins()) + 1));
            team2Stats.setLosses(String.valueOf(Integer.parseInt(team2Stats.getLosses()) + 1));
        } else if (team2goals > team1goals) {
            team2Stats.setWins(String.valueOf(Integer.parseInt(team2Stats.getWins()) + 1));
            team1Stats.setLosses(String.valueOf(Integer.parseInt(team1Stats.getLosses()) + 1));
        } else {
            team1Stats.setDraws(String.valueOf(Integer.parseInt(team1Stats.getDraws()) + 1));
            team2Stats.setDraws(String.valueOf(Integer.parseInt(team2Stats.getDraws()) + 1));
        }
        aq.updateTeamStats(team1Stats);
        aq.updateTeamStats(team2Stats);
    }

    @GetMapping(value = "/teamRanking")
    public String teamRanking(Model model) {
        ArrayList<TeamStatsModel> rank = aq.getTeamRanking();
        rank.sort(Comparator.comparing(TeamStatsModel::getGoals).reversed());
        model.addAttribute("rank", rank);
        return "teamRanking";
    }

    @GetMapping(value = "/errorScoreGame")
    public String errorScoreGame() {
        return "welcomeAdmin";
    }

    @GetMapping(value = "/returnHome")
    public String returnMethod() {
        return "welcomeAdmin";
    }

}
