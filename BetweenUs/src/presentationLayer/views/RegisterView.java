package presentationLayer.views;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class RegisterView extends JFrame {

    private JLabel background;
    private JButton jbRegister;
    private JButton jbLogin;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;

    public RegisterView() {
        setVisible(true);
        setTitle("Register"); // titol
        setSize(1080, 600); // tamaño de la caja
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

        JLabel nameLabel = new JLabel("Username", JLabel.LEFT);
        nameLabel.setBounds(25,10,150,50);
        nameLabel.setFont(new Font("", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        jpVista.add(nameLabel);
        nameField = new JTextField();
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);
        nameField.setFont(new Font("", Font.BOLD, 20));
        nameField.setBounds(23,55,350,40);
        jpVista.add(nameField);

        JLabel emailLabel = new JLabel("Email", JLabel.LEFT);
        emailLabel.setBounds(25,100,150,50);
        emailLabel.setFont(new Font("", Font.BOLD, 24));
        emailLabel.setForeground(Color.WHITE);
        jpVista.add(emailLabel);
        emailField = new JTextField();
        emailField.setBackground(Color.BLACK);
        emailField.setForeground(Color.WHITE);
        emailField.setFont(new Font("", Font.BOLD, 20));
        emailField.setBounds(23,145,350,40);
        jpVista.add(emailField);

        JLabel passwordLabel = new JLabel("Password", JLabel.LEFT);
        passwordLabel.setBounds(25,190,150,50);
        passwordLabel.setFont(new Font("", Font.BOLD, 24));
        passwordLabel.setForeground(Color.WHITE);
        jpVista.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("", Font.BOLD, 24));
        passwordField.setBounds(23,235,350,40);
        jpVista.add(passwordField);

        JLabel repeatPasswordLabel = new JLabel("Repeat password", JLabel.LEFT);
        repeatPasswordLabel.setBounds(25,290,250,50);
        repeatPasswordLabel.setFont(new Font("", Font.BOLD, 24));
        repeatPasswordLabel.setForeground(Color.WHITE);
        jpVista.add(repeatPasswordLabel);
        repeatPasswordField = new JPasswordField();
        repeatPasswordField.setBackground(Color.BLACK);
        repeatPasswordField.setForeground(Color.WHITE);
        repeatPasswordField.setFont(new Font("", Font.BOLD, 24));
        repeatPasswordField.setBounds(23,335,350,40);
        jpVista.add(repeatPasswordField);

        jbRegister = new JButton("Register");
        jbRegister.setFont(new Font("", Font.BOLD, 24));
        jbRegister.setActionCommand("Register"); //
        jbRegister.setBackground(Color.BLACK);
        jbRegister.setForeground(Color.WHITE);
        jbRegister.setBounds(125,390,150,40);
        jpVista.add(jbRegister);

        JLabel loginMessageLabel = new JLabel("Already have an account?", JLabel.LEFT);
        loginMessageLabel.setBounds(110,430,250,50);
        loginMessageLabel.setFont(new Font("", Font.BOLD, 16));
        loginMessageLabel.setForeground(Color.WHITE);
        jpVista.add(loginMessageLabel);

        jbLogin = new JButton("Login");
        jbLogin.setFont(new Font("", Font.BOLD, 16));
        jbLogin.setActionCommand("Login");
        jbLogin.setBackground(Color.BLACK);
        jbLogin.setForeground(Color.WHITE);
        jbLogin.setBounds(160,475,80,35);
        jpVista.add(jbLogin);



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
    public String getRepeatPassword() { return String.valueOf(repeatPasswordField.getPassword()); }

    public void printRegisterErrors(int numError, String passwordError) {
        switch (numError) {
            case 0:

                break;
            case 1:
                JOptionPane.showMessageDialog(null, "ERROR: El nom d'usuari ja existeix", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "ERROR: Les contrasenyes han de coincidir", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, passwordError, "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "ERROR: El format del email és incorrecte", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "ERROR: El correu ja existeix", "Error Registre", JOptionPane.ERROR_MESSAGE);
                break;
            default: break;
        }
    }
}