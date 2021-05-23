package businessLayer;

import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Cell;
import com.google.gson.Gson;
import businessLayer.entities.maps.Map;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class MapManager {
    private static Map map;

    public MapManager(Map map) {
        MapManager.map = map;
    }

    public static Map llegeixMapa(String mapName) {
        try {
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            File f = new File("");
            String path = f.getAbsolutePath();
            reader = new com.google.gson.stream.JsonReader(new FileReader(path + "/BetweenUs/src/mapsFiles/" + mapName));
            map = gson.fromJson(reader, Map.class);

            System.out.println("\nLectura del mapa finalitzada.\n");

        } catch (Exception e) {
            System.out.println("No s'ha pogut llegir el mapa: " + e.getMessage());
        }
        return map;
    }

    public Map getMap() {
        return map;
    }

    public Cell nextPlayerCell (int[] nextCell) {
        return getMap().getCellByCoordinates(nextCell);
    }

    public void updatePlayersMovement() {

    }

    /*
    public LinkedList<String> getCellColors(Player userPlayer, LinkedList<Impostor> impostors, LinkedList<CrewMember> crewMembers, Cell cell) {
        LinkedList<String> colors = new LinkedList<>();
        if (userPlayer.getCell() == cell) {
            colors.add(userPlayer.getColor());
        }
        for (CrewMember crewMember: crewMembers) {
            if (cell == crewMember.getCell()) {
                colors.add(crewMember.getColor());
            }
        }
        for (Impostor impostor: impostors) {
            if (cell == impostor.getCell()) {
                colors.add(impostor.getColor());
            }
        }
        return colors;
    }*/
}


