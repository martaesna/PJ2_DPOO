package businessLayer;

import businessLayer.entities.maps.Cell;
import com.google.gson.Gson;
import businessLayer.entities.maps.Map;

import java.io.File;
import java.io.FileReader;

public class MapManager {
    private static Map map;
    private NpcManager npcManager;
    private PlayerManager playerManager;

    public MapManager() {}

    public static Map llegeixMapa(){
        try{
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            File f = new File("");
            String path = f.getAbsolutePath();
            reader = new com.google.gson.stream.JsonReader(new FileReader(path + "/BetweenUs/src/mapsFiles/space.json"));
            map = gson.fromJson(reader, Map.class);

            System.out.println("\nLectura del mapa finalitzada.\n");

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el mapa: " + e.getMessage());
        }
        return map;
    }

    public Map getDades() {
        return map;
    }
    }


