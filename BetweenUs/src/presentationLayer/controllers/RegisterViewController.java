package presentationLayer.controllers;

import businessLayer.entities.user.User;
import businessLayer.UserManager;
import presentationLayer.views.LoginView;
import presentationLayer.views.PlayView;
import presentationLayer.views.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe 'RegisterViewController' ens permet gestionar la vista del RegisterGameView.
 *
 * Els metodes reben informació de la vista, i realitzen les modificacions pertinents per poder registrarse al joc de manera
 * correcta.
 */
public class RegisterViewController implements ActionListener {
    private final RegisterView rv;
    public RegisterViewController(RegisterView rv) {
        this.rv = rv;
    }

    /**
     * Depen del boto que apretem fa una funcionalitat
     * Register Registra un nou usuari
     * Login ens porta a la vista per fel Log in
     * @param e accionador d'events
     */
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

    /**
     * Comprova si el registre s'ha fet correctament, si ho fa entra al Log in, sino ens dona missatge error
     * @param user Usuari que es vol crear
     * @param rv Vista del registre
     */
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
