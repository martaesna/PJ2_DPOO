package controller;

import model.User;
import model.UserManager;
import model.database.SQLoperations;
import view.LoginView;
import view.RegisterView;
import view.SettingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.database.SQLoperations.borraUsuari;

public class SettingViewController implements ActionListener {

    private SettingView sv;


    public SettingViewController(SettingView sv) {
        this.sv = sv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Logout")) { //cuando apretamos el boton
            int confirmado = JOptionPane.showConfirmDialog(null,"Seguro que quieres hacer log out?");
            if(JOptionPane.OK_OPTION == confirmado){
                sv.setVisible(false);
                LoginView lv = new LoginView();
                LoginViewController lvc = new LoginViewController(lv);
                lv.mainController(lvc);
            }

        }
        if (e.getActionCommand().equals("Delete")) { //cuando apretamos el boton
            int confirmado = JOptionPane.showConfirmDialog(null,"Seguro que quieres borrar la Cuenta?");
            if(JOptionPane.OK_OPTION == confirmado){
                sv.setVisible(false);
                System.out.println("Arar Borrem conta");
                //borraUsuari("pepe");//aqui va el nom del usuari de la persona
                RegisterView rv = new RegisterView();
                RegisterViewController rvc = new RegisterViewController(rv);
                rv.mainController(rvc);

            }

        }
        if (e.getActionCommand().equals("Config")) { //cuando apretamos el boton
            RegisterView rv = new RegisterView();
            RegisterViewController rvc = new RegisterViewController(rv);
            rv.mainController(rvc);
            sv.setVisible(false);
        }
    }
}
