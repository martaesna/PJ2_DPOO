package businessLayer.entities.game;

/**
 * La classe 'Game' conté els atributs generals que hem definit i la utilitzarem
 * principalment per guardar la configuració del joc i modificar la base de dades.
 *
 * Tenim els atributs que defineixen un joc i els mètodes principals (getters i setters)
 * per poder obtenir i modificar les seves dades
 */
public class Game {
    // Attributes
    private String gameName;
    private final int players;
    private final int impostors;
    private final String playerColor;
    private String map;
    private final String creator;

    // Parametrized constructor
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

}
