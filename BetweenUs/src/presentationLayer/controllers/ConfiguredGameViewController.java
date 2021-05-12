package presentationLayer.controllers;

import businessLayer.GameManager;
import presentationLayer.views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfiguredGameViewController implements ActionListener {
    private ConfiguredGameView cogv;
    private String gameName;

    public ConfiguredGameViewController(ConfiguredGameView cogv) {
        this.cogv = cogv;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Charge")) { //cuando apretamos el boton
            System.out.println("Aquí es crea un nou joc amb la configuració d'un anterior");
            GameManager gameManager = new GameManager();
            if (gameManager.checkGame(cogv.getConfiguredName())) {
                if (gameManager.checkRecreateGame(cogv.getConfiguredName())) {
                    cogv.printErrorRecreatedExistance();
                } else {
                    String recreateName = cogv.getConfiguredName() + "(Copy)";

                    //gameManager.createGame(recreateName);     //FALTA CANVIAR LA FUCIÓ A VOID, etc...
                }
            } else {
                cogv.printErrorNoExistance();
            }
        }
        if (e.getActionCommand().equals("Return")) {
            cogv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);

        }

        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            cogv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv,null);
            sv.mainController(svc);
        }
    }
}
