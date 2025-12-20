package controlador;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import vista.PanelOpciones;

public class ControladorOpciones {

    public PanelOpciones panelOpciones;
    public JFrame framePrincipal;

    public ControladorOpciones(PanelOpciones panelOpciones) {
        this.panelOpciones = panelOpciones;

        framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this.panelOpciones);

        JDialog dialogo = new JDialog(framePrincipal , "Opciones", true);
        dialogo.setSize(625, 500);

        dialogo.add(panelOpciones);

        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setVisible(true);

        
    }

}
