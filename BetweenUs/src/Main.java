import model.json.Data;
import model.json.JsonReader;
import model.network.Servidor;
import model.network.ServidorDedicat;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

public class Main {
    private static Data data;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                data = JsonReader.llegeixJSON();
            }
        });
    }
}