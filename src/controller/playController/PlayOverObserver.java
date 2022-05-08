package controller.playController;

import model.Player;

public interface PlayOverObserver {

    void endGame(Player winner);

}
