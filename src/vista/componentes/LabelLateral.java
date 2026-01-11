package vista.componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import vista.FuenteUtils;

public class LabelLateral extends JLabel{

    public LabelLateral(String texto) {
        super(texto);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setFont(FuenteUtils.getFuente().deriveFont(18f));
        this.setForeground(Color.BLACK);
        this.setFocusable(false);
    }

}
