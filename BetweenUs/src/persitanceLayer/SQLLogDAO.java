package persitanceLayer;

import businessLayer.entities.json.Data;
import presentationLayer.views.customComponents.Log;

import static persitanceLayer.JsonReader.llegeixJSON;

/**
 * Classe que implementa tots els mètodes de l'interfície LogDAO
 */
public class SQLLogDAO implements LogDAO {
    /**
     * Mètode que guarda un log a la base de dades
     * @param log log a guardar
     * @param gameName nom del joc
     */
    public void saveLog(Log log, String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        conn.insertQuery("INSERT INTO Log(gameName, color, roomName, instant) " +
                "VALUES (" + "'" + gameName + "'" + "," + "'" + log.getName() + "'" + "," + "'" + log.getRoom() +
                "'" + "," + "'" + log.getInstant() + "')");
    }
}
