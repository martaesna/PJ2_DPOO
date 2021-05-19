package presentationLayer.controllers;

import businessLayer.*;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.game.Game;
import businessLayer.entities.json.Data;
import businessLayer.entities.maps.Map;
import businessLayer.entities.user.User;
import presentationLayer.views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NewGameController implements ActionListener {
    private NewGameView ngv;
    private ArrayList<String> colors;

    public NewGameController(NewGameView ngv) {
        this.ngv = ngv;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));
    }

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
            if (players < 9) {
                ngv.setPlayers(players+1);
            }
        }

        if (e.getActionCommand().equals("Play")) {
            if (ngv.getName().isEmpty()) {
                ngv.printEmptyNameError();
            } else {
                String mapName;
                if (ngv.getMapName() == "Select File") {
                    mapName = "gravity.json";
                } else {
                    mapName = ngv.getMapName();
                }
                GameManager gameManager = new GameManager();
                Game game = new Game(ngv.getName(), ngv.getPlayers(), ngv.getImpostors(), ngv.getColor(), mapName, "creator");
                if(gameManager.checkGame(game.getGameName())) {
                    ngv.printNameError();
                } else {
                    gameManager.createGame(ngv.getName(),game);

                    int starterColor = 0;

                    Character userPlayer = new Character(ngv.getColor());
                    LinkedList<CrewMember> crewMembers = gameManager.getCrewMembers(ngv.getPlayers() - ngv.getImpostors(), ngv.getColor(), starterColor, colors);
                    starterColor = getImpostorsStarterColor(gameManager.getUserColorPosition(ngv.getColor(), colors), crewMembers.size(), starterColor);
                    LinkedList<Impostor> impostors = gameManager.getImpostors(ngv.getImpostors(), ngv.getColor(), starterColor + crewMembers.size(), colors);

                    Map map = MapManager.llegeixMapa(mapName);

                    gameManager.setInitialCell(userPlayer, crewMembers, impostors, map.getCells());

                    PlayerManager playerManager = new PlayerManager(userPlayer);
                    NpcManager npcManager = new NpcManager(crewMembers, impostors);
                    MapManager mapManager = new MapManager(map);

                    MapView mv = new MapView(map, crewMembers, impostors, userPlayer);

                    MapController mapController = new MapController(mv, mapManager, playerManager, npcManager, ngv.getName());
                    mv.mainController(mapController);
                }
            }
        }

        if (e.getActionCommand().equals("Config")) {
            ngv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);
        }

        if (e.getActionCommand().equals("Return")) {
            ngv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);
        }
    }

    public String getMapsPath(){
        File f = new File("");
        String path = f.getAbsolutePath();
        return path + "\\BetweenUs\\src\\mapsFiles";
    }

    public int getColorsPosition(String actualColor){
        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i) == actualColor) {
                return i;
            }
        }
        return 0;
    }

    public int checkImpostors(int players){
        return Math.floorDiv(players+1, 3);
    }

    public int checkPlayers(int impostors) { return impostors*3; }

    public int getImpostorsStarterColor(int userPosition, int crewMembers, int starterColor) {
        if (userPosition <= crewMembers) {
            return starterColor+1;
        } else {
            return starterColor;
        }
    }
}
