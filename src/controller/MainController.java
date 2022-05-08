package controller;

import controller.gameOverController.GameOverController;
import controller.introController.IntroController;
import controller.settingsController.SettingsController;
import model.Player;
import view.playView.PlayFrame;
import controller.playController.PlayController;

public class MainController {

    private static MainController instance;

    private static final PlayFrame frame = new PlayFrame();
    private static Player playerLeft = null;
    private static Player playerRight = null;


    private IntroController introController;
    private PlayController playController;
    private GameOverController gameOverController;

    private MainController() {
    }

    public void begin() {
        SettingsController.getInstance();
        SettingsController.setMainController(this);
        changeToIntroController();
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public static PlayFrame getFrame() {
        return frame;
    }

    public static Player getLeftPlayer() {
        return playerLeft;
    }

    public static Player getRightPlayer() {
        return playerRight;
    }

    public static void setPlayerLeft(Player player) {
        MainController.playerLeft = player;
    }

    public static void setPlayerRight(Player player) {
        MainController.playerRight = player;
    }

    public void changeToPlayController() {
        introController = null;
        playController = new PlayController();
        gameOverController = null;
    }

    public void changeToGameOverController() {
        gameOverController = new GameOverController();
        playController.stopGame();
        if (playController != null) {
            playController.stopGame();
        }
        introController = null;

    }

    public void changeToIntroController() {
        introController = new IntroController(this);
        if (playController != null) {
            playController.stopGame();
        }
        playController = null;
        gameOverController = null;
    }

    public static void main(String[] args) {
        MainController.getInstance().begin();
    }
}
