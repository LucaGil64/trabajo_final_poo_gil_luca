package vista;

import java.awt.*;
import javax.swing.*;

public class PanelMenu extends JPanel{

    JLabel labelBattle, labelCity;
    JButton botonJugar, botonRanking, botonOpciones;
    public PanelMenu() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        
        labelBattle = new JLabel("BATTLE");
        labelBattle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelBattle.setFont(FuenteUtils.getFuente().deriveFont(120f));
        labelBattle.setForeground(Color.YELLOW);

        labelCity = new JLabel("CITY");
        labelCity.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelCity.setFont(FuenteUtils.getFuente().deriveFont(120f));
        labelCity.setForeground(Color.YELLOW);

        
        Dimension dimensionBoton = new Dimension(400, 50);
        botonJugar = new JButton("Jugar");
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonJugar.setFont(FuenteUtils.getFuente().deriveFont(40f));
        botonJugar.setBackground(Color.WHITE);
        botonJugar.setForeground(Color.BLACK);
        botonJugar.setPreferredSize(dimensionBoton);
        botonJugar.setMaximumSize(dimensionBoton);
        botonJugar.setMinimumSize(dimensionBoton);

        botonRanking = new JButton("Ranking");
        botonRanking.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRanking.setFont(FuenteUtils.getFuente().deriveFont(40f));
        botonRanking.setBackground(Color.WHITE);
        botonRanking.setForeground(Color.BLACK);
        botonRanking.setPreferredSize(dimensionBoton);
        botonRanking.setMaximumSize(dimensionBoton);
        botonRanking.setMinimumSize(dimensionBoton);

        botonOpciones = new JButton("Opciones");
        botonOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonOpciones.setFont(FuenteUtils.getFuente().deriveFont(40f));
        botonOpciones.setBackground(Color.WHITE);
        botonOpciones.setForeground(Color.BLACK);
        botonOpciones.setPreferredSize(dimensionBoton);
        botonOpciones.setMaximumSize(dimensionBoton);
        botonOpciones.setMinimumSize(dimensionBoton);

        


        
        
        



        this.add(Box.createVerticalStrut(100));

        this.add(labelBattle);
        this.add(labelCity);
        this.add(Box.createVerticalStrut(100));
        this.add(botonJugar);
        this.add(Box.createVerticalStrut(20));
        this.add(botonRanking);
        this.add(Box.createVerticalStrut(20));
        this.add(botonOpciones);

        this.add(Box.createVerticalGlue());
    }

    public JButton getBotonJugar() {
        return botonJugar;
    }

    public JButton getBotonRanking() {
        return botonRanking;
    }

    public JButton getBotonOpciones() {
        return botonOpciones;
    }
}
