package presentationLayer.controllers;

import businessLayer.GameManager;
import presentationLayer.views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * La classe 'PlayViewController' ens permet gestionar la vista del PlayView.
 *
 * Els metodes reben informació de la vista, i realitzen les modificacions pertinents per poder gestionar el menu principal
 * del joc.
 */
public class PlayViewController implements ActionListener {
    private final PlayView pv;
    private final String userName;
    private String[] gameNames;

    public PlayViewController(PlayView pv, String userName) {
        this.pv = pv;
        this.userName = userName;
    }

    /**
     * Depen del boto que apretem fa una funcionalitat
     * Config ens porta al menu de settings
     * NewGame ens porta el menú per crear un nou joc
     * Configured ens porta al menú per configurar un joc
     * Charge ens porta al menú per carrega un joc
     * Delete ens porta al menú per eliminar un joc
     * @param e escoltador d'events
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Config":
                pv.setVisible(false);
                SettingView sv = new SettingView();
                SettingViewController svc = new SettingViewController(sv, userName);
                sv.mainController(svc);
                break;
            case "NewGame":
                pv.setVisible(false);
                NewGameView ngv = new NewGameView();
                NewGameController ngvc = new NewGameController(ngv, userName);
                ngv.mainController(ngvc);
                break;
            case "Configured":
                GameManager gameManager = new GameManager();
                if (!gameManager.createdGames()) {
                    pv.printErrorNoGames();
                } else {
                    pv.setVisible(false);

                    ConfiguredGameView cogv = new ConfiguredGameView(getGameNames(gameManager));
                    ConfiguredGameViewController cogvc = new ConfiguredGameViewController(cogv, userName);
                    cogv.mainController(cogvc);
                }
                break;
            case "Charge":
                pv.setVisible(false);
                ChargeGameView cgv = new ChargeGameView();
                ChargeGameViewController cgvc = new ChargeGameViewController(cgv, userName);
                cgv.mainController(cgvc);
                break;
            case "Delete":
                pv.setVisible(false);
                DeleteGameView dgv = new DeleteGameView();
                DeleteGameViewController dgvc = new DeleteGameViewController(dgv, userName);
                dgv.mainController(dgvc);
                break;
        }
    }

    public String[] getGameNames(GameManager gameManager){
        LinkedList<String> gameNames = gameManager.getGameNames();
        String[] names = new String[gameNames.size()];

        for (int i = 0; i < gameNames.size(); i++) {
            names[i] = gameNames.get(i);
        }

        return names;
    }
}
