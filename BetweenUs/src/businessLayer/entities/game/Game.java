package businessLayer.entities.game;

import java.util.LinkedList;

public class Game {
    private String gameName;
    private final int players;
    private final int impostors;
    private final String playerColor;
    private String map;
    private String creator;
    private LinkedList<Character> gamePlayers;

    public Game(String gameName, int players, int impostors, String playerColor, String map, String creator) {
        this.gameName = gameName;
        this.players = players;
        this.impostors = impostors;
        this.playerColor = playerColor;
        this.map = map;
        this.creator = creator;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getPlayers() {
        return players;
    }

    public int getImpostors() {
        return impostors;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LinkedList<Character> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(LinkedList<Character> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}
