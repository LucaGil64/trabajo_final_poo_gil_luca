package vista;

import java.awt.*;
import javax.swing.*;

public class PanelOpciones extends JPanel{

    JLabel labelVolumen, labelControles;
    JSlider sliderVolumen;

    JPanel contenedorControles;
    ButtonGroup grupoControles;
    JRadioButton wasdK, flechasF;
    

    public PanelOpciones() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        labelVolumen = new JLabel("Volumen");
        labelVolumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelVolumen.setFont(FuenteUtils.getFuente().deriveFont(30f));
        labelVolumen.setForeground(Color.YELLOW);

        sliderVolumen = new JSlider(0, 100, 50);
        sliderVolumen.setBackground(Color.BLACK);
        sliderVolumen.setForeground(Color.WHITE);
        sliderVolumen.setFont(FuenteUtils.getFuente().deriveFont(12f));
        sliderVolumen.setMajorTickSpacing(25);
        sliderVolumen.setPaintTicks(true);
        sliderVolumen.setPaintLabels(true);
        sliderVolumen.setMaximumSize(new Dimension(500, 60));
        sliderVolumen.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelControles = new JLabel("Controles");
        labelControles.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelControles.setFont(FuenteUtils.getFuente().deriveFont(30f));
        labelControles.setForeground(Color.YELLOW);

        contenedorControles = new JPanel();
        Dimension tamañoFijo = new Dimension(Integer.MAX_VALUE, 100);
        contenedorControles.setMaximumSize(tamañoFijo);
        contenedorControles.setPreferredSize(tamañoFijo);
        contenedorControles.setMinimumSize(new Dimension(0, 100));
        contenedorControles.setLayout(new GridBagLayout());
        contenedorControles.setBackground(Color.BLACK);
        contenedorControles.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Fila 1: Labels
        gbc.gridy = 0;
        gbc.gridx = 0;
        JLabel labelFlechasF = new JLabel("Flechas + F", SwingConstants.CENTER);
        labelFlechasF.setFont(FuenteUtils.getFuente().deriveFont(20f));
        labelFlechasF.setForeground(Color.YELLOW);
        contenedorControles.add(labelFlechasF, gbc);

        gbc.gridx = 1;
        JLabel labelWasdK = new JLabel("WASD + K", SwingConstants.CENTER);
        labelWasdK.setFont(FuenteUtils.getFuente().deriveFont(20f));
        labelWasdK.setForeground(Color.YELLOW);
        contenedorControles.add(labelWasdK, gbc);

        // Fila 2: RadioButtons
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        JPanel panelRadioButtons = new JPanel();
        panelRadioButtons.setBackground(Color.BLACK);
        panelRadioButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 220, 0));
        flechasF = new JRadioButton();
        wasdK = new JRadioButton();

        flechasF.setBackground(Color.BLACK);
        flechasF.setSelected(true);

        wasdK.setBackground(Color.BLACK);        

        grupoControles = new ButtonGroup();
        grupoControles.add(flechasF);
        grupoControles.add(wasdK);


        panelRadioButtons.add(flechasF);
        panelRadioButtons.add(wasdK);
        
        contenedorControles.add(panelRadioButtons, gbc);


        this.add(labelVolumen);
        this.add(Box.createVerticalStrut(25));
        this.add(sliderVolumen);
        this.add(Box.createVerticalStrut(50));
        this.add(labelControles);
        this.add(Box.createVerticalStrut(25));
        this.add(contenedorControles);
        

    }

}
