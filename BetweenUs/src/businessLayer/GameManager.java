package businessLayer;

import persitanceLayer.GameDAO;
import persitanceLayer.SQLGameDAO;
import businessLayer.entities.game.Game;
import businessLayer.entities.game.Time;

import javax.swing.*;

public class GameManager {
    private final GameDAO gameDAO;
    private final Time timer;

    public GameManager() {
        gameDAO = new SQLGameDAO();
        timer = new Time();
    }

    public Time getTimer() {
        return timer;
    }

    public void createGame(String gameName, Game game) {
        gameDAO.createGame(game);
    }

    public void createConfiguredGame(String gameName) {
        if (gameDAO.gameExists(gameName+"(Copy)")) {
            JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc ja existeix", "Error Create Game", JOptionPane.ERROR_MESSAGE);
        } else {
            Game newGame = gameDAO.selectGame(gameName);
            newGame.setGameName(newGame.getGameName()+"(Copy)");
            gameDAO.createGame(newGame);
        }
    }

    public void deleteGame(String gameName) {
        gameDAO.deleteGame(gameName);
    }

    public boolean checkGame(String gameName) {
        if (gameDAO.gameExists(gameName)) {
            return true;
        }
        return false;
    }

    public boolean checkRecreateGame(String gameName) {
        if (gameDAO.recreatedGameExists(gameName)) {
            return true;
        }
        return false;
    }

    public Game chargeGame(String gameName) {
        return gameDAO.selectGame(gameName);
    }
}
