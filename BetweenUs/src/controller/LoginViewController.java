package controller;

import model.User;
import model.UserManager;
import view.LoginView;
import view.RegisterView;
import view.SettingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {
    private LoginView lv;
    public LoginViewController(LoginView lv) {
        this.lv = lv;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) { //cuando apretamos el boton
            lv.setVisible(false);
            RegisterView rv = new RegisterView();
            RegisterViewController rvc = new RegisterViewController(rv);
            rv.mainController(rvc);
        }
        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            UserManager userManager = new UserManager();
            if (userManager.loginUser(lv.getUsername(),lv.getPassword())) {
                System.out.println("Login correcte");
                lv.setVisible(false);
                SettingView sv = new SettingView();
                SettingViewController svc = new SettingViewController(sv,lv.getUsername());
                sv.mainController(svc);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Les credencials introduïdes són incorrectes", "Error Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
