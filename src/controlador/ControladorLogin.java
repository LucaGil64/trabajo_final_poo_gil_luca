package controlador;

import db.Jugador;
import db.JugadorDAOImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.EstadoJuego;
import vista.PanelJuego;
import vista.PanelLogin;
import vista.PanelMenu;
import vista.PanelOpciones;
import vista.VistaPrincipal;

public class ControladorLogin implements ActionListener{

    private PanelLogin panelLogin;

    private PanelMenu panelMenu;
    private ControladorMenu controladorMenu;

    private PanelOpciones panelOpciones;
    private ControladorOpciones controladorOpciones;

    private PanelJuego panelJuego;
    private ControladorJuego controladorJuego;


    public ControladorLogin(PanelLogin panelLogin) {

        this.panelLogin = panelLogin;
        VistaPrincipal.setPanelContenedor(panelLogin);



        this.panelLogin.getbotonMenu().addActionListener(e -> {
            System.out.println("Boton volver al menu");
            panelMenu = new PanelMenu();
            controladorMenu = new ControladorMenu(panelMenu);
        });

        this.panelLogin.getBotonOpciones().addActionListener(e -> {
            System.out.println("Boton opciones");
            panelOpciones = new PanelOpciones();
            controladorOpciones = new ControladorOpciones(panelOpciones);
        });

        this.panelLogin.getBotonLogin().addActionListener(e -> {


            JugadorDAOImpl jugadorDao = new JugadorDAOImpl();
            ArrayList<Jugador> jugadoresTop10 = jugadorDao.top10();
            boolean nombreYaUsado = false;


            for (Jugador jugador : jugadoresTop10) {
                if (jugador.getNombre().equals(panelLogin.getTextFieldLogin().getText())) {
                    nombreYaUsado = true;
                    break;
                }
            }

            if (panelLogin.getTextFieldLogin().getText().isBlank() || panelLogin.getTextFieldLogin().getText().length() <= 3) {
                JOptionPane.showMessageDialog(panelLogin, "Ingresa un nombre de usuario valido", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (nombreYaUsado) {
                JOptionPane.showMessageDialog(panelLogin, "Nombre de usuario no disponible", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {

                System.out.println("Boton login");
                EstadoJuego.getInstance().setNombreJugador(panelLogin.getTextFieldLogin().getText());
                panelJuego = new PanelJuego();
                controladorJuego = new ControladorJuego(panelJuego);

            }


        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
