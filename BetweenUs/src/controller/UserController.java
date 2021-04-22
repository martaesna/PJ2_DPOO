package controller;

import model.User;
import model.UserManager;

public class UserController {
    private User user;
    private boolean error;
    private String confirmPassword;
    private UserManager userManager;

    public UserController(){
        error = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
