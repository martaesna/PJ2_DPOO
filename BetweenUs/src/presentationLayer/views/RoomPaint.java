package presentationLayer.views;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.maps.Map;
import presentationLayer.controllers.NewGameController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class RoomPaint extends JPanel {
    private Color color;
    private Color playerColor;
    private String roomName;
    private LinkedList<CrewMember> crewMembers;
    private LinkedList<Impostor> impostors;
    private Character userPlayer;
    private int separadorX;
    private int separadorY;
    private NewGameController ngc = new NewGameController();

    public RoomPaint( Color color,String roomName, LinkedList<CrewMember> crewMembers, LinkedList<Impostor> impostors, Character userPlayer){
        this.color = color;
        this.roomName = roomName;
        this.crewMembers = crewMembers;
        this.impostors = impostors;
        this.userPlayer = userPlayer;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());



        if (roomName.equals("cafeteria")) {
            separadorX = getWidth() / 4;
            separadorY = getHeight() / 3;


            if (userPlayer.getColor().equals("PURPLE") || userPlayer.getColor().equals("BROWN") || userPlayer.getColor().equals("CYAN") || userPlayer.getColor().equals("LIME")) {
                int[] components = ngc.getColorComponents(userPlayer.getColor());
                playerColor = new Color(components[0], components[1], components[2]);
            } else {
                try {
                    playerColor = (Color) Color.class.getField(userPlayer.getColor()).get(null);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }

            g.setColor(Color.BLACK);
            g.fillOval(separadorX - 1, separadorY - 1, 17, 17);


            g.setColor(playerColor);
            g.fillOval(separadorX, separadorY, 15, 15);
            separadorX += 20;


            for (int j = 0; j < crewMembers.size(); j++) {
                System.out.println(crewMembers.size());
                System.out.println(crewMembers.get(j).getColor());

                if (separadorX + 30 > getWidth()) {
                    separadorY = separadorY + 20;
                    separadorX = getWidth() / 4;
                }
                if (crewMembers.get(j).getColor().equals("PURPLE") || crewMembers.get(j).getColor().equals("BROWN") || crewMembers.get(j).getColor().equals("CYAN") || crewMembers.get(j).getColor().equals("LIME")) {
                    int[] components = ngc.getColorComponents(crewMembers.get(j).getColor());
                    playerColor = new Color(components[0], components[1], components[2]);
                } else {
                    try {
                        playerColor = (Color) Color.class.getField(crewMembers.get(j).getColor()).get(null);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }

                g.setColor(Color.BLACK);
                g.fillOval(separadorX - 1, separadorY - 1, 17, 17);

                g.setColor(playerColor);
                g.fillOval(separadorX, separadorY, 15, 15);
                separadorX += 20;
            }

            for (int m = 0; m < impostors.size(); m++) {
                if (separadorX + 30 > getWidth()) {
                    separadorY = separadorY + 20;
                    separadorX = getWidth() / 4;
                }
                if (impostors.get(m).getColor().equals("PURPLE") || impostors.get(m).getColor().equals("BROWN") || impostors.get(m).getColor().equals("CYAN") || impostors.get(m).getColor().equals("LIME")) {
                    int[] components = ngc.getColorComponents(impostors.get(m).getColor());
                    playerColor = new Color(components[0], components[1], components[2]);
                } else {
                    try {
                        playerColor = (Color) Color.class.getField(impostors.get(m).getColor()).get(null);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                g.setColor(Color.BLACK);
                g.fillOval(separadorX - 1, separadorY - 1, 17, 17);


                g.setColor(playerColor);
                g.fillOval(separadorX, separadorY, 15, 15);
                separadorX += 20;
            }
        }
    }
}
