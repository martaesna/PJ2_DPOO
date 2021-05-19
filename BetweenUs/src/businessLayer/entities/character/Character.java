package businessLayer.entities.character;

import businessLayer.entities.maps.Cell;

import java.awt.*;

public class Character {
    private String color;
    private Cell cell;
    private int xCoordinate;
    private int yCoordinate;

    public Character (String color, int xCoordinate, int yCoordinate) {
        this.color = color;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Character(String color) {this.color = color;}

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
        if(checkRols()) {
            return true;
        }
        return false;
    }

    private boolean checkRols() {
        return true;
    }
}
