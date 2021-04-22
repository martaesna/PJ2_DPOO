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
    }
}