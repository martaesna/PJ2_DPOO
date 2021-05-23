package presentationLayer.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LoginView extends JFrame {
    private JLabel background;
    private final JButton jbRegister;
    private final JButton jbLogin;
    private final JTextField nameField;
    private final JPasswordField passwordField;


    /**
     * Printa la vista del Log in
     */
    public LoginView() {
        setVisible(true);
        setTitle("Login"); // titol
        setSize(1080, 600); // tamaño de la cajacfgggggggggggggggggggg
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/background.jpg")));
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
        jpVista.setBounds(340,20,400,520);

        JLabel nameLabel = new JLabel("Username or Email", JLabel.LEFT);
        nameLabel.setBounds(25,10,300,50);
        nameLabel.setFont(new Font("", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        jpVista.add(nameLabel);
        nameField = new JTextField();
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);
        nameField.setFont(new Font("", Font.BOLD, 20));
        nameField.setBounds(23,55,350,40);
        jpVista.add(nameField);

        JLabel passwordLabel = new JLabel("Password", JLabel.LEFT);
        passwordLabel.setBounds(25,100,150,50);
        passwordLabel.setFont(new Font("", Font.BOLD, 24));
        passwordLabel.setForeground(Color.WHITE);
        jpVista.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("", Font.BOLD, 24));
        passwordField.setBounds(23,145,350,40);
        jpVista.add(passwordField);

        jbLogin = new JButton("Login");
        jbLogin.setFont(new Font("", Font.BOLD, 24));
        jbLogin.setActionCommand("Login"); //
        jbLogin.setBackground(Color.BLACK);
        jbLogin.setForeground(Color.WHITE);
        jbLogin.setBounds(125,200,150,40);
        jpVista.add(jbLogin);

        JLabel loginMessageLabel = new JLabel("Don't have an account?", JLabel.LEFT);
        loginMessageLabel.setBounds(110,240,250,50);
        loginMessageLabel.setFont(new Font("", Font.BOLD, 16));
        loginMessageLabel.setForeground(Color.WHITE);
        jpVista.add(loginMessageLabel);

        jbRegister = new JButton("Register");
        jbRegister.setFont(new Font("", Font.BOLD, 16));
        jbRegister.setActionCommand("Register"); //
        jbRegister.setBackground(Color.BLACK);
        jbRegister.setForeground(Color.WHITE);
        jbRegister.setBounds(140,285,120,35);
        jpVista.add(jbRegister);


        setContentPane(background);
        getContentPane().add(jpVista);
    }

    /**
     * Fa que quan apretem els botons el cotroller ho sapiga
     * @param actionListener escoltador d'accions
     */
    public void mainController(ActionListener actionListener) {
        jbRegister.addActionListener(actionListener);
        jbLogin.addActionListener(actionListener);
    }


    public String getUsername() { return nameField.getText(); }
    public String getPassword() { return String.valueOf(passwordField.getPassword()); }

    /**
     * mostra un JoptionPane que ens diu que les dades son incorrectes
     */
    public void printError() {
        JOptionPane.showMessageDialog(null, "ERROR: Les credencials introduïdes són incorrectes", "Error Login", JOptionPane.ERROR_MESSAGE);
    }
}