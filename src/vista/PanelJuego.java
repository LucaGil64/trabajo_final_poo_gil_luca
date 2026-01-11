package vista;

import controlador.ControladorInput;
import java.awt.*;
import javax.swing.*;
import modelo.GameLoop;
import modelo.Mapa;
import modelo.Niveles;
import modelo.entidad.enemigo.TanqueAltaCadencia;
import modelo.entidad.enemigo.TanqueBasico;
import modelo.entidad.enemigo.TanqueBlindado;
import modelo.entidad.enemigo.TanqueRapido;
import modelo.entidad.jugador.TanqueJugador;
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



        // 1. Cargar los obstáculos del nivel en el Mapa
        Mapa.getInstance().cargarNivel(Niveles.NIVEL_1);
        // 2. Crear el tanque del jugador, se usa una variable local pero es para testear
        TanqueJugador tanqueJugador = new TanqueJugador(80, 200);

        // 3. Agregar el tanque a la lista del Mapa para que se dibuje
        Mapa.getInstance().addObjeto(tanqueJugador); 




        // 3.25 Meter un enemigo
        TanqueBasico tanqueBasico = new TanqueBasico(8, 8, 1.0, false);
        Mapa.getInstance().addObjeto(tanqueBasico); 

        TanqueRapido tanqueRapido = new TanqueRapido(30, 8, 1.0, true);
        Mapa.getInstance().addObjeto(tanqueRapido); 

        TanqueAltaCadencia tanqueAltaCadencia = new TanqueAltaCadencia(50, 8, 1.0, true);
        Mapa.getInstance().addObjeto(tanqueAltaCadencia); 

        TanqueBlindado tanqueBlindado = new TanqueBlindado(70, 8, 1.0, false);
        Mapa.getInstance().addObjeto(tanqueBlindado); 


        // 3.5 Prueba de subir de niveles el tanque


        // tanqueJugador.subirNivel();
        // tanqueJugador.subirNivel();
        // tanqueJugador.subirNivel();





        // 4. Configurar el teclado (InputHandler)
        ControladorInput input = new ControladorInput(tanqueJugador);
        this.addKeyListener(input);

        // 5. Inicializar la vista del juego
        contenedorJuego = new ContenedorJuego();
        this.add(contenedorJuego, BorderLayout.CENTER);

        // 6. Arrancar el motor (GameLoop)
        gameLoop = new GameLoop(this);
        gameLoop.iniciar();

        // 7. Pedir foco (SIEMPRE AL FINAL) - Para poder usar teclado
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