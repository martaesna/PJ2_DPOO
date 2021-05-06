package controller;

import model.GameManager;
import view.ConfiguredGameView;
import view.PlayView;
import model.UserManager;
import view.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteGameViewController implements ActionListener {
    private DeleteGameView dgv;

    public DeleteGameViewController(DeleteGameView dgv) {
        this.dgv = dgv;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Delete")) { //cuando apretamos el boton
            GameManager gameManager = new GameManager();
            if (gameManager.checkGame(dgv.getGameName())) {
                if (JOptionPane.OK_OPTION == dgv.confirmDeleteGame()) {
                    gameManager.deleteGame(dgv.getGameName());
                }
            } else {
                dgv.printErrorNoExistance();
            }
        }
        if (e.getActionCommand().equals("Return")) { //cuando apretamos el boton
            dgv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);

        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            dgv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv,null);
            sv.mainController(svc);
        }

    }
}
