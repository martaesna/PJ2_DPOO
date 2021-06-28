package presentationLayer.controllers;

import businessLayer.NpcManager;
import businessLayer.entities.character.Character;
import presentationLayer.views.LogsView;
import presentationLayer.views.customComponents.Log;

import java.util.LinkedList;

public class LogController {
    private NpcManager npcManager;
    private LogsView lv;

    public LogController(NpcManager npcManager, LogsView lv) {
        this.npcManager = npcManager;
        this.lv = lv;
    }

    public void updateLogView() {
        for (int i = 0; i < npcManager.getPlayers().size(); i++) {
            if (npcManager.checkLogPosition(npcManager.getPlayers().get(i))) {

                //Guardar-ho a la vista

            }
        }
    }
}
