package businessLayer.entities.character;

import businessLayer.entities.maps.Cell;

import java.awt.*;

public class Character {
    private Color color;
    private Cell cell;

    public Character(Color color, Cell cell) {
        this.color = color;
        this.cell = cell;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCellX(int x) {
        this.cell.setX(x);
    }

    public void setCellY(int y) {
        this.cell.setY(y);
    }

    public void move(int nextRoom) {
        int[] actualRoom = new int[2];
        actualRoom[0] = getCell().getX();
        actualRoom[1] = getCell().getY();
        switch (nextRoom) {
            case 1:
                cell.setX(actualRoom[0] - 1);
                break;
            case 2:
                cell.setY(actualRoom[1] + 1);
                break;
            case 3:
                cell.setX(actualRoom[0] + 1);
                break;
            case 4:
                cell.setY(actualRoom[1] - 1);
                break;
        }
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
