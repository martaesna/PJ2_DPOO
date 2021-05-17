package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import com.google.gson.Gson;
import businessLayer.entities.maps.Map;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Timer;

public class MapManager {
    private static Map map;
    private NpcManager npcManager;
    private PlayerManager playerManager;

    public MapManager() {
        npcManager = new NpcManager();
    }

    public NpcManager getNpcManager() {
        return npcManager;
    }

    public void setNpcManager(NpcManager npcManager) {
        this.npcManager = npcManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public static Map llegeixMapa() {
        try {
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            File f = new File("");
            String path = f.getAbsolutePath();
            reader = new com.google.gson.stream.JsonReader(new FileReader(path + "/BetweenUs/src/mapsFiles/space.json"));
            map = gson.fromJson(reader, Map.class);

            System.out.println("\nLectura del mapa finalitzada.\n");

        } catch (Exception e) {
            System.out.println("No s'ha pogut llegir el mapa: " + e.getMessage());
        }
        return map;
    }

    public Map getDades() {
        return map;
    }

    public int[] getCrewMemberIntervals(LinkedList<CrewMember> crewMembers) {
        int[] intervals = new int[crewMembers.size()];
        for (int i = 0; i < crewMembers.size(); i++) {
            intervals[i] = crewMembers.get(i).randomInterval();
        }
        return intervals;
    }

    public int[] getImpostorsIntervals(LinkedList<Impostor> impostors) {
        int[] intervals = new int[impostors.size()];
        for (int i = 0; i < impostors.size(); i++) {
            intervals[i] = impostors.get(i).randomInterval();
        }
        return intervals;
    }

    public void initialCell(Character player, LinkedList<CrewMember> crewMembers, LinkedList<Impostor> impostors, LinkedList<Cell> cells) {
        int[] coordinates = getCoffeShopCoordinates(cells);

        player.setCellX(coordinates[0]);
        player.setCellY(coordinates[1]);

        for (int i = 0; i < crewMembers.size(); i++) {
            crewMembers.get(i).setCellX(coordinates[0]);
            crewMembers.get(i).setCellY(coordinates[1]);
        }

        for (int i = 0; i < impostors.size(); i++) {
            impostors.get(i).setCellX(coordinates[0]);
            impostors.get(i).setCellY(coordinates[1]);
        }
    }

    public int[] getCoffeShopCoordinates(LinkedList<Cell> cells) {
        int[] coordinates = new int[2];

        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getRoomName() == "cafeteria") {
                coordinates[0] = cells.get(i).getX();
                coordinates[1] = cells.get(i).getY();
            }
        }
        return null;
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        MapManager.map = map;
    }
}


