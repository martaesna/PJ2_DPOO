package presentationLayer.controllers;

import businessLayer.MapManager;
import businessLayer.PlayerManager;
import businessLayer.entities.maps.*;
import presentationLayer.views.MapView;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class MapController {

    private Map map;
    private Cell cell;
    private Mobility mobility;
    private MapView mv;
    private MapManager mapManager;
    private LinkedList<Character> npcPlayers;
    private Character userPlayer;

    public MapController(Map map, MapView mv){
        mapManager = new MapManager();
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
        PlayerManager playerManager = new PlayerManager();
        if (e.getActionCommand().equals("up")) { //cuando apretamos el boton
            if (playerManager.checkUp(playerManager.getCell().getMobility())) {
                //MOURE PLAYER

            }
        }
        if (e.getActionCommand().equals("down")) {
            if (playerManager.checkDown(playerManager.getCell().getMobility())) {
                //MOURE PLAYER

            }
        }
        if (e.getActionCommand().equals("right")) {
            if (playerManager.checkRight(playerManager.getCell().getMobility())) {
                //MOURE PLAYER

            }
        }
        if (e.getActionCommand().equals("left")) {
            if (playerManager.checkLeft(playerManager.getCell().getMobility())) {
                //MOURE PLAYER

            }
        }

    }
    public void updateMap(){

        //S'ACTUALITZA A TEMPS REAL




    }
}

