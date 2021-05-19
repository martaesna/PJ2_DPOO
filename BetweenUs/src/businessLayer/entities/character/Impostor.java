package businessLayer.entities.character;

import businessLayer.entities.maps.Cell;

import java.awt.*;

public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private static final int maxProbability = 100;


    public Impostor(String color, int xCoordinate, int yCoordinate) {
        super(color, xCoordinate, yCoordinate);
    }

    public Impostor(String color) {
        super(color);
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
}
