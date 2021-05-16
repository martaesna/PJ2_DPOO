
package businessLayer.entities.maps;

import java.util.LinkedList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cell {
    private int numPlayers;

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
    private LinkedList<String> adjacencies = null;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Mobility getMobility() {
        return mobility;
    }

    public void setMobility(Mobility mobility) {
        this.mobility = mobility;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String image) {
        this.color = color;
    }

    public LinkedList<String> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(LinkedList<String> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
