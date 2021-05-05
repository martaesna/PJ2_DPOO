package model.database;

import model.Game;
import model.json.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import static model.json.JsonReader.llegeixJSON;

public class SQLGameDAO implements GameDAO{
    @Override
    public void createGame(Game game) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        conn.insertQuery("INSERT INTO Game(gameName,players,impostors,playerColor,map,creator) " +
                "VALUES (" + "'" + game.getGameName() + "'" + "," + "'" + game.getPlayers() + "'" + "," + "'" + game.getImpostors() +
                "'" + "," + "'" + game.getPlayerColor() + "'" + "," + "'" + game.getMap() + "'" + "," + "'" + game.getCreator() + ")");
    }

    @Override
    public boolean gameExists(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT g.gameName FROM Game AS g WHERE g.gameName LIKE '" + gameName + "'");
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
    public void deleteGame(String gameName) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Game WHERE gameName LIKE '" + gameName + "'";
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
    public void resumeGame(String gameName) {




    }
}
