package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.PlayerManager;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.*;
import presentationLayer.views.MapView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MapController implements ActionListener {

    private MapView mv;
    private MapManager mapManager;
    private NpcManager npcManager;
    private PlayerManager playerManager;
    private String gameName;

    public MapController(MapView mv, MapManager mapManager, PlayerManager playerManager, NpcManager npcManager, String gameName){
        this.mapManager = mapManager;
        this.npcManager = npcManager;
        this.playerManager = playerManager;
        this.mv = mv;
        this.gameName = gameName;
    }

    /*
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
    }*/


    public void actionPerformed(ActionEvent e) {
        GameManager gameManager = new GameManager();

        if (e.getActionCommand().equals("left")) {
            if (playerManager.checkLeft(playerManager.getPlayer().getCell().getMobility())) {
                int[] nextCell = playerManager.getPlayer().getNextCoordinates(1);
                playerManager.getPlayer().setCell(mapManager.getMap().getCellByCoordinates(nextCell));

                //MOURE PLAYER ESQUERRA

            }
        }
        if (e.getActionCommand().equals("up")) { //cuando apretamos el boton
            if (playerManager.checkUp(playerManager.getPlayer().getCell().getMobility())) {
                int[] nextCell = playerManager.getPlayer().getNextCoordinates(2);
                playerManager.getPlayer().setCell(mapManager.getMap().getCellByCoordinates(nextCell));

                //MOURE PLAYER AMUNT

            }
        }
        if (e.getActionCommand().equals("right")) {
            if (playerManager.checkRight(playerManager.getPlayer().getCell().getMobility())) {
                int[] nextCell = playerManager.getPlayer().getNextCoordinates(3);
                playerManager.getPlayer().setCell(mapManager.getMap().getCellByCoordinates(nextCell));

                //MOURE PLAYER DRETA

            }
        }
        if (e.getActionCommand().equals("down")) {
            if (playerManager.checkDown(playerManager.getPlayer().getCell().getMobility())) {
                int[] nextCell = playerManager.getPlayer().getNextCoordinates(4);
                playerManager.getPlayer().setCell(mapManager.getMap().getCellByCoordinates(nextCell));

                //MOURE PLAYER A BAIX

            }
        }
        if (e.getActionCommand().equals("return")) {
            if (JOptionPane.OK_OPTION == mv.confirmSave()) {
                gameManager.saveGame(playerManager.getPlayer(), npcManager.getImpostors(), npcManager.getCrewMembers(), gameName);
            }


            //DECIDIR A QUINA VISTA ANIREM
        }

    }

    public void updateMap(){


    }

    public void impostorsMovement(LinkedList<Impostor> impostors, Time timer, int startInterval, int[] intervals) {
        for (int i = 0; i < impostors.size(); i++) {
            if (startInterval + intervals[i] == timer.getSeconds() && impostors.get(i).movement()) {
                if (npcManager.checkVentilation(impostors.get(i).getCell())) {
                    if (npcManager.flipCoin()) {
                        int nextRoom = npcManager.chooseVentilationRoom(impostors.get(i).getCell());
                        String roomName = npcManager.getImpostors().get(i).getCell().getAdjacencies().get(nextRoom);
                        npcManager.getImpostors().get(i).setCell(mapManager.getMap().getCellByName(roomName));

                        //MOVEM IMPOSTOR A LA VISTA (passar la 'i' i les coordenades)

                        //int posicioImpostor/posicioCrewMember, int[] coordenadesCell, boolean impostor
                    }
                } else {
                    int nextRoom = npcManager.getNextImpostorRoom(impostors.get(i));
                    int[] nextCell = impostors.get(i).getNextCoordinates(nextRoom);
                    impostors.get(i).setCell(mapManager.getMap().getCellByCoordinates(nextCell));

                        //MOVEM IMPOSTOR A LA VISTA

                }

            }
        }
    }

    public void crewMembersMovement(LinkedList<CrewMember> crewMembers, Time timer, int startInterval, int[] intervals) {
        for (int i = 0; i < crewMembers.size(); i++) {
            if (startInterval + intervals[i] == timer.getSeconds() && crewMembers.get(i).movement()) {
                int nextRoom = npcManager.getNextCrewMemberRoom(crewMembers.get(i));
                crewMembers.get(i).setPreviousRoom(nextRoom);
                int[] nextCell = crewMembers.get(i).getNextCoordinates(nextRoom);
                crewMembers.get(i).setCell(mapManager.getMap().getCellByCoordinates(nextCell));

                //MOVEM CREW MEMBER A LA VISTA

            }
        }
    }
}

