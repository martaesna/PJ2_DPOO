package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;

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

    @Override
    public void setNpcManager(NpcManager npcManager) {
        this.npcManager = npcManager;
    }

    /**
     * Mètode que decideix si l'impostor es mourà o no
     * @return booleà amb si es mourà o no
     */
    public boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        return probability <= 45;
    }

    public int getInterval() {
        return randomInterval(maxInterval, minInterval);
    }

    /**
     * Mètode que retorna quina serà la pròxima habitació on es mogui l'impostor
     * @param impostor impostor que es mourà
     * @return enter amb la pròxima sala on es mourà l'impostor
     */
    public int getNextImpostorRoom(Impostor impostor) {
        Mobility mobility = impostor.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int randomPosition = getRandomPosition(counter);
        return chooseRoom(randomPosition);
    }

    /**
     * Mètode que comprova si hi ha clavagueres en una cella
     * @param cell cella a comprovar
     * @return si té clavagueres o no
     */
    public boolean checkVentilation(Cell cell) {
        return !cell.getAdjacencies().isEmpty();
    }

    /**
     * Mètode que tria a quina clavaguera vol introduirse l'impostor
     * @param cell cella on està
     * @return posició on anirà
     */
    public int chooseVentilationRoom(Cell cell) {
        return getRandomPosition(cell.getAdjacencies().size());
    }

    /**
     * Mètode que decideix amb un 50% de probabilitats
     * @return booleà amb si o no
     */
    public boolean flipCoin() {
        return (int) (Math.random() * (2) + 1) == 1;
    }

    /**
     * Mètode que retorna una cella depenent de les coordenades introduides
     * @param coordinates coordenades que es volen traduir a cella
     * @return cella traduida de les coordenades
     */
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

    /**
     * Mètode que actualitza el moviment de l'impostor
     * @param impostor impostor que es mourà
     * @throws InterruptedException excepció que es llançarà si l'sleep deixa de funcionar
     */
    public void impostorMovement(Impostor impostor) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);

        if (startInterval == getIntervalTime().getSeconds()) {
            if (impostor.movement()) {
                if (checkVentilation(impostor.getCell()) && npcManager.getNumCrewMembersCell(impostor.getCell()) == 0 && flipCoin()) {
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

    /**
     * Thread que fa constantment el moviment de l'impostor
     */
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


