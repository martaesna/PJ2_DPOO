package businessLayer;

import businessLayer.entities.maps.Cell;
import com.google.gson.Gson;
import businessLayer.entities.maps.Map;
import java.io.File;
import java.io.FileReader;

public class MapManager {
    private static Map map;

    public MapManager(Map map) {
        MapManager.map = map;
    }

    /**
     * Mètode que llegeix el mapa a jugar
     * @param mapName nom del mapa
     * @return classe Mapa amb tota la informació del json
     */
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
}


