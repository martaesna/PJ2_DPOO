
import controller.*;
import model.*;
import model.json.Data;
import model.json.JsonReader;
import view.*;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    private static Data data;
    public static void main(String[] args) {
        //RegisterView rv = new RegisterView();
        //RegisterViewController rvc = new RegisterViewController(rv);
        //rv.mainController(rvc);
        SettingView sv = new SettingView();
        SettingViewController svc = new SettingViewController(sv);
        sv.mainController(svc);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                data = JsonReader.llegeixJSON();
            }
        });
    }
}