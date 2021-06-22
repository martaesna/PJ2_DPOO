import presentationLayer.controllers.LoginViewController;
import businessLayer.entities.json.Data;
import persitanceLayer.JsonReader;
import presentationLayer.views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private static Data data;

    public static void main(String[] args) {
        addFont();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                data = JsonReader.llegeixJSON();
                LoginView lv = new LoginView();
                LoginViewController lvc = new LoginViewController(lv);
                lv.mainController(lvc);
            }
        });



    }

    public static void addFont() {
        try {
            File f = new File("");
            String path = f.getAbsolutePath();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            System.out.println(path);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path + "/BetweenUs/src/fonts/RussoOne-Regular.ttf")));
        } catch (IOException |FontFormatException e) {
            System.out.println("No s'ha trobat la font");
        }
    }
}