package model;

import java.awt.*;

public class Game {
    private String gameName;
    private int players;
    private int impostors;
    private Color playerColor;
    private String map;
    private String creator;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getImpostors() {
        return impostors;
    }

    public void setImpostors(int impostors) {
        this.impostors = impostors;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
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
}
