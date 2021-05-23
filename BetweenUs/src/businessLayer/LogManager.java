package businessLayer;

import businessLayer.entities.game.Game;
import persitanceLayer.LogDAO;
import presentationLayer.views.customComponents.Log;

public class LogManager {
    private String gameName;
    private LogDAO logDAO;
    private GameManager gameManager;

    public LogManager(Log log) {
        gameName = getGameName();
        logDAO.saveLog(log, gameName);
    }

    private String getGameName() {
        return gameManager.getGameName();
    }
}
