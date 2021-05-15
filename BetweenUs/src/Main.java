
import presentationLayer.controllers.MapController;
import businessLayer.entities.json.Data;
import businessLayer.JsonReader;
import businessLayer.entities.maps.Map;
import businessLayer.MapManager;
import presentationLayer.controllers.NewGameController;
import presentationLayer.views.NewGameView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private static Data data;
    private static Map map;
    private MapController mc;

    public static void main(String[] args) {
        addFont();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                data = JsonReader.llegeixJSON();
                map = MapManager.llegeixMapa();
            }
        });

        NewGameView ngv = new NewGameView();
        NewGameController gc = new NewGameController(ngv);
        ngv.mainController(gc);
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