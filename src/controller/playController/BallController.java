package controller.playController;

import controller.MainController;
import controller.settingsController.SettingsController;
import model.Ball;
import model.Player;
import view.playView.PlayFrame;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallController extends Thread {

    private static final double MOVE_UNIT = 1;
    private static final double MIN_ANGLE = Math.toRadians(20);
    private static final double MAX_ANGLE = Math.toRadians(60);
    private static final double SPEED_UNIT = 0.1;
    private static final double X_MIN_SPEED = 1;

    private double moveXDirection;
    private double moveYDirection;
    private double speed;
    private boolean threadOn = true;

    private final PlayFrame frame;
    private final Ball ball;
    List<PlayObserver> ballObservers;
    List<PlayOverObserver> gameObservers;

    public BallController(PlayController playController, Ball ball) {
        ballObservers = new ArrayList<>();
        gameObservers = new ArrayList<>();
        this.ball = ball;
        this.frame = playController.getFrame();
        setMoveDirections();
        speed = 1;
    }

    public void attachBallObserver(PlayObserver ballObserver) {
        ballObservers.add(ballObserver);
    }

    public void notifyAllBallObservers() {
        ballObservers.forEach(PlayObserver::updatePlayView);
    }

    public void attachGameOverObserver(PlayOverObserver gameOverObserver) {
        gameObservers.add(gameOverObserver);
    }

    public void notifyAllGameObservers(Player winnerPlayer) {
        gameObservers.forEach(observer -> {
            observer.endGame(winnerPlayer);
        });
    }

    @Override
    public void run() {
        while (threadOn) {
            try {
                move();
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopThread() {
        threadOn = false;
    }

    public void move() {
        hit();

        ball.setxCoord(ball.getxCoord() + moveXDirection * MOVE_UNIT * speed);
        ball.setyCoord(ball.getyCoord() + moveYDirection * MOVE_UNIT * speed);

        notifyAllBallObservers();
    }

    private void playSound() {
        String soundFileName = "sounds/paddle.wav";
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(soundFileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void hit() {
        double ballX = ball.getxCoord();
        double ballY = ball.getyCoord();

        if (moveXDirection > 0) { //jobbra
            if (ballX + ball.getSize() >= MainController.getRightPlayer().getxCoord() && ballX < MainController.getRightPlayer().getxCoord()) {

                if (ballY + ball.getSize() / 2 >= MainController.getRightPlayer().getyCoord() && ballY <= MainController.getRightPlayer().getyCoord() + MainController.getRightPlayer().getHeight()) {
                    moveXDirection *= -1;
                    speed += SPEED_UNIT;
                    playSound();
                }
            }

            if (ballX > frame.getWidth() - frame.frameInsetRight()) {
                playerScored(MainController.getLeftPlayer());
            }

        } else if (moveXDirection < 0) { //balra
            if (ballX <= MainController.getLeftPlayer().getxCoord() + MainController.getLeftPlayer().getWidth() && ballX > MainController.getLeftPlayer().getxCoord()) { //inline with player
                if (ballY + ball.getSize() / 2 >= MainController.getLeftPlayer().getyCoord() && ballY <= MainController.getLeftPlayer().getyCoord() + MainController.getLeftPlayer().getHeight()) {
                    moveXDirection *= -1;
                    speed += SPEED_UNIT;
                    playSound();
                }
            }
            if (ballX + ball.getSize() < 0) {
                playerScored(MainController.getRightPlayer());
            }
        }

        if (moveYDirection < 0) { //felfele
            if (ballY <= 0) {
                moveYDirection *= -1;
                speed += SPEED_UNIT;
            }
        } else if (moveYDirection > 0) {//lefele
            if (ballY + ball.getSize() >= frame.getHeightWithoutInsets()) {
                moveYDirection *= -1;
                speed += SPEED_UNIT;
            }
        }
    }

    public void playerScored(Player player) {
        player.setScore(player.getScore() + 1);
        if (player.getScore() == SettingsController.getWinnerScore()) {
            notifyAllGameObservers(player);
        } else {
            resetBall();
        }
    }

    public void resetBall() {
        ball.setyCoord(new Random().nextDouble(frame.getHeightWithoutInsets() - ball.getSize()));
        ball.setxCoord((double) frame.getWidth() / 2 - frame.frameInsetLeft() - ball.getSize() / 2);
        notifyAllBallObservers();
        speed = 1;
        setMoveDirections();
    }

    public void setMoveDirections() {
        Random rand = new Random();
        moveXDirection = rand.nextDouble(0.5) + X_MIN_SPEED;
        double angle = Math.tan(Math.toRadians(rand.nextDouble(MAX_ANGLE)) + MIN_ANGLE);
        moveYDirection = angle * moveXDirection;

        if (rand.nextBoolean()) moveXDirection = -moveXDirection;
        if (rand.nextBoolean()) moveYDirection = -moveYDirection;
    }

}
