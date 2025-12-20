package controlador;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import vista.PanelRanking;

public class ControladorRanking {

    PanelRanking panelRanking;
    public JFrame framePrincipal;

    public ControladorRanking(PanelRanking panelRanking) {
        this.panelRanking = panelRanking;

        framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this.panelRanking);

        JDialog dialogo = new JDialog(framePrincipal , "Ranking", true);
        dialogo.setSize(800, 640);

        dialogo.add(panelRanking);

        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setVisible(true);

        

    }

}
