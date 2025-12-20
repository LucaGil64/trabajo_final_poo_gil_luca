package vista;

import controlador.ControladorMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class VistaPrincipal extends JFrame{

    public static JPanel panelContenedor;
    private PanelMenu panelMenu;
    private ControladorMenu controladorMenu;

    public VistaPrincipal() {
        setTitle("Battle City");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panelContenedor = new JPanel();
        panelContenedor.setPreferredSize(new Dimension(1000,800));
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.setBackground(Color.BLACK);
        this.add(panelContenedor);
        

        panelMenu = new PanelMenu();
        controladorMenu = new ControladorMenu(panelMenu);



        pack();
        setLocationRelativeTo(null);
    }

    public static void setPanelContenedor(JPanel panelNuevo) {
        panelContenedor.removeAll();
        panelContenedor.add(panelNuevo, BorderLayout.CENTER);

        // Para que swing refresque y se vea
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

}
