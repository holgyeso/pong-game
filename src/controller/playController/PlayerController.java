package controller.playController;

import controller.MainController;
import model.Player;
import view.playView.PlayFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayerController extends KeyAdapter implements Runnable {

    private static final Character PLAYER_LEFT_UP = 'w';
    private static final Character PLAYER_LEFT_DOWN = 's';
    private static final Character PLAYER_RIGHT_UP = 'o';
    private static final Character PLAYER_RIGHT_DOWN = 'k';
    private static final int MOVE_UNIT = 8;

    private boolean threadOn = true;

    private boolean playerLeftUp;
    private boolean playerLeftDown;
    private boolean playerRightUp;
    private boolean playerRightDown;

    private List<PlayObserver> playerObservers;

    private PlayFrame frame;

    public PlayerController(PlayController playController) {

        playerObservers = new ArrayList<>();
        this.frame = playController.getFrame();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Character pressedKey = e.getKeyChar();
        if (pressedKey == Character.toLowerCase(PLAYER_LEFT_UP) || pressedKey == Character.toUpperCase(PLAYER_LEFT_UP))
            playerLeftUp = true;
        else if (pressedKey == Character.toLowerCase(PLAYER_LEFT_DOWN) || pressedKey == Character.toUpperCase(PLAYER_LEFT_DOWN))
            playerLeftDown = true;
        else if (pressedKey == Character.toLowerCase(PLAYER_RIGHT_UP) || pressedKey == Character.toUpperCase(PLAYER_RIGHT_UP))
            playerRightUp = true;
        else if (pressedKey == Character.toLowerCase(PLAYER_RIGHT_DOWN) || pressedKey == Character.toUpperCase(PLAYER_RIGHT_DOWN))
            playerRightDown = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Character pressedKey = e.getKeyChar();
        if (pressedKey == Character.toLowerCase(PLAYER_LEFT_UP) || pressedKey == Character.toUpperCase(PLAYER_LEFT_UP))
            playerLeftUp = false;
        else if (pressedKey == Character.toLowerCase(PLAYER_LEFT_DOWN) || pressedKey == Character.toUpperCase(PLAYER_LEFT_DOWN))
            playerLeftDown = false;
        else if (pressedKey == Character.toLowerCase(PLAYER_RIGHT_UP) || pressedKey == Character.toUpperCase(PLAYER_RIGHT_UP))
            playerRightUp = false;
        else if (pressedKey == Character.toLowerCase(PLAYER_RIGHT_DOWN) || pressedKey == Character.toUpperCase(PLAYER_RIGHT_DOWN))
            playerRightDown = false;
    }

    @Override
    public void run() {
        while (threadOn) {
            try {
                if (playerLeftUp) {
                    moveUp(MainController.getLeftPlayer());
                    notifyAllObservers();
                }
                if (playerRightUp) {
                    moveUp(MainController.getRightPlayer());
                    notifyAllObservers();
                }
                if (playerLeftDown) {
                    moveDown(MainController.getLeftPlayer());
                    notifyAllObservers();
                }
                if (playerRightDown) {
                    moveDown(MainController.getRightPlayer());
                    notifyAllObservers();
                }
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stop() {
        threadOn = false;
    }

    public void moveUp(Player player) {
        if (player.getyCoord() - MOVE_UNIT > 0)
            player.setyCoord(player.getyCoord() - MOVE_UNIT);
    }

    public void moveDown(Player player) {
        if (player.getyCoord() + MOVE_UNIT < frame.getHeightWithoutInsets() - player.getHeight())
            player.setyCoord(player.getyCoord() + MOVE_UNIT);
    }

    public void attach(PlayObserver playerObserver) {
        playerObservers.add(playerObserver);
    }

    public void notifyAllObservers() {
        playerObservers.stream().forEach(PlayObserver::updatePlayView);
    }

}
