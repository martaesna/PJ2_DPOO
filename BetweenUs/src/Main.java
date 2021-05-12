
import presentationLayer.controllers.MapCotroller;
import businessLayer.entities.json.Data;
import businessLayer.JsonReader;
import businessLayer.entities.maps.Map;
import businessLayer.MapManager;
import presentationLayer.views.MapView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private static Data data;
    private static Map map;
    private MapCotroller mc;

    public static void main(String[] args) {
        addFont();

        //RegisterView rv = new RegisterView();
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
                map = MapManager.llegeixMapa();

                MapView mv = new MapView(map);

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