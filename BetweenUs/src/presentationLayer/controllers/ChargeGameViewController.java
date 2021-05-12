package presentationLayer.controllers;

import businessLayer.entities.game.Game;
import businessLayer.GameManager;
import presentationLayer.views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChargeGameViewController implements ActionListener {
    private ChargeGameView cgv;
    private String gameName;

    public ChargeGameViewController(ChargeGameView cgv) {
        this.cgv = cgv;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Charge")) { //cuando apretamos el boton
            System.out.println("Aquí es carrega el joc");
            GameManager gameManager = new GameManager();
            if (gameManager.checkGame(cgv.getChargeName())) {
                Game game = gameManager.chargeGame(cgv.getChargeName());

                //VISTA?
            } else {
                cgv.printErrorNoExistance();
            }
        }
        if (e.getActionCommand().equals("Return")) { //cuando apretamos el boton
            cgv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            cgv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv,null);
            sv.mainController(svc);
        }
    }
}


