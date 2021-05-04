package controller;


import model.User;
import model.UserManager;
import view.LoginView;
import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RegisterViewController implements ActionListener {
    private RegisterView rv;
    public RegisterViewController(RegisterView rv) {
        this.rv = rv;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) { //cuando apretamos el boton
            User user = new User(rv.getUsername(), rv.getEmail(), rv.getPassword(), rv.getRepeatPassword());
            UserManager userManager = new UserManager();
            if(userManager.checkRegister(user)){
                userManager.registerUser(user);
            }
        }
        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            LoginView lv = new LoginView();
            LoginViewController lvc = new LoginViewController(lv);
            lv.mainController(lvc);
        }
    }
}
