package controller.settingsController;

import controller.MainController;
import controller.leaderBoardController.LeaderBoardController;
import view.settingsView.SetBallNumberView;
import view.settingsView.SetPaddleColorView;
import view.settingsView.SetPlayersNameView;
import view.settingsView.SetWinnerScoreView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SettingsController implements ActionListener {

    private static SettingsController instance;
    private static String playerLeftName = "Player 1";
    private static String playerRightName = "Player 2";
    private static int ballNumber = 2;
    private static int winnerScore = 5;
    private static File backgroundFile;
    private static Color paddleColor = new Color(251,209,4);

    private static MainController mainController;
    private SetPlayersNameView playersNameView;
    private SetBallNumberView ballNumberView;
    private SetWinnerScoreView winnerScoreView;
    private SetPaddleColorView paddleColorView;

    private SettingsController() {

    }

    public static SettingsController getInstance() {
        if (instance == null) {
            instance = new SettingsController();
        }
        return instance;
    }

    public static void setMainController(MainController mainController) {
        SettingsController.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Set players' name":
                mainController.changeToIntroController();
                playersNameView = new SetPlayersNameView();
                break;
            case "Set ball number":
                mainController.changeToIntroController();
                ballNumberView = new SetBallNumberView();
                break;
            case "Set winner's score":
                mainController.changeToIntroController();
                winnerScoreView = new SetWinnerScoreView();
                break;
            case "Change background image":
                mainController.changeToIntroController();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(null);
                try {
                    backgroundFile = fileChooser.getSelectedFile();

                } catch (NullPointerException exc) {
                    System.err.println("! You did not select any file. Try again.");
                }
                break;
            case "Set paddle color":
                mainController.changeToIntroController();
                paddleColorView = new SetPaddleColorView();
                break;
            case "By points":
                mainController.changeToIntroController();
                new LeaderBoardController(false);
                break;
            case "By games won":
                mainController.changeToIntroController();
                new LeaderBoardController(true);
                break;

        }

        if (playersNameView != null && e.getSource() == playersNameView.getSaveButton()) {
            playerLeftName = playersNameView.getNameLeftInput().getText();
            playerRightName = playersNameView.getNameRightInput().getText();
            playersNameView.dispose();
            playersNameView = null;
        }

        if (ballNumberView != null && e.getSource() == ballNumberView.getSaveButton()) {
            ballNumber = (int) ballNumberView.getBallNumberSpinner().getValue();
            ballNumberView.dispose();
            ballNumberView = null;
        }

        if (winnerScoreView != null && e.getSource() == winnerScoreView.getSaveButton()) {
            winnerScore = (int) winnerScoreView.getWinnerScoreSpinner().getValue();
            winnerScoreView.dispose();
            winnerScoreView = null;
        }

        if (paddleColorView != null && e.getSource() == paddleColorView.getSaveButton()) {
            paddleColor = paddleColorView.getColorChooser().getColor();
            paddleColorView.dispose();
            paddleColorView = null;
        }

    }

    public static String getPlayerLeftName(){
        return playerLeftName;
    }

    public static String getPlayerRightName() {
        return playerRightName;
    }

    public static int getBallNumber() {
        return ballNumber;
    }

    public static int getWinnerScore() {
        return winnerScore;
    }

    public static File getBackgroundImageFile() {
        return backgroundFile;
    }

    public static Color getPaddleColor() {
        return paddleColor;
    }
}
