
import controller.*;
import model.*;
import model.json.Data;
import model.json.JsonReader;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Data data;
    public static void main(String[] args) {
        /*try {
            File f = new File("");
            String path = f.getAbsolutePath();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\BetweenUs\\src\\model\\fonts\\RussoOne-Regular.ttf")));
            //ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/marta/Desktop/BETWEEN US/Projecte V2/BetweenUs/src/model/fonts/RussoOne-Regular.ttf")));
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }*/
        RegisterView rv = new RegisterView();
        RegisterViewController rvc = new RegisterViewController(rv);
        rv.mainController(rvc);
        /*SettingView sv = new SettingView();
        SettingViewController svc = new SettingViewController(sv, null);
        sv.mainController(svc);

        //DeleteGameView cgv = new DeleteGameView();
        ChargeGameView cgv = new ChargeGameView();*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                data = JsonReader.llegeixJSON();
            }
        });
    }
}