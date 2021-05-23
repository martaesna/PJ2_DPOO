package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.entities.json.JsonGame;
import presentationLayer.views.PlayView;
import presentationLayer.views.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteGameViewController implements ActionListener {
    private final DeleteGameView dgv;
    private String userName;


    public DeleteGameViewController(DeleteGameView dgv, String userName) {
        this.dgv = dgv;
        this.userName = userName;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Delete")) { //cuando apretamos el boton
            GameManager gameManager = new GameManager();
            if (gameManager.checkGame(dgv.getGameName())) {
                if (JOptionPane.OK_OPTION == dgv.confirmDeleteGame()) {
                    gameManager.deleteGame(dgv.getGameName());
                    JsonGame.deleteJsonGame(dgv.getGameName());
                }
            } else {
                dgv.printErrorNoExistance();
            }
        }
        if (e.getActionCommand().equals("Return")) { //cuando apretamos el boton
            dgv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv,userName);
            pv.mainController(pvc);

        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            dgv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv,null, userName);
            sv.mainController(svc);
        }

    }
}
