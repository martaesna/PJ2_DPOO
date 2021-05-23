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
                PlayViewController pvc = new PlayViewController(pv);
                pv.mainController(pvc);
            } else {
                lv.printError();
            }
        }
    }
}
