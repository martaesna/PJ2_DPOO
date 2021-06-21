package businessLayer.entities.character;

import businessLayer.LogManager;
import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;
import presentationLayer.views.customComponents.Log;

public abstract class Character extends Thread{
    private Time totalTime;
    private Time intervalTime;
    private final int[] moveOptions = new int[4];
    private String color;
    private Cell cell;
    private int xCoordinate;
    private int yCoordinate;
    private boolean isRunning;

    public Character (String color, int xCoordinate, int yCoordinate) {
        this.color = color;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Character(String color) {
        this.color = color;
        totalTime = new Time();
        intervalTime = new Time();
    }

    /**
     * Mètode que tria la pròxima habitació on es mourà el jugador
     * @param randomPosition sala on es mourà generada aleatoriament
     * @return enter que indica quina serà la pròxima habitació
     */
    public int chooseRoom(int randomPosition) {
        System.out.println("hola");
        int optionsCounter = -1;
        for (int i = 0; i < 4; i++) {
            if (moveOptions[i] == 1) {
                optionsCounter++;
                if (optionsCounter == randomPosition) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * Mètode per fer un Log
     * @param log classe log amb la informació per registar-lo
     */
    public void makeLog(Log log) {
        LogManager logManager = new LogManager(log);
    }

    /**
     * Mètode que comprova si el jugador es pot moure a la esquerra
     * @param mobility mobilitat del jugador
     * @return booleà amb si es pot moure o no
     */
    public boolean checkLeft(Mobility mobility) {
        return mobility.getLeft() != 0;
    }

    /**
     * Mètode que comprova si el jugador es pot moure a la dreta
     * @param mobility mobilitat del jugador
     * @return booleà amb si es pot moure o no
     */
    public boolean checkRight(Mobility mobility) {
        return mobility.getRight() != 0;
    }

    /**
     * Mètode que comprova si el jugador es pot moure a dalt
     * @param mobility mobilitat del jugador
     * @return booleà amb si es pot moure o no
     */
    public boolean checkUp(Mobility mobility) {
        return mobility.getUp() != 0;
    }

    /**
     * Mètode que comprova si el jugador es pot moure a baix
     * @param mobility mobilitat del jugador
     * @return booleà amb si es pot moure o no
     */
    public boolean checkDown(Mobility mobility) {
        return mobility.getDown() != 0;
    }

    /**
     * Mètode que calcula a quantes sales es pot moure el jugador
     * @param mobility mobilitat del jugador
     * @return enter amb el nombre de sales a les qual es pot moure
     */
    public int setMoveOptions(Mobility mobility) {
        int counter = 0;
        if (checkLeft(mobility)) {
            moveOptions[0] = 1;
            counter++;
        } else {
            moveOptions[0] = 0;
        }
        if (checkUp(mobility)) {
            moveOptions[1] = 1;
            counter++;
        } else {
            moveOptions[1] = 0;
        }
        if (checkRight(mobility)) {
            moveOptions[2] = 1;
            counter++;
        } else {
            moveOptions[2] = 0;
        }
        if (checkDown(mobility)) {
            moveOptions[3] = 1;
            counter++;
        } else {
            moveOptions[3] = 0;
        }
        return counter;
    }

    /**
     * Mètode que obté una posició random entre el nombre de sales a les quals el jugador es pot moure
     * @param counter nombre de sales on el jugador es pot moure
     * @return enter amb la posició
     */
    public int getRandomPosition(int counter) {
        return (int) (Math.random() * (counter));
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {this.cell = cell;}

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Mètode que calcula les coordenades del jugador a la pròxima sala
     * @param nextRoom pròxima sala on es mourà
     * @return coordenades del jugador a la pròxima sala
     */
    public int[] getNextCoordinates(int nextRoom) {
        int[] actualRoom = new int[2];
        actualRoom[0] = getCell().getX();
        actualRoom[1] = getCell().getY();
        switch (nextRoom) {
            case 0:
                actualRoom[0] -= 1;
                return actualRoom;
            case 1:
                actualRoom[1] -= 1;
                return actualRoom;
            case 2:
                actualRoom[0] += 1;
                return actualRoom;
            case 3:
                actualRoom[1] += 1;
                return actualRoom;
            default:
                actualRoom[0] = -1;
                actualRoom[1] = -1;
                return actualRoom;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void startThread() {
        isRunning = true;
        this.start();
    }

    public void stopThread() {
        isRunning = false;
        this.interrupt();
    }

    public int getMaxProbability() {
        return 100;
    }

    public Time getTotalTime() {
        return totalTime;
    }

    /**
     * Mètode que retorna l'interval en el qual el jugador decidirà si moure's o no
     * @param maxInterval valor màxim de temps depenent del rol del jugador
     * @param minInterval valor mínim de temps depenent del rol del jugador
     * @return valor de l'interval de temps
     */
    public int randomInterval(int maxInterval, int minInterval) {
        return (int)(Math.random()*(maxInterval - minInterval + 1) + minInterval);
    }

    public void setNpcManager(NpcManager npcManager){}

    public Time getIntervalTime() {
        return intervalTime;
    }
}
