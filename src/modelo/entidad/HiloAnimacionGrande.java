package modelo.entidad;

import controlador.ControladorSonido;
import controlador.ControladorSprites;
import java.awt.image.BufferedImage;
import modelo.Mapa;
import modelo.obstaculo.Obstaculo;

public class HiloAnimacionGrande extends Obstaculo implements Runnable{
    
    private int posX16, posY16;
    private int i = 0;

    private static BufferedImage img1 = ControladorSprites.getSprite(0 , 224, 16, 16);
    private static BufferedImage img2 = ControladorSprites.getSprite(16 , 224, 16, 16);
    private static BufferedImage img3 = ControladorSprites.getSprite(32 , 224, 16, 16);

    private static BufferedImage img4 = ControladorSprites.getSprite(48 , 224, 32, 32);
    private static BufferedImage img5 = ControladorSprites.getSprite(80 , 224, 32, 32);

    private static BufferedImage img6 = ControladorSprites.getSprite(32 , 224, 16, 16);

    public HiloAnimacionGrande(int posX, int posY) {
        super(posX, posY, 2, 16, 16, ControladorSprites.getSprite(0, 224, 16, 16), false, false, false);
        this.posX = posX;
        this.posY = posY;

        this.posX16 = posX;
        this.posY16 = posY;
    }

    @Override
    public void run() {
        Mapa.getInstance().addObjeto(this);
        ControladorSonido.reproducir("explosion-grande");

        while (i < 6) {
            try {
            
                if (i == 0) this.sprite = this.img1;
                else if (i == 1) this.sprite = this.img2;
                else if (i == 2) this.sprite = this.img3;
                else if (i == 3) {
                    this.posX = this.posX - 8;
                    this.posY = this.posY - 8;

                    this.sprite = this.img4;
                }
                else if (i == 4) this.sprite = this.img5;
                else if (i == 5) {
                    this.posX = this.posX16;
                    this.posY = this.posY16;

                    this.sprite = this.img6;
                }
                else if (i == 6) this.existe = false;    
                i++;

                Thread.sleep(75);
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