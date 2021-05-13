package presentationLayer.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.prefs.BackingStoreException;

public class NewGameView extends JFrame {
    private JTextField gameName;
    private JButton leftButton;
    private JButton rightButton;
    private JButton returnButton;
    private JButton configButton;
    private JButton jbChooseMap;

    public NewGameView() {
        setTitle("New game"); // titol
        setSize(1080, 600); // mida titol
        setLocationRelativeTo(null); // centrar titol
        setDefaultCloseOperation(EXIT_ON_CLOSE); // tancar en 'X'

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
        Font font = new Font("Russo One",Font.BOLD,35);
        Border border =BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE,3,true),
                        BorderFactory.createEmptyBorder(5,5,10,10)
                )

        );

        // Centre
        JPanel jpBody = new JPanel();
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
        gameName.setFont(font);
        gameName.setBorder(border);
        gameName.setBounds(550,0,280,75);

        // Color
        JLabel jlColorList = new JLabel("Color");
        jlColorList.setFont(font);
        jlColorList.setBackground(Color.BLACK);
        jlColorList.setForeground(Color.WHITE);
        jlColorList.setBounds(250,75,275,75);

        // Colors List
        leftButton = new JButton("◀");
        leftButton.setBackground(Color.BLACK);
        leftButton.setForeground(Color.WHITE);
        leftButton.setFont(new Font("", Font.BOLD, 35));
        leftButton.setBounds(560,75,70,70);
        jpBody.add(leftButton);
        JLabel jlColor = new JLabel("       Red      ▶");
        jlColor.setFont(font);
        jlColor.setBackground(Color.BLACK);
        jlColor.setForeground(Color.WHITE);
        jlColor.setBounds(560,75,275,75);

        // Impostors
        JLabel jlImpostors = new JLabel("Impostors");
        jlImpostors.setFont(font);
        jlImpostors.setBackground(Color.BLACK);
        jlImpostors.setForeground(Color.WHITE);
        jlImpostors.setBounds(250,150,275,75);

        // Impostors List
        JLabel jlImpostorsList = new JLabel("◀         2         ▶");
        jlImpostorsList.setFont(font);
        jlImpostorsList.setBackground(Color.BLACK);
        jlImpostorsList.setForeground(Color.WHITE);
        jlImpostorsList.setBounds(560,150,275,75);

        // Players
        JLabel jlPlayers = new JLabel("Players");
        jlPlayers.setFont(font);
        jlPlayers.setBackground(Color.BLACK);
        jlPlayers.setForeground(Color.WHITE);
        jlPlayers.setBounds(250,225,275,75);

        // Players List
        JLabel jlPlayersList = new JLabel("◀        10        ▶");
        jlPlayersList.setFont(font);
        jlPlayersList.setBackground(Color.BLACK);
        jlPlayersList.setForeground(Color.WHITE);
        jlPlayersList.setBounds(560,225,275,75);

        // Map
        JLabel jlMap = new JLabel("Map");
        jlMap.setFont(font);
        jlMap.setBackground(Color.BLACK);
        jlMap.setForeground(Color.WHITE);
        jlMap.setBounds(250,300,275,75);

        // Map List
        jbChooseMap = new JButton("Select File");
        jbChooseMap.setFont(new Font("", Font.BOLD, 16));
        jbChooseMap.setActionCommand("SelectFile");
        jbChooseMap.setBackground(Color.BLACK);
        jbChooseMap.setForeground(Color.WHITE);
        jbChooseMap.setBounds(560,300,275,75);

        jpBody.add(jlGameName);
        jpBody.add((gameName));
        jpBody.add(jlColor);
        jpBody.add(jlColorList);
        jpBody.add(jlImpostors);
        jpBody.add(jlImpostorsList);
        jpBody.add(jlPlayers);
        jpBody.add(jlPlayersList);
        jpBody.add(jlMap);
        jpBody.add(jbChooseMap);

        background.add(JpNorth,BorderLayout.NORTH);
        background.add(jpBody, BorderLayout.CENTER);
        add(background);
        setVisible(true);
    }

    public void mainController(ActionListener actionListener) {
        jbChooseMap.addActionListener(actionListener);
    }
}
