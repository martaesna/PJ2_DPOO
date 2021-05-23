package presentationLayer.controllers;

import presentationLayer.views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayViewController implements ActionListener {
    private final PlayView pv;

    public PlayViewController(PlayView pv) {
        this.pv = pv;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Config":
                pv.setVisible(false);
                SettingView sv = new SettingView();
                SettingViewController svc = new SettingViewController(sv,null);
                sv.mainController(svc);
                break;
            case "NewGame":
                pv.setVisible(false);
                NewGameView ngv = new NewGameView();
                NewGameController ngvc = new NewGameController(ngv);
                ngv.mainController(ngvc);
                break;
            case "Configured":
                pv.setVisible(false);
                ConfiguredGameView cogv = new ConfiguredGameView();
                ConfiguredGameViewController cogvc = new ConfiguredGameViewController(cogv);
                cogv.mainController(cogvc);
                break;
            case "Charge":
                pv.setVisible(false);
                ChargeGameView cgv = new ChargeGameView();
                ChargeGameViewController cgvc = new ChargeGameViewController(cgv);
                cgv.mainController(cgvc);
                break;
            case "Delete":
                pv.setVisible(false);
                DeleteGameView dgv = new DeleteGameView();
                DeleteGameViewController dgvc = new DeleteGameViewController(dgv);
                dgv.mainController(dgvc);
                break;
        }
    }
}
