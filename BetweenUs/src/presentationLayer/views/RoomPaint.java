package presentationLayer.views;

import businessLayer.entities.character.Character;
import presentationLayer.controllers.NewGameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class RoomPaint extends JPanel {
    private final Color color;
    private Color colori;
    private final String roomName;
    private final NewGameController ngc = new NewGameController();
    private final LinkedList<String> colors;
    private final Boolean userIsHere;
    private boolean revealMap;
    private BufferedImage image;
    private int numCorpses;
    private LinkedList<String> corpColors = new LinkedList<>();

    public RoomPaint(Color color, String roomName, LinkedList<String> colors, Boolean userIsHere, Boolean revealMap, int numCorpses){
        this.color = color;
        this.roomName = roomName;
        this.colors = colors;
        this.userIsHere = userIsHere;
        this.revealMap = revealMap;
        this.numCorpses = numCorpses;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/XDeadPlayer.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * pinta les habitacions del mapa
     * @param g grafics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (revealMap || userIsHere) {
            g.setColor(color);
            g.fillRect(0, 0, getWidth(), getHeight());

            int separadorX = getWidth() / 4;
            int separadorY = getHeight() / 3;

            for (int i = 0; i < colors.size(); i++) {
                if (colors.get(i).equals("PURPLE") || colors.get(i).equals("BROWN") || colors.get(i).equals("CYAN") || colors.get(i).equals("LIME")) {
                    int[] components = ngc.getColorComponents(colors.get(i));
                    colori = new Color(components[0], components[1], components[2]);
                } else {
                    try {
                        colori = (Color) Color.class.getField(colors.get(i)).get(null);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                g.setColor(Color.BLACK);
                g.fillOval(separadorX - 1, separadorY - 1, 17, 17);

                g.setColor(colori);
                g.fillOval(separadorX, separadorY, 15, 15);
                separadorX += 20;

                if (separadorX + 30 > getWidth()) {
                    separadorY = separadorY + 20;
                    separadorX = getWidth() / 4;
                }
            }

            for (int i = 0; i < numCorpses; i++) {
                Image imageScaled = image.getScaledInstance(20, 25, Image.SCALE_DEFAULT);
                g.drawImage(imageScaled, separadorX, separadorY, this);
                separadorX += 20;
                if (separadorX + 30 > getWidth()) {
                    separadorY = separadorY + 20;
                    separadorX = getWidth() / 4;
                }
            }

            /*
            if (corpColors != null) {
                for (int i = 0; i < numCorpses; i++) {
                    try {
                        colori = (Color) Color.class.getField(corpColors.get(i)).get(null);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    g.setColor(colori);
                    g.fillRect(20,25,separadorX,separadorY);

                    Image imageScaled = image.getScaledInstance(20, 25, Image.SCALE_DEFAULT);
                    g.drawImage(imageScaled, separadorX, separadorY, this);
                    separadorX += 20;
                    if (separadorX + 30 > getWidth()) {
                        separadorY = separadorY + 20;
                        separadorX = getWidth() / 4;
                    }
                }
            }*/
        } else {
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
