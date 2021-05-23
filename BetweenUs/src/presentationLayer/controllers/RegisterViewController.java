package presentationLayer.controllers;

import businessLayer.entities.user.User;
import businessLayer.UserManager;
import presentationLayer.views.LoginView;
import presentationLayer.views.PlayView;
import presentationLayer.views.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterViewController implements ActionListener {
    private final RegisterView rv;
    public RegisterViewController(RegisterView rv) {
        this.rv = rv;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) { //cuando apretamos el boton
            User user = new User(rv.getUsername(), rv.getEmail(), rv.getPassword(), rv.getRepeatPassword());
            caseRegister(user, rv);
        }
        if (e.getActionCommand().equals("Login")) { //cuando apretamos el boton
            rv.setVisible(false);
            LoginView lv = new LoginView();
            LoginViewController lvc = new LoginViewController(lv);
            lv.mainController(lvc);
        }
    }

    public void caseRegister(User user, RegisterView rv) {
        UserManager userManager = new UserManager();
        int checked = userManager.checkRegister(user);
        if (checked == 0) {
            userManager.registerUser(user);
            rv.setVisible(false);
            LoginView lv = new LoginView();
            LoginViewController lvc = new LoginViewController(lv);
            lv.mainController(lvc);
        } else {
            String finalError = userManager.checkPasswordFormat(user);
            rv.printRegisterErrors(checked,finalError);
        }
    }
}
