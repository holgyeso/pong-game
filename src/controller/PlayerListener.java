package controller;

import controller.playController.PlayObserver;
import model.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class PlayerListener extends KeyAdapter implements Runnable {

    private static final int moveUnit = 3;
    List<PlayObserver> observers;
    List<Character> keys;
    private Character pressedKey;

    HashMap<Player, Character[]> playerKeyHashMap;

    public PlayerListener(HashMap<Player, Character[]> playerKeyHashMap) {
        this.playerKeyHashMap = playerKeyHashMap;
        keys = new ArrayList<>();
        observers = new ArrayList<>();
        pressedKey = null;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKey = e.getKeyChar();
        notifyAllObservers();
    }

    @Override
    public void run() {
        if (pressedKey == 'w') {
            playerKeyHashMap.keySet().stream().findFirst().orElse(null).setyCoord(playerKeyHashMap.keySet().stream().findFirst().orElse(null).getyCoord() - moveUnit);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keys.size() > 0) {
            Character curr = (Character) e.getKeyChar();
            keys.remove(curr);
        }
    }

    public void attachObserver(PlayObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        observers.stream().forEach(PlayObserver::updatePlayView);
    }

}
