package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Mobility;
import presentationLayer.views.customComponents.Log;

import java.util.LinkedList;

public class CrewMember extends Character{
    private static final int minInterval = 5;
    private static final int maxInterval = 15;
    private int previousRoom;
    private int startInterval;
    private MapManager mapManager;

    public CrewMember(String color, int xCoordinate, int yCoordinate, int previousRoom) {
        super(color, xCoordinate, yCoordinate);
        this.previousRoom = previousRoom;
    }

    public CrewMember(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
    }

    public boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        return probability <= 55;
    }
    public int getStartInterval() {
        return startInterval;
    }

    public int getInterval() {
        return randomInterval(maxInterval, minInterval);
    }

    public int getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(int previousRoom) {
        this.previousRoom = previousRoom;
    }

    public void crewMemberMovement(CrewMember crewMember) {
        if (crewMember.getStartInterval() + crewMember.getInterval() == crewMember.getTime().getSeconds() && crewMember.movement()) {
            int nextRoom = getNextCrewMemberRoom(crewMember);
            crewMember.setPreviousRoom(nextRoom);
            int[] nextCell = crewMember.getNextCoordinates(nextRoom);
            crewMember.setCell(mapManager.getMap().getCellByCoordinates(nextCell));
            if (crewMember.getCell().getType().equals("room") && !crewMember.getCell().getRoomName().equals("cafeteria")) {
                Log log = new Log(crewMember.getColor(), crewMember.getCell().getRoomName(), getTime().getSeconds());
                makeLog(log);
            }
        }
    }

    public int getCrewMemberRandomPosition(int counter, int previousRoom) {
        int min = 1;
        int position = (int) (Math.random() * (counter - min + 1) + min);
        if (Math.abs(position-previousRoom) == 2) {
            return (int) (Math.random() * (counter - min + 1) + min);
        }
        return position;
    }

    public int getNextCrewMemberRoom(CrewMember crewMember) {
        Mobility mobility = crewMember.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int optionsCounter = 0;
        int randomPosition = getCrewMemberRandomPosition(counter, crewMember.getPreviousRoom());
        return chooseRoom(optionsCounter, randomPosition);
    }

    @Override
    public void run() {
        getTime().initCounter();
        while (this.isRunning()) {
            startInterval = getTime().getSeconds();
            crewMemberMovement(this);
        }
    }
}
