package controlador;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ControladorSprites {

    private static BufferedImage masterSheet;

    static {
        try {
            masterSheet = ImageIO.read(new File("src/resources/masterSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getSprite(int x, int y, int ancho, int alto) {
        return masterSheet.getSubimage(x, y, ancho, alto);
    }

}
