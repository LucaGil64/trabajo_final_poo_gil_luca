package controlador;

import java.awt.Font;
import vista.FuenteUtils;
import vista.VistaPrincipal;

public class ControladorVistaPrincipal {

    public ControladorVistaPrincipal() {

        Font pressStart2P = FuenteUtils.cargarFuente("src/resources/fuente/PressStart2P-Regular.ttf", 12f);
        FuenteUtils.setFuentesGlobales(pressStart2P);

        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.setVisible(true);
    
    }

}
