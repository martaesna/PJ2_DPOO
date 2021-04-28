package controller;

import model.User;
import model.UserManager;
import view.LoginView;
import view.RegisterView;
import view.SettingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.database.SQLoperations.registreUsuari;

public class SettingViewController implements ActionListener {

    private SettingView sv;

    public SettingViewController(SettingView sv) {
        this.sv = sv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Logout")) { //cuando apretamos el boton
            RegisterView rv = new RegisterView();
            RegisterViewController rvc = new RegisterViewController(rv);
            rv.mainController(rvc);
        }
        if (e.getActionCommand().equals("Delete")) { //cuando apretamos el boton
            RegisterView rv = new RegisterView();
            RegisterViewController rvc = new RegisterViewController(rv);
            rv.mainController(rvc);
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            RegisterView rv = new RegisterView();
            RegisterViewController rvc = new RegisterViewController(rv);
            rv.mainController(rvc);
        }
    }
}
