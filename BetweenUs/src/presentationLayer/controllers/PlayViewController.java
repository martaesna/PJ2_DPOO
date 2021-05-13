package presentationLayer.controllers;
import presentationLayer.views.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayViewController implements ActionListener {
    private PlayView pv;
    public PlayViewController(PlayView pv) {
        this.pv = pv;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            pv.setVisible(false);
            SettingView sv = new SettingView();
            SettingViewController svc = new SettingViewController(sv,null);
            sv.mainController(svc);

        }

        if (e.getActionCommand().equals("NewGame")) { //cuando apretamos el boton
            pv.setVisible(false);

            NewGameView ngv = new NewGameView();
            NewGameController ngvc = new NewGameController(ngv);
            ngv.mainController(ngvc);
        }
        if (e.getActionCommand().equals("Configured")) { //cuando apretamos el boton
            pv.setVisible(false);
            ConfiguredGameView cogv = new ConfiguredGameView();
            ConfiguredGameViewController cogvc = new ConfiguredGameViewController(cogv);
            cogv.mainController(cogvc);

        }
        if (e.getActionCommand().equals("Charge")) { //cuando apretamos el boton
            pv.setVisible(false);
            ChargeGameView cgv = new ChargeGameView();
            ChargeGameViewController cgvc = new ChargeGameViewController(cgv);
            cgv.mainController(cgvc);


        }
        if (e.getActionCommand().equals("Delete")) { //cuando apretamos el boton
            pv.setVisible(false);
            DeleteGameView dgv = new DeleteGameView();
            DeleteGameViewController dgvc = new DeleteGameViewController(dgv);
            dgv.mainController(dgvc);
        }
    }
}
