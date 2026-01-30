package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import modelo.EstadoJuego;
import vista.PanelFinJuego;
import vista.PanelJuego;
import vista.PanelMenu;
import vista.PanelRanking;

public class ControladorFinJuego implements ActionListener{

    private PanelFinJuego panelFinJuego;
    private JFrame framePrincipal;

    private PanelMenu panelMenu;
    private ControladorMenu controladorMenu;
    private PanelRanking panelRanking;
    private ControladorRanking controladorRanking;

    public ControladorFinJuego(PanelFinJuego panelFinJuego, PanelJuego panelJuego) {
        this.panelFinJuego = panelFinJuego;

        framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this.panelFinJuego);

        JDialog dialogo = new JDialog(framePrincipal , "Fin Juego", true);
        dialogo.setSize(625, 500);

        dialogo.add(panelFinJuego);


        System.out.println(EstadoJuego.getInstance().getNombreJugador());
        this.panelFinJuego.setDatos(EstadoJuego.getInstance().getNombreJugador(), EstadoJuego.getInstance().getPuntaje());


        this.panelFinJuego.getBotonMenu().addActionListener(e -> {
            System.out.println("Boton Menu");

            panelJuego.getGameLoop().detener();
            dialogo.dispose();
            panelMenu = new PanelMenu();
            controladorMenu = new ControladorMenu(panelMenu);
        });

        this.panelFinJuego.getBotonRanking().addActionListener(e -> {
            System.out.println("Boton ranking");

            panelRanking = new PanelRanking();
            controladorRanking = new ControladorRanking(panelRanking);
        });




        dialogo.setUndecorated(true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
