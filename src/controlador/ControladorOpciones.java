package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import modelo.ModeloOpciones;
import vista.PanelOpciones;

public class ControladorOpciones implements ActionListener{

    public PanelOpciones panelOpciones;
    public JFrame framePrincipal;

    public ControladorOpciones(PanelOpciones panelOpciones) {
        this.panelOpciones = panelOpciones;

        framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this.panelOpciones);

        JDialog dialogo = new JDialog(framePrincipal , "Opciones", true);
        dialogo.setSize(625, 500);

        dialogo.add(panelOpciones);





        this.panelOpciones.getSliderVolumen().setValue(ModeloOpciones.getInstance().getVolumen());

        this.panelOpciones.getSliderVolumen().addChangeListener(e -> {
            ModeloOpciones.getInstance().setVolumen(this.panelOpciones.getSliderVolumen().getValue());
        });



        this.panelOpciones.getBotonFlechasF().setSelected(ModeloOpciones.getInstance().getFlechasFSeleccionado());
        this.panelOpciones.getBotonWasdK().setSelected(ModeloOpciones.getInstance().getWasdKSeleccionado());

        ActionListener listenerControles = e -> {
            if (this.panelOpciones.getBotonFlechasF().isSelected()) {
                System.out.println("Se selecciono el control flechas + f");
                ModeloOpciones.getInstance().setFlechasFSeleccionado();
                this.panelOpciones.getBotonFlechasF().setSelected(true);
                this.panelOpciones.getBotonWasdK().setSelected(false);

                ControladorInput.setEstrategiaFlechas();

            } else if (this.panelOpciones.getBotonWasdK().isSelected()) {
                System.out.println("Se selecciono el control wasd + k");
                ModeloOpciones.getInstance().setWasdKSeleccionado();
                this.panelOpciones.getBotonWasdK().setSelected(true);
                this.panelOpciones.getBotonFlechasF().setSelected(false);

                ControladorInput.setEstrategiaWasd();

            }
        };
        

        this.panelOpciones.getBotonFlechasF().addActionListener(listenerControles);
        this.panelOpciones.getBotonWasdK().addActionListener(listenerControles);

        



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
