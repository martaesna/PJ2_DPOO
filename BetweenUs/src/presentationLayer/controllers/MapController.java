package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.PlayerManager;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.maps.*;
import presentationLayer.views.MapView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MapController extends Thread implements ActionListener {
    private MapView mv;
    private final MapManager mapManager;
    private final PlayerManager playerManager;
    private final String gameName;
    private boolean isRunning;
    private LinkedList<Character> players;

    public MapController(MapView mv, MapManager mapManager, PlayerManager playerManager, LinkedList<Character> players, String gameName){
        this.mapManager = mapManager;
        this.players = players;
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
        String command = e.getActionCommand();

        switch (command) {
            case "left":
                if (playerManager.checkLeft()) {
                    int[] nextCell = playerManager.nextCell(0);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    System.out.println(playerManager.getPlayer().getCell().getX());
                    System.out.println(playerManager.getPlayer().getCell().getY());
                    System.out.println("Left");

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer());
                }
                break;
            case "down":
                if (playerManager.checkDown()) {
                    int[] nextCell = playerManager.nextCell(1);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    System.out.println(playerManager.getPlayer().getCell().getX());
                    System.out.println(playerManager.getPlayer().getCell().getY());
                    System.out.println("down");

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer());
                }
                break;
            case "right":
                if (playerManager.checkRight()) {
                    int[] nextCell = playerManager.nextCell(2);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    System.out.println(playerManager.getPlayer().getCell().getX());
                    System.out.println(playerManager.getPlayer().getCell().getY());
                    System.out.println("Right");

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer());
                }
                break;
            case "up":
                if (playerManager.checkUp()) {
                    int[] nextCell = playerManager.nextCell(3);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    System.out.println(playerManager.getPlayer().getCell().getX());
                    System.out.println(playerManager.getPlayer().getCell().getY());
                    System.out.println("up");

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer());
                }
                break;
            case "return":
                if (JOptionPane.OK_OPTION == mv.confirmSave()) {
                    //gameManager.saveGame(playerManager.getPlayer(), npcManager.getImpostors(), npcManager.getCrewMembers(), gameName);
                }
                //DECIDIR A QUINA VISTA ANIREM
                break;
            default:
                String[] elements = command.split("_");
                switch (elements[0]) {
                    case "r":
                        mv.moveRight(elements[1]);
                        mv.updateObjectiveTracking(this);
                        break;
                    case "l":
                        mv.moveLeft(elements[1]);
                        mv.updateObjectiveTracking(this);
                        break;
                }
        }
    }

    public void startMapThread() {
        isRunning = true;
        this.start();
    }

    public void stopMapThread() {
        isRunning = false;
        this.interrupt();
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                TimeUnit.SECONDS.sleep(1);
                mv.updateView(mapManager.getMap(), players, playerManager.getPlayer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

