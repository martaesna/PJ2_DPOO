package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;

import java.util.concurrent.TimeUnit;

/**
 * La classe 'Impostor' hereta les propietats i funcions de la classe 'Character' i
 * afageix atributs i mètodes específics de la classe
 * Subclasse que ens permet diferenciar entre tipus de 'Character' determinats.
 *
 * Els mèttodes implementats gestionen el moviment d'aquest tipus de jugador
 */
public class Impostor extends Character{
    // Attributes
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private int startInterval;
    private MapManager mapManager;
    private NpcManager npcManager;
    private Boolean canKill;
    private Time killingPeriod;

    // Parametrized constructor
    public Impostor(String color, int xCoordinate, int yCoordinate) {
        super(color, xCoordinate, yCoordinate);
        killingPeriod = new Time();
    }

    // Parametrized constructor
    public Impostor(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
        killingPeriod = new Time();
    }

    public Boolean isCanKill() {
        return canKill;
    }

    public void setCanKill(Boolean canKill) {
        this.canKill = canKill;
    }

    @Override
    public void setNpcManager(NpcManager npcManager) {
        this.npcManager = npcManager;
    }

    /**
     * Mètode que decideix si l'impostor es mourà o no
     * @return booleà amb si es mourà o no
     */
    public synchronized boolean movement() {
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
    public synchronized int getNextImpostorRoom(Impostor impostor) {
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
    public synchronized boolean checkVentilation(Cell cell) {
        return !cell.getAdjacencies().isEmpty();
    }

    /**
     * Mètode que tria a quina clavaguera vol introduirse l'impostor
     * @param cell cella on està
     * @return posició on anirà
     */
    public synchronized int chooseVentilationRoom(Cell cell) {
        return getRandomPosition(cell.getAdjacencies().size());
    }

    /**
     * Mètode que decideix amb un 50% de probabilitats
     * @return booleà amb si o no
     */
    public synchronized boolean flipCoin() {
        return (int) (Math.random() * (2) + 1) == 1;
    }

    /**
     * Mètode que retorna una cella depenent de les coordenades introduides
     * @param coordinates coordenades que es volen traduir a cella
     * @return cella traduida de les coordenades
     */
    public synchronized Cell getCellByCoordinates(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        for (Cell cell: mapManager.getMap().getCells()) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        System.out.println("Retornem null?");
        return null;
    }

    /**
     * Mètode que actualitza el moviment de l'impostor
     * @param impostor impostor que es mourà
     * @throws InterruptedException excepció que es llançarà si l'sleep deixa de funcionar
     */
    public synchronized void impostorMovement(Impostor impostor) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);

        if (startInterval == getIntervalTime().getSeconds()) {
            if (impostor.movement()) {
                if (!ventilationMovement(impostor)) {
                    int nextRoom = getNextImpostorRoom(impostor);
                    int[] nextCell = impostor.getNextCoordinates(nextRoom);
                    impostor.setCell(getCellByCoordinates(nextCell));
                    impostor.setCanLog(true);
                }
            } else {
                System.out.println("");
            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
    }

    public synchronized boolean ventilationMovement(Impostor impostor) {
        if (checkVentilation(impostor.getCell())) {
            if (npcManager.getNumCrewMembersCell(impostor.getCell()) == 0 && flipCoin()) {
                int nextRoom = chooseVentilationRoom(impostor.getCell());
                String roomName = impostor.getCell().getAdjacencies().get(nextRoom);
                int numCrewMembers = npcManager.getNumCrewMembersCell(mapManager.getMap().getCellByName(roomName));
                if (numCrewMembers == 0 || (numCrewMembers == 1 && impostor.canKill)) {
                    impostor.setCell(mapManager.getMap().getCellByName(roomName));
                    impostor.setCanLog(true);
                    return true;
                }
            }
        }
        return false;
    }

    public void afterKillMovement(Impostor impostor) {
        int nextRoom = getNextImpostorRoom(impostor);
        int[] nextCell = impostor.getNextCoordinates(nextRoom);
        impostor.setCell(getCellByCoordinates(nextCell));
        impostor.setCanLog(true);
    }

    public Time getPeriodTime() {
        return killingPeriod;
    }

    public synchronized boolean checkKillingPeriod(Impostor impostor) {
        if (impostor.getPeriodTime().getSeconds() > 25) {
            impostor.setCanKill(true);
            return true;
        }
        return false;
    }

    /**
     * Thread que fa constantment el moviment de l'impostor
     */
    @Override
    public void run() {
        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();
        killingPeriod.initCounter();
        killingPeriod.setSeconds(26);
        while (isRunning()) {
            try {
                impostorMovement(this);
                if (checkKillingPeriod(this) || canKill == null) {
                    if (npcManager.eliminateCrewMember(mapManager, this)) {
                        canKill = false;
                        afterKillMovement(this);
                    }
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


