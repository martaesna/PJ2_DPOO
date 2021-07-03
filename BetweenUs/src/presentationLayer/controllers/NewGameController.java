package presentationLayer.controllers;

import businessLayer.*;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.game.Game;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Map;
import presentationLayer.views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe 'NewGameController' ens permet gestionar la vista del ChargeGameView.
 *
 * Els metodes ens reben informació de la vista, i realitzen les modificacions pertinents per a la creacio de una nova
 * partida.
 */


public class NewGameController implements ActionListener {
    private NewGameView ngv;
    private ArrayList<String> colors;
    private String userName;

    public NewGameController(){}

    public NewGameController(NewGameView ngv, String userName) {
        this.ngv = ngv;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));
        this.userName = userName;
    }

    /**
     * Segons el botó que apretem fa una funcionalitat
     * SelectFile ens obra un JFileChooser per seleccionar el mapa de la partida
     * ColorLeft/ColorRight mou la selecció per el color del nostre personatge
     * ImpostorLeft/ImporstorRight mou la selecció del número d'impostors
     * PlayersLeft/PlayersRight mou la selecció del número de personatges
     * Play Inicia el joc
     * Return ens retorna al menú anterior
     * Config ens porta ha el menú de settings
     * @param e accionador d'events
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SelectFile")) { //cuando apretamos el boton
            String path = getMapsPath();
            JFileChooser jfc = new JFileChooser(path);

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                String name = selectedFile.getName();
                ngv.setMapName(name);
            }
        }

        if (e.getActionCommand().equals("ColorLeft")) { //cuando apretamos el boton
            int position = getColorsPosition(ngv.getColor());
            if (position > 0) {
                ngv.setColor(colors.get(position-1));
            }
        }

        if (e.getActionCommand().equals("ColorRight")) { //cuando apretamos el boton
            int position = getColorsPosition(ngv.getColor());
            if (position < 11) {
                ngv.setColor(colors.get(position+1));
            }
        }

        if (e.getActionCommand().equals("ImpostorsLeft")) { //cuando apretamos el boton
            int impostors = ngv.getImpostors();
            if (impostors > 1) {
                ngv.setImpostors(impostors-1);
            }
        }

        if (e.getActionCommand().equals("ImpostorsRight")) { //cuando apretamos el boton
            int impostors = ngv.getImpostors();
            int maxImpostors = checkImpostors(ngv.getPlayers());
            if (impostors < 3 && maxImpostors > impostors) {
                ngv.setImpostors(impostors+1);
            }
        }

        if (e.getActionCommand().equals("PlayersLeft")) { //cuando apretamos el boton
            int players = ngv.getPlayers();
            int impostors = ngv.getImpostors();
            if (checkPlayers(impostors) < players + 1 && players > 3) {
                ngv.setPlayers(players-1);
            }
        }

        if (e.getActionCommand().equals("PlayersRight")) { //cuando apretamos el boton
            int players = ngv.getPlayers();
            if (players <= 9) {
                ngv.setPlayers(players+1);
            }
        }

        if (e.getActionCommand().equals("Play")) {
            if (ngv.getName().isEmpty()) {
                ngv.printEmptyNameError();
            } else {
                String mapName;
                if (ngv.getMapName().equals("Select File")) {
                    mapName = "gravity.json";
                } else {
                    mapName = ngv.getMapName();
                }
                GameManager gameManager = new GameManager();
                Game game = new Game(ngv.getName(), ngv.getPlayers(), ngv.getImpostors(), ngv.getColor(), mapName, "creator");
                if(gameManager.checkGame(game.getGameName())) {
                    ngv.printNameError();
                } else {
                    gameManager.createGame(ngv.getName(),game, userName);

                    int starterColor = 0;

                    Map map = MapManager.llegeixMapa(mapName);
                    MapManager mapManager = new MapManager(map);

                    Player userPlayer = new Player(ngv.getColor());
                    if (userPlayer.getColor().equals("RED")) {
                        starterColor++;
                    }
                    LinkedList<CrewMember> crewMembers = gameManager.getCrewMembers(ngv.getPlayers() - ngv.getImpostors() - 1, ngv.getColor(), starterColor, colors, mapManager);
                    starterColor = getImpostorsStarterColor(gameManager.getUserColorPosition(ngv.getColor(), colors), crewMembers.size(), starterColor);
                    LinkedList<Impostor> impostors = gameManager.getImpostors(ngv.getImpostors(), ngv.getColor(), starterColor + crewMembers.size(), colors, mapManager);

                    LinkedList<Character> players = new LinkedList<>();
                    players.addAll(crewMembers);
                    players.addAll(impostors);
                    Collections.shuffle(players);

                    Cell initialCell = gameManager.getCoffeShopCell(map.getCells());
                    userPlayer.setCell(initialCell);

                    gameManager.setInitialCell(userPlayer, players, map.getCells());

                    for (Character character: players) {
                        gameManager.startPlayers(character);
                    }

                    PlayerManager playerManager = new PlayerManager(userPlayer);
                    mapManager.setPlayerManager(playerManager);

                    NpcManager npcManager = new NpcManager(players);
                    for (Character character: players) {
                        if (character instanceof Impostor) {
                            character.setNpcManager(npcManager);
                        }
                    }

                    MapView mv = new MapView(map, players, userPlayer);

                    MapController mapController = new MapController(mv, mapManager, playerManager, players, ngv.getName(), userName, npcManager);
                    mv.mainController(mapController);
                    mapController.startMapThread();
                    ngv.setVisible(false);
                }
            }
        }

        if (e.getActionCommand().equals("Config")) {
            ngv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv, userName);
            pv.mainController(pvc);
        }

        if (e.getActionCommand().equals("Return")) {
            ngv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv, userName);
            pv.mainController(pvc);
        }
    }

    /**
     * Retorna el Path del mapa
     * @return string amb el path
     */

    public String getMapsPath(){
        File f = new File("");
        String path = f.getAbsolutePath();
        return path + "/BetweenUs/src/mapsFiles";
    }

    /**
     * Ens diu la posició del color
     * @param actualColor el color del que volem saber la posició
     * @return retorna la posició del color
     */
    public int getColorsPosition(String actualColor){
        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i).equals(actualColor)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Comprova quants impostors pot haver-hi
     * @param players nombre de jugadors
     * @return quants impostors pot haver
     */
    public int checkImpostors(int players){
        return Math.floorDiv(players+1, 3);
    }

    /**
     * Comprova si hi ha el número correcte d' impostors
     * @param impostors número d' impostors
     * @return retorna el número mínim de jugadors necesari amb aquest número d'impostors
     */
    public int checkPlayers(int impostors) { return impostors*3; }

    /**
     * Comprova que el color del usuari sigui diferent a la resta
     * @param userPosition Posició del color del usuari
     * @param crewMembers Posicio del color del CrewMember
     * @param starterColor Posicio del color final
     * @return Retorna la posicio del color
     */
    public int getImpostorsStarterColor(int userPosition, int crewMembers, int starterColor) {
        if (userPosition <= crewMembers) {
            return starterColor+1;
        } else {
            return starterColor;
        }
    }

    /**
     * Creem els colors que la clase Color no conté
     * @param color El nom del color que volem crear
     * @return Les components del color
     */
    public int[] getColorComponents(String color) {
        int[] components = new int[3];
        switch (color) {
            case "PURPLE":
                components[0] = 102;
                components[2] = 153;
                return components;

            case "BROWN":
                components[0] = 102;
                components[1] = 51;
                return components;

            case "CYAN":
                components[1] = 255;
                components[2] = 255;
                return components;
            default:
                components[0] = 50;
                components[1] = 205;
                components[2] = 50;
                return components;
        }
    }
}
