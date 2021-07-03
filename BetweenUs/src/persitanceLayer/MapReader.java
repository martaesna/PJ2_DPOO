package persitanceLayer;

import businessLayer.entities.maps.Map;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;


/**
 * La classe MapReader llegeix el nom del mapa que li passem per paràmetre
 */
public class MapReader {
    private Map map;

    public MapReader(String mapName) {
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
    }

    public Map getMap() {
        return map;
    }

}
