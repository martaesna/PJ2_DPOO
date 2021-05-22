package businessLayer.entities.character;

import businessLayer.LogManager;
import businessLayer.NpcManager;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Mobility;
import presentationLayer.views.customComponents.Log;

public abstract class Character extends Thread{
    private Time time;
    private final int[] moveOptions = new int[4];
    private int randomInterval;
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
        time = new Time();
    }

    public int chooseRoom(int optionsCounter, int randomPosition) {
        for (int i = 0; i < 4; i++) {
            if (moveOptions[i] == 1) {
                optionsCounter++;
                if (optionsCounter == randomPosition - 1) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void makeLog(Log log) {
        LogManager logManager = new LogManager(log);
    }

    public boolean checkLeft(Mobility mobility) {
        return mobility.getLeft() != 0;
    }

    public boolean checkRight(Mobility mobility) {
        return mobility.getRight() != 0;
    }

    public boolean checkUp(Mobility mobility) {
        return mobility.getUp() != 0;
    }

    public boolean checkDown(Mobility mobility) {
        return mobility.getDown() != 0;
    }

    public int setMoveOptions(Mobility mobility) {
        int counter = 0;
        if (checkLeft(mobility)) {
            moveOptions[0] = 1;
            counter++;
        } else {
            moveOptions[0] = 0;
        }
        if (checkRight(mobility)) {
            moveOptions[1] = 1;
            counter++;
        } else {
            moveOptions[1] = 0;
        }
        if (checkUp(mobility)) {
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

    public int getRandomPosition(int counter) {
        int min = 1;
        return (int) (Math.random() * (counter - min + 1) + min);
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


    public int[] getNextCoordinates(int nextRoom) {
        int[] actualRoom = new int[2];
        actualRoom[0] = getCell().getX();
        actualRoom[1] = getCell().getY();
        switch (nextRoom) {
            case 1:
                //cell.setX(actualRoom[0] - 1);
                actualRoom[0] -= 1;
                return actualRoom;
            case 2:
                //cell.setY(actualRoom[1] + 1);
                actualRoom[1] += 1;
                return actualRoom;
            case 3:
                //cell.setX(actualRoom[0] + 1);
                actualRoom[0]+= 1;
                return actualRoom;
            case 4:
                //cell.setY(actualRoom[1] - 1);
                actualRoom[1] -= 1;
                return actualRoom;
        }
        return null;
    }

    public void leaveGame() {

    }

    public boolean checkClassification(){
        return checkRols();
    }

    private boolean checkRols() {
        return true;
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

    public Time getTime() {
        return time;
    }

    public int randomInterval(int maxInterval, int minInterval) {
        return (int)(Math.random()*(maxInterval - minInterval + 1) + minInterval);
    }

    public void setNpcManager(NpcManager npcManager){}
}
