import model.network.Servidor;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Servidor server = new Servidor();
        server.startServer();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}