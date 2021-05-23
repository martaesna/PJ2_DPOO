package businessLayer;

import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Cell;

public class PlayerManager {
    private final Player player;

    public PlayerManager(Player character) {
        this.player = character;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean checkClasification() {
        return true;
    }

    public boolean checkLogs() {
        return true;
    }

    public void moveUserPlayer(Cell cell) {
        getPlayer().setCell(cell);
    }

    public int[] nextCell(int nextRoom) {
        return getPlayer().getNextCoordinates(nextRoom);
    }

    public boolean checkLeft() {
        return getPlayer().getCell().getMobility().getLeft() != 0;
    }
    public boolean checkRight() {
        return getPlayer().getCell().getMobility().getRight() != 0;
    }
    public boolean checkUp() {
        return getPlayer().getCell().getMobility().getUp() != 0;
    }
    public boolean checkDown() {
        return getPlayer().getCell().getMobility().getDown() != 0;
    }
}
