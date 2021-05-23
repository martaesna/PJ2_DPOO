package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.UserManager;
import businessLayer.entities.json.JsonGame;
import presentationLayer.views.LoginView;
import presentationLayer.views.PlayView;
import presentationLayer.views.RegisterView;
import presentationLayer.views.SettingView;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingViewController implements ActionListener {
    private final SettingView sv;
    private final String nameLogin;
    private String userName;

    public SettingViewController(SettingView sv, String nameLogin, String userName) {
        this.sv = sv;
        this.nameLogin = nameLogin;
        this.userName = userName;
    }

    /**
     * Depen del boto que apretem fa una funcionalitat
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Logout")) { //cuando apretamos el boton
            if(JOptionPane.OK_OPTION == sv.confirmLogout()){
                sv.setVisible(false);
                LoginView lv = new LoginView();
                LoginViewController lvc = new LoginViewController(lv);
                lv.mainController(lvc);
            }

        }
        if (e.getActionCommand().equals("Delete")) { //cuando apretamos el boton
            if(JOptionPane.OK_OPTION == sv.confirmDeleteUser()){
                sv.setVisible(false);

                GameManager gameManager = new GameManager();
                gameManager.deleteUserGames(userName);

                UserManager userManager = new UserManager();
                userManager.deleteUser(userName);

                RegisterView rv = new RegisterView();
                RegisterViewController rvc = new RegisterViewController(rv);
                rv.mainController(rvc);
            }
        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            sv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv, userName);
            pv.mainController(pvc);

        }
    }

    public void stateChanged(ChangeEvent e) {

    }
}
