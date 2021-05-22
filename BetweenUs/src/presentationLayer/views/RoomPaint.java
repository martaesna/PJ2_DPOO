package presentationLayer.views;

import businessLayer.entities.character.Character;
import presentationLayer.controllers.NewGameController;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class RoomPaint extends JPanel {
    private final Color color;
    private Color playerColor;
    private final String roomName;
    private final LinkedList<Character> players;
    private final Character userPlayer;
    private final NewGameController ngc = new NewGameController();

    public RoomPaint(Color color, String roomName, LinkedList<Character> players, Character userPlayer){
        this.color = color;
        this.roomName = roomName;
        this.players = players;
        this.userPlayer = userPlayer;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());

        if (roomName.equals("cafeteria")) {
            int separadorX = getWidth() / 4;
            int separadorY = getHeight() / 3;

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

            for (int j = 0; j < players.size(); j++) {
                System.out.println(players.size());
                System.out.println(players.get(j).getColor());

                if (separadorX + 30 > getWidth()) {
                    separadorY = separadorY + 20;
                    separadorX = getWidth() / 4;
                }
                if (players.get(j).getColor().equals("PURPLE") || players.get(j).getColor().equals("BROWN") || players.get(j).getColor().equals("CYAN") || players.get(j).getColor().equals("LIME")) {
                    int[] components = ngc.getColorComponents(players.get(j).getColor());
                    playerColor = new Color(components[0], components[1], components[2]);
                } else {
                    try {
                        playerColor = (Color) Color.class.getField(players.get(j).getColor()).get(null);
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
