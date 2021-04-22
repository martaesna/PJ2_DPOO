package model.database;

import model.json.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import static model.json.JsonReader.llegeixJSON;

public class SQLoperations {

    /**
     * Montem la query tipus insert de registre amb els parametres corresponents als camps de la taula Usuariç
     * @param name  Nom usuari
     * @param mail  Mail Usuari
     * @param password   Contrasenya Usuari
     */
    public static void registreUsuari (String name, String mail, String password) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        conn.insertQuery("INSERT INTO User(username, email, password, partides_guanyades, partides_jugades) VALUES (" + "'" + name + "'" + "," + "'" + mail + "'" + "," + "'" + password + "'" + ",0,0)");
    }
    /**
     * Montem la query tipus select per comprovar si un usari existeix a la BBDD
     * @param name  Nom usuari

     */
    public static boolean existeixUsuari (String name) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT u.nickname FROM User AS u WHERE u.nickname LIKE '" + name + "'");
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
     * Montem la query tipus select per indicar si el loguin s'ha fet correctament
     * @param name  Nom usuari
     * @param password   Contrasenya Usuari
     */
    public static boolean loginUsuariCorrecte (String name, String password) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT u.nickname FROM User AS u WHERE (u.nickname LIKE '" + name + "' OR u.email LIKE '" + name + "') AND u.password LIKE '" + password + "'";
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
     * Montem la query tipus delete per esborrar un usuari de la BBDDD
     * @param name  Nom usuari
     */
    public static void borraUsuari(String name){
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM User WHERE nickname LIKE '" + name + "' OR email LIKE '" + name + "'";
        conn.deleteQuery(query);
    }

}

