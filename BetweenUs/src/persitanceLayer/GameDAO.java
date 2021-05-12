package persitanceLayer;

import businessLayer.entities.game.Game;

public interface GameDAO {
    void createGame(Game game);
    Game selectGame(String gameName);
    boolean gameExists(String gameName);
    boolean recreatedGameExists(String gameName);
    void deleteGame(String gameName);
    boolean checkCreator(String gameName, String username);
    void resumeGame(String gameName);
}
