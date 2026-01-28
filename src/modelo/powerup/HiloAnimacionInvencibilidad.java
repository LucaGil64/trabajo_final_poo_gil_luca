package modelo.powerup;

import controlador.ControladorSprites;
import java.awt.image.BufferedImage;
import modelo.Mapa;
import modelo.entidad.jugador.TanqueJugador;
import modelo.obstaculo.Obstaculo;

public class HiloAnimacionInvencibilidad extends Obstaculo implements Runnable{

    private int i = 0;

    private static BufferedImage img1 = ControladorSprites.getSprite(0 , 240, 16, 16);
    private static BufferedImage img2 = ControladorSprites.getSprite(16 , 240, 16, 16);

    public HiloAnimacionInvencibilidad(TanqueJugador tanqueJugador) {
        super(tanqueJugador.getBounds().x, tanqueJugador.getBounds().y, 1, 16, 16, ControladorSprites.getSprite(0, 240, 16, 16), false, false, false);

    }

    @Override
    public void run() {
        Mapa.getInstance().addObjeto(this);
        //ControladorSonido.reproducir("explosion-chica");

        while (Mapa.getInstance().getTanqueJugador().esInvencible()) {
            try {
            
                this.posX = Mapa.getInstance().getTanqueJugador().getBounds().x;
                this.posY = Mapa.getInstance().getTanqueJugador().getBounds().y;

                if (i % 2 == 0 ) this.sprite = this.img1;
                if (i % 2 == 1) this.sprite = this.img2;    
                i++;

                Thread.sleep(4);
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
