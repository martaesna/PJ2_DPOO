package presentationLayer.views;

import presentationLayer.controllers.NewGameController;
import presentationLayer.views.customComponents.RoundedBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class NewGameView extends JFrame {
    private final JTextField gameName;
    private final JButton leftButtonColor;
    private final JButton rightButtonColor;

    private final JButton leftButtonImpostor;
    private final JButton rightButtonImpostor;

    private final JButton leftButtonPlayers;
    private final JButton rightButtonPlayers;

    private JButton returnButton;
    private JButton configButton;


    private JButton jbChooseMap = new JButton();
    private final JLabel jlColor = new JLabel();
    private final JLabel jlImpostorsList = new JLabel();
    private final JLabel jlPlayersList = new JLabel();

    private String mapName = "Select File";
    private String color = "RED";
    private int impostors = 1;
    private int players = 9;
    private final Font font;

    private final JButton jbPlay;
    private final JPanel jpBody;
    private final NewGameController ngc = new NewGameController();


    public NewGameView() {
        setTitle("New game"); // titol
        setSize(1080, 600); // mida titol
        setLocationRelativeTo(null); // centrar titol
        setDefaultCloseOperation(EXIT_ON_CLOSE); // tancar en 'X'

        jpBody = new JPanel();

        //--------------FONS
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        background.setBackground(Color.BLACK);

        //-----Part NORD de la finestra
        JPanel JpNorth = new JPanel(new BorderLayout());
        JpNorth.setOpaque(false);
        JLabel title = new JLabel("New game", JLabel.CENTER);
        title.setFont(new Font("Russo One", Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        JpNorth.add(title, BorderLayout.CENTER);

        //
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/config.png")));
            Image scaled = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            configButton = new JButton(backgroundImage);
            configButton.setOpaque(false);
            configButton.setContentAreaFilled(false);
            configButton.setBorderPainted(false);
            configButton.setActionCommand("Config");
            JpNorth.add(configButton, BorderLayout.WEST);


            BufferedImage image2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/tornar.png")));
            Image scaled2 = image2.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage2 = new ImageIcon(scaled2);
            returnButton = new JButton(backgroundImage2);
            returnButton.setOpaque(false);
            returnButton.setContentAreaFilled(false);
            returnButton.setBorderPainted(false);
            returnButton.setActionCommand("Return");
            JpNorth.add(returnButton, BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //-----------COS
        font = new Font("Russo One",Font.BOLD,35);
        Border border =BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE,3,true),
                        BorderFactory.createEmptyBorder(5,5,10,10)
                )

        );

        // Centre
        jpBody.setOpaque(false);
        jpBody.setLayout(null);

        // Game name
        JLabel jlGameName = new JLabel("Game name");
        jlGameName.setFont(font);
        jlGameName.setBackground(Color.BLACK);
        jlGameName.setForeground(Color.WHITE);
        jlGameName.setBounds(250,10,250,50);

        // Game name Text field
        gameName = new JTextField();
        gameName.setBackground(Color.BLACK);
        gameName.setForeground(Color.WHITE);
        gameName.setBorder(border);
        gameName.setBounds(550,0,330,75);

        // Color
        JLabel jlColorList = new JLabel("Color");
        jlColorList.setFont(font);
        jlColorList.setBackground(Color.BLACK);
        jlColorList.setForeground(Color.WHITE);
        jlColorList.setBounds(250,75,275,75);

        // Colors List
        leftButtonColor = new JButton("◀");
        leftButtonColor.setBackground(Color.BLACK);
        leftButtonColor.setForeground(Color.WHITE);
        leftButtonColor.setActionCommand("ColorLeft");
        leftButtonColor.setBorder(null);
        leftButtonColor.setFont(new Font("", Font.BOLD, 35));
        leftButtonColor.setBounds(550,75,70,70);

        setColor(color);

        rightButtonColor = new JButton("▶");
        rightButtonColor.setBackground(Color.BLACK);
        rightButtonColor.setForeground(Color.WHITE);
        rightButtonColor.setActionCommand("ColorRight");
        rightButtonColor.setBorder(null);
        rightButtonColor.setFont(new Font("", Font.BOLD, 35));
        rightButtonColor.setBounds(800,75,70,70);


        // Impostors--------------------------------------------------------------------
        JLabel jlImpostors = new JLabel("Impostors");
        jlImpostors.setFont(font);
        jlImpostors.setBackground(Color.BLACK);
        jlImpostors.setForeground(Color.WHITE);
        jlImpostors.setBounds(250,150,275,75);

        leftButtonImpostor = new JButton("◀");
        leftButtonImpostor.setBackground(Color.BLACK);
        leftButtonImpostor.setForeground(Color.WHITE);
        leftButtonImpostor.setActionCommand("ImpostorsLeft");
        leftButtonImpostor.setBorder(null);
        leftButtonImpostor.setFont(new Font("", Font.BOLD, 35));
        leftButtonImpostor.setBounds(550,150,70,70);

        // Impostors List
        setImpostors(impostors);

        rightButtonImpostor = new JButton("▶");
        rightButtonImpostor.setBackground(Color.BLACK);
        rightButtonImpostor.setForeground(Color.WHITE);
        rightButtonImpostor.setActionCommand("ImpostorsRight");
        rightButtonImpostor.setBorder(null);
        rightButtonImpostor.setFont(new Font("", Font.BOLD, 35));
        rightButtonImpostor.setBounds(800,150,70,70);


        // Players----------------------------------------------------------------------------
        JLabel jlPlayers = new JLabel("Players");
        jlPlayers.setFont(font);
        jlPlayers.setBackground(Color.BLACK);
        jlPlayers.setForeground(Color.WHITE);
        jlPlayers.setBounds(250,225,275,75);

        leftButtonPlayers = new JButton("◀");
        leftButtonPlayers.setBackground(Color.BLACK);
        leftButtonPlayers.setForeground(Color.WHITE);
        leftButtonPlayers.setActionCommand("PlayersLeft");
        leftButtonPlayers.setBorder(null);
        leftButtonPlayers.setFont(new Font("", Font.BOLD, 35));
        leftButtonPlayers.setBounds(550,225,70,70);

        // Players List

        setPlayers(players);

        rightButtonPlayers = new JButton("▶");
        rightButtonPlayers.setBackground(Color.BLACK);
        rightButtonPlayers.setForeground(Color.WHITE);
        rightButtonPlayers.setActionCommand("PlayersRight");
        rightButtonPlayers.setBorder(null);
        rightButtonPlayers.setFont(new Font("", Font.BOLD, 35));
        rightButtonPlayers.setBounds(800,225,70,70);


        // Map-------------------------------------------------------------------------------------
        JLabel jlMap = new JLabel("Map");
        jlMap.setFont(font);
        jlMap.setBackground(Color.BLACK);
        jlMap.setForeground(Color.WHITE);
        jlMap.setBounds(250,300,275,75);

        // Map List

        setMapName(mapName);


        //------------------------------------------------------
        jbPlay = new JButton("Play");
        jbPlay.setFont(font);
        jbPlay.setActionCommand("Play");
        jbPlay.setBackground(Color.black);
        jbPlay.setForeground(Color.WHITE);
        jbPlay.setBounds(450,400,200,50);
        jbPlay.setBorder(new RoundedBorder(50));

        jpBody.add(leftButtonColor);
        jpBody.add(rightButtonColor);

        jpBody.add(rightButtonImpostor);
        jpBody.add(leftButtonImpostor);

        jpBody.add(rightButtonPlayers);
        jpBody.add(leftButtonPlayers);

        jpBody.add(jlGameName);
        jpBody.add((gameName));
        jpBody.add(jlColorList);
        jpBody.add(jlImpostors);
        jpBody.add(jlPlayers);
        jpBody.add(jlMap);
        jpBody.add(jbPlay);

        background.add(JpNorth,BorderLayout.NORTH);
        background.add(jpBody, BorderLayout.CENTER);
        add(background);
        setVisible(true);
    }

    public void mainController(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
        configButton.addActionListener(actionListener);

        jbChooseMap.addActionListener(actionListener);
        jbPlay.addActionListener(actionListener);

        rightButtonColor.addActionListener(actionListener);
        leftButtonColor.addActionListener(actionListener);

        rightButtonImpostor.addActionListener(actionListener);
        leftButtonImpostor.addActionListener(actionListener);

        rightButtonPlayers.addActionListener(actionListener);
        leftButtonPlayers.addActionListener(actionListener);
    }

   public void setMapName(String mapName) {
       this.mapName = mapName;

       jpBody.remove(jbChooseMap);
       jbChooseMap = new JButton(mapName);
       jbChooseMap.setFont(new Font("", Font.BOLD, 16));
       jbChooseMap.setActionCommand("SelectFile");
       jbChooseMap.setBackground(Color.BLACK);
       jbChooseMap.setForeground(Color.WHITE);
       jbChooseMap.setBounds(570,320,300,50);
       jbChooseMap.setBorder(new RoundedBorder(50));

       jpBody.add(jbChooseMap);
   }

    public void setColor(String color) {
        this.color = color;

        jlColor.setText(color);
        jlColor.setFont(font);
        jlColor.setBackground(Color.BLACK);


        if (color.equals("PURPLE") || color.equals("BROWN") || color.equals("CYAN") || color.equals("LIME")) {
            int[] components = ngc.getColorComponents(color);
            Color unusualColor = new Color(components[0],components[1],components[2]);
            jlColor.setForeground(unusualColor);
        } else {
            try {
                Color newColor = (Color) Color.class.getField(color).get(null);
                jlColor.setForeground(newColor);
                if (color.equals("BLACK")) {
                    //Posar traç blanc?
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        jlColor.setBounds(575,75,275,75);
        jlColor.setHorizontalAlignment(SwingConstants.CENTER);
        jpBody.add(jlColor);
    }


    public void setImpostors(int impostors) {
        this.impostors = impostors;

        jlImpostorsList.setText(String.valueOf(impostors));
        jlImpostorsList.setFont(font);
        jlImpostorsList.setBackground(Color.BLACK);
        jlImpostorsList.setForeground(Color.WHITE);
        jlImpostorsList.setBounds(700,150,275,75);
        jpBody.add(jlImpostorsList);
    }


    public void setPlayers(int players) {
        this.players = players;

        jlPlayersList.setText(String.valueOf(players));
        jlPlayersList.setFont(font);
        jlPlayersList.setBackground(Color.BLACK);
        jlPlayersList.setForeground(Color.WHITE);
        jlPlayersList.setBounds(575,225,275,75);
        jlPlayersList.setHorizontalAlignment(SwingConstants.CENTER);
        jpBody.add(jlPlayersList);
    }

    public String getName() {
        return gameName.getText();
    }

    public String getColor() {
        return color;
    }

    public int getImpostors() {
        return impostors;
    }

    public int getPlayers() {
        return players;
    }

    public String getMapName(){
        return mapName;
    }

   /* public int[] getColorComponents(String color) {
        int[] components = new int[3];
        if (color == "PURPLE") {
            components[0] = 102;
            components[1] = 0;
            components[2] = 153;
            return components;

        } else if(color == "BROWN") {
            components[0] = 102;
            components[1] = 51;
            components[2] = 0;
            return components;

        } else if(color == "CYAN") {
            components[0] = 0;
            components[1] = 255;
            components[2] = 255;
            return components;
        } else {
            components[0] = 50;
            components[1] = 205;
            components[2] = 50;
            return components;
        }
    }*/

    public void printNameError() {
        JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc ja existeix", "Error New Game", JOptionPane.ERROR_MESSAGE);
    }

    public void printEmptyNameError() {
        JOptionPane.showMessageDialog(null, "ERROR: El joc ha de tenir un nom", "Error New Game", JOptionPane.ERROR_MESSAGE);
    }
}
