package view;
import controller.MapCotroller;
import model.maps.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MapView extends JFrame {

    private JButton returnButton;
    private JButton configButton;
    private JButton mapButton;
    private MapCotroller mc;

    public MapView(Map map){
        setTitle("Map");
        setSize(1080, 600);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //pintem el panell final
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        background.setBackground(Color.BLACK);

        //Fem un panel a la part de dalt.
        JPanel JpNorth = new JPanel(new BorderLayout());
        JpNorth.setOpaque(false);

        JPanel JpNorthEast = new JPanel(new BorderLayout());
        JpNorthEast.setOpaque(false);

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/model/images/config.png")));
            Image scaled = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            configButton = new JButton(backgroundImage);
            configButton.setOpaque(false);
            configButton.setContentAreaFilled(false);
            configButton.setBorderPainted(false);
            configButton.setActionCommand("Config");

            JpNorth.add(configButton, BorderLayout.WEST);



            BufferedImage image2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/model/images/tornar.png")));
            Image scaled2 = image2.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage2 = new ImageIcon(scaled2);
            returnButton = new JButton(backgroundImage2);
            returnButton.setOpaque(false);
            returnButton.setContentAreaFilled(false);
            returnButton.setBorderPainted(false);
            returnButton.setActionCommand("Return");

            JpNorth.add(returnButton, BorderLayout.EAST);


            BufferedImage image3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/model/images/tornar.png")));
            Image scaled3 = image3.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage3 = new ImageIcon(scaled3);
            mapButton = new JButton(backgroundImage3);
            mapButton.setOpaque(false);
            mapButton.setContentAreaFilled(false);
            mapButton.setBorderPainted(false);
            mapButton.setActionCommand("Return");

            JpNorthEast.add(mapButton, BorderLayout.EAST);
            JpNorth.add(JpNorthEast,BorderLayout.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        }
        background.add(JpNorth, BorderLayout.NORTH);

       JPanel JpCenter = new JPanel(new GridLayout(map.getWidth(), map.getHeight()));
        for(int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                int pos = -1;
                //Aqui printem cada part del mapa corresponent amb la posicio de les celles.
                //primer determinar quin tipus de cella es recorren tots les celles per mirar si esta la corresponent
                for (int m = 0; m < map.getCells().size(); m++) {
                    if (map.getCells().get(m).getX() == j && map.getCells().get(m).getY() == i) pos = m;
                }

                //MIREM SI ES MAPA O CUADRAT NEGRE
                if (pos == -1) {
                    //aixo es un cuadrat negre
                    JPanel empty = new JPanel();
                    empty.setBackground(Color.BLACK);
                    JpCenter.add(empty);

                } else {
                    //MIREM SI ES SALA O PASSADIS


                    if (map.getCells().get(pos).getType().equals("room")) {
                        //creem un panell del color de la sala
                        JPanel room = new JPanel();
                        String color = map.getCells().get(pos).getColor();

                        StyleSheet s = new StyleSheet();
                        Color clr = s.stringToColor(map.getCells().get(pos).getColor());
                        room.setBackground(clr); //fer que el color sigui el que toca
                        JpCenter.add(room);
                    } else {

                        //si estem aqui som un pasadis, printem el mig del cuadrat de blanc
                        JPanel corridor = new JPanel(new BorderLayout());
                        JPanel center = new JPanel();
                        center.setBackground(Color.white);
                        corridor.setBackground(Color.BLACK);
                        corridor.add(center, BorderLayout.CENTER);
                        JpCenter.add(corridor);
                        //comprovem cap on podem anar per printar cap els costats on podem anar.

                        //MIREM SI POT ANAR CAP ADALT
                        if (map.getCells().get(pos).getMobility().getUp() == 1) {

                            JPanel up = new JPanel(new BorderLayout());
                            JPanel Cu = new JPanel();
                            Cu.setBackground(Color.white);
                            up.add(Cu, BorderLayout.CENTER);
                            corridor.add(up, BorderLayout.NORTH);
                        } else { //SINO PINTEM LES PARETS GRISES
                            JPanel b = new JPanel();
                            b.setBackground(Color.GRAY);
                            b.setBorder(new LineBorder(Color.GRAY,10,true));
                            corridor.add(b, BorderLayout.NORTH);
                        }

                        //MIREM SI POT ANAR CAP ABAIX
                        if (map.getCells().get(pos).getMobility().getDown() == 1) {

                            JPanel down = new JPanel(new BorderLayout());
                            JPanel du = new JPanel();
                            du.setBackground(Color.white);

                            down.add(du, BorderLayout.CENTER);
                            corridor.add(down, BorderLayout.SOUTH);
                        }else{
                            JPanel b1 = new JPanel();
                            b1.setBackground(Color.GRAY);
                            b1.setBorder(new LineBorder(Color.GRAY,10,true));
                            corridor.add(b1, BorderLayout.SOUTH);
                        }

                        //MIREM SI POT ANAR CAP A LA DRETA
                        if (map.getCells().get(pos).getMobility().getRight() == 1) {

                            JPanel right = new JPanel(new BorderLayout());
                            JPanel ru = new JPanel();
                            ru.setBackground(Color.white);
                            right.add(ru, BorderLayout.CENTER);
                            corridor.add(right, BorderLayout.EAST);
                        }else{
                            JPanel b2 = new JPanel();
                            b2.setBackground(Color.GRAY);
                            b2.setBorder(new LineBorder(Color.GRAY,15,true));
                            corridor.add(b2, BorderLayout.EAST);
                        }

                        //MIREM SI POT ANAR CAP A L ESQUERRA
                        if (map.getCells().get(pos).getMobility().getLeft() == 1) {

                            JPanel left = new JPanel(new BorderLayout());
                            JPanel lu = new JPanel();
                            lu.setBackground(Color.white);
                            left.add(lu, BorderLayout.CENTER);
                            left.setBackground(Color.black);
                            corridor.add(left, BorderLayout.WEST);
                        }else{
                            JPanel b3 = new JPanel();
                            b3.setBackground(Color.GRAY);
                            b3.setBorder(new LineBorder(Color.GRAY,15,true));
                            corridor.add(b3, BorderLayout.WEST);
                        }

                    }
                }
            }
        }
        JPanel bajo = new JPanel();
        bajo.setBackground(Color.RED);
        JPanel derecha = new JPanel();
        derecha.setBackground(Color.RED);
        JPanel izquierda = new JPanel();
        izquierda.setBackground(Color.RED);
        background.add(bajo,BorderLayout.SOUTH);
        background.add(derecha,BorderLayout.EAST);
        background.add(izquierda,BorderLayout.WEST);
        background.add(JpCenter,BorderLayout.CENTER);

        add(background);
        setVisible(true);
    }


}


