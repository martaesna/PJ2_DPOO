
package businessLayer.entities.maps;

import java.util.LinkedList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * La classe 'Cell' ens serveix per crear els diferents mapes (ja que aquests últims estaran
 * formats per diferents cells)
 *
 * Tenim els atributs que defineixen una cella i els mètodes principals (getters i setters)
 * per poder obtenir i modificar les seves dades
 */
public class Cell {
    // Attributes
    private int numCorpses;

    @SerializedName("x")
    @Expose
    private int x;
    @SerializedName("y")
    @Expose
    private int y;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("roomName")
    @Expose
    private String roomName;
    @SerializedName("mobility")
    @Expose
    private Mobility mobility;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("adjacencies")
    @Expose
    private final LinkedList<String> adjacencies = null;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }
    public String getRoomName() {
        return roomName;
    }
    public Mobility getMobility() {
        return mobility;
    }
    public String getColor() {
        return color;
    }
    public LinkedList<String> getAdjacencies() {
        return adjacencies;
    }
    public int getNumCorpses() {
        return numCorpses;
    }
    public void setNumCorpses(int numCorpses) {
        this.numCorpses = numCorpses;
    }
}
