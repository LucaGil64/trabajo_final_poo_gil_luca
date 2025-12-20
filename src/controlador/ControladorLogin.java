package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            System.out.println("Boton login");
            panelJuego = new PanelJuego();
            controladorJuego = new ControladorJuego(panelJuego);
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
