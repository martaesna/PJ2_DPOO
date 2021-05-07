
import controller.*;
import model.json.Data;
import model.json.JsonReader;
import model.maps.Map;
import model.maps.ReadMap;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Data data;
    private static Map map;
    public static void main(String[] args) {
        addFont();
        /*DeleteGameView dv = new DeleteGameView();
        DeleteGameViewController dgv = new DeleteGameViewController(dv);
        dv.mainController(dgv);*/

       /* RegisterView rv = new RegisterView();
        RegisterViewController rvc = new RegisterViewController(rv);
        rv.mainController(rvc);*/
        /*
        SettingView sv = new SettingView();
        SettingViewController svc = new SettingViewController(sv, null);
        sv.mainController(svc);

         */

        //DeleteGameView cgv = new DeleteGameView();
        //ChargeGameView cgv = new ChargeGameView();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                data = JsonReader.llegeixJSON();
                map = ReadMap.llegeixMapa();
            }
        });
    }

    public static void addFont() {
        try {
            File f = new File("");
            String path = f.getAbsolutePath();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            System.out.println(path);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\BetweenUs\\src\\model\\fonts\\RussoOne-Regular.ttf")));
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }
    }
}