package vista;

import java.awt.*;
import javax.swing.*;

public class PanelJuego extends JPanel{

    private JPanel lateral;
    private JButton botonMenu;
    private JButton botonOpciones;

    private JPanel contenedorJuego;

    public PanelJuego() {
        
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


        contenedorJuego = new JPanel();
        this.contenedorJuego.setBackground(Color.BLACK);
        this.add(contenedorJuego, BorderLayout.CENTER);

    }


    public JButton getBotonMenu() {
        return this.botonMenu;
    }

    public JButton getBotonOpciones() {
        return this.botonOpciones;
    }

}
