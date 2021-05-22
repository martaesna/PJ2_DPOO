package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;
import presentationLayer.views.customComponents.Log;

public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private int startInterval;
    private MapManager mapManager;

    public Impostor(String color, int xCoordinate, int yCoordinate) {
        super(color, xCoordinate, yCoordinate);
    }

    public Impostor(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
    }

    public boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        return probability <= 45;
    }

    public int getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(int startInterval) {
        this.startInterval = startInterval;
    }

    public int getInterval() {
        return randomInterval(maxInterval, minInterval);
    }

    public int getNextImpostorRoom(Impostor impostor) {
        Mobility mobility = impostor.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int optionsCounter = 0;
        int randomPosition = getRandomPosition(counter);
        return chooseRoom(optionsCounter, randomPosition);
    }

    public boolean checkVentilation(Cell cell) {
        return !cell.getAdjacencies().isEmpty();
    }

    public int chooseVentilationRoom(Cell cell) {
        return getRandomPosition(cell.getAdjacencies().size());
    }

    public boolean flipCoin() {
        return (int) (Math.random() * (2) + 1) == 1;
    }

    public void impostorMovement(Impostor impostor) {
        if (impostor.getStartInterval() + impostor.getInterval() == impostor.getTime().getSeconds() && impostor.movement()) {
            if (checkVentilation(impostor.getCell())) {
                if (flipCoin()) {
                    int nextRoom = chooseVentilationRoom(impostor.getCell());
                    String roomName = impostor.getCell().getAdjacencies().get(nextRoom);
                    impostor.setCell(mapManager.getMap().getCellByName(roomName));
                    Log log = new Log(impostor.getColor(), impostor.getCell().getRoomName(), getTime().getSeconds());
                    makeLog(log);
                }
            } else {
                int nextRoom = getNextImpostorRoom(impostor);
                int[] nextCell = impostor.getNextCoordinates(nextRoom);
                impostor.setCell(mapManager.getMap().getCellByCoordinates(nextCell));
                if (impostor.getCell().getType().equals("room") && !impostor.getCell().getRoomName().equals("cafeteria")) {
                    Log log = new Log(impostor.getColor(), impostor.getCell().getRoomName(), getTime().getSeconds());
                    makeLog(log);
                }
            }
        }
    }

    @Override
    public void run() {
        getTime().initCounter();
        while (isRunning()) {
            startInterval = getTime().getSeconds();
            impostorMovement(this);
        }
    }
}
