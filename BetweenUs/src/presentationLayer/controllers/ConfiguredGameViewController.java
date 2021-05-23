package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.PlayerManager;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.game.Game;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Map;
import presentationLayer.views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConfiguredGameViewController implements ActionListener {
    private final ConfiguredGameView cogv;
    private ArrayList<String> colors;
    private String userName;
    public ConfiguredGameViewController(ConfiguredGameView cogv, String userName) {
        this.cogv = cogv;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));
        this.userName = userName;
    }

    /**
     * Segons el boto que apretem fa una funcionalitat
     * Create Crea un nou joc
     * Return Retorna al menu anterior
     * config anem a la vista settings
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Create")) { //cuando apretamos el boton
            GameManager gameManager = new GameManager();
            if (gameManager.checkGame(cogv.getConfiguredName())) {
                if (gameManager.checkRecreateGame(cogv.getConfiguredName())) {
                    cogv.printErrorRecreatedExistance();
                } else {
                    gameManager.createConfiguredGame(cogv.getConfiguredName(), userName);

                    Game game = gameManager.selectGame(cogv.getConfiguredName());
                    int starterColor = 0;

                    Map map = MapManager.llegeixMapa(game.getMap());
                    MapManager mapManager = new MapManager(map);

                    Player userPlayer = new Player(game.getPlayerColor());
                    if (userPlayer.getColor().equals("RED")) {
                        starterColor++;
                    }
                    LinkedList<CrewMember> crewMembers = gameManager.getCrewMembers(game.getPlayers() - game.getImpostors() - 1, game.getPlayerColor(), starterColor, colors, mapManager);
                    starterColor = getImpostorsStarterColor(gameManager.getUserColorPosition(game.getPlayerColor(), colors), crewMembers.size(), starterColor);
                    LinkedList<Impostor> impostors = gameManager.getImpostors(game.getImpostors(), game.getPlayerColor(), starterColor + crewMembers.size(), colors, mapManager);

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
                    NpcManager npcManager = new NpcManager(players);
                    for (Character character: players) {
                        if (character instanceof Impostor) {
                            character.setNpcManager(npcManager);
                        }
                    }

                    MapView mv = new MapView(map, players, userPlayer);

                    MapController mapController = new MapController(mv, mapManager, playerManager, players, cogv.getConfiguredName(), userName, npcManager);
                    mv.mainController(mapController);
                    mapController.startMapThread();
                    cogv.setVisible(false);
                }
            } else {
                cogv.printErrorNoExistance();
            }
        }
        if (e.getActionCommand().equals("Return")) {
            cogv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv, userName);
            pv.mainController(pvc);

        }

        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            cogv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv,null, userName);
            sv.mainController(svc);
        }
    }

    /**
     * Ens canvia el starterColor si coincideix amb el de un crewMember
     * @param userPosition posicio del color del user
     * @param crewMembers quantitat de crewmembers
     * @param starterColor Color del impostor
     * @return Retorna el color canviat o no del impostor
     */
    public int getImpostorsStarterColor(int userPosition, int crewMembers, int starterColor) {
        if (userPosition <= crewMembers) {
            return starterColor+1;
        } else {
            return starterColor;
        }
    }
}
