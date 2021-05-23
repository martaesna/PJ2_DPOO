package presentationLayer.views;

import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Mobility;
import presentationLayer.controllers.NewGameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class CorridorPaint extends JPanel{
    private final Mobility mov;
    private final String map;
    private final LinkedList<String> colors;
    private final Boolean userIsHere;
    private Color colori;
    private final NewGameController ngc = new NewGameController();
    private boolean revealMap;
    private BufferedImage image;
    private int numCorpses;
    private LinkedList<String> corpColors = new LinkedList<>();


    public CorridorPaint(Mobility mov, String map, LinkedList<String> colors, Boolean userIsHere, Boolean revealMap, int numCorpses){
        this.mov = mov;
        this.map = map;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (revealMap || userIsHere) {
            g.setColor(Color.BLACK);
            g.fillRect(0,0,400,400);

            int alturaX = (getHeight()/5)*3;
            int ampladaX = (getWidth()/5)*3;
            int alturaY = (getWidth()/5)*3;
            int ampladaY = (getHeight()/5)*3;

            // sabre posicio o tamant dels quadrats.
            if(mov.getUp() == 1){
                g.setColor(Color.WHITE);
                g.fillRect((getWidth()/2) - ampladaY/2,0, ampladaY, ampladaY);
            }
            if(mov.getDown() == 1){
                g.setColor(Color.WHITE);
                g.fillRect((getWidth()/2) - ampladaY/2,(getHeight()/5)*2, ampladaY,alturaY);
            }
            if(mov.getLeft() == 1){
                g.setColor(Color.WHITE);
                g.fillRect(0,getHeight()/5,(getWidth()/2) - ampladaY/2 + ampladaY, alturaX);
            }
            if(mov.getRight() == 1){
                g.setColor(Color.WHITE);
                g.fillRect((getWidth()/2) - ampladaY/2,getHeight()/5,ampladaX + alturaX/2, alturaX);
            }

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
                    if (corpColors.get(i).equals("PURPLE") || corpColors.get(i).equals("BROWN") || corpColors.get(i).equals("CYAN") || corpColors.get(i).equals("LIME")) {
                        g.setColor(Color.GRAY);
                    } else {
                        try {
                            colori = (Color) Color.class.getField(corpColors.get(i)).get(null);
                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        g.setColor(colori);
                    }
                    g.fillRect(20, 25, separadorX, separadorY);

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
            g.fillRect(0,0,400,400);
        }
    }
}
