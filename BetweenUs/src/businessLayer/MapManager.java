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

    public MapManager() {}

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

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        MapManager.map = map;
    }
}


