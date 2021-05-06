package view;

import view.customComponents.RoundedBorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ConfiguredGameView extends JFrame {

    private JButton jbConfigure;
    private JTextField gameName;
    private JButton configButton;
    private JButton returnButton;

    public ConfiguredGameView() {
        setVisible(true);
        setTitle("Configured game"); // titol
        setSize(1080, 600); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        //Creem el fons
        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);
        background.setLayout(null);
        background.setBounds(0,0,1080,600);


        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/model/images/config.png")));
            Image scaled = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            configButton = new JButton(backgroundImage);
            configButton.setOpaque(false);
            configButton.setContentAreaFilled(false);
            configButton.setBorderPainted(false);
            configButton.setActionCommand("Config");
            configButton.setBounds(10,10, 30, 30);
            background.add(configButton);

            BufferedImage image2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/model/images/tornar.png")));
            Image scaled2 = image2.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage2 = new ImageIcon(scaled2);
            returnButton = new JButton(backgroundImage2);
            returnButton.setOpaque(false);
            returnButton.setContentAreaFilled(false);
            returnButton.setBorderPainted(false);
            returnButton.setActionCommand("Return");
            returnButton.setBounds(1000,10, 40, 30);
            background.add(returnButton);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        JLabel title = new JLabel("Configured game", JLabel.LEFT);
        title.setBounds(175,50,800,95);
        title.setFont(new Font("Russo One", Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        background.add(title);


        Font font = new Font("Russo One",Font.BOLD,35);

        JLabel jlMusic = new JLabel("Game name",JLabel.LEFT);
        jlMusic.setBounds(420,190,250,50);
        jlMusic.setFont(font);
        jlMusic.setForeground(Color.WHITE);
        background.add(jlMusic);

        gameName = new JTextField();
        gameName.setBackground(Color.BLACK);
        gameName.setForeground(Color.WHITE);
        gameName.setFont(new Font("", Font.BOLD, 20));
        gameName.setBounds(350,260,350,40);
        background.add(gameName);


        jbConfigure = new JButton("Create");
        jbConfigure.setForeground(Color.WHITE);
        jbConfigure.setBackground(Color.BLACK);
        jbConfigure.setBounds(375,400,300,75);
        jbConfigure.setActionCommand("Create");
        jbConfigure.setFont(font);
        jbConfigure.setBorder(new RoundedBorder(50));
        background.add(jbConfigure);

        setContentPane(background);
    }

    public void mainController(ActionListener actionListener) {
        jbConfigure.addActionListener(actionListener);
        configButton.addActionListener(actionListener);
        returnButton.addActionListener(actionListener);
    }

    public String getConfiguredName() { return gameName.getText(); }

    public void printErrorNoExistance(){
        JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc no existeix", "Error Delete Game", JOptionPane.ERROR_MESSAGE);
    }
}