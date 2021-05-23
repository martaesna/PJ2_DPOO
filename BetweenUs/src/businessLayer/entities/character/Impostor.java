package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;
import presentationLayer.views.customComponents.Log;

import java.util.concurrent.TimeUnit;

public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private int startInterval;
    private MapManager mapManager;
    private NpcManager npcManager;

    public Impostor(String color, int xCoordinate, int yCoordinate) {
        super(color, xCoordinate, yCoordinate);
    }

    public Impostor(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
    }

    public NpcManager getNpcManager() {
        return npcManager;
    }

    @Override
    public void setNpcManager(NpcManager npcManager) {
        this.npcManager = npcManager;
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

    public void impostorMovement(Impostor impostor) throws InterruptedException {
        System.out.println("COLOR IMPOSTOR: "+ impostor.getColor());
        TimeUnit.MILLISECONDS.sleep(500);
        int[] room = new int[2];
        room[0] = impostor.getCell().getX();
        room[1] = impostor.getCell().getY();

        if (startInterval == getIntervalTime().getSeconds()) {
            if (impostor.movement()) {
                if (checkVentilation(impostor.getCell()) && flipCoin()) {
                    int nextRoom = chooseVentilationRoom(impostor.getCell());
                    String roomName = impostor.getCell().getAdjacencies().get(nextRoom);
                    impostor.setCell(mapManager.getMap().getCellByName(roomName));
                    //Log log = new Log(impostor.getColor(), impostor.getCell().getRoomName(), getTotalTime().getSeconds());
                    //makeLog(log);
                } else {
                    int nextRoom = getNextImpostorRoom(impostor);
                    int[] nextCell = impostor.getNextCoordinates(nextRoom);
                    impostor.setCell(getCellByCoordinates(nextCell));
                    /*  if (impostor.getCell().getType().equals("room") && !impostor.getCell().getRoomName().equals("cafeteria")) {
                        Log log = new Log(impostor.getColor(), impostor.getCell().getRoomName(), getTotalTime().getSeconds());
                        //makeLog(log);
                    }*/
                }
            } else {
                System.out.println("");
            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
    }

    /*public boolean checkAloneCrewMember(String roomName, NpcManager npcManager, MapManager mapManager, int numPlayers) {
        if (numPlayers == 1 || numPlayers == 2) {
            for (Character player: npcManager.getPlayers()) {
                if (player.getCell() == mapManager.getMap().getCellByName(roomName) && (player instanceof CrewMember || player instanceof Player)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eliminateCrewMember(Cell cell, NpcManager npcManager, MapManager mapManager) {
        int numPlayers = npcManager.getCellPlayers(mapManager.getMap().getCellByName(cell.getRoomName()));
        if (checkAloneCrewMember(cell.getRoomName(), npcManager, mapManager, numPlayers)) {
            cell.setNumCorpses(cell.getNumCorpses()+1);
            return true;
        }
        return false;
    }

    public int getPosCrewMember(Cell cell, NpcManager npcManager) {
        for (int i = 0; i < npcManager.getPlayers().size(); i++) {
            if (npcManager.getPlayers().get(i).getCell() == cell && (npcManager.getPlayers().get(i) instanceof CrewMember || npcManager.getPlayers().get(i) instanceof Player)){
                return i;
            }
        }
        return 0;
    }*/

    @Override
    public void run() {
        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();
        while (isRunning()) {
            try {
                impostorMovement(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


/*package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;
import presentationLayer.views.customComponents.Log;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private int startInterval;
    private MapManager mapManager;
    private Time killCooldown;
    private NpcManager npcManager;

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

    public NpcManager getNpcManager() {
        return npcManager;
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

    public void impostorMovement(Impostor impostor) throws InterruptedException {
        if (startInterval == getIntervalTime().getSeconds()+1) {
            if (impostor.movement()) {
                if (checkVentilation(impostor.getCell()) && flipCoin() && npcManager.getCellPlayers(impostor.getCell()) == 0) {
                    int nextRoom = chooseVentilationRoom(impostor.getCell());
                    String roomName = impostor.getCell().getAdjacencies().get(nextRoom);
                    if (ventilationConditions(roomName,npcManager, mapManager)) {
                        impostor.setCell(mapManager.getMap().getCellByName(roomName));
                        //Log log = new Log(impostor.getColor(), impostor.getCell().getRoomName(), getTotalTime().getSeconds());
                        //makeLog(log);
                    }
                } else {
                    int nextRoom = getNextImpostorRoom(impostor);
                    int[] nextCell = impostor.getNextCoordinates(nextRoom);
                    impostor.setCell(getCellByCoordinates(nextCell));

                    if (eliminateCrewMember(getCellByCoordinates(nextCell), npcManager, mapManager)) {
                        int posCrewMember = getPosCrewMember(getCellByCoordinates(nextCell), npcManager);
                        npcManager.getPlayers().remove(posCrewMember);
                    }

                   if (impostor.getCell().getType().equals("room") && !impostor.getCell().getRoomName().equals("cafeteria")) {
                        Log log = new Log(impostor.getColor(), impostor.getCell().getRoomName(), getTotalTime().getSeconds());
                        //makeLog(log);
                    }
                }
            } else {
                System.out.println("");
            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
    }

    public boolean ventilationConditions(String roomName, NpcManager npcManager, MapManager mapManager) {
        int numPlayers = npcManager.getCellPlayers(mapManager.getMap().getCellByName(roomName));
        if  (numPlayers == 0 || checkAloneCrewMember(roomName, npcManager, mapManager, numPlayers)){
            return true;
        }
        return false;
    }

    public boolean checkAloneCrewMember(String roomName, NpcManager npcManager, MapManager mapManager, int numPlayers) {
        if (numPlayers == 1) {
            for (Character player: npcManager.getPlayers()) {
                if (player.getCell() == mapManager.getMap().getCellByName(roomName) && player instanceof CrewMember) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eliminateCrewMember(Cell cell, NpcManager npcManager, MapManager mapManager) {
        int numPlayers = npcManager.getCellPlayers(mapManager.getMap().getCellByName(cell.getRoomName()));
        if (checkAloneCrewMember(cell.getRoomName(), npcManager, mapManager, numPlayers)) {
            cell.setNumCorpses(cell.getNumCorpses()+1);
            return true;
        }
        return false;
    }

    public int getPosCrewMember(Cell cell, NpcManager npcManager) {
        for (int i = 0; i < npcManager.getPlayers().size(); i++) {
            if (npcManager.getPlayers().get(i).getCell() == cell && npcManager.getPlayers().get(i) instanceof CrewMember){
                return i;
            }
        }
        return 0;
    }

    @Override
    public void run() {
        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();

        while (isRunning()) {
            try {
                TimeUnit.SECONDS.sleep(startInterval-1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                impostorMovement(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }
    */

