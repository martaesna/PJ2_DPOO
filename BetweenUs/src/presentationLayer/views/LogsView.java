package presentationLayer.views;

import presentationLayer.controllers.MapController;
import presentationLayer.views.customComponents.Log;
import presentationLayer.views.customComponents.LogTableModel;
import javax.swing.*;
import java.util.LinkedList;

public class LogsView extends JFrame{
    private LinkedList<Log> logs;

    /**
     * mostra la vista del Logs
     */
    public LogsView(LinkedList<Log> logs) {
        this.logs = logs;

        setTitle("Logs"); // titol
        setSize(700, 400); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        /*falta logica per omplir la barra de Logs
        Log log = new Log("green", "cafeteria", 34);
        logs.add(log);*/

        LogTableModel model = new LogTableModel(logs);
        JTable logsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(logsTable);

        setContentPane(scrollPane);
        setVisible(true);
    }
}
