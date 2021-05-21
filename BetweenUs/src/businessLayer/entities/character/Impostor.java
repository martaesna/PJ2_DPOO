package businessLayer.entities.character;

import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;

import java.awt.*;

public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private static final int maxProbability = 100;
    private Time time;
    private int interval;
    private int startInterval;
    private boolean go;
    private NpcManager npcManager;
    private boolean isRunning;

    public Impostor(String color, int xCoordinate, int yCoordinate) {
        super(color, xCoordinate, yCoordinate);
        time = new Time();
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

    public int getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(int startInterval) {
        this.startInterval = startInterval;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setNpcManager(NpcManager npcManager) {
        this.npcManager = npcManager;
    }

    public void startThread() {
        isRunning = true;
        this.start();
    }

    public void stopThread() {
        isRunning = false;
        this.interrupt();
    }

    @Override
    public void run() {
        time.initCounter();
        while (isRunning) {
            startInterval = time.getSeconds();
            npcManager.impostorMovement(this, npcManager);
        }
    }
}
