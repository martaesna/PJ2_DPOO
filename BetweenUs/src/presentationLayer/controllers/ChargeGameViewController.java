package presentationLayer.controllers;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.PlayerManager;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.game.Game;
import businessLayer.GameManager;
import businessLayer.entities.maps.Map;
import presentationLayer.views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;

/**
 * La classe 'ChargeGameViewController' ens permet gestionar la vista del ChargeGameView.
 *
 * Els metodes reben informació de la vista, i realitzen les modificacions pertinents per poder carregar les partides guardades.
 */
public class ChargeGameViewController implements ActionListener {
    private final ChargeGameView cgv;
    private String userName;

    public ChargeGameViewController(ChargeGameView cgv, String userName) {
        this.cgv = cgv;
        this.userName = userName;
    }

    /**
     * Segons el botó que apretem fa una funcionalitat
     * Charge carrega una partida guardada
     * Return ens retorna al menú anterior
     * Config ens porta ha el menú de settings
     * @param e escoltador d'accions
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Charge")) { //cuando apretamos el boton
           /* Game game = JsonGame.searchJsonGame(cgv.getChargeName());
            GameManager gameManager = new GameManager();
            if (gameManager.checkGame(cgv.getChargeName())) {
                Player userPlayer = new Player(game.getPlayerColor());

                Map map = MapManager.llegeixMapa(game.getMap());

                LinkedList<Character> players = game.getGamePlayers();

                PlayerManager playerManager = new PlayerManager(userPlayer);
                MapManager mapManager = new MapManager(map);

                for (Character character: players) {
                    gameManager.startPlayers(character);
                }

                NpcManager npcManager = new NpcManager(players);
                for (Character character: players) {
                    if (character instanceof Impostor) {
                        character.setNpcManager(npcManager);
                    }
                }

                MapView mv = new MapView(map, players, userPlayer);

                MapController mapController = new MapController(mv, mapManager, playerManager, players, cgv.getName(), userName, npcManager);
                mv.mainController(mapController);
                mapController.startMapThread();
                cgv.setVisible(false);
*/
            cgv.printNoImplementationMsg();
        }
        if (e.getActionCommand().equals("Return")) { //cuando apretamos el boton
            cgv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv, userName);
            pv.mainController(pvc);
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            cgv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv, userName);
            sv.mainController(svc);
        }
    }
/*
    public LinkedList<CrewMember> setCellsCrewMembers(LinkedList<CrewMember> crewMembers, Map map) {
        for (CrewMember crewMember: crewMembers) {
            int[] coordinates = new int[2];
            coordinates[0] = crewMember.getxCoordinate();
            coordinates[1] = crewMember.getyCoordinate();
            crewMember.setCell(map.getCellByCoordinates(coordinates));
        }
        return crewMembers;
    }

    public LinkedList<Impostor> setCellsImpostors(LinkedList<Impostor> impostors, Map map) {
        for (Impostor impostor: impostors) {
            int[] coordinates = new int[2];
            coordinates[0] = impostor.getxCoordinate();
            coordinates[1] = impostor.getyCoordinate();
            impostor.setCell(map.getCellByCoordinates(coordinates));
        }
        return impostors;
    }

    public Player setCellUserPlayer(Player userPlayer, Map map) {
        int[] coordinates = new int[2];
        coordinates[0] = userPlayer.getxCoordinate();
        coordinates[1] = userPlayer.getyCoordinate();
        userPlayer.setCell(map.getCellByCoordinates(coordinates));
        return userPlayer;
    }*/
}


