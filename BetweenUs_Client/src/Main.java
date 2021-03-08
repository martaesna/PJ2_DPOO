import model.json.Data;
import model.json.ReadJson;
import model.network.ServerComunication;


import javax.swing.*;

public class Main {
    private static ReadJson rj;
    private static Data data;
    private ServerComunication sc;


    public static void main(String[] args) {
        //Reading the JSON file
        ReadJson Rj = new ReadJson();
        Rj.llegeixJSON();

        ServerComunication sc = new ServerComunication();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}