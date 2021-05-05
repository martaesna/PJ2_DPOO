package model.database;

import model.Game;
import model.User;

public interface GameDAO {
    void createGame(Game game);
    boolean gameExists(String gameName);
    void deleteGame(String gameName);
    boolean checkCreator(String gameName, String username);
    void resumeGame(String gameName);
}
