package businessLayer.entities.json;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.game.Game;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class JsonGame {

    public static void jsonGame(Game game, LinkedList<Character> players){
        JSONObject gameObj = new JSONObject();
        gameObj.put("Gamename", game.getGameName());
        gameObj.put("Players", game.getPlayers());
        gameObj.put("Impostors", game.getImpostors());
        gameObj.put("PlayerColor", game.getPlayerColor());
        gameObj.put("Map", game.getMap());
        gameObj.put("Creator", game.getCreator());

        JSONArray gamePlayers = new JSONArray();
        for(Character character: players){
            JSONObject characters = new JSONObject();
            boolean isImpostor = character instanceof Impostor;
            characters.put("TotalTime", character.getTotalTime().getSeconds());
            characters.put("IntervalTime", character.getIntervalTime().getSeconds());
            characters.put("Color", character.getColor());
            characters.put("X", character.getxCoordinate());
            characters.put("Y", character.getyCoordinate());
            characters.put("IsRunning", character.isRunning());
            characters.put("IsImpostor", isImpostor);
            gamePlayers.put(characters);
        }
        gameObj.put("GamePlayers", gamePlayers);

        String f = new File("").getAbsolutePath();
        try (FileWriter file = new FileWriter(f.concat  ("/BetweenUs/src/games/" + game.getGameName() + ".json"))) {
            file.write(gameObj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Game searchJsonGame(String game) {
        Game gameFound = null;
        String path = new File("").getAbsolutePath();
        System.out.println("hola");
        try {
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            reader = new com.google.gson.stream.JsonReader(new FileReader(path.concat("/BetweenUs/src/games/marta.json")));
            gameFound = gson.fromJson(reader, Game.class);
            System.out.println("ola");

        } catch(Exception e) {
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
        System.out.println("hola¿");

        return gameFound;
    }

    public static void deleteJsonGame(String game) {
        String path = new File("").getAbsolutePath();
        File fileToDelete = new File(path.concat("/BetweenUs/src/games/" + game + ".json"));
        fileToDelete.delete();
    }
}
