package controller;

import model.User;
import model.UserManager;
import view.LoginView;
import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.database.SQLoperations.registreUsuari;

public class LoginViewController implements ActionListener {
    private LoginView lv;
    public LoginViewController(LoginView lv) {
        this.lv = lv;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) { //cuando apretamos el boton
            RegisterView rv = new RegisterView();
            RegisterViewController rvc = new RegisterViewController(rv);
        }
        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            //comprovar si existeix l'usuari i si existeix iniciar vista del joc
        }
    }
}
