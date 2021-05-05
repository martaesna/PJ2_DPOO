package controller;

import model.UserManager;
import view.ChargeGameView;
import view.LoginView;
import view.RegisterView;
import view.SettingView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChargeGameViewController {
    private ChargeGameView cgv;
    private String gameName;

    public ChargeGameViewController(ChargeGameView cgv) {
        this.cgv = cgv;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Charge")) { //cuando apretamos el boton
            System.out.println("Aquí es carrega el joc");
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            cgv.setVisible(false);
        }
    }
}
