package controller.playController;

import controller.MainController;
import controller.settingsController.SettingsController;
import model.Ball;
import model.Player;
import view.playView.PlayFrame;
import view.playView.PlayView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayController implements PlayObserver, PlayOverObserver {

    private static final int PLAYER_HEIGHT = 60;
    private static final int PLAYER_WIDTH = 10;
    private static final int BALL_SIZE = 15;

    private final PlayerController playerController;
    List<BallController> ballControllers;

    private PlayFrame frame;

    public PlayFrame getFrame() {
        return frame;
    }

    public PlayController() {
        this.ballControllers = new ArrayList<>();
        this.frame = MainController.getFrame();
        PlayView playView = new PlayView();
        playView.setPreferredSize(new Dimension(frame.getWidth() - frame.frameInsetRight() - frame.frameInsetLeft(), frame.getHeight() - frame.frameInsetTop() - frame.frameInsetBottom()));

        frame.setContentPane(playView);
        Insets insets = frame.getInsets();

        MainController.setPlayerLeft(new Player(5, frame.getHeight() / 2 - PLAYER_HEIGHT / 2, PLAYER_HEIGHT, PLAYER_WIDTH, SettingsController.getPlayerLeftName()));
        MainController.setPlayerRight(new Player(frame.getWidthWithoutInsets() - PLAYER_WIDTH - 5, frame.getHeight() / 2 - PLAYER_HEIGHT / 2, PLAYER_HEIGHT, PLAYER_WIDTH, SettingsController.getPlayerRightName()));

        playerController = new PlayerController(this);
        frame.addKeyListener(playerController);

        Thread playerThread = new Thread(playerController);
        playerThread.start();
        playerController.attach(this);

        playView.addPlayer(MainController.getLeftPlayer());
        playView.addPlayer(MainController.getRightPlayer());

        Random rand = new Random();

        for (int i = 1; i <= SettingsController.getBallNumber(); i++) {
            double xCoord = (double) frame.getWidth() / 2 - frame.frameInsetLeft() - BALL_SIZE / 2;
            double yCoord = rand.nextDouble(frame.getHeightWithoutInsets() - BALL_SIZE);
            Ball ball = new Ball(xCoord, yCoord, BALL_SIZE);
            BallController bc = new BallController(this, ball);
            bc.start();
            bc.attachBallObserver(this);
            bc.attachGameOverObserver(this);
            ballControllers.add(bc);
            playView.addBall(ball);
        }

        frame.validate();
    }

    @Override
    public void updatePlayView() {
        frame.repaint();
    }


    @Override
    public void endGame(Player winnerPlayer) {
        MainController.getInstance().changeToGameOverController();
        stopGame();
    }

    public void stopGame() {
        ballControllers.forEach(BallController::stopThread);
        playerController.stop();
    }
}
