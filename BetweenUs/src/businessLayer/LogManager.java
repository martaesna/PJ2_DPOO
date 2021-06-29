package businessLayer;

import businessLayer.entities.character.Character;
import persitanceLayer.LogDAO;
import presentationLayer.views.customComponents.Log;

import java.util.LinkedList;

/**
 * La classe 'LogManager' serveix per obtenir i gestionar dades dels logs
 *
 * El mètode implementat ens retorna el nom de la partida
 */
public class LogManager {
    // Attributes
    private LogDAO logDAO;
    private String gameName;
    private LinkedList<Log> logs;

    // Parametrized constructor
    public LogManager(String gameName) {
        this.gameName = gameName;
        logs = new LinkedList<>();
    }
/*
    private String getGameName() {
        return gameManager.getGameName();
    }*/

    public void addLog(Character character) {
        Log log = new Log(character.getColor(), character.getCell().getRoomName(), character.getTotalTime().getSeconds());
        //logDAO.saveLog(log, gameName);
        logs.add(log);
    }

    public LinkedList<Log> getLogs() {
        return logs;
    }
}
