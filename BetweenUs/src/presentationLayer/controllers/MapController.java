package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.PlayerManager;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.maps.*;
import presentationLayer.views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MapController extends Thread implements ActionListener {
    private MapView mv;
    private final MapManager mapManager;
    private final PlayerManager playerManager;
    private final NpcManager npcManager;
    private final String gameName;
    private LogsView logsView;
    private boolean isRunning;
    private LinkedList<Character> players;
    private boolean revealMap;
    private String userName;


    public MapController(MapView mv, MapManager mapManager, PlayerManager playerManager, LinkedList<Character> players, String gameName, String userName, NpcManager npcManager){
        this.mapManager = mapManager;
        this.players = players;
        this.playerManager = playerManager;
        this.mv = mv;
        this.gameName = gameName;
        revealMap = false;
        this.userName = userName;
        this.npcManager = npcManager;
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

    /**
     * Segons el boto que apretem fa una funcionalitat
     * left Mou l'usuari a la esquerra, sempre comprovant que es pugui
     * down Mou l'usuari cap abaix, sempre comprovant que es pugui
     * right Mou l'usuari a la dreta, sempre comprovant que es pugui
     * up Mou l'usuari cap a dalt, sempre comprovant que es pugui
     * Return ens retorna al menu anterior, primer preguntant si volem guardar la partida
     * Config ens porta al menu de settings, primer preguntant si volem guardar la partida
     * Revel ens mostra o amaga el mapa
     * Solution Comprova si les prediccions son correctes
     * Logs mostra el menu de Logs
     * @param e accionador d'events
     */
    public void actionPerformed(ActionEvent e) {
        GameManager gameManager = new GameManager();
        String command = e.getActionCommand();

        switch (command) {
            case "left":
                if (playerManager.checkLeft()) {
                    int[] nextCell = playerManager.nextCell(0);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer(), revealMap);
                }
                break;
            case "down":
                if (playerManager.checkDown()) {
                    int[] nextCell = playerManager.nextCell(3);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer(), revealMap);
                }
                break;
            case "right":
                if (playerManager.checkRight()) {
                    int[] nextCell = playerManager.nextCell(2);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer(), revealMap);
                }
                break;
            case "up":
                if (playerManager.checkUp()) {
                    int[] nextCell = playerManager.nextCell(1);
                    playerManager.moveUserPlayer(mapManager.nextPlayerCell(nextCell));

                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer(), revealMap);
                }
                break;
            case "Return":
                npcManager.interruptThreads();
                stopMapThread();

                if (JOptionPane.OK_OPTION == mv.confirmSave()) {
                    gameManager.saveGame(gameManager.selectGame(gameName), players);
                    mv.setVisible(false);
                    PlayView pv = new PlayView();
                    PlayViewController pvc = new PlayViewController(pv,userName);
                    pv.mainController(pvc);
                } else {
                    mv.setVisible(false);
                    PlayView pv = new PlayView();
                    PlayViewController pvc = new PlayViewController(pv,userName);
                    pv.mainController(pvc);
                }
                break;
            case "Config":
                npcManager.interruptThreads();
                stopMapThread();

                if (JOptionPane.OK_OPTION == mv.confirmSave()) {
                    mv.printNoImplementationMsg();
                    //gameManager.saveGame(playerManager.getPlayer(), players, gameName);
                }
                mv.setVisible(false);
                SettingView sv = new SettingView();
                SettingViewController svc = new SettingViewController(sv, userName);
                sv.mainController(svc);
                break;

            case "Reveal":
                if (revealMap) {
                    revealMap = false;
                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer(), revealMap);
                } else {
                    revealMap = true;
                    mv.updateView(mapManager.getMap(), players, playerManager.getPlayer(), revealMap);
                }
                break;
            case "Solution":
                if (mv.checkSolution()) {
                    npcManager.interruptThreads();
                    stopMapThread();
                    System.out.println("QUE ESTA PASSANT");
                    mv.userWins();
                    mv.setVisible(false);
                    PlayView playView = new PlayView();
                    PlayViewController playViewController = new PlayViewController(playView, userName);
                    playView.mainController(playViewController);
                }
                break;
            case "logs":
                logsView = new LogsView();
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

    /**
     * Inicia el Thread del mapa
     */
    public void startMapThread() {
        isRunning = true;
        this.start();
    }

    /**
     * Finalitza el Thread del mapa
     */
    public void stopMapThread() {
        isRunning = false;
        this.interrupt();
    }

    /**
     * Thread del mapa que va actualitzant el mapa a temps real
     */
    @Override
    public void run() {
        while(isRunning) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                if (npcManager.checkImpostorsWinCondition()) {
                    npcManager.interruptThreads();
                    stopMapThread();
                    mv.impostorsWinMsg();
                    PlayView pv = new PlayView();
                    PlayViewController pvc = new PlayViewController(pv, userName);
                    pv.mainController(pvc);
                    mv.setVisible(false);
                }
                if (npcManager.eliminateUserPlayer(playerManager.getPlayer())) {
                    npcManager.interruptThreads();
                    stopMapThread();
                    mv.userDefeatMsg();
                    PlayView pv = new PlayView();
                    PlayViewController pvc = new PlayViewController(pv, userName);
                    pv.mainController(pvc);
                    mv.setVisible(false);
                }
                //npcManager.eliminateCrewMember(mapManager, playerManager.getPlayer());
                mv.updateView(mapManager.getMap(), players, playerManager.getPlayer(), revealMap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

