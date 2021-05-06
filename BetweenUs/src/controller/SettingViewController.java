package controller;

import model.User;
import model.UserManager;
import view.LoginView;
import view.PlayView;
import view.RegisterView;
import view.SettingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingViewController implements ActionListener {

    private SettingView sv;
    private String nameLogin;

    public SettingViewController(SettingView sv, String nameLogin) {
        this.sv = sv;
        this.nameLogin = nameLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Logout")) { //cuando apretamos el boton
            if(JOptionPane.OK_OPTION == sv.confirmLogout()){
                sv.setVisible(false);
                LoginView lv = new LoginView();
                LoginViewController lvc = new LoginViewController(lv);
                lv.mainController(lvc);
            }

        }
        if (e.getActionCommand().equals("Delete")) { //cuando apretamos el boton
            if(JOptionPane.OK_OPTION == sv.confirmDeleteUser()){
                sv.setVisible(false);
                UserManager userManager = new UserManager();
                userManager.deleteUser(nameLogin);
                RegisterView rv = new RegisterView();
                RegisterViewController rvc = new RegisterViewController(rv);
                rv.mainController(rvc);
            }
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            sv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);

        }
    }
}
