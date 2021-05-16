package businessLayer.entities.character;

import businessLayer.entities.maps.Cell;

import java.awt.*;

public class CrewMember extends Character{
    private static final int minInterval = 5;
    private static final int maxInterval = 15;
    private static final int maxProbability = 100;
    private int previousRoom;

    public CrewMember(Color color, String role, boolean npc, Cell cell) {
        super(color, role, npc, cell);
    }


    public int randomInterval() {
        int interval = (int)(Math.random()*(maxInterval-minInterval+1)+minInterval);
        return interval;
    }

    public boolean movement() {
        int probability = (int)(Math.random()*(maxProbability+1));
        if (probability <= 55) {
            return true;
        } else {
            return false;
        }
    }

    public int randomRoom(int numRooms) {
        return ((int)(Math.random()*(numRooms))) + 1;
    }

    public int getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(int previousRoom) {
        this.previousRoom = previousRoom;
    }
}
