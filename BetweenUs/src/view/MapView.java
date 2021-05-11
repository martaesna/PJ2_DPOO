package view;
import controller.MapCotroller;
import model.maps.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MapView extends JFrame {

    private JButton returnButton;
    private JButton configButton;
    private JButton mapButton;
    private MapCotroller mc;

    public MapView(){
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

        JPanel JpCenter = new JPanel(new GridLayout(mc.getWidth(), mc.getHeight()));

        for(int i = 0; i < mc.getHeight(); ++i){
            for( int j = 0; j < mc.getWidth(); ++j){
                //Aqui printem cada part del mapa corresponent amb la posicio de les celles.
                //primer determinar quin tipus de cella es
             int pos = mc.trobaCela(j,i);
             if (pos == -1){
                 //aixo es un cuadrat negre
                JPanel empty = new JPanel();
                empty.setBackground(Color.BLACK);
                JpCenter.add(empty);

             } else{
                String tipusCela =  mc.getType(i);
                if (tipusCela == "room") {
                    //creem un panell del color de la sala

                }
             }

            }
        }



        background.add(JpCenter,BorderLayout.CENTER);
        add(background);
        setVisible(true);
    }


}


