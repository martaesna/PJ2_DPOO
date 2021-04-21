package controller;

import model.User;

public class MainController {
    private UserController userController;

    public MainController(UserController userController) {
        this.userController = userController;
    }

    public void registerUser(){

    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
