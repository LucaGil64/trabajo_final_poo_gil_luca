package modelo.proyectil;

import controlador.ControladorSonido;
import controlador.ControladorSprites;
import java.awt.image.BufferedImage;
import modelo.Mapa;
import modelo.obstaculo.Obstaculo;

public class HiloAnimacionProyectil extends Obstaculo implements Runnable{

    private int i = 0;

    private static BufferedImage img1 = ControladorSprites.getSprite(0 , 224, 16, 16);
    private static BufferedImage img2 = ControladorSprites.getSprite(16 , 224, 16, 16);
    private static BufferedImage img3 = ControladorSprites.getSprite(32 , 224, 16, 16);

    public HiloAnimacionProyectil(int posX, int posY) {
        super(posX, posY, 4, 16, 16, ControladorSprites.getSprite(0, 224, 16, 16), false, false, false);
        this.posX = posX - 6;
        this.posY = posY - 6;
    }

    @Override
    public void run() {
        Mapa.getInstance().addObjeto(this);
        ControladorSonido.reproducir("explosion-chica");

        while (i < 3) {
            try {
            
                if (i == 0) this.sprite = this.img1;
                if (i == 1) this.sprite = this.img2;
                if (i == 2) this.sprite = this.img3;
                if (i == 3) this.existe = false;    
                i++;

                Thread.sleep(50);
            } catch (Exception e) {
                System.err.println("FUNCO MAL");
                return;
            }
        }

        this.destruir();

    }       

    @Override
    public boolean esSolido() {
        return false;
    }

    

}
