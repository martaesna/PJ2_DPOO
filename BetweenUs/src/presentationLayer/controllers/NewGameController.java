package presentationLayer.controllers;

import businessLayer.UserManager;
import businessLayer.entities.user.User;
import presentationLayer.views.LoginView;
import presentationLayer.views.NewGameView;
import presentationLayer.views.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameController implements ActionListener {
    private NewGameView ngv;

    public NewGameController(NewGameView ngv) {
        this.ngv = ngv;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SelectFile")) { //cuando apretamos el boton
            JOptionPane.showMessageDialog(null, "ERROR: El format del email és incorrecte", "Error Registre", JOptionPane.ERROR_MESSAGE);
        }

        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            LoginView lv = new LoginView();
            LoginViewController lvc = new LoginViewController(lv);
            lv.mainController(lvc);
        }
    }
}
