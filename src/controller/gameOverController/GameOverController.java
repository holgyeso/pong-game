package controller.gameOverController;

import controller.MainController;
import view.playView.GameOverView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class GameOverController implements ActionListener {
    private File games;

    public GameOverController() {
        games = null;

        try {
            games = new File((System.getProperty("user.dir") + "/src/allGames.txt"));
        } catch (NullPointerException e) {
            System.err.println("allGames.txt cannot be opened");
            e.printStackTrace();
        }

        boolean rightWinner = false;
        boolean leftWinner = false;

        GameOverView gameOverView;

        if (MainController.getRightPlayer().getScore() > MainController.getLeftPlayer().getScore()) {
            rightWinner = true;
            gameOverView = new GameOverView(MainController.getRightPlayer().getName(), this);
        } else {
            leftWinner = true;
            gameOverView = new GameOverView(MainController.getLeftPlayer().getName(), this);

        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(games, true));
            writer.print(MainController.getLeftPlayer().getName() + "\t" + MainController.getLeftPlayer().getScore() + "\t" + leftWinner);
            writer.println();
            writer.print(MainController.getRightPlayer().getName() + "\t" + MainController.getRightPlayer().getScore() + "\t" + rightWinner);
            writer.println();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameOverView.setPreferredSize(new Dimension(MainController.getFrame().getWidthWithoutInsets(), MainController.getFrame().getHeightWithoutInsets()));

        MainController.getFrame().setContentPane(gameOverView);
        MainController.getFrame().validate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainController.getInstance().changeToPlayController();
    }
}
