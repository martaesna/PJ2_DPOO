package persitanceLayer;

import businessLayer.entities.user.User;
import businessLayer.entities.json.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import static persitanceLayer.JsonReader.llegeixJSON;

/**
 * Classe que implementa tots els mètodes de l'interfície UserDAO
 */
public class SQLUserDAO implements UserDAO{
    private Data data;
    private ConectorDB conn;

    public SQLUserDAO() {
        data = llegeixJSON();
        conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
    }

    /**
     * Mètode que introdueix un usuari a la base de dades
     * @param user usuari a introduir
     */
    @Override
    public void registerUser(User user) {
        conn.connect();
        conn.insertQuery("INSERT INTO User(username, email, password, partides_guanyades, partides_jugades) VALUES (" + "'" + user.getName() + "'" + "," + "'" + user.getMail() + "'" + "," + "'" + user.getPassword() + "'" + ",0,0)");
        conn.disconnect();
    }

    /**
     * Mètode que mira si el login de l'usuari és correcte
     * @param userNameMail email de l'usuari
     * @param password contrasenya de l'usuari
     * @return si és correcte o no
     */
    @Override
    public boolean checkLoginUser(String userNameMail, String password) {
        conn.connect();
        String query = "SELECT u.username FROM User AS u WHERE (u.username LIKE '" + userNameMail + "' OR u.email LIKE '" + userNameMail + "') AND u.password LIKE '" + password + "'";
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
     * Mètode que elimina un usuari de la base de dades
     * @param nameLogin nom de l'usuari
     */
    @Override
    public void deleteUser(String nameLogin) {
        conn.connect();
        String query = "DELETE FROM User WHERE username LIKE '" + nameLogin + "'";
        conn.deleteQuery(query);
        conn.disconnect();
    }

    /**
     * Mètode que comprova si un usuari existeix
     * @param userName nom a comprovar
     * @return si existeix o no
     */
    @Override
    public boolean userNameExists(String userName) {
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT u.username FROM User AS u WHERE u.username LIKE '" + userName + "'");
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
     * Mètode que comporva si un correu existeix
     * @param userMail correu a comporvar
     * @return si existeix o no
     */
    @Override
    public boolean userMailExists(String userMail) {
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT u.email FROM User AS u WHERE u.email LIKE '" + userMail + "'");
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
     * Mètode que retorna el nom de l'usuari en funció del login
     * @param loginName input per l'usuari que pot ser el nom o el correu
     * @return String amb el nom de l'usuari
     */
    @Override
    public String getUsername(String loginName) {
        if (userMailExists(loginName)) {
            conn.connect();
            ResultSet rs = conn.selectQuery("SELECT u.username FROM User AS u WHERE u.email LIKE '" + loginName + "'");
            try {
                if(rs.next()) {
                    return rs.getString("username");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        } else {
           return loginName;
        }
    }
}
