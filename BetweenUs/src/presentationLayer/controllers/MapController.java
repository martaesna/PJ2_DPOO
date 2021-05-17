package presentationLayer.controllers;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.PlayerManager;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.*;
import presentationLayer.views.MapView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class MapController {

    private Map map;
    private MapView mv;
    private MapManager mapManager;
    private NpcManager npcManager;
    private PlayerManager playerManager;

    public MapController(Map map, MapView mv){
        mapManager = new MapManager();
        npcManager = new NpcManager();
        this.map = map;
        this.mv = mv;
    }

    public int getWidth(){
        return map.getWidth();
    }

    public int getHeight(){
        return map.getHeight();
    }

    public int trobaCela (int x, int y){
        for (int i = 0; i < map.getCells().size(); i++){
            if(map.getCells().get(i).getX() == x || map.getCells().get(i).getY() == y){
                return i;
            }
        }
        return -1;
    }

    public String getType(int i){
        return map.getCells().get(i).getType();
    }

    public String getColor(int i){
        return map.getCells().get(i).getColor();
    }


    public void actionPerformed(ActionEvent e) {
        Character character = new Character(new Color(230,0,250),new Cell());
        playerManager = new PlayerManager(character);

        if (e.getActionCommand().equals("left")) {
            if (playerManager.checkLeft(playerManager.getPlayer().getCell().getMobility())) {
                playerManager.getPlayer().move(1);

                //MOURE PLAYER

            }
        }
        if (e.getActionCommand().equals("up")) { //cuando apretamos el boton
            if (playerManager.checkUp(playerManager.getPlayer().getCell().getMobility())) {
                playerManager.getPlayer().move(2);

                //MOURE PLAYER

            }
        }
        if (e.getActionCommand().equals("right")) {
            if (playerManager.checkRight(playerManager.getPlayer().getCell().getMobility())) {
                playerManager.getPlayer().move(3);

                //MOURE PLAYER

            }
        }
        if (e.getActionCommand().equals("down")) {
            if (playerManager.checkDown(playerManager.getPlayer().getCell().getMobility())) {
                playerManager.getPlayer().move(4);

                //MOURE PLAYER

            }
        }

    }

    public void updateMap(){


    }

    //POSAR TAMBÉ EL COLOR INICIAL, TENIR LES LLISTES PLENES AMB LES DADES DE COLOR I CELL (CAFF)

    public void setInitialCell(Character player, LinkedList<CrewMember> crewMembers, LinkedList<Impostor> impostors, LinkedList<Cell> cells) {
        mapManager.initialCell(player, crewMembers, impostors, cells);
    }

    public void impostorsMovement(LinkedList<Impostor> impostors, Time timer, int startInterval, int[] intervals) {
        for (int i = 0; i < impostors.size(); i++) {
            if (startInterval + intervals[i] == timer.getSeconds() && impostors.get(i).movement()) {
                int nextRoom = npcManager.getNextImpostorRoom(impostors.get(i));
                impostors.get(i).move(nextRoom);

                //MOVEM IMPOSTOR A LA VISTA
            }
        }
    }

    public void crewMembersMovement(LinkedList<CrewMember> crewMembers, Time timer, int startInterval, int[] intervals) {
        for (int i = 0; i < crewMembers.size(); i++) {
            if (startInterval + intervals[i] == timer.getSeconds() && crewMembers.get(i).movement()) {
                int nextRoom = npcManager.getNextCrewMemberRoom(crewMembers.get(i));
                crewMembers.get(i).setPreviousRoom(nextRoom);
                crewMembers.get(i).move(nextRoom);

                //MOVEM CREW MEMBER A LA VISTA
            }
        }
    }
}

