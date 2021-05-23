package persitanceLayer;

import businessLayer.ConectorDB;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.game.Game;
import businessLayer.entities.json.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static businessLayer.JsonReader.llegeixJSON;

public class SQLGameDAO implements GameDAO{
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

    @Override
    public void deleteUserPlayer(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM PlayerCharacter WHERE gameName LIKE '" + gameName + "'";
        conn.deleteQuery(query);
    }

    @Override
    public void deleteCrewMembers(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM CrewMember WHERE gameName LIKE '" + gameName + "'";
        conn.deleteQuery(query);
    }

    @Override
    public void deleteImpostors(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Impostor WHERE gameName LIKE '" + gameName + "'";
        conn.deleteQuery(query);
    }

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

    @Override
    public void saveGame(Character userPlayer, LinkedList<Impostor> impostors, LinkedList<CrewMember> crewMembers, String gameName) {
        savePlayerCharacter(userPlayer, gameName);
        saveImpostors(impostors, gameName);
        saveCrewMembers(crewMembers, gameName);
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

    @Override
    public void saveImpostors(LinkedList<Impostor> impostors, String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        for (Impostor impostor: impostors) {
            conn.insertQuery("INSERT INTO Impostor(color, xCoordinate, yCoordinate, gameName) " +
                    "SELECT (" + "'" + impostor.getColor() + "'" + "," + "'" + impostor.getCell().getX() + "'" + "," + "'" +
                    impostor.getCell().getY() + "'" + ", g.gameName FROM GAME AS g WHERE" + gameName + "= g.gameName LIMIT 1");
        }
    }

    @Override
    public void saveCrewMembers(LinkedList<CrewMember> crewMembers, String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        for (CrewMember crewMember: crewMembers) {
            conn.insertQuery("INSERT INTO Impostor(color, xCoordinate, yCoordinate, previousRoom, gameName) " +
                    "SELECT (" + "'" + crewMember.getColor() + "'" + "," + "'" + crewMember.getCell().getX() + "'" + "," + "'" +
                    crewMember.getCell().getY() + "'" + "," + "'" + crewMember.getPreviousRoom() + "'" + "," +
                    "'" + ", g.gameName FROM GAME AS g WHERE" + gameName + "= g.gameName LIMIT 1");
        }
    }

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

    @Override
    public LinkedList<Impostor> getImpostors(String gameName) {
        LinkedList<Impostor> impostors = new LinkedList<>();
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT * FROM Impostor AS i WHERE i.gameName LIKE '" + gameName + "'");
        try {
            while(rs.next()) {
                String color = rs.getString("color");
                int xCoordinate = rs.getInt("xCoordinate");
                int yCoordinate = rs.getInt("yCoordinate");

                Impostor impostor = new Impostor(color, xCoordinate, yCoordinate);
                impostors.add(impostor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return impostors;
    }

    @Override
    public LinkedList<CrewMember> getCrewMembers(String gameName) {
        LinkedList<CrewMember> crewMembers = new LinkedList<>();
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT * FROM CrewMember AS cm WHERE cm.gameName LIKE '" + gameName + "'");
        try {
            while(rs.next()) {
                String color = rs.getString("color");
                int xCoordinate = rs.getInt("xCoordinate");
                int yCoordinate = rs.getInt("yCoordinate");
                int previousRoom = rs.getInt("previousRoom");

                CrewMember crewMember = new CrewMember(color, xCoordinate,yCoordinate, previousRoom);
                crewMember.startThread();
                crewMembers.add(crewMember);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return crewMembers;
    }

    @Override
    public void deleteUserGames(String userName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Game WHERE creator LIKE '" + userName + "'";
        conn.deleteQuery(query);
    }
}
