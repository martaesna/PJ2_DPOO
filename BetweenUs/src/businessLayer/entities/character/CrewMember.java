package businessLayer.entities.character;

import businessLayer.MapManager;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;


/**
 * La classe 'CrewMember' hereta les propietats i funcions de la classe 'Character' i
 * afageix atributs i mètodes específics de la classe
 * Subclasse que ens permet diferenciar entre tipus de 'Character' determinats.
 *
 * Els mèttodes implementats gestionen el moviment d'aquest tipus de jugador
 */
public class CrewMember extends Character{
    // Attributes
    private static final int minInterval = 5;
    private static final int maxInterval = 15;
    private int previousRoom;
    private MapManager mapManager;
    private int startInterval;

    // Parametrized constructor
    public CrewMember(String color, int xCoordinate, int yCoordinate, int previousRoom) {
        super(color, xCoordinate, yCoordinate);
        this.previousRoom = previousRoom;
    }

    // Parametrized constructor
    public CrewMember(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
    }

    /**
     * Mètode que retorna si el crewmember es mourà o no
     * @return si es mourà o no
     */
    public synchronized boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        return probability <= 55;
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

    /**
     * Mètode que actualitza la sala anterior d'on ve el crewmember
     * @param nextRoom enter que indica la sala cap on es mourà
     * @return enter que indica la sala d'on ve
     */
    public synchronized int selectPreviousRoom(int nextRoom) {
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

    /**
     * Mètode que mou al crewmember si aquest decideix moure's
     */
    public synchronized void crewMemberMovement() {
        if (startInterval == getIntervalTime().getSeconds()) {
            if (movement()) {
                int nextRoom = getNextCrewMemberRoom(this);
                setPreviousRoom(selectPreviousRoom(nextRoom));
                int[] nextCell = getNextCoordinates(nextRoom);
                setCell(getCellByCoordinates(nextCell));
                setCanLog(true);
            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
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
        return null;
    }

    /**
     * Mètode que obté una posició random per al crewmember amb penalització de la sala on acaba d'estar
     * @param counter nombre de sales on el crewmember es vol moure
     * @param previousRoom sala previa d'on ve el crewmember
     * @return posició on s'haurà de moure el crewmember
     */
    public synchronized int getCrewMemberRandomPosition(int counter, int previousRoom) {
        int position = (int) (Math.random() * (counter));
        if (Math.abs(position-previousRoom) == 2) {
            return (int) (Math.random() * (counter));
        }
        return position;
    }

    /**
     * Mètode que retorna quina serà la pròxima sala on es mourà el crewmember
     * @param crewMember el crewmember que es mourà
     * @return enter amb la pròxima sala
     */
    public synchronized int getNextCrewMemberRoom(CrewMember crewMember) {
        Mobility mobility = crewMember.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int randomPosition = getCrewMemberRandomPosition(counter, crewMember.getPreviousRoom());
        return chooseRoom(randomPosition);
    }

    /**
     * Thread on el crewmember es mourà quan passi l'interval i vulgui moure's
     */
    @Override
    public void run() {
        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();
        while (isRunning()) {
            try {
                crewMemberMovement();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}