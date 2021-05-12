package businessLayer.entities.game;

public class Game {
    private String gameName;
    private int players;
    private int impostors;
    private String playerColor;
    private String map;
    private String creator;

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

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getImpostors() {
        return impostors;
    }

    public void setImpostors(int impostors) {
        this.impostors = impostors;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String  playerColor) {
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
