package controlador;

import db.Jugador;
import db.JugadorDAOImpl;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import vista.PanelRanking;

public class ControladorRanking {

    PanelRanking panelRanking;
    public JFrame framePrincipal;

    public ControladorRanking(PanelRanking panelRanking) {
        this.panelRanking = panelRanking;

        framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this.panelRanking);

        JDialog dialogo = new JDialog(framePrincipal , "Ranking", true);
        dialogo.setSize(800, 640);


        JugadorDAOImpl jugadorDAO = new JugadorDAOImpl();
        ArrayList<Jugador> jugadores = jugadorDAO.top10();
        
        DefaultTableModel modelo = panelRanking.getModelo();
        modelo.setRowCount(0);

        int posicion = 1;
        for (Jugador jugador : jugadores) {
            Object[] fila = {
                posicion + "º",
                jugador.getNombre(),
                jugador.getPuntos()
            };
            modelo.addRow(fila);
            posicion++;
        }


        dialogo.add(panelRanking);

        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setVisible(true);

        

    }

}
