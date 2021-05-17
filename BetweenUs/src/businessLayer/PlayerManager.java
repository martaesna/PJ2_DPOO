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

    public void setPlayer(Character player) {
        this.player = player;
    }


    public boolean checkClasification() {




        return true;
    }

    public boolean checkLogs() {


        return true;
    }

    public boolean checkLeft(Mobility mobility) {
        if (mobility.getLeft() == 0) {
            return false;
        }
        return true;
    }
    public boolean checkRight(Mobility mobility) {
        if (mobility.getRight() == 0) {
            return false;
        }
        return true;
    }
    public boolean checkUp(Mobility mobility) {
        if (mobility.getUp() == 0) {
            return false;
        }
        return true;
    }
    public boolean checkDown(Mobility mobility) {
        if (mobility.getDown() == 0) {
            return false;
        }
        return true;
    }



}
