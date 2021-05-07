package model.database;

import model.user.User;

public interface UserDAO {
    void registerUser(User user);
    boolean checkLoginUser(String userNameMail, String password);
    void deleteUser(String nameLogin);
    boolean userNameExists(String userName);
    boolean userMailExists(String userMail);
}
