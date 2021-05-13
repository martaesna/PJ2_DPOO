package businessLayer;

import persitanceLayer.GameDAO;
import persitanceLayer.SQLGameDAO;
import businessLayer.entities.game.Game;

import javax.swing.*;

public class GameManager {
    private final GameDAO gameDAO;

    public GameManager() {gameDAO = new SQLGameDAO();}

    public void createGame(String gameName) {
        //POSAR EL DIALOG A LA VISTA

        if (gameDAO.gameExists(gameName)) {
            JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc ja existeix", "Error Create Game", JOptionPane.ERROR_MESSAGE);
        } else {
            Game newGame = gameDAO.selectGame(gameName);
            gameDAO.createGame(newGame);
        }
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
