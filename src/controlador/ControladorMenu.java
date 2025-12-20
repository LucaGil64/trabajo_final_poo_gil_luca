package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.PanelLogin;
import vista.PanelMenu;
import vista.PanelOpciones;
import vista.PanelRanking;
import vista.VistaPrincipal;

public class ControladorMenu implements ActionListener{

    private PanelMenu panelMenu;


    private PanelLogin panelLogin;
    private PanelRanking panelRanking;
    private PanelOpciones panelOpciones;

    private ControladorLogin controladorLogin;
    private ControladorRanking controladorRanking;
    private ControladorOpciones controladorOpciones;

    public ControladorMenu(PanelMenu panelMenu) {

        this.panelMenu = panelMenu;
        VistaPrincipal.setPanelContenedor(panelMenu);



        this.panelMenu.getBotonJugar().addActionListener(e -> {
            System.out.println("Boton jugar");

            panelLogin = new PanelLogin();
            controladorLogin = new ControladorLogin(panelLogin);


        });

        this.panelMenu.getBotonRanking().addActionListener(e -> {
            System.out.println("Boton ranking");

            panelRanking = new PanelRanking();
            controladorRanking = new ControladorRanking(panelRanking);


            

        });

        this.panelMenu.getBotonOpciones().addActionListener(e -> {
            System.out.println("Boton opciones");

            panelOpciones = new PanelOpciones();
            controladorOpciones = new ControladorOpciones(panelOpciones);
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
