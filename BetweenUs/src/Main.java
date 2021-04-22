import controller.MainController;
import controller.RegisterViewController;
import controller.UserController;
import model.User;
import model.json.Data;
import model.json.JsonReader;
import view.RegisterView;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    private static Data data;
    public static void main(String[] args) {
        RegisterView rv = new RegisterView();
        RegisterViewController rvc = new RegisterViewController(rv);
        SwingUtilities.invokeLater(new Runnable() {
        //SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                data = JsonReader.llegeixJSON();
                rv.mainController(rvc);
            }
        });

        /******************  PROVA REGISTRE USUARI  ******************/
/*
        //Creem el UserController
        UserController userController = new UserController();

        Scanner myObj = new Scanner(System.in);
        boolean error;

        System.out.println("\nIntrodueix el nom d'usuari:");
        String userName = myObj.nextLine();

        System.out.println("\nIntrodueix el correu:");
        String userMail = myObj.nextLine();

        do {
            System.out.println("\nIntrodueix la contrasenya:");
            String userPassword = myObj.nextLine();

            User user = new User(userName,userMail,userPassword);
            userController.setUser(user);

            error = userController.checkPasswordFormat();
        } while (error);

        do {
            System.out.println("\nConfirma la contrasenya:");
            String confirmedPassword = myObj.nextLine();

            error = userController.unequalPasswords(confirmedPassword);
        } while (error);

        System.out.println("\nBenvingut " + userName + ", t'has registrat correctament!\n");
    */
    }
}