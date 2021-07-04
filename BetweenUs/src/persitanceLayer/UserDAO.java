package persitanceLayer;

import businessLayer.entities.user.User;

/**
 * Interfície que conté els mètodes que tracten l'usuari
 */

public interface UserDAO {
    void registerUser(User user);
    boolean checkLoginUser(String userNameMail, String password);
    void deleteUser(String username);
    boolean userNameExists(String userName);
    boolean userMailExists(String userMail);
    String getUsername(String loginName);
}
