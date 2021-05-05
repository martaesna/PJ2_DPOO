package controller;

import model.UserManager;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfiguredGameViewController {
    private ConfiguredGameView cgv;
    private String gameName;

    public ConfiguredGameViewController(ConfiguredGameView cgv) {
        this.cgv = cgv;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Charge")) { //cuando apretamos el boton
            System.out.println("Aquí es crea un nou joc amb la configuració d'un anterior");
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            cgv.setVisible(false);
        }
    }
}
