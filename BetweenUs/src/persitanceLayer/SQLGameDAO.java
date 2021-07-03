package persitanceLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.game.Game;
import businessLayer.entities.json.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static persitanceLayer.JsonReader.llegeixJSON;

/**
 *
 */
public class SQLGameDAO implements GameDAO{
    /**
     * Mètode que insereix un joc a la base de dades
     * @param game joc a introduir
     * @param userName nom de l'usuari
     */
    @Override
    public void createGame(Game game, String userName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        conn.insertQuery("INSERT INTO Game(gameName,players,impostors,playerColor,map,creator) " +
                "VALUES (" + "'" + game.getGameName() + "'" + "," + "'" + game.getPlayers() + "'" + "," + "'" + game.getImpostors() +
                "'" + "," + "'" + game.getPlayerColor() + "'" + "," + "'" + game.getMap() + "'" + "," + "'" + userName + "')");
    }

    /**
     * Mètode que comporva si un joc ja existeix
     * @param gameName nom del joc
     * @return si existeix o no
     */
    @Override
    public boolean gameExists(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        try (ResultSet rs = conn.selectQuery("SELECT g.gameName FROM Game AS g WHERE g.gameName LIKE '" + gameName + "'")) {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return false;
    }

    /**
     * Mètode que elimina un joc de la base de dades
     * @param gameName joc a eliminar
     */
    @Override
    public void deleteGame(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Game WHERE gameName LIKE '" + gameName + "'";
        conn.deleteQuery(query);

        deleteUserPlayer(gameName);
    }

    /**
     * Mètode que elimina els jocs de l'usuari a la taula PlayerCharacter
     * @param gameName nom del joc
     */
    @Override
    public void deleteUserPlayer(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM PlayerCharacter WHERE gameName LIKE '" + gameName + "'";
        conn.deleteQuery(query);
    }

    /**
     * Mètode que comprova si l'usuari és el creador del joc
     * @param gameName nom del joc
     * @param username nom de l'usuari
     * @return si es el creador o no
     */
    @Override
    public boolean checkCreator(String gameName, String username) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT g.creator FROM Game AS g WHERE (g.creator LIKE '" + username + "')";
        ResultSet rs = conn.selectQuery(query);
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mètode que mira si la recreació d'un joc existeix
     * @param gameName nom del joc
     * @return si existeix o no
     */
    @Override
    public boolean recreatedGameExists(String gameName) {
        String recreatedGame = gameName + "(Copy)";
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT g.gameName FROM Game AS g WHERE g.gameName LIKE '" + recreatedGame + "'");
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mètode que selecciona un joc de la base de dades
     * @param gameName nom del joc
     * @return joc seleccionat
     */
    @Override
    public Game selectGame(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT * FROM Game AS g WHERE g.gameName LIKE '" + gameName + "'");
        try {
            if(rs.next()) {
                String name = rs.getString("gameName");
                int players = rs.getInt("players");
                int impostors = rs.getInt("impostors");
                String playerColor = rs.getString("playerColor");
                String map = rs.getString("map");
                String creator = rs.getString("creator");

                return new Game(name, players, impostors, playerColor, map, creator);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Mètode que guarda la partida de l'usuari
     * @param userPlayer usuari
     * @param impostors llista amb els impostors
     * @param crewMembers llista amb els crewmembers
     * @param gameName nom del joc
     */
    @Override
    public void saveGame(Character userPlayer, LinkedList<Impostor> impostors, LinkedList<CrewMember> crewMembers, String gameName) {
        savePlayerCharacter(userPlayer, gameName);
    }

    @Override
    public void savePlayerCharacter(Character userPlayer, String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        conn.insertQuery("INSERT INTO PlayerCharacter(color, xCoordinate, yCoordinate, gameName) " +
                "SELECT (" + "'" + userPlayer.getColor() + "'" + "," + "'" + userPlayer.getCell().getX() + "'" + "," + "'" + userPlayer.getCell().getY() +
                "'" + ", g.gameName FROM GAME AS g WHERE" + gameName + "= g.gameName LIMIT 1");
    }

    /**
     * Mètode que retorna l'usuari
     * @param gameName nom del joc
     * @return usuari
     */
    @Override
    public Player getUserPlayer(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT * FROM PlayerCharacter AS c WHERE c.gameName LIKE '" + gameName + "'");
        try {
            if(rs.next()) {
                String color = rs.getString("color");
                int xCoordinate = rs.getInt("xCoordinate");
                int yCoordinate = rs.getInt("yCoordinate");

                return new Player(color, xCoordinate, yCoordinate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Mètode que elimina els jocs de l'usuari
     * @param userName nom de l'usuari
     */
    @Override
    public void deleteUserGames(String userName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Game WHERE creator LIKE '" + userName + "'";

        conn.deleteQuery(query);
    }

    @Override
    public LinkedList<String> readGames() {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT gameName FROM Game WHERE gameName NOT LIKE '%(Copy)%'");
        LinkedList<String> gameNames = new LinkedList<>();
        try {
            while(rs.next()) {
                gameNames.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return gameNames;
    }

    @Override
    public boolean createdGames() {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT gameName FROM Game");
        try {
            if(!rs.next()) {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
