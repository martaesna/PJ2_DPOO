package businessLayer;

import persitanceLayer.LogDAO;
import presentationLayer.views.customComponents.Log;

/**
 * La classe 'LogManager' serveix per obtenir i gestionar dades dels logs
 *
 * El mètode implementat ens retorna el nom de la partida
 */
public class LogManager {
    // Attributes
    private LogDAO logDAO;
    private GameManager gameManager;

    // Parametrized constructor
    public LogManager(Log log) {
        String gameName = getGameName();
        logDAO.saveLog(log, gameName);
    }

    private String getGameName() {
        return gameManager.getGameName();
    }
}
