package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class PanelLogin extends JPanel{

    private JPanel lateral;
    private JButton botonMenu;
    private JButton botonOpciones;

    private JPanel contenedor;
    private JLabel labelLogin;
    private JTextField textFieldLogin;
    private JButton botonLogin;

    public PanelLogin() {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        lateral = new JPanel();
        lateral.setLayout(new BoxLayout(lateral, BoxLayout.Y_AXIS));
        lateral.setBackground(Color.GRAY);
        lateral.setPreferredSize(new Dimension(200, 0));

        Dimension dimensionBotonLateral = new Dimension(180, 50);
        botonMenu = new JButton("Menu");
        botonMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonMenu.setFont(FuenteUtils.getFuente().deriveFont(18f));
        botonMenu.setBackground(Color.WHITE);
        botonMenu.setForeground(Color.BLACK);
        botonMenu.setPreferredSize(dimensionBotonLateral);
        botonMenu.setMaximumSize(dimensionBotonLateral);
        botonMenu.setMinimumSize(dimensionBotonLateral);

        botonOpciones = new JButton("Opciones");
        botonOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonOpciones.setFont(FuenteUtils.getFuente().deriveFont(18f));
        botonOpciones.setBackground(Color.WHITE);
        botonOpciones.setForeground(Color.BLACK);
        botonOpciones.setPreferredSize(dimensionBotonLateral);
        botonOpciones.setMaximumSize(dimensionBotonLateral);
        botonOpciones.setMinimumSize(dimensionBotonLateral);
        
        
        lateral.add(Box.createVerticalStrut(50));
        
        lateral.add(botonMenu);
        lateral.add(Box.createVerticalStrut(15));
        lateral.add(botonOpciones);

        this.add(lateral, BorderLayout.WEST);




        Border borde = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(100,100,400,100), BorderFactory.createLineBorder(Color.WHITE, 3));

        contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(Color.BLACK);
        contenedor.setBorder(borde);

        this.add(contenedor, BorderLayout.CENTER);
        

        labelLogin = new JLabel("Login");
        labelLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelLogin.setFont(FuenteUtils.getFuente().deriveFont(30f));
        labelLogin.setForeground(Color.YELLOW);

        Dimension dimensionTextFieldLogin = new Dimension(400, 40);
        textFieldLogin = new JTextField();
        textFieldLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        textFieldLogin.setFont(FuenteUtils.getFuente().deriveFont(20f));
        textFieldLogin.setBackground(Color.WHITE);
        textFieldLogin.setForeground(Color.BLACK);
        textFieldLogin.setPreferredSize(dimensionTextFieldLogin);
        textFieldLogin.setMaximumSize(dimensionTextFieldLogin);
        textFieldLogin.setMinimumSize(dimensionTextFieldLogin);

        Dimension dimensionBotonLogin = new Dimension(400, 50);
        botonLogin = new JButton("Login");
        botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonLogin.setFont(FuenteUtils.getFuente().deriveFont(40f));
        botonLogin.setBackground(Color.WHITE);
        botonLogin.setForeground(Color.BLACK);
        botonLogin.setPreferredSize(dimensionBotonLogin);
        botonLogin.setMaximumSize(dimensionBotonLogin);
        botonLogin.setMinimumSize(dimensionBotonLogin);
    
        contenedor.add(Box.createVerticalStrut(50));
        contenedor.add(labelLogin);
        contenedor.add(Box.createVerticalStrut(50));
        contenedor.add(textFieldLogin);
        contenedor.add(Box.createVerticalStrut(20));
        contenedor.add(botonLogin);
    }

    public JButton getbotonMenu() {
        return this.botonMenu;
    }

    public JButton getBotonOpciones() {
        return this.botonOpciones;
    }

    public JButton getBotonLogin() {
        return this.botonLogin;
    }

    public JTextField getTextFieldLogin() {
        return this.textFieldLogin;
    }

}
