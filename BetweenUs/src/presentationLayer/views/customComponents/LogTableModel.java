package presentationLayer.views.customComponents;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class LogTableModel extends AbstractTableModel {

    private final String[] header = {"CREWMATE", "ROOM", "INSTANT"};
    private final LinkedList<Log> data;

    public LogTableModel(LinkedList<Log> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    public void addLog(Log log) {
        int row = data.size();
        System.out.println(row);
        System.out.println(log);
        data.add(log);
        fireTableRowsInserted(row, row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Log log = data.get(rowIndex);
        if (log == null) {
            return null;
        }
        switch (columnIndex) {
            case 0:
                return log.getName();
            case 1:
                return log.getRoom();
            case 2:
                return log.getInstant();
            default:
                return null;
        }
    }
}
