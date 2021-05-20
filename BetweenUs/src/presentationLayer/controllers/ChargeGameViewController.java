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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ChargeGameViewController implements ActionListener {
    private ChargeGameView cgv;
    private String gameName;

    public ChargeGameViewController(ChargeGameView cgv) {
        this.cgv = cgv;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Charge")) { //cuando apretamos el boton
            System.out.println("Aquí es carrega el joc");
            GameManager gameManager = new GameManager();
            if (gameManager.checkGame(cgv.getChargeName())) {

                Game game = gameManager.chargeGame(cgv.getChargeName());
                Player userPlayer = gameManager.getUserPlayer(cgv.getChargeName());
                LinkedList<Impostor> impostors = gameManager.getImpostorsGame(cgv.getChargeName());
                LinkedList<CrewMember> crewMembers = gameManager.getCrewMembersGame(cgv.getChargeName());

                Map map = MapManager.llegeixMapa(game.getMap());
                userPlayer = setCellUserPlayer(userPlayer, map);
                impostors = setCellsImpostors(impostors, map);
                crewMembers = setCellsCrewMembers(crewMembers, map);

                PlayerManager playerManager = new PlayerManager(userPlayer);
                NpcManager npcManager = new NpcManager(crewMembers, impostors);
                MapManager mapManager = new MapManager(map);

                MapView mv = new MapView(map, crewMembers, impostors, userPlayer);

                MapController mapController = new MapController(mv, mapManager, playerManager, npcManager, cgv.getChargeName());
                mv.mainController(mapController);

            } else {
                cgv.printErrorNoExistance();
            }
        }
        if (e.getActionCommand().equals("Return")) { //cuando apretamos el boton
            cgv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            cgv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv,null);
            sv.mainController(svc);
        }
    }

    public LinkedList<CrewMember> setCellsCrewMembers(LinkedList<CrewMember> crewMembers, Map map) {
        for (int i = 0; i < crewMembers.size(); i++) {
            int[] coordinates = new int[2];
            coordinates[0] = crewMembers.get(i).getxCoordinate();
            coordinates[1] = crewMembers.get(i).getyCoordinate();
            crewMembers.get(i).setCell(map.getCellByCoordinates(coordinates));
        }
        return crewMembers;
    }

    public LinkedList<Impostor> setCellsImpostors(LinkedList<Impostor> impostors, Map map) {
        for (int i = 0; i < impostors.size(); i++) {
            int[] coordinates = new int[2];
            coordinates[0] = impostors.get(i).getxCoordinate();
            coordinates[1] = impostors.get(i).getyCoordinate();
            impostors.get(i).setCell(map.getCellByCoordinates(coordinates));
        }
        return impostors;
    }

    public Player setCellUserPlayer(Player userPlayer, Map map) {
        int[] coordinates = new int[2];
        coordinates[0] = userPlayer.getxCoordinate();
        coordinates[1] = userPlayer.getyCoordinate();
        userPlayer.setCell(map.getCellByCoordinates(coordinates));
        return userPlayer;
    }
}


