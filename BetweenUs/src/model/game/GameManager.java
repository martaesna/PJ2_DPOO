package model.game;

import model.database.GameDAO;
import model.database.SQLGameDAO;

import javax.swing.*;

public class GameManager {
    private final GameDAO gameDAO;

    public GameManager() {gameDAO = new SQLGameDAO();}

    public void createGame(Game game) {
        //POSAR EL DIALOG A LA VISTA

        if (gameDAO.gameExists(game.getGameName())) {
            JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc ja existeix", "Error Create Game", JOptionPane.ERROR_MESSAGE);
        } else {
            gameDAO.createGame(game);
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
