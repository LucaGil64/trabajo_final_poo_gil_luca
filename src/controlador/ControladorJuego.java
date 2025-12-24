package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.PanelJuego;
import vista.PanelMenu;
import vista.PanelOpciones;
import vista.VistaPrincipal;

public class ControladorJuego implements ActionListener{

    private PanelJuego panelJuego;

    private PanelMenu panelMenu;
    private ControladorMenu controladorMenu;

    private PanelOpciones panelOpciones;
    private ControladorOpciones controladorOpciones;

    public ControladorJuego(PanelJuego panelJuego) {

        this.panelJuego = panelJuego;
        VistaPrincipal.setPanelContenedor(panelJuego);



        this.panelJuego.getBotonMenu().addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                panelJuego,
                "¿Estás seguro de que quieres salir?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                panelJuego.getGameLoop().detener();
                panelMenu = new PanelMenu();
                controladorMenu = new ControladorMenu(panelMenu);
            }
            
        });

        this.panelJuego.getBotonOpciones().addActionListener(e -> {
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
