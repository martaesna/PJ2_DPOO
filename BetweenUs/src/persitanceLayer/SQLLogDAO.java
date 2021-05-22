package persitanceLayer;

import businessLayer.ConectorDB;
import businessLayer.entities.json.Data;
import presentationLayer.views.customComponents.Log;

import static businessLayer.JsonReader.llegeixJSON;

public class SQLLogDAO implements LogDAO {
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
