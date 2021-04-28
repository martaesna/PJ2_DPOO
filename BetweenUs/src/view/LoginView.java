package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoginView extends JFrame {
    private JLabel background;
    private JButton jbRegister;
    private JButton jbLogin;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;



    public LoginView() {
        setVisible(true);
        setTitle("Login"); // titol
        setSize(1080, 600); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);


        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/model/images/background.jpg"));
            Image scaled = image.getScaledInstance(1080, 600, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            background = new JLabel("", backgroundImage, JLabel.CENTER);
            background.setBounds(0,0,1080,600);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Creem un panell
        JPanel jpVista = new JPanel();
        jpVista.setBackground(new Color(255,0,0,99));
        jpVista.setLayout(null);
        jpVista.setBounds(340,150,400,300);


        JLabel nameLabel = new JLabel("Username or Email", JLabel.LEFT);
        nameLabel.setBounds(25,10,300,50);
        nameLabel.setFont(new Font("Russo One", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        jpVista.add(nameLabel);
        nameField = new JTextField();
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);
        nameField.setFont(new Font("Russo One", Font.BOLD, 20));
        nameField.setBounds(23,55,350,40);
        jpVista.add(nameField);



        JLabel passwordLabel = new JLabel("Password", JLabel.LEFT);
        passwordLabel.setBounds(25,90,150,50);
        passwordLabel.setFont(new Font("Russo One", Font.BOLD, 24));
        passwordLabel.setForeground(Color.WHITE);
        jpVista.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Russo One", Font.BOLD, 24));
        passwordField.setBounds(23,135,350,40);
        jpVista.add(passwordField);




        jbLogin = new JButton("Login");
        jbLogin.setFont(new Font("Russo One", Font.BOLD, 24));
        jbLogin.setActionCommand("Register"); //
        jbLogin.setBackground(Color.BLACK);
        jbLogin.setForeground(Color.WHITE);
        jbLogin.setBounds(125,140,150,40);
        jpVista.add(jbLogin);

        JLabel loginMessageLabel = new JLabel("Don't have an account?", JLabel.LEFT);
        loginMessageLabel.setBounds(110,200,250,50);
        loginMessageLabel.setFont(new Font("Russo One", Font.BOLD, 16));
        loginMessageLabel.setForeground(Color.WHITE);
        jpVista.add(loginMessageLabel);

        jbRegister = new JButton("Register");
        jbLogin.setFont(new Font("Russo One", Font.BOLD, 16));
        jbLogin.setActionCommand("Register"); //
        jbLogin.setBackground(Color.BLACK);
        jbLogin.setForeground(Color.WHITE);
        jbLogin.setBounds(160,250,80,35);
        jpVista.add(jbRegister);



        setContentPane(background);
        getContentPane().add(jpVista);
    }

    public void mainController(ActionListener actionListener) {
        jbRegister.addActionListener(actionListener);
        jbLogin.addActionListener(actionListener);
    }

    public String getUsername() { return nameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return String.valueOf(passwordField.getPassword()); }

}