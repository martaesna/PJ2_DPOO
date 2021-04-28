package controller;

import model.User;
import model.UserManager;
import view.LoginView;
import view.RegisterView;
import view.SettingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.database.SQLoperations.loginUsuariCorrecte;
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
            rv.mainController(rvc);
        }
        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            if (loginUsuariCorrecte(lv.getUsername(), lv.getPassword())) {
                System.out.println("login correcte");
                //passem a la vista principal del joc
                SettingView sv = new SettingView();
                SettingViewController svc = new SettingViewController(sv);
                sv.mainController(svc);
            } else {
                System.out.println("error login");
            }
        }
    }
}
