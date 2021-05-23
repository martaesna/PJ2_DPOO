package presentationLayer.controllers;

import businessLayer.UserManager;
import presentationLayer.views.LoginView;
import presentationLayer.views.PlayView;
import presentationLayer.views.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {
    private final LoginView lv;
    public LoginViewController(LoginView lv) {
        this.lv = lv;
    }

    /**
     * Depen del boto que apretem fa una funcionalitat
     * Register Ens porta a la vista per registrarse
     * Login comprova el usuari, en cas afiramatiu entra al joc, en cas negatiu surt missatge error
     * @param e escoltador d'accions
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) { //cuando apretamos el boton
            lv.setVisible(false);
            RegisterView rv = new RegisterView();
            RegisterViewController rvc = new RegisterViewController(rv);
            rv.mainController(rvc);
        }
        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            UserManager userManager = new UserManager();
            if (userManager.loginUser(lv.getUsername(),lv.getPassword())) {
                lv.setVisible(false);
                PlayView pv = new PlayView();
                PlayViewController pvc = new PlayViewController(pv, lv.getUsername());
                pv.mainController(pvc);
            } else {
                lv.printError();
            }
        }
    }
}
