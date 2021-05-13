package businessLayer.entities.character;

import java.awt.*;

public class Character {
    private Color color;
    private String role;
    private boolean npc;

    public Character(Color color, String role, boolean npc) {
        this.color = color;
        this.role = role;
        this.npc = npc;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isNpc() {
        return npc;
    }

    public void setNpc(boolean npc) {
        this.npc = npc;
    }

    public void move() {


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
