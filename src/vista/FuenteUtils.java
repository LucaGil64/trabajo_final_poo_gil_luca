package vista;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class FuenteUtils {


    public static Font fuente;

    public static Font cargarFuente(String ruta, float tamano) {
        try {
            // Cargamos el archivo .ttf
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File(ruta)).deriveFont(tamano);
            // La registramos en el sistema
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);
            return fuente;
        } catch (IOException | FontFormatException e) {
            System.err.println("Error cargando la fuente: " + e.getMessage());
            return new Font("Arial", Font.PLAIN, (int)tamano);
        }
    }

    public static void setFuentesGlobales(Font fuente) {
        FontUIResource res = new FontUIResource(fuente);
        Enumeration<Object> keys = UIManager.getLookAndFeelDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource || value instanceof Font) {
                UIManager.put(key, res);
            }
        }
    }

    public static Font getFuente() {
        return fuente;
    };
}