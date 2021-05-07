
package model.maps;

import java.util.LinkedList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import model.maps.Cell;

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
    private LinkedList<Cell> cells = null;

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }

    public void setCells(LinkedList<Cell> cells) {
        this.cells = cells;
    }

}
