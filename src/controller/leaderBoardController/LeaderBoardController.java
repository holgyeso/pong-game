package controller.leaderBoardController;

import view.leaderBoardView.LeaderBoardView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class LeaderBoardController {


    public LeaderBoardController(boolean byGamesWon) {

        HashMap<String, Integer> leaderboard = new HashMap<>();
        HashMap<String, Integer> gamesWonLeaderboard = new HashMap<>();

        try {
            BufferedReader gamesFile = new BufferedReader(new FileReader((System.getProperty("user.dir") + "/src/allGames.txt")));

            gamesFile.lines().map(gameLine -> gameLine.split("\\t", 3)).forEach(player -> {

                if (byGamesWon) {
                    if (player[2].equals("true")) {
                        if (gamesWonLeaderboard.get(player[0]) != null)
                            gamesWonLeaderboard.put(player[0], gamesWonLeaderboard.get(player[0]) + 1);
                        else
                            gamesWonLeaderboard.put(player[0], 1);
                    }

                } else {
                    if (leaderboard.get(player[0]) != null)
                        leaderboard.put(player[0], leaderboard.get(player[0]) + Integer.parseInt(player[1]));
                    else {
                        leaderboard.put(player[0], Integer.parseInt(player[1]));
                    }
                }

            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<HashMap<String, Integer>> sortedLeaderboard = null;

        if (byGamesWon) {

            sortedLeaderboard = gamesWonLeaderboard.entrySet().stream().sorted(((o1, o2) -> {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else if (o1.getValue() == o2.getValue()) {

                    return (o1.getKey().compareTo(o2.getKey()));

                } else {
                    return -1;
                }
            })).map(set -> {
                var curr = new HashMap<String, Integer>();
                curr.put(set.getKey(), set.getValue());
                return curr;
            }).toList();

        } else {
            sortedLeaderboard = leaderboard.entrySet().stream().sorted((o1, o2) -> {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else if (o1.getValue() == o2.getValue()) {

                    return (o1.getKey().compareTo(o2.getKey()));

                } else {
                    return -1;
                }
            }).map(set -> {
                var curr = new HashMap<String, Integer>();
                curr.put(set.getKey(), set.getValue());
                return curr;
            }).toList();
        }

        new LeaderBoardView(sortedLeaderboard.stream().limit(10).toList());
    }

}
