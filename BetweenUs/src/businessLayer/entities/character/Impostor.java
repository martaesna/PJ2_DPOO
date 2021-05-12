package businessLayer.entities.character;

import java.awt.*;

public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private static final int maxProbability = 100;

    public Impostor(Color color, String role, boolean npc) {
        super(color, role, npc);
    }

    @Override
    public void move() {

    }

    public void chooseVentilation() {


    }

    public int randomInterval() {
        int interval = (int)(Math.random()*(maxInterval-minInterval+1)+minInterval);
        return interval;
    }

    public boolean movement() {
        int probability = (int)(Math.random()*(maxProbability+1));
        if (probability <= 45) {
            return true;
        } else {
            return false;
        }
    }

    public int randomRoom(int numRooms) {
        return ((int)(Math.random()*(numRooms))) + 1;
    }
}
