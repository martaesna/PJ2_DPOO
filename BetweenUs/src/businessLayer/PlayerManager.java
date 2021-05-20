package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;

public class PlayerManager {
    private Character player;

    public PlayerManager(Character character) {
        this.player = character;
    }

    public Character getPlayer() {
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
        if (getPlayer().getCell().getMobility().getLeft() == 0) {
            return false;
        }
        return true;
    }
    public boolean checkRight() {
        if (getPlayer().getCell().getMobility().getRight() == 0) {
            return false;
        }
        return true;
    }
    public boolean checkUp() {
        if (getPlayer().getCell().getMobility().getUp() == 0) {
            return false;
        }
        return true;
    }
    public boolean checkDown() {
        if (getPlayer().getCell().getMobility().getDown() == 0) {
            return false;
        }
        return true;
    }
}
