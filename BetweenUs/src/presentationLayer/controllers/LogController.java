package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.LogManager;
import businessLayer.NpcManager;
import businessLayer.entities.character.Character;
import presentationLayer.views.LogsView;
import presentationLayer.views.customComponents.Log;

import java.util.LinkedList;

/**
 * La classe 'LogController' ens permet gestionar la vista del LogsView.
 *
 * Els metodes reben informació de la vista, i realitzen les modificacions pertinents pero poder gestionar la vista del logs.
 */
public class LogController {
    private NpcManager npcManager;
    private LogsView logv;
    private LogManager logm;

    public LogController(NpcManager npcManager, LogsView logv, String gameName) {
        this.npcManager = npcManager;
        this.logv = logv;
        this.logm = new LogManager(gameName);
    }

    public void updateLogs() {
        for (int i = 0; i < npcManager.getPlayers().size(); i++) {
            if (npcManager.checkLogPosition(npcManager.getPlayers().get(i))) {
                logm.addLog(npcManager.getPlayers().get(i));
            }
        }
    }

    public LinkedList<Log> getLogs() {
        return logm.getLogs();
    }
}
