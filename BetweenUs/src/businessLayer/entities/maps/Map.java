
package businessLayer.entities.maps;

import java.util.LinkedList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map {
    @SerializedName("cellNumber")
    @Expose
    private int cellNumber;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("mapName")
    @Expose
    private String mapName;
    @SerializedName("cells")
    @Expose
    private final LinkedList<Cell> cells = null;

    public int getCellNumber() {
        return cellNumber;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getMapName() {
        return mapName;
    }
    public LinkedList<Cell> getCells() {
        return cells;
    }
    public Cell getCellByName(String roomName) {
        for (Cell cell : cells) {
            if (cell.getRoomName().equals(roomName)) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Mètode que retorna una cella depenent de les coordenades introduides
     * @param coordinates coordenades que es volen traduir a cella
     * @return cella traduida de les coordenades
     */
    public Cell getCellByCoordinates(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        for (Cell cell: this.cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }
}
