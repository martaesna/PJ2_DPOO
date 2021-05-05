package view;

import javax.imageio.ImageIO;
import javax.management.BadAttributeValueExpException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.BorderLayout.NORTH;


public class PlayView extends JFrame {
    private JButton jbNewGame;
    private JButton jbConfiguredGame;
    private JButton jbChargeGame;
    private JButton jbDeleteGame;
    private JButton configButton;

    public PlayView() {

        setTitle("Play");
        setSize(1080, 600);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(null);

        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        background.setBackground(Color.BLACK);
        //background.setLayout(null);
        //background.setBounds(0, 0, 1080, 600);

        JPanel JpNorth = new JPanel(new BorderLayout());
        JpNorth.setOpaque(false);

        try {
            //boton de settings
            BufferedImage image = ImageIO.read(getClass().getResource("/model/images/config.png"));
            Image scaled = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            configButton = new JButton(backgroundImage);
            configButton.setOpaque(false);
            configButton.setContentAreaFilled(false);
            configButton.setBorderPainted(false);
            configButton.setActionCommand("Config");
            //configButton.setBounds(10,10, 30, 30);
            JpNorth.add(configButton, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel JpBody = new JPanel(new GridBagLayout());
        JLabel title = new JLabel("Play", JLabel.CENTER);
        //title.setBounds(375,10,400,95);
        title.setFont(new Font("Russo One", Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        JpNorth.add(title, BorderLayout.CENTER);



        background.add(JpNorth, BorderLayout.NORTH);

        Font font = new Font("Russo One",Font.BOLD,35);
        Border border =BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE,3,true),
                        BorderFactory.createEmptyBorder(5,5,10,10)
                )

        );

        JPanel JpCenter = new JPanel(new GridLayout(4, 1));

        // Boto New Game
        //background.add(new JButton("New Game"), BorderLayout.CENTER);
        jbNewGame = new JButton("New Game");
        jbNewGame.setForeground(Color.WHITE);
        jbNewGame.setBackground(Color.BLACK);
        jbNewGame.setActionCommand("NewGame");
        //jbNewGame.setBounds(375,350,300,75);
        jbNewGame.setFont(font);
        jbNewGame.setBorder(border);
        JpCenter.add(jbNewGame);

        // Boto Configured game

        jbConfiguredGame = new JButton("Configured game");
        jbConfiguredGame.setForeground(Color.WHITE);
        jbConfiguredGame.setBackground(Color.BLACK);
        jbConfiguredGame.setActionCommand("Configured");
        //jbConfiguredGame.setBounds(375,350,300,75);
        jbConfiguredGame.setFont(font);
        jbConfiguredGame.setBorder(border);
        JpCenter.add(jbConfiguredGame);

        // Boto Charge Game
        jbChargeGame = new JButton("Charge game");
        jbChargeGame.setForeground(Color.WHITE);
        jbChargeGame.setBackground(Color.BLACK);
        jbChargeGame.setActionCommand("Charge");
        //jbChargeGame.setBounds(375,350,300,75);
        jbChargeGame.setFont(font);
        jbChargeGame.setBorder(border);
        JpCenter.add(jbChargeGame);

        // Boto Delete game
        jbDeleteGame = new JButton("Delete game");
        jbDeleteGame.setForeground(Color.WHITE);
        jbDeleteGame.setBackground(Color.BLACK);
        jbDeleteGame.setActionCommand("Delete");
        //jbDeleteGame.setBounds(375,350,300,75);
        jbDeleteGame.setFont(font);
        jbDeleteGame.setBorder(border);
        JpCenter.add(jbDeleteGame);

        JpBody.add(JpCenter);
        JpBody.setOpaque(false);
        background.add(JpBody, BorderLayout.CENTER);

        //setContentPane(background);
        add(background);
        setVisible(true);
    }

    public void mainController(ActionListener actionListener) {
        jbNewGame.addActionListener(actionListener);
        jbConfiguredGame.addActionListener(actionListener);
        jbChargeGame.addActionListener(actionListener);
        jbDeleteGame.addActionListener(actionListener);
        configButton.addActionListener(actionListener);
    }
}

