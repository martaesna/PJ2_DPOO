package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;
import presentationLayer.views.customComponents.Log;

import java.util.concurrent.TimeUnit;


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

    public int selectPreviousRoom(int nextRoom) {
        switch (nextRoom) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            case 3:
                return 1;
        }
        return -1;
    }

    public void crewMemberMovement() {
        if (movement()) {
            int nextRoom = getNextCrewMemberRoom(this);
            setPreviousRoom(selectPreviousRoom(nextRoom));
            int[] nextCell = getNextCoordinates(nextRoom);
            setCell(getCellByCoordinates(nextCell));
            /*if (crewMember.getCell().getType().equals("room") && !crewMember.getCell().getRoomName().equals("cafeteria")) {
                   Log log = new Log(crewMember.getColor(), crewMember.getCell().getRoomName(), getTotalTime().getSeconds());
                   // makeLog(log);
               }*/
        }
    }

    public Cell getCellByCoordinates(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        for (Cell cell: mapManager.getMap().getCells()) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
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
        System.out.println("comencem!");
        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();
        while (isRunning()) {
            if (startInterval == getIntervalTime().getSeconds()) {
                crewMemberMovement();
                startInterval = getInterval();
                getIntervalTime().resetCounter();
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
