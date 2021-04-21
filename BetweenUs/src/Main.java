import controller.MainController;
import controller.UserController;
import model.User;
import model.json.Data;
import model.json.JsonReader;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static Data data;
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                data = JsonReader.llegeixJSON();
            }
        });*/

        /**PROVA REGISTRE USUARI**/

        //Definim els controladors
        UserController userController = new UserController();
        MainController mainController = new MainController(userController);

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
    }
}