package businessLayer;

import businessLayer.entities.maps.Cell;
import com.google.gson.Gson;
import businessLayer.entities.maps.Map;
import persitanceLayer.MapReader;

import java.io.File;
import java.io.FileReader;

/**
 * La classe 'MapManager' serveix per gestionar i llegir el mapa de la partida
 *
 * Els mètodes implementats ens retornen informació del mapa en funció dels
 * paràmetres que els hi passem a aquests primers
 */
public class MapManager {
    // Attributes
    private static Map map;
    private PlayerManager playerManager;
    private static MapReader mapReader;

    // Parametrized constructor
    public MapManager(Map map) {
        MapManager.map = map;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Mètode que llegeix el mapa a jugar
     * @param mapName nom del mapa
     * @return classe Mapa amb tota la informació del json
     */
    public static Map llegeixMapa(String mapName) {
        mapReader = new MapReader(mapName);
        return mapReader.getMap();
    }

    public Map getMap() {
        return map;
    }

    public Cell nextPlayerCell (int[] nextCell) {
        return getMap().getCellByCoordinates(nextCell);
    }

    /**
     * Mètode que retorna la cella on es troba el personatge de l'usuari
     * @return Cell corresponent a la posició de l'usuari
     */
    public Cell userPlayerCell () {
        for(int i = 0; i < map.getCells().size(); i++) {
            if (map.getCells().get(i) == playerManager.getPlayer().getCell()) {
                return map.getCells().get(i);
            }
        }
        return null;
    }
}


