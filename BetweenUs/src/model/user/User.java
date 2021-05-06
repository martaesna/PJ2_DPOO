package model.user;

import model.database.SQLUserDAO;
import model.database.UserDAO;

public class User {
    private String name;
    private String mail;
    private String password;
    private String confirmedPassword;

    public User (String name, String mail, String password, String confirmedPassword) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
