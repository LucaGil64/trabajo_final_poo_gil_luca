package vista;

import controlador.ControladorInput;
import java.awt.*;
import javax.swing.*;
import modelo.EstadoJuego;
import modelo.GameLoop;
import modelo.Mapa;
import modelo.nivel.GestorNiveles;
import vista.componentes.LabelLateral;

public class PanelJuego extends JPanel{

    private JPanel lateral;
    private JButton botonMenu;
    private JButton botonOpciones;

    private LabelLateral labelEnemigos, labelNumeroEnemigos, labelVidas, labelNumeroVidas, labelNivel, labelNumeroNivel, labelPuntos, labelNumeroPuntos;

    private ContenedorJuego contenedorJuego;
    private GameLoop gameLoop;

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
        botonMenu.setFocusable(false);

        botonOpciones = new JButton("Opciones");
        botonOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonOpciones.setFont(FuenteUtils.getFuente().deriveFont(18f));
        botonOpciones.setBackground(Color.WHITE);
        botonOpciones.setForeground(Color.BLACK);
        botonOpciones.setPreferredSize(dimensionBotonLateral);
        botonOpciones.setMaximumSize(dimensionBotonLateral);
        botonOpciones.setMinimumSize(dimensionBotonLateral);
        botonOpciones.setFocusable(false);

        labelEnemigos = new LabelLateral("Enemigos");
        labelNumeroEnemigos = new LabelLateral("20");


        labelVidas = new LabelLateral("Vidas");
        labelNumeroVidas = new LabelLateral("3");

        labelNivel = new LabelLateral("Nivel");
        labelNumeroNivel = new LabelLateral("1");

        labelPuntos = new LabelLateral("Puntos");
        labelNumeroPuntos = new LabelLateral("0");

        
        lateral.add(Box.createVerticalStrut(50));
        
        lateral.add(botonMenu);
        lateral.add(Box.createVerticalStrut(15));
        lateral.add(botonOpciones);


        lateral.add(Box.createVerticalGlue());


        lateral.add(labelEnemigos);
        lateral.add(labelNumeroEnemigos);
        lateral.add(Box.createVerticalStrut(30));
        lateral.add(labelVidas);
        lateral.add(labelNumeroVidas);
        lateral.add(Box.createVerticalStrut(30));
        lateral.add(labelNivel);
        lateral.add(labelNumeroNivel);
        lateral.add(Box.createVerticalStrut(30));
        lateral.add(labelPuntos);
        lateral.add(labelNumeroPuntos);

        lateral.add(Box.createVerticalStrut(50));

        this.add(lateral, BorderLayout.WEST);



        // 1. Resetear todo y cargar el nivel 1
        EstadoJuego.getInstance().resetearPartida();
        GestorNiveles.getInstance().resetearNiveles();
        GestorNiveles.getInstance().cargarNivelActual();
        Mapa.getInstance().getTanqueJugador().resetearTotal();

        // 2. Para poder moverse
        ControladorInput input = new ControladorInput(Mapa.getInstance().getTanqueJugador());
        this.addKeyListener(input);


        // 3. Inicializar la vista del juego
        contenedorJuego = new ContenedorJuego();
        this.add(contenedorJuego, BorderLayout.CENTER);

        // 4. Arrancar el GameLoop
        gameLoop = new GameLoop(this);
        gameLoop.iniciar();

        // 5. Pedir foco (SIEMPRE AL FINAL) - Para poder usar teclado
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addAncestorListener(new javax.swing.event.AncestorListener() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                // Se ejecuta cuando el panel aparece en pantalla
                event.getComponent().requestFocusInWindow();
            }

            @Override public void ancestorRemoved(javax.swing.event.AncestorEvent event) {}
            @Override public void ancestorMoved(javax.swing.event.AncestorEvent event) {}
        });


    }


    public void actualizarLabels() {
        int enemigosEnCola = GestorNiveles.getInstance().getDatosNivelActual().cuantosEnemigos();
        int enemigosVivos = Mapa.getInstance().getCantidadEnemigosVivos();
        int enemigosTotales = enemigosEnCola + enemigosVivos;

        int vidas = EstadoJuego.getInstance().getVidas();
        int nivel = GestorNiveles.getInstance().getNivelActual();
        int puntos = EstadoJuego.getInstance().getPuntaje();
        
        this.labelNumeroEnemigos.setText(String.valueOf(enemigosTotales));
        this.labelNumeroVidas.setText(String.valueOf(vidas));
        this.labelNumeroNivel.setText(String.valueOf(nivel));
        this.labelNumeroPuntos.setText(String.valueOf(puntos));
    }


    public JButton getBotonMenu() {
        return this.botonMenu;
    }

    public JButton getBotonOpciones() {
        return this.botonOpciones;
    }

    public ContenedorJuego getContenedorJuego() {
        return this.contenedorJuego;
    }

    public GameLoop getGameLoop() {
        return this.gameLoop;
    }

}