package persitanceLayer;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La classe 'ConectorDB' ens permet conectarnos/desconectarnos a la Base de Dades
 *
 *Els metodes que conte ens permeten conectarnos a la base o desconectarnos.
 */
public class ConectorDB {
    private static String userName;
    private static String password;
    private static String db;
    private static int port;
    private static String url = "jdbc:mysql://localhost";
    private static Connection conn = null;
    private static Statement s;


    public ConectorDB(String usr, String pass, String db, int port) {
        ConectorDB.userName = usr;
        ConectorDB.password = pass;
        ConectorDB.db = db;
        ConectorDB.port = port;
        ConectorDB.url += ":"+port+"/";
        ConectorDB.url += db;
        ConectorDB.url += "?verifyServerCertificate=false&useSSL=false";
    }

    /**
     * Mètode que estableix la connexió amb la base de dades
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection)DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Conexió a base de dades " + url + " ... Ok");
            }
        }
        catch(SQLException ex) {
            System.out.println("Problema al connectar-nos a la BBDD --> " + url);
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Classe no trobada");
        }
    }

    /**
     * Mètode que desestableix la connexió amb la base de dades
     */
    public void disconnect(){
        try {
            conn.close();
            System.out.println("Desconnectat!");
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }

    /**
     * Mètode que fa una inserció a la base de dades
     * @param query query a executar
     */
    public void insertQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode que fa una selecció a la base de dades
     * @param query query a executar
     * @return informació demanada per l'usuari
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            s = conn.createStatement();
            rs = s.executeQuery (query);
        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
        return rs;
    }

    /**
     * Mètode que fa una eliminacó a la base de dades
     * @param query query a executar
     */
    public void deleteQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
