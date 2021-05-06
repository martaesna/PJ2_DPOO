package controller;

import model.User;
import model.UserManager;
import view.LoginView;
import view.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterViewController implements ActionListener {
    private RegisterView rv;
    public RegisterViewController(RegisterView rv) {
        this.rv = rv;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) { //cuando apretamos el boton
            User user = new User(rv.getUsername(), rv.getEmail(), rv.getPassword(), rv.getRepeatPassword());
            caseRegister(user);
        }
        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            rv.setVisible(false);
            LoginView lv = new LoginView();
            LoginViewController lvc = new LoginViewController(lv);
            lv.mainController(lvc);
        }
    }

    public void caseRegister(User user) {
        UserManager userManager = new UserManager();
        int checked = userManager.checkRegister(user);
        switch (checked) {
            case 0:
                //Registre correcte
                userManager.registerUser(user);


                break;
            case 1:
                JOptionPane.showMessageDialog(null, "ERROR: El nom d'usuari ja existeix", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "ERROR: Les contrasenyes han de coincidir", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 3:
                String finalError = userManager.checkPasswordFormat(user);
                JOptionPane.showMessageDialog(null, finalError, "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "ERROR: El format del email és incorrecte", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "ERROR: El correu ja existeix", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            default: break;
        }
    }
}
