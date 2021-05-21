package businessLayer.entities.character;

import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;

import java.awt.*;

public class CrewMember extends Character{
    private static final int minInterval = 5;
    private static final int maxInterval = 15;
    private static final int maxProbability = 100;
    private int previousRoom;
    private boolean go;
    private Time time;
    private int interval;
    private int startInterval;
    private NpcManager npcManager;
    private boolean isRunning;

    public CrewMember(String color, int xCoordinate, int yCoordinate, int previousRoom) {
        super(color, xCoordinate, yCoordinate);
        this.previousRoom = previousRoom;
        time = new Time();
    }

    public CrewMember(String color) {
        super(color);
    }

    public int randomInterval() {
        interval = (int)(Math.random()*(maxInterval-minInterval+1)+minInterval);
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

    public int getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(int previousRoom) {
        this.previousRoom = previousRoom;
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
            npcManager.crewMemberMovement(this);
        }
    }
}
