package vista;

import java.awt.*;
import javax.swing.*;

public class PanelFinJuego extends JPanel {

    private JLabel labelTitulo; // Un "GAME OVER" queda bien
    private JLabel labelNombreJugador;
    private JLabel labelPuntos;
    private JButton botonMenu, botonRanking;

    public PanelFinJuego() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        

        labelTitulo = new JLabel("JUEGO TERMINADO");
        estilizarLabel(labelTitulo, 30, Color.RED); // Rojo para impacto
        
        labelNombreJugador = new JLabel("JUGADOR: ---");
        estilizarLabel(labelNombreJugador, 25, Color.YELLOW);

        labelPuntos = new JLabel("PUNTOS: 0");
        estilizarLabel(labelPuntos, 25, Color.YELLOW);


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0)); // 30px de separación horizontal
        panelBotones.setBackground(Color.BLACK); // Mismo fondo para que sea invisible
        panelBotones.setMaximumSize(new Dimension(600, 60)); // Limitar altura para que no rompa el layout

        botonMenu = crearBoton("MENU");
        botonRanking = crearBoton("RANKING");

        panelBotones.add(botonMenu);
        panelBotones.add(botonRanking);

        // --- 4. ARMADO DEL LAYOUT (Centrado Vertical con 'Glues') ---
        
        this.add(labelTitulo);

        this.add(Box.createVerticalGlue());

        this.add(labelNombreJugador);
        this.add(Box.createVerticalStrut(20)); // Espacio vacio
        this.add(labelPuntos);

        this.add(Box.createVerticalGlue());
        
        this.add(panelBotones);
    }

    // Metodo para actualizar la info antes de mostrar el panel
    public void setDatos(String nombre, int puntos) {
        this.labelNombreJugador.setText("JUGADOR: " + nombre);
        this.labelPuntos.setText("PUNTOS: " + puntos);
    }


    private void estilizarLabel(JLabel label, float tamanoFuente, Color color) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(color);
        label.setFont(FuenteUtils.getFuente().deriveFont(tamanoFuente));
    }

    private JButton crearBoton(String texto) {
        Dimension dimensionBoton = new Dimension(200, 50);

        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusable(false);
        btn.setFont(FuenteUtils.getFuente().deriveFont(18f));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setPreferredSize(dimensionBoton);
        btn.setMaximumSize(dimensionBoton);
        btn.setMinimumSize(dimensionBoton);

        return btn;
    }


    public JButton getBotonMenu() {
        return botonMenu;
    }

    public JButton getBotonRanking() {
        return botonRanking;
    }
}