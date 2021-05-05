package model;

import model.database.GameDAO;
import model.database.SQLGameDAO;

import javax.swing.*;

public class GameManager {
    private final GameDAO gameDAO;

    public GameManager(GameDAO gameDAO) {this.gameDAO = new SQLGameDAO();}

    public void createGame(Game game) {
        if (gameDAO.gameExists(game.getGameName())) {
            JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc ja existeix", "Error Create Game", JOptionPane.ERROR_MESSAGE);
        } else {
            gameDAO.createGame(game);
        }
    }

    public void deleteGame(String nameGame, String username) {
        if (gameDAO.gameExists(nameGame)) {
            if (gameDAO.checkCreator(nameGame,username)) {
                gameDAO.deleteGame(nameGame);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: No ets el creador d'aquest joc", "Error Create Game", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc no existeix", "Error Create Game", JOptionPane.ERROR_MESSAGE);
        }
    }

}
