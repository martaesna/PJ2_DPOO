package businessLayer;

import persitanceLayer.LogDAO;
import presentationLayer.views.customComponents.Log;

public class LogManager {
    private LogDAO logDAO;
    private GameManager gameManager;

    public LogManager(Log log) {
        String gameName = getGameName();
        logDAO.saveLog(log, gameName);
    }

    private String getGameName() {
        return gameManager.getGameName();
    }
}
